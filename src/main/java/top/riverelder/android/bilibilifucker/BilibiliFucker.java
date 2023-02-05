package top.riverelder.android.bilibilifucker;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookConstructor;
import static de.robv.android.xposed.XC_MethodReplacement.DO_NOTHING;

import android.view.View;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class BilibiliFucker implements IXposedHookLoadPackage {

    public static final String LOG_HEADER = "BilibiliFucker: ";

    public static final Set<String> VALID_TARGET_PACKAGE_NAMES = new HashSet<>(Arrays.asList(
            "com.bilibili.app.in",
            "tv.danmaku.bili"
    ));

    public static void log(String message) {
        XposedBridge.log(LOG_HEADER + message);
    }

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        String packageName = lpparam.packageName;

        if (!VALID_TARGET_PACKAGE_NAMES.contains(packageName)) return;

        log("Loaded app: " + packageName);
        log("Start hook: " + packageName);

        // Debug用的view绑定
//        hookViewClickEvent();


        // 解除长按快进
        try {
            findAndHookMethod("tv.danmaku.biliplayerimpl.gesture.GestureService", lpparam.classLoader, "f6", DO_NOTHING);
            log("Hook succeeded: Preventing Function: LongPressToFastForward");
        } catch (Exception e) {
            log("Hook failed: Preventing Function: LongPressToFastForward");
            log("message: " + e.getMessage());
        }

        // 解除评论区关键字蓝字
        try {
            findAndHookMethod(
                    "com.bilibili.app.comm.comment2.model.rpc.CommentRpcKt",
                    lpparam.classLoader,
                    "p",
                    "com.bapis.bilibili.main.community.reply.v1.ReplyInfo",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            log("comment result = " + param.getResult());
                        }
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Object comment = param.getResult();
//                            log("Someone is getting BiliComment with fields: ");
//                            printBean(comment);
                            try {
                                setDescendantField(comment, new String[]{ "mContent", "jumpUrls" }, null);
                                param.setResult(comment);
                            } catch (Exception ignored) { }

//                            log("Someone is constructing BiliComment with fields: ");
//                            printFields(param.thisObject);
//                            log("Someone received BiliCommentDetail: ");
//                            log("comment result = " + param.getResult());
                        }
                    });
            log("Hook succeeded: Preventing Function: KeyWorkLink");
        } catch (Exception e) {
            log("Hook failed: Preventing Function: KeyWorkLink");
            log("message: " + e.getMessage());
        }

        // 禁止在点击UP头像时转跳到直播间
        try {
//            "tv.danmaku.bili.videopage.common.widget.view.VerifyAvatarFrameLayout",
            findAndHookMethod(
                    "tv.danmaku.bili.videopage.data.view.model.OwnerExt",
                    lpparam.classLoader,
                    "hasLive",
                    new XC_MethodReplacement() {
                        @Override
                        public Object replaceHookedMethod(MethodHookParam param) {
                            return false;
                        }
                    });
            log("Hook succeeded: Preventing Function: JumpToLiveRoomWhileClickingVideoPageAuthorAvatar");
        } catch (Exception e) {
            log("Hook failed: Preventing Function: JumpToLiveRoomWhileClickingVideoPageAuthorAvatar");
            log("message: " + e.getMessage());
        }

        log("Hook finished: " + packageName);
    }

    public static void printBean(Object object) {
        printBean(object, 1, new HashSet<>());
    }

    public static void printBean(Object object, int indentLevel, Set<Object> visited) {
        if (object == null) return;

        String indent = repeatString("|---", indentLevel);
        Class<?> clazz = object.getClass();

        if (!isLiteral(clazz)) {
            visited.add(object);
        }

        if (clazz.isArray()) {
            int length = Array.getLength(object);
            for (int i = 0; i < length; i++) {
                printBeanLine(indent, "[" + i + "]", Array.get(object, i), indentLevel, visited);
            }
        } if (object instanceof Collection) {
            Collection<?> collection = (Collection<?>) object;
            int i = 0;
            for (Object value : collection) {
                printBeanLine(indent, "[" + i + "]", value, indentLevel, visited);
                i++;
            }
        } if (object instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) object;
            Set<? extends Map.Entry<?, ?>> entries = map.entrySet();
            for (Map.Entry<?, ?> entry : entries) {
                printBeanLine(indent, "[" + entry.getKey() + "]", entry.getValue(), indentLevel, visited);
            }
        } else {
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) continue;

                field.setAccessible(true);
                try {
                    printBeanLine(indent, field.getName(), field.get(object), indentLevel, visited);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printBeanLine(String indent, String name, @Nullable Object value, int indentLevel, Set<Object> visited) {
        String valueString = (value instanceof String && ((String) value).matches("^\\s*$")) ? "\"\"" : String.valueOf(value);
        if (value == null || isLiteral(value.getClass())) {
            log(indent + name + ": " + valueString);
        } else {
            if (visited.contains(value)) {
                log(indent + name + ": " + valueString + " (loop reference)");
            } else {
                log(indent + name + ": " + valueString);
                printBean(value, indentLevel + 1, visited);
            }
        }
    }

    public static boolean isLiteral(Class<?> clazz) {
        return clazz.isPrimitive() || clazz == String.class;
    }

    public static String repeatString(String string, int amount) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            builder.append(string);
        }
        return builder.toString();
    }

    public static void printStackTrace() {
        for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
            log(stackTraceElement.getClassName() + "#" + stackTraceElement.getMethodName());
        }
    }

    public static void setDescendantField(Object object, String[] path, @Nullable Object value) throws Exception {
        Object currentObject = object;
        Field field = null;
        for (int i = 0; i < path.length; i++) {
            String fieldName = path[i];
            Class<?> clazz = Objects.requireNonNull(currentObject).getClass();
            field = clazz.getDeclaredField(fieldName);
            Objects.requireNonNull(field).setAccessible(true);
            if (i < path.length - 1) {
                currentObject = field.get(currentObject);
            }
        }
        Objects.requireNonNull(field).set(currentObject, value);
    }

    public static void hookViewClickEvent() {
        try {
            findAndHookMethod(
                    View.class,
                    "setOnClickListener",
                    View.OnClickListener.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            View.OnClickListener l = (View.OnClickListener) param.args[0];
                            param.args[0] = new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    log("!!! on click view: " + v);
                                    log("!-- listener: " + l);
                                    printStackTrace();
                                    l.onClick(v);
                                }
                            };
                        }
                    });
            log("Hook succeeded: ViewDebug");
        } catch (Exception e) {
            log("Hook failed: ViewDebug");
            log("message: " + e.getMessage());
        }
    }

}