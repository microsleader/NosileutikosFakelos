package micros_leader.george.nosileutikosfakelos.DiffUtil;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;

public class ItemsRv_Callback extends DiffUtil.Callback {

    private   ArrayList  oldLista = new ArrayList<>();
    private   ArrayList   newLista  = new ArrayList<>();

    private final  int  typeOfitems = 0;
    public static int ITEMS_RV_ITEMS = 1;

    public static int STRING_ITEMS = 2;
//    private final ArrayList oldListaStr;
//    private final  ArrayList newListaStr;

    public ItemsRv_Callback(ArrayList oldList, ArrayList newList) {
        this.oldLista = oldList;
        this.newLista = newList;


    }






    @Override
    public int getOldListSize() {
        return oldLista.size();
    }

    @Override
    public int getNewListSize() {
        return newLista.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//        return oldLista.get(oldItemPosition) == newLista.get(
//                newItemPosition);

       // return oldItemPosition == newItemPosition;

        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
       // Log.e("theseis" , String.valueOf(oldItemPosition) + " , " +  String.valueOf(newItemPosition));
        int result = 0;
        if (newLista.get(newItemPosition) instanceof ItemsRV && oldLista.get(oldItemPosition )instanceof ItemsRV) {
             result = ((ItemsRV) newLista.get(newItemPosition)).compareTo(oldLista.get(oldItemPosition));
        }
        else if ( newLista.get(newItemPosition).equals(oldLista.get(oldItemPosition)))
            result = 1;




        if (result == 1){
            return true;
        }
        return false;
    }



    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        Bundle diff = new Bundle();

        if (newLista.get(newItemPosition) instanceof ItemsRV && oldLista.get(oldItemPosition )instanceof ItemsRV) {

            ItemsRV newItem = (ItemsRV) newLista.get(newItemPosition);
            ItemsRV oldItem = (ItemsRV) oldLista.get(oldItemPosition);


            if (newItem.getValue() != null && oldItem.getValue() != null) {
                if (!newItem.getValue().equals(oldItem.getValue())) {
                    diff.putString("value", newItem.getValue());
                }
            }

            else if (newItem.getValue() != null && oldItem.getValue() == null)
                diff.putString("value", newItem.getValue());
            else
                diff.putString("value", "");

        }

        else if (newLista.get(newItemPosition).equals(oldLista.get(oldItemPosition)))
                    diff.putString("value", (String) newLista.get(newItemPosition));




        if (diff.size()==0){
            return null;
        }


        return diff;
    }

}
