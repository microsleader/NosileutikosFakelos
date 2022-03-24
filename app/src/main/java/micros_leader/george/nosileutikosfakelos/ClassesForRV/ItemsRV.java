package micros_leader.george.nosileutikosfakelos.ClassesForRV;


import java.util.ArrayList;
import java.util.Map;

import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;

public class ItemsRV implements  Comparable{

    public static int TEXTVIEW_TITLE_TYPE = 0;  //titlos i apla mia timi
    // textview type
    public static int MEDICINES =  3; //eleuthero keimeno
    public static int TEXTVIEW_TYPE = 1;
    public static int EDIT_TEXT_TYPE = 2;
    public static int SPINNER_TYPE = 3;  // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΕΧΟΥΜΕ ΣΠΙΝΝΕΡ ΝΑ ΔΗΛΩΝΝΟΥΜΕ ΚΑΙ ΤΗΝ ARRAYLIST ΠΟΥ ΘΑ ΤΡΕΧΕΙ ΑΝ ΕΙΝΑΙ ΔΙΑΘΕΣΙΜΗ
    public static int SPINNER_TYPE_NEW = 12;  //ΚΑΙΝΟΥΡΓΙΟ ΣΠΙΝΕΝΡ ΠΟΥ ΠΑΙΡΝΕΙ ΤΟ ID ΤΟΥ ITEM ΜΕ ΔΙΑΦΟΡΕΤΙΚΟ ΤΡΟΠΟ ΠΙΟ ΑΠΛΟ
    public static int CHECKBOX_TYPE = 4;
    public static int MULTI_TYPE = 2000;
    public String lookup_query;


    public String compareColValue;//ΕΔΩ ΔΙΝΩ ΕΝΑ ΟΝΟΜΑ ΑΛΛΟΥ ΠΕΔΙΟΥ ΩΣΤΕ ΝΑ ΣΥΓΚΡΙΝΕΙ ΤΗΝ ΤΙΜΗ ΤΟΥ ΜΕ ΑΥΤΗ ΠΟΥ ΕΧΕΙ , ΑΝ ΕΙΝΑΙ ΚΕΝΟ ΤΟ ΑΠΟΤΕΛΕΣΜΑ ΝΑ ΒΕΛΕΙ ΤΗΝ ΤΙΜΗ ΑΠΟ ΤΟ compareColValue
    public boolean isCompValueSame; // ΑΝ ΔΕΝ ΕΙΝΑΙ ΙΔΙΑ ΣΤΟ RECYECLERVIEW ΤΟ ΠΕΔΙΟ ΤΟΥ ΘΑ ΕΙΝΑΙ ΚΟΚΚΙΝΟ
    public boolean stable_col;
    public String[] listaArray = new String[]{};
    public int desplay_image;

    // text_type gia textview type = 1
    // type1 = date
    // alli timi simainei apla topothetisi tis tmis apo to server


    // text_type gia edittext


    // type1 = eleuthero keimeno
    // type2 = akeraioi
    // type3 = dekadikoi
    // type4 = na dexetai ena sigekrimeno euros timos opos apo to 1-10



    public String titleID, value , col_name ;
    public Boolean isTrue;
    public int type, texttype ,min,max;
    public ArrayList<String> lista;
    public ArrayList<Spinner_item> sp_item_lista;
    public Map map;
    private  int CUSTOMER_ID;

    public ItemsRV(String titleID , String value, int type){

        this.titleID = titleID;
        this.value = value;
        this.type = type;
    }

    public ItemsRV(String titleID ,String col_name, String value, int type){

        this.titleID = titleID;
        this.col_name = col_name;
        this.value = value;
        this.type = type;
    }


    public ItemsRV(String titleID , String value, int type, int texttype){

        this.titleID = titleID;
        this.value = value;
        this.type = type;
        this.texttype = texttype;
    }


    public ItemsRV(String titleID ,String col_name, String value, int type, int texttype){

        this.titleID = titleID;
        this.col_name = col_name;
        this.value = value;
        this.type = type;
        this.texttype = texttype;
    }



    public ItemsRV(String titleID , boolean isChecked, int type){

        this.titleID = titleID;
        this.isTrue = isChecked;
        this.type = type;
    }


    public ItemsRV(String titleID ,String col_name, boolean isChecked, int type){

        this.titleID = titleID;
        this.col_name = col_name;
        this.isTrue = isChecked;
        this.type = type;
    }


    public ItemsRV(String titleID, String col_name, int type, ArrayList lista){

        this.titleID = titleID;
        this.col_name = col_name;
        this.type = type;
        this.lista = lista;
    }

    public ItemsRV(String titleID, String col_name, int type, Object [] lista){

        this.titleID = titleID;
        this.col_name = col_name;
        this.type = type;

        ArrayList xaxa = new ArrayList();
        this.lista = choices(lista);
    }


    public ItemsRV(String titleID, String col_name, int type, String  lookup_query){

        this.titleID = titleID;
        this.col_name = col_name;
        this.type = type;
        this.lookup_query = lookup_query;
    }


    public ItemsRV(String titleID, String col_name, String value, int textviewWithAlertDialogSpinner, String[] listaArray) {
        this.titleID = titleID;
        this.col_name = col_name;
        this.value = value;
        this.type = textviewWithAlertDialogSpinner;
        this.listaArray = listaArray;

    }


    public String getTitleID() {
        return titleID;
    }

    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }


    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getTrue() {
        if (isTrue == null)
            return false;

        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int gettexttype() {
        return texttype;
    }

    public void settexttype(int texttype) {
        this.texttype = texttype;
    }

    public ArrayList<String> getLista() {
        return lista;
    }

    public ArrayList<Spinner_item> getSpinnerItemLista() {
        return sp_item_lista;
    }
    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }
    public void setLista_default(ArrayList lista){
        this.lista = lista;
    }

    public ArrayList choices(Object[] values){
        try {
            if (values == null || values.length == 0)
                return null ;

            ArrayList <Spinner_item> lista = new ArrayList<>();
            for (int i = 0; i < values.length - 1; i++) { //χω βαελι  - 1 επειδη παντα παιρνει το επομενο ωστε να μη χτυπησει

                int id = (int) values[i];
                String val = String.valueOf(values[i + 1]);
                lista.add(new Spinner_item(id, val));
                i ++;
            }

            return  lista;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getLookup_query() {
        return lookup_query;
    }

    public void setLookup_query(String lookup_query) {
        this.lookup_query = lookup_query;
    }

    public String[] getListaArray() {
        return listaArray;
    }

    public void setListaArray(String[] listaArray) {
        this.listaArray = listaArray;
    }


    public String getcompareColValue() {
        return compareColValue;
    }

    public ItemsRV setcompareColValue(String compareColValue) {
        this.compareColValue = compareColValue;
        return this;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public ItemsRV setCUSTOMER_ID(ArrayList<ItemsRV>list,int CUSTOMER_ID) {

        this.CUSTOMER_ID = CUSTOMER_ID;

        return this;
    }


    public ItemsRV setStable_col(boolean v) {
        this.stable_col = v;
        return this;
    }





    public boolean isStable_col() {
        return stable_col ;
    }



    public int getDesplay_image() {
        return desplay_image;
    }

    public ItemsRV setInfo_image(int desplay_image) {
        this.desplay_image = desplay_image;
        return this;
    }


    @Override
    public int compareTo(Object o) {
        ItemsRV compare = (ItemsRV) o;

        if (compare.getCol_name() == null  ){  //TITLOI
            return 1;
        }

        else if( compare.getCol_name().equals(col_name) && compare.getValue()!= null && compare.getValue().equals(value))
            return 1;

        return 0;

    }




}
