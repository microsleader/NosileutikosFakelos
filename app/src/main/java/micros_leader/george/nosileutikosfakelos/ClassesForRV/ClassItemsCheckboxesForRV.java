package micros_leader.george.nosileutikosfakelos.ClassesForRV;

public class ClassItemsCheckboxesForRV {

    public String  titleID;
    public boolean isTrue;

    public ClassItemsCheckboxesForRV(String titleID, boolean isTrue) {
        this.titleID = titleID;
        this.isTrue = isTrue;
    }

    public String getTitleID() {
        return titleID;
    }

    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
