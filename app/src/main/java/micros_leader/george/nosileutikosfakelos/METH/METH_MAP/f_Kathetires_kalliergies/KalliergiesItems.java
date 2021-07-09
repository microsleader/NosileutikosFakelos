package micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies;

public class KalliergiesItems {

    public String digma, apotelesma,itemname, eidos,date, dateSent ,mikrovio;
    public int id, itemID,nurseID;

    public KalliergiesItems(){

    }


    public KalliergiesItems(int id, String digma, String apotelesma, int nurseID,String dateSent){
            this.id = id;
            this.digma = digma;
            this.apotelesma = apotelesma;
            this.nurseID = nurseID;
            this.dateSent = dateSent;
    }


    public KalliergiesItems(int id, int itemID,String itemname, String date,String dateSent ,String mikrovio , int nurseID){
        this.id = id;
        this.itemID = itemID;
        this.itemname = itemname;
        this.date = date;
        this.dateSent = dateSent;
        this.mikrovio = mikrovio;
        this.nurseID = nurseID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDigma() {
        return digma;
    }

    public void setDigma(String digma) {
        this.digma = digma;
    }

    public String getApotelesma() {
        return apotelesma;
    }

    public void setApotelesma(String apotelesma) {
        this.apotelesma = apotelesma;
    }

    public String getEidos() {
        return eidos;
    }

    public void setEidos(String eidos) {
        this.eidos = eidos;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }

    public String getMikrovio() {
        return mikrovio;
    }

    public void setMikrovio(String mikrovio) {
        this.mikrovio = mikrovio;
    }

    public int getNurseID() {
        return nurseID;
    }

    public void setNurseID(int nurseID) {
        this.nurseID = nurseID;
    }
}
