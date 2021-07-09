package micros_leader.george.nosileutikosfakelos.TableView;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.provider.MediaStore;

import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.core.content.FileProvider;
import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskUpdate;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncGetUpdateResult;
import micros_leader.george.nosileutikosfakelos.Interfaces.MyDialogFragmentMedicineCloseListener;
import micros_leader.george.nosileutikosfakelos.R;
import micros_leader.george.nosileutikosfakelos.Str_queries;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.Permissions.MY_PERMISSIONS_CAMERA;

public class TableActivity extends AppCompatActivity implements MyDialogFragmentMedicineCloseListener
        //AsyncCompleteTask2 , AsyncGetUpdate_JSON , , AsyncGetDelete
{


    // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΘΕΛΟΥΜΕ CHECKBOX ΣΕ ΚΑΠΟΙΟ COLUMN NAME ΤΟΤΕ ΠΡΕΠΕΙ ΝΑ ΓΡΑΦΤΕΙ "column_name,checkbox" MONO ETSI DOYLEYEI

    private EditText text;
    private TextView infoTV;
    private EditText infoET;
    private CheckBox infoCH;
    private Spinner infoSP;
    private TableRow firstRow;
    private TableLayout llheader, ll;
    public boolean editextsUsingDialogs ;

    public  AlertDialog alertDialog;

    private Toolbar t ;
    private Table tableClass;
    private Activity act;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        ll = findViewById(R.id.displayLinear);

        t = findViewById(R.id.toolbar);
        act = this;
        alertDialog = Utils.setLoadingAlertDialog(act);
        Intent in = getIntent();

        tableClass =  new Table(ll, t, in, this);




    }



    @Override
    public void dialogMedicineClose(String id_name) {
//        if (currentTextView != null)
//            currentTextView.setText(id_name);
//
//        ArrayList<String> valuesLista = valuesMap.get(currentPosRow);
//        if (valuesLista != null) {
//            valuesLista.set(currentIndexOfCol, Utils.getfirstPartSplitCommaString(currentTextView.getText().toString()));
//            valuesMap.put(currentPosRow, valuesLista);
     //   }
        tableClass.setInfoFromMedicineDialogFragment(id_name);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                try {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // Ensure that there's a camera activity to handle the intent
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        // Create the File where the photo should go
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                            photoPath = photoFile.getAbsolutePath();
                        } catch (IOException ex) {
                            // Error occurred while creating the File

                        }
                        // Continue only if the File was successfully created
                        if (photoFile != null) {
                            Uri photoURI = FileProvider.getUriForFile(this,
                                    "micros_leader.george.nosileutikosfakelos.fileprovider",
                                    photoFile);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePictureIntent, MY_PERMISSIONS_CAMERA);
                        }
                    }




                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            } else {
                Toast.makeText(this, "permission denied ", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions,
                    grantResults);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MY_PERMISSIONS_CAMERA:
                if (resultCode == Activity.RESULT_OK){
                    try {
                        alertDialog.show();
                         File file = new File(photoPath);
                  //  long sizeInBytes = Utils.getFileSize(file.length());
                  //  long sizeInMb = sizeInBytes / (1024 * 1024);

                            byte [] photoBytes = Utils.getNativePhoto(act,file);
                            String byteStr = Utils.bytesToHex(photoBytes);

                            if (!byteStr.isEmpty()){
                                AsyncTaskUpdate task = new AsyncTaskUpdate(TableActivity.this,
                                        Str_queries.insertIntoMedicalRecordsFile(
                                                Utils.getcompanyID(act), tableClass.patientID, tableClass.transgroupID,
                                                Utils.getLinkDoctorID(act), byteStr, "jpg"));

                                task.listener = str -> {
                                    alertDialog.dismiss();
                                    Toast.makeText(act, str, Toast.LENGTH_SHORT).show();
                                };
                                task.execute();
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }













    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        //mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onDestroy() {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
        super.onDestroy();
    }


    public void set_editextsUsingDialogs (boolean x){
        editextsUsingDialogs = x;
    }




}
