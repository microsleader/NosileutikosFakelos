package micros_leader.george.nosileutikosfakelos.ClassesForRV;



public class CheckBoxesForRV {

    public String title;
    public boolean isChecked;


    public CheckBoxesForRV(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }

    public CheckBoxesForRV() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals( Object obj) {
        return super.equals(obj);
    }
}
