package micros_leader.george.nosileutikosfakelos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;
import com.victor.loading.rotate.RotateLoading;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Vardies;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Utils  {


    public static SharedPreferences sp;
    private static SharedPreferences.Editor editor;





    public static  boolean isNetworkAvailable(Context ctx) {
        final ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();

                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();

                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);
                    if (nc != null)
                        return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }

        return false;
    }

    public static  boolean isNetworkAvailable2(Context ctx) {
        if (ctx == null)
            return false;

        final ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();

                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();

                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }

        Toast.makeText(ctx, R.string.check_internet_access, Toast.LENGTH_SHORT).show();
        return false;
    }


    public static String getCurrentDate() {

        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",Locale.US);

        String currentDate = df.format(c.getTime());

        return currentDate;

    }

    public static String getCurrentDateTimeConverted() {

        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String currentDate = df.format(c.getTime());



        return convertDateTomilliseconds(currentDate);

    }

    public static String getCurrentMonthYear() {

        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("MM/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));

        return df.format(c.getTime());

    }

    @SuppressLint("DefaultLocale")
    public static String convertMilliSecondsToTime(String x){

        String time = "";
        try {
            if (x == null || x.equals(""))
                return "";

            long millis = Long.parseLong(x);

             time = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
        }

        catch (Exception e){
            time = x;
        }

        return time;
    }

    public static String getNextDate(Context context, String curDate) {
        @SuppressLint("SimpleDateFormat")
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar calendar = Calendar.getInstance();
        final Date date;
        try {
            date = format.parse(curDate);

            if (date != null) {
                calendar.setTime(date);
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(calendar.getTime());

    }


    public static String getNextMonth( String curMonth) {
        @SuppressLint("SimpleDateFormat") final SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");
        final Calendar calendar = Calendar.getInstance();
        final Date date;
        try {
            date = format.parse(curMonth);
            if (date != null) {
                calendar.setTime(date);
                calendar.add(Calendar.MONTH, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(calendar.getTime());

    }

    public static String getCurrentTime() {
        Date today = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        return format.format(today);

    }

    public static String getCurrentTime2() {
        Date today = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        return format.format(today);

    }

    public static void  createDialogWithoutDateField(FragmentManager fragmentManager, final TextView textView) {

        MonthYearPickerDialog pickerDialog = new MonthYearPickerDialog();
        pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int i2) {

                textView.setText(month + "/" + year);
            }
        });
        pickerDialog.show(fragmentManager, "MonthYearPickerDialog");
    }




    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static byte[] hex_to_bytes(String hex) {
        if (hex == null) {
            return null;
        }
        char[] chars = hex.toCharArray();
        int start = 0;
        int end = hex.length();
        int len = (end - start) >> 1;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            char c = chars[start++];
            int b = (c >= '0' && c <= '9') ? c - '0'
                    : (c >= 'A' && c <= 'F') ? c - ('A' - 10)
                    : (c >= 'a' && c <= 'f') ? c - ('f' - 10)
                    : 0;
            b <<= 4;
            c = chars[start++];
            b |= (c >= '0' && c <= '9') ? c - '0'
                    : (c >= 'A' && c <= 'F') ? c - ('A' - 10)
                    : (c >= 'a' && c <= 'f') ? c - ('f' - 10)
                    : 0;
            bytes[i] = (byte) b;
        }
        return bytes;
    }


    public static void createFolder(String tmp) {


        File f = new File(Environment.getExternalStorageDirectory(), tmp);
        if (!f.exists()) {
            f.mkdirs();
        }
    }


    public static boolean deleteFolder(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        return (path.delete());
    }


    public static void saveDocumentExternal(Context context, byte[] bytes, String currentTimeMills, String folder, String ext) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);

            Toast.makeText(context, "Δεν έχετε δώσει δικαίωμα στην εφαρμογή για πρόσβαση στον αποθηκευτικό σας χωρο", Toast.LENGTH_SHORT).show();

            //getPermissions(context);
        }

        File document = new File(Environment.getExternalStorageDirectory() + "/" + folder, currentTimeMills + "." + ext);

        try {
            FileOutputStream fos = new FileOutputStream(document.getPath());

            fos.write(bytes);
            fos.close();
        } catch (java.io.IOException ignored) {
        }


    }


    public static void openDocument(Context context, String tmpfolder, String tmpFileName, String ext) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + tmpfolder + "/" + tmpFileName + "." + ext);
        String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        if (extension.equalsIgnoreCase("") || mimetype == null) {
            // if there is no extension or there is no definite mimetype, still try to open the file
            intent.setDataAndType(Uri.fromFile(file), "text/*");
        } else {
            intent.setDataAndType(Uri.fromFile(file), mimetype);
        }


        // custom message for the intent
        context.startActivity(Intent.createChooser(intent, "Choose an Application:"));


    }


    public static void saveDocumentInternal(Context context, byte[] bytes, String currentTimeMills, String folder, String ext) {
        File file = new File(context.getFilesDir() + "/" + folder, currentTimeMills + "." + ext);
        if (!file.exists()) {
            file.mkdir();
        }

        try {
            FileOutputStream fos = new FileOutputStream(file.getPath());

            fos.write(bytes);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    public static void openDocumentInternal(Context context, String tmpfolder, String tmpFileName, String ext) {


        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        File file = new File(context.getFilesDir() + "/" + tmpfolder + "/" + tmpFileName + "." + ext);
        String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        if (extension.equalsIgnoreCase("") || mimetype == null) {
            // if there is no extension or there is no definite mimetype, still try to open the file
            intent.setDataAndType(Uri.fromFile(file), "text/*");
        } else {
            intent.setDataAndType(Uri.fromFile(file), mimetype);
        }
        // custom message for the intent
        context.getApplicationContext().startActivity(Intent.createChooser(intent, "Choose an Application:"));


    }


    public static String getAddress(Context context) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String address = sp.getString("address", "");

        return address;
    }


    public static String getPort(Context context) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String port = sp.getString("port", "");

        return port;
    }



    public static boolean isDeviceSignedToDatabase(Context context) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        boolean signed = sp.getBoolean("signed", false);

        return signed;
    }

    public static void setDeviceSignedIn(Context context, boolean isSigned) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        editor = sp.edit();
        editor.putBoolean("signed",isSigned);
        editor.commit();

    }


    public static String getUserID(Context context) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        String id = sp.getString("id", "");

        return id;
    }

    public static String getUserName(Context context) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        return  sp.getString("name", "");

    }

    public static String getcompanyID(Context context) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        return  sp.getString("companyID", "");
    }




    public static void setFloorMap(Context ctx, HashMap<String, Integer> floorsMap) {
        //convert to string using gson
        Gson gson = new Gson();
        String hashMapString = gson.toJson(floorsMap);

        //save in shared prefs
        sp = ctx.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("signed", hashMapString).apply();

    }

    public static HashMap<String, Integer> getFloorMap(Context context) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        String floorsStr = sp.getString("floorsMapStr", "");
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
            return gson.fromJson(floorsStr, type);
    }


    public static void setCustomerID(Context context, int custID) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("custID",custID);
        editor.commit();

    }

    public static int getCustomerID(Context context) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        return  sp.getInt("custID", 0);

    }

    public static boolean getIsNurse(Context context) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        return  sp.getBoolean("isNurse",false);

    }


    public static String getLinkDoctorID(Context context) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        return  sp.getString("linkdoctorID", "");

    }

    public static boolean getIsDoctor(Context context) {
        return  !getLinkDoctorID(context).isEmpty();

    }


    public static boolean getIsFirstTimeLogin(Context context) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        return  sp.getBoolean("firstTimeLogin", true);


    }


    public static boolean get_is_nursing_unlock(Context context) {
        sp = context.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        return  sp.getBoolean("is_nursing_unlock", false);

    }



    public static ArrayList<String> getMedicinesArrayList(Context context) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        Set<String> set = sp.getStringSet("medicinesArrayList", null);
        ArrayList <String> lista = new ArrayList<>();
        lista.addAll(set);
        return lista;
    }

    public static HashMap<String,Integer> getMedicinesHashMap(Context context) {

        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String mapString = sp.getString("medicinesMap", "");
        TypeToken<HashMap<String,Integer>> token = new TypeToken<HashMap<String,Integer>>() {};
        HashMap<String,Integer> retrievedMap = new Gson().fromJson(mapString,token.getType());

        return retrievedMap;
    }

    public static void setPosFloorID(Context context, int floorPositionID) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("floorPositionID",floorPositionID);
        editor.apply();

    }

    public static Integer getPosFloorID(Context context) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        return sp.getInt("floorPositionID", 0);

    }



    public static void setPosPatientID(Context context, int patientPositionID) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("patientPositionID",patientPositionID);
        editor.apply();

    }

    public static Integer getPosPatientID(Context context) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        return sp.getInt("patientPositionID", 0);

    }


    public static String  getMedicineKeyByValue(Context ctx , String id) {
        HashMap<String,Integer> map = getMedicinesHashMap(ctx);

        if (map == null)
            return null;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (Integer.parseInt(id)  == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getMedicineIDForValueJson(Context context) {
        sp = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String med = sp.getString("medicineValueJson","");

        return med;
    }

    public static void getPermissions(Context context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED

                && ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {

        } else {

        }

        ActivityCompat.requestPermissions((Activity) context, new String[]{
                        "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_CONTACTS",
                        "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"},
                123);
        //  ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);

    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void dismissKeyboard(Activity act){
        InputMethodManager imm = (InputMethodManager) act.getSystemService(INPUT_METHOD_SERVICE);
        View v = Objects.requireNonNull(act).getCurrentFocus();
        if (v != null)
            imm.hideSoftInputFromWindow(Objects.requireNonNull(v).getWindowToken(), 0);

    }


    public static String URLreplaceBlanks(String str){

        String tmp = str.replace(" ","%20").replace("+","%2B").replace("\n","\\");

        return tmp;

    }


    public static String getVardiaID(ArrayList<Vardies> vardies){

        for (int i=0; i<vardies.size(); i++) {

            String period = vardies.get(i).getName();
            String id = String.valueOf(vardies.get(i).getId());

            String[] parts = period.split("-");


            Calendar now = Calendar.getInstance();

            // PAIRNEI TIN TORINI ORA KAI VGAZEI TO SINOLO TON LEPTON
            int currentHour = now.get(Calendar.HOUR_OF_DAY);
            int currentMinute = now.get(Calendar.MINUTE);
            int currentTotalMinutes = currentHour * 60 + currentMinute;
           // Log.e("nowCurrent", String.valueOf(currentHour + " " + currentMinute + " " + currentTotalMinutes));

            // PAIRNEI TIN ORA POU EXEI APO TO OBJECT POU TO PIRE APO TIN VASI

            String[] leftTime = parts[0].split(":");
            String leftHour = leftTime[0];
            String leftMinutes = leftTime[1];

            String[] rightTime = parts[1].split(":");
            String rightHour = rightTime[0];
            String rightMinutes = rightTime[1];

            int leftTotalMinutes = Integer.parseInt(leftHour) * 60 + Integer.parseInt(leftMinutes);
            int rightTotalMinutes = Integer.parseInt(rightHour) * 60 + Integer.parseInt(rightMinutes);


            //ΣΥΓΚΡΙΣΗ ΣΥΝΟΛΙΚΩΝ ΛΕΠΤΩΝ ΤΗΣ ΤΩΡΙΝΗΣ ΩΡΑΣ ΜΕ ΤΗΝ ΠΕΡΙΟΔΟ ΠΟΥ ΕΧΕΙ ΠΑΡΕΙ ΑΠΟ ΤΗ ΒΑΣΗ
            // ΠΕΡΙΟΔΟΣ ΑΠΟ ΤΗ ΒΑΣΗ ΣΕ ΜΟΡΦΗ  ΓΙΑ ΠΑΡΑΔΕΙΓΜΑ 10:00- 14:10
            if (currentTotalMinutes >= leftTotalMinutes && currentTotalMinutes <= rightTotalMinutes) {



                return id; // ΕΠΙΣΤΡΕΦΕΙ ΤΟ ID ΤΗΣ ΑΝΤΙΣΤΟΙΧΗΣ ΠΕΡΙΟΔΟΥ

            }

        }

        return "0";


    }


    public static ArrayList<String> getVardiesCodeName(ArrayList<Vardies> vardies){

        ArrayList<String> vardiesCodeName = new ArrayList();

        if (vardies == null || vardies.isEmpty())
            vardiesCodeName.add("0 , Δεν υπάρχουν καταχωρημένες βάρδιες");
        else {
            for (int i = 0; i < vardies.size(); i++) {

                vardiesCodeName.add(vardies.get(i).getId() + " , " + vardies.get(i).getName());


                 }
            }



            return vardiesCodeName;

        }




    public static  String getfirstPartSplitCommaString(String str){

        if (str == null || !str.contains(","))
            return "";

        String[] parts = str.split(",");
        return parts[0].trim();

    }

    public static  String getfirstPartSplitString(String str,String split){

        if (str == null || !str.contains(split))
            return "";

        String[] parts = str.split(split);
        return parts[0].trim();

    }


    public static  String getSplitSPartString(String str,String split, int index){

        if (str == null || !str.contains(split))
            return "";

        String[] parts = str.split(split);
        if (parts.length < index)
            return "";

        return parts[index].trim();

    }



    public static  String checkZeroAndComma(String str){

        str = str.replace(",", ".");

        if (str.equals("")){
            return null;
        }


        if (str.startsWith(".")){
            str = "0" + str;
        }

        if (str.endsWith(".")){
            str = str.substring(0, str.length() - 1);
        }

        return str;

    }





    public static String checkFormat1(String str){

        DecimalFormat formatter = new DecimalFormat("0.000");
        if (str.equals("")){
            return str;
        }

        Double num = Double.parseDouble(str);
      //  num = (num == 0.000 ? 0.000 : Double.parseDouble(formatter.format(num)));
        str = formatter.format(num).replace(",",".");

      // str = num.toString();
        return str;

    }

    public static String checkFormat2(String str){

        DecimalFormat formatter = new DecimalFormat("0.0");
        if (str.equals("")){
            return str;
        }
        Double num = Double.parseDouble(str);
        //  num = (num == 0.000 ? 0.000 : Double.parseDouble(formatter.format(num)));
        str = formatter.format(num).replace(",",".");

        // str = num.toString();
        return str;

    }

    public static String checkFormat3(String str) {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        str = (str.equals("") ? "00:00:00" : str);
        //Double num = Double.parseDouble(str);
        //  num = (num == 0.000 ? 0.000 : Double.parseDouble(formatter.format(num)));
        Date date = null;
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null)
          str = formatter.format(date);

        return str;

    }


    public static String hourFormat(String str) {

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        if (str.equals("")){
            return null;
        }

        //Double num = Double.parseDouble(str);
        //  num = (num == 0.000 ? 0.000 : Double.parseDouble(formatter.format(num)));
        Date date = null;
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            try {
                date = formatter.parse("00:00");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        if (date != null)
            str = formatter.format(date);

        return str;

    }

    public static String afairesiDouble(String arxiko, String teliko){

        if (arxiko == null || teliko == null){
            return "";
        }

        if ((arxiko.equals("") || teliko.equals(""))){
            return "";
        }

        double arx = Double.parseDouble(arxiko);
        double tel = Double.parseDouble(teliko);
        double apotelesma = arx - tel;

        return checkFormat1(String.valueOf(apotelesma));

    }


    public static String timeDiffrence(String arxiko, String teliko){

        if ((arxiko == null || teliko == null)){
            return "";
        }

        if ((arxiko.equals("") || teliko.equals(""))){
            return "";
        }

        if (!(arxiko.contains(":") && teliko.contains(":"))){
            return "";
        }

        String[] partsArxiko = arxiko.split(":");
        String[] partsTeliko = teliko.split(":");

        String hourArxiko = partsArxiko[0];
        String minutesArxiko = partsArxiko[1];

        String hourTeliko = partsTeliko[0];
        String minutesTeliko = partsTeliko[1];

        int TotalMinutesArxiko = Integer.parseInt(hourArxiko) * 60 + Integer.parseInt(minutesArxiko);
        int TotalMinutesTeliko = Integer.parseInt(hourTeliko) * 60 + Integer.parseInt(minutesTeliko);


        if (TotalMinutesTeliko <= TotalMinutesArxiko){
            return "";
        }
        else{

            int apotelesma = TotalMinutesTeliko - TotalMinutesArxiko;
            int hours = apotelesma / 60;
            int minnutesRemaining = apotelesma % 60;

        //    Log.e("time2", hours + " " + minnutesRemaining);

            return hours + ":" + minnutesRemaining;
        }


    }


    public static void logout(Context ctx) {

        //-----------------------------------------------------------------
        SharedPreferences prefs;
        prefs = ctx.getSharedPreferences("loginSettings", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editorForLogin;
        editorForLogin = prefs.edit();
        editorForLogin.clear();
        editorForLogin.apply();


    }






    public static String convertObjToString(Object randomBg){

        if (randomBg == null)
            return "";

        String tmp = String.valueOf(randomBg);
        if (tmp.equals("null"))
            return "";

        return tmp;

    }



    public static void  getAlertDialogInfo (Activity activity, String title, String message){

        new FancyAlertDialog.Builder(activity)
                .setTitle(title)
                .setBackgroundColor(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
                .setMessage(message)
                .setPositiveBtnBackground(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("ΟΚ")
                .setNegativeBtnText("Cancel")
                .setAnimation(Animation.POP)
                .isCancellable(false)
                .setIcon(R.drawable.error_icon, Icon.Visible)
                .build();
    }


//    public static FancyAlertDialog  getFancyAlertDialog (Activity activity, String title, String message, String posBut ,String negBut){
//
//       FancyAlertDialog alertDialog =  new FancyAlertDialog.Builder(activity)
//                .setTitle(title)
//                .setBackgroundColor(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
//                .setMessage(message)
//                .setPositiveBtnBackground(Color.parseColor("#2288c1"))  //Don't pass R.color.colorvalue
//                .setPositiveBtnText(posBut)
//                .setNegativeBtnText(negBut)
//                .setAnimation(Animation.POP)
//                .isCancellable(false)
//                .setIcon(R.drawable.error_icon, Icon.Visible)
//                .build();
//
//        return alertDialog;
//    }


    public static boolean checkIsTrue(String v){
        if (v.equals("true") || v.equals("1"))
            return true;

        return false;
    }


    public static String checkNull(String str){

        if (str == null)
            return "";

        if (str.trim().equals(""))
            return  null;

        else
            return str.trim();
    }




    public static int convertTime(String time) {

        if (time.equals("")){
            return 0;
        }

        String[]  parts  = time.split(":");
        String hours = parts[0];
        String minutes = parts[1];



        return
                Integer.parseInt(minutes) * (60 * 1000)
                + Integer.parseInt(hours) * (60 * 60 * 1000);
    }




    public static String chechZeroInTime(String str){


        if (str.equals(""))
            return  "";



        else{
            String[] parts = str.split(":");
            String hour = parts[0];
            String min = parts[1];

            if (hour.length() == 1)
                hour = "0" + hour;
            if (min.length() == 1)
                min = "0" + min;

            return hour + ":" + min;


        }


    }


    public static void edittextSetText(List<EditText> editTextList, String value){
        for(EditText mEditText : editTextList){

            mEditText.setText(value);
        }


    }

    public static void textViewSetText(List<TextView> textViewList, String value){
        for(TextView textView : textViewList){

            textView.setText(value);
        }


    }

    public static String getChecked(boolean value){
        if (value)
            return "1";
        else
            return null;
    }

    public static boolean setChecked(Object value){
        if (value == null)
            return false;
        if (Boolean.parseBoolean(value.toString()))
            return true;
        else
            return false;
    }


    public static void timeHandlerDoneButton(final CircularProgressButton button, final Context ctx){



            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (button != null) {
                        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.ic_done_white_48dp);

                        button.doneLoadingAnimation(Color.GREEN, bitmap);
                    }
                    Toast.makeText(ctx, "Πετυχημένη ενημέρωση", Toast.LENGTH_SHORT).show();


                }
            }, 800);

            deMorphing(button);


    }

    public static void timeHandlerErrorButton(final CircularProgressButton button, final Context ctx){

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (button != null) {

                        Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.cancel);

                        button.doneLoadingAnimation(Color.GREEN, bitmap);
                    }
                    Toast.makeText(ctx, "Προέκυψε σφάλμα κατά την ενημέρωση (error 101)", Toast.LENGTH_SHORT).show();

                }
            }, 800);

            deMorphing(button);


    }

    public static void deMorphing(final CircularProgressButton button)
    {
        if (button != null) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    button.revertAnimation();
                }
            }, 1800);

        }

    }



    public static AlertDialog setLoadingAlertDialog(Activity activity){

        final Context mContext = activity;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_dialog,
                (ViewGroup) activity.findViewById(R.id.custom_layout_dialog));

        RotateLoading loading = layout.findViewById(R.id.rotateloading);
        loading.start();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(layout);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setCancelable(true);

        return alertDialog;

    }


    public static String checkIfThereIsComma(String str){

       if (str.contains(","))
           return  str.replace(",",".");

       return str;
    }


    public static void dateListener( final Context ctx, final TextView textView){

        final Calendar myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                textView.setText(sdf.format(myCalendar.getTime()));
             //   updateLabel(textView);

            }

        };

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ctx, date12, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }




    public static void timeListener(final Context ctx, final TextView textView){

        final Calendar myCalendar = Calendar.getInstance();


        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ctx, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        textView.setText( Utils.chechZeroInTime(selectedHour + ":" + selectedMinute));
                    }
                }, hour, minute, true);
                mTimePicker.show();
            }
        });
    }

    public static boolean isEmpty(TextView textView, Context ctx){

        if (textView.getText().toString().trim().equals("")) {
            Toast.makeText(ctx, "Δεν υπάρχουν ασθενείς αυτη τη στιγμή", Toast.LENGTH_SHORT).show();

            return true;
        }

            else{
            return false;
        }

    }

    //ΚΑΝΩ ΚΑΠΟΙΕΣ ΦΟΡΕΣ ΚΑΤΙ ΔΟΚΙΜΕΣ ΜΟΝΟ ΓΙΑ ΤΟ TABLE
    public static String convertDateTomillisecondsTable(String date){
        if (date !=null && !date.isEmpty())
           return convertDateTomilliseconds(date);
        return "";
    }


    public static String convertDateTomilliseconds(String date){

        Locale locale = new Locale("el", "GR");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm" , locale);
        formatter.setLenient(false);


        String oldTime ;
        long oldMillis = 0;

        if (date.contains(":"))
            oldTime = date.trim().replace("/","-").replace(" " ,", ");
        else
            oldTime = date.trim().replace("/","-") + ", " + getCurrentTime2();


        Date oldDate = null;
        try {
            oldDate = formatter.parse(oldTime);
        } catch (ParseException e) {
            //ΑΔΜΙΝe.printStackTrace();
        }

        if (oldDate == null ) {

            try {
                oldDate = formatter.parse(getCurrentDate().trim().replace("/","-") + ", " + getCurrentTime2());
                oldMillis = oldDate.getTime() + 7200000L;  // ΓΙΑ ΝΑ ΠΑΙΡΝΕΙ ΤΑ MILLISECONDS ΤΗΣ ΕΛΛΑΔΑΣ
                return  String.valueOf(oldMillis);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        long xaxa = oldDate.getTime();
        oldMillis = oldDate.getTime() + 7200000L;// + 3600000L;
        return String.valueOf(oldMillis);
    }

    public static String convertDateToDatemilliseconds(String date){


        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        formatter.setLenient(false);


        String oldTime = date.trim().replace("/","-") ;
        Date oldDate = null;
        try {
            oldDate = formatter.parse(oldTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (oldDate == null ) {

            try {
                oldDate = formatter.parse(getCurrentDate().trim().replace("/","-") );
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        long oldMillis = oldDate.getTime();
        return String.valueOf(oldMillis);
    }


    public static String  utc_to_localtime(String utc) {
        TimeZone tz = TimeZone.getDefault();
        long timeMills = Long.parseLong(utc) + tz.getOffset(Long.parseLong(utc));

        DateFormat formatter = new SimpleDateFormat("HH:mm", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String time = formatter.format(new Date(timeMills));

        return time;

    }

    public static String convertHourTomilliseconds(String oldTime){


        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setLenient(false);


        Date oldDate = null;
        try {
            oldDate = formatter.parse(oldTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (oldDate == null)
            return "";
        long oldMillis = oldDate.getTime();
        return String.valueOf(oldMillis);
    }

    public static String convertHourTomillisecondsGR(String oldTime){

        if (oldTime == null)
            return "";

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setLenient(false);


        Date oldDate = null;
        try {
            if (oldTime.equals(""))
                return "";
            oldDate = formatter.parse(oldTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (oldDate == null)
            return "";
        long oldMillis = oldDate.getTime() + 7200000; // ΔΥΟ ΩΡΕΣ ΕΠΙΠΛΕΟΝ ΓΙΑ ΤΗΝ ΕΛΛΑΔΑ
        return String.valueOf(oldMillis);
    }

    public static String convertHourTomillisecondsGR(int oldTime) {
        if (oldTime >24)
            return "Η ώρα δεν μπορεί να είναι μεγαλύτερη του 24";

        return convertHourTomillisecondsGR(oldTime + ":00");
    }


        public static boolean containsCaseInsensitive(String strToCompare, ArrayList<String>list)
    {
        for(String str:list)
        {
            if(str.equalsIgnoreCase(strToCompare))
            {
                return(true);
            }
        }
        return(false);
    }


    public static String dateformat(long date, String format){
        if (date == 0)
            return "-";

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

        return   formatter.format(date);
    }

    public static String convertMillisecondsToDateTime(String milliSeconds){

        try {
            if (milliSeconds == null || milliSeconds.equals("")) {
                return "";
            }
            // Create a DateFormatter object for displaying date in specified format.
           // Locale locale = new Locale("el","GR");
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

            // Create a calendar object that will convert the date and time value in milliseconds to date.
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(Long.parseLong(milliSeconds) - 7200000);
//---------------------

            return formatter.format(Long.parseLong(milliSeconds));

        }
        catch(Exception e){
            return milliSeconds;
        }

    }


    public static String convertMillisecondsTO_onlyDate(String milliSeconds){

        try {
            if (milliSeconds == null || milliSeconds.equals("")) {
                return "";
            }
            // Create a DateFormatter object for displaying date in specified format.
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.parseLong(milliSeconds));

            return formatter.format(calendar.getTime());

        }
        catch(Exception e){
            return milliSeconds;
        }

    }


    public static Date getYesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }


    public static String getYesterdayDateString() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(getYesterday());
    }


    public static ArrayList<String> deleteProtesTheseis(ArrayList<String> lista, int fores){

        if (fores > lista.size())
            return lista;

        if (fores > 0) {
            lista.subList(0, fores).clear();
        }


        return lista;
    }

    public static ArrayList<String> deleteTeleutaiesTheseis(ArrayList<String> lista, int fores){
        for (int i = 0; i<fores; i++){
            lista.remove(lista.size() - 1);
        }
        return lista;

    }



    public static void setValuesTolistaAdaptor(int[] titles_positions, ArrayList<ItemsRV> listAdaptor, ArrayList<String> valuesJson){

        if (titles_positions == null)
            titles_positions  = new int [] {};

        ArrayList<Integer> lista_titles = new ArrayList<>();

        for (int x = 0; x<titles_positions.length; x++) {

            lista_titles.add(titles_positions[x]);     //ΑΠΟΘΗΚΕΥΟΥΜΕ ΤΙΣ ΘΕΣΕΙΣ ΤΩΝ ΤΙΤΛΩΝ
        }

        int position = 0;
        for (int x = 0; x<listAdaptor.size(); x++) {

            if (!lista_titles.contains(x)){             // ΕΔΩ ΟΤΑΝ ΔΕΝ ΣΥΜΠΙΠΤΕΙ Η ΘΕΣΗ ΤΗΣ ΛΙΣΤΑΣ ΤΟΥ ΑΝΤΑΠΤΟΡΑ
                // ΜΕ ΤΗ ΘΕΣΗ ΤΗς ΛΙΣΤΑΣ ΤΩΝ ΤΙΤΛΩΝ ΝΑ ΒΑΖΕΙ ΤΙΜΗ
                if(listAdaptor.get(x).getValue() == null)
                    listAdaptor.get(x).setTrue(valuesJson.get(position).equals("1"));
                else
                    listAdaptor.get(x).setValue(valuesJson.get(position));

                position ++;

            }
        }
    }


    public static ArrayList <String>  replaceTrueOrFalse(ArrayList <String> lista){
        for (int i=0; i<lista.size(); i++){

            if (lista.get(i) == null)
                lista.set(i,"");
            if (lista.get(i).equals("true"))
                lista.set(i,"1");
            if (lista.get(i).equals("false") || lista.get(i).equals(""))
                lista.set(i,"");


        }

        return lista;
    }


    public static void setValuesTo_valuesJSON(int[] titles_positions, ArrayList<ItemsRV> listAdaptor, ArrayList<String> valuesJson){

        ArrayList<Integer> lista_titles = new ArrayList<>();
        ArrayList<ItemsRV> lista_values ;

        lista_values = (ArrayList<ItemsRV>)listAdaptor.clone();

        if (titles_positions == null)
            titles_positions = new int[] {};

        for (int x = 0; x<titles_positions.length; x++) {

            lista_titles.add(titles_positions[x]);     //ΑΠΟΘΗΚΕΥΟΥΜΕ ΤΙΣ ΘΕΣΕΙΣ ΤΩΝ ΤΙΤΛΩΝ
        }

        for (int x = lista_titles.size()-1 ; x >= 0; x--) {

            lista_values.remove((int)lista_titles.get(x));

        }

        //  GIA INSERT
        if (valuesJson.size() == 0) {
            for (int x = 0; x < lista_values.size(); x++) {

                if (lista_values.get(x).getValue() == null)
                    valuesJson.add(x, lista_values.get(x).getTrue().toString());
                else
                    valuesJson.add(x, lista_values.get(x).getValue());
            }
        }

        else {
// GIA UPDATE

            for (int x = 0; x < lista_values.size(); x++) {

                if (lista_values.get(x).getValue() == null)
                    valuesJson.set(x, lista_values.get(x).getTrue().toString());
                else
                    valuesJson.set(x, lista_values.get(x).getValue());
            }

        }
    }

        public static String getColorText(String text, String color) {

         String input = "<font color=" + color + ">" + text + "</font>";

            return  input;
    }

    public static String getColorTextRed(String text) {

        return "<font color=" + "#800000" + ">" + text + "</font>";
    }

    public static String getColorTextBlue(String text) {

        return "<font color=" + "#000080" + ">" + text + "<br> <br>" + "</font>";
    }

    public static String getColorTextGreen(String text) {

        return "<font color=" + "#208000" + ">" + text + "<br> <br>" + "</font>";
    }


    public static String getKeyByValue(Map<String, String> map,String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return  entry.getKey();
            }
        }
        return "";
    }




    public static String [] getArraylistToStringArray(ArrayList<String> lista){

        if (lista != null) {
            String[] str_array = new String[lista.size()];

            for (int i = 0; i < lista.size(); i++) {
                str_array[i] = lista.get(i);
            }

            return str_array;
        }
        else
            return new String[0];


    }

    public static ImageButton thereIsImageUpdateButton(Toolbar toolbar,Context ctx){


        ImageButton but = new ImageButton(ctx);
        Toolbar.LayoutParams l1=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l1.gravity= Gravity.START;
        but.setLayoutParams(l1);
        but.setBackgroundResource(R.drawable.save_icon_48px);
        but.setPadding(20,20,20,20);
        but.setScaleType(ImageButton.ScaleType.FIT_CENTER);
        toolbar.addView(but);
        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        l3.gravity= Gravity.END;
        but.setLayoutParams(l3);

        return but;

    }





    public static void openDocument(Context context, File file) {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= 24) {

            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");

                m.invoke(null);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }


        String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());

        String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);

        if (extension.equalsIgnoreCase("") || mimetype == null) {

            // if there is no extension or there is no definite mimetype, still try to open the file

            intent.setDataAndType(Uri.fromFile(file), "text/*");

        } else {

            intent.setDataAndType(Uri.fromFile(file), mimetype);

        }


        // custom message for the intent

        context.startActivity(Intent.createChooser(intent, "Choose an Application:"));


    }










    public static void openDocumentInternal(Context context) {


        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        File file = new File(context.getFilesDir() + "diaitologio_odigies.xlsx");
        String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file).toString());
        String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        if (extension.equalsIgnoreCase("") || mimetype == null) {
            // if there is no extension or there is no definite mimetype, still try to open the file
            intent.setDataAndType(Uri.fromFile(file), "text/*");
        } else {
            intent.setDataAndType(Uri.fromFile(file), mimetype);
        }
        // custom message for the intent
        context.getApplicationContext().startActivity(Intent.createChooser(intent, "Choose an Application:"));


    }




    public static boolean checkValueInArray(String[] arr, String targetValue) {
        for (String s: arr) {
            if (s.equals(targetValue))
                return true;
        }
        return false;
    }











    public static void copyFileFromAssetsToExternal(Context ctx, String fileName) {

        String folder_main = "nosileutikosFakelos";

        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }

        try{

            InputStream is = ctx.getAssets().open(fileName);
            OutputStream out = null;


            String outDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + StaticFields.MAIN_FOLDER_NOSILEUTIKOS_FAKELOS;

            File outFile = new File(outDir, fileName);
            out = new FileOutputStream(outFile);
            Utils.copyFile(is, out);
            is.close();
            is = null;
            out.flush();
            out.close();
            out = null;



        } catch(Exception e)

        {
            throw new RuntimeException(e);
        }

    }

    public static int parseInt(String num){
        try{
            return Integer.parseInt(num);
        }
        catch (Exception e){
            return -1;
        }
    }


    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }


    public static void setDatePicker(final TextView textView, final Context ctx) {

        textView.setText(Utils.getCurrentDate());

        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateStr = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                textView.setText(sdf.format(myCalendar.getTime()));
            }

        };

        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ctx, dateStr, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }





    public static void showImageDialog(Context ctx, int imageID) {


        Dialog builder = new Dialog(ctx);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (builder.getWindow() != null) {
            builder.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    //nothing;

                }
            });

            ImageView imageView = new ImageView(ctx);
            imageView.setImageResource(imageID);
            builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            builder.show();
        }
    }





    public static void showImageDialog(Context ctx, Bitmap theImage) {

        try{

            Dialog builder = new Dialog(ctx);
            builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (builder.getWindow() != null) {
                builder.getWindow().setBackgroundDrawable(
                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        //nothing;

                    }
                });

                ImageView imageView = new ImageView(ctx);
                imageView.setImageBitmap(theImage);
                imageView.setRotation(90);
               // imageView.setImageBitmap(Bitmap.createScaledBitmap(theImage, imageView.getWidth(), imageView.getHeight(), false));

                builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));


                builder.show();
            }
        }
        catch (Exception e){
            Toast.makeText(ctx, "Η μετατροπή σε εικόνα απέτυχε", Toast.LENGTH_SHORT).show();
           // e.printStackTrace();
        }
    }



    public static AlertDialog.Builder getAlertMultipleChoice(Context ctx, CharSequence[] ITEMS , boolean[] ITEMS_VALUES){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);


        builder.setMultiChoiceItems(ITEMS, ITEMS_VALUES,
                (dialog, which, isChecked) -> {
                    if (isChecked) {
                        ITEMS_VALUES[which] = true;
                    } else {
                        ITEMS_VALUES[which] = false;
                    }
                });

        return builder;
    }


    public static void specifyEdittext(EditText valueET,  int edittextType,int min, int max) {



        if (edittextType == 1)
            valueET.setInputType(InputType.TYPE_CLASS_TEXT  | InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        else if (edittextType == 2){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER );
        }

        else if(edittextType == 3){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        else if(edittextType == 4){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER );
            if ( max!= 0)
                valueET.setFilters(new InputFilter[]{new InputFilterMinMax(min, max)});

        }
        else
        if(edittextType == 5)
            valueET.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME );




    }


    public static boolean hasOnlyNumbers(String str){
         return str.matches("[0-9]+") && str.length() > 2;
    }


    public static boolean checkPreQuery(String table, String[] cols,String[] vals , Context ctx){
        if(table == null || table.isEmpty()) {
            Toast.makeText(ctx, "Δεν έχετε επιλέξει table για αποθήκευση", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cols == null || vals == null){
            Toast.makeText(ctx, "Τα πεδία ή οι τιμές ειναι null", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cols.length != vals.length){
            Toast.makeText(ctx, "Τα πεδία και οι τιμές δεν έχουν το ίδιο μέγεθος", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }
    public static String createInsert(String table, String[] cols,String[] vals , Context ctx) {
        if (checkPreQuery(table, cols, vals, ctx)) {

            StringBuilder sb = new StringBuilder();
            StringJoiner sj = new StringJoiner(",");
            sb.append("INSERT INTO ").append(table).append(" ( ");
            for (String col : cols) {
                sj.add(col);
            }

            sb.append(sj.toString()).append(") \n").append("VALUES ( ");
            sj = new StringJoiner(",");

            for (String val : vals) {
                sj.add( (hasOnlyNumbers(val) ? val : "'" + val + "'" ));
            }
            sb.append(sj.toString()).append(")");

            return sb.toString();
        }

        return null;
    }



    public static String createUpdate(String table, String[] cols,String[] vals , String where , Context ctx ) {
        if (checkPreQuery(table, cols, vals, ctx)) {

            StringBuilder sb = new StringBuilder();
            StringJoiner sj = new StringJoiner(",");
            sb.append("UPDATE  " + table + " \n");
            sb.append("SET ");
            for (int i=0; i<cols.length && i<vals.length; i++) {
                sj.add(cols[i] + " = " + (hasOnlyNumbers(vals[i]) ? vals[i] : "'" + vals[i] + "'" ));
            }

            sb.append(sj.toString() + "\n");
            sb.append(where);


            return sb.toString();
        }

        return null;
    }

    public String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public static byte[] readFile(File file) throws IOException {
        if (!file.exists()) {
            return null;
        }
        RandomAccessFile ras = new RandomAccessFile(file, "r");
        int size = (int) ras.getChannel().size();
        byte[] bin = new byte[size];
        ras.read(bin, 0, size);
        ras.close();
        return bin;
    }

    public static String getTextFromMultiType(String val,ArrayList<Spinner_item> multiList){
        if (val == null || val.isEmpty())
            return "";

            String[] v_ids = val.split("\ufffd");
            StringJoiner s = new StringJoiner(" , ");

            if (v_ids.length > 0) {
                for (int i = 0; i < v_ids.length; i++)
                    for (int x = 0; x < multiList.size(); x++) {
                        if (Integer.parseInt(v_ids[i]) == multiList.get(x).id) {
                            s.add(multiList.get(x).name);
                            break;
                        }
                    }
            }
            return s.toString();
    }




    public static String convertFileTOBase64String(InputStream inputStream, Context ctx) {
       // InputStream inputStream = null;//You can get an inputStream using any IO API

//        try {
//            inputStream = new FileInputStream(file.getAbsolutePath());
//        } catch (FileNotFoundException e) {
//         //   e.printStackTrace();
//          ..  Toast.makeText(ctx, "Error while getting path " + file.toString(), Toast.LENGTH_SHORT).show();
//
//            return null;
//        }

        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream outputStr = new ByteArrayOutputStream();
        Base64OutputStream output64 = new Base64OutputStream(outputStr, Base64.DEFAULT);

        try {

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }


            output64.close();
            inputStream.close();

        } catch (IOException e) {
          //  e.printStackTrace();
            Toast.makeText(ctx, "Error while converting ", Toast.LENGTH_SHORT).show();
            return outputStr.toString();

        }
        return outputStr.toString();

    }


    private static void copyInputStreamToFile(InputStream in, File file) {
        OutputStream out = null;

        try {
            out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Ensure that the InputStreams are closed even if there's an exception.
            try {
                if ( out != null ) {
                    out.close();
                }

                // If you want to close the "in" InputStream yourself then remove this
                // from here but ensure that you close it yourself eventually.
                in.close();
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }


    public static long getFileSize(long sizeInBytes){
        return sizeInBytes / (1024 * 1024);
    }

    public static byte[]  getNativePhoto(Activity act, String path) throws IOException {
        return  getNativePhoto(act, new File(path));

    }

    public static byte[] getNativePhoto(Activity act, File file) throws IOException {
        Bitmap photo = MediaStore.Images.Media.getBitmap(act.getContentResolver(), Uri.fromFile(file));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        return stream.toByteArray();
    }





    public static String bytesToHex(byte[] bytes) {
        final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = (char) HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = (char) HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static void showNotification(Context context, String title, String message, Intent intent, int reqCode) {

        PendingIntent pendingIntent = PendingIntent.getActivity(context, reqCode, intent, PendingIntent.FLAG_ONE_SHOT);
        String CHANNEL_ID = "channel_name";// The id of the channel.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel Name";// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        notificationManager.notify(reqCode, notificationBuilder.build()); // 0 is the request code, it should be unique id

      //  Log.d("showNotification", "showNotification: " + reqCode);
    }

}
