package com.veryworks.android.musicplayer;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by pc on 2/8/2017.
 */

public class Message {

    public static void show(String msg, Context context){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
