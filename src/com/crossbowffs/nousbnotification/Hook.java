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

        XposedHelpers.findAndHookMethod(
            "com.android.server.usb.UsbDeviceManager$UsbHandler", lpparam.classLoader,
            "updateAdbNotification", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.v(TAG, "In UsbHandler#updateAdbNotification()");
                    param.setResult(null);
                }
            });

        XposedHelpers.findAndHookMethod(
            "com.android.server.usb.UsbDeviceManager$UsbHandler", lpparam.classLoader,
            "updateUsbNotification", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Log.v(TAG, "In UsbHandler#updateUsbNotification()");
                    param.setResult(null);
                }
            });

        Log.i(TAG, "NoUsbNotification init successful!");
    }
}
