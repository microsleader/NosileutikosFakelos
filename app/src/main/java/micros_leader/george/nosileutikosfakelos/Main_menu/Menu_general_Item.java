package micros_leader.george.nosileutikosfakelos.Main_menu;

import android.app.Activity;

public class Menu_general_Item {

    public String title;
    public int imageID;
    public int backgroundColor;
    public Activity activity;

    public Menu_general_Item(String title, int imageID, int backgroundColor , Activity activity) {
        this.title = title;
        this.imageID = imageID;
        this.backgroundColor = backgroundColor;
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
