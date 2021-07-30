package micros_leader.george.nosileutikosfakelos.ClassesForRV;

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
        return table_lista;
    }
}
