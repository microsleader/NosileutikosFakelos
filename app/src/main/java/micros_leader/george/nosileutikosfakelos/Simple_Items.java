package micros_leader.george.nosileutikosfakelos;

public class Simple_Items {

    public int id, transgroupID , itemID;
    public String title, datein = "" ,dateout = "", value = "" , userID = "";
    public boolean hasDateoutFromServer;

    public Simple_Items(int itemID, String title) {
        this.itemID = itemID;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
