package micros_leader.george.nosileutikosfakelos;

public class Simple_Items {


    public int  transgroupID , itemID , typecol1;
    public String title, datein = "" ,datechange = "" ,dateout = "", value = "" , userID = "" , col1 ,col2,col3,col4 ,col5,col6;
    public String valSP1 , valSP2, valSP3 , valET1, valET2, valET3;
    public boolean hasDateoutFromServer , isFromHome;
    public long id ;




    public Simple_Items(int itemID, String title) {
        this.itemID = itemID;
        this.title = title;
    }



    public Simple_Items(int itemID, String title ,String col1,String col2,String col3) {
        this.itemID = itemID;
        this.title = title;
        this.col1 = col1;
        this.col2 = col3;
        this.col2 = col3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTransgroupID() {
        return transgroupID;
    }

    public void setTransgroupID(int transgroupID) {
        this.transgroupID = transgroupID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getDatein() {
        return datein;
    }

    public void setDatein(String datein) {
        this.datein = datein;
    }

    public String getDatechange() {
        return datechange;
    }

    public void setDatechange(String datechange) {
        this.datechange = datechange;
    }

    public String getDateout() {
        return dateout;
    }

    public void setDateout(String dateout) {
        this.dateout = dateout;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }








}
