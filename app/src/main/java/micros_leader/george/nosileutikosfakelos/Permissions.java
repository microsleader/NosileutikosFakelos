package micros_leader.george.nosileutikosfakelos;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import android.widget.Toast;

public class Permissions {

    public static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 120;
    public static final int MY_PERMISSIONS_CAMERA = 200;


    public static void requestFilePermission(Activity act) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(act, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(act, "Write External Storage permission allows us to do manage files. Please allow this permission in App Settings"
                    , Toast.LENGTH_LONG).show();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               act.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
            }
        }
    }


    public static void requestPhotoPermission(Activity act) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(act, Manifest.permission.CAMERA)) {
            Toast.makeText(act, "Camera permission allows us to capture photos. Please allow this permission in App Settings"
                    , Toast.LENGTH_LONG).show();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                act.requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_CAMERA);
            }
        }
    }


}
