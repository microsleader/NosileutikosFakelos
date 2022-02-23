package micros_leader.george.nosileutikosfakelos.TableView;

import java.io.Serializable;
import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;

public class TableViewItem implements Serializable {

    public String id;
    public String column;
    public String force_value ;
    public String lookup_query;
    public int typeElement, textType;
    public ArrayList<Spinner_item> spinnerLista;
    public String title,checkboxTitle;
    private int width;

    public ArrayList<TableViewItem> tableLista = new ArrayList<>();

    private boolean stable_col = false; //ΜΕΤΑΒΛΗΤΗ ΩΣΤΕ ΜΟΝΟ ΤΟ ΠΕΔΙΟ ΝΑ ΕΙΝΑΙ ΑΚΙΝΗΤΟ ΣΤΟ ΣΚΡΟΛΛ , ΜΟΝΟ ΕΝΑ ΠΕΔΙΟ ΣΕ ΚΑΘΕ ΠΙΝΑΚΑ ΚΑΙ ΜΟΝΟ ΣΕ ΜΟΝΟΔΙΑΣΤΑΤΟ ΠΙΝΑΚΑ

    private boolean editWithNoSameUserID = false;  //ΜΕΤΑΒΛΗΤΗ ΠΟΥ ΑΚΟΜΑ ΚΑΙ ΝΑ ΜΗΝ ΕΙΝΑΙ ΙΔΙΟΣ ΧΡΗΣΤΗΣ ΝΑ ΕΧΕΙ ΤΟ ΔΙΚΑΙΩΜΑ ΝΑ ΚΑΝΕΙ ΑΛΛΑΓΗ ΣΤΟ ΣΥΓΚΕΚΡΙΜΕΝΟ ΠΕΔΙΟ
    private TextViewTableListener textViewTableListener;


    public TableViewItem(){


    }


    public TableViewItem(String title, String column, int typeElement, int textType) {
        this.title = title;
        this.column = column;
        this.typeElement = typeElement;
        this.textType = textType;
    }

    public TableViewItem( String column, int typeElement, int textType) {
        this.column = column;
        this.typeElement = typeElement;
        this.textType = textType;
    }


    public TableViewItem(String title, String column, int typeElement, ArrayList<Spinner_item> spinnerLista) {
        this.title = title;
        this.column = column;
        this.typeElement = typeElement;
        this.spinnerLista = spinnerLista;
    }

    public TableViewItem( String column, int typeElement, ArrayList<Spinner_item> spinnerLista) {
        this.column = column;
        this.typeElement = typeElement;
        this.spinnerLista = spinnerLista;
    }

    public TableViewItem( String title,String column, int typeElement, String lookup_query) {
        this.title = title;
        this.column = column;
        this.typeElement = typeElement;
        this.lookup_query = lookup_query;
    }

    public TableViewItem( String column, int typeElement, String lookup_query) {
        this.column = column;
        this.typeElement = typeElement;
        this.lookup_query = lookup_query;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(int typeElement) {
        this.typeElement = typeElement;
    }

    public int getTextType() {
        return textType;
    }

    public void setTextType(int textType) {
        this.textType = textType;
    }

    public ArrayList<Spinner_item> getSpinnerLista() {
        return spinnerLista;
    }

    public String getLookup_query() {
        return lookup_query;
    }

    public void setLookup_query(String lookup_query) {
        this.lookup_query = lookup_query;
    }

    public void setSpinnerLista(ArrayList<Spinner_item> spinnerLista) {
        this.spinnerLista = spinnerLista;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TableViewItem setValue(boolean v) {
        if (v)
            force_value = "true";
        else
            force_value = "false";
        return this;
    }

    public TableViewItem setValue(String v) {
        this.force_value = v;
        return this;

    }

    public TableViewItem editWithNoSameUserID(boolean v) {
        this.editWithNoSameUserID = v;
        return this;

    }



    public TableViewItem setStable_col(boolean v) {
        this.stable_col = v;
        return this;
    }

    public boolean isStable_col() {
        return stable_col ;
    }


    public void additem(TableViewItem item){
        tableLista.add(item);
    }

    public TableViewItem setCheckboxTitle(String title){
        checkboxTitle = title;
        return this;
    }

    public String getCheckBoxTitle(){
        return checkboxTitle;
    }

    public String getValueFromSpinnerList(int pos){
        if (spinnerLista == null)
            return  "";

       return spinnerLista.get(pos).name;
    }

    public boolean isEditWithNoSameUserID() {
        return editWithNoSameUserID;
    }


    public TableViewItem setTextViewTableListener(String query, String transgroupID, ArrayList<TableViewItem> lista, boolean exeiWatchID, boolean exeiSinolo, boolean editable) {
        this.textViewTableListener = new TextViewTableListener(query, transgroupID, lista, exeiWatchID, exeiSinolo, editable);
        return this;
    }

    public TextViewTableListener getTextViewTableListener(){
        return textViewTableListener;
    }




    public static class TextViewTableListener implements Serializable {
        String query ,transgroupID;
        ArrayList<TableViewItem> lista;
        boolean exeiWatchID,  exeiSinolo,  editable;


        public TextViewTableListener(String query, String transgroupID,  ArrayList<TableViewItem> lista, boolean exeiWatchID, boolean exeiSinolo, boolean editable) {
            this.query = query;
            this.transgroupID = transgroupID;
            this.lista = lista;
            this.exeiWatchID = exeiWatchID;
            this.exeiSinolo = exeiSinolo;
            this.editable = editable;
        }
    }
}
