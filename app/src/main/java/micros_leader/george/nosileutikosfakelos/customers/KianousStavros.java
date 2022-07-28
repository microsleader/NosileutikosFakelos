package micros_leader.george.nosileutikosfakelos.customers;

import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.AKERAIOS;
import static micros_leader.george.nosileutikosfakelos.InfoSpecificLists.AKERAIOS_WITH_NEGATIVE;
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
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_DATETIME_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_DATE_TYPE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_ITEM_READ_ONLY_VALUE;
import static micros_leader.george.nosileutikosfakelos.StaticFields.TEXTVIEW_MEDICINE_TYPE;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.BasicList;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ItemsRV;
import micros_leader.george.nosileutikosfakelos.Customers;
import micros_leader.george.nosileutikosfakelos.MyApplication;
import micros_leader.george.nosileutikosfakelos.Spinner_items_lists;
import micros_leader.george.nosileutikosfakelos.StaticFields;
import micros_leader.george.nosileutikosfakelos.TableView.TableViewItem;

public class KianousStavros {

    public static ArrayList getStathersMetriseisLista(boolean isForRV) {

        BasicList lista = new BasicList();

        // lista.add(new ItemsRV("Ημ/νία:", "schedule_time_start","" , TEXTVIEW_TYPE ));
        if (!isForRV)
            lista.add(new ItemsRV("ID", "ID", "", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new ItemsRV("Είδος αιμοκάθαρσης", "eidos_aimID", SPINNER_ITEM_NEW, "Nursing_items_med_instr_eidos_aim").setcompareColValue("med_eidos_aim_name"));
        lista.add(new ItemsRV("Διάρκεια:", "duration_aim_ID", SPINNER_ITEM_NEW, Spinner_items_lists.getDuration_aim()).setcompareColValue("duration"));
        lista.add(new ItemsRV("Διαστ. πίεση εισόδου:", "diast_eisodou", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Συστ. πίεση εισόδου:", "sist_eisodou", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Ξηρό βάρος (kg):", "xiro_varos", "", EDITTEXT_ITEM, DEKADIKOS).setcompareColValue("ksiro_varos"));

        lista.add(new ItemsRV("Βάρος προ αιμ. (kg):", "arxiko_varos", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Βάρος μετά συνεδρίας (kg):", "varos_meta_sinedrias", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Διαφορά βάρους(kg):", "diafora_varous", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Βάρος εξόδου (kg):", "varos_exodou", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Διαστ. εξόδου :", "diast_exodou", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Συστ. εξόδου :", "sist_exodou", "", EDITTEXT_ITEM, DEKADIKOS));

        lista.add(new ItemsRV("Ένδειξη βάρους", "endiksiVarous", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Ένδειξη Φίλτρου", "endiksiFiltrou",  SPINNER_ITEM_NEW, Spinner_items_lists.getEndeiksiFiltrou()));


        lista.add(new ItemsRV("Φίλτρο", "filtroID",  SPINNER_ITEM_NEW, "Nursing_items_med_instr_filter").setcompareColValue("filter"));
        lista.add(new ItemsRV("Διάλυμα", "dialimaID",  SPINNER_ITEM_NEW, "Nursing_items_med_instr_dialima").setcompareColValue("dialima"));
        lista.add(new ItemsRV("Αντιπ. αγωγή", "antip_agogi", SPINNER_ITEM_NEW, "Nursing_items_med_instr_agogi").setcompareColValue("antip_agogiID"));
        lista.add(new ItemsRV("Αντιπ. αγωγή\n δόση", "agogiDosisID", SPINNER_ITEM_NEW, "Med_instr_antipiktiki_dosh").setcompareColValue("med_antip_agogiID"));
        lista.add(new ItemsRV("Παρατηρήσεις", "paratiriseis", "", EDITTEXT_ITEM, KEIMENO));



        if (isForRV)
            return lista.getList();
        else
            return lista.getTableList();


    }













    public static ArrayList getSinexeisMetriseisLista(boolean isForRV) {


        BasicList lista = new BasicList();


        if (!isForRV)
            lista.add(new ItemsRV("ID", "ID", "",  TABLE_NO_ELEMENT, TABLE_NO_TYPE));

        lista.add(new ItemsRV("Ημ/νία και ώρα", "Date", "", isForRV ? TEXTVIEW_DATETIME_TYPE : TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Πίεση αρτ.γραμμής", "piesi_art", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Πίεση φλεβ.γραμμής", "piesi_flev", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("TMP", "TMP", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Επιθυμ. απώλεια βάρους (UF vol)", "desiredLoseWeight", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Απώλεια βάρους/ώρα (UFR)", "loseWeightPerHour", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Αντλία αίματος", "antlia_aimatos", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Θερμοκρασία διαλυμ.", "thermokrasia_dialimatos", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Ροή διαλυμ.", "roi_dialimatos", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Θερμοκρασία (°C)", "thermokrasia", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Σφύξεις:", "sfikseis", "", EDITTEXT_ITEM, AKERAIOS));
        lista.add(new ItemsRV("Συστολική πίεση (mmHg)", "sis_piesi", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Διαστολική πίεση (mmHg)", "dias_piesi", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("SPO2 (%)", "spo2", "", EDITTEXT_ITEM, DEKADIKOS));
        lista.add(new ItemsRV("Σάκχαρο προ", "sakxaro_pro", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));
        lista.add(new ItemsRV("Σάκχαρο μετά", "sakxaro_meta", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));

        lista.add(new ItemsRV("Ξηρά κάθαρση", "ksira_katharsi",  SPINNER_ITEM_NEW, Spinner_items_lists.getKsiraKatharsi()));
        lista.add(new ItemsRV("Ρυθμ. ξηράς κάθαρ.", "rithmos_ksiras_katharsis", "", EDITTEXT_ITEM, AKERAIOS_WITH_NEGATIVE));

        lista.add(new ItemsRV("Kt/V", "Kt_V", "", EDITTEXT_ITEM, DEKADIKOS_WITH_NEGATIVE));


        lista.add(new ItemsRV("Μετάγγιση", "metaggisi",  false, CHECKBOX_ITEM));



//        lista.add(new ItemsRV("Epoetin", "epo", false, CHECKBOX_ITEM));
//        lista.add(new ItemsRV("VIT B", "vit_b", false, CHECKBOX_ITEM));
//        lista.add(new ItemsRV("carnitine", "carnitine", false, CHECKBOX_ITEM));
//        lista.add(new ItemsRV("VIT D", "vit_d", false, CHECKBOX_ITEM));
//        lista.add(new ItemsRV("Fe", "fe", false, CHECKBOX_ITEM));
//        lista.add(new ItemsRV("Etelalcetide", "etelalcetide", false, CHECKBOX_ITEM));


        lista.add(new ItemsRV("Παρατηρήσεις:", "paratiriseis", "", EDITTEXT_ITEM, KEIMENO));

        if (isForRV)
            return lista.getList();
        else
            return lista.getTableList();

    }


    public static ArrayList getMedicalInsLista(boolean isForRV) {

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


        lista.add(new ItemsRV("Αλλεργίες", "allergiesMeds", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Φαρμ. αγωγή", "farmAgogiMeds", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Εμβόλιο ηπ.Β", "embolio_b", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Αντιγριπ. εμβόλιο", "embolio_antiFlu", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Εμβόλιο πνευμονιόκοκου", "embolio_pneum", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Εμβόλιο covid 19", "embolio_covid", "", TEXTVIEW_ITEM_READ_ONLY_VALUE, TEXTVIEW_DATE_TYPE));
        lista.add(new ItemsRV("Γενικές οδηγίες", "genikes_odigies", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));


        if (isForRV)
            return lista.getList();
        else
            return lista.getTableList();

    }



    public static String getXorigisiStr(String patientID){
        return "" +
//                "IF NOT EXISTS (SELECT 1 from Nursing_meds_xorigisis x\n" +
//                "    join Nursing_xorigisi_farmakon_efapax ef on ef.id = x.xorigisiID\n" +
//                " where ef.patientID =  " + patientID + " and x.date = dbo.DateTime2Date(dbo.localTime()))\n" +
//                "\n" +
//                " BEGIN\n" +
//                "\n" +
//                " DECLARE @LAST_XORIGISI_DATE BIGINT = (SELECT TOP 1 DATE FROM Nursing_meds_xorigisis x\n" +
//                "    join Nursing_xorigisi_farmakon_efapax ef on ef.id = x.xorigisiID\n" +
//                " where ef.patientID =  " + patientID + " ORDER BY DATE DESC)\n" +
//                "\n" +
//                "  INSERT Nursing_meds_xorigisis(xorigisiID)\n" +
//                "  SELECT ID FROM Nursing_xorigisi_farmakon_efapax \n" +
//                "  where \n" +
//                "  patientID =  " + patientID + "\n" +
//                "  and sinedrias = 1\n" +
//                "  and datestart is not null\n" +
//                "  and datestop is null\n" +
//                "  \n" +
//                "\n" +
//                " END \n" +
                "\n" +
                "DECLARE @NOT_INSERTED_MEDS BIGINT = \n" +
                "(\n" +
                "SELECT\n" +
                "    count(*)\n" +
                "\n" +
                "    from Nursing_xorigisi_farmakon_efapax ef \n" +
                "    left join Nursing_meds_xorigisis x on ef.id = x.xorigisiID\n" +
                "    join person p on p.id = ef.patientID\n" +
                "    \n" +
                "    WHERE \n" +
                "    p.id = " + patientID + "\n" +
                "    and ef.sinedrias = 1\n" +
                "    and datestart is not null\n" +
                "    and datestop is null\n" +
                " and (( x.id is null) or( x.date < dbo.DateTime2Date(dbo.localTime()))) \n" +
                ")\n" +
                "\n" +
                "IF @NOT_INSERTED_MEDS > 0\n" +
                "BEGIN\n" +
                " INSERT Nursing_meds_xorigisis(xorigisiID)\n" +
                " SELECT\n" +
                "    ef.id\n" +
                "    from Nursing_xorigisi_farmakon_efapax ef \n" +
                "    left join Nursing_meds_xorigisis x on ef.id = x.xorigisiID\n" +
                "    join person p on p.id = ef.patientID\n" +
                "    WHERE \n" +
                "    p.id = " + patientID + "\n" +
                "    and ef.sinedrias = 1\n" +
                "    and datestart is not null\n" +
                "    and datestop is null\n" +
                " and (( x.id is null) or( x.date < dbo.DateTime2Date(dbo.localTime()))) \n" +
                "END\n" +

                "\n" +
                "\n" +
                "\n" +
                "SELECT\n" +
                "x.ID,\n" +
                "p.lastname + ' ' + p.firstname as patName,\n" +
                "i.name as itemName,\n" +
                "ef.dosologiaText,\n" +
                "x.remarks,\n" +
                "x.hour,\n" +
                "x.xorigithike,x.date\n" +
                "from Nursing_meds_xorigisis x\n" +
                "join Nursing_xorigisi_farmakon_efapax ef on ef.id = x.xorigisiID\n" +
                "join person p on p.id = ef.patientID\n" +
                "join item i on i.id = ef.itemID\n" +
                "\n" +
                "WHERE \n" +
                "p.id = " + patientID +"\n" +
                "and ef.sinedrias = 1\n" +
                "and x.date = dbo.DateTime2Date(dbo.localTime())\n" +
                "and datestart is not null\n" +
                "and datestop is null";
    }



    public static ArrayList<TableViewItem> getXorigisiLista() {

        BasicList lista = new BasicList();

        lista.add(new ItemsRV("ID", "ID", "",  TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new ItemsRV("Φάρμακο", "itemName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
       // lista.add(new ItemsRV("Ασθενής", "patName", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));

        lista.add(new ItemsRV("Δοσολογία", "dosologiaText", "", TEXTVIEW_ITEM_READ_ONLY_VALUE));
        lista.add(new ItemsRV("Ώρα", "hour", "", TEXTVIEW_CLOCK_TYPE, TABLE_NO_TYPE ));
        lista.add(new ItemsRV("Χορηγήθηκε", "xorigithike",  false, CHECKBOX_ITEM));
        lista.add(new ItemsRV("Παρατηρήσεις", "remarks", "",  EDITTEXT_ITEM, KEIMENO));



        return lista.getTableList();


    }





    public static ArrayList<TableViewItem>  getFarm_agogi_lista(boolean isMonimi ,boolean isDoctor) {
        ArrayList<TableViewItem> lista = new ArrayList<>();

        int TYPE_ELEMENT ;
        if (isDoctor)
            TYPE_ELEMENT = EDITTEXT_ITEM;
        else
            TYPE_ELEMENT = TEXTVIEW_ITEM_READ_ONLY_VALUE;



        lista.add(new TableViewItem("ID","ID", TABLE_NO_ELEMENT, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Φάρμακο","itemID", TEXTVIEW_MEDICINE_TYPE, TABLE_NO_TYPE));

        lista.add(new TableViewItem("Δοσολογία","dosologiaText", EDITTEXT_ITEM, KEIMENO));
        lista.add(new TableViewItem("Παρατηρήσεις", "remarks",  EDITTEXT_ITEM, KEIMENO  ));
        lista.add(new TableViewItem("Ημ/νία έναρξης","datestart_str", TEXTVIEW_ITEM_READ_ONLY_VALUE, TABLE_NO_TYPE));
        lista.add(new TableViewItem("Ημ/νία λήξης","DateStop", TEXTVIEW_DATE_TYPE, TABLE_NO_TYPE).editWithNoSameUserID(true));
        if (isMonimi)
            lista.add(new TableViewItem("Μόνιμη","efapax", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE).setValue(true));
        else
            lista.add(new TableViewItem("Συνεδρίας","sinedrias", CHECKBOX_TYPE_READ_ONLY_VALUE, TABLE_NO_TYPE).setValue(true));



        return lista;
    }





}
