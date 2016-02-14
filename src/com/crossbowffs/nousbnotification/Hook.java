package com.crossbowffs.nousbnotification;

import android.util.Log;
import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Hook implements IXposedHookLoadPackage {
    private static final String TAG = "NoUsbNotification";

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (!"android".equals(lpparam.packageName)) {
            return;
        }

        String[] classNames = {
            "com.android.server.usb.UsbDeviceManager$UsbHandler",
            "com.android.server.usb.UsbDeviceManagerEx$UsbHandlerEx"
        };

        for (String className : classNames) {
            Class<?> cls;
            try {
                cls = XposedHelpers.findClass(className, lpparam.classLoader);
            } catch (XposedHelpers.ClassNotFoundError e) {
                continue;
            }

            Log.i(TAG, "Hooking USB handler class: " + className);

            try {
                XposedHelpers.findAndHookMethod(cls, "updateAdbNotification", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.v(TAG, "In UsbHandler#updateAdbNotification()");
                        param.setResult(null);
                    }
                });

                XposedHelpers.findAndHookMethod(cls, "updateUsbNotification", new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.v(TAG, "In UsbHandler#updateUsbNotification()");
                        param.setResult(null);
                    }
                });
            } catch (Throwable e) {
                Log.e(TAG, "Failed to hook USB handler methods", e);
            }
        }

        Log.i(TAG, "NoUsbNotification init successful!");
    }
}
