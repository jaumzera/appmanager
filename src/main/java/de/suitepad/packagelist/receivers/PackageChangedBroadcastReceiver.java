package de.suitepad.packagelist.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import de.suitepad.packagelist.MainActivity;

public class PackageChangedBroadcastReceiver extends BroadcastReceiver {

    private static final String ANDROID_INTENT_ACTION_PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";
    private static final String ANDROID_INTENT_ACTION_PACKAGE_REMOVED = "android.intent.action.PACKAGE_REMOVED";
    private static final String ANDROID_INTENT_ACTION_PACKAGE_CHANGED = "android.intent.action.PACKAGE_CHANGED";

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        String pkgName = removePakageName(data);

        if(MainActivity.getInstance() != null) {
            switch (intent.getAction()) {
                case ANDROID_INTENT_ACTION_PACKAGE_ADDED :
                    MainActivity.getInstance().onPackageInstalled(pkgName);
                    break;
                case ANDROID_INTENT_ACTION_PACKAGE_REMOVED :
                    MainActivity.getInstance().onPackageUninstalled(pkgName);
                    break;
                case ANDROID_INTENT_ACTION_PACKAGE_CHANGED :
                    MainActivity.getInstance().onPackageChanged(pkgName);
            }
        }
    }

    @NonNull
    private String removePakageName(Uri data) {
        return data.toString().substring(data.getScheme().length() + 1);
    }
}
