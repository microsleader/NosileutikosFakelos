package micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon;

import android.os.Parcel;
import android.os.Parcelable;

public class Medicines implements Parcelable {

    public String id,TransGroupID,dateStart,dateStop,itemID,category,ml_hour,
            dosi,odos_xorigisis,dosologia,ores_xorigisis,itemname,isXorigithike,isDiakopike;
    public int int_itemID;

    public Medicines() {
    }
    public Medicines(String itemID, String itemname) {
        this.itemID =  itemID;
        this.itemname = itemname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransGroupID() {
        return TransGroupID;
    }

    public void setTransGroupID(String transGroupID) {
        TransGroupID = transGroupID;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateStop() {
        return dateStop;
    }

    public void setDateStop(String dateStop) {
        this.dateStop = dateStop;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMl_hour() {
        return ml_hour;
    }

    public void setMl_hour(String ml_hour) {
        this.ml_hour = ml_hour;
    }

    public String getDosi() {
        return dosi;
    }

    public void setDosi(String dosi) {
        this.dosi = dosi;
    }

    public String getOdos_xorigisis() {
        return odos_xorigisis;
    }

    public void setOdos_xorigisis(String odos_xorigisis) {
        this.odos_xorigisis = odos_xorigisis;
    }

    public String getDosologia() {
        return dosologia;
    }

    public void setDosologia(String dosologia) {
        this.dosologia = dosologia;
    }

    public String getOres_xorigisis() {
        return ores_xorigisis;
    }

    public void setOres_xorigisis(String ores_xorigisis) {
        this.ores_xorigisis = ores_xorigisis;
    }

    public String getItemname() {
        return itemname;
    }

    public String getIsDiakopike() {
        return isDiakopike;
    }

    public void setIsDiakopike(String isDiakopike) {
        this.isDiakopike = isDiakopike;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String isXorigithike() {
        return isXorigithike;
    }

    public void setXorigithike(String xorigithike) {
        isXorigithike = xorigithike;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    public static final Parcelable.Creator<Medicines> CREATOR = new ClassLoaderCreator<Medicines>() {
        @Override
        public Medicines createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Medicines[] newArray(int size) {
            return new Medicines[0];
        }

        @Override
        public Medicines createFromParcel(Parcel source, ClassLoader loader) {
            return null;
        }
    };

   }
