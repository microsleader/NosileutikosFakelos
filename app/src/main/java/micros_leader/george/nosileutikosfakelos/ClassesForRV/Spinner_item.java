package micros_leader.george.nosileutikosfakelos.ClassesForRV;

import java.io.Serializable;

public class Spinner_item implements Serializable {

    public String name, imagePath;
    public int id;
    public boolean checked;



    public Spinner_item(){

    }

    public Spinner_item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Spinner_item(int id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public Spinner_item(int id, String name, boolean checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImageTitle(String imageTitle) {
        this.imagePath = imageTitle;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
