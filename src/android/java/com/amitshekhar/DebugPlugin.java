package com.amitshekhar;
import android.content.Context;
import android.widget.Toast;

import org.apache.cordova.CordovaPlugin;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sun.wq on 2017/12/12.
 */

public class DebugPlugin extends CordovaPlugin {

    @Override
    public void initialize(final CordovaInterface cordova, final CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d("DebugPlugin", "initialize"  );
		DebugDB.initialize(this.cordova.getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
    }

    @Override
    public void onStop() {

        super.onStop();
    }


    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        boolean cmdProcessed = true;
        if ("open".equals(action)) {
            showDebugDBAddressLogToast(this.cordova.getActivity());
        } else {
            cmdProcessed = false;
        }

        return cmdProcessed;
    }

    public static void showDebugDBAddressLogToast(Context context) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
            } catch (Exception ignore) {

            }
    }

    public static void setCustomDatabaseFiles(Context context) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Class[] argTypes = new Class[]{HashMap.class};
                Method setCustomDatabaseFiles = debugDB.getMethod("setCustomDatabaseFiles", argTypes);
                HashMap<String, File> customDatabaseFiles = new HashMap<String, File>();
                // set your custom database files
//                customDatabaseFiles.put(ExtTestDBHelper.DATABASE_NAME,
//                        new File(context.getFilesDir() + "/" + ExtTestDBHelper.DIR_NAME +
//                                "/" + ExtTestDBHelper.DATABASE_NAME));
                setCustomDatabaseFiles.invoke(null, customDatabaseFiles);
            } catch (Exception ignore) {

            }
    }
}
