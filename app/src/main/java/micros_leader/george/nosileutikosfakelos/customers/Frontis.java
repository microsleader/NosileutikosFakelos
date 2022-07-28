package micros_leader.george.nosileutikosfakelos.customers;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.BasicList;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.MyApplication;
import micros_leader.george.nosileutikosfakelos.Spinner_items_lists;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;
import micros_leader.george.nosileutikosfakelos.Utils;

import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.CHECKBOX_TYPE;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.EDIT_TEXT_TYPE;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.SPINNER_TYPE_NEW;
import static micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV.TEXTVIEW_TYPE;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.AKERAIOS;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.CHECKBOX_ITEM;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.DEKADIKOS;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.DEKADIKOS_WITH_NEGATIVE;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.KEIMENO;
import static micros_leader.george.nosileutikosfakelos.StaticFields.CHECKBOX_TYPE_READ_ONLY_VALUE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.EDITTEXT_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.SPINNER_ITEM_NEW;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TABLE_NO_ELEMENT;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TABLE_NO_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_CLOCK_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_DATE_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_ITEM;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_ITEM_READ_ONLY_VALUE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_MEDICINE_TYPE;

public class Frontis {



    public static ArrayList<ItemsRV> getMedicalInsLista(boolean isDoctor) {

        BasicList lista = new BasicList();

        if (isDoctor){

            lista.add(new ItemsRV("Έτος", "Year",  SPINNER_ITEM_NEW, "Years"));
            lista.add(new ItemsRV("Μήνας", "Month",  SPINNER_ITEM_NEW, "Month"));

            lista.add(new ItemsRV("Ιατρός", "doctorid", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Είδος αιμοκάθαρσης", "eidos_aim",  SPINNER_ITEM_NEW,"Nursing_items_med_instr_eidos_aim"));
            lista.add(new ItemsRV("όγκος υποκατάστασης (k)", "ipok_ogkos", SPINNER_ITEM_NEW,"Nursing_items_ogkos_ipok"));
            lista.add(new ItemsRV("Συχνότητα αιμοκ.\nανά εβδομάδα", "sixnotita_aim", "", EDITTEXT_ITEM,DEKADIKOS));

            lista.add(new ItemsRV("Διάρκεια", "duration_aim_ID", SPINNER_ITEM_NEW, Spinner_items_lists.getDuration_aim()));

            lista.add(new ItemsRV("Φίλτρο", "Filter",  SPINNER_ITEM_NEW,"Nursing_items_med_instr_filter"));
            lista.add(new ItemsRV("Αιματοκρίτης", "lot_filter", "",  EDITTEXT_ITEM,KEIMENO));

            lista.add(new ItemsRV("Αντιπηκτική αγωγή", "agogi",  SPINNER_ITEM_NEW,"Nursing_items_med_instr_agogi"));
            lista.add(new ItemsRV("Αντιπηκτική αγωγή 0", "agogi_zero", "",  EDITTEXT_ITEM,DEKADIKOS));
            lista.add(new ItemsRV("Αντιπηκτική αγωγή 120", "agogi120", "",  EDITTEXT_ITEM,DEKADIKOS));


            lista.add(new ItemsRV("Ροή αίματος (ml/min)", "roi_aima", "",  EDITTEXT_ITEM,DEKADIKOS));

            lista.add(new ItemsRV("Ροή διαλύματος (ml/min)", "roi_dialima", "",  EDITTEXT_ITEM,DEKADIKOS));
            lista.add(new ItemsRV("Διάλυμα - είδος", "dialima",  SPINNER_ITEM_NEW,"Nursing_items_med_instr_dialima"));
            lista.add(new ItemsRV("Αγωγιμότητα (mmol/L)", "agogimotita", "", EDITTEXT_ITEM,DEKADIKOS));
            lista.add(new ItemsRV("Προφίλ UF", "profile_uf", "", EDITTEXT_ITEM,DEKADIKOS));
            lista.add(new ItemsRV("Διττανθρακικά", "Dittanthrakika", "", EDITTEXT_ITEM,DEKADIKOS));
            lista.add(new ItemsRV("Θερμοκρασία", "temparture", "", EDITTEXT_ITEM,DEKADIKOS));
            lista.add(new ItemsRV("Paricalcitol i.v", "Paracalcitol", "", EDITTEXT_ITEM,KEIMENO));
            lista.add(new ItemsRV("Alfacalcidol i.v", "Alfacalcidol", "", EDITTEXT_ITEM,KEIMENO));

            lista.add(new ItemsRV("Εμβόλιο ηπ.Β", "embolio_b", "", TEXTVIEW_ITEM, TABLE_NO_TYPE));
            lista.add(new ItemsRV("Αντιγριπ. εμβόλιο", "embolio_antiFlu", "",TEXTVIEW_ITEM, TABLE_NO_TYPE));
            lista.add(new ItemsRV("Μέγιστη Αφυδάτωση (ml)", "rithmos_afidatosis", "", EDITTEXT_ITEM,DEKADIKOS));

            lista.add(new ItemsRV("Πρόσθετο βάρος", "additional_weight", "",  EDITTEXT_ITEM,DEKADIKOS));

            lista.add(new ItemsRV("Ξηρό βάρος", "ksiro_varos", "", EDITTEXT_ITEM,DEKADIKOS));
            lista.add(new ItemsRV("Max UF (ml/h)", "max_uf", "", EDITTEXT_ITEM,DEKADIKOS));
            lista.add(new ItemsRV("Βελόνες", "velonesID", SPINNER_ITEM_NEW, "Nursing_items_statheres_metr_velones"));
            lista.add(new ItemsRV("Epoetin", "epoetinID",  SPINNER_ITEM_NEW,"Nursing_items_med_instr_epoetin"));

            lista.add(new ItemsRV("Etelalcetide", "etelalcetide", "",  EDITTEXT_ITEM,KEIMENO));
            lista.add(new ItemsRV("Darbepoetin", "darbepoetin", "",  EDITTEXT_ITEM,KEIMENO));
            lista.add(new ItemsRV("Fe i.v", "Fe_ID",   SPINNER_ITEM_NEW,"Nursing_items_med_Fe_types"));
            lista.add(new ItemsRV("L-carnitine i.v", "Carnitine_ID", SPINNER_ITEM_NEW,"Nursing_items_med_L_carnitine"));
            lista.add(new ItemsRV("Vit B I.v", "vit_B_ID", SPINNER_ITEM_NEW,"Nursing_items_med_Vit_B"));
            lista.add(new ItemsRV("Λοιπά φάρμακα", "other_meds", "",  EDITTEXT_ITEM,KEIMENO));
            lista.add(new ItemsRV("Αλλεργίες", "allergies", "", EDITTEXT_ITEM,KEIMENO));

            lista.add(new ItemsRV("Εμβόλιο ηπ.Β", "embolio_b", "", TEXTVIEW_TYPE,TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Αντιγριπ. εμβόλιο", "embolio_antiFlu", "", TEXTVIEW_TYPE,TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβ.covid 19 δόση 1", "embolio_covid", "", TEXTVIEW_TYPE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβ.covid 19 δόση 2", "embolio_covid2", "", TEXTVIEW_TYPE,TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβ.covid 19 δόση 3", "embolio_covid3", "", TEXTVIEW_TYPE,TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβ.covid 19 δόση 4", "embolio_covid4", "", TEXTVIEW_TYPE,TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβόλιο πνευμονιόκοκου", "embolio_pneum", "", TEXTVIEW_TYPE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Άλλα εμβόλια", "other_vac", "", EDITTEXT_ITEM,KEIMENO));

            lista.add(new ItemsRV("Γενικές οδηγίες", "genikes_odigies", "",  EDITTEXT_ITEM,KEIMENO));

        }

        else {


            lista.add(new ItemsRV("Μήνας/Έτος", "Month", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Ιατρός", "doctorid", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Είδος αιμοκάθαρσης", "eidosName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("όγκος υποκατάστασης (k)", "ipokName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Συχνότητα αιμοκ.\nανά εβδομάδα", "sixnotita_aim", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Διάρκεια", "durationName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Φίλτρο", "filName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Αιματοκρίτης", "lot_filter", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Αντιπηκτική αγωγή", "agogiName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Αντιπηκτική αγωγή 0", "agogi_zero", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Αντιπηκτική αγωγή 120", "agogi120", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));


            lista.add(new ItemsRV("Ροή αίματος (ml/min)", "roi_aima", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Ροή διαλύματος (ml/min)", "roi_dialima", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Διάλυμα - είδος", "dialeimaName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Αγωγιμότητα (mmol/L)", "agogimotita", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Προφίλ UF", "profile_uf", "",TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Διττανθρακικά", "Dittanthrakika", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Θερμοκρασία", "temparture", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Paricalcitol i.v", "Paracalcitol", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Alfacalcidol i.v", "Alfacalcidol", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Εμβόλιο ηπ.Β", "embolio_b", "", TEXTVIEW_TYPE));
            lista.add(new ItemsRV("Αντιγριπ. εμβόλιο", "embolio_antiFlu", "", TEXTVIEW_TYPE));
            lista.add(new ItemsRV("Μέγιστη Αφυδάτωση (ml)", "rithmos_afidatosis", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Πρόσθετο βάρος", "additional_weight", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Ξηρό βάρος", "ksiro_varos", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Max UF (ml/h)", "max_uf", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Βελόνες", "velonesName", "",TEXTVIEW_ITEM_READ_ONLY_VALUE ));

            lista.add(new ItemsRV("Epoetin", "epoName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Etelalcetide", "etelalcetide", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("darbepoetin", "darbepoetin", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Fe i.v", "feName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("L-carnitine i.v", "CarnitineName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Vit B I.v", "VitB_Name", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Λοιπά φάρμακα", "other_meds", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
            lista.add(new ItemsRV("Αλλεργίες", "allergies", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

            lista.add(new ItemsRV("Εμβόλιο ηπ.Β", "embolio_b", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Αντιγριπ. εμβόλιο", "embolio_antiFlu", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβ.covid 19 δόση 1", "embolio_covid", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβ.covid 19 δόση 2", "embolio_covid2", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβ.covid 19 δόση 3", "embolio_covid3", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβ.covid 19 δόση 4", "embolio_covid4", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Εμβόλιο πνευμονιόκοκου", "embolio_pneum", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
            lista.add(new ItemsRV("Άλλα εμβόλια", "other_vac", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));


            lista.add(new ItemsRV("Γενικές οδηγίες", "genikes_odigies", "", TEXTVIEW_TYPE));
        }

        return lista.getList();


    }




    public static ArrayList<ItemsRV> getStathersMetriseisLista() {

        BasicList lista = new BasicList();

        //lista.add(new ItemsRV("Ημ/νία","datestr", TEXTVIEW_ITEM_READ_ONLY_VALUE));

       // lista.add(new ItemsRV("Προγρ. \nώρα έναρξης:", "schedule_time_start","" , TEXTVIEW_CLOCK_TYPE ));
        lista.add(new ItemsRV("Ώρα έναρξης:", "time_start", "", TEXTVIEW_CLOCK_TYPE));
        lista.add(new ItemsRV("Διάρκεια:", "duration_aim_ID", SPINNER_ITEM_NEW, Spinner_items_lists.getDuration_aim()));
        lista.add(new ItemsRV("Ώρα λήξης:", "time_finish", "", TEXTVIEW_CLOCK_TYPE));

        lista.add(new ItemsRV("Αρχικό βάρος εισόδου(gr):", "arxiko_varos", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Ιματισμός:", "imatismos", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Τελικό βάρος εισόδου(gr):", "teliko_arxiko_varos", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Ξηρό βάρος(gr):", "ksiro_varos", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Διαφορά βάρους(gr):", "diafora_varous", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Πρόσθετο βάρος(gr):", "additional_weight", "", EDITTEXT_ITEM,DEKADIKOS));

        lista.add(new ItemsRV("Στόχος UF:", "target_UF", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Ρυθμός UF", "UF", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Τελικό στόχος UF", "final_UF", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Πραγματικό UF", "real_UF", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Βάρος εξόδου(gr):", "varos_exodou", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Ιματισμός εξόδου(gr):", "imatismos_exit", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Τελικό βάρος εξόδου(gr):", "teliko_varos_exodou", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));


        lista.add(new ItemsRV("Φίλτρο", "filtroID", SPINNER_ITEM_NEW, "Nursing_items_med_instr_filter"));
        lista.add(new ItemsRV("Διάλυμα", "dialimaID", SPINNER_ITEM_NEW, "Nursing_items_med_instr_dialima"));
        lista.add(new ItemsRV("Ηπαρίνη", "ipar_efapax", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Kt/V", "Kt_V", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("SPO2 (%)", "SPO2", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Θερμοκ. 2ωρο/αρχή ", "therm_arxi", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Θερμοκρασια 4ωρο/τέλος", "therm_telos", "", EDITTEXT_ITEM, DEKADIKOS));
       // lista.add(new ItemsRV("fistula", "fistulaID", SPINNER_ITEM_NEW, "Nursing_items_statheres_fistoula_and_mosxeuma"));
       // lista.add(new ItemsRV("Μόσχευμα", "mosxeumaID", SPINNER_ITEM_NEW, "Nursing_items_statheres_fistoula_and_mosxeuma"));
        lista.add(new ItemsRV( "Φίστουλα-\n Μόσχευμα","fistulaOrMosxeuma",  SPINNER_ITEM_NEW, Spinner_items_lists.getFistoulaOrMosxeuma()));
        lista.add(new ItemsRV("Βελόνες", "velonesID", SPINNER_ITEM_NEW, "Nursing_items_statheres_metr_velones"));

        lista.add(new ItemsRV("SN-Y LINE ADAPTOR - Συνδετικό", "sindetiko", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Είδος αιμοκάθαρσης", "eidos_aimID", SPINNER_ITEM_NEW, "Nursing_items_med_instr_eidos_aim"));

        lista.add(new ItemsRV("Κεντρ. φλεβ.καθετηρας μονιμος", "flev_kathtiras_monimos", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Κεντρ. φλεβ.καθετηρας προσωρινος", "flev_kathtiras_prosorinos", false, CHECKBOX_ITEM));

        lista.add(new ItemsRV("Κεντρ. φλεβ.καθετηρας_κειμενο", "flev_kathtiras_text", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Χωρητ. ηπαρινισμού σκελών Α", "skelon_A", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Χωρητ. ηπαρινισμού σκελών Φ", "skelon_f", "", EDITTEXT_ITEM, KEIMENO));

        return lista.getList();


    }


    public static ArrayList<ItemsRV> getSinexeisMetriseisLista() {

        BasicList lista = new BasicList();

        lista.add(new ItemsRV("Ημ/νία και ώρα", "Date", "", TEXTVIEW_TYPE));
        lista.add(new ItemsRV("Συστολική πίεση (Hg)", "sis_piesi", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Διαστολική πίεση (mm)", "dias_piesi", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));

        lista.add(new ItemsRV("Σφύξεις:", "sfikseis", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Θερμοκρασία (°C)", "thermokrasia", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Ρυθμός υπερδιηθησης ανά ώρα(U.F.) (l/h):", "iperd", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Απώλεια βάρους:", "apoleia_varous", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Πίεση αρτ. γραμμής:", "piesi_art", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Πίεση φλεβ. γραμμής:", "piesi_flev", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("TMP:", "TMP", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Ρυθμός ροή αντλίας (ml/min):", "roi", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Αγωγιμότητα (mmol/L)", "agogimotita", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("HCO3 (ms/cm)","hco3", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Μετρήσεις-Παρεμβάσεις:", "paremvaseis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Παρατηρήσεις:", "paratiriseis", "", EDITTEXT_ITEM, KEIMENO));
        lista.add(new ItemsRV("Εγχύσεις - Φάρμακα", "egxiseis", "", EDITTEXT_ITEM, KEIMENO));

        lista.add(new ItemsRV("Vit - B", "vit_b", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("L - carnittine", "carnitine", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Alphacalcidol:", "alphacalcidol", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("EPO", "epo", false, CHECKBOX_ITEM));
//        lista.add(new ItemsRV("Epoetin alpha", "epo_alpha", false, CHECKBOX_ITEM));
//        lista.add(new ItemsRV("Epoetin zeta", "epo_zeta", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Darbepoetin", "epo_darbepoetin", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("etelalcetide", "etelalcetide", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Paricalcitol", "paricalcitol", false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Σίδηρος", "sidiros", false, CHECKBOX_ITEM));


        return lista.getList();

    }


    public static ArrayList<TableViewItem> getSigkentrotikaStatheresMetriseis() {


        BasicList lista = new BasicList();

        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Ημ/νία","datestr", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE).setStable_col(true));
        lista.add(new TableViewItem("Χρήστης","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Υπευθ. Ιατρός \nβάρδιας","ipefthinos_iatros_vardias", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Ώρα έναρξης" ,"time_start", TEXTVIEW_CLOCK_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ώρα λήξης","time_finish", TEXTVIEW_CLOCK_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Διάρκεια","duration_aim_ID", SPINNER_TYPE_NEW, Spinner_items_lists.getDuration_aim()));
        lista.add(new TableViewItem("Αρχικό βάρος\nεισόδου",  "arxiko_varos", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Ιματισμός","imatismos", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Τελικό βάρος\nεισόδου", "teliko_arxiko_varos", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ξηρό βάρος",  "ksiro_varos", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Διαφορά βάρους","diafora_varous", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Πρόσθετο βάρος ", "additional_weight", EDIT_TEXT_TYPE, DEKADIKOS));

                //"fistula", "Μόσχευμα",


        lista.add(new TableViewItem("Στόχος UF", "target_UF", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem( "Ρυθμός UF",  "UF", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Τελικό στόχος UF", "final_UF", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Πραγματικό UF","real_UF", EDIT_TEXT_TYPE, DEKADIKOS));

        lista.add(new TableViewItem("Βάρος εξόδου","varos_exodou", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Ιματισμός εξόδου","imatismos_exit", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Τελικό βάρος\nεξόδου","teliko_varos_exodou", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Φίλτρο","filtroID", SPINNER_TYPE_NEW, "Nursing_items_med_instr_filter"));
        lista.add(new TableViewItem("Διάλυμα","dialimaID", SPINNER_TYPE_NEW, "Nursing_items_med_instr_dialima"));
        lista.add(new TableViewItem("Ηπαρίνη","ipar_efapax", EDIT_TEXT_TYPE, KEIMENO));
        //lista.add(new TableViewItem("Θερμοκ. \nδιαλύματος (°C)","therm_dialimatos", EDIT_TEXT_TYPE, DEKADIKOS));

        lista.add(new TableViewItem(  "Kt/V","Kt_V", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem(  "SPO2","SPO2", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem( "Θερμοκ. 2ωρο/αρχή","therm_arxi",  EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem( "Θερμοκ. 4ωρο/τέλος ","therm_telos",  EDIT_TEXT_TYPE, DEKADIKOS));
        //lista.add(new TableViewItem("fistulaID", SPINNER_TYPE_NEW, "Nursing_items_statheres_fistoula_and_mosxeuma"));
        //lista.add(new TableViewItem("mosxeumaID", SPINNER_TYPE_NEW, "Nursing_items_statheres_fistoula_and_mosxeuma"));
        lista.add(new TableViewItem(  "Φίστουλα /\n Μόσχευμα","fistulaOrMosxeuma",  SPINNER_TYPE_NEW, Spinner_items_lists.getFistoulaOrMosxeuma()));
        lista.add(new TableViewItem("Βελόνες","velonesID", SPINNER_TYPE_NEW, "Nursing_items_statheres_metr_velones"));




        lista.add(new TableViewItem("SN-Y LINE ADAPTOR \n Συνδετικό","sindetiko", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem(  "Είδος αιμοκάθαρσης","eidos_aimID", SPINNER_TYPE_NEW, "Nursing_items_med_instr_eidos_aim"));
        lista.add(new TableViewItem( "Κεντρ. φλεβ. \nκαθετηρας μονιμος","flev_kathtiras_monimos", CHECKBOX_TYPE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Κεντρ. φλεβ. \nκαθετηρας προσωρινος","flev_kathtiras_prosorinos", CHECKBOX_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem(   "Κεντρ. φλεβ. \nκαθετηρας_κειμενο","flev_kathtiras_text", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem( "Χωρητ. ηπαρινισμού σκελών Α","skelon_A", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Χωρητ. ηπαρινισμού \nσκελών Φ","skelon_f", EDIT_TEXT_TYPE, DEKADIKOS));



        return lista.getTableList();
    }


    public static ArrayList<TableViewItem> getSigkentrotikaSinexeisMetriseis() {

        BasicList lista = new BasicList();


        lista.add(new TableViewItem( "ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));

        lista.add(new TableViewItem( "ΗΜ/ΝΙΑ / ΩΡΑ","Date", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE).setStable_col(true));
        lista.add(new TableViewItem("Χρήστης","username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Συστολική πίεση (Hg)","sis_piesi", EDIT_TEXT_TYPE, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new TableViewItem("Διαστολική πίεση (mm)","dias_piesi", EDIT_TEXT_TYPE, DEKADIKOS_WITH_NEGATIVE));

        lista.add(new TableViewItem(  "Σφύξεις:","sfikseis", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Θερμοκρασία (°C)","thermokrasia", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Ρυθμός υπερδιηθησης \n ανά ώρα(U.F.) (l/h)", "iperd", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Απώλεια \n βάρους","apoleia_varous", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Πίεση αρτ. γραμμής:","piesi_art", EDIT_TEXT_TYPE, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new TableViewItem("Πίεση φλεβ. γραμμής","piesi_flev", EDIT_TEXT_TYPE, DEKADIKOS_WITH_NEGATIVE));

        lista.add(new TableViewItem("TMP","TMP", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem( "Ρυθμός ροή αντλίας (ml/min)", "roi", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Αγωγιμότητα (ms/cm)","agogimotita", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("HCO3","hco3", EDIT_TEXT_TYPE, DEKADIKOS));
        lista.add(new TableViewItem("Μετρήσεις-Παραμβάσεις","paremvaseis", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Παρατηρήσεις","paratiriseis", EDIT_TEXT_TYPE, KEIMENO));
        lista.add(new TableViewItem("Εγχύσεις - Φάρμακα","egxiseis", EDIT_TEXT_TYPE, KEIMENO));

        lista.add(new TableViewItem("Vit - B","vit_b", CHECKBOX_ITEM, TABLE_NO_TYPE));
        lista.add(new TableViewItem("L - carnittine", "carnitine", CHECKBOX_ITEM, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Alphacalcidol","alphacalcidol", CHECKBOX_ITEM, TABLE_NO_TYPE));
        lista.add(new TableViewItem("EPO","epo", CHECKBOX_ITEM, TABLE_NO_TYPE));
//        lista.add(new TableViewItem( "Epoetin alpha","epo_alpha", CHECKBOX_ITEM, TABLE_NO_TYPE));
//
//        lista.add(new TableViewItem("Epoetin zeta","epo_zeta", CHECKBOX_ITEM, TABLE_NO_TYPE));
        lista.add(new TableViewItem(  "Darbepoetin","epo_darbepoetin", CHECKBOX_ITEM, TABLE_NO_TYPE));
        lista.add(new TableViewItem(  "Etelalcetide","etelalcetide", CHECKBOX_ITEM, TABLE_NO_TYPE));
        lista.add(new TableViewItem( "Paricalcitol","paricalcitol", CHECKBOX_ITEM, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Σίδηρος","sidiros", CHECKBOX_ITEM, TABLE_NO_TYPE));


        return lista.getTableList();

    }



//    public static String[] getSigkentrotikaStatherwnTitles() {
//        return new String[]{"ID","Ημ/νία", "Χρήστης", "Υπευθ. Ιατρός \nβάρδιας", "Ώρα έναρξης" ,"Ώρα λήξης",     "Διάρκεια",
//                "Αρχικό βάρος\nεισόδου",    "Ιματισμός", "Τελικό βάρος\nεισόδου",  "Ξηρό βάρος",      "Διαφορά βάρους",
//                "Πρόσθετο βάρος ",    "Στόχος UF",   "Ρυθμός UF",   "Τελικό στόχος UF",   "Πραγματικό UF",
//                "Βάρος εξόδου","Ιματισμός εξόδου","Τελικό βάρος\nεξόδου",
//                "Φίλτρο","Διάλυμα", "Ηπαρίνη",
//                "Θερμοκ. \nδιαλύματος (°C)","Kt/V", "SPO2",
//                "Θερμοκ. 2ωρο/αρχή","Θερμοκ. 4ωρο/τέλος ",
//                //"fistula", "Μόσχευμα",
//                "Φίστουλα /\n Μόσχευμα",
//                "Βελόνες","SN-Y LINE ADAPTOR \n Συνδετικό","Είδος αιμοκάθαρσης",
//                "Κεντρ. φλεβ. \nκαθετηρας μονιμος","Κεντρ. φλεβ. \nκαθετηρας προσωρινος","Κεντρ. φλεβ. \nκαθετηρας_κειμενο","Χωρητ. ηπαρινισμού σκελών Α",
//                "Χωρητ. ηπαρινισμού \nσκελών Φ"};
//
//    }

//    public static String[] getSigkentrotikaSinexeisTitles() {
//        return new String[]{"ID", "ΗΜ/ΝΙΑ / ΩΡΑ","Χρήστης", "Συστολική πίεση (Hg)", "Διαστολική πίεση (mm)",
//                "Σφύξεις:", "Θερμοκρασία (°C)", "Ρυθμός ροή αντλίας (ml/min)", "Πίεση φλεβ. γραμμής",
//                "Πίεση αρτ. γραμμής:", "Ρυθμός υπερδιηθησης \n ανά ώρα(U.F.) (l/h)", "Απώλεια \n βάρους", "TMP", "Αγωγιμότητα (ms/cm)",
//                "Μετρήσεις-Παραμβάσεις", "Παρατηρήσεις", "Εγχύσεις - Φάρμακα", "Vit - B",
//                "L - carnittine", "Alphacalcidol", "EPO", "Epoetin alpha",
//                "Epoetin zeta", "Darbepoetin", "Paricalcitol", "Σίδηρος"};
//
//    }


    public static ArrayList<TableViewItem>  getFarm_agogi_lista(boolean isNurse , boolean monimi_agogi) {
        ArrayList<TableViewItem> lista = new ArrayList<>();



        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));

           // lista.add(new TableViewItem("UserID", "UserID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
       // if (monimi_agogi) {
       // lista.add(new TableViewItem("Ασθενής","patientName", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Χρήστης\nκαταχώρησης", "username", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Φάρμακο","itemID", TEXTVIEW_MEDICINE_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Δόση","dosi_ID", SPINNER_TYPE_NEW, "Nursing_items_dosis"));
        lista.add(new TableViewItem("Συχνότητα","sixnotita_ID", SPINNER_TYPE_NEW, "Nursing_items_sixnotites"));

        lista.add(new TableViewItem("Ημ/νία έναρξης", "datestart_str", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        if (monimi_agogi){
            lista.add(new TableViewItem("Χρήστης\nδιακοπής", "username_stop", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        }
        else{
            lista.add(new TableViewItem("Ημ/νία διακοπής", "DateStop", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE).editWithNoSameUserID(true));
            lista.add(new TableViewItem("Ημ/νία επομ.\n χορήγησης", "nextDate", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE).editWithNoSameUserID(true));

        }

        //lista.add(new TableViewItem("Εκτός συνεδρίας","efapax", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE));
        if(isNurse)
            lista.add(new TableViewItem("Συνεδρίας","sinedrias", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE).setValue(true));
        else
            lista.add(new TableViewItem("Εκτός \n συνεδρίας","efapax", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE).setValue(true));


        return lista;
    }




    public static String getMEDICAL_INSTRACTIONS(String cols, String patientid, boolean isNurse){

        return  "select top 1 " + cols + " ,n.ID , \n" +
                "ep.name as epoName ,  eid.name as eidosName, fil.name as filName,patientID, \n" +
                "ag.name as agogiName, dos.name as agogiDosisName, dur.name as durationName," +
                " fe.name as feName, lcar.name as CarnitineName  ,vit.name as VitB_Name , " +
                  " ipok.name as ipokName, other_meds,"  +
                "vitD.name as VitD_Name,\n" +
                " d.name as dialeimaName,\n" +
                " vel.name as velonesName,\n" +
                // " ipok.name as ipokName," +
                " embolio_b,\n" +
                " embolio_antiFlu,\n" +
                " dbo.NAMEDOCTOR(n.doctorid) as doctorName, y.name as yearName \n" +
                " from Nursing_Medical_Instructions n \n" +
                " inner join years y on y.id = n.year \n" +
                " left join  Nursing_items_med_instr_epoetin     ep    on ep.ID =  n.epoetinID \n" +
                " left join  Nursing_items_med_instr_eidos_aim    eid  on eid.ID = n.eidos_aim \n" +
                " left join  Nursing_items_med_instr_filter      fil on fil.ID = n.Filter \n" +
                " left join  Nursing_items_med_instr_agogi       ag  on ag.ID = n.agogi \n" +
                " left join  Med_instr_antipiktiki_dosh       dos  on dos.ID = n.agogiDosisID \n" +
                " left join  Nursing_duration_aim                dur  on dur.ID = n.duration_aim_ID \n" +

                        "left join  Nursing_items_med_instr_agogi    ag120  on ag120.ID = n.agogi120 \n" +
                        "left join  Nursing_items_med_Fe_types    fe  on fe.ID = n.fe_ID \n " +
                        "left join  Nursing_items_med_L_carnitine    lcar  on lcar.ID = n.Carnitine_ID \n " +
                        "left join  Nursing_items_ogkos_ipok    ipok  on ipok.ID = n.ipok_ogkos \n" +
                        "left join  Nursing_items_med_Vit_B    vit  on vit.ID = n.vit_B_ID \n"  +
                        "left join  Nursing_items_statheres_metr_velones    vel  on vel.ID = n.velonesID \n" +


        " left join  Nursing_items_med_Vit_D    vitD  on vitD.ID = n.vit_D_ID \n" +
                " left join  Nursing_items_med_instr_dialima      d  on d.ID =  n.dialima \n" +
                " where n.patientID = " + patientid +
                " order by n.year desc , n.month desc , n.id desc";
    }



}
