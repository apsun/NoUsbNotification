package com.crossbowffs.noadbnotification;

import de.robv.android.xposed.*;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Hook implements IXposedHookLoadPackage {
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
                    XposedBridge.log("In UsbHandler#updateAdbNotification()");
                    param.setResult(null);
                }
            });

        XposedBridge.log("NoAdbNotification init successful!");
    }
}
