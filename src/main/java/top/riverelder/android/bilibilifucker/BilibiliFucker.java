package top.riverelder.android.bilibilifucker;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XC_MethodReplacement.DO_NOTHING;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.IXposedHookLoadPackage;
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
//            findAndHookMethod("tv.danmaku.biliplayerimpl.gesture.GestureService", lpparam.classLoader, "f6", DO_NOTHING);
            log("Hook succeeded: Preventing Function: KeyWorkLink");
        } catch (Exception e) {
            log("Hook failed: Preventing Function: KeyWorkLink");
            log("message: " + e.getMessage());
        }

        XposedBridge.log("Hook finished: " + packageName);
    }

}