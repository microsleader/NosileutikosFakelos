package micros_leader.george.nosileutikosfakelos;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
       // setCustID(Customers.CUSTID_FRONTIS);          // ΓΙΑ ΤΕΣΤΑΡΙΣΜΑ
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
      //  MultiDex.install(this);  // NEPHROXENIA
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static Integer getCustomerID() {
        return Utils.getCustomerID(MyApplication.context);
    }

    public static void setCustID(int custID){
        Utils.setCustomerID(context,custID);
    }
}
