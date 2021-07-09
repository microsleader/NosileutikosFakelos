package micros_leader.george.nosileutikosfakelos.RV_version2_mixanismos_UNCOMPLETED;

import android.content.Context;

import java.util.ArrayList;

public class RV2_listes {

    //TEXTVIEWS
    public static int TV_TITLE= 1;
    public static int TV_DATE = 2;
    public static int TV_TIME = 3;

    //EDITTEXT
    public static int EDITTEXT_KEIMENO = 11;
    public static int EDITTEXT_AKERAIOS = 12;
    public static int EDITTEXT_DEKADIKOS = 13;

    //CHECKBOX
    public static int CHECKBOX_TYPE = 21;
    //SPINNER
    public static int SPINNER_TYPE = 31;



    public static ArrayList<RV_version2> getExampleList(Context ctx){

        ArrayList<RV_version2> lista = new ArrayList<>();

        lista.add(new RV_version2("col1",TV_TITLE, "titlos 1" ,ctx));
        lista.add(new RV_version2("col 2",EDITTEXT_KEIMENO, "titlos 2" ,ctx));
        lista.add(new RV_version2("col 3",EDITTEXT_AKERAIOS, "titlos 3" ,ctx));

        return lista;

    }

}
