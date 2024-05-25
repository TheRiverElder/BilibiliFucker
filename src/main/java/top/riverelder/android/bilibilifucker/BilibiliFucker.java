package top.riverelder.android.bilibilifucker;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findAndHookConstructor;
import static de.robv.android.xposed.XC_MethodReplacement.DO_NOTHING;
import static top.riverelder.android.bilibilifucker.Utils.log;
import static top.riverelder.android.bilibilifucker.Utils.printBean;
import static top.riverelder.android.bilibilifucker.Utils.printStackTrace;
import static top.riverelder.android.bilibilifucker.Utils.setDescendantField;
import static top.riverelder.android.bilibilifucker.Utils.tryGetDescendantField;
import static top.riverelder.android.bilibilifucker.Utils.trySetDescendantField;

import android.view.View;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class BilibiliFucker implements IXposedHookLoadPackage {

    public static final Set<String> VALID_TARGET_PACKAGE_NAMES = new HashSet<>(Arrays.asList(
            "com.bilibili.app.in",
            "tv.danmaku.bili"
    ));

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        String packageName = lpparam.packageName;

        if (!VALID_TARGET_PACKAGE_NAMES.contains(packageName)) return;

        log("Loaded app: " + packageName);
        log("Start hook: " + packageName);

//        if (!lpparam.isFirstApplication) return;

        ClassLoader classLoader = lpparam.classLoader;

        // 解除长按快进
//        try {
//            findAndHookMethod("tv.danmaku.biliplayerimpl.gesture.GestureService", lpparam.classLoader, "f6", DO_NOTHING);
//            log("Hook succeeded: Preventing Function: LongPressToFastForward");
//        } catch (Exception e) {
//            log("Hook failed: Preventing Function: LongPressToFastForward");
//            log("message: " + e.getMessage());
//        }

        // 解除评论区关键字蓝字
//        try {
//            findAndHookMethod(
//                    "com.bilibili.app.comm.comment2.model.rpc.CommentRpcKt",
//                    lpparam.classLoader,
//                    "p",
//                    "com.bapis.bilibili.main.community.reply.v1.ReplyInfo",
//                    new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                            log("comment result = " + param.getResult());
//                        }
//
//                        @Override
//                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                            Object comment = param.getResult();
////                            log("Someone is getting BiliComment with fields: ");
////                            printBean(comment);
//                            try {
//                                setDescendantField(comment, new String[]{"mContent", "jumpUrls"}, null);
//                                param.setResult(comment);
//                            } catch (Exception ignored) {
//                            }
//
////                            log("Someone is constructing BiliComment with fields: ");
////                            printFields(param.thisObject);
////                            log("Someone received BiliCommentDetail: ");
////                            log("comment result = " + param.getResult());
//                        }
//                    });
//            log("Hook succeeded: Preventing Function: KeyWorkLink");
//        } catch (Exception e) {
//            log("Hook failed: Preventing Function: KeyWorkLink");
//            log("message: " + e.getMessage());
//        }

        // 禁止在点击UP头像时转跳到直播间
//        try {
////            "tv.danmaku.bili.videopage.common.widget.view.VerifyAvatarFrameLayout",
//            findAndHookMethod(
//                    "tv.danmaku.bili.videopage.data.view.model.OwnerExt",
//                    lpparam.classLoader,
//                    "hasLive",
//                    new XC_MethodReplacement() {
//                        @Override
//                        public Object replaceHookedMethod(MethodHookParam param) {
//                            return false;
//                        }
//                    });
//            log("Hook succeeded: Preventing Function: VideoPageAuthorAvatarToLiveRoom");
//        } catch (Exception e) {
//            log("Hook failed: Preventing Function: VideoPageAuthorAvatarToLiveRoom");
//            log("message: " + e.getMessage());
//        }

        // Debug用的view绑定
//        hookViewClickEvent();
//        hookViewScrollFreshEvent(lpparam);
//        XposedHelpers.findAndHookMethod("com.bilibili.lib.homepage.startdust.secondary.BasePrimaryMultiPageFragment", classLoader, "kt", java.util.List.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("!!! ");
//                printBean(param.args[0]);
////                printStackTrace();
//            }
//        });
//        XposedHelpers.findAndHookMethod("com.bilibili.pegasus.promo.index.f", classLoader, "onBindViewHolder", "androidx.recyclerview.widget.RecyclerView$b0", int.class, java.util.List.class, new XC_MethodHook() {
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                if (!Integer.valueOf(10).equals(param.getResult())) return;
//                log("!!! " + param.thisObject);
//                printBean(param.thisObject, 1);
//                printStackTrace();
//            }
//        });
        // com.bilibili.pegasus.api.modelv2.SmallCoverV2Item
//        XposedHelpers.findAndHookMethod("com.bilibili.pegasus.api.BaseTMApiParser", classLoader, "e", "com.alibaba.fastjson.JSONArray", new XC_MethodHook() {
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                log("!!! " + param.thisObject);
//                printBean(param.getResult());
////                printStackTrace();
//            }
//        });
//        XposedHelpers.findAndHookMethod("com.bilibili.pegasus.api.BaseTMApiParser", classLoader, "e", "com.alibaba.fastjson.JSONArray", new XC_MethodHook() {
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                log("!!! " + ((List) param.getResult()).size());
//                printStackTrace();
//            }
//        });
        // com.bilibili.pegasus.api.b0
        // com.bilibili.okretro.call.a.g
//        XposedHelpers.findAndHookConstructor("com.bilibili.okretro.call.a", classLoader, "okhttp3.a0", java.lang.reflect.Type.class, java.lang.annotation.Annotation[].class, "okhttp3.y", "ha.a", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//            }
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                log("!!! " + param.thisObject);
//                printStackTrace();
//            }
//        });
//        XposedHelpers.findAndHookMethod("com.bilibili.okretro.call.a", classLoader, "g", "retrofit2.r", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                Object r = param.args[0];
////                log("艹艹艹艹艹艹艹艹");
////                printBean(r);
////                log("艹艹艹艹艹艹艹艹");
//                Object rb = getDescendantField(r, "b");
//                if (rb == null || !"com.bilibili.okretro.GeneralResponse".equals(rb.getClass().getName())) return;
//                Object data = getDescendantField(rb, "data");
//                if (data == null || !"com.bilibili.pegasus.api.modelv2.PegasusFeedResponse".equals(data.getClass().getName())) return;
//                Object selfO = getDescendantField(param.thisObject, "o");
//                log("========");
//                log("!!! " + selfO);
//                printBean(selfO);
//                printStackTrace();
//                log("========");
//            }
//        });
        // com.bilibili.pegasus.promo.index.IndexFeedFragmentV2
//        XposedHelpers.findAndHookMethod("com.bilibili.pegasus.promo.index.IndexFeedFragmentV2$mIndexCallback$1", classLoader, "onDataSuccess", "com.bilibili.pegasus.api.modelv2.PegasusFeedResponse", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                log("!!! " + param.thisObject);
//                printBean(param.args[0]);
//            }
//        });
//        try {
//            XposedHelpers.findAndHookMethod("com.bilibili.pegasus.promo.index.IndexFeedFragmentV2", classLoader, "Ax", "com.bilibili.pegasus.api.modelv2.PegasusFeedResponse", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    log("!!! ");
//                    Utils.BufferedResult bufferedResult = tryGetDescendantField(param.args[0], "config");
//                    if (!bufferedResult.succeeded) return;
//                    Object config = bufferedResult.result;
//                    boolean autoRefreshTimeByActiveResult = trySetDescendantField(config, new String[] { "autoRefreshTimeByActive" }, 0L);
//                    boolean autoRefreshTimeByAppearResult = trySetDescendantField(config, new String[] { "autoRefreshTimeByAppear" }, 0L);
//    //                log("autoRefreshTimeByActiveResult = " + autoRefreshTimeByActiveResult);
//    //                log("autoRefreshTimeByAppearResult = " + autoRefreshTimeByAppearResult);
//    //                log("aaaaaaaa ");
//    //                printBean(config);
//                }
//            });
//            log("Hook succeeded: Preventing Function: AutoRefreshRecommendVideoList");
//        } catch (Exception e) {
//            log("Hook failed: Preventing Function: AutoRefreshRecommendVideoList");
//            log("message: " + e.getMessage());
//        }

        // 修复稍后再看列表
        try {
            log("Hook Start: FixWatchLater");

            Class<?> FastJsonClass = classLoader.loadClass("com.alibaba.fastjson.JSON");

            Class<?> OkHttpResponseClass = classLoader.loadClass("okhttp3.e0");
            Class<?> FeatureClass = classLoader.loadClass("com.alibaba.fastjson.parser.Feature");
            Class<?> WatchLaterListConverterClass = classLoader.loadClass("com.bilibili.okretro.converter.b");

            XposedHelpers.findAndHookMethod("com.bilibili.okretro.converter.b", classLoader, "convert", java.lang.Object.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    try {
                        // 替换其他方法，XP主动调用 方法
                        String rawResponseString = (String) XposedHelpers.callMethod(methodHookParam.args[0], "m");
                        String responseString = rawResponseString;
                        try {
                            Object json = XposedHelpers.callStaticMethod(FastJsonClass, "parseObject", rawResponseString);
                            Object data = XposedHelpers.callMethod(json, "getJSONObject", "data");
                            if (data != null) {
                                Object list = XposedHelpers.callMethod(data, "getJSONArray", "list");
                                if (list != null) {
                                    int listSize = (Integer) XposedHelpers.callMethod(list, "size");
                                    boolean changed = false;
                                    for (int i = 0; i < listSize; i++) {
                                        Object item = XposedHelpers.callMethod(list, "getJSONObject", Integer.valueOf(i));
                                        Object page = XposedHelpers.callMethod(item, "getJSONObject", "page");
                                        if (page != null) {
                                            long cid = (Long) XposedHelpers.callMethod(page, "getLong", "cid");
                                            if (cid > Integer.MAX_VALUE) {
                                                int newCid = (int) (cid & Integer.MAX_VALUE);
                                                XposedHelpers.callMethod(page, "put", "cid", newCid);
                                                changed = true;
                                            }
                                        }
                                    }

                                    if (changed) {
                                        responseString = (String) XposedHelpers.callStaticMethod(FastJsonClass, "toJSONString", json);
                                    }
                                }
                            }
                        } catch (Throwable e) {
                            responseString = rawResponseString;
                        }

//                        log("responseString.length = " + responseString.length());
                        Object beanType = XposedHelpers.getObjectField(methodHookParam.thisObject, "a");
//                        log("beanType = " + beanType);
                        int FEATURE_FLAGS = XposedHelpers.getStaticIntField(WatchLaterListConverterClass, "b");
//                        log("FEATURE_FLAGS = " + FEATURE_FLAGS);
                        Object[] features = (Object[]) Array.newInstance(FeatureClass, 1);
                        features[0] = XposedHelpers.getStaticObjectField(FeatureClass, "AutoCloseSource");
//                        log("features = " + Arrays.toString(features));

                        Object result = XposedHelpers.callStaticMethod(FastJsonClass, "parseObject", responseString, beanType, FEATURE_FLAGS, features);

                        return result;
                    } catch (Throwable e) {
//                        return XposedBridge.invokeOriginalMethod (methodHookParam.method, methodHookParam.thisObject, methodHookParam.args);
                        throw e;
                    }
                }
            });

            log("Hook succeeded: FixWatchLater");
        } catch (Exception e) {
            log("Hook failed: FixWatchLater");
            log("message: " + e.getMessage());
        }

        log("Hook finished: " + packageName);
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
//                                    printStackTrace();
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

    public static void hookViewScrollFreshEvent(final LoadPackageParam lpparam) {
        try {
            findAndHookConstructor(
//            findAndHookMethod(
                    "androidx.recyclerview.widget.RecyclerView.Adapter",
                    lpparam.classLoader,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            log("!!! RecyclerView.Adapter " + param.thisObject);
                            printBean(param.thisObject);
//                            printStackTrace();
                        }
                    });
            log("Hook succeeded: ViewDebug");
        } catch (Exception e) {
            log("Hook failed: ViewDebug");
            log("message: " + e.getMessage());
        }

    }

}