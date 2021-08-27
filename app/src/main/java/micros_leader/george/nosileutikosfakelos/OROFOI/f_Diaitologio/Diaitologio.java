package micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio;

public class Diaitologio {

    public String id,userID,transgroupid,datefrom ,hourFrom,dieta, sxolia, sitisiSinodou;
    private String name ,periektikotita, xrisi_odigies,category;
    boolean isSelected = false;


    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getHourFrom() {
        return hourFrom;
    }

    public void setHourFrom(String hourFrom) {
        this.hourFrom = hourFrom;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public String getSxolia() {
        return sxolia;
    }

    public void setSxolia(String sxolia) {
        this.sxolia = sxolia;
    }

    public String getSitisiSinodou() {
        return sitisiSinodou;
    }

    public void setSitisiSinodou(String sitisiSinodou) {
        this.sitisiSinodou = sitisiSinodou;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransgroupid() {
        return transgroupid;
    }

    public void setTransgroupid(String transgroupid) {
        this.transgroupid = transgroupid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriektikotita() {
        return periektikotita;
    }

    public void setPeriektikotita(String periektikotita) {
        this.periektikotita = periektikotita;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getXrisi_odigies() {
        return xrisi_odigies;
    }

    public void setXrisi_odigies(String xrisi_odigies) {
        this.xrisi_odigies = xrisi_odigies;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
