package micros_leader.george.nosileutikosfakelos;

public class Customers {

    public static final int CUSTID_ATG = 100; // ΠΑΠΟΥΛΙΔΗΣ
    public static final int CUSTID_MEDITERRANEO = 101;
    public static final int CUSTID_ATHENSEYE = 102;
    public static final int CUSTID_ZILAKOS = 103;
    public static final int CUSTID_AGIOS_PANTELEIMONAS = 104;
    public static final int CUSTID_MEDISALUS = 105;
    // INSTALLATION ID
    public static final int CUSTID_NEPHROXENIA = 1106;
    public static final int CUSTID_EYGENIDEIO = 1109;
    public static final int CUSTID_SEA_MEDICAL = 1117;

    public static final int CUSTID_FRONTIS = 1119;
    public static final int CUSTID_FRONTIS_2 = 1120;


    public static boolean isFrontis(int custID){
        return custID == CUSTID_FRONTIS || custID == CUSTID_FRONTIS_2;
    }
}
