package micros_leader.george.nosileutikosfakelos.ClassesForRV;

import static micros_leader.george.nosileutikosfakelos.StaticFields.TITLE_ITEM;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.MyApplication;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;

public class BasicList {


    //ΑΝ ΥΠΑΡΧΟΥΝ ΤΙΤΛΟΙ ΣΤΟ RV
    // ΓΙΑ ΚΑΘΕ ΠΕΛΑΤΗ ΠΡΕΠΕΙ ΝΑ ΜΠΟΥΝ ΔΙΑΦΟΡΕΤΙΚΕΣ ΘΕΣΕΙΣ ΣΤΟ titloi_positions
    private final ArrayList<ItemsRV> lista = new ArrayList<>();
    private final ArrayList<TableViewItem> table_lista = new ArrayList<>();
    private int CUSTOMER_ID = 0;

    public BasicList (){

        CUSTOMER_ID = Utils.getCustomerID(MyApplication.getAppContext());
    }

    public void add (ItemsRV x){
        lista.add(x);
    }
    public void add (int custID,ItemsRV x){
        if (custID > 0 && custID == CUSTOMER_ID ){
            lista.add(x);
        }
    }


    //----------------
    public void remove (ItemsRV x){
        lista.remove(x);
    }

    public ArrayList<ItemsRV> getList(){
        return lista;
    }


    public void add (TableViewItem x){
        table_lista.add(x);
    }
    public void add (int custID,TableViewItem x){
        if (custID > 0 && custID == CUSTOMER_ID ){
            table_lista.add(x);
        }
    }

    public void remove(TableViewItem x){
        table_lista.remove(x);
    }

    public ArrayList<TableViewItem> getTableList(){
        if (table_lista.size() > 0)
            return table_lista;
        else if (lista.size() >0 && table_lista.size() == 0 )
            return convertListToTableList();

        return table_lista;
    }

    private ArrayList<TableViewItem> convertListToTableList(){
        for (int i=0; i<lista.size(); i++){

            if (lista.get(i).type == TITLE_ITEM)
                continue;

            TableViewItem x = new TableViewItem();

            x.title = lista.get(i).getTitleID();
            x.column = lista.get(i).col_name;
            if (!x.column.equalsIgnoreCase("id") && lista.get(i).value != null && !lista.get(i).value.isEmpty())
                x.force_value = lista.get(i).value;

            x.textType = lista.get(i).texttype;
            x.typeElement = lista.get(i).type;
            x.setStable_col(lista.get(i).stable_col);
            if (lista.get(i).sp_item_lista != null)
                x.spinnerLista = lista.get(i).sp_item_lista;
            else if ( lista.get(i).lista != null )
                x.spinnerLista = (ArrayList) lista.get(i).lista;

            x.lookup_query = lista.get(i).lookup_query;
            x.normalMinValue = lista.get(i).normalMinValue;
            x.normalMaxValue = lista.get(i).normalMaxValue;

            table_lista.add(x);
        }
        return table_lista;
    }
}
