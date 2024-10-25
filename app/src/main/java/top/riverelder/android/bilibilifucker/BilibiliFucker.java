package top.riverelder.android.bilibilifucker;

import static java.nio.charset.StandardCharsets.UTF_8;
import static top.riverelder.android.bilibilifucker.Utils.log;
import static top.riverelder.android.bilibilifucker.Utils.printBean;

import android.content.Context;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import kotlin.text.Charsets;
import top.riverelder.android.bilibilifucker.data.HttpConnectionState;

public class BilibiliFucker implements IXposedHookLoadPackage {

    public static final String PACKAGE_NAME_PLAY = "com.bilibili.app.in";
    public static final String PACKAGE_NAME_ORIGIN = "tv.danmaku.bili";

    public static final Set<String> VALID_TARGET_PACKAGE_NAMES = new HashSet<>(Arrays.asList(
            PACKAGE_NAME_PLAY,
            PACKAGE_NAME_ORIGIN
    ));

    private OkHttpWatcherServer okHttpWatcherServer;

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        String packageName = lpparam.packageName;

        if (!VALID_TARGET_PACKAGE_NAMES.contains(packageName)) return;
        if (!lpparam.isFirstApplication) return;
        if (!Objects.equals(packageName, lpparam.processName)) return;

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

            String watchLaterListConverterClassName = "";
            String responseReadAsStringMethodName = "";

            if (PACKAGE_NAME_PLAY.equals(packageName)) {
                watchLaterListConverterClassName = "com.bilibili.okretro.converter.b";
                responseReadAsStringMethodName = "m";
            } else if (PACKAGE_NAME_ORIGIN.equals(packageName)) {
                watchLaterListConverterClassName = "com.bilibili.okretro.e.b";
                responseReadAsStringMethodName = "string";
            }

            Class<?> FastJsonClass = classLoader.loadClass("com.alibaba.fastjson.JSON");

            Class<?> FeatureClass = classLoader.loadClass("com.alibaba.fastjson.parser.Feature");
            Class<?> WatchLaterListConverterClass = classLoader.loadClass(watchLaterListConverterClassName);

            String finalResponseReadAsStringMethodName = responseReadAsStringMethodName;
            XposedHelpers.findAndHookMethod(WatchLaterListConverterClass, "convert", java.lang.Object.class, new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                    try {
                        // 替换其他方法，XP主动调用 方法
                        String rawResponseString = (String) XposedHelpers.callMethod(methodHookParam.args[0], finalResponseReadAsStringMethodName);
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

                        return XposedHelpers.callStaticMethod(FastJsonClass, "parseObject", responseString, beanType, FEATURE_FLAGS, features);
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

        // 修复非大会员无法观看大会员缓存视频的问题
        try {
            log("Hook Start: PlayVipOfflineVideo");

            // 不提示弹窗
            XposedHelpers.findAndHookMethod("tv.danmaku.bili.ui.offline.g1", classLoader, "s", "kn1.c", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(0);
                }
            });

            // 让视频可以被点击打开
            XposedHelpers.findAndHookMethod("kn1.c", classLoader, "c", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });

            // 不显示VIP限制的红字
            XposedHelpers.findAndHookMethod("kn1.c", classLoader, "d", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });

            // 让视频不被从实际播放列表中剔除
            XposedHelpers.findAndHookMethod("kn1.c", classLoader, "f", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });

//            XposedHelpers.findAndHookMethod("ay1.a", classLoader, "b", java.util.List.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    List<?> playList = (List<?>) param.args[0];
//                    List<?> playList = (List<?>) XposedHelpers.getObjectField(param.thisObject, "f");
//                    log("PlayList("+playList.size()+"): ");
//                    for (int i = 0; i < playList.size(); i++) {
//                        log("  " + i + ": " + playList.get(i));
//                    }
//                }
//            });

            XposedHelpers.findAndHookMethod("ml2.c", classLoader, "h", "com.bilibili.videodownloader.model.d", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(0);
                }
            });

//            XposedHelpers.findAndHookMethod("tv.danmaku.bili.ui.offline.l1", classLoader, "N", "kn1.c", long.class, android.content.Context.class, new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    List<?> playList = (List<?>) XposedHelpers.getObjectField(param.thisObject, "f");
//                    log("PlayList("+playList.size()+"): ");
//                    for (int i = 0; i < playList.size(); i++) {
//                        log("  " + i + ": " + playList.get(i));
//                    }
//                }
//            });
//            XposedHelpers.findAndHookMethod("tv.danmaku.bili.ui.offline.GroupedPlayListMedia", classLoader, "c", new XC_MethodHook() {
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    List<?> playList = (List<?>) param.getResult();
//                    log("PlayList("+playList.size()+"): ");
//                    for (int i = 0; i < playList.size(); i++) {
//                        log("  " + i + ": " + playList.get(i));
//                    }
//                }
//            });

            log("Hook succeeded: PlayVipOfflineVideo");
        } catch (Exception e) {
            log("Hook failed: PlayVipOfflineVideo");
            log("message: " + e.getMessage());
        }

        // 添加分享到QQ
//        try {
//            log("Hook Start: ShareToQQ");
//
//            try {
//                int port = 8841;
//                if (PACKAGE_NAME_PLAY.equals(packageName)) port = 8841;
//                else if (PACKAGE_NAME_ORIGIN.equals(packageName)) port = 8842;
//
//                okHttpWatcherServer = new OkHttpWatcherServer(port);
//                okHttpWatcherServer.start();
//            } catch (Throwable e) {
//                log("Error: " + e);
//            }
//
////            Class<LinearLayout> LinearLayoutClass = (Class<LinearLayout>) classLoader.loadClass("android.widget.LinearLayout");
////            List<Method> methods = new ArrayList<>();
////            for (Method method : LinearLayoutClass.getDeclaredMethods()) {
////                log("LinearLayout: " + method);
////            }
//
////            android.widget.LinearLayout::setOnClickListener;
//
////            XposedHelpers.findAndHookConstructor("tv.danmaku.bili.videopage.common.widget.view.DetailsShareAnimView", classLoader, android.content.Context.class, android.util.AttributeSet.class, int.class, new XC_MethodHook() {
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    printStackTrace();
////                }
////            });
//
////            XposedHelpers.findAndHookMethod("com.bilibili.socialize.share.core.ui.BiliShareDelegateActivity", classLoader, "onCreate", android.os.Bundle.class, new XC_MethodHook() {
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                     Activity thisObject = (Activity) param.thisObject;
////                }
////            });
//
////            XposedHelpers.findAndHookMethod("tv.danmaku.android.log.BLog", classLoader, "d", java.lang.String.class, java.lang.String.class, new XC_MethodHook() {
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    log("BLog: " + param.args[0] + " " + param.args[1]);
////                }
////            });
//
////            XposedHelpers.findAndHookMethod("u43.p", classLoader, "invoke", Object.class, Object.class, new XC_MethodHook() {
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    log("u43.p.invoke");
////                    printStackTrace();
////                }
////            });
////
////            XposedHelpers.findAndHookMethod("com.bilibili.socialize.share.core.ui.WxAssistActivity", classLoader, "Ia", "com.bilibili.socialize.share.core.SocializeMedia", "com.bilibili.socialize.share.core.BiliShareConfiguration", new XC_MethodHook() {
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    printStackTrace();
////                }
////            });
//
////            XposedHelpers.findAndHookConstructor("com.bilibili.app.comm.supermenu.share.v2.SharePanelEntry", classLoader, "com.bilibili.app.comm.supermenu.share.v2.n$b", new XC_MethodHook() {
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                    Object names = XposedHelpers.getObjectField(param.thisObject, "d");
////                    log("names: " + names.toString());
////                }
////            });
//
////            XposedHelpers.findAndHookMethod("sf.b", classLoader, "a", android.content.Context.class, java.lang.String.class, new XC_MethodHook() {
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                    param.setResult(true);
////                }
////            });
//
////            Class<?> ChannelItemClass = classLoader.loadClass("com.bilibili.lib.sharewrapper.online.api.ShareChannels$ChannelItem");
////            XposedHelpers.findAndHookConstructor(ChannelItemClass, new XC_MethodHook() {
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////            log("谁叫的？ com.bilibili.lib.sharewrapper.online.api.ShareChannels$ChannelItem");
////            printStackTrace();
////                }
////            });
//
////            XposedHelpers.findAndHookMethod("com.bilibili.lib.sharewrapper.online.api.ShareChannels", classLoader, "setAboveChannels", java.util.ArrayList.class, new XC_MethodHook() {
////
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                    log("谁叫的？ com.bilibili.lib.sharewrapper.online.api.ShareChannels");
//////                    printStackTrace();
////                    printBean(param.args);
////                }
////            });
//
//            if (PACKAGE_NAME_PLAY.equals(packageName)) {
//                XposedHelpers.findAndHookMethod("com.bilibili.lib.sharewrapper.selector.SharePlatform", classLoader, "q", Context.class, new XC_MethodReplacement() {
//                    @Override
//                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//                        Context context = (Context) param.args[0];
//                        boolean result = (boolean) XposedHelpers.callStaticMethod(classLoader.loadClass("com.bilibili.lib.sharewrapper.selector.SharePlatform"), "h", context, "com.tencent.mobileqq");
//                        log("修改qq判定：" + result);
//                        return result;
//                    }
//                });
//            }
//
//
////            XposedHelpers.findAndHookMethod("okhttp3.z", classLoader, "g", "okhttp3.y", "okhttp3.a0", boolean.class, new XC_MethodHook() {
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                   Object eventListener = XposedHelpers.getObjectField(param.getResult(), "e");
////                   log("eventListener: " + eventListener.getClass());
////                }
////            });
//
////            XposedHelpers.findAndHookConstructor("i53.a", classLoader, "okhttp3.y", "okhttp3.internal.connection.e", "okio.BufferedSource", "okio.BufferedSink", new XC_MethodHook() {
////
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                    Object self = param.thisObject;
////                    Object source = XposedHelpers.getObjectField(self, "c");
////                    log("Http1ExchangeCodec.source: " + source.getClass().getName());
////                    printBean(source);
////                }
////            });
//
////            XposedHelpers.findAndHookMethod("okio.InputStreamSource", classLoader, "read", "okio.Buffer", long.class, new XC_MethodHook() {
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                    long result = (long) param.getResult();
////                    log("okio.InputStreamSource.read result = " + result + ", @" + Integer.toHexString(param.thisObject.hashCode()));
////                }
////            });
//
////            XposedHelpers.findAndHookConstructor("okio.InputStreamSource", classLoader, java.io.InputStream.class, "okio.Timeout", new XC_MethodHook() {
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    InputStream origin = (InputStream) param.args[0];
////                    if ("java.net.SocketInputStream".equals(origin.getClass().getName())) {
////                        ByteArrayOutputStream copied = cloneInputStream(origin);
////                        param.args[0] = copied;
////                        log("Copied " + origin + " -> " + copied);
////                    }
////                }
////            });
//
//            AtomicInteger counter = new AtomicInteger(0);
//            Map<Object, HttpConnectionState> httpConnectionStates = new WeakHashMap<>();
////            Class<?> SourceClass = classLoader.loadClass("okio.Source");
////            Class<?> BufferClass = classLoader.loadClass("okio.Buffer");
////            Class<?> ByteStringClass = classLoader.loadClass("okio.ByteString");
//
//            // RealCall.execute
//
//            if (PACKAGE_NAME_PLAY.equals(packageName)) {
//                // 修复play版不能分享到qq
//
//            }
//            XposedHelpers.findAndHookMethod("okhttp3.z", classLoader, "execute", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    // 开始执行网络请求，现在还没开始
//                    Object request = XposedHelpers.getObjectField(param.thisObject, "f");
//
////                    if (PACKAGE_NAME_PLAY.equals(packageName)) {
//                    if (false) {
//                        Object httpUrl = XposedHelpers.getObjectField(request, "a");
//
//                        List<String> pathSegments = (List<String>) XposedHelpers.getObjectField(httpUrl, "f");
//
//                        if ("x".equals(pathSegments.get(0)) && "share".equals(pathSegments.get(1))) { // 只处理分享
//                            log("pathSegments = ");
//                            printBean(pathSegments);
//
//                            List<String> queryNamesAndValues = (List<String>) XposedHelpers.getObjectField(httpUrl, "g");
//                            log("queryNamesAndValues = ");
//                            printBean(queryNamesAndValues);
//                            if (queryNamesAndValues != null) {
//                                for (int i = 0; i < queryNamesAndValues.size(); i += 2) {
//                                    String name = queryNamesAndValues.get(i);
//                                    String value = queryNamesAndValues.get(i + 1);
//                                    if ("mobi_app".equals(name)) {
//                                        queryNamesAndValues.set(i + 1, "android");
//                                    }
//                                    if ("statistics".equals(name)) {
//                                        queryNamesAndValues.set(i + 1, "{\"appId\":1,\"platform\":3,\"version\":\"6.18.1\",\"abtest\":\"\"}");
//                                    }
//                                    if ("channel".equals(name)) {
//                                        queryNamesAndValues.set(i + 1, "alifenfa");
//                                    }
//                                    if ("appkey".equals(name)) {
//                                        queryNamesAndValues.set(i + 1, "1d8b6e7d45233436");
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    HttpConnectionState state = new HttpConnectionState(counter.getAndIncrement());
//                    Object url = XposedHelpers.getObjectField(request, "a");
//                    String urlString = url.toString();
//
//                    state.setRequest(request);
//                    state.setRequestUrl(urlString);
//                    state.setStatus(HttpConnectionState.Companion.getSTATUS_REQUESTING());
//                    httpConnectionStates.put(request, state);
//                    //                    log("request: " + request);
//                    okHttpWatcherServer.broadcastState(state);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    // 网络请求结束，返回了response
//                    Object response = param.getResult();
//                    if (response == null) return;
//                    //                    log("response: " + response);
//                    Object request = XposedHelpers.getObjectField(param.thisObject, "f");
//                    HttpConnectionState state = httpConnectionStates.get(request);
//                    //                    log("related state: " + state);
//                    if (state == null) return;
//                    state.setResponse(response);
//                    Object responseBody = XposedHelpers.getObjectField(response, "g");
//                    state.setResponseBody(responseBody);
//
//
//                    final Object actualSource = XposedHelpers.getObjectField(responseBody, PACKAGE_NAME_PLAY.equals(packageName) ? "d" : "c");
//
//                    XposedHelpers.callMethod(actualSource, "request", Long.MAX_VALUE);
//                    Object buffer = XposedHelpers.callMethod(actualSource, PACKAGE_NAME_PLAY.equals(packageName) ? "buffer" : "v2");
//                    Object clonedBuffer = XposedHelpers.callMethod(buffer, "clone");
//
//                    Charset charset = Charsets.UTF_8;
//                    Object contentType = XposedHelpers.callMethod(responseBody, PACKAGE_NAME_PLAY.equals(packageName) ? "g" : "contentType");
//                    if (contentType != null) {
//                        charset = (Charset) XposedHelpers.callMethod(contentType, "b", charset);
//                    }
//                    //                    long contentLength = (long) XposedHelpers.callMethod(responseBody, "f");
//                    byte[] responseContent = (byte[]) XposedHelpers.callMethod(clonedBuffer, PACKAGE_NAME_PLAY.equals(packageName) ? "readByteArray" : "i1");
//                    state.setResponseContent(responseContent);
//                    state.setResponseCharset(charset);
//                    //                    log("response responseContent: " + Arrays.toString(responseContent));
//
//                    // 把response的source换成自己的source
//                    okHttpWatcherServer.broadcastState(state);
//                    httpConnectionStates.remove(request);
//
//                    //                    Object injectedSource = Proxy.newProxyInstance(classLoader, new Class[]{SourceClass}, (proxyOfSource, methodOfSource, argsOfSource) -> {
//                    //                        if ("read".equals(methodOfSource.getName())) {
//                    //                            state.setStatus(HttpConnectionState.Companion.getSTATUS_RESPONSING());
//                    //                            Object actualBuffer = argsOfSource[0];
//                    //                            // 把当读到read方法时候，把数据复制一份
//                    //                            Object injectedBuffer = Proxy.newProxyInstance(classLoader, new Class[]{BufferClass}, (proxyOfBuffer, methodOfBuffer, argsOfBuffer) -> {
//                    //                                if ("write".equals(methodOfBuffer.getName()) && Arrays.equals(new Object[]{byte[].class, int.class, int.class}, methodOfBuffer.getParameterTypes())) {
//                    //                                    byte[] data = ((byte[]) argsOfBuffer[0]);
//                    //                                    int offset = (int) argsOfBuffer[0];
//                    //                                    int byteLength = (int) argsOfBuffer[0];
//                    //                                    log("read response data " + Arrays.toString(data));
//                    //
//                    //                                    state.getResponseData().add(data, offset, byteLength);
//                    //
//                    //                                    return methodOfBuffer.invoke(actualBuffer, data, offset, byteLength);
//                    //                                } else return methodOfBuffer.invoke(actualBuffer, argsOfBuffer);
//                    //                            });
//                    //                            Object[] newArgsOfSource = new Object[argsOfSource.length];
//                    //                            newArgsOfSource[0] = injectedBuffer;
//                    //                            if (argsOfSource.length - 1 >= 0)
//                    //                                System.arraycopy(argsOfSource, 1, newArgsOfSource, 1, argsOfSource.length - 1);
//                    //                            return methodOfSource.invoke(actualSource, newArgsOfSource);
//                    //                        } else if ("close".equals(methodOfSource.getName())) {
//                    //                            // 检测source关闭
//                    //                            state.setStatus(HttpConnectionState.Companion.getSTATUS_RESPONSE_READ());
//                    //                            log("read response done");
//                    //
//                    //                            return methodOfSource.invoke(actualSource, argsOfSource);
//                    //                        } else return methodOfSource.invoke(actualSource, argsOfSource);
//                    //                    });
//                    //                    XposedHelpers.setObjectField(response, "g", injectedSource);
//
//                }
//            });
//
//
//            // 每次read会调用
////            XposedHelpers.findAndHookMethod("i53.a$b", classLoader, "read", "okio.Buffer", long.class, new XC_MethodHook() {
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    super.beforeHookedMethod(param);
////                }
////
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                    super.afterHookedMethod(param);
////                }
////            });
//
//            // 当一个stream读完后会调用这个
////            XposedHelpers.findAndHookMethod("i53.a$b", classLoader, "a", boolean.class, java.io.IOException.class, new XC_MethodHook() {
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    super.beforeHookedMethod(param);
////                }
////
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                    super.afterHookedMethod(param);
////                }
////            });
//
////            XposedHelpers.findAndHookConstructor("okhttp3.d0", classLoader, "okhttp3.d0$a", new XC_MethodHook() {
////                @Override
////                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
////                    Object request = XposedHelpers.getObjectField(param.thisObject, "a");
//////                    Object url = XposedHelpers.getObjectField(request, "a");
//////                    String urlString = url.toString();
//////                    log("OkHttp request: " + urlString);
//////                    Object responseBody = XposedHelpers.getObjectField(param.thisObject, "g");
//////                    log("ResponseBody:");
//////                    printBean(responseBody);
////
//////                    String responseBodyString = (String) XposedHelpers.callMethod(responseBody, "m");
//////                    log("OkHttp response: " + responseBodyString);
////                }
////            });
//
////            XposedHelpers.findAndHookMethod(LinearLayoutClass, "onDraw", "android.graphics.Canvas", new XC_MethodHook() {
////                @SuppressLint("ResourceType")
////                @Override
////                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////
////                    LinearLayout thisObject = (LinearLayout) param.thisObject;
////
////                    if (thisObject.getId() == 0x7f091544) {
////                        log("ShareToQQ: frame_share");
////                        for (Field field : thisObject.getClass().getDeclaredFields()) {
////                            log("LinearLayout: " + field);
////                        }
//////                        printStackTrace();
////                    }
////                }
////            });
//
//
//            log("Hook succeeded: ShareToQQ");
//        } catch (Exception e) {
//            log("Hook failed: ShareToQQ");
//            log("message: " + e.getMessage());
//        }

        log("Hook finished: " + packageName);
    }

}