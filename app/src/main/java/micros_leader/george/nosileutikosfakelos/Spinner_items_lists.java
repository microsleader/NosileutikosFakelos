package micros_leader.george.nosileutikosfakelos;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.Spinner_item;

public class Spinner_items_lists {

    public static ArrayList <Spinner_item> getEidosEisagogisLista (){

        ArrayList <Spinner_item> lista = new ArrayList<>();


        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Τακτική"));
        lista.add(new Spinner_item(2,"Επείγουσα"));

        return lista;
    }

    public static ArrayList <Spinner_item> getDermaLista () {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Φυσιολογικό"));
        lista.add(new Spinner_item(2,"Κυανωτικό"));
        lista.add(new Spinner_item(3,"Ικτερικό"));
        lista.add(new Spinner_item(4,"Εξάνθημα"));
        lista.add(new Spinner_item(5,"Εκδορές"));
        lista.add(new Spinner_item(6,"Εκχυμώσεις"));
        lista.add(new Spinner_item(7,"Πετέχειες"));

        return lista;
    }

    public static ArrayList<Spinner_item> getTroposMetaforasLista() {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Περιπατητικός"));
        lista.add(new Spinner_item(2,"Τροχήλατο κάθισμα"));
        lista.add(new Spinner_item(3,"Φορείο"));
        lista.add(new Spinner_item(4,"Σανίδα/Φορείο"));

        return lista;
    }


    public static ArrayList<Spinner_item> getTisPliroforiesDideiLista() {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Ο ασθενής "));
        lista.add(new Spinner_item(2,"Η οικογένεια"));
        lista.add(new Spinner_item(3,"Ο Συνοδός"));
        lista.add(new Spinner_item(4,"Ο ασθενής / Συνοδός"));

        return lista;
    }


    public static ArrayList<Spinner_item> getAllaNosimata() {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ΑΥ"));
        lista.add(new Spinner_item(2,"#"));
        lista.add(new Spinner_item(3,"Υποθηρεοειδισμο"));
        lista.add(new Spinner_item(4,"ΟΝΑ"));
        lista.add(new Spinner_item(5,"ΚΑ"));
        lista.add(new Spinner_item(6,"ΟΕΜ"));
        lista.add(new Spinner_item(7,"ήπια νεφρ. ανεπάρκεια "));
        lista.add(new Spinner_item(8,"Αρθροπλαστική"));
        lista.add(new Spinner_item(9,"Αγγειοπλαστική"));

        return lista;
    }


    public static ArrayList<Spinner_item> getVimatodotis_apinidotis() {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Βηματοδότης"));
        lista.add(new Spinner_item(2,"Απινιδωτής"));


        return lista;
    }


    public static ArrayList<Spinner_item> getNosileutiki_logodosia_vardies() {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Πρωινή βάρδια"));
        lista.add(new Spinner_item(2,"Απογευματινή βάρδια"));
        lista.add(new Spinner_item(3,"Βραδινή βάρδια"));


        return lista;
    }


    public static ArrayList<Spinner_item> getOnlineAimokatharsi() {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"online Π.Φ."));
        lista.add(new Spinner_item(2,"online Μ.Φ."));
        lista.add(new Spinner_item(3,"κλασσική HD"));


        return lista;
    }


    public static ArrayList <Spinner_item> getYesNo(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Ναι"));
        lista.add(new Spinner_item(2,"Όχι"));

        return lista;
    }



    public static ArrayList <Spinner_item> getRightLeft(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Δεξιά"));
        lista.add(new Spinner_item(2,"Αριστερά"));
        lista.add(new Spinner_item(3,"Ύπτια"));
        lista.add(new Spinner_item(4,"Πρηνή"));

        return lista;
    }

    public static ArrayList <Spinner_item> getMonimoProsorino(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Μόνιμο"));
        lista.add(new Spinner_item(2,"Προσωρινό"));

        return lista;
    }




    public static ArrayList <Spinner_item> getLoutroLista(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Γενικό"));
        lista.add(new Spinner_item(2,"Κεφαλής"));
        lista.add(new Spinner_item(3,"Τοπικό"));

        return lista;
    }



    public static ArrayList <Spinner_item> getIxous(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ΚΦ"));
        lista.add(new Spinner_item(2,"ΑΥΞΗΜΕΝΟΙ"));
        lista.add(new Spinner_item(3,"ΜΕΙΩΜΕΝΟΙ"));
        lista.add(new Spinner_item(4,"ΑΠΟΝΤΕΣ"));

        return lista;
    }


    public static ArrayList <Spinner_item> getTipousKilias(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Μαλακή"));
        lista.add(new Spinner_item(2,"Σκληρή"));
        lista.add(new Spinner_item(3,"Διογκωμένη"));
        lista.add(new Spinner_item(4,"Επώδυνη"));

        return lista;
    }


    public static ArrayList <Spinner_item> getVardiesLista(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(1,"07:00 - 15:00  ΠΡΩΙΝΗ ΒΑΡΔΙΑ"));
        lista.add(new Spinner_item(2,"15:00 - 21:00  ΑΠΟΓΕΥΜΑΤΙΝΗ ΒΑΡΔΙΑ"));
        lista.add(new Spinner_item(3,"21:00 - 07:00  ΒΡΑΔΙΝΗ ΒΑΡΔΙΑ"));

        return lista;
    }



    public static ArrayList <Spinner_item> getAnoigmaMation(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Καμία αντίδραση    1"));
        lista.add(new Spinner_item(2,"Σε πόνο            2"));
        lista.add(new Spinner_item(3,"Σε πρόσταγμα       3"));
        lista.add(new Spinner_item(4,"Αυθορμητα          4"));

        return lista;
    }

    public static ArrayList <Spinner_item> getOmilia(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Καμία αντίδραση      /χωρίς επαφή   1"));
        lista.add(new Spinner_item(2,"Ακατάληπτοι ήχοι     /χωρίς επαφή   2"));
        lista.add(new Spinner_item(3,"Ακατανόητες λέξεις   /πιθανή επαφή  3"));
        lista.add(new Spinner_item(4,"Συγχητική            /πιθανή επαφή  4"));
        lista.add(new Spinner_item(5,"Προσανατολισμένη     /σαφής επαφή   5"));

        return lista;
    }


    public static ArrayList <Spinner_item> getKinitikiAntidrasi(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Καμία αντίδραση                      1"));
        lista.add(new Spinner_item(2,"Κινησεις απεγκεφλισμού (εκτάσεις)    2"));
        lista.add(new Spinner_item(3,"Κινησεις αποφλολιωσης (κάμψεις)      3"));
        lista.add(new Spinner_item(4,"Αντιδρά στο επώδυνο ερέθισμα         4"));
        lista.add(new Spinner_item(5,"εντοπίζει στο επώδυνο ερέθισμα       5"));
        lista.add(new Spinner_item(6,"Ακολουθεί παραγγελίες                6"));
        return lista;
    }




    public static ArrayList <Spinner_item> getAkra(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Αριστερό άνω άκρο"));
        lista.add(new Spinner_item(2,"Δεξί άνω άκρο"));
        lista.add(new Spinner_item(3,"Αριστερό κάτω άκρο"));
        lista.add(new Spinner_item(4,"Δεξί κάτω άκρο"));
        lista.add(new Spinner_item(5,"Άλλο"));


        return lista;
    }



    public static ArrayList <Spinner_item> getSfigmoAkrou(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"x"));
        lista.add(new Spinner_item(2,"D"));
        lista.add(new Spinner_item(3,"+"));
        lista.add(new Spinner_item(4,"++"));

        return lista;
    }


    public static ArrayList <Spinner_item> getEidosMetron () {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Φυσιολογικό"));
        lista.add(new Spinner_item(2,"Κυανωτικό"));
        lista.add(new Spinner_item(3,"Ικτερικό"));
        lista.add(new Spinner_item(4,"Εξάνθημα"));
        lista.add(new Spinner_item(5,"Εκδορές"));
        lista.add(new Spinner_item(6,"Εκχυμώσεις"));
        lista.add(new Spinner_item(7,"Πετέχειες"));

        return lista;

    }


    public static ArrayList <Spinner_item> getSitisi_sinodou () {

        ArrayList<Spinner_item> lista = new ArrayList<>();


        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Π-Γ-Δ"));
        lista.add(new Spinner_item(2,"Γ-Δ"));
        lista.add(new Spinner_item(3,"Π"));
        lista.add(new Spinner_item(4,"Γ"));
        lista.add(new Spinner_item(5,"Δ"));


        return lista;
    }


    public static ArrayList<Spinner_item> getMetraLoimokseisLista() {

        ArrayList<Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Πολυανθεκτικά"));
        lista.add(new Spinner_item(2,"ουδετεροπενικου και ανοσοκατασταλμένου"));
        lista.add(new Spinner_item(3,"Ουδετεροπεπτικού"));
        lista.add(new Spinner_item(4,"Μέτρα επαφής"));
        lista.add(new Spinner_item(5,"Σταγονιδίων"));
        lista.add(new Spinner_item(6,"MDR"));


        return lista;
    }




    public static ArrayList <Spinner_item> getZotikaVardies(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0, ""));
        lista.add(new Spinner_item(1, "01:00"));
        lista.add(new Spinner_item(2, "02:00"));
        lista.add(new Spinner_item(3, "03:00"));
        lista.add(new Spinner_item(4, "04:00"));
        lista.add(new Spinner_item(5, "05:00"));
        lista.add(new Spinner_item(6, "06:00"));
        lista.add(new Spinner_item(7, "07:00"));
        lista.add(new Spinner_item(8, "08:00"));
        lista.add(new Spinner_item(9, "09:00"));
        lista.add(new Spinner_item(10, "10:00"));
        lista.add(new Spinner_item(11, "11:00"));
        lista.add(new Spinner_item(12, "12:00"));
        lista.add(new Spinner_item(13, "13:00"));
        lista.add(new Spinner_item(14, "14:00"));
        lista.add(new Spinner_item(15, "15:00"));
        lista.add(new Spinner_item(16, "16:00"));
        lista.add(new Spinner_item(17, "17:00"));
        lista.add(new Spinner_item(18, "18:00"));
        lista.add(new Spinner_item(19, "19:00"));
        lista.add(new Spinner_item(20, "20:00"));
        lista.add(new Spinner_item(21, "21:00"));
        lista.add(new Spinner_item(22, "22:00"));
        lista.add(new Spinner_item(22, "23:00"));
        lista.add(new Spinner_item(24, "00:00"));


        return lista;
    }

    public static ArrayList <Spinner_item> getVathmousMeSin(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"+"));
        lista.add(new Spinner_item(2,"++"));
        lista.add(new Spinner_item(3,"+++"));

        return lista;
    }

    public static ArrayList <Spinner_item> getVathmoiPonou(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"0","vathmoi_ponou/ponos0.png"));
        lista.add(new Spinner_item(2,"2","vathmoi_ponou/ponos2.png"));
        lista.add(new Spinner_item(3,"4","vathmoi_ponou/ponos4.png"));
        lista.add(new Spinner_item(4,"6","vathmoi_ponou/ponos6.png"));
        lista.add(new Spinner_item(5,"8","vathmoi_ponou/ponos8.png"));
        lista.add(new Spinner_item(6,"10","vathmoi_ponou/ponos10.png"));


        return lista;
    }


    public static ArrayList <Spinner_item> getSinthikes_vimatodoti(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"mode VVI"));
        lista.add(new Spinner_item(2,"mode DDD"));
        lista.add(new Spinner_item(3,"mode AAI"));
        lista.add(new Spinner_item(4,"κλειστός"));
        lista.add(new Spinner_item(5,"stand by"));

        return lista;
    }



    public static ArrayList <Spinner_item> getIABP_mode(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"1:1"));
        lista.add(new Spinner_item(2,"1:2"));
        lista.add(new Spinner_item(3,"1:4"));

        return lista;
    }


    public static ArrayList <Spinner_item> getIABP_side(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1, "ΔΕ μηριαία"));
        lista.add(new Spinner_item(2, "ΑΡ μηριαία"));

        return lista;
    }

    public static ArrayList <Spinner_item> getMegethosKoris(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"2 mm"));
        lista.add(new Spinner_item(2,"3 mm"));
        lista.add(new Spinner_item(3,"4 mm"));
        lista.add(new Spinner_item(4,"5 mm"));
        lista.add(new Spinner_item(5,"6 mm"));
        lista.add(new Spinner_item(6,"7 mm"));
        lista.add(new Spinner_item(7,"8 mm"));
        lista.add(new Spinner_item(8,"9 mm"));

        return lista;
    }

    public static ArrayList <Spinner_item> getSymbolsKoris(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"+"));
        lista.add(new Spinner_item(2,"-"));

        return lista;
    }



    public static ArrayList <Spinner_item> getTroposAerismou(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Χωρίς οξυγόνο"));
        lista.add(new Spinner_item(2,"Ριν. κάνουλα"));
        lista.add(new Spinner_item(3,"Μάσκα απλή"));
        lista.add(new Spinner_item(4,"Μάσκα Venturi"));
        lista.add(new Spinner_item(5,"Μάσκα μη επανεισπ."));
        lista.add(new Spinner_item(6,"BiPAP"));
        lista.add(new Spinner_item(7,"Hi - Flow"));
        lista.add(new Spinner_item(8,"Μάσκα τραχειστ."));
        lista.add(new Spinner_item(9,"T-tube"));
        lista.add(new Spinner_item(10,"Βαλβίδα ομιλίας"));
        lista.add(new Spinner_item(11,"Mech. ventilation"));


        return lista;
    }



    public static ArrayList <Spinner_item> getAllosTroposAerismou(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"V-CMV"));
        lista.add(new Spinner_item(2,"V-SIMV"));
        lista.add(new Spinner_item(3,"P-SIMV"));
        lista.add(new Spinner_item(4,"VS"));
        lista.add(new Spinner_item(5,"SPONT"));


        return lista;
    }

     public static ArrayList <Spinner_item> getKardiakosRithmos(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Φ.Κ."));
        lista.add(new Spinner_item(2,"Κ.Μ."));
        lista.add(new Spinner_item(3,"Κολπ. πτερυγ."));
        lista.add(new Spinner_item(4,"Ταχυκαρδία"));
        lista.add(new Spinner_item(5,"Κολποκοιλιακός αποκλεισμός"));
        lista.add(new Spinner_item(6,"Ασυστολία"));
        lista.add(new Spinner_item(7,"Κοιλιακη μαρμαριγή"));
        lista.add(new Spinner_item(8,"Κοιλιακή ταχυκαρδία"));
        lista.add(new Spinner_item(9,"Ιδιοκοιλιακός ρυθμός"));


        return lista;
    }



    public static ArrayList <Spinner_item> getThesiSomatos(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Δεξιά"));
        lista.add(new Spinner_item(2,"Αριστερά"));
        lista.add(new Spinner_item(3,"Ημι-fowler"));
        lista.add(new Spinner_item(4,"Κάθισμα - κρεβάτι"));
        lista.add(new Spinner_item(5,"Κάθισμα - καρέκλα"));
        lista.add(new Spinner_item(6,"Trendelenburg"));


        return lista;
    }


    public static ArrayList <Spinner_item> getTypeMedicine(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Συστημ. φαρμ. αγωγή"));
        lista.add(new Spinner_item(2,"Ορός"));
        lista.add(new Spinner_item(3,"Εφάπαξ φάρμακο"));
        lista.add(new Spinner_item(4,"Άλλο φάρμακο"));
        lista.add(new Spinner_item(5,"Μετάγγιση"));



        return lista;
    }


    public static ArrayList <Spinner_item> getAntlia(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Αντλία έγχυσης φαρμάκων"));
        lista.add(new Spinner_item(2,"Αντλία σίτισης"));
        lista.add(new Spinner_item(3,"Αντλία πόνου"));


        return lista;
    }




    public static ArrayList <Spinner_item> getVardiesForProgrammatismo_p_a(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Πρωί 14:00"));
        lista.add(new Spinner_item(2,"Απόγευμα 22:00"));

        return lista;
    }

    public static ArrayList <Spinner_item> getEidiMetronEpafis(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Βασικές προφυλάξεις"));
        lista.add(new Spinner_item(2,"μέτρα σταγονιδίων"));
        lista.add(new Spinner_item(3,"μέτρα επαφής"));
        lista.add(new Spinner_item(4,"αερογενείς προφυλάξεις"));
        lista.add(new Spinner_item(5,"προστασία ουδετεροπενικών- ανοσοκατασταλμένων"));
        lista.add(new Spinner_item(6,"πολυανθεκτικά"));

        return lista;
    }



    public static ArrayList <Spinner_item> getAimokatharsi_aimodiathisi(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ΑΙΜΟΚΑΘΑΡΣΗ"));
        lista.add(new Spinner_item(2,"ΑΙΜΟΔΙΑΔΙΗΘΗΣΗ"));
        lista.add(new Spinner_item(3,"ΑΙΜΟΚΑΘ + ΑΙΜΟΔΙΑΔ"));

        return lista;
    }

    public static ArrayList <Spinner_item> getDuration_aim(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"2:00"));
        lista.add(new Spinner_item(2,"2:15"));
        lista.add(new Spinner_item(3,"2:30"));
        lista.add(new Spinner_item(4,"2:45"));
        lista.add(new Spinner_item(5,"3:00"));
        lista.add(new Spinner_item(6,"3:15"));
        lista.add(new Spinner_item(7,"3:30"));
        lista.add(new Spinner_item(8,"3:45"));
        lista.add(new Spinner_item(9,"4:00"));
        lista.add(new Spinner_item(10,"4:15"));
        lista.add(new Spinner_item(11,"4:30"));
        lista.add(new Spinner_item(12,"4:45"));
        lista.add(new Spinner_item(13,"5:00"));

        return lista;
    }


    public static ArrayList <Spinner_item> getFistoulaOrMosxeuma(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Φίστουλα"));
        lista.add(new Spinner_item(2,"Μόσχευμα"));

        return lista;
    }



    public static ArrayList <Spinner_item> get_geuma(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Πρωινό"));
        lista.add(new Spinner_item(2,"Δεκατιανό"));
        lista.add(new Spinner_item(3,"Μεσημεριανό"));
        lista.add(new Spinner_item(4,"Απογευματινό"));
        lista.add(new Spinner_item(5,"Βραδινό"));
        lista.add(new Spinner_item(6,"Προ ύπνου"));
        lista.add(new Spinner_item(7,"Άσκηση"));


        return lista;
    }




    public static ArrayList <Spinner_item> get_periferiki_grammi_megethos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"14G"));
        lista.add(new Spinner_item(2,"16G"));
        lista.add(new Spinner_item(3,"18G"));
        lista.add(new Spinner_item(4,"20G"));
        lista.add(new Spinner_item(5,"22G"));
        lista.add(new Spinner_item(6,"24G"));


        return lista;
    }


    public static ArrayList <Spinner_item> get_periferiki_grammi_thesi_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ΔΕ άκρα χείρα"));
        lista.add(new Spinner_item(2,"ΔΕ αντιβράχιο"));
        lista.add(new Spinner_item(3,"ΔΕ βραχίονας"));
        lista.add(new Spinner_item(4,"ΑΡ άκρα χείρα"));
        lista.add(new Spinner_item(5,"ΑΡ αντιβράχιο"));
        lista.add(new Spinner_item(6,"ΑΡ βραχίονας"));


        return lista;
    }


    public static ArrayList <Spinner_item> get_artiriaki_grammi_thesi_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1, "ΔΕ κερκιδική"));
        lista.add(new Spinner_item(2, "ΔΕ βραχιόνια"));
        lista.add(new Spinner_item(3, "ΔΕ μηριαία"));
        lista.add(new Spinner_item(4, "ΑΡ κερκιδική"));
        lista.add(new Spinner_item(5, "ΑΡ βραχιόνια"));
        lista.add(new Spinner_item(6, "ΑΡ μηριαία"));


        return lista;
    }


    public static ArrayList <Spinner_item> get_artiriaki_grammi_megethos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"T20Gx51mm"));
        lista.add(new Spinner_item(2,"T20Gx32mm"));
        lista.add(new Spinner_item(3,"5cm"));
        lista.add(new Spinner_item(4,"8cm"));

        return lista;
    }


    public static ArrayList <Spinner_item> get_artiriaki_grammi_eidos_choices(){

            ArrayList <Spinner_item> lista = new ArrayList<>();

            lista.add(new Spinner_item(0,""));
            lista.add(new Spinner_item(1,"Arrow"));
            lista.add(new Spinner_item(2,"Abbocath"));

            return lista;
        }




    public static ArrayList <Spinner_item> get_folley_eidos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"2way latex"));
        lista.add(new Spinner_item(2,"2way silicone"));
        lista.add(new Spinner_item(3,"3way latex"));
        lista.add(new Spinner_item(4,"3way silicone"));
        lista.add(new Spinner_item(5,"3way dufour"));
        lista.add(new Spinner_item(6,"3way couvelaire"));

        return lista;
    }


    public static ArrayList <Spinner_item> get_folley_megethos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Νο. 12"));
        lista.add(new Spinner_item(2,"Νο. 14"));
        lista.add(new Spinner_item(3,"Νο. 16"));
        lista.add(new Spinner_item(4,"Νο. 18"));
        lista.add(new Spinner_item(5,"Νο. 20"));
        lista.add(new Spinner_item(6,"Νο. 22"));
        lista.add(new Spinner_item(7,"Νο. 24"));

        return lista;
    }






    public static ArrayList <Spinner_item> get_levin_eidos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"1way"));
        lista.add(new Spinner_item(2,"2way"));


        return lista;
    }


    public static ArrayList <Spinner_item> get_levin_megethos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Νο. 14"));
        lista.add(new Spinner_item(2,"Νο. 16"));
        lista.add(new Spinner_item(3,"Νο. 18"));


        return lista;
    }


    public static ArrayList <Spinner_item> get_kentr_eidos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"1lumen"));
        lista.add(new Spinner_item(2,"2lumen"));
        lista.add(new Spinner_item(3,"3lumen"));
        lista.add(new Spinner_item(4,"4lumen"));
        lista.add(new Spinner_item(5,"1lumen picc line"));
        lista.add(new Spinner_item(6,"2lumen picc line"));


        return lista;
    }

    public static ArrayList <Spinner_item> kentr_thesi_and_kath_aim_and_thikari_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ΔΕ σφαγίτιδα"));
        lista.add(new Spinner_item(2,"ΔΕ υποκλείδια"));
        lista.add(new Spinner_item(3,"ΔΕ μηριαία"));
        lista.add(new Spinner_item(4,"ΑΡ σφαγίτιδα"));
        lista.add(new Spinner_item(5,"ΑΡ υποκλείδια"));
        lista.add(new Spinner_item(6,"ΑΡ μηριαία"));
        lista.add(new Spinner_item(7,"ΔΕ βραχιόνια"));
        lista.add(new Spinner_item(8,"ΑΡ βραχιόνια"));


        return lista;
    }



    public static ArrayList <Spinner_item> get_kath_aim_eidos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"2lumen"));
        lista.add(new Spinner_item(2,"3lumen"));

        return lista;
    }











    public static ArrayList <Spinner_item> get_paroxeteusi_eidos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"κεραμιδάκι"));
        lista.add(new Spinner_item(2,"penrose"));
        lista.add(new Spinner_item(3,"Redon"));
        lista.add(new Spinner_item(4,"Nelaton"));
        lista.add(new Spinner_item(5,"Redovac"));
        lista.add(new Spinner_item(6,"Blake"));
        lista.add(new Spinner_item(7,"Billow"));

        return lista;
    }



    public static ArrayList <Spinner_item> get_paroxeteusi_perigrafi_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Bomba"));
        lista.add(new Spinner_item(2,"Jacksson"));
        lista.add(new Spinner_item(3,"Σάκος στομίας"));

        return lista;
    }


    public static ArrayList <Spinner_item> get_episk_ferei_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ΟΜΣΣ"));
        lista.add(new Spinner_item(2,"ΘΜΣΣ"));

        return lista;
    }

     public static ArrayList <Spinner_item> end_sol_size_choices(){

            ArrayList <Spinner_item> lista = new ArrayList<>();

            lista.add(new Spinner_item(0,""));
            lista.add(new Spinner_item(1,"Νο.61/2"));
            lista.add(new Spinner_item(2,"Νο.7"));
            lista.add(new Spinner_item(3,"Νο71/2"));
            lista.add(new Spinner_item(4,"Νο.8"));
            lista.add(new Spinner_item(5,"Νο.81/2"));
            lista.add(new Spinner_item(6,"Νο.9"));

            return lista;
    }


    public static ArrayList <Spinner_item> sol_trax_size_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Νο.61/2"));
        lista.add(new Spinner_item(2,"Νο.7"));
        lista.add(new Spinner_item(3,"Νο71/2"));
        lista.add(new Spinner_item(4,"Νο.8"));
        lista.add(new Spinner_item(5,"Νο.81/2"));
        lista.add(new Spinner_item(6,"Νο.9"));
        lista.add(new Spinner_item(7,"Νο.91/2"));

        return lista;
    }





    public static ArrayList <Spinner_item> get_rin_aer_size_choices_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Νο.6"));
        lista.add(new Spinner_item(2,"Νο.7"));
        lista.add(new Spinner_item(3,"Νο.8"));
        lista.add(new Spinner_item(4,"Νο.9"));

        return lista;
    }



    public static ArrayList <Spinner_item> get_stoma_aer_size_choices_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"Νο.0"));
        lista.add(new Spinner_item(2,"Νο.1"));
        lista.add(new Spinner_item(3,"Νο.2"));
        lista.add(new Spinner_item(4,"Νο.3"));
        lista.add(new Spinner_item(5,"Νο.4"));
        lista.add(new Spinner_item(6,"Νο.5"));
        lista.add(new Spinner_item(7,"Νο.6"));

        return lista;
    }






    public static ArrayList <Spinner_item> get_thikari_eidos_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ηλεκτρόδια βηματοδότη"));
        lista.add(new Spinner_item(2,"swan ganz"));

        return lista;
    }



    public static ArrayList <Spinner_item> get_thikari_antlia_thesi_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ΔΕ μηριαία αρτηρία"));
        lista.add(new Spinner_item(2,"ΑΡ μηριαία αρτηρία"));

        return lista;
    }



    public static ArrayList <Spinner_item> get_port_a_cath_thesi_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"ΔΕ υποκλείδιο"));
        lista.add(new Spinner_item(2,"ΑΡ υποκλείδιο"));

        return lista;
    }



    public static ArrayList <Spinner_item> get_port_a_cath_gripper_choices(){

        ArrayList <Spinner_item> lista = new ArrayList<>();

        lista.add(new Spinner_item(0,""));
        lista.add(new Spinner_item(1,"19Gx0.75''"));
        lista.add(new Spinner_item(2,"20Gx0.75''"));
        lista.add(new Spinner_item(3,"19Gx1''"));
        lista.add(new Spinner_item(4,"20Gx11/4''"));

        return lista;
    }






}
