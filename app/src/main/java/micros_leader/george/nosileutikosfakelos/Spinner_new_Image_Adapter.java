package micros_leader.george.nosileutikosfakelos;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.Nullable;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;

public class Spinner_new_Image_Adapter extends ArrayAdapter<Spinner_item>{

    // Your sent context
    private Context context;
    // Your custom values for the spinner (Spinner_item)
    private Spinner_item[] values;

    public Spinner_new_Image_Adapter(Context context, int textViewResourceId,
                                     Spinner_item[] values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }



    @Override
    public int getCount(){
        return values.length;
    }

    @Override
    public Spinner_item getItem(int position){
        if (values == null)
            return new Spinner_item();
        if (position >= values.length)
            return values[0];

        return values[position];
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
//        TextView label = (TextView) super.getView(position, convertView, parent);
//        label.setTextColor(Color.BLACK);
//        // Then you can get the current item using the values array (Users array) and the current position
//        // You can NOW reference each method you has created in your bean object (User class)
//        label.setText(values[position].getName());
//
//        // And finally return your dynamic (or custom) view for each spinner item
//        return label;


        return getCustomView(position, convertView, parent,true);
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
//        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
//        label.setTextColor(Color.BLACK);
//        label.setText(values[position].getName());
//
//
//        if (values[position].getImageTitle() != null) {
//            ImageView imageView = (ImageView) super.getDropDownView(position, convertView, parent);
//            try {
//                // get input stream
//                InputStream ims = context.getAssets().open(values[position].getImageTitle());
//                // load image as Drawable
//                Drawable d = Drawable.createFromStream(ims, null);
//                // set image to ImageView
//                imageView.setImageDrawable(d);
//            } catch (IOException ex) {
//
//            }
//
//        }
//        return label;

        return getCustomView(position, convertView, parent,false);
    }


    public View getCustomView(int position, View convertView, ViewGroup parent , boolean isFirstPos) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row ;
//        if (isFirstPos)
//            row = inflater.inflate(R.layout.spinner_layout2, parent, false);
//        else
            row = inflater.inflate(R.layout.spinner_text_image_layout, parent, false);






        TextView textView = (TextView) row.findViewById(R.id.titleTV);
        if (position < values.length)
             textView.setText(values[position].getName());
        if (isFirstPos)
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.drop_down2, 0);


        if (position < values.length &&  values[position].getImagePath() != null) {
              ImageView imageView = row.findViewById(R.id.iconIV);
            try {
                // get input stream
                InputStream ims = context.getAssets().open(values[position].getImagePath());
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                imageView.setImageDrawable(d);
            } catch (IOException ex) {

                ex.printStackTrace();
            }

        }

        return row;
    }

    @Override
    public int getPosition(@Nullable Spinner_item item) {

        int itemID = item.getId();
        String text = item.getName();

        if (itemID != -1) {
            for (int i = 0; i < values.length; i++) {
                Spinner_item t = values[i];
                if (t.getId() == itemID)
                    return super.getPosition(t);
            }
        }

        if (text != null)
            for (int i = 0; i < values.length; i++) {
                Spinner_item t = values[i];
                if (t.getName().equals(text))
                    return super.getPosition(t);
            }


        return super.getPosition(item);
    }
}