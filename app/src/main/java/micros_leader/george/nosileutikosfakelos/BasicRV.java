package micros_leader.george.nosileutikosfakelos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;

import micros_leader.george.nosileutikosfakelos.AsyncTasks.AsyncTaskGetJSON2;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;
import micros_leader.george.nosileutikosfakelos.DiffUtil.ItemsRv_Callback;
import micros_leader.george.nosileutikosfakelos.Interfaces.AsyncCompleteTask2;
import micros_leader.george.nosileutikosfakelos.Interfaces.DataSended;
import micros_leader.george.nosileutikosfakelos.Listeners.SearchMedicineListener_Base;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim;


import static micros_leader.george.nosileutikosfakelos.StaticFields.MULTI_TYPE;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.TEXTVIEW_TITLE_TYPE;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.DEKADIKOS;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.EUROS_TIMON;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.MEDICINES;
import static micros_leader.george.nosileutikosfakelos.StaticFields.AKERAIOS;
import static micros_leader.george.nosileutikosfakelos.StaticFields.AKERAIOS_WITH_NEGATIVE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.CHECKBOX_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.CHECKBOX_TYPE_READ_ONLY_VALUE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.DEKADIKOS_WITH_NEGATIVE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.EDITTEXT_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.SPINNER_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.SPINNER_ITEM_NEW;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_CLOCK_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_DATETIME_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_DATE_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_ITEM_READ_ONLY_VALUE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_WITH_ALERT_DIALOG_SPINNER;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TITLE_ITEM;

public class BasicRV extends RecyclerView.Adapter<BasicRV.MyViewHolder> implements SearchView.OnQueryTextListener {


    public  ArrayList<ItemsRV> result;
    public  ArrayList<String> oldValuesLista;
    public  Activity act;
    private final int custID;
    private int [] titloi_positions;
    public DataSended dataSendedListener;
    //ΧΡΗΣΙΜΟΠΟΙΕΙ DIALOGS ΓΙΑ ΝΑ ΚΑΝΕΙ ΕΙΣΑΓΩΓΗ ΚΕΙΜΕΝΟΥ ΚΑΙ ΚΑΝΕΙ
    // ΤΟ RECYLERVIEW  ΝΑ ΠΗΓΑΙΝΕΙ ΠΙΟ ΓΡΗΓΟΡΑ
    // TA EDITEXTS ΑΡΓΟΥΝ ΤΟ RECYLERVIEW ΕΚΤΟΣ ΑΝ ΕΙΝΑΙ enabled = false;
    public boolean editextsUsingDialogs = false;
    private ArrayList <Integer> titloi_position_lista =  new ArrayList<>();
    private boolean isIncludingOldValues = false;






    // create constructor to innitilize context and data sent from MainActivity
    public BasicRV(Activity act, ArrayList<ItemsRV> result) {

        this.act = act;
        this.result = result;
    //    setHasStableIds(true);
        dataSendedListener = (DataSended) act;
        custID = Utils.getCustomerID(act);


    }

    public BasicRV(Activity act, ArrayList<ItemsRV> result, ArrayList oldValuesLista, boolean isIncludingOldValues) {

        this.act = act;
        this.result = result;
        this.oldValuesLista = oldValuesLista;
        this.isIncludingOldValues = isIncludingOldValues;
     //   setHasStableIds(true);
        dataSendedListener = (DataSended) act;
        custID = Utils.getCustomerID(act);

    }

    public void set_editextsUsingDialogs (boolean x){
        editextsUsingDialogs = x;
    }




    public BasicRV(Activity act, ArrayList<ItemsRV> result, int titloi_positions[]) {


        this.act = act;
        this.result = result;
        this.titloi_positions = titloi_positions;

        for (int i=0; i<titloi_positions.length; i++){
            titloi_position_lista.add(titloi_positions[i]);
        }

      //  setHasStableIds(true);
        dataSendedListener = (DataSended) act;
        custID = Utils.getCustomerID(act);

    }

    public ArrayList<ItemsRV> getItems(){
        return result;
    }

    public void setItems(ArrayList<ItemsRV> lista){
        result = lista;
    }




    public void updateLista(ArrayList  lista) {
         ItemsRv_Callback diffCallback = new ItemsRv_Callback(this.result, lista);
         DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
         diffResult.dispatchUpdatesTo(this);
         this.result.clear();
         this.result.addAll(lista);

    }

    public void updateOldLista(ArrayList <String> lista) {
        ItemsRv_Callback diffCallback = new ItemsRv_Callback(this.oldValuesLista, lista);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diffResult.dispatchUpdatesTo(this);
        this.oldValuesLista.clear();
        this.oldValuesLista.addAll(lista);

    }






    @Override
    public int getItemViewType(int position) {

        return result.get(position).getType();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public BasicRV.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = null;

        if (isIncludingOldValues){

            // ΕΔΩ ΘΑ ΤΡΕΞΕΙ ΟΤΑΝ ΕΧΟΥΜΕ ΤΙΤΛΟ ΠΑΛΙΑ ΤΙΜΗ ΚΑΙ ΤΙΜΗ ΠΟΥ ΘΑ ΕΙΣΑΓΟΥΜΕ

            switch (viewType) {


                case 0: {
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_titles_rec_adapter, parent, false);
                    break;
                }

                case 1:
                case 5:
                case 8:
                case 10:
                case 138:

                    {

                        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_with_old_textview_rec_adapter, parent, false);

                    break;
                }


                case 2: {

                        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_with_old_edittext_rec_adapter, parent, false);

                    break;
                }

                case 3:
                case 12: {

                        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_with_old_spinner_rec_adapter, parent, false);

                    break;
                }


                case 4: {
                        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_item_with_old_checkbox_rec_adapter, parent, false);
                    break;
                }


            }

            }

        else{
// ΕΔΩ ΘΑ ΤΡΕΞΕΙ ΟΤΑΝ ΕΧΟΥΜΕ ΤΙΤΛΟ ΚΑΙ ΤΙΜΗ ΜΟΝΟ

        switch (viewType) {


            case 0: {
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_titles_rec_adapter, parent, false);
                break;
            }

            case 1:

            case 5:

            case 8:

            case 10:
            case 138:

            case 2000: {
                if (custID == Customers.CUSTID_MEDITERRANEO && act instanceof MainActivity_Aim)
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_medit_many_items_textview_rec_adapter, parent, false);
                else
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_textview_rec_adapter, parent, false);
                break;
            }

            case 2: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_edittext_rec_adapter, parent, false);
                break;
            }
            case 3:
            case 12: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_items_spinner_rec_adapter, parent, false);
                break;
            }
            case 4:

            case 14: {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_many_item_checkbox_rec_adapter, parent, false);

                break;
            }

        }

        }


        BasicRV.MyViewHolder vh = new BasicRV.MyViewHolder(v,viewType,dataSendedListener); // pass the view to View Holder
   //     vh.getAdapterPosition();


        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {

        // ΕΔΩ ΘΑ ΜΠΕΙ ΓΙΑ ΤΑ ΙΤΕΜΣ ΠΟΥ ΕΧΟΥΜΕ ΣΤΗΝ ΟΘΟΝΗ ΕΚΕΙΝΗ ΤΗ ΣΤΙΓΜΗ ΚΑΙ ΑΛΛΑΖΟΥΝ ΚΑΙ ΠΡΕΠΕΙ ΝΑ ΤΑ ΑΛΛΑΞΩ
        // ΣΤΑ ΑΛΛΑ ΠΟΥ ΔΕΝ ΦΑΙΝΟΝΤΑΙ ΚΑΙ Α ΕΧΟΥΝ ΑΛΛΑΞΕΙ ΔΕΝ ΘΑ ΜΠΕΙ ΕΠΕΙΔΗ ΘΑ ΤΑ ΦΟΡΤΩΣΕΙ ΚΑΤΑ ΤΟ ΣΚΡΟΛ
        //Diff utils
        if (payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads);
        }
        else{
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("value")) {
                    String val  = o.getString("value");
                   // String val  = result.get(position).getValue();

                    if (holder.valueTV != null) {
                        int type = result.get(position).getType();
                        int textType = result.get(position).gettexttype();

                        if (type== TEXTVIEW_ITEM && textType == TEXTVIEW_DATE_TYPE)
                            holder.valueTV.setText(Utils.convertMillisecondsTO_onlyDate(val));
                        else if (type== TEXTVIEW_ITEM)
                            holder.valueTV.setText(Utils.convertMillisecondsToDateTime(val));
                        else if (type == TEXTVIEW_ITEM_READ_ONLY_VALUE && textType == TEXTVIEW_DATE_TYPE)
                            holder.valueTV.setText(Utils.convertMillisecondsTO_onlyDate(val));
                        else  if (type == TEXTVIEW_CLOCK_TYPE    ||   ( type == TEXTVIEW_ITEM_READ_ONLY_VALUE && textType == TEXTVIEW_CLOCK_TYPE))
                            holder.valueTV.setText(Utils.convertMilliSecondsToTime(val));
                        else if (type == TEXTVIEW_DATETIME_TYPE)
                            holder.valueTV.setText(Utils.convertMillisecondsToDateTime(val));

                        else if (type== MULTI_TYPE) {
                            ArrayList ar =  result.get(position).getLista();
                            ArrayList <Spinner_item> multiList =  ar;
                            holder.valueTV.setText(Utils.getTextFromMultiType(val,multiList));
                        }

                        else
                            holder.valueTV.setText(val);
                    }

                    else if (holder.valueET != null)
                      holder.valueET.setText(val);

                    else if (holder.valueSP != null){
                        if (val == null || val.equals(""))
                            val = "0";

                        if (holder.valueSP.getAdapter() != null){
                            SpinnerAdapter adapter =  holder.valueSP.getAdapter();

                            // το Spinner_new_Image_Adapter ειναι instance του ArrayAdapter επειδη κανει extends
                            if (adapter instanceof ArrayAdapter && !(adapter instanceof Spinner_new_Image_Adapter)){
                                if (adapter.getCount() >=0 )
                                    holder.valueSP.setSelection(Integer.parseInt(val));
                            }
                            else{
                                Spinner_new_Image_Adapter adap = (Spinner_new_Image_Adapter) holder.valueSP.getAdapter();
                                int realPos  =  adap.getPosition(new Spinner_item(Integer.parseInt(val), null));
                                holder.valueSP.setSelection(realPos == -1 ? 0 : realPos);
                            }

                        }

                    }

                    else if (holder.valueCH != null) {
                        holder.valueCH.setChecked(result.get(position).isTrue);
                        if (val != null && result.get(position).getValue() != null) {
                            holder.valueCH.setText(val); //ΕΔΩ ΣΤΗΝ ΟΥΣΙΑ ΘΑ ΜΠΕΙ ΜΟΝΟ ΓΙΑ ΤΙΣ ΣΥΝΕΧΕΙΣ ΜΕΤΡΗΣΕΙΣ
                            holder.valueCH.setEnabled(!val.isEmpty());
                        }
                    }


                }
            }
        }


    }




    @Override
    public void onBindViewHolder(final BasicRV.MyViewHolder holder, final int position) {
        // set the data in items

        String title = result.get(position).getTitleID();
        String value = result.get(position).getValue();
        String compCol = result.get(position).getcompareColValue();
        boolean isCompValueSame = result.get(position).isCompValueSame;
        int desplayImageID = result.get(position).getDesplay_image();



        int type = result.get(position).getType();
        int textType = result.get(position).gettexttype();

        // TITLE

        if (type == TEXTVIEW_TITLE_TYPE) {


            holder.titleTV.setText(title);
        }


        // TEXTVIEW

        if (type == TEXTVIEW_ITEM || type == TEXTVIEW_ITEM_READ_ONLY_VALUE || type == TEXTVIEW_CLOCK_TYPE ||
                type == TEXTVIEW_DATETIME_TYPE || type == TEXTVIEW_WITH_ALERT_DIALOG_SPINNER) {


            if (type == TEXTVIEW_ITEM && textType == TEXTVIEW_DATE_TYPE)
                value = Utils.convertMillisecondsTO_onlyDate(value);
            else if (type == TEXTVIEW_ITEM || type == TEXTVIEW_DATETIME_TYPE)
                 value = Utils.convertMillisecondsToDateTime(value);
            else if (type == TEXTVIEW_ITEM_READ_ONLY_VALUE && textType == TEXTVIEW_DATE_TYPE)
                value = Utils.convertMillisecondsTO_onlyDate(value);
           else  if (type == TEXTVIEW_CLOCK_TYPE || type == TEXTVIEW_ITEM_READ_ONLY_VALUE && textType == TEXTVIEW_CLOCK_TYPE)
               value = Utils.convertMilliSecondsToTime(value);




            if (oldValuesLista !=null)
                if (position < oldValuesLista.size()) {
                    if (type == TEXTVIEW_ITEM || type == TEXTVIEW_DATETIME_TYPE)
                        holder.oldValueTV.setText(Utils.convertMillisecondsToDateTime(oldValuesLista.get(position)));
                    else if (type == TEXTVIEW_CLOCK_TYPE)
                        //holder.oldValueTV.setText(Utils.convertMilliSecondsToTime(oldValuesLista.get(position)));
                    holder.oldValueTV.setText(Utils.convertMilliSecondsToTime(oldValuesLista.get(position)));

                    else
                        holder.oldValueTV.setText(oldValuesLista.get(position));

                }

                holder.valueTV.setText(value);
                holder.titleTV.setText(title);


            //    holder.valueTV.setText(convertMillisecondsToDate(value));


            holder.valueTV.setTag(position);
        }




        if (type == TEXTVIEW_ITEM && result.get(position).gettexttype() == MEDICINES) {


            holder.titleTV.setText(title);
            holder.valueTV.setText(value);
            holder.valueTV.setOnClickListener(new SearchMedicineListener_Base(act));


            holder.valueTV.setTag(position);
        }


        // EDITTEXT
        if (type == 2) {
            holder.valueET.setTag(position);

            holder.titleTV.setText(title);
            holder.valueET.setText(value);

            if (!editextsUsingDialogs) {
                int edittextType = result.get(position).gettexttype();
                int min = result.get(position).getMin();
                int max = result.get(position).getMax();

                specifyEdittext(holder.valueET, title, edittextType, min, max);
            }


            if (compCol != null && !isCompValueSame)
                setStrokeColor(holder.valueET,3,Color.RED);
            else
                setStrokeColor(holder.valueET,3, Color.parseColor("#038768"));

//   ΓΙΑ ΤΗΝ ΤΕΛΕΥΤΑΙΑ ΠΑΛΙΑ ΤΙΜΗ

            if (oldValuesLista !=null)
                if (position < oldValuesLista.size()) {
                    holder.oldValueTV.setText(oldValuesLista.get(position));
                    //ΤΟ ΒΑΖΩ ΚΑΙ ΕΔΩ ΕΠΕΙΔΗ ΟΤΑΝ ΤΟ ΠΡΟΗΓΟΥΜΕΝΟ VIEW ΕΙΝΑΙ ΚΟΚΚΙΝΟ, ΓΙΝΕΤΑΙ ΚΑΙ ΑΥΤΟ ΚΟΚΚΙΝΟ , ΑΛΛΑ ΜΟΝΟ ΤΟ ΕΝΑ ΕΠΟΜΕΝΟ
                    //Π.Χ. ΣΤΑΘΕΡΕΣ ΜΕΤΡΗΣΕΙΣ ΟΤΑΝ Η ΘΕΡΜΟΚΡΑΣΙΑ ΕΙΝΑΙ ΚΟΚΚΙΝΗ ΤΟ VIEW OLD KTV ΜΕΝΕΙ ΚΑΙ ΑΥΤΟ
                    setStrokeColor(holder.oldValueTV,3, Color.parseColor("#038768"));

                }

        }


        // SPINNER
        else if (type == SPINNER_ITEM || type == SPINNER_ITEM_NEW) {


            ArrayList lista ;
            String lookup_query ;

            if(result.get(position).getLista() != null)
                lista = result.get(position).getLista();
            else
                lista = new ArrayList();


            holder.titleTV.setText(title);
            holder.valueSP.setTag(position);

            if (compCol != null && !isCompValueSame)
                setStrokeColor(holder.valueSP,3,Color.RED);
            else
                setStrokeColor(holder.valueSP,3, Color.parseColor("#038768"));



            //---------
            String valueFromBase = result.get(position).getValue();

            if (valueFromBase == null || valueFromBase.equals("")) {
                valueFromBase = "0";
            }
            //                else if (Integer.parseInt(valueFromBase) > lista.size())
//                    valueFromBase = "0";
             int spinner_position = 0;


            if (type == SPINNER_ITEM) {

                if (result.get(position).getMap() != null) {

                    //GIA SETMAP AN EXOUME VALEI AN THIMAMAI KALA EINIA KALITERA NA EINAI SE MORFI (INT,INT)
                    // EDW DEN MAS KANEI AN TA ID EINAI GIA ATOMA , ITEM P.X. 10803
                    Map map = result.get(position).getMap();

                    if (map.containsKey(Integer.parseInt(valueFromBase)))
                        spinner_position = (int) map.get(Integer.parseInt(valueFromBase));
                    else
                        spinner_position = 0;


                } else
                    spinner_position = Integer.parseInt(valueFromBase);

//   ΓΙΑ ΤΗΝ ΤΕΛΕΥΤΑΙΑ ΠΑΛΙΑ ΤΙΜΗ
                if (oldValuesLista != null)
                    if (position < oldValuesLista.size())
                        holder.oldValueTV.setText(oldValuesLista.get(position));



               addadapterToSpinner(holder.valueSP, spinner_position, lista );
            }

            else {


                //  SPINNER_ITEM_NEW
            if (result.get(position).getValue() == null )
                result.get(position).setValue("");

            if (lista != null) {
                    ArrayList<Spinner_item> finalList = lista;

                    //Toast.makeText(act, "timi server: " + valueFromBase, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < lista.size(); i++) {
                        if (Utils.parseInt(valueFromBase) == finalList.get(i).getId()) {
                            spinner_position = i;
                            break;
                        }
                    }

                    //   ΓΙΑ ΤΗΝ ΤΕΛΕΥΤΑΙΑ ΠΑΛΙΑ ΤΙΜΗ
                    if (oldValuesLista != null)
                        if (position < oldValuesLista.size()) {


                            //ΤΟΠΟΘΕΤΗ ΤΕΛΕΥΤΑΙΑΣ ΠΑΛΙΑΣ ΤΙΜΗΣ ΣΕ ΣΧΕΣΗ ΜΕ ΤΟ ΣΠΙΝΝΕΡ
                           // int new_spinner_adapter_pos = oldValuesLista.get(position).equals("") ? 0 : Integer.parseInt(oldValuesLista.get(position));
                            if (finalList.size() > 0) {
                                holder.oldValueTV.setText(finalList.get(spinner_position).getName());


                                /*
                                ΤΟ ΕΒΑΛΑ ΕΔΩ ΕΠΕΙΔΗ ΒΡΙΣΚΕΙ ΚΑΠΟΙΟ ΠΡΟΒΛΗΜΑ ΜΕ ΤΗΝ ΕΠΙΛΟΓΗ ΤΟΥ ΙΤΕΜ ΣΤΗΝ ΑΝΑΝΕΩΣΗ
                                ΕΒΡΙΣΚΕ ΤΟΝ ΑΝΤΑΠΤΟΡ NULL ΧΩΡΙΣ ΝΑ ΕΧΩ ΒΡΕΙ ΤΟΝ ΛΟΓΟ

                                ΕΠΙΣΗΣ ΚΑΝΩ ΤΟΝ ΕΛΕΓΧΟ ΑΝ Η ΥΠΑΡΧΟΥΣΑ ΛΙΣΤΑ ΕΙΝΑΙ ΙΔΙΑ ΜΕ ΑΥΤΗ ΤΟΥ ΑΝΤΑΠΤΟΡΑ
                                ΕΠΕΙΔΗ ΓΙΑ ΚΑΠΟΙΟ ΛΟΓΟ (ΛΟΓΙΚΑ ΚΑΤΙ ΦΤΑΙΕΙ ΜΕ ΤΟ RECYCLING) ΑΛΛΑΖΕΙ ΤΙΣ ΛΙΣΤΕΣ ΣΤΑ ΣΠΙΝΝΕΡ
                                ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΔΕΝ ΕΙΝΑΙ ΙΔΙΑ ΝΑ ΤΟΥ ΞΑΝΑΒΑΛΕΙ ΤΗ ΣΩΣΤΗ

                                   //ΣΥΓΚΡΙΝΩ ΑΝ ΤΟ ΟΝΟΜΑ ΤΟΥ ΤΕΛΕΥΤΟΥ ΙΤΕΜ ΕΙΝΑΙ ΙΔΙΟ ΜΕΤΑΞΥ ΤΟΥς ΓΙΑ ΕΠΙΠΛΕΟΝ ΕΠΙΒΕΒΑΙΩΣΗ
                                    // ΕΠΕΙΔΗ ΣΕ ΚΑΠΟΙΑ Η ΛΙΣΤΑ ΕΧΕΙ ΤΟ ΙΔΙΟ ΜΕΓΕΘΟΣ ΟΠΟΤΕ ΔΔΗΜΙΟΥΡΓΟΥΣΕ ΠΑΛΙ ΤΟ ΑΠΟ ΠΑΝΩ ΠΡΟΒΛΗΜΑ
                                 */
                                if (holder.valueSP.getAdapter() != null
                                        && (
                                            finalList.size() != holder.valueSP.getAdapter().getCount()
                                             || (! finalList.get(finalList.size() -1 ).getName().equals(
                                                 ((Spinner_item) holder.valueSP.getAdapter().getItem(holder.valueSP.getAdapter().getCount()-1)).getName()))))
                                             {
                                               addadapterToSpinner(holder.valueSP, spinner_position, lista);

                                            }

                                if (holder.valueSP.getAdapter() != null && holder.valueSP.getAdapter().getCount() >=0 )
                                    holder.valueSP.setSelection(spinner_position);
                            }
                        }



            }


           // if (holder.valueSP.getAdapter().getCount() > 0)
            //LOOK UP PINAKAS DOWNLOAD
             if (result.get(position).getLookup_query() != null  && lista.size() == 0) {

                lookup_query = result.get(position).getLookup_query();


                AsyncTaskGetJSON2 task = new AsyncTaskGetJSON2();
                task.query = "select ID, Name from " +  lookup_query ;
                task.ctx = act;
                 final int[] final_spinner_position = {0};
                 final String finalValueFromBase = valueFromBase;
                 task.listener = new AsyncCompleteTask2() {
                    @Override
                    public void taskComplete2(JSONArray results) throws JSONException {
                        if (results != null && !results.getJSONObject(0).has("status")){
                           // ArrayList <Spinner_item> spinnerLista = new ArrayList();
                            Spinner_item item  = new Spinner_item();
                            //Η πρωτη επιλογή να ειναι κενή

                            item.setId(0);
                            item.setName("");
                            lista.clear();
                            lista.add(item);

                            //boolean hasFoundSameValue = false;
                            String valueForOldLista = "";
                            String valueID = result.get(position).getValue();
                            int final_spinner_position = 0;

                            if (valueID == null || valueID.equals(""))
                                valueID = "0";

                            for (int i=0; i<results.length(); i ++) {
                                JSONObject jsonObject = results.getJSONObject(i);
                                int id = jsonObject.getInt("ID");
                                String name = jsonObject.getString("Name");
                                item = new Spinner_item();
                                item.setId(id);
                                item.setName(name);
                                lista.add(item);


                                if (valueID.equals(String.valueOf(id))){
                                    final_spinner_position = i + 1; //εδω του οριζουμε την θεση που θα εμφανισει το σπιννερ i = θεση λιστας το +1 επειδη εχει προηγηθει το κενο item
                                }


                                if (oldValuesLista != null)
                                    if (oldValuesLista.get(position).equals(String.valueOf(id)))
                                        valueForOldLista = name;

                            }



                            if (oldValuesLista != null)
                                holder.oldValueTV.setText(valueForOldLista);

                            result.get(position).setLista_default((ArrayList) lista.clone());
                            addadapterToSpinner(holder.valueSP, final_spinner_position, lista);
                        }
                    }
                };
                task.execute();
            }

            }
            if (lista != null && lista.size() > 0 &&  holder.valueSP.getAdapter() == null) // to adapter == null mpike gia tis statheres(dokimastika)
                 addadapterToSpinner(holder.valueSP, spinner_position, lista);

        }


        //  CHECKBOX
        else if (type == CHECKBOX_ITEM || type == CHECKBOX_TYPE_READ_ONLY_VALUE) {

            if(type == CHECKBOX_TYPE_READ_ONLY_VALUE)
                holder.valueCH.setClickable(false);

            holder.valueCH.setTag(position);
            holder.valueCH.setText(title);
            holder.valueCH.setChecked(result.get(position).getTrue());

//   ΓΙΑ ΤΗΝ ΤΕΛΕΥΤΑΙΑ ΠΑΛΙΑ ΤΙΜΗ

            if (oldValuesLista !=null)
                if (position < oldValuesLista.size()) {
                    holder.oldValueCH.setText(title);
                    holder.oldValueCH.setClickable(false);
                    boolean trueOrFalse = oldValuesLista.get(position).equals("1");
                    holder.oldValueCH.setChecked(trueOrFalse);
                }

        }

        else if (type == MULTI_TYPE){
            holder.titleTV.setText(title);
            holder.valueTV.setTag(position);

            if(result.get(position).getLista() != null) {
                ArrayList l = result.get(position).getLista();
                ArrayList<Spinner_item> multiList = l;

                if (value != null && !value.isEmpty()) {
                    String[] v_ids = value.split("\ufffd");
                    if (v_ids.length > 0) {
                        StringJoiner s = new StringJoiner(" , ");
                        for (int i = 0; i < v_ids.length; i++)
                            for (int x = 0; x < multiList.size(); x++) {
                                if (Integer.parseInt(v_ids[i]) == multiList.get(x).id) {
                                    s.add(multiList.get(x).name);
                                    break;
                                }
                            }

                        holder.valueTV.setText(s.toString());
                    }
                }
            }
           // value = value.replace("\ufffd",",");
        }


        if (desplayImageID != 0){
            holder.titleTV.setTypeface(null, Typeface.BOLD);
            holder.titleTV.setOnClickListener(view -> Utils.showImageDialog(act,desplayImageID));

        }


    }

    public void specifyEdittext(EditText valueET, String title, int edittextType,int min, int max) {



        if (edittextType == StaticFields.KEIMENO)
            valueET.setInputType(InputType.TYPE_CLASS_TEXT  | InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        else if (edittextType == AKERAIOS){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER );
        }

        else if(edittextType == DEKADIKOS){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL );
        }
        else if(edittextType == EUROS_TIMON){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER );
            if ( max!= 0)
            valueET.setFilters(new InputFilter[]{new InputFilterMinMax(min, max)});

        }
        else if(edittextType == 5)
              valueET.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME );

        else if (edittextType == AKERAIOS_WITH_NEGATIVE){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        }

        else if(edittextType == DEKADIKOS_WITH_NEGATIVE){
            valueET.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
        }







    }

    @Override
    public int getItemCount() {

        return result.size();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void filterList(ArrayList<ItemsRV> filteredList) {

        result = filteredList;
        notifyDataSetChanged();
    }



    private void setStrokeColor(View v, int width, int color){
        GradientDrawable drawable = (GradientDrawable)v.getBackground();
        drawable.setStroke(width,color); // set stroke width and stroke color

    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView oldValueTV;
        public TextView valueTV;
        public EditText valueET;
        public EditText oldTitleTV;
        public TextView titleTV;
        public CheckBox oldValueCH,valueCH;
        public Spinner valueSP;
        public DataSended dataSendedListener;


        public MyViewHolder(final View itemView, int viewType, DataSended dataSendedListener) {
            super(itemView);

            this.dataSendedListener = dataSendedListener;
            // INITIALIZE KAI LISTENERS ANALOGA TO PEDIO

            // ΓΙΑ ΤΗΝ ΠΑΛΙΑ ΤΙΜΗ ΣΕ TEXTVIEW
            if (isIncludingOldValues) {
                oldValueTV = itemView.findViewById(R.id.oldDocTV);

                if (viewType == EDITTEXT_ITEM) {
                    valueET =  itemView.findViewById(R.id.valueET);
                    oldValueTV.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int pos = (Integer) valueET.getTag();
                            String col = result.get(pos).getCol_name();
                            if (col.equals("paratiriseis") || col.equals("paremvaseis"))
                                 Dialogs.showText(act, result.get(pos).getTitleID(), oldValueTV.getText().toString());
                        }
                    });




                }
            }


            if (viewType == TEXTVIEW_ITEM ) {
                valueTV =  itemView.findViewById(R.id.valueTV);
                setDateListener(valueTV);
            }

            if (viewType == TEXTVIEW_CLOCK_TYPE){
                titleTV =  itemView.findViewById(R.id.titleTV);
                valueTV =  itemView.findViewById(R.id.valueTV);

                setClockListener(valueTV);
            }

            else if (viewType == TEXTVIEW_DATETIME_TYPE){
                valueTV =  itemView.findViewById(R.id.valueTV);

                final String[] dt = {""};
                final Calendar myCalendar = Calendar.getInstance();
                final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        valueTV.setText(sdf.format(myCalendar.getTime()));
                        int position = (int) valueTV.getTag();
                        result.get(position).setValue(Utils.convertDateTomilliseconds(valueTV.getText().toString()));

                    }

                };


                valueTV.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        // TODO Auto-generated method stub
                        DatePickerDialog datepicker = new DatePickerDialog(act, date12, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH));


                        datepicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                //dt[0] = valueTV.getText().toString();
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                myCalendar.set(Calendar.YEAR, year);
                                myCalendar.set(Calendar.MONTH, monthOfYear);
                                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                String myFormat = "dd-MM-yyyy";
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                                dt[0] = sdf.format(myCalendar.getTime());

                                //tv.setText(sdf.format(myCalendar.getTime()));

                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(act, new TimePickerDialog.OnTimeSetListener() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                                        valueTV.setText( dt[0] + " " + (selectedHour < 10 ? "0" + selectedHour : selectedHour) + ":"
                                                + (selectedMinute < 10 ? "0" + selectedMinute : selectedMinute));
                                        int position = (int)valueTV.getTag();
                                        result.get(position).setValue(Utils.convertDateTomilliseconds(valueTV.getText().toString()));
                                    }
                                }, hour, minute, true);//Yes 24 hour time
                                mTimePicker.show();
                            }
                        });

                        datepicker.show();





                    }
                });

            }





            if (viewType == TEXTVIEW_WITH_ALERT_DIALOG_SPINNER){
                titleTV =  itemView.findViewById(R.id.titleTV);
                valueTV =  itemView.findViewById(R.id.valueTV);

                valueTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = (int) valueTV.getTag();
                        final String [] str_array = result.get(position).getListaArray();
                        AlertDialog.Builder b = new AlertDialog.Builder(act);
                        b.setTitle("Επιλογές");

                        b.setItems(result.get(position).getListaArray(), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                valueTV.setText(str_array[which]);
                                int position = (int)valueTV.getTag();
                                result.get(position).setValue(valueTV.getText().toString());

                            }
                        });
                        b.show();
                    }
                });


            }


            if (viewType == TEXTVIEW_ITEM_READ_ONLY_VALUE )
                valueTV =  itemView.findViewById(R.id.valueTV);



            if (viewType == TITLE_ITEM) {
                titleTV =  itemView.findViewById(R.id.titleTV);
                valueTV =  itemView.findViewById(R.id.valueTV);

            }

            if (viewType == EDITTEXT_ITEM) {
                valueET =  itemView.findViewById(R.id.valueET);

                if (editextsUsingDialogs) {
                 //   valueET.seten(false);
                    valueET.setInputType(0);
                    valueET.setFocusable(false);
                    valueET.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int pos = (int) valueET.getTag();
                            Dialogs.setDialogInputText(act, valueET, result.get(pos), result, pos);
                        }
                    });

                }
                else{
                    valueET.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                            int position = (int)valueET.getTag();
                            result.get(position).setValue(valueET.getText().toString());

                        }
                    });
                }







            } else if (viewType == CHECKBOX_ITEM || viewType == CHECKBOX_TYPE_READ_ONLY_VALUE) {

                if (isIncludingOldValues)
                    oldValueCH = itemView.findViewById(R.id.oldValueCH);

                valueCH =  itemView.findViewById(R.id.valueCH);
                if (viewType == CHECKBOX_TYPE_READ_ONLY_VALUE) {
                    valueCH.setClickable(false);
                }

                valueCH.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = (int)valueCH.getTag();
                        result.get(position).setTrue(valueCH.isChecked());

                     //   Toast.makeText(act, result.get(position).getTrue().toString() , Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(act, String.valueOf(valueCH.isChecked())   , Toast.LENGTH_SHORT).show();

                    }
                });

            }


            else if (viewType == SPINNER_ITEM){
                valueSP = itemView.findViewById(R.id.valueSP);

                valueSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        int pos = (int)valueSP.getTag();

                        String value = "";

                        Map<Integer, Integer> map = result.get(pos).getMap();
                        if ( map != null){
                            int spinner_pos = valueSP.getSelectedItemPosition();

                            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                                if (entry.getValue().equals(spinner_pos)) {
                                    value = String.valueOf(entry.getKey());
                                    break;
                                }
                            }

                        }

                        else
                        {
                            if (result.get(pos).getLista() != null)
                              value = String.valueOf(result.get(pos).getLista().indexOf(valueSP.getSelectedItem()));
                            else
                                value = valueSP.getSelectedItem().toString();

                        }

                     //   Toast.makeText(act, value, Toast.LENGTH_SHORT).show();

                        result.get(pos).setValue(value);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }


            else if (viewType == SPINNER_ITEM_NEW) {
                valueSP = itemView.findViewById(R.id.valueSP);

                valueSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int positionOrID, long id) {
                        int pos = (int)valueSP.getTag();
                        Spinner_item item = (Spinner_item) valueSP.getSelectedItem();
//                        String value = result.get(pos).getValue();
//                        if (value.equals("0"))
//                            value = String.valueOf(item.getId());
                        String value = String.valueOf(item.getId());



                        result.get(pos).setValue(value);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }



            else if (viewType == MULTI_TYPE){
                valueTV =  itemView.findViewById(R.id.valueTV);
                valueTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = (int )valueTV.getTag();
                        ArrayList ar  =  result.get(pos).getLista();
                        ArrayList<Spinner_item> itemsLista  =  ar;
                        String val = result.get(pos).getValue();
                        String [] v_ids;
                        if (val == null || val.isEmpty())
                            v_ids = new String[0];
                        else
                            v_ids = val.replace("\ufffd",",").split(",");
                        AlertDialog.Builder builder =  Dialogs.getAlertMultipleChoice(act, itemsLista, v_ids);

                        builder.setTitle(R.string.choose_item);
                        builder.setPositiveButton("OK", (dialog, which) -> {


                            StringJoiner s_ids = new StringJoiner(",");
                            StringJoiner s_names = new StringJoiner(",");
                                    for (int i=0; i<itemsLista.size();i++){
                                        Spinner_item x = itemsLista.get(i);
                                        if (x.isChecked()) {
                                            s_ids.add(String.valueOf(x.id));
                                            s_names.add(x.name);
                                        }
                                    }
                                    valueTV.setText(s_names.toString());
                                    result.get(pos).setValue(s_ids.toString().replace(",","\ufffd"));

                                });

                        builder.setNegativeButton("Ακύρωση", (dialog, which) -> {});


                        AlertDialog dialog = builder.create();
                        dialog.show();



                    }
                });

            }


                titleTV =  itemView.findViewById(R.id.titleTV);

         //   itemView.setOnClickListener(this);


            if (custID == Customers.CUSTID_MEDITERRANEO && act instanceof MainActivity_Aim){
                if (oldValueTV != null) {oldValueTV.setHeight(15);}
                if ( valueET != null) {valueET.setHeight(15);valueET.setTextSize(12);}

            }


        }

        @Override
        public void onClick(View v) {

            dataSendedListener.hereIsYourData(getAdapterPosition());

        }
    }




    private void setDateListener(TextView tv){
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date12 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                tv.setText(sdf.format(myCalendar.getTime()));
                int position = (int) tv.getTag();
                result.get(position).setValue(Utils.convertDateTomilliseconds(tv.getText().toString()));

            }

        };
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(act, date12, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void setClockListener(TextView tv){
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(act, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        tv.setText((selectedHour < 10 ? "0" + selectedHour : selectedHour) + ":"
                                + (selectedMinute < 10 ? "0" + selectedMinute : selectedMinute));
                        int position = (int)tv.getTag();
                        result.get(position).setValue(String.valueOf(Utils.convertTime(tv.getText().toString())));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.show();

            }
        });

    }





    public void addadapterToSpinner( Spinner valueSP, int spinner_posORvalue, ArrayList lista ) {


        ArrayAdapter adapter;

        if (lista.get(0) instanceof String)
            adapter = new ArrayAdapter<String>(act, R.layout.spinner_layout2, lista);
        else
            adapter = new Spinner_new_Image_Adapter(act, R.layout.spinner_layout2, (Spinner_item[]) lista.toArray(new Spinner_item[lista.size()]));


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        valueSP.setAdapter(adapter);

        //int realPos  =  adapter.getPosition(new Spinner_item(spinner_posORvalue, null));
        valueSP.setSelection(spinner_posORvalue);


        //this.lista.clear();



    }








}


