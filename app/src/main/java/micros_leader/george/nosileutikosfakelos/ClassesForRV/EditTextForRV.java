package micros_leader.george.nosileutikosfakelos.ClassesForRV;


public class EditTextForRV {

    public String title, value;
    public int type;
    // type1 = eleuthero keimeno
    // type2 = akeraioi
    // type3 = dekadikoi
    // type4 = na dexetai ena sigekrimeno euros timos opos apo to 1-10

    public EditTextForRV(String title, String value, int type) {
        this.title = title;
        this.value = value;

        if (type < 1 || type > 4)
        this.type = 1;
        else
            this.type = type;

    }

    public EditTextForRV() {

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if (type < 1 || type > 4)
            this.type = 1;
        else
            this.type = type;
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
