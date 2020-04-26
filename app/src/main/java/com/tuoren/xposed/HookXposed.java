package com.tuoren.xposed;

import android.content.ContentValues;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Create by JDT on 2020-04-25.
 */
public class HookXposed implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if ("com.tencent.mm".equals(lpparam.packageName)) {
            try {
                XposedHelpers.findAndHookMethod("com.tencent.wcdb.database.SQLiteDatabase", lpparam.classLoader,
                        "insertWithOnConflict",String.class, String.class, ContentValues.class, int.class, new XC_MethodHook() {

                            //beforeHookedMethod 用于Hook方法调用之前的操作
                            @Override
                            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                //去打印 参数信息
                                Object[] args = param.args;
                                if (args != null && args.length >= 3 && args[2] instanceof ContentValues) {
                                    ContentValues contentValues = (ContentValues) args[2];
                                    int type = (int) contentValues.get("type");
                                    //说明一个红包信息
                                    if (type == 436207665) {
                                        String content = (String) contentValues.get("content");
                                        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
                                        String nativeUrl = XmlParser.getNativeUrl(inputStream);
                                    }
                                }
//                                XposedBridge.log("Xposed vx" + "param.args 个数:" + args.length);
//                                XposedBridge.log("Xposed vx" + "参数1:" + args[0]);
//                                XposedBridge.log("Xposed vx" + "参数2:" + args[1]);
//                                ContentValues contentValues = (ContentValues) args[2];
//                                for (Map.Entry<String, Object> item : contentValues.valueSet()) {
//                                    XposedBridge.log("Xposed vx" + "参数3:" + " key = " + item.getKey() + "value = " + item.getValue());
//                                }
//                                XposedBridge.log("Xposed vx" + "参数4:" + args[3]);
                                super.beforeHookedMethod(param);
                            }
                            //beforeHookedMethod 用于Hook方法调用之后的操作
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                super.afterHookedMethod(param);
                            }
                        });
            } catch (Throwable t) {
                XposedBridge.log(t);
            }
        }
    }
}
