package micros_leader.george.nosileutikosfakelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.BasicList;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.CheckBoxesForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsCheckboxesForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.EditTextForRV;
import micros_leader.george.nosileutikosfakelos.EKSOTERIKA.Enimerotiko_simeioma_eksetasis_activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.Ektimisi_kind_ptoseos.Ektimisi_kind_ptoseos_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Isozigio_Meth.Isozigio_Meth_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.Kathetires_paroxeteuseis_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Med_instr_transfer_cardio.Med_instr_tr_cardio_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Nosil_Elegxos.Nosil_elegxos_Meth;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Nursing_katagrafi_efarmogis_metron_elegxou_loimokseon.Katagrafi_efarmogis_metron_elegxou_loimokseon_Act;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia.Zotika_Activity_Meth;
import micros_leader.george.nosileutikosfakelos.Main_menu.Menu_general_Item;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Aksiologiseis.NeurikiAksiologisi3Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Diagnoseis_Istoriko.Diagnoseis_Istoriko_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Diaitologio.DiaitologioActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Eksitirio.EksitirioActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_EktimisiEpigonton.EktimisiEpigontonActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Isozigio_pros_apov.Isozigio_p_a_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Kathimerino_zigisma.Kathimerino_Zigisma_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko.Nosil_IstorikoActivity_NEW;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Snel.SnelActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Zotika_simeia.Zotika_simeia_Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_karta_xorigisis_farmakon.FarmakaListActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_programmatismos_p_a.Programmatismos_p_a_Activity;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko.Nosil_IstorikoList;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.SpinnersForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.TextViewSForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.TitlesForRV;

import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.CHECKBOX_TYPE;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.EDIT_TEXT_TYPE;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.SPINNER_TYPE;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.SPINNER_TYPE_NEW;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.TEXTVIEW_TYPE;


public class InfoSpecificLists extends StaticFields{


    public static int CHECKBOX_ITEM = 4;


    // textview type
    public static int MEDICINES = 3; //eleuthero keimeno


    // text_type gia edittext
    public static int KEIMENO = 1; //eleuthero keimeno
    public static int AKERAIOS = 2; //akeraioi
    public static int DEKADIKOS = 3; //dekadikoi
    public static int EUROS_TIMON = 4; //na dexetai ena sigekrimeno euros timos opos apo to 1-10
    public static int HOUR = 5;
    public static int AKERAIOS_WITH_NEGATIVE = 7;
    public static int DEKADIKOS_WITH_NEGATIVE = 8;

    //ΓΙΑ ΤΟ ΕΥΡΟΣ ΠΡΕΠΕΙ ΝΑ ΒΑΛΟΥΜΕ ΚΑΙ ΤΙΜΕΣ ΣΤΟ ΑΝΤΙΚΕΙΜΕΝΟ setMin(); , setMax();

    public static ArrayList<String> getKatastasiList() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("Καθαρό");
        list.add("Μέτρια καθαρό");
        list.add("Πηγμένο");

        return list;
    }

    public static String getKatastasiID(String name) {

        String id;

        Map<String, Integer> map = new HashMap<>();

        map.put("", 0);
        map.put("Καθαρό", 1);
        map.put("Μέτρια καθαρό", 2);
        map.put("Πηγμένο", 3);

        id = map.get(name).toString();

        return id;
    }

        public static ArrayList<Menu_general_Item> getSigxoneusiFilladiwn(){
            ArrayList<Menu_general_Item> lista  = new ArrayList<>();

            lista.add(new Menu_general_Item("Ζωτικά σημεία / Ώρα", R.drawable.search , R.color.mpornto, new Zotika_Activity_Meth()));
            lista.add(new Menu_general_Item("Καθημερινό ζύγισμα\nασθενούς", R.drawable.search , R.color.grey, new Kathimerino_Zigisma_Activity()));
            lista.add(new Menu_general_Item("Ισοζύγιο ΜΕΘ", R.drawable.search , R.color.dark_blue, new Isozigio_Meth_Activity()));
            lista.add(new Menu_general_Item("3ωρη νευρολογική\nαξιολόγηση", R.drawable.search , R.color.mov, new NeurikiAksiologisi3Activity()));
            lista.add(new Menu_general_Item("Καθετήρες - Καλλιεργιες", R.drawable.search , R.color.grey, new Kathetires_paroxeteuseis_Activity()));



            return lista;

        }


        public static ArrayList<Menu_general_Item> getFloorsMenu(){
        ArrayList<Menu_general_Item> lista  = new ArrayList<>();

        lista.add(new Menu_general_Item("Ισοζύγιο προσ/αποβ", R.drawable.search ,  R.color.ladi, new Isozigio_p_a_Activity()));
        lista.add(new Menu_general_Item("Νοσηλευτικός - Αιμοκαθάρσεις", R.drawable.search , R.color.mpornto,
                new micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim()));
        lista.add(new Menu_general_Item("3ωρη νευρολογική αξιολόγηση", R.drawable.search , R.color.mov, new NeurikiAksiologisi3Activity()));
        lista.add(new Menu_general_Item("Διαγνώσεις - Ιστορικό", R.drawable.search , R.color.grey, new Diagnoseis_Istoriko_Activity()));
        lista.add(new Menu_general_Item("Διαιτολόγιο", R.drawable.search , R.color.dark_blue, new DiaitologioActivity()));
        lista.add(new Menu_general_Item("Εκτίμηση επειγόντων", R.drawable.search , R.color.yellow, new EktimisiEpigontonActivity()));
        lista.add(new Menu_general_Item("Εξιτήριο", R.drawable.search , R.color.green, new EksitirioActivity()));
        lista.add(new Menu_general_Item("Νοσηλευτική αναφορά", R.drawable.search , R.color.ladi, new Nosil_IstorikoActivity_NEW()));
        lista.add(new Menu_general_Item("ΣΝΕΛ", R.drawable.search , R.color.mpornto, new SnelActivity()));
        lista.add(new Menu_general_Item("Κάρτα χορήγησης φαρμάκων", R.drawable.search , R.color.mov, new FarmakaListActivity()));
        lista.add(new Menu_general_Item("Ζωτικά σημεία", R.drawable.search , R.color.cyan2, new Zotika_simeia_Activity()));
        lista.add(new Menu_general_Item("Καθημερινό ζύγισμα ασθενούς", R.drawable.search , R.color.grey, new Kathimerino_Zigisma_Activity()));
        lista.add(new Menu_general_Item("Προγραμματιμός Π.Α. (Περιποίησης ασθενών)", R.drawable.search , R.color.dark_blue, new Programmatismos_p_a_Activity()));


     //   lista.add(new Menu_general_Item("Test", R.drawable.search , R.color.ladi, new TestBaseAct()));


        //        lista.add(new Menu_general_Item("Νοσηλευτικός - Αιμοκαθάρσεις test 3", R.drawable.search , R.color.ladi,
//                new microsleader.george.nosileutikosfakelos.OROFOI.f_Nosileutikos_aimokatharseis.Nosileutikos_aim_mediteraneo_3.MainActivity_Aim()));

        return lista;
    }


    public static ArrayList<Menu_general_Item> getMethMenu(){
        ArrayList<Menu_general_Item> lista  = new ArrayList<>();

        lista.add(new Menu_general_Item("Νοσηλ. έλεγχος \n Νοσηλ. λογοδοσία", R.drawable.search ,  R.color.ladi, new Nosil_elegxos_Meth()));
        lista.add(new Menu_general_Item("Ζωτικά σημεία / Ώρα", R.drawable.search , R.color.mpornto, new Zotika_Activity_Meth()));
        lista.add(new Menu_general_Item("Νοσηλευτική αναφορά", R.drawable.search , R.color.mov, new Nosil_IstorikoActivity_NEW()));
        lista.add(new Menu_general_Item("Καθετήρες - Καλλιεργιες", R.drawable.search , R.color.grey, new Kathetires_paroxeteuseis_Activity()));
        lista.add(new Menu_general_Item("Ισοζύγιο ΜΕΘ", R.drawable.search , R.color.dark_blue, new Isozigio_Meth_Activity()));
        lista.add(new Menu_general_Item("Ιατρικές οδηγίες \n μεταφορας " +
                "καρδιολογικού / καρδιοχειρουγικού ασθενούς \n απο την ΜΕΘ/ΜΑΦ", R.drawable.search , R.color.cyan2, new Med_instr_tr_cardio_Activity()));
        lista.add(new Menu_general_Item("ΣΝΕΛ", R.drawable.search , R.color.blue, new SnelActivity()));

        lista.add(new Menu_general_Item("Καταγραφή εφαρμογής\nμέτρων " +
                "ελέγχου λοιμόξεων", R.drawable.search , R.color.yellow, new Katagrafi_efarmogis_metron_elegxou_loimokseon_Act()));

       // lista.add(new Menu_general_Item("Ημερ/γιο καταγ. \n διατροφής", R.drawable.search , R.color.ladi, new Imer_katagr_diatrofis_Activity()));
        lista.add(new Menu_general_Item("Εκτίμηση κινδύνου πτώσεως", R.drawable.search , R.color.mpornto, new Ektimisi_kind_ptoseos_Activity()));



        return lista;
    }

    public static ArrayList<Menu_general_Item> getEksoterikaIatreiaMenu(){
        ArrayList<Menu_general_Item> lista  = new ArrayList<>();

        lista.add(new Menu_general_Item("Ενημερωτικό σημείωμα \n ιατρικής εξέτασης", R.drawable.search ,  R.color.cyan2, new Enimerotiko_simeioma_eksetasis_activity()));

        return lista;
    }

    public static String getKatastasiName(String id) {

        if (id == null || id.equals(""))
            return "";

        String name;

        Map<String, String> map = new HashMap<>();

        map.put("0", "");
        map.put("1", "Καθαρό");
        map.put("2", "Μέτρια καθαρό");
        map.put("3", "Πηγμένο");

        name = map.get(id);

        return name;
    }

    public static ArrayList<String> getWatchList_3Hours() {

        ArrayList<String> list = new ArrayList<>();

        list.add("00:00");
        list.add("03:00");
        list.add("06:00");
        list.add("09:00");
        list.add("12:00");
        list.add("15:00");
        list.add("18:00");
        list.add("21:00");

        return list;
    }


    public static String getWatchID(String name) {

        String id;

        Map<String, Integer> map = new HashMap<>();

        map.put("00:00", 1);
        map.put("03:00", 2);
        map.put("06:00", 3);
        map.put("09:00", 4);
        map.put("12:00", 5);
        map.put("15:00", 6);
        map.put("18:00", 7);
        map.put("21:00", 8);

        id = map.get(name).toString();

        return id;
    }


    public static ArrayList<String> getAggProspelasiList() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("AVF");
        list.add("AVF ΑΡ");
        list.add("AVF ΔΕ");
        list.add("AVG");
        list.add("AVG ΑΡ");
        list.add("VG ΔΕ");
        list.add("ΚΑΘΕΤΗΡΑΣ");
        list.add("ΚΕΝΤΙΚΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΜΟΝΙΜΟΣ");
        list.add("ΚΕΝΤΡΙΚΟΣ ΚΑΘΕΤΗΡΑΣ ΔΕ");
        list.add("ΚΕΝΤΡΙΚΟΣ ΚΑΘΕΤΗΡΑΣ ΥΠΟΚΛΕΙΔΙΟΣ ΔΕ");

        list.add("ΜOΣΧΕΥΜΑ");
        list.add("ΜOΣΧΕΥΜΑ ΑΡ");
        list.add("ΜΟΝΙΜΟΣ ΚΑΘΕΤΗΡΑΣ");
        list.add("ΜΟΝΙΜΟΣ ΚΑΘΕΤΗΡΑΣ ΣΦΑΓΙΤΙΚΟΣ ΔΕ");
        list.add("ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ");
        list.add("ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΑΡ");
        list.add("ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΔΕ");
        list.add("ΜΟΣΧΕΥΜΑ ΔΕ");
        list.add("ΠΡΟΣΩΡΙΝΟΣ ΚΑΘΕΤΗΡΑΣ");
        list.add("ΠΡΟΣΩΡΙΝΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΔΕ");

        list.add("ΦΙΣΤΟΥΛΑ");

        return list;
    }

    public static String getAggProspelasiID(String name) {

        String id;

        Map<String, Integer> map = new HashMap<>();

        map.put("", 0);
        map.put("AVF", 1);
        map.put("AVF ΑΡ", 2);
        map.put("AVF ΔΕ", 3);
        map.put("AVG", 4);
        map.put("AVG ΑΡ", 5);
        map.put("VG ΔΕ", 6);
        map.put("ΚΑΘΕΤΗΡΑΣ", 7);
        map.put("ΚΕΝΤΙΚΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΜΟΝΙΜΟΣ", 8);
        map.put("ΚΕΝΤΡΙΚΟΣ ΚΑΘΕΤΗΡΑΣ ΔΕ", 9);
        map.put("ΚΕΝΤΡΙΚΟΣ ΚΑΘΕΤΗΡΑΣ ΥΠΟΚΛΕΙΔΙΟΣ ΔΕ", 10);

        map.put("ΜOΣΧΕΥΜΑ", 11);
        map.put("ΜOΣΧΕΥΜΑ ΑΡ", 12);
        map.put("ΜΟΝΙΜΟΣ ΚΑΘΕΤΗΡΑΣ", 13);
        map.put("ΜΟΝΙΜΟΣ ΚΑΘΕΤΗΡΑΣ ΣΦΑΓΙΤΙΚΟΣ ΔΕ", 14);
        map.put("ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ", 15);
        map.put("ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΑΡ", 16);
        map.put("ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΔΕ", 17);
        map.put("ΜΟΣΧΕΥΜΑ ΔΕ", 18);
        map.put("ΠΡΟΣΩΡΙΝΟΣ ΚΑΘΕΤΗΡΑΣ", 19);
        map.put("ΠΡΟΣΩΡΙΝΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΔΕ", 20);

        map.put("ΦΙΣΤΟΥΛΑ", 21);

        id = map.get(name).toString();

        return id;
    }


    public static String getAggProspelasiName(int id) {

        String name;

        Map<Integer, String> map = new HashMap<>();

        map.put(0, "");
        map.put(1, "AVF");
        map.put(2, "AVF ΑΡ");
        map.put(3, "AVF ΔΕ");
        map.put(4, "AVG");
        map.put(5, "AVG ΑΡ");
        map.put(6, "VG ΔΕ");
        map.put(7, "ΚΑΘΕΤΗΡΑΣ");
        map.put(8, "ΚΕΝΤΙΚΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΜΟΝΙΜΟΣ");
        map.put(9, "ΚΕΝΤΡΙΚΟΣ ΚΑΘΕΤΗΡΑΣ ΔΕ");
        map.put(10, "ΚΕΝΤΡΙΚΟΣ ΚΑΘΕΤΗΡΑΣ ΥΠΟΚΛΕΙΔΙΟΣ ΔΕ");

        map.put(11, "ΜOΣΧΕΥΜΑ");
        map.put(12, "ΜOΣΧΕΥΜΑ ΑΡ");
        map.put(13, "ΜΟΝΙΜΟΣ ΚΑΘΕΤΗΡΑΣ");
        map.put(14, "ΜΟΝΙΜΟΣ ΚΑΘΕΤΗΡΑΣ ΣΦΑΓΙΤΙΚΟΣ ΔΕ");
        map.put(15, "ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ");
        map.put(16, "ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΑΡ");
        map.put(17, "ΜΟΝΙΜΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΔΕ");
        map.put(18, "ΜΟΣΧΕΥΜΑ ΔΕ");
        map.put(19, "ΠΡΟΣΩΡΙΝΟΣ ΚΑΘΕΤΗΡΑΣ");
        map.put(20, "ΠΡΟΣΩΡΙΝΟΣ ΣΦΑΓΙΤΙΔΙΚΟΣ ΔΕ");

        map.put(21, "ΦΙΣΤΟΥΛΑ");

        name = map.get(id);

        return name;
    }

    public static ArrayList<ClassItemsForRV> getExampleList() {

        ArrayList<ClassItemsForRV> lista = new ArrayList<>();


        lista.add(new ClassItemsForRV("title1", ""));
        lista.add(new ClassItemsForRV("title2", ""));
        lista.add(new ClassItemsForRV("title3", ""));
        lista.add(new ClassItemsForRV("title4", ""));
        lista.add(new ClassItemsForRV("title5", ""));
        lista.add(new ClassItemsForRV("title6", ""));
        lista.add(new ClassItemsForRV("title7", ""));
        lista.add(new ClassItemsForRV("title8", ""));


        return lista;

    }

    public static HashMap<String, String> getExampleHashMap() {

        HashMap<String, String> map = new HashMap<>();


        map.put("title1", "");
        map.put("title2", "");
        map.put("title3", "");
        map.put("title4", "");
        map.put("title5", "");
        map.put("title6", "");
        map.put("title7", "");
        map.put("title8", "");


        return map;

    }


    public static String getSitisiSinodouID(String name) {

        String id;

        Map<String, Integer> map = new HashMap<>();

        map.put("", 0);
        map.put("Π-Γ-Δ", 1);
        map.put("Γ-Δ", 2);
        map.put("Π", 3);
        map.put("Γ", 4);
        map.put("Δ", 5);

        id = map.get(name).toString();

        return id;


    }

    public static String getSitisiSinodouName(int id) {

        String name;

        Map<Integer, String> map = new HashMap<>();

        map.put(0, "");
        map.put(1, "Π-Γ-Δ");
        map.put(2, "Γ-Δ");
        map.put(3, "Π");
        map.put(4, "Γ");
        map.put(5, "Δ");

        name = map.get(id);

        return name;


    }


    public static ArrayList<String> getSitisiSinodouNames() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("Π-Γ-Δ");
        list.add("Γ-Δ");
        list.add("Π");
        list.add("Γ");
        list.add("Δ");

        return list;
    }


    public static ArrayList<ClassItemsForRV> getExitirioListaForRV() {

        ArrayList<ClassItemsForRV> lista = new ArrayList<>();

        lista.add(new ClassItemsForRV("Ασπιρίνη", ""));
        lista.add(new ClassItemsForRV("β-Αναστολέας", ""));
        lista.add(new ClassItemsForRV("Αναστ.Ασβεστίου", ""));
        lista.add(new ClassItemsForRV("Λιγοξίνη", ""));
        lista.add(new ClassItemsForRV("Αναστ.Υποδ.Αγγειοτ", ""));
        lista.add(new ClassItemsForRV("Διουρητικο", ""));
        lista.add(new ClassItemsForRV("Νιτρώδες", ""));
        lista.add(new ClassItemsForRV("Αντιπηκτικό", ""));
        lista.add(new ClassItemsForRV("Στόχος INR", ""));
        lista.add(new ClassItemsForRV("Αντιλιπιδαιμικό", ""));


        return lista;

    }
//--------------------------------
// EDWWWWWWWWWWWWWWWWWWWWWWWWWW

    public static ArrayList<String> getKinitikotitaLista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("Περιπατητικός");
        list.add("Βαδίζει με βοήθεια");
        list.add("Ακίνητος σε φορείο");


        return list;
    }


    public static int getKinitikotitaListaID(String value) {

        int id;
        if (value == null || value.equals(""))
            return 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);
        map.put(64, 1);
        map.put(65, 2);
        map.put(66, 3);


        //to proto kommati dinei san parametro kai pairnie to deutero
        if (!map.containsKey(Integer.parseInt(value)))
            return 0;
        else
            id = map.get(Integer.parseInt(value));

        return id;

    }

    public static String getKinitikotitaListaName(String name) {

        if (name.equals(""))
            return null;


        Map<String, Integer> map = new HashMap<>();

        map.put("", null);
        map.put("Περιπατητικός", 64);
        map.put("Βαδίζει με βοήθεια", 65);
        map.put("Ακίνητος σε φορείο", 66);


        return map.get(name).toString();


    }


//------------------------------------


    public static ArrayList<String> getAnapneustikiSixnotitaLista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("<9");
        list.add("9-14");
        list.add("15-20");
        list.add("21-29");
        list.add(">29");


        return list;
    }

    public static int getAnapneustikiSixnotitaListaID(String value) {

        int id;
        if (value == null || value.equals(""))
            return 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);
        map.put(34, 1);
        map.put(49, 2);
        map.put(64, 3);
        map.put(65, 4);
        map.put(66, 5);


        id = map.get(Integer.parseInt(value));

        return id;

    }

    public static String getAnapneustikiSixnotitaListaName(String id) {

        String name;
        if (id.equals(""))
            return null;
        //Map <Integer, String> map = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();

        map.put("", null);
        map.put("<9", 34);
        map.put("9-14", 49);
        map.put("15-20", 64);
        map.put("21-29", 65);
        map.put(">29", 66);

        name = map.get(id).toString();

        return name;


    }


    //----------------------------------------


    public static ArrayList<String> getKardiakiSixnotitaLista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("<41");
        list.add("41-50");
        list.add("51-100");
        list.add("101-110");
        list.add("111-129");
        list.add(">129");


        return list;
    }


    public static String getEidosAim(String eidosID) {

        if (eidosID == null)
            return "";
        Map<String, String> map = new HashMap<>();

        map.put("", "");
        map.put("0", "");
        map.put("1", "ΚΛΑΣΙΚΗ ΑΙΜΟΚΑΘΑΡΣΗ ΜΕ ΔΙΤΤΑΝΘΡΑΚΙΚΑ");
        map.put("2", "ON LINE ΑΙΜΟΔΙΑΔΙΗΘΗΣΗ ΠΡΟ ΦΙΛΤΡΟΥ");
        map.put("3", "ON LINE ΑΙΜΟΔΙΑΔΙΗΘΗΣΗ ΜΕΤΑ ΦΙΛΤΡΟΥ");


        return map.get(eidosID);
    }


    public static String getAntipiktikiAgogi(String agogiID) {

        if (agogiID == null)
            return "";
        Map<String, String> map = new HashMap<>();

        map.put("", "");
        map.put("0", "");
        map.put("1", "INNOHEP 1000");
        map.put("2", "INNOHEP 2000");
        map.put("3", "IVOR 2500 IU");
        map.put("4", "IVOR 3500 IU");
        map.put("5", "IVOR 5000 IU");
        map.put("6", "CLEXANE 20");
        map.put("7", "CLEXANE 40");
        map.put("8", "CLEXANE 60");
        map.put("9", "CLEXANE 80");
        map.put("10", "ΑΛΛΟ");



        return map.get(agogiID);
    }



    public static String getEidosDialimatos(String eidosID) {

        if (eidosID == null)
            return "";
        Map<String, String> map = new HashMap<>();

        map.put("", "");
        map.put("0", "");
        map.put("1", "A 761");
        map.put("2", "A 294");
        map.put("3", "A 257");
        map.put("4", "A 296");
        map.put("5", "A 298");
        map.put("6", "A 293");

        return map.get(eidosID);
    }


    public static String getEidosFiltrou(String eidosID) {

        if (eidosID == null)
            return "";
        Map<String, String> map = new HashMap<>();

        map.put("", "");
        map.put("0", "");
        map.put("1", "HIGH FLUX (ELISIO -19 M)");
        map.put("2", "HIGH FLUX (ELISIO -21 M)");
        map.put("3", "LOW FLUX (ELISIO -21 H)");
        map.put("4", "LOW FLUX (FDX -180)");
        map.put("5", "LOW FLUX (FDX -120)");
        map.put("6", "LOW FLUX (ULF-240H)");

        return map.get(eidosID);
    }


    public static int getKardiakiSixnotitaListaID(String value) {

        int id;
        if (value == null || value.equals(""))
            return 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);
        map.put(34, 1);
        map.put(49, 2);
        map.put(64, 3);
        map.put(65, 4);
        map.put(66, 5);
        map.put(67, 6);


        id = map.get(Integer.parseInt(value));

        return id;

    }

    public static String getKardiakiSixnotitaListaName(String id) {

        String name;
        if (id.equals(""))
            return null;
        Map<String, Integer> map = new HashMap<>();

        map.put("", null);
        map.put("<41", 34);
        map.put("41-50", 49);
        map.put("51-100", 64);
        map.put("101-110", 65);
        map.put("111-129", 66);
        map.put(">129", 67);

        name = map.get(id).toString();

        return name;


    }


    //---------------------------

    public static ArrayList<String> getSAP_lista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("<71");
        list.add("71-80");
        list.add("81-100");
        list.add("101-199");
        list.add(">199");

        return list;
    }


    public static int getSAP_listaID(String value) {

        int id;
        if (value == null || value.equals(""))
            return 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);
        map.put(19, 1);
        map.put(34, 2);
        map.put(49, 3);
        map.put(64, 4);
        map.put(66, 5);


        id = map.get(Integer.parseInt(value));

        return id;

    }


    public static String getSAP_listaName(String id) {

        String name;
        if (id.equals(""))
            return null;
        Map<String, Integer> map = new HashMap<>();

        map.put("", null);
        map.put("<71", 19);
        map.put("71-80", 34);
        map.put("81-100", 49);
        map.put("101-199", 64);
        map.put(">199", 66);

        name = map.get(id).toString();

        return name;


    }

    //--------------------------------




    public static ArrayList<String> getThermokrasiaLista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("ΨΥΧΡΟΣ <35°C");
        list.add("35-38.4°C");
        list.add("ΖΕΣΤΟΣ >38.4°C");


        return list;
    }


    public static int getThermokrasiaListaID(String value) {

        int id;
        if (value == null || value.equals(""))
            return 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);
        map.put(34, 1);
        map.put(64, 2);
        map.put(66, 3);

        id = map.get(Integer.parseInt(value));

        return id;

    }


    public static String getThermokrasiaName(String id) {

        String name;
        if (id.equals(""))
            return null;
        Map<String, Integer> map = new HashMap<>();

        map.put("", null);
        map.put("ΨΥΧΡΟΣ <35°C", 34);
        map.put("35-38.4°C", 64);
        map.put("ΖΕΣΤΟΣ >38.4°C", 66);


        name = map.get(id).toString();

        return name;


    }


    //-----------------------------

    public static ArrayList<String> getEpipedoSinProsoxisLista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("Συγχυτικός°C");
        list.add("Σε εγρήγορση");
        list.add("Αντιδρά στα λεκτικά");
        list.add("Αντιδρά στα επώδυνα");
        list.add("Κωματώδης");


        return list;
    }


    public static int getEpipedoSinProsoxisListaID(String value) {

        int id;
        if (value == null || value.equals(""))
            return 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);
        map.put(34, 1);
        map.put(64, 2);
        map.put(65, 3);
        map.put(66, 4);
        map.put(67, 5);


        id = map.get(Integer.parseInt(value));

        return id;

    }

    public static String getEpipedoSinProsoxisListaName(String id) {

        String name;
        if (id.equals(""))
            return null;
        Map<String, Integer> map = new HashMap<>();

        //  Log.e("eleos", id);

        map.put("", null);
        map.put("Συγχυτικός°C", 34);
        map.put("Σε εγρήγορση", 64);
        map.put("Αντιδρά στα λεκτικά", 65);
        map.put("Αντιδρά στα επώδυνα°C", 66);
        map.put("Κωματώδης", 67);


        name = map.get(id).toString();

        return name;


    }

// TELOSSSSSSSSSSSSSSSSSSSSSSSSSSS

    //-------------------------------------------------------------
    public static ArrayList<ItemsRV> getEktimisiEpigontonCheckboxes2() {
        Map<Integer, Integer> map;
        ItemsRV itemsRV;
        ArrayList<ItemsRV> lista = new ArrayList<>();


        lista.add(new ItemsRV("ΥΠΟΛΟΓΙΣΜΟΣ ΣΚΟΡ ΖΩΤΙΚΩΝ ΣΗΜΕΙΩΝ ΕΝΗΛΙΚΩΝ ( >14 ετών, > 150cm) ύψους )", "", TITLE_ITEM)); //0
//---
        map = new HashMap();
        map.put(0, 0);
        map.put(64, 1);
        map.put(65, 2);
        map.put(66, 3);
        itemsRV = new ItemsRV("Κινητικότητα:", "kinitikotita", SPINNER_TYPE, getKinitikotitaLista());
        itemsRV.setMap(map);
        lista.add(itemsRV);
        //---
        map = new HashMap();
        map.put(0, 0);
        map.put(34, 1);
        map.put(49, 2);
        map.put(64, 3);
        map.put(65, 4);
        map.put(66, 5);
        itemsRV = new ItemsRV("Αναπν. συχνότητα:", "anapnefstiki_sixnotita", SPINNER_TYPE, getAnapneustikiSixnotitaLista());
        itemsRV.setMap(map);
        lista.add(itemsRV);
        //---
        map = new HashMap();
        map.put(0, 0);
        map.put(34, 1);
        map.put(49, 2);
        map.put(64, 3);
        map.put(65, 4);
        map.put(66, 5);
        map.put(67, 6);
        itemsRV = new ItemsRV("Καρδιακή συχνότητα:", "kardiaki_sixnotita", SPINNER_TYPE, getKardiakiSixnotitaLista());
        itemsRV.setMap(map);
        lista.add(itemsRV);
        //---
        map = new HashMap();

        map.put(0, 0);
        map.put(19, 1);
        map.put(34, 2);
        map.put(49, 3);
        map.put(64, 4);
        map.put(66, 5);
        itemsRV = new ItemsRV("Συστολ. Αρτ. Πίεση(ΣΑΠ):", "sistoliki_artiriaki_piesi", SPINNER_TYPE, getSAP_lista());
        itemsRV.setMap(map);
        lista.add(itemsRV);
        //---
        map = new HashMap();
        map.put(0, 0);
        map.put(34, 1);
        map.put(64, 2);
        map.put(66, 3);
        itemsRV = new ItemsRV("Θερμοκρασία:", "thermokrasia", SPINNER_TYPE, getThermokrasiaLista());
        itemsRV.setMap(map);
        lista.add(itemsRV);
        //---
        map = new HashMap();
        map.put(0, 0);
        map.put(34, 1);
        map.put(64, 2);
        map.put(65, 3);
        map.put(66, 4);
        map.put(67, 5);
        itemsRV = new ItemsRV("Επίπεδο συνειδ. και προσοχής:", "sinidisi_prosoxi", SPINNER_TYPE, getEpipedoSinProsoxisLista());
        itemsRV.setMap(map);
        lista.add(itemsRV);
        //---


        lista.add(new ItemsRV("Τραύμα:", "travma", false, CHECKBOX_ITEM));

        //   ΕΠΕΙΓΟΥΣΑ ΠΑΡΕΜΒΑΣΗ
        lista.add(new ItemsRV("ΕΠΕΙΓΟΥΣΑ ΠΑΡΕΜΒΑΣΗ", "", TITLE_ITEM));   //8

        lista.add(new ItemsRV("Άπνοια", "apnoia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Διασωληνωμένος ασθενής", "diasolinomenos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Αναπνευστική δυσχέρεια "
                + "(αναπνοές πάνω από 30/λεπτό, "
                + "εισπνευστικός συριγμός, "
                + "κυάνωση άκρων, "
                + "εκφωνεί με δυσκολία απλές λέξεις)", "anapnefstiki_disxeria", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Έγκαυμα προσώπου, εισπνοή καπνού", "egkavma_prosopou", false, CHECKBOX_ITEM));

        lista.add(new ItemsRV("Απουσία σφίξεων στις κερκιδικές αρτηρίες αμφω", "apousia_sfixeon", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κώμα (δεν εκτελεί απλές οδηγίες)", "koma", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Επιληπτικοί σπασμοί", "epiliptikoi_spasmoi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Υπογλυκαιμία (GLU < 60 mg/dl)", "ipoglikaimia", false, CHECKBOX_ITEM));

        lista.add(new ItemsRV("Δηλητηρίαση ή εισπνοή πτητικών υγρών (νέφτι, βενζίνη, κλπ)", "dilitiriasi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ανεξέλεγκτη αιμορραγία", "anexelegkti_aimoragia", false, CHECKBOX_ITEM));


        // ΑΜΕΣΗ ΠΑΡΕΜΒΑΣΗ
        lista.add(new ItemsRV("ΑΜΕΣΗ ΠΑΡΕΜΒΑΣΗ", "", TITLE_ITEM));  //19

        lista.add(new ItemsRV("Τροχαίο (ταχύτητες πάνω από 40χλμ/ώρα), πτώση από ύψος (πάνω απο 3μ)", "troxaio", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ανοικτά τραύματα με ορατή μεγάλη αιμορραγία", "anoikta_travmata", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Τραύμα από αιχμηρό αντικείμενο (μαχαίρι, κλπ)", "travma_apo_aixmiro", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Έγκαυμα ολικού πάχους (χημικό, θερμικό) πάνω από το 20% της " +
                "συνολικής επιφάνειας του σώματος ή έγκαυμα στο οσχέο", "egkavma_paxous_20_more", false, CHECKBOX_ITEM));

        lista.add(new ItemsRV("Κυκλοτερές έγκαυμα άκρων, κορμού", "kikloteres_egkauma_akron", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Οξεία δύσπνοια με SaO2<90%", "oxia_dispnoia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Πόνος στο στήθος ή μεταξύ των ωμοπλατών", "ponos_stithous", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Εστιακή συμπτωματολογία συμβατή με αγγειακό επεισόδιο", "simptomatologia_egkefalikou", false, CHECKBOX_ITEM));

        lista.add(new ItemsRV("Ηλεκτροπληξία", "ilektroplixia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ανοικτό κάταγμα ή κάταγμα με παραμόρφωση του μέλους", "anoixto_katagma", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Μεταβαλλόμενο επίπεδο συνείδησης - συγχυτικός ασθενής", "metavalomeni_sineidisi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Αιματέμεση", "aimatemesi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Αποβολή αιμοφυρτών πτυελών ή καθαρού αίματος με τον βήχα", "apovoli_aima_ptielon", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Φαρμακευτική δηλητηρίαση", "farmakeftiki_dilitiriasi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Εξάρθρωση μεγάλων αρθρώσεων", "exarthrosi_megalon", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Πόνος πάνω από 7/10", "ponos_7_more", false, CHECKBOX_ITEM));

        lista.add(new ItemsRV("Επιθετικότητα, ψυχωσικά συμπτώματα, αυτοκτονικός ιδεασμός", "epithetikotita", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ασφυγμο ψυχρό άκρο", "asfigmo_akro", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Εκτεταμένο οίδημα άκρου", "Ektetameno_oidima_akrou", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Τραυματισμοί ματιών", "travmatismoi_mation", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Διαβητικός με σάκχαρο >200 και κετόνες ούρων", "diavitikos_200_more", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Πυρετός με ανοσοκατεσταλμένο ασθενή", "piretos_anosokatestalmeno", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κεφαλαλγία αιφνίδιας έναρξης", "kefalalgia_efnidia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κοιλιακό άλγος ή τραυματισμός στην κοιλιά σε έγκυο", "koiliako_algos_egkiou", false, CHECKBOX_ITEM));

//   ΕΣΠΕΥΣΜΕΝΗ ΠΑΡΕΜΒΑΣΗ
        lista.add(new ItemsRV("ΕΣΠΕΥΣΜΕΝΗ ΠΑΡΕΜΒΑΣΗ", "", TITLE_ITEM));  //45

        lista.add(new ItemsRV("Ελεγχόμενη αιμοραγία", "elegxomeni_aimoragia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Εξάρθρωση δακτύλων", "exarthrosi_daktilon", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κλειστό κάταγμα χωρίς παραμόρφωση ή σημαντική διόγκωση του σκέλους", "kleisto_katagma", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Έγκαυμα ολικού πάχους κάτω από το 20% της συνολικής επιφάνειας του σωματός", "egkavma_paxous_20_less", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κοιλιακό άλγος", "koiliako_algos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Σάκχαρο αίματος >300 mg/dl χωρίς κετόνες", "sakxaro_300_more", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Τραυματισμός εγκύου (πλην της περιοχής της κοιλίας)", "travmatismos_egkiou", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Απώλεια αίματος από τον κόλπο σε έγκυο", "apoleia_aimatos_egkiou", false, CHECKBOX_ITEM));
// συνολο 44 -3(position) οι τιτλοι
        // 53 - 4 synolika pedia
        return lista;

    }


    public static ArrayList<ClassItemsCheckboxesForRV> getEktimisiEpigontonCheckboxes() {

        ArrayList<ClassItemsCheckboxesForRV> lista = new ArrayList<>();

        //   ΕΠΕΙΓΟΥΣΑ ΠΑΡΕΜΒΑΣΗ

        lista.add(new ClassItemsCheckboxesForRV("ΕΠΕΙΓΟΥΣΑ ΠΑΡΕΜΒΑΣΗ", false));  //0

        lista.add(new ClassItemsCheckboxesForRV("Άπνοια", false));
        lista.add(new ClassItemsCheckboxesForRV("Διασωληνωμένος ασθενής", false));
        lista.add(new ClassItemsCheckboxesForRV("Αναπνευστική δυσχέρεια "
                + "(αναπνοές πάνω από 30/λεπτό, "
                + "εισπνευστικός συριγμός, "
                + "κυάνωση άκρων, "
                + "εκφωνεί με δυσκολία απλές λέξεις)", false));
        lista.add(new ClassItemsCheckboxesForRV("Έγκαυμα προσώπου, εισπνοή καπνού", false));

        lista.add(new ClassItemsCheckboxesForRV("Απουσία σφίξεων στις κερκιδικές αρτηρίες αμφω", false));
        lista.add(new ClassItemsCheckboxesForRV("Κώμα (δεν εκτελεί απλές οδηγίες)", false));
        lista.add(new ClassItemsCheckboxesForRV("Επιληπτικοί σπασμοί", false));
        lista.add(new ClassItemsCheckboxesForRV("Υπογλυκαιμία (GLU < 60 mg/dl)", false));

        lista.add(new ClassItemsCheckboxesForRV("ηλητηρίαση ή εισπνοή πτητικών υγρών (νέφτι, βενζίνη, κλπ)", false));
        lista.add(new ClassItemsCheckboxesForRV("Ανεξέλεγκτη αιμορραγία", false));


        // ΑΜΕΣΗ ΠΑΡΕΜΒΑΣΗ
        lista.add(new ClassItemsCheckboxesForRV("ΑΜΕΣΗ ΠΑΡΕΜΒΑΣΗ", false));  //11

        lista.add(new ClassItemsCheckboxesForRV("Τροχαίο (ταχύτητες πάνω από 40χλμ/ώρα), πτώση από ύψος (πάνω απο 3μ)", false));
        lista.add(new ClassItemsCheckboxesForRV("Ανοικτά τραύματα με ορατή μεγάλη αιμορραγία", false));
        lista.add(new ClassItemsCheckboxesForRV("Τραύμα από αιχμηρό αντικείμενο (μαχαίρι, κλπ)", false));
        lista.add(new ClassItemsCheckboxesForRV("Έγκαυμα ολικού πάχους (χημικό, θερμικό) πάνω από το 20% της " +
                "συνολικής επιφάνειας του σώματος ή έγκαυμα στο οσχέο", false));

        lista.add(new ClassItemsCheckboxesForRV("Κυκλοτερές έγκαυμα άκρων, κορμού", false));
        lista.add(new ClassItemsCheckboxesForRV("Οξεία δύσπνοια με SaO2<90%", false));
        lista.add(new ClassItemsCheckboxesForRV("Πόνος στο στήθος ή μεταξύ των ωμοπλατών", false));
        lista.add(new ClassItemsCheckboxesForRV("Εστιακή συμπτωματολογία συμβατή με αγγειακό επεισόδιο", false));

        lista.add(new ClassItemsCheckboxesForRV("Ηλεκτροπληξία", false));
        lista.add(new ClassItemsCheckboxesForRV("Ανοικτό κάταγμα ή κάταγμα με παραμόρφωση του μέλους", false));
        lista.add(new ClassItemsCheckboxesForRV("Μεταβαλλόμενο επίπεδο συνείδησης - συγχυτικός ασθενής", false));
        lista.add(new ClassItemsCheckboxesForRV("Αιματέμεση", false));
        lista.add(new ClassItemsCheckboxesForRV("Αποβολή αιμοφυρτών πτυελών ή καθαρού αίματος με τον βήχα", false));
        lista.add(new ClassItemsCheckboxesForRV("Φαρμακευτική δηλητηρίαση", false));
        lista.add(new ClassItemsCheckboxesForRV("Εξάρθρωση μεγάλων αρθρώσεων", false));
        lista.add(new ClassItemsCheckboxesForRV("Πόνος πάνω από 7/10", false));

        lista.add(new ClassItemsCheckboxesForRV("Επιθετικότητα, ψυχωσικά συμπτώματα, αυτοκτονικός ιδεασμός", false));
        lista.add(new ClassItemsCheckboxesForRV("Ασφυγμο ψυχρό άκρο", false));
        lista.add(new ClassItemsCheckboxesForRV("Εκτεταμένο οίδημα άκρου", false));
        lista.add(new ClassItemsCheckboxesForRV("Τραυματισμοί ματιών", false));
        lista.add(new ClassItemsCheckboxesForRV("Διαβητικός με σάκχαρο >200 και κετόνες ούρων", false));
        lista.add(new ClassItemsCheckboxesForRV("Πυρετός με ανοσοκατεσταλμένο ασθενή", false));
        lista.add(new ClassItemsCheckboxesForRV("Κεφαλαλγία αιφνίδιας έναρξης", false));
        lista.add(new ClassItemsCheckboxesForRV("Κοιλιακό άλγος ή τραυματισμός στην κοιλιά σε έγκυο", false));

//   ΕΣΠΕΥΣΜΕΝΗ ΠΑΡΕΜΒΑΣΗ
        lista.add(new ClassItemsCheckboxesForRV("ΕΣΠΕΥΣΜΕΝΗ ΠΑΡΕΜΒΑΣΗ", false));  //36

        lista.add(new ClassItemsCheckboxesForRV("Ελεγχόμενη αιμοραγία", false));
        lista.add(new ClassItemsCheckboxesForRV("Εξάρθρωση δακτύλων", false));
        lista.add(new ClassItemsCheckboxesForRV("Κλειστό κάταγμα χωρίς παραμόρφωση ή σημαντική διόγκωση του σκέλους", false));
        lista.add(new ClassItemsCheckboxesForRV("Έγκαυμα ολικού πάχους κάτω από το 20% της συνολικής επιφάνειας του σωματός", false));
        lista.add(new ClassItemsCheckboxesForRV("Κοιλιακό άλγος", false));
        lista.add(new ClassItemsCheckboxesForRV("Σάκχαρο αίματος >300 mg/dl χωρίς κετόνες", false));
        lista.add(new ClassItemsCheckboxesForRV("Τραυματισμός εγκύου (πλην της περιοχής της κοιλίας)", false));
        lista.add(new ClassItemsCheckboxesForRV("Απώλεια αίματος από τον κόλπο σε έγκυο", false));

// συνολο 44 -3(position) οι τιτλοι
        // 45 - 3 synolika pedia
        return lista;

    }


    public static ArrayList<ItemsRV> getParakolouthisiListaForRV2() {

        ArrayList<ItemsRV> lista = new ArrayList<>();


        lista.add(new ItemsRV("Σφύξεις", "sfixeis", "", EDITTEXT_ITEM, AKERAIOS));       // 0  NUMBER AKERAIOS
        lista.add(new ItemsRV("Συστολική πίεση", "sis_piesi", "", EDITTEXT_ITEM, DEKADIKOS));   // 1  DECIMAL
        lista.add(new ItemsRV("Διαστολική πίεση", "dias_piesi", "", EDITTEXT_ITEM, DEKADIKOS));   // 1  DECIMAL
        // lista.add(new ItemsRV("Αρτηριακή πίεση", "piesi","",2,3));   // 1  DECIMAL
        lista.add(new ItemsRV("Θερμοκρασία (°C)", "thermokrasia", "", EDITTEXT_ITEM, DEKADIKOS));   // 2  DECIMAL
        lista.add(new ItemsRV("Παλμική οξυμετρία", "oximetria", "", EDITTEXT_ITEM, DEKADIKOS)); // 3  DECIMAL
        lista.add(new ItemsRV("Αναπνοές", "anapnoes", "", EDITTEXT_ITEM, EDITTEXT_ITEM));          // 4  DECIMAL

        ItemsRV x = new ItemsRV("Αυτοαξιολόγηση πόνου", "ponos", "", EDITTEXT_ITEM, EUROS_TIMON);
        x.setMin(0);
        x.setMax(10);
        lista.add(x);  // 5  NUMBER AKERAIOS   0-10
        lista.add(new ItemsRV("Σάκχαρα", "sakxara", "", EDITTEXT_ITEM, DEKADIKOS));          // 4  DECIMAL

        lista.add(new ItemsRV("Επισκληρίδιος καθετήρας", "Episkliridios", "", EDITTEXT_ITEM, 1)); // 6  KEIMENO
        lista.add(new ItemsRV("Βάρος σώματος (kg)", "varos", "", EDITTEXT_ITEM, AKERAIOS));             // 7  NUMBER AKERAIOS
        lista.add(new ItemsRV("Ύψος (cm)", "ipsos", "", EDITTEXT_ITEM, DEKADIKOS));              // 8  DECIMAL


        lista.add(new ItemsRV("Προσλαμβανόμενα", "", 0));       //TITLOS     POSITION 11

        lista.add(new ItemsRV("Από το στόμα", "in_stoma", "", 2, 1));       // 10  KEIMENO
        lista.add(new ItemsRV("Ενδοφλεβίως", "in_endoflevia", "", 2, 1));       // 11  KEIMENO

        lista.add(new ItemsRV("Αποβαλλόμενα", "", 0));            //TITLOS    POSITION 14

        lista.add(new ItemsRV("Ούρα", "out_oura", "", 2, 1));                  // 13  KEIMENO
        lista.add(new ItemsRV("Παροχέτευση 1", "out_paroxetefsi1", "", 2, 1));         // 14  KEIMENO
        lista.add(new ItemsRV("Παροχέτευση 2", "out_paroxetefsi2", "", 2, 1));         // 15  KEIMENO
        lista.add(new ItemsRV("Παροχέτευση 3", "out_paroxetefsi3", "", 2, 1));         // 16  KEIMENO
        lista.add(new ItemsRV("Παροχέτευση 4", "out_paroxetefsi4", "", 2, 1));         // 17  KEIMENO

        lista.add(new ItemsRV("Εμετοί", "out_emetoi", "", 2, 1));                // 18  KEIMENO
        lista.add(new ItemsRV("Εφιδρώσεις", "out_efidroseis", "", 2, 1));            // 19  KEIMENO
        lista.add(new ItemsRV("Κενώσεις", "out_kenoseis", "", 2, 1));              // 20  KEIMENO EPILOGIS ME LISTENER ALERT_DIALOG


        return lista;

        // POSITION 20-2  oi titloi einai to -2
        // ΣΥΝΟΛΟ 21 -2   oi titloi einai to -2

    }

    public static ArrayList<ClassItemsForRV> getParakolouthisiListaForRV() {

        ArrayList<ClassItemsForRV> lista = new ArrayList<>();

//          ΤΑ ΣΧΟΛΙΑ ΕΙΝΑΙ ΓΙΑ ΤΟ ΤΙ ΤΥΠΟ ΘΑ ΠΑΡΕΙ ΤΟ ΚΑΘΕ EDITTEXT ΣΤΟ RecyclerViewParakolouthisiAdaptor
        // ΑΝΑΛΟΓΑ ΤΗ ΘΕΣΗ ΤΗΣ ΛΙΣΤΑΣ

        lista.add(new ClassItemsForRV("Θερμοκρασία", ""));   // 0  DECIMAL
        lista.add(new ClassItemsForRV("Σφύξεις", ""));       // 1  NUMBER AKERAIOS
        lista.add(new ClassItemsForRV("Αρτηριακή πίεση", ""));   // 2  DECIMAL
        lista.add(new ClassItemsForRV("Παλμική οξυμετρία", "")); // 3  DECIMAL
        lista.add(new ClassItemsForRV("Αναπνοές", ""));          // 4  DECIMAL

        lista.add(new ClassItemsForRV("Αυτοαξιολόγηση πόνου", ""));  // 5  NUMBER AKERAIOS   0-10
        lista.add(new ClassItemsForRV("Επισκληρίδιος καθετήρας", "")); // 6  KEIMENO
        lista.add(new ClassItemsForRV("Βάρος σώματος (kg)", ""));             // 7  NUMBER AKERAIOS
        lista.add(new ClassItemsForRV("Ύψος (cm)", ""));              // 8  DECIMAL


        lista.add(new ClassItemsForRV("Προσλαμβανόμενα", ""));       //TITLOS     POSITION 9

        lista.add(new ClassItemsForRV("Από το στόμα", ""));       // 10  KEIMENO
        lista.add(new ClassItemsForRV("Ενδοφλεβίως", ""));       // 11  KEIMENO

        lista.add(new ClassItemsForRV("Αποβαλλόμενα", ""));            //TITLOS    POSITION 12

        lista.add(new ClassItemsForRV("Ούρα", ""));                  // 13  KEIMENO
        lista.add(new ClassItemsForRV("Παροχέτευση 1", ""));         // 14  KEIMENO
        lista.add(new ClassItemsForRV("Παροχέτευση 2", ""));         // 15  KEIMENO

        lista.add(new ClassItemsForRV("Εμετοί", ""));                // 18  KEIMENO
        lista.add(new ClassItemsForRV("Εφιδρώσεις", ""));            // 19  KEIMENO
        lista.add(new ClassItemsForRV("Κενώσεις", ""));              // 20  KEIMENO EPILOGIS ME LISTENER ALERT_DIALOG

        lista.add(new ClassItemsForRV("Παροχέτευση 3", ""));         // 16  KEIMENO
        lista.add(new ClassItemsForRV("Παροχέτευση 4", ""));         // 17  KEIMENO
        lista.add(new ClassItemsForRV("Σάκχαρα", ""));          // 4  DECIMAL

        return lista;

        // POSITION 20-2  oi titloi einai to -2
        // ΣΥΝΟΛΟ 21 -2   oi titloi einai to -2

    }


    public static ArrayList<ItemsRV> getIsozigio_Apov_Pros() {

        ArrayList<ItemsRV> lista = new ArrayList<>();


        lista.add(new ItemsRV("Προσλαμβανόμενα", "", TITLE_ITEM));       //TITLOS     POSITION 0

        lista.add(new ItemsRV("Ενδοφλεβίως", "endoflevios", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Διεντερικώς", "dienterikos", "", EDITTEXT_ITEM, AKERAIOS));

        lista.add(new ItemsRV("Αποβαλλόμενα", "", TITLE_ITEM));    //TITLOS     POSITION 3

        lista.add(new ItemsRV("Ούρα", "out_oura", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 1", "out_paroxetefsi1", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 2", "out_paroxetefsi2", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 3", "out_paroxetefsi3", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 4", "out_paroxetefsi4", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 5", "out_paroxetefsi5", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Εμετοί", "out_emetoi", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Εφιδρώσεις", "out_efidroseis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Κενώσεις", "out_kenoseis", "", EDITTEXT_ITEM, DEKADIKOS));

        return lista;


    }


    public static ArrayList<ItemsRV> getIsozigio_Apov_Pros_items_statheres_metriseis() {

        ArrayList<ItemsRV> lista = new ArrayList<>();



        lista.add(new ItemsRV("Ενδοφλεβίως", "endoflevios", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Διεντερικώς", "dienterikos", "", EDITTEXT_ITEM, AKERAIOS));

        lista.add(new ItemsRV("Αποβαλλόμενα", "", TITLE_ITEM));    //TITLOS     POSITION 3

        lista.add(new ItemsRV("Ούρα", "out_oura", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 1", "out_paroxetefsi1", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 2", "out_paroxetefsi2", "", EDITTEXT_ITEM, DEKADIKOS));


        return lista;


    }







    public static  ArrayList<Simple_Items> getIsozigio_Apov_Pros_item() {

        ArrayList<Simple_Items> lista = new ArrayList<>();

        lista.add(new Simple_Items(1,"ΠΕΡΙΦΕΡΙΚΗ ΓΡΑΜΜΗ 1"));
        lista.add(new Simple_Items(2,"ΠΕΡΙΦΕΡΙΚΗ ΓΡΑΜΜΗ 2"));
        lista.add(new Simple_Items(3,"ΑΛΛΑΓΗ ΠΕΡΙΦΕΡΙΚΗΣ ΓΡΑΜΜΗ"));
        lista.add(new Simple_Items(4,"ΚΕΝΤΡΙΚΗ ΓΡΑΜΜΗ"));
        lista.add(new Simple_Items(5,"ΑΛΛΑΓΗ ΚΕΝΤΡΙΚΗΣ ΓΡΑΜΜΗ"));
        lista.add(new Simple_Items(6,"ΚΑΘΕΤΗΡΑΣ ΑΙΜΟΚΑΘΑΡΣΗΣ"));
        lista.add(new Simple_Items(7,"ΑΡΤΗΡΙΑΚΗ ΓΡΑΜΜΗ"));
        lista.add(new Simple_Items(8,"ΘΗΚΑΡΙ (new Isozigio_Items(+ ηλεκτροδια προσ.βημ.))"));
        lista.add(new Simple_Items(9,"ΕΠΙΚΑΡΔΙΑ ΗΛΕΚΤΡ. \n ΠΡΟΣΩΡ.ΒΗΜ"));

        lista.add(new Simple_Items(10,"Ουροκαθετήρας FOLLEY -τύπος"));
        lista.add(new Simple_Items(11,"Αλλαγή ουροκαθετήρα -τυπος"));
        lista.add(new Simple_Items(12,"LEVIN -ύπος ρινογαστρ. καθ."));
        lista.add(new Simple_Items(13,"Ρινοφαρυγγικός αεραγωγός Νο"));
        lista.add(new Simple_Items(14,"Στοματοφαρυγγικός αεραγωγός Νο"));
        lista.add(new Simple_Items(15,"ΣΩΛΗΝΑΣ ΤΡΑΧΕΙΟΣΤΟΜΙΑΣ -Νο"));
        lista.add(new Simple_Items(16,"ΣΩΛ. ΤΡΑΧ. Πίεση CUFF"));
        lista.add(new Simple_Items(17,"ΑΛΛΑΓΗ ΣΩΛΗΝΑ ΤΡΑΧΕΙΟΣΤΟΜΙΑΣ -Νο"));
        lista.add(new Simple_Items(18,"ΑΛΛΑΓΗ ΣΩΛΗΝΑ Πίεση CUFF"));
        lista.add(new Simple_Items(19,"Επισκληρίδιος καθετήρας"));
        lista.add(new Simple_Items(20,"Αντλία πόνου"));

        lista.add(new Simple_Items(21,"ΠΑΡΟΧΕΤΕΥΣΗ θωρακική-πίεση στηλης νερού (new Isozigio_Items(cm))"));
        lista.add(new Simple_Items(22, "Διαφυγή αέρα"));
        lista.add(new Simple_Items(23,"ΠΑΡΟΧΕΤΕΥΣΗ πλευριτική: Δεξιά-Αριστερά"));
        lista.add(new Simple_Items(24,"άλλη ΠΑΡΟΧΕΤΕΥΣΗ: Α"));
        lista.add(new Simple_Items(25,"άλλη ΠΑΡΟΧΕΤΕΥΣΗ: Β"));
        lista.add(new Simple_Items(26,"άλλη ΠΑΡΟΧΕΤΕΥΣΗ: Γ"));
        lista.add(new Simple_Items(27,"άλλη ΠΑΡΟΧΕΤΕΥΣΗ: Δ"));
        lista.add(new Simple_Items(28,"άλλη ΠΑΡΟΧΕΤΕΥΣΗ: Ε"));
        lista.add(new Simple_Items(29,"Άλλο"));
        lista.add(new Simple_Items(30,"ΗΜ/ΝΙΑ τελ. κένωσης"));


        return lista;

    }


    public static ArrayList<ItemsRV> getIsozigio_Meth_Lista() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

        lista.add(new ItemsRV("Προσλαμβανόμενα", "", TITLE_ITEM));       //TITLOS     POSITION 0

      //  lista.add(new ItemsRV("Ορός 24/ώρου", "itemID", "", TEXTVIEW_TYPE, MEDICINES));
      //  lista.add(new ItemsRV("Ml/h", "posotita", "", EDITTEXT_ITEM, DEKADIKOS));
       // lista.add(new ItemsRV("ογκος ", "emploutismos", "", EDITTEXT_ITEM, KEIMENO));
      //  lista.add(new ItemsRV("Δοσολογία", "dosologia", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Φάρμακα συνεχούς έγχυσης", "all_hours_meds", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Συστημική φαρμ. αγωγή", "systemic_meds", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Χορήγηση εφάπαξ φαρμάκων", "one_time_meds", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Άλλα φάρμακα", "other_meds", "",  TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Μεταγγίσεις", "metaggiseis", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("PO/PL/PG:Σίτιση-Υγρά(ml)", "PO_PL_PG", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Αιμοδιαδιήθηση(Α/Δ)\n:HEPARINE I.U./h", "aimodiadihthisi", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Αποβαλλόμενα", "", TITLE_ITEM));    //TITLOS     POSITION 7

        lista.add(new ItemsRV("ΟΥΡΑ", "oura", "", EDITTEXT_ITEM, DEKADIKOS));
        //lista.add(new ItemsRV("Πλύσεις κύστεως", "pluseis_kusteos", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Αιμοκάθαρση/Αιμοδιήθηση", "aimokatharsi_aimodihthisi", "" ,EDIT_TEXT_TYPE , DEKADIKOS));
       // lista.add(new ItemsRV("Αιμοκάθαρση/Αιμοδιήθηση", "aimokatharsi_aimodihthisi", SPINNER_TYPE_NEW , Spinner_items_lists.getAimokatharsi_aimodiathisi()));
        //lista.add(new ItemsRV("Διαλύματα Α/Δ", "dialumata", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Εφιδρώσεις", "out_efidroseis", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Εμετοί", "out_emetoi",  "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Κενώσεις", "out_kenoseis", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("levin", "levin", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 1", "v_paroxeteush1", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 1\nΠοσότητα", "paroxeteush1_posotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 2", "v_paroxeteush2", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 2\nΠοσότητα", "paroxeteush2_posotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 3", "v_paroxeteush3", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 3\nΠοσότητα", "paroxeteush3_posotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 4", "v_paroxeteush4", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 4\nΠοσότητα", "paroxeteush4_posotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 5", "v_paroxeteush5", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 5\nΠοσότητα", "paroxeteush5_posotita", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Παροχέτευση 6", "v_paroxeteush6", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 6\nΠοσότητα", "paroxeteush6_posotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 7", "v_paroxeteush7", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 7\nΠοσότητα", "paroxeteush7_posotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 8", "v_paroxeteush8", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 8\nΠοσότητα", "paroxeteush8_posotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 9", "v_paroxeteush9", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 9\nΠοσότητα", "paroxeteush9_posotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παροχέτευση 10", "v_paroxeteush10", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Παροχέτευση 10\nΠοσότητα", "paroxeteush10_posotita", "", EDITTEXT_ITEM, DEKADIKOS));

       // lista.add(new ItemsRV("κολοστομία/κένωση(:|)\n+++:διαρροϊκή", "kolostomia_kenwsh", "", EDITTEXT_ITEM, DEKADIKOS));

        return lista;
    }



    public static ArrayList<String> getKenoseisLista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("");
        list.add("Φυσιολογική");
        list.add("Διαρροϊκή");
        list.add("Μέλαινα");

        return list;
    }

    public static String getKenoseisID(String name) {

        String id;

        Map<String, Integer> map = new HashMap<>();

        map.put("", 0);
        map.put("Φυσιολογική", 1);
        map.put("Διαρροϊκή", 2);
        map.put("Μέλαινα", 3);

        if (map.get(name) == null)
            return "";

        id = map.get(name).toString();

        return id;
    }


    public static String getKenoseisName(String id) {

        String name;

        Map<String, String> map = new HashMap<>();

        map.put("0", "");
        map.put("1", "Φυσιολογική");
        map.put("2", "Διαρροϊκή");
        map.put("3", "Μέλαινα");

        name = map.get(id);

        return name;
    }


    public static ArrayList<String> getParakolouthisiKatigoriesDiagramLista() {

        ArrayList <String>  lista = new ArrayList<>();
        lista.add("Θερμοκρασία");
        lista.add("Σφύξεις");
        lista.add("Πιεση");
        lista.add("Οξυμετρία");
        lista.add("Αναπνοές");
        lista.add("Πόνος");


        return lista;
    }


    public static ArrayList<String> getZotikaKatigoriesDiagramLista() {

        ArrayList <String> lista = new ArrayList<>();
        lista.add("Συστολική πίεση");
        lista.add("Διαστολική πίεση");
        lista.add("Σφύξεις");
        lista.add("Θερμοκρασία");
        lista.add("Κλίμακα πόνου");
        lista.add("SPO2");
        lista.add("Stick glu");
        lista.add("Ούρα");
        lista.add("Αναπνοές");


        return lista;
    }



    public static ArrayList<String> getZotikaKatigories_meth_DiagramLista() {

        ArrayList <String> lista = new ArrayList<>();
        lista.add("Συστολική πίεση");
        lista.add("Διαστολική πίεση");
        lista.add("Σφύξεις");
        lista.add("Θερμοκρασία");
        lista.add("Κλίμακα πόνου");
       // lista.add("SPO2");
        lista.add("Stick glu");
        //lista.add("Ούρα");
        lista.add("Αναπνοές");


        return lista;
    }


    public static ArrayList<Nosil_IstorikoList> getNosil_Istoriko_lista() {

        // Η ΛΙΣΤΑ ΝΑ ΕΧΕΙ ΤΗ ΣΕΙΡΑ ΟΠΩς ΕΡΧΟΝΤΑΙ ΤΑ ΔΕΟΔΜΕΝΑ ΑΠΟ ΤΗ ΒΑΣΗ

        ArrayList<Nosil_IstorikoList> lista = new ArrayList<>();

        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΦΥΛΛΟ ΝΟΣΗΛΕΥΤΙΚΗΣ ΑΝΑΦΟΡΑΣ")));  //0

        lista.add(new Nosil_IstorikoList(new TextViewSForRV("Ημ/νία - ώρα", "")));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Είδος Εισαγωγής", "")));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Τρόπος μεταφοράς", "")));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Τις πληροφορίες δίδει", "")));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Άλλα νοσήματα", "", 1)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Σακχαρώδης διαβήτης", false)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Προηγούμενες νοσηλείες (ημ/νία, αιτία)", "", 1)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Αλλεργίες", "", 1)));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Μέτρα λοιμώξεων (είδος)", "")));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Κύρια συμπτώματα κατά την εισαγωγή", "", 1)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Σφύξεις", "", 2)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Αρτηριακή πίεση Σ", "", 1)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Αρτηριακή πίεση Δ", "", 1)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Θερμοκρασία", "", 3)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Παλμική οξυμετρία", "", 3)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Αναπνοές", "", 2)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Κλίμακα πόνου", "", 4)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Βάρος σώματος (kg)", "", 2)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Ύψος (cm)", "", 3)));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΑΙΣΘΗΤΗΡΙΑ ΟΡΓΑΝΑ"))); //20

        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Ομιλία", "")));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Μιλάει ξένη γλώσσα", "", 1)));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Ακοή", "")));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Ακουστικά", "")));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Όραση", "")));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Δέρμα", "")));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Τραυμα", "", 1)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Έλκη κατάκλισης (σημείο/βαθμός)", "", 1)));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΑΝΑΠΝΕΥΣΤΙΚΟ"))); //29

        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Αναπνοή", "")));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Βήχας", "")));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΚΥΚΛΟΦΟΡΙΚΟ"))); //32

        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Καρδιακός ρυθμός", "")));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Βηματοδότης / απινιδωτής", "", 1)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Αίσθημα παλμών", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Υπερταση", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Οιδήματα", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Φλεβίτιδα", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Αναιμία", false)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Στοιχεία ΗΚΓ", "", 1)));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΠΕΠΤΙΚΟ"))); //41

        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ελεύθερη δίαιτα", false)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Ειδική δίαιτα", "", 1)));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Όρεξη", "")));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Δυσκολία στη μάσηση", false)));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Οδοντοστοιχία", "")));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Ρινογαστρικός σωλήνας", "", 1)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ναυτία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Εμετοί", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Δυσκολία στην κατάποση", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Γαστροστομία", false)));
        lista.add(new Nosil_IstorikoList(new TextViewSForRV("Ημ/νία τελεύταιας κένωσης", "")));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Παχύ έντερο", "")));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Μέλαινα κένωση", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Διάταση κοιλίας", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Κολοστομία", false)));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΝΕΦΡΟΛΟΓΙΚΟ - ΟΥΡΟΠΟΙΗΤΙΚΟ"))); //57

        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Συχνοουρία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Δυσουρία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Αιματουργία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ολιγουρία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ανουρία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Υπο αιμοκάθαρση", false)));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Ουροκαθετήρας", "")));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Ουρ. Είδος", "", 1)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Ουρ. Νο.", "", 1)));
        lista.add(new Nosil_IstorikoList(new TextViewSForRV("Ημ/νία τοποθέητησης", "")));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΜΥΟΣΚΕΛΕΤΙΚΟ"))); //68

        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Αυτοεξυπηρέτηση", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Βοήθεια ατ.υγιεινής", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("βοήθεια στο ντύσιμο", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("έγερση", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("πόνος", false)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("πόνος που", "", 1)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("κλίμακα πόνου", "", 2)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Βάδιση", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Με Π", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Με πατερίτσες", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Με μπαστούνι", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Δεν μετακινείται", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Κάταγμα", false)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Κάταγμα πού", "", 1)));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΨΥΧΟΔΙΑΝΟΗΤΙΚΗ ΚΑΤΑΣΤΑΣΗ"))); //83

        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Αισιόδοξος", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ανήσυχος", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Φοβισμένος", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Απομονωμένος", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ενήμερος για τη νόσο", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Αγνοεί τη σοβαρότητα", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Φόβος θανάτου", false)));
        lista.add(new Nosil_IstorikoList(new SpinnersForRV("Επικοινωνία", "")));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΝΕΥΡΟΛΟΓΙΚΟ"))); //92

        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ημιπληγία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Παράλυση", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Επιληπτικές κρίσεις", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Λιποθυμία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Αδυναμία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Μούδιασμα", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Τρόμος", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Κεφαλαλγία", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ζάλη", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Προσανατολισμένος (τόπος, χρόνος, πρόσωπα)", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Κώμα", false)));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΑΤΟΜΙΚΕΣ ΣΥΝΗΘΕΙΕΣ"))); //104

        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Κάπνισμα", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Χρήση οινοπνεύματος", false)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Άλλες", "", 1)));
        lista.add(new Nosil_IstorikoList(new EditTextForRV("Ώρες ύπνου το 24ωρο", "", 2)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Ζει μόνος", false)));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΦΑΡΜΑΚΑ ΠΟΥ ΛΑΜΒΑΝΕΙ"))); //110

        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Φέρει δικά του φάρμακα", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Φέρει δικές του εξετάσεις", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Αριστερόχειρος", false)));
        lista.add(new Nosil_IstorikoList(new CheckBoxesForRV("Δεξιόχειρος", false)));


        lista.add(new Nosil_IstorikoList(new TitlesForRV("ΝΟΣΗΛΕΥΤΙΚΗ ΕΚΤΙΜΗΣΗ"))); //115

        lista.add(new Nosil_IstorikoList(new EditTextForRV("Νοσηλευτικές διαγνώσεις", "", 1)));

        //12 TITLOI
        // ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΓΙΝΟΥΝ ΑΛΛΑΓΕΣ ΣΤΗ ΛΙΣΤΑ ΠΡΠΕΕΙ ΝΑ ΑΛΛΑΞΩ  ΣΤΑ (IF STATEMENT) ΤΙΣ ΤΙΜΕΣ ΣΤΟ
        //  Nosil_IstorikoActivity ---> manager.setSpanSizeLookup ΤΟΥ RECYCLER VIEW,
        // Nosil_IstorikoActivity ---> STO valuesFromJSONlista,
        // StringQueries ----> getNOSILEUTIKO_ISTORIKO_INSERT

        return lista;
    }





    public static ArrayList<ItemsRV> getNosil_Elegxos_Meth() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

        lista.add(new ItemsRV("ΝΟΣΗΛΕΥΤΙΚΟΣ ΕΛΕΓΧΟΣ", "", TITLE_ITEM));  //0


        lista.add(new ItemsRV("ΝΟΣΗΛ. ΑΞΙΟΛΟΓΟΓΗΣΗ", "aksiologisi", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΝΟΣΗΛ. ΠΑΡΑΜΒΑΣΕΙΣ\n ΕΚΤΙΜΙΣΗ ΑΠΟΤΕΛ.", "paremvaseis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΠΡΟΓΡΑΜΜΑΤΙΣΜΟΣ", "programmatismos", "", EDITTEXT_ITEM ,KEIMENO));

        lista.add(new ItemsRV("ΕΙΔΙΚΕΣ ΝΟΣΗΛΕΥΤΙΚΕΣ ΑΝΑΓΚΕΣ", "", TITLE_ITEM));  //4

        lista.add(new ItemsRV("Ψυχολογική υποστήριξη", "psixologiki_ipostiriksi", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Βοήθεια \n στη σίτιση", "sitisi", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Άλλη", "alli", "", EDITTEXT_ITEM,KEIMENO));

        // ΝΕΥΡΟΛΟΓΙΚΗ ΚΑΤΑΣΤΑΣΗ

        lista.add(new ItemsRV("ΝΕΥΡΟΛΟΓΙΚΗ ΚΑΤΑΣΤΑΣΗ", "", TITLE_ITEM));  //0

        lista.add(new ItemsRV("Κλίμακα Γλασκώβης(score)", "glaskovi_score", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));


        lista.add(new ItemsRV("Κλίμακα RAMSAY", "ramsay", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Είδος Εισαγωγής", "epafi_me_to_periballon","", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Κινήτ.Ανταπόκριση Δεξ.", "kinitiki_antapokrisi_deksia", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Κινήτ.Ανταπόκριση Α.", "kinitiki_antapokrisi_aristera", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Επίπεδο προσανατολισμού", "epipedo_prosanatolismou", "", EDITTEXT_ITEM,KEIMENO));

        lista.add(new ItemsRV("Έναρξη 24ωρης καταγραφής", "enarksi_katagrafis",false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Λήξη 3ωρης καταγραφής", "liksi_katagrafis", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Γλώσσα Επικοινωνίας", "glossa", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("ΝΠ_01_ΕΠΕΙΓΟΝ ΑΕΕ", "epeigon_aee", false, CHECKBOX_ITEM ));

        // ΚΑΡΔΙΑΓΓΕΙΑΚΟ

        lista.add(new ItemsRV("ΚΑΡΔΙΑΓΓΕΙΑΚΟ", "", TITLE_ITEM));  //11 ,19

        lista.add(new ItemsRV("Mόνιμος Βηματοδότης", "vimatodotis", "", EDITTEXT_ITEM,KEIMENO));
        lista.add(new ItemsRV("Μόνιμος Απινιδωτής", "apinidotis","", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Εμφύτευση", "emfiteysi", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Έλεγχος Προγραμματιστή", "elegxos_programmatisti", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Φέρει αντιθρομβωτικές κάλτσες Κ.Α", "antithrombotikes_kaltses", "", EDITTEXT_ITEM,KEIMENO));

        lista.add(new ItemsRV("Φέρει βάρος:Α-Δ μηρ.", "feri_baros","", EDITTEXT_ITEM,KEIMENO));
        lista.add(new ItemsRV("Φέρει ΜΥΝΧ:Α-Δ μηρ.", "feri_mynx", "", EDITTEXT_ITEM,KEIMENO));
        lista.add(new ItemsRV("ΣΦΥΓΜΟΙ:κερκιδική δεξιά", "kerkidiki_deksia", "", EDITTEXT_ITEM ,KEIMENO));
        lista.add(new ItemsRV("ΣΦΥΓΜΟΙ:κερκιδική αριστερά", "kerkidiki_aristera", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Οπίσθια κνημιαία δεξιά", "opisthia_knimiaia_deksia", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("Οπίσθια κνημιαία αριστερά", "opisthia_knimiaia_aristera","", EDITTEXT_ITEM,KEIMENO));
        lista.add(new ItemsRV("opisthia_knimiaia_deksia", "raxiaia_podos_deksia", "", EDITTEXT_ITEM,KEIMENO));
        lista.add(new ItemsRV("Ραχιαία ποδός αριστερά", "raxiaia_podos_aristera", "", EDITTEXT_ITEM ,KEIMENO));
        lista.add(new ItemsRV("Αιμάτωμα", "aimatoma", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Έλεγχος", "elegxos", "", EDITTEXT_ITEM, KEIMENO ));


        // ΑΝΑΠΝΕΥΣΤΙΚΟ

        lista.add(new ItemsRV("ΑΝΑΠΝΕΥΣΤΙΚΟ", "", TITLE_ITEM));  //27 , 35

        lista.add(new ItemsRV("Αναπνευστικοί ήχοι", "anapneystikoi_hxoi", "", EDITTEXT_ITEM, KEIMENO ));


        // ΕΚΚΡΙΣΕΙΣ

        lista.add(new ItemsRV("ΕΚΚΡΙΣΕΙΣ", "", TITLE_ITEM));  //29 ,37

        lista.add(new ItemsRV("πτύελα(χρώμα,ποσότητα)", "ptuela", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Πίεση CUFF", "piesi_cuff", "", EDITTEXT_ITEM, AKERAIOS ));
        lista.add(new ItemsRV("Πίεση (ώρα)", "piesi_ora", "", TEXTVIEW_CLOCK_TYPE ));
        lista.add(new ItemsRV("CM εξόδου Ενδ.Σ", "cm_eksodou", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("CM εξόδου (ώρα)", "cm_eksodou_ora", "", TEXTVIEW_CLOCK_TYPE ));



        // ΦΥΣΙΚΟΘΕΡΑΠΕΙΑ

        lista.add(new ItemsRV("ΦΥΣΙΚΟΘΕΡΑΠΕΙΑ", "", TITLE_ITEM));  //35 , 43

        lista.add(new ItemsRV("ΚΙΝΔΥΝΟΣ ΠΤΩΣΗΣ", "kindinos_ptosis", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("ΚΙΝΗΤΟΠΟΙΗΣΗ εις", "kinitopoihsh_eis", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Κάγκελα", "kagkela", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Περιχ.", "perix", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Ώρα", "ora", "", TEXTVIEW_CLOCK_TYPE));


        // ΚΑΤΑΣΤΑΣΗ ΣΩΜΑΤΟΣ

        lista.add(new ItemsRV("ΚΑΤΑΣΤΑΣΗ ΣΩΜΑΤΟΣ", "", TITLE_ITEM));  //41 ,49

        lista.add(new ItemsRV("Δέρμα", "derma", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Χρώμα", "xroma", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Φροντίδα", "frontida", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Στόματος", "frontida_stomatos", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Τραχειοστομία", "frontida_traxiostomia", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("Δεξια κόρη (μεγεθος mm)", "deksia_kori",  SPINNER_TYPE_NEW, Spinner_items_lists.getMegethosKoris()));
        lista.add(new ItemsRV("Αριστερή κόρη (μεγεθος mm)", "aristeri_kori",  SPINNER_TYPE_NEW, Spinner_items_lists.getMegethosKoris()));

        lista.add(new ItemsRV("Οίδημα", "oidima", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("τόπος", "oidima_topos", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("φροντίδα", "oidima_frontida", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Φίστουλα", "fistoula", SPINNER_TYPE_NEW, Spinner_items_lists.getRightLeft()));
        lista.add(new ItemsRV("Τεχνητό μέλος", "texnito_melos", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("Κατακλίσεις (δε Φύλλο Κατακλίσεων)", "katakliseis", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Λουτρό", "loutro", SPINNER_TYPE_NEW, Spinner_items_lists.getLoutroLista()));
        lista.add(new ItemsRV("έλξη", "elksi", SPINNER_TYPE_NEW, Spinner_items_lists.getRightLeft()));
        lista.add(new ItemsRV("Τεχνητή οδοντοστοιχία", "texniti_odontostoixia", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Άλλο", "allo", "", EDITTEXT_ITEM, KEIMENO ));

        // ΕΛΕΓΧΟΣ ΤΡΑΥΜΑΤΟΣ

        lista.add(new ItemsRV("ΕΛΕΓΧΟΣ ΤΡΑΥΜΑΤΟΣ", "", TITLE_ITEM));  //57 ,67

        lista.add(new ItemsRV("Χειρουργική αλλαγή στις", "xeirourgiki_allagi", "", TEXTVIEW_CLOCK_TYPE ));
        lista.add(new ItemsRV("Ιατρός", "DoctorID", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("1.τόπος", "topos_1", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("1.εμφάνιση", "emfanish1", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("1.αλλαγή επίδεσης", "allagh_epidesis1", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("2.τόπος", "topos_2", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("2.εμφάνιση", "emfanish2", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("2.αλλαγή επίδεσης", "allagh_epidesis2", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("3.τόπος", "topos_3", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("3.εμφάνιση", "emfanish3", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("3.αλλαγή επίδεσης", "allagh_epidesis3", "", EDITTEXT_ITEM, KEIMENO ));

        // ΠΑΡΟΧΕΤΕΥΣΕΙΣ

        lista.add(new ItemsRV("ΠΑΡΟΧΕΤΕΥΣΕΙΣ", "", TITLE_ITEM));  //69 ,79

        lista.add(new ItemsRV("Θωρακική", "thorakiki", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("-πίεση στήλης νερού", "piesi_stilis_nerou", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("-διαφυγή αέρα", "diafygi_aera", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Πλευρική δεξιά", "pleyritiki_deksia", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Πλευρική αριστερά", "pleyritiki_aristera", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("εμφάνιση", "pleyritikh_emfanish", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("αλλαγή επίδεσης", "pleyritikh_allagi_epidesis", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Άλλη,τόπος1", "allh_topos1", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Εμφάνιση1", "par_emfanish1", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Αλλαγή επίδεσης1", "par_allagi_epidesis1", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("Άλλη,τόπος2", "allh_topos2", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Εμφάνιση2", "par_emfanish2", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Αλλαγή επίδεσης2", "par_allagi_epidesis2", "", EDITTEXT_ITEM, KEIMENO ));


        // ΟΥΡΟΠΟΙΗΤΙΚΟ

        lista.add(new ItemsRV("ΟΥΡΟΠΟΙΗΤΙΚΟ", "", TITLE_ITEM));  //83 , 93

        lista.add(new ItemsRV("Τ.Ν[Braun/Prisma/\nPrismaflex]-Έναρξη", "t_n", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Αλλαγή φίλτρου", "allagh_filtrou", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("REF", "ref", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Λήξη", "liksi", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Συνεχίζει", "sinexizei", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("Πλύση κύστεως", "plisi_kisteos", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("-pH", "oura_ph", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Χρώμα ούρων", "xroma_ouron", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("-BLD", "oura_bld", "", EDITTEXT_ITEM, KEIMENO ));




        // ΓΑΣΤΡΕΝΤΕΡΙΚΟ

        lista.add(new ItemsRV("ΓΑΣΤΡΕΝΤΕΡΙΚΟ", "", TITLE_ITEM));  //93 , 103

        lista.add(new ItemsRV("Μέλαινα κένωση", "melena_kenosi", SPINNER_TYPE_NEW, Spinner_items_lists.getYesNo()));
        lista.add(new ItemsRV("Εφαρμογή Σωλήνα αερίων", "efarmogh_solina_aerion", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Υποκλισμός (είδος)", "ypoklismos", "", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Διαρροϊκή κένωση", "diarroiki_kenosi", SPINNER_TYPE_NEW, Spinner_items_lists.getYesNo()));
        lista.add(new ItemsRV("Κολοστομία->αλλαγή σάκου", "kolostomia", SPINNER_TYPE_NEW, Spinner_items_lists.getYesNo()));

        lista.add(new ItemsRV("Είδος κένωσης", "eidos_kenosis","", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Ήχοι:ΚΦ-Αυξημένοι-\n Μειωμένοι-Απόντες", "hxoi",  SPINNER_TYPE_NEW, Spinner_items_lists.getIxous()));
        lista.add(new ItemsRV("Κοιλιά:μαλακή-σκληρή-διογκωμένη-επώδυνη", "koilia",SPINNER_TYPE_NEW, Spinner_items_lists.getTipousKilias()));
        lista.add(new ItemsRV("Levin:έλεγχος θέσης ", "levin", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("Εμετοί", "emetoi","", EDITTEXT_ITEM, KEIMENO ));
        lista.add(new ItemsRV("Εφιδρώσεις", "efidroseis",  SPINNER_TYPE_NEW, Spinner_items_lists.getVathmousMeSin()));

        lista.add(new ItemsRV("Παροχέτευση", "paroxeteysi", "", EDITTEXT_ITEM, KEIMENO ));

        lista.add(new ItemsRV("Ph", "gastr_ph", "", EDITTEXT_ITEM, AKERAIOS ));
        lista.add(new ItemsRV("-BLD", "gastr_bld", "", EDITTEXT_ITEM, KEIMENO ));


        // ΔΙΑΙΤΑ

        lista.add(new ItemsRV("ΔΙΑΙΤΑ", "", TITLE_ITEM));  //108 , 118


        lista.add(new ItemsRV("22:00-06:00\nέλεγχος υπολλείματος-per levin.", "elegxos_upolleimatos", "", EDITTEXT_ITEM, KEIMENO ));




        return lista;
    }



    public static ArrayList<ItemsRV> getMed_Instr_trnasfer_cardio_meth() {
        ArrayList<ItemsRV> lista = new ArrayList<>();

        lista.add(new ItemsRV("Ιατρός", "doctorName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
      //  lista.add(new ItemsRV("Ιατρός", "doctorName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Ημέρα μεταφοράς", "Date", "", TEXTVIEW_TYPE));
        lista.add(new ItemsRV("Διάγνωση", "diagnosis", "", EDITTEXT_ITEM, KEIMENO));
     //   lista.add(new ItemsRV("ΕΠΕΜΒΑΣΗ / ΕΠΕΜΒΑΤΙΚΗ ΠΡΑΞΗ", "", TITLE_ITEM));  //3

        lista.add(new ItemsRV("Είδος", "type", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Καρδιοχειρουργός/\nΚαρδιολόγος", "doctor_surgerID",  SPINNER_TYPE_NEW, Str_queries.getKardiologous_Look_up()));
        lista.add(new ItemsRV("ΓΕΝΙΚΗ ΚΑΤΑΣΤΑΣΗ", "situation", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΑΛΛΕΡΓΙΕΣ", "allergies", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΖΩΤΙΚΑ ΣΗΜΕΙΑ", "vital_points", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΚΑΡΔΙΑΚΟΣ ΡΥΘΜΟΣ", "heart_beat", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΕΡΓΑΣΤΗΡΙΑΚΑ", "labs", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΠΡΟΣΛΑΜΒΑΝΟΜΕΝΑ", "participated", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΔΙΑΤΡΟΦΗ", "diet", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΦΥΣΙΚΟΘΕΡΑΠΕΙΑ", "physio", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΠΑΡΑΤΗΡΗΣΕΙΣ-ΠΡΟΒΛΗΜΑΤΑ", "remarks", "", EDITTEXT_ITEM, KEIMENO));

        return lista;
    }









    public static ArrayList<ItemsRV> getNosil_Istoriko_lista_new() {

       // ArrayList<ItemsRV> lista = new ArrayList<>();

        BasicList lista = new BasicList();
        lista.add(new ItemsRV("ΦΥΛΛΟ ΝΟΣΗΛΕΥΤΙΚΗΣ ΑΝΑΦΟΡΑΣ", "", TITLE_ITEM));  //0

        lista.add(new ItemsRV("Ημ/νία - ώρα", "Date", "", TEXTVIEW_TYPE));
        lista.add(new ItemsRV("Είδος Εισαγωγής", "Eidos_eisagogis", SPINNER_TYPE_NEW, Spinner_items_lists.getEidosEisagogisLista()));
        lista.add(new ItemsRV("Τρόπος μεταφοράς", "Tropos_metaforas", SPINNER_TYPE_NEW, Spinner_items_lists.getTroposMetaforasLista()));
        lista.add(new ItemsRV("Τις πληροφορίες δίδει", "info_provider", SPINNER_TYPE_NEW, Spinner_items_lists.getTisPliroforiesDideiLista()));

        lista.add(new ItemsRV("Άλλα νοσήματα", "Alla_nosimata", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Σακχαρώδης διαβήτης", "Sakx_diavitis", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Υπέρταση", "ipertasi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Α.Ε.Ε.", "aee", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ο.Ε.Μ.", "oem", false, CHECKBOX_ITEM));

        lista.add(new ItemsRV("Προηγ. νοσηλείες (ημ/νία, αιτία)", "Proigoumenes_nosileies","", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Αλλεργίες φαρμ.", "Allergies",  MULTI_TYPE_LOOKUP, "medicine"));
        lista.add(new ItemsRV("Αλλεργίες\nφαγητού", "allergies_fagitou", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Μέτρα λοιμώξεων (είδος)", "metra_loimoxeon", SPINNER_TYPE, getMetraLoimokseisLista()));

        lista.add(new ItemsRV("Κύρια συμπτώματα κατά την εισαγωγή", "simptomata_eisagogis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Αρτηριακή πίεση Σ", "piesi1", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Αρτηριακή πίεση Δ", "piesi2", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Σφύξεις", "sfixeis", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Θερμοκρασία", "thermokrasia", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Παλμική οξυμετρία", "oximetria", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Αναπνοές", "anapnoes", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Κλίμακα πόνου", "ponos", SPINNER_TYPE_NEW, Spinner_items_lists.getVathmoiPonou()));
        lista.add(new ItemsRV("Βάρος σώματος (kg)", "varos", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Ύψος (cm)", "ipsos", "", EDITTEXT_ITEM, DEKADIKOS));


        lista.add(new ItemsRV("ΑΙΣΘΗΤΗΡΙΑ ΟΡΓΑΝΑ", "", TITLE_ITEM)); //20

        lista.add(new ItemsRV("Ομιλία", "omilia", SPINNER_TYPE, getomiliaLista()));
        lista.add(new ItemsRV("Μιλάει ξένη γλώσσα", "xeni_glossa", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Ακοή", "akoi", SPINNER_TYPE, getAkoiLista()));
        lista.add(new ItemsRV("Ακουστικά", "akoustika", SPINNER_TYPE, getAkoustikaLista()));
        lista.add(new ItemsRV("Όραση", "orasi", SPINNER_TYPE, getOrasiLista()));
        lista.add(new ItemsRV("Δέρμα", "derma", MULTI_TYPE, Spinner_items_lists.getDermaLista()));
        lista.add(new ItemsRV("Τραυμα", "travma", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Έλκη κατάκλισης (σημείο/βαθμός)", "elki_kataklisis", "", EDITTEXT_ITEM, KEIMENO));


        lista.add(new ItemsRV("ΑΝΑΠΝΕΥΣΤΙΚΟ", "", TITLE_ITEM)); //29

        lista.add(new ItemsRV("Αναπνοή", "anapnoi", SPINNER_TYPE, getAnapnoiLista()));
        lista.add(new ItemsRV("Βήχας", "vixas", SPINNER_TYPE, getVixasLista()));

        lista.add(new ItemsRV("ΚΥΚΛΟΦΟΡΙΚΟ", "", TITLE_ITEM)); //32

        lista.add(new ItemsRV("Καρδιακός ρυθμός", "kardiakos_rithmos", SPINNER_TYPE, getKardiakosRithmosLista()));
        lista.add(new ItemsRV("Βηματοδότης / απινιδωτής", "vimatodotis", SPINNER_TYPE, Spinner_items_lists.getVimatodotis_apinidotis()));
        lista.add(new ItemsRV("Αίσθημα παλμών", "esthima_palmon", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Οιδήματα", "idimata", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Φλεβίτιδα", "flevitida", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Αναιμία", "anaimia", false, CHECKBOX_ITEM));
        //lista.add(new ItemsRV("Στοιχεία ΗΚΓ", "stoixeia_hkg", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("ΠΕΠΤΙΚΟ", "", TITLE_ITEM)); //41

        lista.add(new ItemsRV("Ελεύθερη δίαιτα", "dieta_eleftheri", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ειδική δίαιτα", "dieta_idiki", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Όρεξη", "orexi", SPINNER_TYPE, getOreksiLista()));
        lista.add(new ItemsRV("Δυσκολία στη μάσηση", "diskolia_sti_masisi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Οδοντοστοιχία", "odontostixia", SPINNER_TYPE, getOdontostoixiaLista()));

        lista.add(new ItemsRV("Ρινογαστ. σωλήνας", "is_rin_sol", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ριν. σωλ. Είδος", "rinogastrikos_solinas","", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new ItemsRV("Ριν. σωλ Νο", "rin_sol_no", "", EDIT_TEXT_TYPE, AKERAIOS));
        lista.add(new ItemsRV("Ριν. σωλ Ημ/νια", "rin_sol_date", "", TEXTVIEW_ITEM, TEXTVIEW_DATE_TYPE));

        lista.add(new ItemsRV("Ναυτία", "nautia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Εμετοί", "emetoi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Δυσκολία στην κατάποση", "diskolia_sti_kataposi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Γαστροστομία", "gastrostomia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Νηστιδοστομία", "nistidostomia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ημ/νία τελεύταιας κένωσης", "teleftea_kenosi", "", TEXTVIEW_TYPE));
        lista.add(new ItemsRV("Παχύ έντερο", "paxi_entero", SPINNER_TYPE, getPaxiEnteroLista()));
        lista.add(new ItemsRV("Μέλαινα κένωση", "melena_kenosi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Διάταση κοιλίας", "diatasi_kilias", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κολοστομία", "kolostomia", false, CHECKBOX_ITEM));


        lista.add(new ItemsRV("ΝΕΦΡΟΛΟΓΙΚΟ - ΟΥΡΟΠΟΙΗΤΙΚΟ", "", TITLE_ITEM)); //57

        lista.add(new ItemsRV("Συχνουρία", "sixnoouria", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Δυσουρία", "disouria", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Αιματουρία", "aimatouria", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ολιγουρία", "oligouria", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ανουρία", "Anouria", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Υπο αιμοκάθαρση", "ipo_emokatharsi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ουροκαθετήρας", "ourokathetiras", SPINNER_TYPE, getOurokathetirasLista()));
        lista.add(new ItemsRV("Ουρ. Είδος", "ourokathetiras_eidos", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Ουρ. Νο.", "ourokathetiras_no", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Ημ/νία τοποθέτησης", "ourokathetiras_date_topothetisi", "", TEXTVIEW_TYPE));
        lista.add(new ItemsRV("Φίστουλα", "fistoula", SPINNER_TYPE , Spinner_items_lists.getRightLeft()));

        lista.add(new ItemsRV("Καθετήρας\nΑιμοκάθαρσης", "kathetiras_aim", SPINNER_TYPE , Spinner_items_lists.getMonimoProsorino()));
        lista.add(new ItemsRV("Σημείο", "simeio", "" , EDIT_TEXT_TYPE,KEIMENO));
        lista.add(new ItemsRV("Καθ. Αιμ.\nΗμ/νία Τοποθέτησης",  "kath_aim_date_topothetisis", "", TEXTVIEW_ITEM, TEXTVIEW_DATE_TYPE));


//        lista.add(new ItemsRV("Φύστουλα δεξιά", "fistoula_deksia", false, CHECKBOX_ITEM));
//        lista.add(new ItemsRV("Φύστουλα αριστερά", "fistoula_aristera", false, CHECKBOX_ITEM));


        lista.add(new ItemsRV("ΜΥΟΣΚΕΛΕΤΙΚΟ", "", TITLE_ITEM)); //70

        lista.add(new ItemsRV("Αυτοεξυπηρέτηση", "autoexipiretisi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Βοήθεια ατ.υγιεινής", "voithia_igiini", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("ντύσιμο", "voithia_ntisimo", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("έγερση", "voithia_egersi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("πόνος", "ms_ponos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("πόνος που", "ms_ponos_pou", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("κλίμακα πόνου", "ms_ponos_klimaka", SPINNER_TYPE_NEW,Spinner_items_lists.getVathmoiPonou()));
        lista.add(new ItemsRV("Βάδιση", "vadisi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Με Π", "me_pi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Με πατερίτσες", "me_pateritses", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Με μπαστούνι", "me_mpasouni", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Δεν μετακινείται", "den_metakinite", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κάταγμα", "katagma", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κάταγμα πού", "katagma_pou", "", EDITTEXT_ITEM, KEIMENO));


        lista.add(new ItemsRV("ΨΥΧΟΔΙΑΝΟΗΤΙΚΗ ΚΑΤΑΣΤΑΣΗ", "", TITLE_ITEM)); //85

        lista.add(new ItemsRV("Αισιόδοξος", "aisioodoxos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ανήσυχος", "anisixos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Φοβισμένος", "fovismenos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Απομονωμένος", "apomonomenos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ενήμερος για τη νόσο", "enimeros_gia_tin_noso", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Αγνοεί τη σοβαρότητα", "agnoi_tin_sovatorita", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Φόβος θανάτου", "fovos_thanatou", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Επικοινωνία", "epikinonia", SPINNER_TYPE, getEpikoinoniaLista()));


        lista.add(new ItemsRV("ΝΕΥΡΟΛΟΓΙΚΟ", "", TITLE_ITEM)); //94

        lista.add(new ItemsRV("Ημιπληγία", "imipligia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Παράλυση", "paralisi", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Επιληπτικές κρίσεις", "epiliptikes_kriseis", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Λιποθυμία", "lipothimia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Αδυναμία", "adinamia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Μούδιασμα", "moudiasma", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Τρόμος", "tromos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κεφαλαλγία", "kefalalgia", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ζάλη", "zali", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Προσανατολισμένος (τόπος, χρόνος, πρόσωπα)", "psosanatolismenos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κώμα", "koma", false, CHECKBOX_ITEM));


        lista.add(new ItemsRV("ΑΤΟΜΙΚΕΣ ΣΥΝΗΘΕΙΕΣ", "", TITLE_ITEM)); //106

        lista.add(new ItemsRV("Κάπνισμα", "kapnisma", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Χρήση οινοπνεύματος", "xrisi_inopnevmatos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Άλλες", "alles_sinithies", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Ώρες ύπνου το 24ωρο", "ores_ipnou", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Ζει μόνος", "zei_monos", false, CHECKBOX_ITEM));


        lista.add(new ItemsRV("ΦΑΡΜΑΚΑ ΠΟΥ ΛΑΜΒΑΝΕΙ", "", TITLE_ITEM)); //112

        lista.add(new ItemsRV("Φέρει δικά του φάρμακα", "dika_tou_farmaka", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Φέρει δικές του εξετάσεις", "dikes_tou_exetaseis", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Αριστερόχειρος", "aristeroxeiros", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Δεξιόχειρος", "dexioxeiros", false, CHECKBOX_ITEM));


        lista.add(new ItemsRV("ΝΟΣΗΛΕΥΤΙΚΗ ΕΚΤΙΜΗΣΗ", "", TITLE_ITEM)); //117

        lista.add(new ItemsRV("Νοσηλευτικές διαγνώσεις", "nosileftikes_diagnoseis", "", EDITTEXT_ITEM, KEIMENO));


        return lista.getList();
    }

    // ΛΙΣΤΕΣ ΓΙΑ ΤΟ ΝΟΣΙΛΕΥΤΙΚΟ ΙΣΤΟΡΙΚΟ


    public static ArrayList<String> getEidosEisagogisLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Τακτική");
        lista.add("Επείγουσα");

        return lista;
    }


    public static ArrayList<String> getTroposMetaforasLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Περιπατητικός");
        lista.add("Τροχήλατο κάθισμα");
        lista.add("Φορείο");
        lista.add("Σανίδα/Φορείο");

        return lista;
    }


    public static ArrayList<String> getTisPliroforiesDideiLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Ο ασθενής ");
        lista.add("Η οικογένεια");
        lista.add("Ο Συνοδός");
        lista.add("Ο ασθενής / Συνοδός");


        return lista;
    }


    public static ArrayList<String> getomiliaLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Καλή");
        lista.add("Βράγχος");
        lista.add("Δεν μπορεί να μιλήσει");


        return lista;
    }


    public static ArrayList<String> getAkoiLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Καλή");
        lista.add("Βαρηκοϊα");
        lista.add("Ακουστικά");
        lista.add("Κώφωση");


        return lista;
    }


    public static ArrayList<String> getAkoustikaLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Δεξιά");
        lista.add("Αριστερά");
        lista.add("Άμφω");


        return lista;
    }

    public static ArrayList<String> getOrasiLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Καλή");
        lista.add("Γυαλιά");
        lista.add("Φακοί επαφής");
        lista.add("Τύφλωση ΑΡ-ΔΕ");
        lista.add("Τύφλωση ΑΡ");
        lista.add("Τύφλωση ΔΕ");


        return lista;
    }


    public static ArrayList<String> getDermaLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Φυσιολογικό");
        lista.add("Κυανωτικό");
        lista.add("Ικτερικό");
        lista.add("Εξάνθημα");
        lista.add("Εκδορές");
        lista.add("Εκχυμώσεις");
        lista.add("Πετέχειες");


        return lista;
    }





    public static ArrayList<String> getMetraLoimokseisLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Πολυανθεκτικά");
        lista.add("ουδετεροπερονικου και ανοσοκατεσταλμένου");
        lista.add("Ουδετεροπεπτικού");
        lista.add("Μέτρα επαφής");
        lista.add("Σταγονιδίων");
        lista.add("MDR");


        return lista;
    }


    public static ArrayList<String> getAnapnoiLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Κανονική");
        lista.add("Δύσπνοια");

        return lista;
    }

    public static ArrayList<String> getOdontostoixiaLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Πάνω");
        lista.add("Κάτω");
        lista.add("Πανω/Κατω");
        lista.add("Όχι");


        return lista;
    }


    public static ArrayList<String> getVixasLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Δεν βήχει");
        lista.add("Με απόχρεμψη");
        lista.add("Χωρίς απόχρεμψη");

        return lista;
    }


    public static ArrayList<String> getOurokathetirasLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Ναι");
        lista.add("Όχι");

        return lista;
    }


    public static ArrayList<String> getKardiakosRithmosLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Ρυθμικός");
        lista.add("Άρρυθμος");
        lista.add("βημαδοτούμενος ρυθμός");

        return lista;
    }


    public static ArrayList<String> getOreksiLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Καλή");
        lista.add("Πτωχή");

        return lista;
    }


    public static ArrayList<String> getPaxiEnteroLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Φυσικές κενώσεις");
        lista.add("Δυσκοιλιότητα");
        lista.add("Διάρροιες");


        return lista;
    }


    public static ArrayList<String> getEpikoinoniaLista() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Προφορική");
        lista.add("Γραπτή");
        lista.add("Με νοήματα");
        lista.add("Καμμία");

        return lista;
    }


    public static ArrayList<ItemsRV> getEktimisiEpigonton_PERSON() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

        //   ΕΠΕΙΓΟΥΣΑ ΠΑΡΕΜΒΑΣΗ

        lista.add(new ItemsRV("ΕΠΕΙΓΟΥΣΑ ΠΑΡΕΜΒΑΣΗ", "", 1));  //0

        lista.add(new ItemsRV("Άπνοια", false, 4));
        lista.add(new ItemsRV("Διασωληνωμένος ασθενής", false, 4));
        lista.add(new ItemsRV("Αναπνευστική δυσχέρεια "
                + "(αναπνοές πάνω από 30/λεπτό, "
                + "εισπνευστικός συριγμός, "
                + "κυάνωση άκρων, "
                + "εκφωνεί με δυσκολία απλές λέξεις)", false, 4));
        lista.add(new ItemsRV("Έγκαυμα προσώπου, εισπνοή καπνού", false, 4));

        lista.add(new ItemsRV("Απουσία σφίξεων στις κερκιδικές αρτηρίες αμφω", false, 4));
        lista.add(new ItemsRV("Κώμα (δεν εκτελεί απλές οδηγίες)", false, 4));
        lista.add(new ItemsRV("Επιληπτικοί σπασμοί", false, 4));
        lista.add(new ItemsRV("Υπογλυκαιμία (GLU < 60 mg/dl)", false, 4));

        lista.add(new ItemsRV("ηλητηρίαση ή εισπνοή πτητικών υγρών (νέφτι, βενζίνη, κλπ)", false, 4));
        lista.add(new ItemsRV("Ανεξέλεγκτη αιμορραγία", false, 4));


        // ΑΜΕΣΗ ΠΑΡΕΜΒΑΣΗ
        lista.add(new ItemsRV("ΑΜΕΣΗ ΠΑΡΕΜΒΑΣΗ", "", 1));  //11

        lista.add(new ItemsRV("Τροχαίο (ταχύτητες πάνω από 40χλμ/ώρα), πτώση από ύψος (πάνω απο 3μ)", false, 4));
        lista.add(new ItemsRV("Ανοικτά τραύματα με ορατή μεγάλη αιμορραγία", false, 4));
        lista.add(new ItemsRV("Τραύμα από αιχμηρό αντικείμενο (μαχαίρι, κλπ)", false, 4));
        lista.add(new ItemsRV("Έγκαυμα ολικού πάχους (χημικό, θερμικό) πάνω από το 20% της " +
                "συνολικής επιφάνειας του σώματος ή έγκαυμα στο οσχέο", false, 4));

        lista.add(new ItemsRV("Κυκλοτερές έγκαυμα άκρων, κορμού", false, 4));
        lista.add(new ItemsRV("Οξεία δύσπνοια με SaO2<90%", false, 4));
        lista.add(new ItemsRV("Πόνος στο στήθος ή μεταξύ των ωμοπλατών", false, 4));
        lista.add(new ItemsRV("Εστιακή συμπτωματολογία συμβατή με αγγειακό επεισόδιο", false, 4));

        lista.add(new ItemsRV("Ηλεκτροπληξία", false, 4));
        lista.add(new ItemsRV("Ανοικτό κάταγμα ή κάταγμα με παραμόρφωση του μέλους", false, 4));
        lista.add(new ItemsRV("Μεταβαλλόμενο επίπεδο συνείδησης - συγχυτικός ασθενής", false, 4));
        lista.add(new ItemsRV("Αιματέμεση", false, 4));
        lista.add(new ItemsRV("Αποβολή αιμοφυρτών πτυελών ή καθαρού αίματος με τον βήχα", false, 4));
        lista.add(new ItemsRV("Φαρμακευτική δηλητηρίαση", false, 4));
        lista.add(new ItemsRV("Εξάρθρωση μεγάλων αρθρώσεων", false, 4));
        lista.add(new ItemsRV("Πόνος πάνω από 7/10", false, 4));

        lista.add(new ItemsRV("Επιθετικότητα, ψυχωσικά συμπτώματα, αυτοκτονικός ιδεασμός", false, 4));
        lista.add(new ItemsRV("Ασφυγμο ψυχρό άκρο", false, 4));
        lista.add(new ItemsRV("Εκτεταμένο οίδημα άκρου", false, 4));
        lista.add(new ItemsRV("Τραυματισμοί ματιών", false, 4));
        lista.add(new ItemsRV("Διαβητικός με σάκχαρο >200 και κετόνες ούρων", false, 4));
        lista.add(new ItemsRV("Πυρετός με ανοσοκατεσταλμένο ασθενή", false, 4));
        lista.add(new ItemsRV("Κεφαλαλγία αιφνίδιας έναρξης", false, 4));
        lista.add(new ItemsRV("Κοιλιακό άλγος ή τραυματισμός στην κοιλιά σε έγκυο", false, 4));

//   ΕΣΠΕΥΣΜΕΝΗ ΠΑΡΕΜΒΑΣΗ
        lista.add(new ItemsRV("ΕΣΠΕΥΣΜΕΝΗ ΠΑΡΕΜΒΑΣΗ", "", 1));  //36

        lista.add(new ItemsRV("Ελεγχόμενη αιμοραγία", false, 4));
        lista.add(new ItemsRV("Εξάρθρωση δακτύλων", false, 4));
        lista.add(new ItemsRV("Κλειστό κάταγμα χωρίς παραμόρφωση ή σημαντική διόγκωση του σκέλους", false, 4));
        lista.add(new ItemsRV("Έγκαυμα ολικού πάχους κάτω από το 20% της συνολικής επιφάνειας του σωματός", false, 4));
        lista.add(new ItemsRV("Κοιλιακό άλγος", false, 4));
        lista.add(new ItemsRV("Σάκχαρο αίματος >300 mg/dl χωρίς κετόνες", false, 4));
        lista.add(new ItemsRV("Τραυματισμός εγκύου (πλην της περιοχής της κοιλίας)", false, 4));
        lista.add(new ItemsRV("Απώλεια αίματος από τον κόλπο σε έγκυο", false, 4));

// συνολο 44 -3(position) οι τιτλοι
        // 45 - 3 synolika pedia
        return lista;

    }


    public static ArrayList<ItemsRV> getSNEL_LISTA() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

        lista.add(new ItemsRV("ΑΠΟ ΤΟ ΤΜΗΜΑ ΝΟΣΗΛΕΙΑΣ", "", 0));  //0

        lista.add(new ItemsRV("Ημ/νία έναρξης εφαρμ. μέτρων  ελέγχου λοιμώξεων", "date_elegxos_limokseon", "", TEXTVIEW_TYPE));
        lista.add(new ItemsRV("Εντελλών ιατρός", "entellon_iatros", "", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new ItemsRV("Είδος μέτρων\nκείμενο", "eidos_metron", "", EDIT_TEXT_TYPE));
        lista.add(new ItemsRV("Είδος μέτρων ", "eidos_metronIDs",  MULTI_TYPE ,Spinner_items_lists.getEidiMetronEpafis()));

        lista.add(new ItemsRV("Ειδος θετ.καλλιεργειας ή είδος θετ. ιολογιου ελεγχόυ και ημν. αποστολής", "eidos_thet_kalliergias", "", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new ItemsRV("Αποτέλεσμα καλλιεργειας: Μικρόβιο & ημ/νία παραλαβής τελικού αποτελέσματος", "apotelesma_kalliergias", "", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new ItemsRV("Ενημέρωση συνοδών", "enimerosi_sinodon", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Απομόνωση", "apomonosi", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Τοποθέτηση Hospital box", "topothetisi_box", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Λουτρό με hibitane", "loutro", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Κόκκινο κυτίο αιχμηρών", "kokkino", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Κύκλωμα κλειστής αναρροφησης", "kikloma", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Χρήση μάσκας(Αερογενων προφυλ.)", "xrisi_maskas", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Άλλες", "alles1", false, CHECKBOX_TYPE));

        lista.add(new ItemsRV("ΣΤΟ ΤΜΗΜΑ ΝΟΣΗΛΕΙΑΣ", "", 0));  //14

        lista.add(new ItemsRV("Ημ/νία αρσης μέτρων - Εντελλών ιατρός", "date_arsis_metron", "", 1));
        lista.add(new ItemsRV("Επικύρωση άρσης μέτρων: Ημ/νία καλλιεργειας/ εργασ. δείκτες", "epikirosi", "", 2));
        lista.add(new ItemsRV("Τοποθέτηση λάμπας 12Ω - 24Ω", "topothetisi_lampas", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Ενισχυμένο καθάρισμα", "enisximeno_katharisma", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Καθάρισμα τοίχων", "katharisma_toixon", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Αφαίρεση κουρτινών", "afairesi_kourtinon", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Καθάρισμα  clostridium \ndifficile (Klorkleen)", "kathararisma", false, CHECKBOX_TYPE));
        lista.add(new ItemsRV("Άλλες", "alles2", false, CHECKBOX_TYPE));
        //22


        //SINOLO 23 POSITION 22
        //23 - 2 TITLOI


        return lista;
    }




    public static ArrayList<ItemsRV> getImerologio_katagr_diatrofis() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

        lista.add(new ItemsRV("Γεύμα", "daypartID", SPINNER_ITEM_NEW, Spinner_items_lists.get_geuma()));
        lista.add(new ItemsRV("Ημέρα", "DayID",  SPINNER_ITEM_NEW, "DaysWeek"));
        lista.add(new ItemsRV("Δίετα", "dieta", "", EDIT_TEXT_TYPE, KEIMENO));


        return lista;
    }





    public static ArrayList<TableViewItem>  getSNEL_Sigkentrotika() {

        ArrayList<TableViewItem>  lista = new ArrayList<>();

        lista.add(new TableViewItem("Ημ/νία \n εισαγωγής","date", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία έναρξης εφαρμ. μέτρων  \nελέγχου λοιμώξεων", "date_elegxos_limokseon", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Εντελλών \nιατρός", "entellon_iatros",  TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Είδος μέτρων\nκείμενο", "eidos_metron",  TEXTVIEW_ITEM_READ_ONLY_VALUE , TABLE_NO_TYPE));
        lista.add(new TableViewItem("Είδος μέτρων ", "eidos_metronIDs",  MULTI_TYPE ,Spinner_items_lists.getEidiMetronEpafis()));

        lista.add(new TableViewItem("Ειδος θετ.καλλιεργειας ή είδος θετ.\nιολογιου ελεγχόυ και ημν. αποστολής", "eidos_thet_kalliergias", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Αποτέλεσμα καλλιεργειας: Μικρόβιο & \nημ/νία παραλαβής τελικού αποτελέσματος", "apotelesma_kalliergias",  TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ενημέρωση \nσυνοδών", "enimerosi_sinodon", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Απομόνωση", "apomonosi", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Τοποθέτηση\n Hospital box", "topothetisi_box", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Λουτρό με hibitane", "loutro", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Κόκκινο κυτίο αιχμηρών", "kokkino", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Κύκλωμα κλειστής αναρροφησης", "kikloma", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Χρήση μάσκας(Αερογενων προφυλ.)", "xrisi_maskas", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Άλλες", "alles1", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Ημ/νία αρσης μέτρων -\n Εντελλών ιατρός", "date_arsis_metron", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Επικύρωση άρσης μέτρων:\n Ημ/νία καλλιεργειας/ εργασ. δείκτες", "epikirosi", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Τοποθέτηση \nλάμπας 12Ω - 24Ω", "topothetisi_lampas", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ενισχυμένο\n καθάρισμα", "enisximeno_katharisma", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Καθάρισμα\n τοίχων", "katharisma_toixon", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Αφαίρεση \nκουρτινών", "afairesi_kourtinon", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Καθάρισμα clostridium \ndifficile (Klorkleen)", "kathararisma", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Άλλες", "alles2", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));


        return lista;
    }


    public static ArrayList<ItemsRV> getMedicalInsLista() {

        BasicList lista = new BasicList();

        lista.add(new ItemsRV("Μήνας/Έτος", "Month", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Ιατρός", "doctorid", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Είδος αιμοκάθαρσης", "eidosName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Συχνότητα αιμοκ.\nανά εβδομάδα", "sixnotita_aim", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Διάρκεια", "durationName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE ));
        lista.add(new ItemsRV("Φίλτρο", "filName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Αντιπηκτική αγωγή", "agogiName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Αρχική δόση αντιπ. αγωγ.", "agogiDosisName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Δόση συντήρησης", "dosiSintirisis", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("χρόνος διακοπής\nαντλίας  ηπαρίνης", "xronosDiakopisIparinis", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Αγγ. Προσπέλαση (τύπος)", "agg_prospelasi", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Αγγ. Προσπέλαση (θέση)", "ag_pros_thesi", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Αγγ. Προσ. (τύπος κείμενο)", "agg_prospelasi_text", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));



        lista.add(new ItemsRV("Ροή αίματος (ml/min)", "roi_aima", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Ροή διαλύματος (ml/min)", "roi_dialima", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Διάλυμα - είδος", "dialeimaName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Νάτριο (mmol/L)", "agogimotita", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Διττανθρακικά (meq/l)", "Dittanthrakika", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Θερμοκρασία", "temparture", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Μέγιστος Ρυθμός\n Αφυδάτωσης (ml/h)", "rithmos_afidatosis", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Ξηρό βάρος (kg)", "ksiro_varos", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Epoetin", "epoName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Fe", "fe_Name", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Vit B ", "vitB_Name", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("L-carnitine", "carnitine_Name", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Vitamin D", "vitD_Name", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Etelalcetide", "etel_Name", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Αλλεργίες", "allergiesMeds", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Φαρμ. αγωγή", "farmAgogiMeds", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Εμβόλιο ηπ.Β", "embolio_b", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Αντιγριπ. εμβόλιο", "embolio_antiFlu", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Εμβόλιο πνευμονιόκοκου", "embolio_pneum", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Εμβόλιο covid 19", "embolio_covid", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Γενικές οδηγίες", "genikes_odigies", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));


        return lista.getList();


    }


    public static ArrayList<ItemsRV> getStathersMetriseisLista() {

        BasicList lista = new BasicList();

        // lista.add(new ItemsRV("Ημ/νία:", "schedule_time_start","" , TEXTVIEW_TYPE ));

        lista.add(new ItemsRV("Είδος αιμοκάθαρσης", "eidos_aimID", SPINNER_ITEM_NEW, "Nursing_items_med_instr_eidos_aim").setcompareColValue("med_eidos_aim_name"));

        lista.add(new ItemsRV("Προγρ. ώρα έναρξης", "schedule_time_start", "", TEXTVIEW_CLOCK_TYPE));

        lista.add(new ItemsRV("Ώρα έναρξης:", "time_start", "", TEXTVIEW_CLOCK_TYPE));
        lista.add(new ItemsRV("Διάρκεια:", "duration_aim_ID", SPINNER_ITEM_NEW, Spinner_items_lists.getDuration_aim()).setcompareColValue("duration"));
        lista.add(new ItemsRV("Αρχικό βάρος (kg):", "arxiko_varos", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Ξηρό βάρος (kg):", "xiro_varos", "", EDITTEXT_ITEM, DEKADIKOS).setcompareColValue("ksiro_varos"));
        lista.add(new ItemsRV("Ένδειξη υπερ/τος Μηχαν.τ.ν. (UF)", "UF", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Διαφορά βάρους(kg):", "diafora_varous", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));


        lista.add(new ItemsRV("Βάρος εξόδου (kg):", "varos_exodou", "", EDITTEXT_ITEM, DEKADIKOS));


        lista.add(new ItemsRV("Θερμοκ. διαλύματος (°C)", "therm_dialimatos", "", EDITTEXT_ITEM, KEIMENO).setcompareColValue("temparture"));
        lista.add(new ItemsRV("Kt/V", "Kt_V", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("fistula", "fistulaID",  SPINNER_ITEM_NEW, "Nursing_items_statheres_fistoula_and_mosxeuma"));
        lista.add(new ItemsRV("Μόσχευμα", "mosxeumaID", SPINNER_ITEM_NEW, "Nursing_items_statheres_fistoula_and_mosxeuma"));
        lista.add(new ItemsRV("Βελόνες", "velonesID", SPINNER_ITEM_NEW, "Nursing_items_statheres_metr_velones"));

        lista.add(new ItemsRV("SN-Y LINE ADAPTOR - Συνδετικό", "sindetiko", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Κεντρ. φλεβ.καθετηρας μονιμος", "flev_kathtiras_monimos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κεντρ. φλεβ.καθετηρας προσωρινος", "flev_kathtiras_prosorinos", false, CHECKBOX_ITEM));

        lista.add(new ItemsRV("Κεντρ. φλεβ.καθετηρας_κειμενο", "flev_kathtiras_text", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Χωρητ. ηπαρινισμού σκελών Α", "skelon_A", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Χωρητ. ηπαρινισμού σκελών Φ", "skelon_f", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Διάλυμα", "dialimaID", SPINNER_ITEM_NEW, "Nursing_items_med_instr_dialima").setcompareColValue("dialima"));
        lista.add(new ItemsRV("Φίλτρο", "filtroID",  SPINNER_ITEM_NEW, "Nursing_items_med_instr_filter").setcompareColValue("filter"));
        lista.add(new ItemsRV("LOT Φίλτρου", "lot_filtrou", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Αντιπ. αγωγή", "antip_agogi", SPINNER_ITEM_NEW, "Nursing_items_med_instr_agogi").setcompareColValue("antip_agogiID"));
        lista.add(new ItemsRV("Αντιπ. αγωγή\n δόση", "agogiDosisID", SPINNER_ITEM_NEW, "Med_instr_antipiktiki_dosh").setcompareColValue("med_antip_agogiID"));

        return lista.getList();


    }

    public static String getAkra_by_id(int id){

        Map<Integer,String> lista = new HashMap();

        lista.put(0,"");
        lista.put(1,"Αριστερό άνω άκρο");
        lista.put(2,"Δεξί άνω άκρο");
        lista.put(3,"Αριστερό κάτω άκρο");
        lista.put(4,"Δεξί κάτω άκρο");
        lista.put(5,"Άλλο");


        return lista.get(id);
    }


    public static ArrayList<ItemsRV> getSinexeisMetriseisLista() {

        BasicList lista = new BasicList();

        lista.add(new ItemsRV("Ημ/νία και ώρα", "Date", "", TEXTVIEW_DATETIME_TYPE));
        lista.add(new ItemsRV("Συστολική πίεση (mmHg)", "sis_piesi", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Διαστολική πίεση (mmHg)", "dias_piesi", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        //lista.add(new ItemsRV("Αρτηριακή πίεση:", "art_piesi","" , EDITTEXT_ITEM,DEKADIKOS));
        lista.add(new ItemsRV("Σφύξεις:", "sfikseis", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Θερμοκρασία (°C)", "thermokrasia", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("SPO2 (%)", "spo2", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Ρυθμός ροή αντλίας (ml/min):", "roi", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Πίεση φλεβ. γραμμής(mmHg):", "piesi_flev", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Πίεση αρτ. γραμμής(mmHg):", "piesi_art", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Ρυθμός υπερδιηθησης ανά ώρα(U.F.) (l/h):", "iperd", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Αγωγιμότητα (mmol/L)", "agogimotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("HCO 3 (ms/cm):", "hco3", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Μετρήσεις-Παρεμβάσεις:", "paremvaseis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Παρατηρήσεις:", "paratiriseis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Εγχύσεις - Φάρμακα", "egxiseis", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Epoetin", "epo", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("VIT B", "vit_b", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("carnitine", "carnitine", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("VIT D", "vit_d", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Fe", "fe", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Etelalcetide", "etelalcetide", false, CHECKBOX_ITEM));




        return lista.getList();

    }


    public static ArrayList<ItemsRV> getKlimakaGlaskovis() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

        lista.add(new ItemsRV("Α. ΑΝΟΙΓΜΑ ΜΑΤΙΩΝ", "mati", SPINNER_TYPE_NEW, Spinner_items_lists.getAnoigmaMation()));
        lista.add(new ItemsRV("Β. ΟΜΙΛΙΑ - ΔΙΑΣΩΛΗΝΟΜΕΝΟΣ", "omilia", SPINNER_TYPE_NEW, Spinner_items_lists.getOmilia()));
        lista.add(new ItemsRV("Γ. ΚΙΝΗΤΙΚΗ ΑΝΤΙΔΡΑΣΗ", "kinisi", SPINNER_TYPE_NEW, Spinner_items_lists.getKinitikiAntidrasi()));


        return lista;
    }


    public static ArrayList<String> get3HoursLista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("00:00");
        list.add("03:00");
        list.add("06:00");
        list.add("09:00");
        list.add("12:00");
        list.add("15:00");
        list.add("18:00");
        list.add("21:00");


        return list;
    }


    public static ArrayList<String> get24HoursLista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("07:00");
        list.add("08:00");
        list.add("09:00");
        list.add("10:00");
        list.add("11:00");
        list.add("12:00");
        list.add("13:00");
        list.add("14:00");
        list.add("15:00");
        list.add("16:00");
        list.add("17:00");
        list.add("18:00");
        list.add("19:00");
        list.add("20:00");
        list.add("21:00");
        list.add("22:00");
        list.add("23:00");
        list.add("00:00");
        list.add("01:00");
        list.add("02:00");
        list.add("03:00");
        list.add("04:00");
        list.add("05:00");
        list.add("06:00");

        return list;
    }


    public static ArrayList<String> get24Hours_Meth_Lista() {

        ArrayList<String> list = new ArrayList<>();

        list.add("08:00");
        list.add("09:00");
        list.add("10:00");
        list.add("11:00");
        list.add("12:00");
        list.add("13:00");
        list.add("14:00");
        list.add("15:00");
        list.add("16:00");
        list.add("17:00");
        list.add("18:00");
        list.add("19:00");
        list.add("20:00");
        list.add("21:00");
        list.add("22:00");
        list.add("23:00");
        list.add("00:00");
        list.add("01:00");
        list.add("02:00");
        list.add("03:00");
        list.add("04:00");
        list.add("05:00");
        list.add("06:00");
        list.add("07:00");

        return list;
    }

    public static String get3hoursID(String name) {

        String id;

        Map<String, Integer> map = new HashMap<>();

        map.put("00:00", 300);
        map.put("03:00", 303);
        map.put("06:00", 306);
        map.put("09:00", 309);
        map.put("12:00", 312);
        map.put("15:00", 315);
        map.put("18:00", 318);
        map.put("21:00", 321);

        id = map.get(name).toString();

        return id;
    }


    public static String get24hoursID(String name) {

        String id;

        Map<String, Integer> map = new HashMap<>();

        map.put("07:00", 7);
        map.put("08:00", 8);
        map.put("09:00", 9);
        map.put("10:00", 10);
        map.put("11:00", 11);
        map.put("12:00", 12);
        map.put("13:00", 13);
        map.put("14:00", 14);
        map.put("15:00", 15);
        map.put("16:00", 16);
        map.put("17:00", 17);
        map.put("18:00", 18);
        map.put("19:00", 19);
        map.put("20:00", 20);
        map.put("21:00", 21);
        map.put("22:00", 22);
        map.put("23:00", 23);
        map.put("00:00", 24);
        map.put("01:00", 1);
        map.put("02:00", 2);
        map.put("03:00", 3);
        map.put("04:00", 4);
        map.put("05:00", 5);
        map.put("06:00", 6);

        id = map.get(name).toString();

        return id;
    }


    public static ArrayList<ItemsRV> getZotika24oroAnaOraLista() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

        lista.add(new ItemsRV("Συστολική", "sistoliki_piesi", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Διαστολική", "diastoliki_piesi", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Καρδ. ρυθμός", "rithmos", SPINNER_TYPE_NEW, Spinner_items_lists.getKardiakosRithmos()));
        lista.add(new ItemsRV("Σφύξεις", "sfixeis", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Θερμοκρασία (°C)", "thermokrasia", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Κλίμακα πόνου", "ponos",  SPINNER_TYPE_NEW, Spinner_items_lists.getVathmoiPonou()));
        lista.add(new ItemsRV("SPO 2", "spo2", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Stick glu", "stick_glu", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Αναπνοές", "anapnoes", "", EDITTEXT_ITEM, AKERAIOS));


        return lista;
    }


    public static ArrayList getZotika24oroAnaOraLista_Meth( boolean forRV) {

        BasicList lista = new BasicList();
        if (!forRV){
            lista.add(new ItemsRV("ID", "ID", "", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
            lista.add(new ItemsRV("UserID","UserID", "", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
            lista.add(new ItemsRV("ΗΜ/ΝΙΑ / ΩΡΑ","Date", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
            lista.add(new ItemsRV("Νοσηλευτής","username", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
            lista.add(new ItemsRV("Βάρδια","Watch",  SPINNER_TYPE_NEW, Spinner_items_lists.getZotikaVardies()));
        }

        lista.add(new ItemsRV("V Συστολική\n Αρτ. Πίεση (mmHg)", "sistoliki_artiriaki_piesi", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Λ Διαστολική\n Αρτ. Πίεση (mmHg)", "diastoliki_artiriaki_piesi", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Σφύξεις (bpm)", "sfiksis", "", EDITTEXT_ITEM, AKERAIOS));

        lista.add(new ItemsRV("ΘΕΡΜΟΚΡΑΣΙΑ (°C)", "thermokrasia", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("ΠΟΝΟΣ", "ponos",  SPINNER_TYPE_NEW, Spinner_items_lists.getVathmoiPonou()));
        lista.add(new ItemsRV("Καρδ.Ρυθμός \n(συχνότητα)", "rithmos_bimatodotis", SPINNER_TYPE_NEW, Spinner_items_lists.getKardiakosRithmos()));

        lista.add(new ItemsRV("Βηματοδότης", "vimatodotis", SPINNER_TYPE_NEW, Spinner_items_lists.getMonimoProsorino()));
        lista.add(new ItemsRV("Συνθήκες\n Βηματοδότης", "sinthikes_vimatodoti", "", EDITTEXT_ITEM, KEIMENO));


        lista.add(new ItemsRV("GLU", "glu", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("CVP (mgHg)", "cvp", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Δεξιά κόρη \n(μέγεθος mm)", "deksia_kori",  SPINNER_TYPE_NEW, Spinner_items_lists.getMegethosKoris()).setInfo_image(R.drawable.megethos_koris));
        lista.add(new ItemsRV("Δεξιά κόρη + -", "deksia_symbol",  SPINNER_TYPE_NEW, Spinner_items_lists.getSymbolsKoris()));
        lista.add(new ItemsRV("Αριστερή κόρη \n(μέγεθος mm)", "aristeri_kori",  SPINNER_TYPE_NEW, Spinner_items_lists.getMegethosKoris()).setInfo_image(R.drawable.megethos_koris));
        lista.add(new ItemsRV("Αριστερή κόρη + -", "aristeri_symbol",  SPINNER_TYPE_NEW, Spinner_items_lists.getSymbolsKoris()));

        lista.add(new ItemsRV("IABP", "iabp", SPINNER_TYPE_NEW, Spinner_items_lists.getIABP_mode()));
        lista.add(new ItemsRV("Σφυγμός", "sfigmos_akrou", SPINNER_TYPE_NEW, Spinner_items_lists.getSfigmoAkrou()));


        lista.add(new ItemsRV("ACT", "act", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("PCW", "pcw", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("PAS-PAD", "pas", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("ICP-CPP", "icp", "", EDITTEXT_ITEM, AKERAIOS));


        //ΑΝΑΠΝΕΥΣΤΙΚΟ
        lista.add(new ItemsRV("ΑΝΑΠΝΕΥΣΤΙΚΟ", "", TITLE_ITEM));  //12

        lista.add(new ItemsRV("ΟΞΥΜΕΤΡΊΑ(Ο2 Sat)", "oksimetria", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Σύνολο αναπνοών", "sinolo_anapnown", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Τρόπος αερισμού", "tropos_aerismou", SPINNER_TYPE_NEW, Spinner_items_lists.getTroposAerismou()));
        lista.add(new ItemsRV("Άλλος τρόπος \nαερισμού", "allos_tropos_aerismou", SPINNER_TYPE_NEW, Spinner_items_lists.getAllosTroposAerismou()));
        lista.add(new ItemsRV("Άλλος τρόπος\n αερισμού (Κείμενο)", "allos_tropos_aerismou_text", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Μείγμα Οξυγόνου (%)", "migma_oxigonou", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Αναπνοές \nΑναπνευστήρα", "anapnoes_anapneustira", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Εισπνεόμενος όγκος(ντ)", "eispneomenos_ogkos", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("PEEP", "PEEP", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Pr.Control", "pr_control", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Pr.Support", "pr_support", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Peak Pressure-\nτύπος αναπνοής", "tupos_anapnohs", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("ΘΕΣΗ ΚΡΕΒΑΤΙΟΥ", "thesi_krebatiou", SPINNER_TYPE_NEW, Spinner_items_lists.getRightLeft()));
        lista.add(new ItemsRV("ΘΕΣΗ ΣΩΜΑΤΟΣ", "thesi_somatos", SPINNER_TYPE_NEW, Spinner_items_lists.getThesiSomatos()));

        lista.add(new ItemsRV("ΕΝΔΟΦΛΕΒΙΑ \nΡΥΘΜΙΣΗ ΣΑΚΧΑΡΟΥ", "endoflevia_sakxarou", "", EDITTEXT_ITEM, KEIMENO).setInfo_image(R.drawable.endoflevia_insoulini));
        lista.add(new ItemsRV("ΥΠΟΔΟΡΙΑ \nΡΥΘΜΙΣΗ ΣΑΚΧΑΡΟΥ", "ipodoria_sakxarou", "", EDITTEXT_ITEM, KEIMENO).setInfo_image(R.drawable.ipodoria_insoulini));
        lista.add(new ItemsRV("ΑΝΑΡΡΟΦΗΣΗ", "anarrofisi", false, CHECKBOX_ITEM));



        if (forRV)
            return lista.getList();

        return lista.getTableList();
    }


    public static String getKARTA_FARMAKON_KATIGORIA_NAME(String id) {

        String name;

        Map<String, String> map = new HashMap<>();

        map.put("", "");
        map.put("0", "");
        map.put("1", "Φάρμακο");
        map.put("2", "Ορός");
        map.put("3", "Εφάπαξ φάρμακο");

        name = map.get(id);

        return name;
    }

    public static Map getKARTA_FARMAKON_KATIGORIA_MAP2() {

        String name;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);


        return map;
    }

    public static Map getKARTA_FARMAKON_KATIGORIA_MAP() {


        Map<String, Integer> map = new HashMap<>();

        map.put("", 0);
        map.put("Φάρμακο", 1);
        map.put("Ορός", 2);
        map.put("Εφάπαξ φάρμακο", 3);


        return map;
    }


    public static ArrayList<String> getKARTA_FARMAKON_KATIGORIA_LISTA() {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("");
        lista.add("Φάρμακο");
        lista.add("Ορός");
        lista.add("Εφάπαξ φάρμακο");

        return lista;
    }


    public static ArrayList<ItemsRV> getKARTA_XORGISIS_FARMAKON_LISTA() {

        ArrayList<ItemsRV> lista = new ArrayList<>();
        ItemsRV item;
        lista.add(new ItemsRV("Φάρμακο", "itemID", "", TEXTVIEW_TYPE, MEDICINES));
        lista.add(new ItemsRV("Ημ/νία Έναρξης", "dateStart", "", TEXTVIEW_TYPE, 1));
        //  lista.add(new ItemsRV("Ημ/νία","date", "", TEXTVIEW_TYPE, 1));
        item = new ItemsRV("Κατηγορία", "category", SPINNER_TYPE, getKARTA_FARMAKON_KATIGORIA_LISTA());
        item.setMap(getKARTA_FARMAKON_KATIGORIA_MAP2());
        lista.add(item);
        lista.add(new ItemsRV("ml/ώρα", "ml_hour", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Δόση", "dosi", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Οδός χορήγησης", "odos_xorigisis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Δοσολογία", "dosologia", "", EDITTEXT_ITEM, KEIMENO));
        // lista.add(new ItemsRV("Ώρες χορήγησης","ores_xorigisis", "", TEXTVIEW_TYPE, KEIMENO));


        return lista;
    }


    public static ArrayList<String> getDays(int days) {

        ArrayList<String> lista = new ArrayList<>();
        // lista.add("");
        for (int i = 1; i <= days; i++)
            lista.add(i + "η ημέρα");

        return lista;
    }


    public static String[] getFistoulaOrMosxeumaListaArray() {
        return new String[]{"Αριστερό άνω άκρο", "Δεξί άνω άκρο", "Αριστερό κάτω άκρο", "Δεξί κάτω άκρο", "Άλλο"};
    }

    public static String[] getDialimaListaArray() {
        return new String[]{"761", "294", "257", "293", "296", "298"};
    }

    public static String[] getFiltroArray() {
        return new String[]{"ELISIO -19M", "ELISIO -21M", "ELISIO -21H", "ulf -240H", "FDX -180GW", "FDX-210GW"};
    }


    public static String getNosileutiki_logodosia_orario(String id) {

        if (id == null || id.equals(""))
            id = "0";

        Map map = new HashMap<String, String>();
        map.put(0, "");
        map.put(1, "Πρωινή βάρδια");
        map.put(2, "Απογευματινή βάρδια");
        map.put(3, "Βραδινή βάρδια");


        return (String) map.get(Integer.parseInt(id));
    }



    public static ArrayList<TableViewItem> getDiaitologio() {

        BasicList lista = new BasicList();

        lista.add(new TableViewItem("ID", "ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία", "datestr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Δίαιτα","Dieta", TEXTVIEW_DIETA_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Αλλεργίες\nφαγητού","allergies_fagitou", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Μέτρα \nλοιμώξεων","metra_loimoxeon", SPINNER_TYPE_NEW, Spinner_items_lists.getMetraLoimokseisLista()));
        lista.add(new TableViewItem("Διατροφολογικές οδηγίες","diatr_odigies", SPINNER_ITEM_NEW, Spinner_items_lists.getYesNo()));
        lista.add(new TableViewItem("Σχόλια","remarks", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Σίτιση\n συνοδού","SitisiSinodou", SPINNER_ITEM_NEW, Spinner_items_lists.getSitisi_sinodou()));
        lista.add(new TableViewItem("Δίετα \nσυνοδού","DietaSinodou", TEXTVIEW_DIETA_TYPE, TABLE_NO_TYPE));


        return lista.getTableList();
    }


    public static ArrayList<TableViewItem> getDailyDiaitologio() {

        BasicList lista = new BasicList();

        lista.add(new TableViewItem("ID", "ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
       // lista.add(new TableViewItem("Ημ/νία", "datestr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
       // lista.add(new TableViewItem("userID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Πρωινό","proino", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Δεκατιανό","dekatiano", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Μεσημεριανό","mesimeriano", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Απογευματινό","apogeumatino", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Βραδινό","vradino", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Προ ύπνου","pro_ipnou", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Άσκηση","askisi", EDIT_TEXT_TYPE, KEIMENO));

        lista.add(new TableViewItem("Οδηγίες","odigies", EDIT_TEXT_TYPE, KEIMENO));


        return lista.getTableList();
    }


    public static ArrayList<TableViewItem> getDiaitologiki_ektimisi() {

        BasicList lista = new BasicList();

        lista.add(new TableViewItem("ID", "ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία", "datestr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Μετρήσεις","metriseis", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Θεραπευτικό πλάνο","plano", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));



        return lista.getTableList();
    }


        public static ArrayList<TableViewItem> getSigkentrotikaStatheresMetriseis_MEDIT() {

        BasicList lista = new BasicList();

        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Ημ/νία","datestr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Υπευθ. Ιατρός \nβάρδιας","ipefthinos_iatros_vardias", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Είδος αιμοκάθαρσης","eidos_aimID", SPINNER_TYPE_NEW, "Nursing_items_med_instr_eidos_aim"));

        lista.add(new TableViewItem("Προγρ. \nώρα έναρξης","schedule_time_start", TEXTVIEW_CLOCK_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ώρα έναρξης","time_start", TEXTVIEW_CLOCK_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Διάρκεια","duration_aim_ID", SPINNER_TYPE_NEW, Spinner_items_lists.getDuration_aim()));
        lista.add(new TableViewItem("Αρχικό βάρος (kg)","arxiko_varos", EDIT_TEXT_TYPE,DEKADIKOS));
        lista.add(new TableViewItem("Ξηρό βάρος (kg)","xiro_varos", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Ένδειξη υπερ/τος\n Μηχαν.τ.ν. (UF)","UF", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Διαφορά βάρους (kg)","diafora_varous", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Βάρος εξόδου (kg)","varos_exodou", EDIT_TEXT_TYPE,DEKADIKOS));

        lista.add(new TableViewItem("Θερμοκ. \nδιαλύματος (°C)","therm_dialimatos", EDIT_TEXT_TYPE, DEKADIKOS));

        lista.add(new TableViewItem("Kt/V","Kt_V", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("fistula","fistulaID", SPINNER_TYPE_NEW, "Nursing_items_statheres_fistoula_and_mosxeuma"));
        lista.add(new TableViewItem("Μόσχευμα","mosxeumaID", SPINNER_TYPE_NEW,"Nursing_items_statheres_fistoula_and_mosxeuma"));
        lista.add(new TableViewItem("Βελόνες","velonesID", SPINNER_TYPE_NEW, "Nursing_items_statheres_metr_velones"));

        lista.add(new TableViewItem("SN-Y LINE ADAPTOR \n Συνδετικό","sindetiko", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Κεντρ. φλεβ. \nκαθετηρας μονιμος","flev_kathtiras_monimos", CHECKBOX_TYPE,TABLE_NO_TYPE));
        lista.add(new TableViewItem("Κεντρ. φλεβ. \nκαθετηρας προσωρινος","flev_kathtiras_prosorinos", CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Κεντρ. φλεβ. \nκαθετηρας_κειμενο","flev_kathtiras_text", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Χωρητ. ηπαρινισμού σκελών Α","skelon_A", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Χωρητ. ηπαρινισμού \nσκελών Φ","skelon_f", EDIT_TEXT_TYPE,DEKADIKOS));
        lista.add(new TableViewItem("Διάλυμα","dialimaID", SPINNER_TYPE_NEW, "Nursing_items_med_instr_dialima"));

        lista.add(new TableViewItem("Φίλτρο","filtroID", SPINNER_TYPE_NEW, "Nursing_items_med_instr_filter"));
        lista.add(new TableViewItem("LOT Φίλτρου","lot_filtrou", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Αντιπ. αγωγή", "antip_agogi", SPINNER_ITEM_NEW, "Nursing_items_med_instr_agogi"));
        lista.add(new TableViewItem("Αντιπ. αγωγή\n δόση", "agogiDosisID", SPINNER_ITEM_NEW, "Med_instr_antipiktiki_dosh"));

        return lista.getTableList();
    }



        public static ArrayList<TableViewItem> getSigkentrotikaSinexeisMetriseis_MEDIT() {

            BasicList lista = new BasicList();

            lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
          //  lista.add(new TableViewItem("ΗΜ/ΝΙΑ / ΩΡΑ","Date", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
            lista.add(new TableViewItem("Συστολική πίεση (mmHg)","sis_piesi", EDIT_TEXT_TYPE, DEKADIKOS_WITH_NEGATIVE));
            lista.add(new TableViewItem("Διαστολική πίεση (mmHg)","dias_piesi", EDIT_TEXT_TYPE, DEKADIKOS_WITH_NEGATIVE));

            lista.add(new TableViewItem("Σφύξεις","sfikseis", EDIT_TEXT_TYPE, DEKADIKOS));
            lista.add(new TableViewItem("Θερμοκρασία (°C)","thermokrasia", EDIT_TEXT_TYPE, DEKADIKOS));
            lista.add(new TableViewItem("SPO2 (%)","spo2", EDIT_TEXT_TYPE, DEKADIKOS));
            lista.add(new TableViewItem("Ρυθμός ροή αντλίας (ml/min)","roi", EDIT_TEXT_TYPE, DEKADIKOS));
            lista.add(new TableViewItem("Πίεση φλεβ. γραμμής(mmHg)","piesi_flev", EDIT_TEXT_TYPE, DEKADIKOS_WITH_NEGATIVE));

            lista.add(new TableViewItem("Πίεση αρτ. γραμμής(mmHg):","piesi_art", EDIT_TEXT_TYPE, DEKADIKOS_WITH_NEGATIVE));
            lista.add(new TableViewItem("Ρυθμός υπερδιηθησης \n ανά ώρα(U.F.) (l/h)","iperd", EDIT_TEXT_TYPE, DEKADIKOS));

            lista.add(new TableViewItem("Αγωγιμότητα (ms/cm)","agogimotita", EDIT_TEXT_TYPE, DEKADIKOS));
            lista.add(new TableViewItem("HCO 3 (ms/cm)","hco3", EDIT_TEXT_TYPE, DEKADIKOS));

            lista.add(new TableViewItem("Μετρήσεις-Παραμβάσεις","paremvaseis", EDIT_TEXT_TYPE, KEIMENO));
            lista.add(new TableViewItem("Παρατηρήσεις","paratiriseis", EDIT_TEXT_TYPE, KEIMENO));
            lista.add(new TableViewItem("Εγχύσεις - Φάρμακα","egxiseis", EDIT_TEXT_TYPE, KEIMENO));
            lista.add(new TableViewItem("Vit - B","vit_b", CHECKBOX_ITEM, TABLE_NO_TYPE));

            lista.add(new TableViewItem("L - carnittine","carnitine", CHECKBOX_ITEM, TABLE_NO_TYPE));
            lista.add(new TableViewItem("Alphacalcidol","alphacalcidol", CHECKBOX_ITEM, TABLE_NO_TYPE));
            lista.add(new TableViewItem("EPO","epo", CHECKBOX_ITEM, TABLE_NO_TYPE));
            lista.add(new TableViewItem("Epoetin alpha","epo_alpha", CHECKBOX_ITEM, TABLE_NO_TYPE));

            lista.add(new TableViewItem("Epoetin zeta","epo_zeta", CHECKBOX_ITEM, TABLE_NO_TYPE));
            lista.add(new TableViewItem("Darbepoetin","epo_darbepoetin", CHECKBOX_ITEM, TABLE_NO_TYPE));
            lista.add(new TableViewItem("Paricalcitol","paricalcitol", CHECKBOX_ITEM, TABLE_NO_TYPE));
            lista.add(new TableViewItem("Σίδηρος","sidiros", CHECKBOX_ITEM, TABLE_NO_TYPE));


            return lista.getTableList();

    }



    public static ArrayList<TableViewItem> getEidikiFrontidaMeth() {

        ArrayList<TableViewItem> lista = new ArrayList<>();
       // lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Date", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("itemID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("perigrafi", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("datein", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("dateout", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));


        return lista;
    }


    public static ArrayList<TableViewItem> getErgastiriakaMeth() {

        ArrayList<TableViewItem> lista = new ArrayList<>();
        // lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Date",   TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("itemID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("hour", TEXTVIEW_CLOCK_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("date_tomorrow", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));

        return lista;
    }


    public static ArrayList<TableViewItem> getIatrikesOdigies_Medicines_Meth() {

        ArrayList<TableViewItem> lista = new ArrayList<>();
        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));

        lista.add(new TableViewItem("datetimeStr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("nameItem", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ml_hour", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("dosi", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("dosologia", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("timetoStr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("xorigithike", CHECKBOX_ITEM, TABLE_NO_TYPE));


        return lista;

    }


    public static ArrayList<TableViewItem> getEnimerotiko_Simeioma_eksoterika() {

        ArrayList<TableViewItem> lista = new ArrayList<>();

        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Date", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("datetimeStr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("aitia", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("anamnistiko", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("farmaka", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("eurimata", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("apotelesmata", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("diagnosisIn", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("therapeia", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("therapeia_odigies", EDIT_TEXT_TYPE, KEIMENO));

        return lista;

    }

    public static ArrayList<TableViewItem> geτKlimakaGlaskovis() {

        ArrayList<TableViewItem> lista = new ArrayList<>();

        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Date", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("dateStr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("vardiaID", SPINNER_TYPE_NEW, Spinner_items_lists.getNosileutiki_logodosia_vardies()));

        lista.add(new TableViewItem("mati", SPINNER_TYPE_NEW, Spinner_items_lists.getAnoigmaMation()));
        lista.add(new TableViewItem("omilia", SPINNER_TYPE_NEW, Spinner_items_lists.getOmilia()));
        lista.add(new TableViewItem("kinisi", SPINNER_TYPE_NEW, Spinner_items_lists.getKinitikiAntidrasi()));


        return lista;

    }



    public static ArrayList<TableViewItem> getNursing_Kathethres_Topos_for_item() {

        ArrayList<TableViewItem> lista = new ArrayList<>();

       // lista.add(new TableViewItem("ID", "ID", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία εγγραφής","dateStr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ονομασία","name", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Τόπος","topos", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία εισόδου","datestartStr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία εξόδου","datestopStr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));


        return lista;

    }

    public static ArrayList<TableViewItem> getSigkentrotikaIsozigioMeth(String transgroupID) {

        ArrayList<TableViewItem> lista = new ArrayList<>();
        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΗΜ/ΝΙΑ / ΩΡΑ","Date", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Φάρμακα συνεχούς έγχυσης","all_hours_meds", TEXTVIEW_LISTENER, TABLE_TYPE_LISTENER)
                .setTextViewTableListener(Str_queries.getSigkentrotika_meds_meth_types(transgroupID, Type_meds.MEDS_SISTIMIKI_FARM_AGOGI),null,
                        InfoSpecificLists.getFarmaka_isozigeio_meth(StaticFields.Type_meds.MEDS_SISTIMIKI_FARM_AGOGI), false, false, true));

        lista.add(new TableViewItem("Συστημική φαρμ. αγωγή","systemic_meds", TEXTVIEW_LISTENER, TABLE_TYPE_LISTENER)
                .setTextViewTableListener(Str_queries.getSigkentrotika_meds_meth_types(transgroupID, Type_meds.MEDS_OROS),null,
                        InfoSpecificLists.getFarmaka_isozigeio_meth(StaticFields.Type_meds.MEDS_OROS), false, false, true));

        lista.add(new TableViewItem("Χορήγηση εφάπαξ φαρμάκων","one_time_meds", TEXTVIEW_LISTENER, TABLE_TYPE_LISTENER)
                .setTextViewTableListener(Str_queries.getSigkentrotika_meds_meth_types(transgroupID, StaticFields.Type_meds.ONE_TIME_MEDS),null,
                        InfoSpecificLists.getFarmaka_isozigeio_meth(StaticFields.Type_meds.ONE_TIME_MEDS), false, false, true));

        lista.add(new TableViewItem("Αλλα φάρμακα","other_meds", TEXTVIEW_LISTENER, TABLE_TYPE_LISTENER)
                .setTextViewTableListener(Str_queries.getSigkentrotika_meds_meth_types(transgroupID,  StaticFields.Type_meds.OTHER_MEDS),null,
                        InfoSpecificLists.getFarmaka_isozigeio_meth( StaticFields.Type_meds.OTHER_MEDS), false, false, true));

        lista.add(new TableViewItem("Μεταγγίσεις","metaggiseis", TEXTVIEW_LISTENER, TABLE_TYPE_LISTENER)

                .setTextViewTableListener( Str_queries.getSigkentrotika_metagiseis_meth(transgroupID),null,
                        InfoSpecificLists.getMetaggiseis(), false, false, true));

        lista.add(new TableViewItem("Αιμοδιαδιήθηση(Α/Δ):HEPARINE I.U./h","PO_PL_PG", EDIT_TEXT_TYPE, DEKADIKOS));



        lista.add(new TableViewItem("ΟΥΡΑ","oura", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Πλύσεις κύστεως","pluseis_kusteos", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Αιμοκάθαρση/Αιμοδιήθηση","aimodiadihthisi", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Διαλύματα Α/Δ","dialumata", EDIT_TEXT_TYPE, DEKADIKOS));

        lista.add(new TableViewItem("levin","levin", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 1","paroxeteush1", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 1\nΠοσότητα","paroxeteush1_posotita", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 2","paroxeteush2", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 2\nΠοσότητα","paroxeteush2_posotita", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 3","paroxeteush3", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 3\nΠοσότητα","paroxeteush3_posotita", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 4","paroxeteush4", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 4\nΠοσότητα","paroxeteush4_posotita", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 5", "paroxeteush5",  EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 5\nΠοσότητα", "paroxeteush5_posotita",  EDIT_TEXT_TYPE, DEKADIKOS));

        lista.add(new TableViewItem("Παροχέτευση 6", "paroxeteush6",  EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 6\nΠοσότητα", "paroxeteush6_posotita",  EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 7", "paroxeteush7",  EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 7\nΠοσότητα", "paroxeteush7_posotita",  EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 8", "paroxeteush8",  EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 8\nΠοσότητα", "paroxeteush8_posotita",  EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 9", "paroxeteush9",  EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 9\nΠοσότητα", "paroxeteush9_posotita",  EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Παροχέτευση 10", "paroxeteush10",  EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παροχέτευση 10\nΠοσότητα", "paroxeteush10_posotita",  EDIT_TEXT_TYPE, DEKADIKOS));

      //  lista.add(new TableViewItem("total_pros", TEXTVIEW_VALUE_FROM_VIEW, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΣΥΝΟΛΟ ΠΡΟΣ","total_pros_sum_str", TEXTVIEW_VALUE_FROM_VIEW, TABLE_NO_TYPE));
        //  lista.add(new TableViewItem("total_apov", TEXTVIEW_VALUE_FROM_VIEW, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΣΥΝΟΛΟ ΑΠΟΒ", "total_apov_sum_str", TEXTVIEW_VALUE_FROM_VIEW, TABLE_NO_TYPE));

        lista.add(new TableViewItem("ΙΣΟΖΥΓΙΟ","total_isozigio", TEXTVIEW_VALUE_FROM_VIEW, TABLE_NO_TYPE));


        return lista;
    }


    public static ArrayList<TableViewItem> getSigkentrotikaZotikaMeth() {

        ArrayList<TableViewItem> lista = new ArrayList<>();


        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΗΜ/ΝΙΑ / ΩΡΑ","Date", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("V Συστολική Αρτ.Πίεση","sistoliki_artiriaki_piesi", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("X  Μέση Αρτ.Πίεση","mesi_artiriaki_piesi", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Λ Διαστ. Αρτ. Πίεση","diastoliki_artiriaki_piesi", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("ΘΕΡΜΟΚΡΑΣΙΑ","thermokrasia", EDIT_TEXT_TYPE, AKERAIOS));
        lista.add(new TableViewItem("Σφύξεις","sfiksis", EDIT_TEXT_TYPE, AKERAIOS));

        lista.add(new TableViewItem("Καρδ.Ρυθμός-ΒΗΜΑΤΟΔΟΤΗΣ\n(συχνότητα)","rithmos_bimatodotis", SPINNER_TYPE_NEW, Spinner_items_lists.getKardiakosRithmos()));
        lista.add(new TableViewItem("Δεξια κόρη (μεγεθος mm)","deksia_kori", SPINNER_TYPE_NEW, Spinner_items_lists.getMegethosKoris()));
        lista.add(new TableViewItem("Δεξια κόρη\nσυμβολο","deksia_symbol", SPINNER_TYPE_NEW, Spinner_items_lists.getSymbolsKoris()));
        lista.add(new TableViewItem("Αριστερή κόρη (μεγεθος mm)","aristeri_kori", SPINNER_TYPE_NEW, Spinner_items_lists.getMegethosKoris()));
        lista.add(new TableViewItem("Αριστερή κόρη\nσυμβολο","aristeri_symbol", SPINNER_TYPE_NEW, Spinner_items_lists.getSymbolsKoris()));
        lista.add(new TableViewItem("Πόνος","ponos", SPINNER_TYPE_NEW, Spinner_items_lists.getVathmoiPonou()));

        lista.add(new TableViewItem("ΙΑΒΡ-Σφυγμός Άκρου","sfigmos_akrou", SPINNER_TYPE_NEW, Spinner_items_lists.getSfigmoAkrou()));
        lista.add(new TableViewItem("ACT","act", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("CVP","cvp", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("PCW","pcw", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("PAS-PAD","pas", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("ScvO2-CO/CI","scvO2", EDIT_TEXT_TYPE, DEKADIKOS));

        lista.add(new TableViewItem("ICP-CPP","icp", EDIT_TEXT_TYPE, DEKADIKOS));



        //ΑΝΑΠΝΕΥΣΤΙΚΟ

        lista.add(new TableViewItem("Τρόπος αερισμού","tropos_aerismou", SPINNER_TYPE_NEW, Spinner_items_lists.getTroposAerismou()));
        lista.add(new TableViewItem("Άλλος τρόπος\nαερισμού","allos_tropos_aerismou", SPINNER_TYPE_NEW, Spinner_items_lists.getAllosTroposAerismou()));
        lista.add(new TableViewItem("Άλλος τρόπος\nαερισμού (κείμενο)","allos_tropos_aerismou_text", SPINNER_TYPE_NEW, Spinner_items_lists.getAllosTroposAerismou()));
        lista.add(new TableViewItem("Μείγμα Οξυγόνου","migma_oxigonou", EDIT_TEXT_TYPE, KEIMENO));

        lista.add(new TableViewItem("Αναπνοές Αναπνευστήρα","anapnoes_anapneustira", EDIT_TEXT_TYPE, AKERAIOS));
        lista.add(new TableViewItem("Σύνολο αναπνοών","sinolo_anapnown", EDIT_TEXT_TYPE, AKERAIOS));
        lista.add(new TableViewItem("Εισπνεόμενος όγκος(ντ)","eispneomenos_ogkos", EDIT_TEXT_TYPE, KEIMENO));

        lista.add(new TableViewItem("PEEP","PEEP", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Pr.Control\"","pr_control", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Pr.Support","pr_support", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("ΟΞΥΜΕΤΡΊΑ(Ο2 Sat)","oksimetria", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Peak Pressure-\nτύπος αναπνοής","tupos_anapnohs", EDIT_TEXT_TYPE, KEIMENO));

        lista.add(new TableViewItem("ΘΕΣΗ ΚΡΕΒΑΤΙΟΥ","thesi_krebatiou", SPINNER_TYPE_NEW, Spinner_items_lists.getRightLeft()));
        lista.add(new TableViewItem("ΘΕΣΗ ΣΩΜΑΤΟΣ","thesi_somatos", SPINNER_TYPE_NEW, Spinner_items_lists.getThesiSomatos()));
        lista.add(new TableViewItem("ΑΝΑΡΡΟΦΗΣΗ","anarrofisi", CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("ΕΝΔΟΦΛΕΒΙΑ ΡΥΘΜΙΣΗ ΣΑΚΧΑΡΟΥ","endoflevia_sakxarou", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("ΥΠΟΔΟΡΙΑ ΡΥΘΜΙΣΗ ΣΑΚΧΑΡΟΥ","ipodoria_sakxarou", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Ινσουλίνη","insoulini", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("GLU","glu", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("IABP-MODE","iabp", SPINNER_TYPE_NEW, Spinner_items_lists.getIABP_mode()));

        return lista;

    }


    public static ArrayList<TableViewItem> getFarmaka_isozigeio_meth(String type_medID){

        ArrayList<TableViewItem> lista = new ArrayList<>();

        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΗΜ/ΝΙΑ / \nΩΡΑ ΕΝΑΡΞΗΣ","DateStart", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΗΜ/ΝΙΑ / \nΩΡΑ ΛΗΞΗΣ","DateStop", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE).editWithNoSameUserID(true));
        lista.add(new TableViewItem("ΦΑΡΜΑΚΟ","itemID", TEXTVIEW_MEDICINE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ML/ΏΡΑ","ml_hour", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("ΔΟΣΟΛΟΓΙΑ","dosologia", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Ογκος","emploutismos", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Κατηγορία φαρμάκου","type_medID", TABLE_NO_ELEMENT, TABLE_NO_TYPE).setValue(type_medID));


        return lista;

    }

    public static ArrayList<TableViewItem> getMetaggiseis(){

        ArrayList<TableViewItem> lista = new ArrayList<>();


        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID_start","UserID_start_name", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID_finish","UserID_finish_name", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ώρα έναρξης","TimeStart", TEXTVIEW_CLOCK_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("'Ωρα λήξης","TimFinish", TEXTVIEW_CLOCK_TYPE, TABLE_NO_TYPE));
       // lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΣΥΜΠΥΚΩΝΜΕΝΑ ΕΡΥΘΡΑ:\n Άριθμός ασκού","simpiknomena", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("ΠΛΑΣΜΑ F.F.P.: \n Άριθμός ασκού","plasma", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("ΑΙΜΟΠΕΤΑΛΙΑ P.L.T.: \n Άριθμός ασκού","aimopetalia", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("ογκος", "ogkos", EDIT_TEXT_TYPE, DEKADIKOS));

        lista.add(new TableViewItem("Υπογραφή\n ιατρού","signDoctor", CHECKBOX_ITEM, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ιατρός","DoctorName", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Παρατηρήσεις","Remarks", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Συνημμένο","medicalRecordID", TEXTVIEW_PHOTO, TABLE_NO_TYPE));



        return lista;

    }


    public static ArrayList<TableViewItem> getSigkentrotikaFarmakaSinedrias_Nephroxenia() {

        ArrayList<TableViewItem> lista = new ArrayList<>();

        lista.add(new TableViewItem("ID", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("itemID", TEXTVIEW_MEDICINE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("dosologia", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("monada", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("dosi", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("sixnotita_imeras", EDIT_TEXT_TYPE, AKERAIOS));
        lista.add(new TableViewItem("sixnotita_oras", EDIT_TEXT_TYPE, AKERAIOS));

        return lista;
    }


    public static ArrayList<ItemsRV> getNosileutikiLogodosiaMeth() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

       // lista.add(new ItemsRV("ΝΟΣΗΛ. ΑΞΙΟΛΟΓΟΓΗΣΗ", "aksiologisi", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΝΟΣΗΛ. ΠΑΡΑΜΒΑΣΕΙΣ\n ΕΚΤΙΜΙΣΗ ΑΠΟΤΕΛ.", "paremvaseis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΠΡΟΓΡΑΜΜΑΤΙΣΜΟΣ", "programmatismos", "", EDITTEXT_ITEM ,KEIMENO));

        lista.add(new ItemsRV("ΕΙΔΙΚΕΣ ΝΟΣΗΛΕΥΤΙΚΕΣ ΑΝΑΓΚΕΣ", "", TITLE_ITEM));  //3

        lista.add(new ItemsRV("Ψυχολογική υποστήριξη", "psixologiki_ipostiriksi", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Βοήθεια \n στη σίτιση", "sitisi", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Άλλη", "alli", "", EDITTEXT_ITEM,KEIMENO));

        return lista;
    }


    public static ArrayList<ItemsRV> getEnimerotikoSimeiomaEksetasisEksoterika() {

        ArrayList<ItemsRV> lista = new ArrayList<>();


        lista.add(new ItemsRV("ΑΙΤΙΑ ΠΡΟΣΕΛΕΥΣΗΣ", "aitia", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΑTOMIKO AΝΑΜΝΗΣΤΙΚΟ", "anamnistiko", "", EDITTEXT_ITEM ,KEIMENO));
        lista.add(new ItemsRV("ΦΑΡΜΑΚΑ ΠΟΥ ΛΑΜΒΑΝΕΙ", "farmaka", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΚΛΙΝΙΚΑ ΕΥΡΗΜΑΤΑ", "eurimata", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΕΡΓΑΣΤΗΡΙΑΚΑ \n ΑΠΟΤΕΛΕΣΜΑΤΑ", "apotelesmata", "", EDITTEXT_ITEM,KEIMENO));
        lista.add(new ItemsRV("ΔΙΑΓΝΩΣΗ", "diagnosisIn", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΘΕΡΑΠΕΙΑ ΠΟΥ ΕΦΑΡΜΟΣΤΗΚΕ\"", "therapeia", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("ΟΔΗΓΙΕΣ & ΘΕΡΑΠΕΙΑ ΠΟΥ ΘΑ ΣΥΝΕΧΙΣΘΕΙ \n ΜΕΤΑ ΤΗΝ ΕΞΟΔΟ ΤΟΥ ΑΣΘΕΝΗ",
                                                            "therapeia_odigies", "", EDITTEXT_ITEM,KEIMENO));
        return lista;
    }



    public static ArrayList<TableViewItem> getSigkentrotika_MedicalInst_lista() {

        ArrayList<TableViewItem> lista = new ArrayList<>();
        // lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("agogimotita",   TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("max_uf",   TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("dialima",   TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("genikes_odigies",   TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        return lista;
    }

    public static ArrayList<TableViewItem> getKartaXorigisisFarmakwn(boolean isOroi) {

        ArrayList<TableViewItem> lista = new ArrayList<>();
        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Χρήστης","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία Εναρξης","dateStart", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Φάρμακα","itemID", TEXTVIEW_MEDICINE_TYPE, TABLE_NO_TYPE));
        if (!isOroi)
            lista.add(new TableViewItem("Κατηγορία","category", SPINNER_TYPE_NEW, Spinner_items_lists.getTypeMedicine()));
        lista.add(new TableViewItem("ML/ώρα","ml_hour", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Δόση","dosi", EDIT_TEXT_TYPE, KEIMENO));

        lista.add(new TableViewItem("Οδος χορήγησης","odos_xorigisisID", SPINNER_TYPE_NEW, "Nursing_odoi_xorigisis"));
        lista.add(new TableViewItem("Αντλία","antlia", SPINNER_TYPE_NEW, Spinner_items_lists.getAntlia()));
        lista.add(new TableViewItem("Δοσολογία","dosologia", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Όγκος","ogkos", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Διακόπηκε","diakopike", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία διακοπής","dateStop", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        if (isOroi)
            lista.add(new TableViewItem("Κατηγορία","category", SPINNER_TYPE_NEW, Spinner_items_lists.getTypeMedicine()).setValue("2"));

        return lista;
    }


    public static ArrayList<TableViewItem> getKartaXorigisisFarmakwn_general() {

        ArrayList<TableViewItem> lista = new ArrayList<>();
        // lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("dateStart", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("itemID", TEXTVIEW_MEDICINE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ml_hour", EDIT_TEXT_TYPE, KEIMENO));
       // lista.add(new TableViewItem("dosi", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("dosologia", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("diakopike", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("dateStop", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));


        return lista;
    }



    public static ArrayList<TableViewItem> getKartaXorigisisFarmakwn_provoli_hours() {

        ArrayList<TableViewItem> lista = new ArrayList<>();
        // lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Χρήστης","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Είδος","item", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("ML/ώρα","ml_hour", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("οδοί χορήγησης","odos_xorigisis", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        //lista.add(new TableViewItem("διαλύτης","dosi", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Όγκος","ogkos", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Δοσολογία","dosologia", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Ωρα χορήγησης","hourstr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Χορηγήθηκε","xorigithike", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Παρατηρήσεις","remarks", EDIT_TEXT_TYPE, KEIMENO));


        return lista;
    }



    public static ArrayList<TableViewItem> getSigkentrotika_Programmatismos_p_a() {
        ArrayList<TableViewItem> lista = new ArrayList<>();
        // // lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("bedid","bedID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Κλίνη","bed", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ασθενής Πρωί","name_p", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ασθενής Απόγευμα","name_a", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Κουρτίνες προς πλύσιμο\n επι ΕΞΙΤ/ΜΕΤΑΦ.","kourtines", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Πάπλωμα προς πλύσιμο\n επι ΕΞΙΤ/ΜΕΤΑΦ.","paploma", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Μέτρα ελέγχου λοιμώξεων\nΠρωί","metra_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Μέτρα ελέγχου λοιμώξεων\nΑπογ.","metra_a", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Αποκλειστική\nΠρωί","apokleistiki_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Αποκλειστική\nΑπογ.","apokleistiki_a", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΛΟΥΤΡΟ\nΠρωί","loutro_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΛΟΥΤΡΟ\nΑπογ.","loutro_a", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΛΟΥΣΙΜΟ\nΠρωί","lousimo_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem( "ΛΟΥΣΙΜΟ\nΑπογ.","lousimo_a", CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("ΚΟΨΙΜΟ ΝΥΧΙΩΝ\nΠρωί","nixia_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem( "ΚΟΨΙΜΟ ΝΥΧΙΩΝ\nΑπογ.","nixia_a", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΞΥΡΙΣΜΑ ΑΝΔΡΩΝ\nΠρωί","ksirisma_andron_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΞΥΡΙΣΜΑ ΑΝΔΡΩΝ\nΑπογ.","ksirisma_andron_a", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΚΙΝΗΤΟΠΟΙΗΣΗ ΚΑΙ ΒΟΗΘΕΙΑ\nΣΤΟ ΜΠΑΝΙΟ\nΠρωί","voitheia_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΚΙΝΗΤΟΠΟΙΗΣΗ ΚΑΙ ΒΟΗΘΕΙΑ\nΣΤΟ ΜΠΑΝΙΟ\nΑπογ.","voitheia_a", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΤΟΠΙΚΗ ΚΑΘΑΡΙΟΤΗΤΑ\nΠρωί","kathariotita_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΤΟΠΙΚΗ ΚΑΘΑΡΙΟΤΗΤΑ\nΑπογ.","kathariotita_a", CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("ΠΕΡΙΠΟΙΗΣΗ ΚΑΤΑΚΛΙΣΕΩΝ\nΠρωί","peripoiisi_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΠΕΡΙΠΟΙΗΣΗ ΚΑΤΑΚΛΙΣΕΩΝ\nΑπογ.","peripoiisi_a", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΑΛΛΑΓΗ ΛΕΥΧΗΜΑΤΩΝ\nΠρωί","allagi_leuximaton_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΑΛΛΑΓΗ ΛΕΥΧΗΜΑΤΩΝ\nΑπογ.","allagi_leuximaton_a", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΒΟΗΘΕΙΑ ΣΙΤΙΣΗΣ\nΠρωί","sitisi_p", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ΒΟΗΘΕΙΑ ΣΙΤΙΣΗΣ\nΑπογ","sitisi_a", CHECKBOX_TYPE, TABLE_NO_TYPE));





      //// lista.add(new TableViewItem("", CHECKBOX_TYPE, TABLE_NO_TYPE));// san hack epeidh den vriskw to lathos sto table


        return lista;
    }



    public static ArrayList<TableViewItem> getSigkentrotika_Programmatismos_p_a_checklist() {

        ArrayList<TableViewItem> lista = new ArrayList<>();


        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("datestr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("vardiaID", SPINNER_TYPE_NEW, Spinner_items_lists.getVardiesForProgrammatismo_p_a()));
        lista.add(new TableViewItem("kenosi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("oura",CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("ourisi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("paroxeuteusi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("pagotherapeia", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("oxygen", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("per_flev_grammi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("oros", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("kentr_flev_grammi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("art_grammi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("kathetiras", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("allagi_panas", CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("kathariotita", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("kataklisi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("thesi_somatos", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("sitisi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ipnos", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("koudouni", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("isixia_thalamou", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("maksilari", CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("leuximata", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("klines_komodina", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("kourtines", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("ntoulapia", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("diathesimotita", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("mikri_apothiki", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("kamaraki", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("domatio_imatismou", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("apothema_imatismou", CHECKBOX_TYPE, TABLE_NO_TYPE));


        return lista;
    }


    public static ArrayList<TableViewItem>  getKatagrafi_efarmogis_metron_elegxou_loimokseon_orofon_meth(){
        ArrayList<TableViewItem> lista = new ArrayList<>();


        lista.add(new TableViewItem("ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Date", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("DoctorID", SPINNER_TYPE_NEW,"doctor"));
        lista.add(new TableViewItem("eidos", SPINNER_TYPE_NEW, Spinner_items_lists.getEidosMetron()));
        lista.add(new TableViewItem("eidos_thetiko", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Date_thetiko", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("result", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Date_recieved", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("enimerosi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("apomonosi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("hospital_box", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("loutro", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("kitio", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("anarrofisi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("maska", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("alles", CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Date_arsis_metron", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("DoctorID_arsi", SPINNER_TYPE_NEW,"doctor"));
        lista.add(new TableViewItem("Date_epikirosis", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("deiktes", EDIT_TEXT_TYPE, KEIMENO));

        lista.add(new TableViewItem("lampa", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("diplo_katharisma", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("toixoi", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("kourtines", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("klorkleen", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("alles_eksodo", CHECKBOX_TYPE, TABLE_NO_TYPE));

        return lista;

    }


    public static ArrayList<TableViewItem>  getFarm_agogi_lista(boolean isMonimi) {
        ArrayList<TableViewItem> lista = new ArrayList<>();

        int TYPE_ELEMENT ;
        if (isMonimi)
            TYPE_ELEMENT = TEXTVIEW_ITEM_READ_ONLY_VALUE;
        else
            TYPE_ELEMENT = EDITTEXT_ITEM;

        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
      //  lista.add(new TableViewItem("ID","UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
       // lista.add(new TableViewItem("ID","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Φάρμακο","itemID", TEXTVIEW_MEDICINE_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Ασθενής","patientName", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Δοσολογία","dosologia", TYPE_ELEMENT, DEKADIKOS));
        lista.add(new TableViewItem("Μονάδα","monada", TYPE_ELEMENT, AKERAIOS));
        lista.add(new TableViewItem("Δόση","dosi", TYPE_ELEMENT, DEKADIKOS));
        lista.add(new TableViewItem("Ανά ημέρες","sixnotita_imeras", TYPE_ELEMENT, AKERAIOS));
        lista.add(new TableViewItem("Ανά ώρες","sixnotita_oras", TYPE_ELEMENT, AKERAIOS));
        //lista.add(new TableViewItem("Εκτός συνεδρίας","efapax", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Συνεδρίας","sinedrias", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE).setValue(true));


        return lista;
    }


    public static  ArrayList<TableViewItem> getSigkentrotika_zotika(){

            ArrayList<TableViewItem> lista = new ArrayList<>();

             lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Νοσηλευτής","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Ασθενής","patName", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Ημ/νία","dateStr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Ωρα","Watch", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Συστολική πίεση","sistoliki_piesi", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Διαστολική πίεση","diastoliki_piesi", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Καρδ.ρυμός","rithmos", SPINNER_TYPE_NEW, Spinner_items_lists.getKardiakosRithmos()));
             lista.add(new TableViewItem("Σφύξεις","sfixeis", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Θερμοκρασία","thermokrasia", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Κλίμακα πόνου","ponos", SPINNER_TYPE_NEW, Spinner_items_lists.getVathmoiPonou()));
             lista.add(new TableViewItem("SPO2 (%)","spo2", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Stick glu","stick_glu", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
             lista.add(new TableViewItem("Αναπνοές","anapnoes", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

             return lista;
    }




    public static ArrayList<ItemsRV> getEktimisi_kind_ptoseos() {

        ArrayList<ItemsRV> lista = new ArrayList<>();

        lista.add(new ItemsRV("Σχετιζόμενο Αναμνηστικό\nΙστορικό", "anamnhstiko_istoriko",  MULTI_TYPE, new Object[]{
                0,"",
                1,"Ζαλάδες" ,
                2,"Ίλιγγοι" ,
                3,"Απώλεια αισθήσεων"
        }));

        lista.add(new ItemsRV("Προβλήματα", "problhmata", MULTI_TYPE, new Object[]{
                1,"ΟΡΘΟΠΕΔΙΚΟ" ,
                2,"ΝΕΥΡΟΛΟΓΙΚΟ" ,
                3,"ΑΚΟΗΣ",
                4,"ΟΡΑΣΗΣ"
        }));



        lista.add(new ItemsRV("Μη αυτοεξυπηρέτηση", "mi_autoeksipiretisi", MULTI_TYPE, new Object[]{
                1,"ΜΠΑΝΙΟ" ,
                2,"ΚΑΘΙΣΜΑ" ,
                3,"ΚΙΝΗΤΟΠΟΙΗΣΗ"
        }));
        lista.add(new ItemsRV("Φαρμακευτικη αγωγή", "meds", MULTI_TYPE, new Object[]{
                1,"ΗΡΕΜΙΣΤΙΚΑ" ,
                2,"ΥΠΝΩΤΙΚΑ" ,
                3,"ΔΙΟΥΡΗΤΙΚΑ"
        }));

        //----------------------------
        lista.add(new ItemsRV("ΑΞΙΟΛΟΓΗΣΗ ΑΣΘΕΝΟΥΣ ΓΙΑ ΤΟΝ ΚΙΝΔΥΝΟ ΠΤΩΣΕΩΣ - ΚΛΙΜΑΚΑ MORSE ", "", TITLE_ITEM));       //TITLOS     POSITION 4

        lista.add(new ItemsRV("ΙΣΤΟΡΙΚΟ ΠΤΩΣΕΩΝ\n(πρόσφατη ή τους τελευταίους 3 μήνες)", "istoriko_ptwsewn", SPINNER_ITEM_NEW, Spinner_items_lists.getYesNo()) );
        lista.add(new ItemsRV("ΔΕΥΤΕΡΕΥΟΥΣΑ ΔΙΑΓΝΩΣΗ\n(πέραν της μίας διαγνώσεις κατά την εισαγωγή)", "deytereuousa_diagn", SPINNER_ITEM_NEW, Spinner_items_lists.getYesNo()));
        lista.add(new ItemsRV("ΧΡΗΣΗ ΒΟΗΘΗΜΑΤΩΝ", "xrhsh_bohthimatwn", MULTI_TYPE, new Object[]{
                1,"ΣΤΗΡΙΖΕΤΑΙ ΣΕ ΕΠΙΠΛΑ-ΥΠΟΣΤΗΡΙΖΕΤΑΙ ΑΠΟ ΦΥΣΙΚΟΘΕΡΑΠΕΥΤΗ/ΝΟΣΗΛΕΥΤΗ/ΣΥΝΟΔΟ" ,
                2,"ΠΑΤΕΡΙΤΣΕΣ,ΜΠΑΣΤΟΥΝΙ,ΠΕΡΙΠΑΤΗΤΗΣ(Π)" ,
                3,"ΟΧΙ/ΚΑΤΑΚΕΚΛΙΜΕΝΟΣ/ΑΝΑΠ.ΚΑΡΕΚΛΑ/ΑΠΟΚΛΕΙΣΤΙΚΗ 24ΩΡΟΥ"

        }));

        lista.add(new ItemsRV("ΦΕΡΕΙ ΟΡΟ/ΦΛΕΒΟΚΑΘΕΤΗΡΑ", "ferei_oro_flevokathetira", SPINNER_ITEM_NEW, Spinner_items_lists.getYesNo()));
        lista.add(new ItemsRV("ΒΑΔΙΣΗ/ΜΕΤΑΚΙΝΗΣΗ", "badisi_metakinhsh", MULTI_TYPE, new Object[]{
                1,"ΑΣΤΑΘΗΣ/ΜΕ ΔΥΣΚΟΛΙΑ " ,
                2,"ΑΔΥΝΑΜΗ/ΜΕ ΔΙΣΤΑΓΜΟ" ,
                3,"ΚΑΝΟΝΙΚΗ/ΚΑΤΑΚΕΚΛΙΜΕΝΟΣ/ΣΕ ΑΚΙΝΗΣΙΑ"

        }));
        lista.add(new ItemsRV("ΠΝΕΥΜΑΤΙΚΗ ΚΑΤΑΣΤΑΣΗ", "pneumatikh_katastash", MULTI_TYPE, new Object[]{
                1,"ΑΓΝΟΙΑ ΤΩΝ ΔΥΝΑΤΟΤΗΤΩΝ " ,
                2,"ΓΝΩΣΗ ΤΩΝ ΔΥΝΑΤΟΤΗΤΩΝ"

        }));

        //-------------------------------
//        lista.add(new ItemsRV("ΕΠΙΠΕΔΑ ΚΙΝΔΥΝΟΥ", "epipeda_kindynou", SPINNER_ITEM_NEW, Spinner_items_lists.getYesNo()));
//        lista.add(new ItemsRV("Ο ΚΙΝΔΥΝΟΣ ΠΤΩΣΗΣ \n ΤΟΥ ΑΣΘΕΝΟΥΣ ΧΑΡΑΚΤΗΡΙΖΕΤΑΙ ΩΣ", "xaraktirismos_kindynou", SPINNER_ITEM_NEW, Spinner_items_lists.getYesNo()));


        lista.add(new ItemsRV("ΕΚΤΙΜΗΣΗ ΚΙΝΔΥΝΟΥ ΠΤΩΣΗΣ", "", TITLE_ITEM));       //TITLOS     POSITION 11
        lista.add(new ItemsRV("ΣΥΝΟΛΟ:", "total", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("ΕΦΑΡΜΟΓΗ ΜΕΤΡΩΝ ΠΡΟΛΗΨΗΣ ΠΤΩΣΕΩΣ", "", TITLE_ITEM));       //TITLOS     POSITION 13

//        lista.add(new ItemsRV("Αναγκαίες δράσεις", "anagkaies_draseis", MULTI_TYPE, new Object[]{
//                1,"Καλή βασική νοσηλευτική φροντίδα" ,
//                2,"Εφαρμογή καθιερωμένων παρεμβάσεων πρόληψης πτώσεως" ,
//                3,"Εφαρμογή παρεμβάσεων πρόληψης πτώσεως υψηλού κινδύνου"
//
//        }));




        lista.add(new ItemsRV("ΧΑΜΗΛΟΣ ΚΙΝΔΥΝΟΣ ΠΤΩΣΗΣ", "xamhlos_kindynos_ptwshs", MULTI_TYPE, new Object[]{
                1,"ΧΟΡΗΓΗΣΗ ΕΝΤΥΠΟΥ ΝΥ_Ε 002-1_ΟΔΗΓΙΕΣ \n ΠΡΟΛΗΨΗΣ ΠΤΩΣΕΩΝ" ,
                2,"ΑΝΑΛΥΤΙΚΟΣ ΠΡΟΣΑΝΑΤΟΛΙΣΜΟΣ ΣΤΟΝ ΘΑΛΑΜΟ" ,
                3,"ΔΙΑΣΦΑΛΙΣΗ ΣΤΑΘΕΡΗΣ & ΕΛΕΓΧΟΜΕΝΗΣ \n ΠΡΟΣΒΑΣΗΣ ΣΤΟ ΚΟΥΔΟΥΝΙ ΚΛΗΣΕΩΣ",
                4,"ΜΗ ΧΡΗΣΗ ΟΛΙΣΘΗΡΩΝ ΥΠΟΔΗΜΑΤΩΝ",
                5,"ΣΥΝΕΧΗΣ ΠΑΡΑΜΟΝΗ ΣΥΝΟΔΟΥ"

        }));
        lista.add(new ItemsRV("ΜΕΤΡΙΟΣ ΚΙΝΔΥΝΟΣ ΠΤΩΣΗΣ", "metrios_kindynos_ptwshs", MULTI_TYPE, new Object[]{
                1,"ΧΟΡΗΓΗΣΗ ΕΝΤΥΠΟΥ ΝΥ_Ε 002-1_ΟΔΗΓΙΕΣ \n ΠΡΟΛΗΨΗΣ ΠΤΩΣΕΩΝ" ,
                2,"ΕΝΘΑΡΡΥΝΣΗ ΓΙΑ ΣΥΜΜΕΤΟΧΗ ΣΕ ΛΕΙΤΟΥΡΓΙΚΕΣ \nΔΡΑΣΤΗΡΙΟΤΗΤΕΣ(κινησιοθεραπεία-φυσιοθεραπεία ανάλογα με την \n αιτία εισόδου-έμφαση στη μυϊκή ενδυνάμωση και στην ισορροπία)" ,
                3,"ΔΙΑΘΕΣΗ ΠΕΡΙΠΑΤΗΡΑ",
                4,"ΜΟΝΙΜΑ ΑΝΑΜΜΕΝΟ ΦΩΣ",
                5,"ΕΞΑΣΦΑΛΙΣΗ ΣΥΜΠΛΗΡΩΜΑΤΙΚΩΝ ΣΤΗΡΙΓΜΑΤΩΝ \n ΑΣΦΑΛΕΙΑΣ ΣΤΟ ΜΠΑΝΙΟ",
                6,"ΣΥΝΕΧΗΣ ΠΑΡΑΜΟΝΗ ΣΥΝΟΔΟΥ"

        }));
        lista.add(new ItemsRV("ΥΨΗΛΟΣ ΚΙΝΔΥΝΟΣ ΠΤΩΣΗΣ", "ypsilos_kindynos_ptwshs", MULTI_TYPE, new Object[]{
                1,"ΧΟΡΗΓΗΣΗ ΕΝΤΥΠΟΥ ΝΥ_Ε 002-1_ΟΔΗΓΙΕΣ \n ΠΡΟΛΗΨΗΣ ΠΤΩΣΕΩΝ" ,
                2,"ΕΝΘΑΡΡΥΝΣΗ ΓΙΑ ΣΥΜΜΕΤΟΧΗ ΣΕ ΛΕΙΤΟΥΡΓΙΚΕΣ \nΔΡΑΣΤΗΡΙΟΤΗΤΕΣ(κινησιοθεραπεία-φυσιοθεραπεία ανάλογα με την \n αιτία εισόδου-έμφαση στη μυϊκή ενδυνάμωση και στην ισορροπία)" ,
                3,"ΕΦΑΡΜΟΓΗ ΠΕΡΙΔΕΣΕΩΝ ΑΣΦΑΛΕΙΑΣ",
                4,"ΕΦΑΡΜΟΓΗ ΚΙΚΛΙΔΩΜΑΤΩΝ",
                5,"ΑΞΙΟΛΟΓΗΣΗ ΤΩΝ ΕΠΙΔΡΑΣΕΩΝ ΤΩΝ ΦΑΡΜΑΚΩΝ ΠΟΥ\nΑΥΞΑΝΟΥΝ ΤΟΝ ΚΙΝΔΥΝΟ ΠΤΩΣΗΣ ΤΟΥ ΑΣΘΕΝΗ(καταγραφή στη νοσηλευτική λογοδοσία του Θεράποντος Ιατρού)",
                6,"ΣΥΝΕΧΗΣ ΠΑΡΑΜΟΝΗ ΣΥΝΟΔΟΥ"

        }));

        lista.add(new ItemsRV("Δεν είναι εφικτή \nη παραμονή του συνοδού\n", "paramonh_synodoy", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Ονοματεπώνυμο συνοδού", "syn_name", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Δεν υπάρχει συνοδός", "den_yparxei_synodos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Σχόλια", "remarks", "", EDITTEXT_ITEM, KEIMENO));

        return lista;


    }

}



