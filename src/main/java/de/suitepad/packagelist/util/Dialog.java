package de.suitepad.packagelist.util;

import android.content.Context;
import android.widget.Toast;

public class Dialog {

    public static Dialog create(Context context) {
        Dialog instance = new Dialog();
        instance.context = context;
        return instance;
    }

    private Dialog() {}

    private Context context;

    public void popup(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
