package micros_leader.george.nosileutikosfakelos.ClassesForRV;


public class SpinnersForRV {

    public SpinnersForRV(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public SpinnersForRV() {

    }


    public String title, value;

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


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals( Object obj) {
        return super.equals(obj);
    }
}
