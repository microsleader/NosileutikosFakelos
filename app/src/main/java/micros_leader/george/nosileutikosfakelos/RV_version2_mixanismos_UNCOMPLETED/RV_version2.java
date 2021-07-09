package micros_leader.george.nosileutikosfakelos.RV_version2_mixanismos_UNCOMPLETED;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class RV_version2 {




    //TEXTVIEWS
    public final static int TV_KATIGORIA_TITLE= 0;

    public final static int TV_TITLE= 1;
    public final static int TV_DATE = 2;
    public final static int TV_TIME = 3;

    //EDITTEXT
    public final static int EDITTEXT_KEIMENO = 11;
    public final static int EDITTEXT_AKERAIOS = 12;
    public final static int EDITTEXT_DEKADIKOS = 13;

    //CHECKBOX
    public final static int CHECKBOX_TYPE = 21;
    //SPINNER
    public final static int SPINNER_TYPE = 31;
    public ArrayList<String> lista;
    public Map map;

    public String title ,col;
    public int typeChild = 0;
    public TextView textView;
    public EditText editText;
    public Spinner spinner;
    public CheckBox checkBox;
    Context ctx;




    public RV_version2(String col , int typeChild  , String title , Context ctx) {
        this.col = col;
        this.typeChild = typeChild;
        this.title = title;
        this.ctx = ctx;

        initializeTypeChild(typeChild);


    }

    private void initializeTypeChild(int typeChild) {
        switch (typeChild ){

            case TV_TITLE:
                textView = new TextView(ctx);
                textView.setTextColor(Color.RED);

                break;

            case EDITTEXT_KEIMENO:
                editText = new EditText(ctx);
                editText.setInputType(InputType.TYPE_CLASS_TEXT );

                break;


            case EDITTEXT_AKERAIOS:
                editText = new EditText(ctx);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);

                break;


            case EDITTEXT_DEKADIKOS:
                editText = new EditText(ctx);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                break;



            default:
                break;
        }

    }


    public void showAsTitle(){

    }


    public ArrayList<String> getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public int getTypeChild() {
        return typeChild;
    }

    public void setTypeChild(int typeChild) {
        this.typeChild = typeChild;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }
}
