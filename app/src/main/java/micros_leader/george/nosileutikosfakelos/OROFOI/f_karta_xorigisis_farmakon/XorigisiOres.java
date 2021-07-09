package micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon;

import java.io.Serializable;

public class  XorigisiOres  implements Serializable {

    public String id, xorigisiID,hour;
    public boolean xorigithike;

    public XorigisiOres(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXorigisiID() {
        return xorigisiID;
    }

    public void setXorigisiID(String xorigisiID) {
        this.xorigisiID = xorigisiID;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean isXorigithike() {
        return xorigithike;
    }

    public void setXorigithike(boolean xorigithike) {
        this.xorigithike = xorigithike;
    }
}
