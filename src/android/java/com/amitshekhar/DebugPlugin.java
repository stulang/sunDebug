package com.amitshekhar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import org.apache.cordova.CordovaPlugin;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import android.util.Log;
import org.json.JSONException;
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

    public  void showDebugDBAddressLogToast(Context context) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
                open("http://127.0.0.1:"+DebugDB.DEFAULT_PORT);
            } catch (Exception ignore) {

            }
    }
    void open( String url){
          Intent intent = new Intent();
          //Intent intent = new Intent(Intent.ACTION_VIEW,uri);
          intent.setAction("android.intent.action.VIEW");
          Uri content_url = Uri.parse(url);
          intent.setData(content_url);
          this.cordova.getActivity().startActivity(intent);
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
