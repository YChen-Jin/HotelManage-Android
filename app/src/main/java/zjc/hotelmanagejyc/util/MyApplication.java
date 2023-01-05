package zjc.hotelmanagejyc.util;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class MyApplication extends Application {
    private static Context context;

    public static void subThreadToast(String message){
        Looper.prepare();
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
}
