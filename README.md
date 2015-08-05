# NoAdbNotification

Removes the USB debugging notification on Android. Has the same effect
as setting `persist.adb.notify=0` in `/system/build.prop`, without
modifying any system files.

Update: Also removes the MTP/PTP connection notification, for your
debugging pleasure.

Relevant code can be found [here](https://github.com/android/platform_frameworks_base/blob/master/services/usb/java/com/android/server/usb/UsbDeviceManager.java).

## Requirements

- A rooted Android phone
- [Xposed framework](http://forum.xda-developers.com/xposed/xposed-installer-versions-changelog-t2714053)
  ([Lollipop version](http://forum.xda-developers.com/showthread.php?t=3034811))

## Download

Download a pre-built APK [here](https://bitbucket.org/crossbowffs/noadbnotification/downloads/NoAdbNotification.apk),
or build the app yourself (it only takes a few seconds!)

## License

Distributed under the [MIT License](http://opensource.org/licenses/MIT).
