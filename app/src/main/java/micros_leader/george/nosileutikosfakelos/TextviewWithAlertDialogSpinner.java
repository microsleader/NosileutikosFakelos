package micros_leader.george.nosileutikosfakelos;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class TextviewWithAlertDialogSpinner implements View.OnClickListener{


    private Activity act;
    private TextView textView;
    private String title;
    private ArrayList lista;
    private String[] str_array;

    public  TextviewWithAlertDialogSpinner(Activity act, TextView textView, String title, ArrayList lista){
        this.act = act;
        this.textView = textView;
        this.lista = lista;
        this.title = title;
        if (lista != null)
        str_array =  Utils.getArraylistToStringArray(lista);

    }

    public  TextviewWithAlertDialogSpinner(Activity act,TextView textView,  String title, String[] str_array){
        this.act = act;
        this.textView = textView;
        this.title = title;
        this.str_array = str_array;

    }


    @Override
    public void onClick(View v) {

        AlertDialog.Builder b = new AlertDialog.Builder(act);
        b.setTitle(title);

        b.setItems(str_array, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

                textView.setText(str_array[which]);


            }

        });

        b.show();
    }
}
