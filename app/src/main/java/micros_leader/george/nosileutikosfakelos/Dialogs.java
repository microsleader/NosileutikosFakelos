package micros_leader.george.nosileutikosfakelos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AlertDialog;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;

public class Dialogs {

    public static void showText(Activity ctx, String title,String text) {
        new AlertDialog.Builder(ctx)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


    public static void setDialogInputText(Activity ctx, final TextView textView, ItemsRV itemsRV, ArrayList<ItemsRV> result, int pos){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);

        LinearLayout layout = new LinearLayout(ctx);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(parms);

        layout.setGravity(Gravity.CLIP_VERTICAL);
        layout.setPadding(2, 2, 2, 2);

        TextView tv = new TextView(ctx);
        tv.setText(itemsRV.getTitleID());
        tv.setPadding(40, 40, 40, 40);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);



        final EditText et = new EditText(ctx);
        et.setText(itemsRV.getValue());
        Utils.specifyEdittext(et, itemsRV.gettexttype(),itemsRV.min,itemsRV.max);
        et.requestFocus();


        LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1Params.bottomMargin = 5;
        layout.addView(et, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        alertDialogBuilder.setView(layout);
        alertDialogBuilder.setTitle("title");
        alertDialogBuilder.setCustomTitle(tv);


        alertDialogBuilder.setCancelable(true);

        // Setting Negative "Cancel" Button
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });


        //  Setting Positive "OK" Button
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String val = et.getText().toString().trim();
                textView.setText(val);
                result.get(pos).setValue(val);
                et.clearFocus();

            }
        });



         AlertDialog alertDialog = alertDialogBuilder.create();




        try {
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    et.clearFocus();

                InputMethodManager imm = (InputMethodManager) layout.getRootView().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);

                }
            });

        } catch (Exception e) {
            // WindowManager$BadTokenException will be caught and the app would
            // not display the 'Force Close' message
            e.printStackTrace();
        }
    }






    public static void setDialogInputTextTable(Activity ctx, final TextView textView, String title, HashMap<Integer, ArrayList<String>> valuesMap, ArrayList<String> valuesLista, int positionRow, int indexOfColumn, String value, int textType){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);

        LinearLayout layout = new LinearLayout(ctx);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(parms);

        layout.setGravity(Gravity.CLIP_VERTICAL);
        layout.setPadding(2, 2, 2, 2);

        TextView tv = new TextView(ctx);
        tv.setText(title);
        tv.setPadding(40, 40, 40, 40);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);



        final EditText et = new EditText(ctx);
        et.setText(valuesLista.get(indexOfColumn));
        Utils.specifyEdittext(et, textType,0,0);
        et.requestFocus();




        LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1Params.bottomMargin = 5;
        layout.addView(et, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        alertDialogBuilder.setView(layout);
        alertDialogBuilder.setTitle("title");
        alertDialogBuilder.setCustomTitle(tv);


        alertDialogBuilder.setCancelable(true);

        // Setting Negative "Cancel" Button
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });


        //  Setting Positive "OK" Button
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String val = et.getText().toString().trim();
                textView.setText(val);
                valuesLista.set(indexOfColumn,val);
                valuesMap.put(positionRow, valuesLista);
                et.clearFocus();

            }
        });



        AlertDialog alertDialog = alertDialogBuilder.create();




        try {
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    et.clearFocus();

                    InputMethodManager imm = (InputMethodManager) layout.getRootView().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.RESULT_HIDDEN, 0);

                }
            });

        } catch (Exception e) {
            // WindowManager$BadTokenException will be caught and the app would
            // not display the 'Force Close' message
            e.printStackTrace();
        }
    }








    public static void closeInput(final View caller) {
        caller.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(caller.getContext(), "xoxo", Toast.LENGTH_SHORT).show();
                InputMethodManager imm = (InputMethodManager) caller.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(caller.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 100);
    }

    protected static void hideKeyboard(Activity act) {
        final Activity activity = act;
        final View view = activity != null ? activity.getCurrentFocus() : null;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null)
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }, 1);
    }




    public static AlertDialog.Builder getAlertMultipleChoice(Context ctx, ArrayList<Spinner_item> lista , String[] items_ids){

        if (lista.get(0).id == 0 && lista.get(0).name.equals("")) // ΠΑΝΤΑ ΤΟ ΠΡΩΤΟ ΙΤΕΜ ΕΙΝΑΙ (0,"") ΑΛΛΑ ΕΠΕΙΔΗ ΜΠΟΡΕΙ ΝΑ ΤΥΧΕΙ ΕΞΑΙΡΕΣΗ ΤΟ ΒΑΖΩ ΣΕ IF
            lista.remove(0);                                //ΤΟ ΒΑΖΩ ΓΙΑ ΝΑ ΜΗΝ ΜΟΥ ΒΓΑΛΕΙ ΣΤΗΝ ΛΙΣΤΑ ΕΠΙΛΟΓΗΣ ΤΗΝ ΚΕΝΗ ΤΙΜΗ

        String []  ITEMS = new String[lista.size() ] ;
        boolean [] ITEMS_VALUES = new boolean[lista.size() ] ;

        for (int i=0; i<lista.size(); i++){
            ITEMS[i] = lista.get(i).getName();
            if (items_ids.length > 0 && !items_ids[0].isEmpty()) {
                for (int x=0; x<items_ids.length; x++){
                    if (lista.get(i).getId() == Integer.parseInt(items_ids[x])){
                        ITEMS_VALUES [i] = true;
                        lista.get(i).setChecked(true);
                        break;
                    }
                    else {
                        ITEMS_VALUES[i] = false;
                        lista.get(i).setChecked(false);
                    }

                }
            }

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);


        builder.setMultiChoiceItems(ITEMS, ITEMS_VALUES,
                (dialog, which, isChecked) -> {
                    if (isChecked) {
                        ITEMS_VALUES[which] = true;
                        lista.get(which).setChecked(true);

                    } else {
                        ITEMS_VALUES[which] = false;
                        lista.get(which).setChecked(false);
                    }
                });

        return builder;
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

}
