package micros_leader.george.nosileutikosfakelos;


import java.util.ArrayList;
import java.util.StringJoiner;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsCheckboxesForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.ClassItemsForRV;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko.Nosil_IstorikoList;

public class Str_queries {


    public String PATIENTS_NOSILEUOMENOI_OLD = "select  tg.id as transgroupID," +
            " p.FirstName,  p.LastName,  tg.PatientID, " +
            " dbo.datetostr(tg.DateIn) as datein, tg.isEmergency, " +
            "tg.code, tg.MTNWatchID " +
            "from TransGroup tg  "  +
            "left join Person p on p.id = tg.PatientID " +
            "where "  +
            "tg.Category = 1  AND tg.DateOut is null "  +
            "and tg.companyid = 1";


    public  String XREOSIMA_ILIKA = "select dbo.NAMEITEM(ti.itemid) as name," +
            " d.name as tmima, " +
            " ti.quantity as posotita" +
            " from TransItem ti" +
            " inner join Item i on i.id=ti.ItemID " +
            " inner join TransGroup tg on tg.id=ti.TransGroupID " +
            " left join Department d on d.id=i.DepartmentID " +
            " where TransGroupid = " ;


    public final String MEDICINES = "select id, name from Item where Category = 2 and cancelled is null";

    public final static String CUR_DATE = "declare @curDate bigint\n" +
            "    set @curDate = dbo.datetime_to_date( dbo.timeToNum(GETDATE())) \n";



    public static String SEARCH_MEDICINES(String txt,String companyID){
        String [] words = txt.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words){
            sb.append("\"" + w + "*\" AND " );
        }

        String declareText = sb.toString().substring(0,sb.toString().length() - 4) ;

        return "DECLARE @MED NVARCHAR(450) = dbo.phonetic('" + declareText + "'); " +
                "SELECT " +
                "TOP 32 " +
                "id, name " +
                "FROM Item a " +
                "WHERE " +
                "((a.ID IN (SELECT j.RowID FROM CompanyJoins j WHERE j.companyid= " + companyID + " AND j.TableID=3242771 AND j.RowID=a.id))) " +
                 "AND (NULLIF(cancelled,0) IS NULL)\n" +
                 "AND a.id IN (SELECT id FROM Item WHERE CONTAINS(tags, @MED)) " +
                 "AND a.category = 2";
    }


    public String getPATIENTS_NOSILEUOMENOI(String companyID) {
        return "select " +

                "tgl.ID as lasttransgroupID," +
                "tgf.id as transgroupID," +
                "p.FirstName," +
                "p.LastName," +
                "p.fatherName," +
                "dbo.datetostr(tgf.datein) as datein," +
                "tgf.isEmergency ," +
                "p.id as PatientID," +
                "tgf.Code as code," +
                "tgf.MTNWatchID " +

                "from transgroup tgf " +
                "join transgroup tgl on tgl.id = tgf.lasttransgroupid " +
                "left join Person p on p.id = tgf.PatientID  " +
                "where tgf.id = tgf.firsttransgroupid " +
                "and tgf.category = 1 " +
                "AND tgl.DateOut is null " +
                "and tgf.companyid = " + companyID +
                "order by dbo.NAMEPATIENT(p.ID) " ;
    }

    public static String setglobals(String userID,String langid, String companyid){
       return " EXEC dbo.set_globals " + userID  + ", 0," + langid + ",2," + companyid + " \n";
    }


    public String getPATIENTS_PLANO_KLINON_NOSILEUOMENOI(String companyID, int floorID) {
        return "select " +

                "tgl.ID as lasttransgroupID," +
                "tgf.id as transgroupID," +
                "p.FirstName," +
                "p.LastName," +
                "p.fatherName," +
                "dbo.datetostr(tgf.datein) as datein," +
                "tgf.isEmergency ," +
                "p.id as PatientID," +
                "tgf.Code as code," +
                "tgf.MTNWatchID, " +
                "f.id " +

                "from transgroup tgf " +
                "join transgroup tgl on tgl.id = tgf.lasttransgroupid " +
                "left join Person p on p.id = tgf.PatientID  " +
                "left join Bed b on b.ID = tgf.BedID " +
                "left join Room r on r.ID = b.RoomID " +
                "left join Floor f on f.ID = r.FloorID " +

                "where tgf.id = tgf.firsttransgroupid " +
                "and tgf.category = 1 " +
                "AND tgl.DateOut is null " +
                "and tgf.companyid = " + companyID +
                " and f.id = " + floorID +
                " order by dbo.NAMEPATIENT(p.ID) " ;
    }


    public static String getNefrologous(String companyID){
        return "select " +
                "p.id, p.Name " +
                "from Person p " +
                "join users us on us.LinkDoctorID = p.id " +
                "where p.isDoctor = 1 " +
                "and isNephrologist = 1 " +
                "and us.companyID = " + companyID;
    }

    public static  String getKardiologous_Look_up (){
        return  " person  " +
                " where isDoctor = 1 " +
                " and DoctorSpecialityID = 16";
    }


    public String getFloors(String companyID) {

        return " select id,name from floor where companyid = " + companyID;
    }



        public String getLOGODOSIES_PERSON(String transgroupID){

        return "select n.id,  n.TransGroupID as transgroupID, " +
                "dbo.dateToStr(n.date) as date, " +
                "dbo.timeToStr(n.date) as time, " +
                "p.name, " +
                "n.UserID as userID, " +
                "n.logodosia " +
                "from Nursing_Logodosia n " +
                "left join users p on p.id = n.userid " +
                "where n.transgroupid = " +  transgroupID +
                " order by n.date desc" ;
    }



    public static String getPLANO_KLINON_TMIMA(String companyID, String clinicID){
        return
                "    select " +

                        "    p.FirstName, " +
                        "    p.LastName, " +
                        "    p.fatherName, " +
                        "    dbo.NAMEINSURANCE(tgf.ins1id) as insurance1,"+
                        "    dbo.NAMEINSURANCE(tgf.ins2id) as insurance2,"+
                        "    dbo.NAMEINSURANCE(tgf.ins3id) as insurance3,"+
                        "    dbo.NAMEINSURANCE(tgf.ins4id) as insurance4,"+
                        "    dbo.datetostr(p.DateBirth) as dateBirth, " +
                        "    dbo.datetostr(tgf.datein) as datein, " +
                        "    p.phone , " +
                        "    p.MobilePhone, " +
                        "    tgf.DiagnosisIn, " +
                     //   "    tgf.isEmergency , " +
                      //  "    p.id as PatientID, " +
                        "    p.Code as code, " +
                        "    tgf.ClinicID, " +
                        "    bed.Code as bedCode, " +
                        "    tgf.remarks," +
                        " dbo.namedoctor(tgf.TreatmentDoctor1ID) as treatment1," +
                        " dbo.namedoctor(tgf.TreatmentDoctor2ID) as treatment2," +
                        " dbo.namedoctor(tgf.TreatmentDoctor3ID) as treatment3," +
                        " dbo.namedoctor(tgf.TreatmentDoctor4ID) as treatment4" +

                        "    from v_transgroup tgf " +
                        "    join transgroup tgl on tgl.id = tgf.lasttransgroupid " +
                        "    left join Person p on p.id = tgf.PatientID " +
                        "    left join bed bed on bed.id = tgf.BedID " +
                        "    where tgf.id = tgf.firsttransgroupid " +
                        "    and tgf.category = 1 " +
                        "    AND tgl.DateOut is null " +
                        "    and tgf.companyid =  " + companyID +
                        "    and tgf.ClinicID = " + clinicID +
                        "    order by dbo.NAMEPATIENT(p.ID)" ;
    }





    public String getLOGODOSIES_DELETE(String id) {

      return  "exec dbo.disable_triggers " +
                " delete from Nursing_Logodosia where id = " + id +
                " exec dbo.enable_triggers ";
    }

    public String getLOGODSIA_UPDATE(String id, String text) {

        return "exec dbo.disable_triggers " +

                "update  Nursing_Logodosia " +
                "set logodosia = '" + text + "' " +
                " where id =  " + id +

                " exec dbo.enable_triggers" ;
    }

        public static String getCLINICS(){
                return "select id, name from clinic";
        }

        public static String getNosileuomenousFloors(String date, String time, String floorID, String companyID){

        if (floorID.equals("0"))
            floorID = ">0";
        else
            floorID = "= " + floorID;

        if (date.equals("") && !time.equals(""))
            date = Utils.getCurrentDate();

        else if (time.equals("") && !date.equals(""))
            time = Utils.getCurrentTime();

        else if (date.equals("") && time.equals("")){
            date = Utils.getCurrentDate();
            time = Utils.getCurrentTime();

        }


        return "DECLARE @CURDATE BIGINT \n" +
                "SET @CURDATE = dbo.timeToNum(CONVERT(datetime,  getdate() , 103)); \n" +
                "\n" +
                "SELECT \n" +

                "  b.code as bed,\n" +
                "  fl.Name as floor,\n" +
                "  dbo.datetostr(tg.datein) as datein, \n" +
                "  dbo.datetostr(tgf.datein) as dateinF, \n" +
                "  p.FirstName, \n  " +
                "  p.LastName,   \n " +
                "  p.fatherName, \n  " +
                "  p.code, \n" +
                "  dbo.NAMEINSURANCE(tg.ins1id) as insurance1, \n " +
                "  dbo.NAMEINSURANCE(tg.ins2id) as insurance2, \n " +
                "  dbo.NAMEINSURANCE(tg.ins3id) as insurance3, \n " +
                "  dbo.NAMEINSURANCE(tg.ins4id) as insurance4, \n " +
                "  [patient_age] = dbo.age(dbo.timeToNum(getdate()), p.dateBirth),\n" +
                "  [tg_clinic] = tg.clinic, \n" +
                "  tg.DiagnosisIn ,\n " +
                "  dbo.namedoctor(tg.TreatmentDoctor1ID) as treatment1,  \n " +
                "  dbo.namedoctor(tg.TreatmentDoctor2ID) as treatment2,  \n " +
                "  dbo.namedoctor(tg.TreatmentDoctor3ID) as treatment3,  \n " +
                "  dbo.namedoctor(tg.TreatmentDoctor4ID) as treatment4,"
 +
                "  tg.remarks \n  " +
                "from bed b \n" +
                "LEFT JOIN NursingGroup tg ON \n" +
                "tg.id=dbo.Nursing_SearchBy_Bed_Date(b.id, @CURDATE) \n" +
                "left join TransGroup tgf on  tgf.id = tg.FirstTransGroupID \n" +

                "LEFT JOIN Person p ON p.id=tg.patientid \n" +
                "LEFT JOIN Room r ON r.id=b.roomid \n" +
                "left join Floor fl on fl.id = r.FloorID \n" +
                "where fl.id  " + floorID +
                " and b.companyID = " + companyID +

                " ORDER BY  fl.Name, b.code  ";
        }




    public static String getNosileuomenousFloors(String floorID, String companyID){



        return "DECLARE @CURDATE BIGINT\n" +
                "SET @CURDATE = dbo.timeToNum(CONVERT(datetime,  getdate() , 103)); \n" +
                "SELECT " +

                "  b.code as bed," +
                "  fl.Name as floor," +
                "  tg.ID as transgroupID," +
                "  tg.code, " +
                "  dbo.datetostr(tg.datein) as datein, " +
                "  p.FirstName,   " +
                "  p.LastName,    " +
                "  p.fatherName,   " +
                "  tg.IsEmergency " +

                "from bed b " +
                "LEFT JOIN NursingGroup tg ON " +
                "tg.id=dbo.Nursing_SearchBy_Bed_Date(b.id, @CURDATE) " +

                "LEFT JOIN Person p ON p.id=tg.patientid " +
                "LEFT JOIN Room r ON r.id=b.roomid " +
                "left join Floor fl on fl.id = r.FloorID " +
                "where fl.id  = " + floorID +
                " and tg.companyID = " + companyID +
                " ORDER BY  fl.Name, b.code  ";
    }

    public static String getNosileuomenousFloors_v2(String floorID, String companyID){

        return "select " +
                "   b.code as bed,  " +
                "  fl.Name as floor,  " +
                "  tg.ID as transgroupID,  " +
                "  tg.code,   " +
                "  dbo.datetostr(tg.datein) as datein,  " +
                "  p.FirstName,    " +
                "  p.LastName,      " +
                "  p.fatherName, " +
                "  tg.IsEmergency  " +
                "  from transgroup tg  " +
                " " +
                "     LEFT JOIN Person p ON p.id=tg.patientid  " +
                " left join bed b on b.id =  tg.BedID " +
                " LEFT JOIN Room r ON r.id=b.roomid  " +
                " left join Floor fl on fl.id = r.FloorID " +
                " " +
                "   where " +
                "  tg.companyID =  " + companyID +
                "  and fl.id  = " + floorID +
                "  and isnull(tg.dateout,0) = 0 " +
                "  ORDER BY  fl.Name, b.code  ";
    }



    public static String getEKSOTERIKA_PATIENTS() {

        return  " select " +
                "  tg.ID as transgroupID," +
                "  dbo.datetostr(tg.datein) as datein, " +
                "  p.FirstName,   " +
                "  p.LastName,    " +
                "  p.fatherName,  " +
                "  tg.code ,"       +
                "  tg.IsEmergency " +
                " from transgroup tg " +
                " left join person p on tg.patientid = p.id " +
                "where dbo.datetostr(tg.datein) = dbo.datetostr(dbo.timeToNum(CONVERT(datetime, '" + Utils.getCurrentDate() + "' , 103))) " +
                "and tg.category = 2 ";

    }


    public String getLOGODOSIA_INSERT(String transgroupid, String date, String time, String userID, String logodosia) {

        return "exec dbo.disable_triggers " +

                "INSERT INTO Nursing_Logodosia (TransGroupID, Date, UserID,logodosia) " +
                "VALUES (" + transgroupid + "," +
                "dbo.timeToNum(CONVERT(datetime, " + "'" + date + " " + time + "' , 103))"  +
                "," + userID + "," + "'"+logodosia + "'" + ");"+
                " exec dbo.enable_triggers";
    }


    public static String getOldteleytaiesStatheresMetriseis(String patientID, int custID){
        return "select top 1 n.* , dbo.namedoctor(n.ipefthinos_iatros_vardias) as docName, dbo.timeTostr(n.duration) as Duration," +
                " dbo.timeToStr(n.schedule_time_start) as schtimeStart, " +
                " dbo.timeToStr(n.time_start) as timeStart, " +
                " dbo.timeToStr(n.time_finish) as timeFinish, " +
                " dbo.timeToStr(n.duration) as dur, " +
                " isnull(arxiko_varos,0) - isnull(xiro_varos,0) as diafora_varous, " +
                ( Customers.isFrontis(custID)  ? " isnull(arxiko_varos,0) - isnull(imatismos,0) as teliko_arxiko_varos, " : "" ) +
                ( Customers.isFrontis(custID) ? " isnull(varos_exodou,0) - isnull(imatismos_exit,0) as teliko_varos_exodou, " : "" ) +
                ( Customers.isFrontis(custID) ?
                    " (select top 1 isnull(n.arxiko_varos,0) -  isnull(n.xiro_varos,0) +  isnull(b.additional_weight,0) " +
                    " from nursing_medical_instructions b where b.patientid = " + patientID + " order by b.Year desc,b.Month desc) as target_UF ," : "") +

                //"  (select top 1 additional_weight from nursing_medical_instructions b where b.patientid = " + patientID + " order by b.Year desc,b.Month desc) as additional_weight, "  +
                "  (select top 1 ksiro_varos from nursing_medical_instructions b where b.patientid = " + patientID + " order by b.Year desc,b.Month desc) as ksiro_varos "  +
                // " dbo.dateToStr(n.date) as datestr " +
                // " m.Filter, m.online ,m.dialima as dial, m.ksiro_varos " +

                " from Nursing_Hemodialysis_initial2_MEDIT n " +
                //" left join Nursing_Medical_Instructions m on m.PatientID = n.PatientID " +
                " left join doctor doc on doc.id = n.ipefthinos_iatros_vardias" +
                " where n.patientid =  " + patientID
                + " order by TransGroupid desc ";
        //" order by n.date desc";
    }

    public static String getCURRENT_METRISEIS_MAZI_ME_MEDICAL_INSTRUCTIONS(String transgroupID,String patientID,int custID){
        return  "" +
                "DECLARE @STATHERES_ID BIGINT;\n" +
                "SET @STATHERES_ID = (SELECT TOP 1 ID FROM Nursing_Hemodialysis_initial2_MEDIT WHERE TransGroupID = " + transgroupID + ")\n" +
                "\n" +
                "IF ISNULL(@STATHERES_ID,0) = 0\n" +
                "BEGIN \n" +
                "SELECT TOP 1 eidos_aim as eidos_aimID , " +
                "  filter as filtroID,\n" +
                "  agogi as antip_agogi,\n" +
                "  agogiDosisID,\n" +
                "  duration_aim_ID,\n" +
                "  dialima as dialimaID,\n" +
                "  ksiro_varos as xiro_varos,\n " +
                "  temparture as therm_dialimatos \n" +

                "FROM Nursing_Medical_Instructions where Patientid = " + patientID + " order by year desc, Month desc ,id desc\n" +
                "END\n" +
                "\n" +
                "ELSE\n" +
                "BEGIN\n" +
                "select top 1 " +
                " n.* ,\n" +

                "m.eidos_aim as med_eidos_aim_name,\n" +
                "m.dialima,\n" +
                "m.duration_aim_ID as  duration,\n" +
                "m.filter,\n" +
                "m.agogi as antip_agogiID,\n" +
                "m.agogiDosisID as med_antip_agogiID,\n" +
                "m.temparture, \n" +

                " dbo.timeToStr(n.schedule_time_start) as schtimeStart, \n" +
                " dbo.namedoctor(n.ipefthinos_iatros_vardias) as docName, \n" +
                " dbo.timeToStr(n.time_start) as timeS,\n" +
                " dbo.timeToStr(n.time_finish) as timeF,\n" +
                ( Customers.isFrontis(custID)  ? " isnull(arxiko_varos,0) - isnull(imatismos,0) - isnull(m.ksiro_varos,0) + isnull(n.additional_weight,0) as target_UF, " : "" )+
                ( Customers.isFrontis(custID) ? " isnull(arxiko_varos,0) - isnull(imatismos,0) as teliko_arxiko_varos, " : "" )+
                ( Customers.isFrontis(custID) ? " isnull(varos_exodou,0) - isnull(imatismos_exit,0) as teliko_varos_exodou, " : "" ) +


                " dbo.dateToStr(n.date) as datestr,\n" +
                " dbo.timeTostr(m.duration) as dur,\n " +
                ( Customers.isFrontis(custID) ? "  isnull(n.arxiko_varos,0) - isnull(n.imatismos,0) - m.ksiro_varos as diafora_varous, " : " isnull(n.arxiko_varos,0) - isnull(n.xiro_varos,0) as diafora_varous,")+
                "  m.Filter," +
                //"  m.Online," +
                "  m.dialima as dialimaaa,\n" +
                "  m.ksiro_varos, \n" +
                "  m.additional_weight as med_instr_additional_weight,\n " +
                "  dbo.nameuser(n.userID) +  CHAR(10) + dbo.nameuser(n.userID2) as username,\n " +
                "  n.therapon_iatros \n" +

                "from v_Nursing_Hemodialysis_initial2_MEDIT n \n" +
                "left join Nursing_Medical_Instructions m on m.PatientID = n.PatientID \n" +
                "where n.TransGroupID = " + transgroupID +
                " order by year desc, Month desc ,m.id desc  \n" +
                "\n" +
                " END";
    }




        public static String getSigkentrotika_statherwn_metrisewn(String patientID,String transgroupID ,int custID ) {

            return  " select distinct top  3 n.* ," +
                    " dbo.timeToStr(n.schedule_time_start) as schtimeStart, " +
                    " dbo.namedoctor(n.ipefthinos_iatros_vardias) as ipefthinos_iatros_vardias, " +
                    " dbo.nameuser(n.userID) +  CHAR(10) +  dbo.nameuser(n.userID2) as username, " +
                    " dbo.timeToStr(n.time_start) as timeS," +
                    " dbo.timeToStr(n.time_finish) as timeF," +
                    ( Customers.isFrontis(custID) ? " ISNULL(n.arxiko_varos,0) - isnull(n.imatismos,0) - ISNULL(ins.ksiro_varos,0) + ISNULL(n.additional_weight,0) as target_UF , " : "" )+
                    ( Customers.isFrontis(custID) ? " isnull(arxiko_varos,0) - isnull(imatismos,0) as teliko_arxiko_varos, " : "" )+
                    ( Customers.isFrontis(custID) ? " isnull(varos_exodou,0) - isnull(imatismos_exit,0) as teliko_varos_exodou, " : "" ) +

                    " ins.ksiro_varos, " +
                    " dbo.dateToStr(tg.datein) as datestr," +
                    " dbo.timeToStr(n.duration) as durationstr," +
                    ( Customers.isFrontis(custID) ? "  isnull(n.arxiko_varos,0) -  isnull(n.imatismos,0) - isnull(ins.ksiro_varos,0) as diafora_varous, " :  " isnull(n.arxiko_varos,0) - isnull(n.xiro_varos,0) as diafora_varous," )+
                    "  tg.TreatmentDoctor1ID as therapon_iatros, " +
                    " eidos.name  as eidos_aimID_text, " +
                    " fil.name  as filtroID_text, " +
                    " dial.name  as dialimaID_text, " +
                    " fis.name  as fistulaID_text, " +
                    " mos.name  as mosxeumaID_text, " +
                    " vel.name  as velonesID_text " +

                   // " ISNULL(ins.additional_weight,0) as additional_weight" +


                    " from Nursing_Hemodialysis_initial2_MEDIT n " +
                    " join transgroup tg on tg.id = n.TransGroupID" +
                    " left join nursing_medical_instructions ins on  n.patientID = ins.patientID " +
//
                    " left join Nursing_items_med_instr_eidos_aim eidos on eidos.id = n.eidos_aimID " +
                    " left join Nursing_items_med_instr_filter fil on fil.id = n.filtroID " +
                    " left join Nursing_items_med_instr_dialima dial on dial.id = n.dialimaID " +
                    " left join Nursing_items_statheres_fistoula_and_mosxeuma fis on fis.id = n.fistulaID " +
                    " left join Nursing_items_statheres_fistoula_and_mosxeuma mos on mos.id = n.mosxeumaID " +
                    " left join Nursing_items_statheres_metr_velones vel on vel.id = n.velonesID " +

                    ( Customers.isFrontis(custID) ? " where n.patientID = " + patientID
                            :
                            " where n.transgroupID = " + transgroupID ) +
                    " order by 1 desc";
    }


    //

        public static String getSigkentrotika_sinexwn_metrisewn(String patientID) {
        return "select  top 8 " +
                "ni.*, " +
                "dbo.DateTimeToString(date) as datestr,   " +
                "dbo.TimeToStr(date) as timestr ,  " +
                "dbo.datetostr(date) as cur_date, " +
                " dbo.nameuser(userID)  as username, " +

                "  m.VitB as vitB_text,  " +
                "  m.Carnitine as carnitine_text,   " +
                "  m.Alfacalcidol as alfacalcidol_text,  " +
                "  m.zeta as zeta_text,  " +
                "  m.alpha as alpha_text,  " +
                "  m.darbepoetin as darbepoetin_text,  " +
                "  m.Paracalcitol as paracalcitol_text   " +
                "  from Nursing_Hemodialysis_2_MEDIT  ni  " +
                "left join ( " +
                "select   " +
                "top 1 " +
                "patientid , " +
                "VitB, " +
                "Carnitine, " +
                "Alfacalcidol, " +
                "zeta, " +
                "alpha, " +
                "darbepoetin, " +
                "Paracalcitol " +
                "from Nursing_Medical_Instructions " +
                "where patientid =  " + patientID +
                " group by PatientID,id,VitB,Carnitine,Alfacalcidol,zeta,alpha,darbepoetin,paracalcitol " +
                "order by id desc " +
                ")  m  on m.PatientID = ni.PatientID " +
                " " +
                "  where  ni.PatientID = " + patientID +
              //  "  and  dbo.DateTime2Date(date) = dbo.DateTime2Date(dbo.timeToNum(getdate()))  " +

                "  order by id desc ";
    }


    public static String getFarm_agogi(int customerID, String patientID, boolean monimi_agogi) {

        return " select n.* ,  \n " +
                "p.lastname + ' ' + p.firstname as patientName , dbo.nameuser(userID) as username, dbo.nameuser(userID_stop) as username_stop, \n" +
                " dbo.datetostr(dateStart) as datestart_str , dbo.datetostr(dateStop) as datestop_str \n" +
                (Customers.isFrontis(customerID) ? ", dosi.name as dosi_ID_text , sixn.name as sixnotita_ID_text " : "" ) +

        " from Nursing_xorigisi_farmakon_efapax  n  \n " +
                " left join person p on p.id = n.patientID  \n " +
                (Customers.isFrontis(customerID)
                        ?
                " left join Nursing_items_dosis dosi on dosi.id = n.dosi_ID  \n " +
                " left join Nursing_items_sixnotites sixn on sixn.id = n.sixnotita_ID  \n "
                        :
                        "")+
                " where patientID = " + patientID +
                (monimi_agogi ? " and efapax = 1 and sinedrias is null " : " and efapax is null and sinedrias = 1 ")
               // " \n and (DATESTOP IS NULL OR dbo.DateTime2Date(datestop) <= dbo.DateTime2Date(dbo.localtime()) )"

                ;
    }


        public static String getMed_instr_Notifications(String patientID) {
        return "  select n.id, " +
                " dbo.DateTimeToString(date) as date , " +
                " dbo.namedoctor(n.DoctorID) as doctor ," +
                " n.Description, " +
                " n.Confirmation_Date, " +
                " n.Confirmation " +
                " from Nursing_iatrikes_entoles n " +
                " left join transgroup tg on tg.id = n.TransGroupID " +
                " left join person p on p.id = tg.PatientID " +
                " where tg.patientid =  " + patientID +
                " order by date desc ";
    }


    public static String getCURRENT_POSOTITA_VELONON(String id){

        return "select posotita_vel15 ,posotita_vel16 , posotita_vel17 from Nursing_Hemodialysis_initial2_MEDIT " +
                "where id = " + id;
    }

    public static String getUPDATE_POSOTITA_VELONON(String id, String pos15 ,String pos16 ,String pos17){

        return "update Nursing_Hemodialysis_initial2_MEDIT " +
                "set posotita_vel15 = " + pos15 + " ,posotita_vel16 = " + pos16 + " ,posotita_vel17 = " + pos17 +
                " where id = " + id;
    }

    public String getDiagnosisIstoriko(String companyID ,String transgroupID){

        return "select top 1 " +
                "isnull(ng.id,'') as id,  " +
                "isnull(ng.DiagnosisIn,'')  as diagnosisIn, " +
                "isnull(ng.DiagnosisIn_ ,'') as diagnosisInICD10, " +
                "isnull(ng.DiagnosisInSecond,'')  as diagnosisInSecond , " +
                "isnull(ni.ipsos,'') as ipsos,  " +
                "isnull(ni.varos,'') as varos,  " +
                "isnull(ni.ikogeniaki_katastasi,'') as ikogeniaki_katastasi,  " +
                "isnull(ni.ikogeniako_istoriko,'') as ikogeniako_istoriko,  " +
                "isnull(ni.atomiko_anamnistiko,'') as atomiko_anamnistiko,  " +
                "isnull(ni.lipsi_farmakon,'') as lipsi_farmakon,  " +
                "isnull(ni.parousa_nosos,'') as parousa_nosos,  " +
                "isnull(ni.epaggelma,'') as epaggelma " +

                " from Nursing_Istoriko ni " +
                " left join NursingGroup ng ON ng.id=ni.TransGroupID " +
                " inner join TransGroup tg ON tg.id=ni.TransGroupID " +
                "  where (1=1) " +
                "  and isnull(tg.DateOut,0) = 0 " +
                "  and tg.ID = " + transgroupID +
                "  and tg.CompanyID = " + companyID +
                "  order by tg.DateIn desc" ;
    }


    public String getXREOSIMA_ILIKA(String transgroupID) {

        return XREOSIMA_ILIKA + transgroupID;
    }


    public String getISTORIKO_INSERT(String transgroupID ,String weight, String height
            ,String oikKatastasi, String oikIstoriko ,String parousaNosos, String lipsiFarmakon
            ,String atomAnamnistiko, String epaggelma) {

        return "INSERT INTO nursing_istoriko (transgroupID, varos, ipsos, ikogeniaki_katastasi, " +
                "ikogeniako_istoriko, lipsi_farmakon,parousa_nosos,atomiko_anamnistiko,epaggelma) " +
                "VALUES (" + transgroupID + "," + weight + "," + height + ",'" +  oikKatastasi + "' ,'" + oikIstoriko + "'," +
                " '" + lipsiFarmakon + "','" + parousaNosos + "' ,'" + atomAnamnistiko + "', '" + epaggelma + "')";
    }





    public String getISTORIKO_UPDATE(String transgroupID ,String weight, String height
            ,String oikKatastasi, String oikIstoriko ,String parousaNosos, String lipsiFarmakon
            ,String atomAnamnistiko, String epaggelma) {

        return "update nursing_istoriko " +
                "set varos  = " + weight + " , ipsos = " + height +
                " , ikogeniaki_katastasi = " + "'" + oikKatastasi +
                "' , ikogeniako_istoriko = " + "'" + oikIstoriko +
                "' , lipsi_farmakon = " + "'" + lipsiFarmakon +
                "' , parousa_nosos = " + "'" + parousaNosos +
                "' , atomiko_anamnistiko = " + "'" + atomAnamnistiko +
                "' , epaggelma = " + "'" + epaggelma + "'" +
                " where transGroupID = " + transgroupID;
    }




    public String getDIAITOLOGIO_PERSON(String transgroupID){

        return "select * , " +
                "dbo.datetostr(datefrom) as dateF, " +
                "dbo.timetostr(datefrom) as timeF, " +
                "isnull(SitisiSinodou,0) as sitisi_sinodou " +
                "from Nursing_Diaitologio " +
                "where transgroupid = " + transgroupID +
                " order by DateFrom desc , id";
    }


    public String getDIAITOLOGIO_INSERT(String transgroupID, String dateFrom, String hourFrom, String diaita
    , String remarks, String sitisiSinodou, String userID){

        String dateTime = "" ;

        if (sitisiSinodou.equals("0"))
            sitisiSinodou = "null";
        else
            sitisiSinodou = "'" + sitisiSinodou + "'";

        if (dateFrom .equals("") && hourFrom.equals(""))
            dateTime = "null";
            else
            dateTime = "dbo.timeToNum(CONVERT(datetime, '" + dateFrom + " " + hourFrom + "'" + ",103))";



        return Str_queries.setglobals(userID,"2","1") +
                "INSERT INTO nursing_Diaitologio(transgroupID, dateFrom, dieta, remarks, sitisiSinodou,userID) " +
                "VALUES (" + transgroupID + "," +
                dateTime +
                ",'" + diaita.replace(",","\ufffd") + "', ' " + remarks + "' ,  " + sitisiSinodou + "," + userID + ")";

    }


    public String getDIAITOLOGIO_DELETE(String id){

        return "delete nursing_Diaitologio " +
                "where id = " + id;
    }

    public String getDIAITOLOGIO_UPDATE(String id, String dateFrom, String hourFrom, String dieta
            ,String remarks, String sitisiSinodou,String userID){

        String dateTime = "" ;

        if (dateFrom .equals("") && hourFrom.equals(""))
            dateTime = "null";
        else
            dateTime = "dbo.timeToNum(CONVERT(datetime, '" + dateFrom + " " + hourFrom + "'" + ",103))";

        return "update Nursing_Diaitologio " +
                "set dateFrom = "  + dateTime + " , " +
                "dieta = " + "'" + dieta + "' , " +
                "remarks = " + "'" + remarks + "' , " +
                "sitisiSinodou = " + "'" + sitisiSinodou + "' , " +
                "userID = "  + userID + " " +
                "where id  = " + id;
    }


    public String getEKSITIRIO_PERSON(String transgroupID){

        return "select * from Nursing_Exitirio where transgroupid = " + transgroupID;
    }


    public String getEKTIMISI_EPIGONTON_PERSON(String transgroupID){

        return "select * from v_Nursing_Ektimisi_Epigonton where transgroupid = " + transgroupID;
    }


    public String getEKTIMISI_EPIGONTON_INSERT(String transgroupID, String kinitikotita, String anapSixnotita ,
                                               String kardSixnotita, String  sap,
                                               String thermokrasia , String epipSinProsoxis, boolean travma,
                                               ArrayList <ClassItemsCheckboxesForRV> lista){

        String traumatismos ;

        if (travma)
            traumatismos = "64";
        else
            traumatismos = null;


        return "insert into Nursing_Ektimisi_Epigonton("
                + "TransGroupID," +"kinitikotita," + "anapnefstiki_sixnotita," + "kardiaki_sixnotita,"
                + "sistoliki_artiriaki_piesi," + "thermokrasia," +"sinidisi_prosoxi,"

                +   "travma," + "apnoia," + "diasolinomenos,"
                + "anapnefstiki_disxeria," + "egkavma_prosopou," + "apousia_sfixeon," + "koma,"

                + "epiliptikoi_spasmoi," + "ipoglikaimia," + "dilitiriasi," + "anexelegkti_aimoragia,"
                + "troxaio," + "anoikta_travmata," + "travma_apo_aixmiro," + "egkavma_paxous_20_more,"
                + "kikloteres_egkauma_akron," + "oxia_dispnoia," + "ponos_stithous,"
                + "simptomatologia_egkefalikou," + "ilektroplixia," + "anoixto_katagma,"
                + "metavalomeni_sineidisi," + "aimatemesi," + "apovoli_aima_ptielon,"
                + "farmakeftiki_dilitiriasi," + "exarthrosi_megalon," + "ponos_7_more,"
                + "epithetikotita," + "asfigmo_akro," + "Ektetameno_oidima_akrou,"
                + "travmatismoi_mation," + "diavitikos_200_more," + "piretos_anosokatestalmeno,"
                + "kefalalgia_efnidia," + "koiliako_algos_egkiou," + "elegxomeni_aimoragia,"
                + "exarthrosi_daktilon," + "kleisto_katagma," + "egkavma_paxous_20_less,"
                + "koiliako_algos," + "sakxaro_300_more," + "travmatismos_egkiou,"
                + "apoleia_aimatos_egkiou" + ")"

                + "values(" + transgroupID + "," + kinitikotita + ","+ anapSixnotita + ","
                + kardSixnotita + "," + sap + "," + thermokrasia + "," + epipSinProsoxis + "," + traumatismos + ","
                + getElementsFromList(lista)

                + ")";



    }

    private String getElementsFromList(ArrayList <ClassItemsCheckboxesForRV> listaCH) {

        String result = "";
        for (int i = 0; i < listaCH.size(); i++) {

            if (!(i == 0 || i == 11 || i == 36)) {          // ΟΙ ΘΕΣΕΙΣ ΤΩΝ ΤΙΤΛΩΝ
                if (listaCH.get(i).isTrue())

                    result += 1 + ",";
                else
                    result += null + ",";
            }

        }

            // ΓΙΑ ΝΑ ΒΓΑΛΕΙ ΤΟ ΤΕΕΥΤΑΙΟ ΚΟΜΜΑ
            return result.substring(0, result.length() - 1);

    }


    public String getEKTIMISI_EPIGONTON_UPDATE(String transgroupID, String kinitikotita, String anapSixnotita ,
                                               String kardSixnotita, String  sap,
                                               String thermokrasia , String epipSinProsoxis,boolean travma,
                                               ArrayList <ClassItemsCheckboxesForRV> lista){

        String traumatismos ;

        if (travma)
            traumatismos = "64";
        else
            traumatismos = null;

        //-----------------
        //ΑΥΤΟ ΓΙΝΕΤΑΙ ΓΙΑ ΝΑ ΑΝΤΙΚΑΤΑΣΤΗΣΩ ΤΟ TRUE ΜΕ ΤΟ 1 Η NULL ΓΙΑ ΝΑ ΜΠΕΙ ΣΤΗ ΒΑΣΗ
        ArrayList <String> stringLista = new ArrayList<>();

        for (int i =0; i<lista.size(); i ++){

           String TFString = String.valueOf(lista.get(i).isTrue());

           if (TFString.equals("1"))
            stringLista.add("1");
           else
               stringLista.add(null);

        }

//-------------------------

        return "UPDATE Nursing_Ektimisi_Epigonton "
                + "SET "
                + "kinitikotita= " + kinitikotita + ","
                + "anapnefstiki_sixnotita= " + anapSixnotita + ","
                + "kardiaki_sixnotita= " + kardSixnotita + ","
                + "sistoliki_artiriaki_piesi= " + sap + ","
                + "thermokrasia= " + thermokrasia + ","
                + "sinidisi_prosoxi= " + epipSinProsoxis + ","
                + "travma= " + traumatismos + ","
                + "apnoia= " + stringLista.get(1) + ","
                + "diasolinomenos= " + stringLista.get(2) + ","
                + "anapnefstiki_disxeria= " + stringLista.get(3) + ","
                + "egkavma_prosopou= " + stringLista.get(4) + ","
                + "apousia_sfixeon= " + stringLista.get(5) + ","
                + "koma=" + stringLista.get(6) + ","
                + "epiliptikoi_spasmoi=" + stringLista.get(7) + ","
                + "ipoglikaimia=" + stringLista.get(8) + ","
                + "dilitiriasi=" + stringLista.get(9) + ","
                + "anexelegkti_aimoragia=" + stringLista.get(10) + ","
                + "troxaio=" + stringLista.get(12) + ","
                + "anoikta_travmata=" + stringLista.get(13) + ","
                + "travma_apo_aixmiro=" + stringLista.get(14) + ","
                + "egkavma_paxous_20_more=" + stringLista.get(15) + ","
                + "kikloteres_egkauma_akron=" + stringLista.get(16) + ","
                + "oxia_dispnoia=" + stringLista.get(17) + ","
                + "ponos_stithous=" + stringLista.get(18) + ","
                + "simptomatologia_egkefalikou=" + stringLista.get(19) + ","
                + "ilektroplixia=" + stringLista.get(20) + ","
                + "anoixto_katagma=" + stringLista.get(21) + ","
                + "metavalomeni_sineidisi=" + stringLista.get(22) + ","
                + "aimatemesi=" + stringLista.get(23) + ","
                + "apovoli_aima_ptielon=" + stringLista.get(24) + ","
                + "farmakeftiki_dilitiriasi=" + stringLista.get(25) + ","
                + "exarthrosi_megalon=" + stringLista.get(26) + ","
                + "ponos_7_more=" + stringLista.get(27) + ","
                + "epithetikotita=" + stringLista.get(28) + ","
                + "asfigmo_akro=" + stringLista.get(29) + ","
                + "Ektetameno_oidima_akrou=" + stringLista.get(30) + ","
                + "travmatismoi_mation=" + stringLista.get(31) + ","
                + "diavitikos_200_more=" + stringLista.get(32) + ","
                + "piretos_anosokatestalmeno=" + stringLista.get(33) + ","
                + "kefalalgia_efnidia=" + stringLista.get(34) + ","
                + "koiliako_algos_egkiou=" + stringLista.get(35) + ","
                + "elegxomeni_aimoragia=" + stringLista.get(37) + ","
                + "exarthrosi_daktilon=" + stringLista.get(38) + ","
                + "kleisto_katagma=" + stringLista.get(39) + ","
                + "egkavma_paxous_20_less=" + stringLista.get(40) + ","
                + "koiliako_algos=" + stringLista.get(41) + ","
                + "sakxaro_300_more=" + stringLista.get(42) + ","
                + "travmatismos_egkiou=" + stringLista.get(43) + ","
                + "apoleia_aimatos_egkiou=" + stringLista.get(44) + " "
                + "where  TransGroupID = " + transgroupID
                ;
    }




    //----------------------------


    public static String getIsozigio_A_P(String transgroupID, String date, String watchID) {
        return "SELECT * " +
                "from v_Nursing_isozigio_proslamvanomenon_apovallomenon " +
                "where TransGroupid = " + transgroupID  +
                " and date =  dbo.timeToNum(CONVERT(datetime,  " + "'" + date  + "' , 103)) " +
                " and watch = " + watchID;
    }


    public String getPARAKOLOUTHISI_PERSON(String transgroupID, String date , String watch){

        return "select ID, TransGroupID ,Date, Watch, " +
                "sfixeis,sis_piesi,dias_piesi,thermokrasia,oximetria,anapnoes,ponos,sakxara,Episkliridios,varos,ipsos, " +
                "in_stoma,in_endoflevia,out_oura,out_paroxetefsi1,out_paroxetefsi2, " +
                "out_paroxetefsi3,out_paroxetefsi4,out_emetoi,out_efidroseis,out_kenoseis " +
                "from Nursing_Parakolouthisi " +
                " where transgroupID = " + transgroupID +
                " and date = dbo.timeToNum(CONVERT(datetime, '" + date + "' , 103))" +
                " and watch = " + watch;
    }


    public String getPARAKOLOUTHISI_INSERT(String transgroupid, String date, String watchID, ArrayList <ClassItemsForRV> lista){




        return "insert into Nursing_Parakolouthisi(transgroupid , date , watch ,sfixeis,piesi,thermokrasia,oximetria," +
                "anapnoes, ponos, Episkliridios, varos, ipsos,in_stoma, in_endoflevia," +
                "out_oura, out_paroxetefsi1 ,out_paroxetefsi2, out_emetoi," +
                "out_efidroseis, out_kenoseis)" +

                "values(" + transgroupid + "," +
                "dbo.timeToNum(CONVERT(datetime, '" + date + "' ,103))," +
                watchID + "," +
                getElementsForParakolouthisiList(lista) + ")";
    }

    public String getPARAKOLOUTHISI_UPDATE(String id, ArrayList <ClassItemsForRV> lista){




        return "update nursing_parakolouthisi " +
                "set sfixeis = " + Utils.checkNull(lista.get(0).getValue()) + " , piesi= " + Utils.checkNull(lista.get(1).getValue()) +
                " , thermokrasia= " + Utils.checkNull(lista.get(2).getValue()) + " , oximetria= " + Utils.checkNull(lista.get(3).getValue()) +
                " , anapnoes= " + Utils.checkNull(lista.get(4).getValue()) + " , " + "ponos= " + Utils.checkNull(lista.get(5).getValue()) +
                 " , Episkliridios= " + "'" + lista.get(6).getValue() + "' , varos = " + Utils.checkNull(lista.get(7).getValue()) +
                " , ipsos= " + Utils.checkNull(lista.get(8).getValue()) + " ," +
                "in_stoma= " + "'" + lista.get(10).getValue() +"' , in_endoflevia= '" + lista.get(11).getValue() +"' , " +
                "out_oura= '" + lista.get(13).getValue() +"' , out_paroxetefsi1= '" + lista.get(14).getValue() +"' ," +
                " out_paroxetefsi2= '" + lista.get(15).getValue() +"' , out_emetoi= '" + lista.get(16).getValue() +"' ," +
                "out_efidroseis = '" + lista.get(17).getValue() +
                "' , out_kenoseis = " + Utils.checkNull(InfoSpecificLists.getKenoseisID(lista.get(18).getValue())) +
                " where id  = " + id ;
    }

//    private String getgetElemenentParakolUpdATE(String str){
//
//    }


    private  String getElementsForParakolouthisiList(ArrayList <ClassItemsForRV> lista){


        String kenosiID = InfoSpecificLists.getKenoseisID(lista.get(lista.size() - 1).getValue());

        lista.get(lista.size() - 1).setValue(kenosiID);             //ΤΟ ΚΕΙΜΕΝΟ ΤΟ ΚΑΝΩ ID ΓΙΑ ΝΑ ΤΟ ΒΑΛΩ ΣΤΗ ΒΑΣΗ

        String numRegex   = ".*[0-9].*";
        String alphaRegex = ".*[A-Z].*";


        String result = "";
        for (int i = 0; i < lista.size(); i++) {

            if (!(i == 9 || i == 12)) {          // ΟΙ ΘΕΣΕΙΣ ΤΩΝ ΤΙΤΛΩΝ

                if (lista.get(i).getValue().trim().equals(""))
                    result +=  null + ",";

                else  //(lista.get(i).getValue().trim().matches(alphaRegex) )// ΕΛΕΓΧΕΙ ΑΝ ΕΧΕΙ ΓΡΑΜΜΑΤΑ ΚΑΙ ΑΡΙΘΜΟΥΣ
                    result +=  "'" + lista.get(i).getValue() + "'" + ",";

//                else if (lista.get(i).getValue().trim().matches(numRegex) )// ΕΛΕΓΧΕΙ ΑΝ ΕΧΕΙ ΓΡΑΜΜΑΤΑ ΚΑΙ ΑΡΙΘΜΟΥΣ
//                    result +=   lista.get(i).getValue()  + ",";


//                else if (lista.get(i).getValue().trim().matches(".*\\d+.*"))  // ΕΛΕΓΧΕΙ ΑΝ ΕΧΕΙ ΜΟΝΟ ΑΡΙΘΜΟΥΣ
//                    result +=  lista.get(i).getValue() + ",";



//                else
//                    result +=  "'" + lista.get(i).getValue() + "'" + ",";

            }
        }


        return result.substring(0, result.length() - 1);
    }


    //-----------------------------------

    public static String getPARAKOLOUTHISI_DIAGRAM_INFO(String transgroupID ,String katigoria, String date){

        return "select " + katigoria + ", watch " + "  from nursing_parakolouthisi where TransGroupid = " + transgroupID +
                " and date = dbo.timeToNum(CONVERT(datetime, '" + date +"',103)) " +
                " order by watch";
    }

    public static String getZOTIKA_DIAGRAM_INFO_ANA_HOUR(String transgroupID ,String katigoria, String date){

        return "select " + katigoria + ", watch " + "  from Nursing_Zotika_Simeia where TransGroupid = " + transgroupID +
                " and date = dbo.timeToNum(CONVERT(datetime, '" + date +"',103)) " +
                " and LEN(watch) < 3 " +
                " order by watch";
    }

    public static String getZOTIKA_DIAGRAM_INFO_ANA_3ORO(String transgroupID ,String katigoria, String date){

        return "select " + katigoria + ", watch " + "  from Nursing_Zotika_Simeia where TransGroupid = " + transgroupID +
                " and date = dbo.timeToNum(CONVERT(datetime, '" + date +"',103)) " +
                " and LEN(watch) = 3 " +
                " order by watch";
    }

//    public static String getNOSIL_ISTORIKO_PERSON(String transgroupID){
//
//        return " , UserID, blue_count  from v_Nursing_nosil_istoriko where TransGroupID = " + transgroupID;
//    }

    public static String getNOSIL_ISTORIKO_PERSON(String transgroupID){

        return "select *,\n" +
                "(CASE WHEN Tropos_metaforas IN (2,3,4) THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN omilia IN (2) THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN akoi IN (2,4) THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN orasi IN (2,4) THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN anapnoi IN (2) THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN anaimia = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN nautia = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN emetoi = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN diskolia_sti_kataposi = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN paxi_entero IN (3) THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN sixnoouria = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN disouria = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN ipo_emokatharsi = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN ourokathetiras = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN voithia_igiini = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN voithia_ntisimo = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN voithia_egersi = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN ms_ponos = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN me_pi = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN me_pateritses = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN me_mpasouni = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN katagma = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN anisixos = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN agnoi_tin_sovatorita = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN epikinonia IN (2,3) THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN imipligia = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN epiliptikes_kriseis = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN lipothimia = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN adinamia = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN moudiasma = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN tromos = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN kefalalgia = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN zali = 1 THEN 1 ELSE 0 END\n"
                + "+ CASE WHEN xrisi_inopnevmatos = 1 THEN 1 ELSE 0 END) as Blue_count\n" +
                " from Nursing_nosil_istoriko where TransGroupID = " + transgroupID;
    }



    public static String getNOSILEUTIKO_ISTORIKO_INSERT(String transgroupid, ArrayList<Nosil_IstorikoList> lista){

        String values = "";



        for (int i=0; i<lista.size(); i++) {
// ΟΙ ΤΙΤΛΟΙ ΠΟΥ ΕΙΧΑ ΒΑΛΕΙ ΣΤΗ ΛΙΣΤΑ
            if (!(i == 0 || i == 20 || i == 29
                    || i == 32  || i == 41
                    || i == 57  || i == 68 || i == 83  || i == 92
                    || i == 104  || i == 110 || i == 115)){


            if (lista.get(i).getTextViewSForRV() != null) {
                if (lista.get(i).getTextViewSForRV().getValue().trim().equals(""))
                     values += null + ",";
                else
                    values += lista.get(i).getTextViewSForRV().getValue() + ",";

            }

            else if (lista.get(i).getEditTextForRV() != null) {

                if (lista.get(i).getEditTextForRV().getValue().trim().equals(""))
                    values += null + ",";

                else if ((lista.get(i).getEditTextForRV().getType() == 1))
                    values += "'" + lista.get(i).getEditTextForRV().getValue() + "',";

                else
                values += lista.get(i).getEditTextForRV().getValue() + ",";
            }


            else if (lista.get(i).getSpinnersForRV() != null) {
                if (lista.get(i).getSpinnersForRV().getValue().trim().equals("0"))
                    values += null + ",";
                else if (lista.get(i).getSpinnersForRV().getValue().trim().equals(""))
                    values += null + ",";
                else
                values += lista.get(i).getSpinnersForRV().getValue() + ",";
            }



            else if (lista.get(i).getCheckBoxesForRV() != null) {

              if(lista.get(i).getCheckBoxesForRV().isChecked())
               values +=  1 + ",";
                 else
                   values+= null + ",";
            }

            }

        }




        return  "INSERT INTO Nursing_nosil_istoriko (TransGroupID,Date,Eidos_eisagogis," +
                " Tropos_metaforas,info_provider,Alla_nosimata,Sakx_diavitis," +
                " Proigoumenes_nosileies,Allergies,metra_loimoxeon,simptomata_eisagogis," +
                " sfixeis,piesi1,piesi2,thermokrasia,oximetria,anapnoes,ponos,varos,ipsos,omilia,xeni_glossa," +
                " akoi,akoustika,orasi,derma,travma,elki_kataklisis,anapnoi,vixas,kardiakos_rithmos,vimatodotis,esthima_palmon," +
                " ipertasi,idimata,flevitida,anaimia,stoixeia_hkg,dieta_eleftheri,dieta_idiki,orexi,diskolia_sti_masisi," +
                " odontostixia,rinogastrikos_solinas,nautia,emetoi,diskolia_sti_kataposi," +
                " gastrostomia,teleftea_kenosi,paxi_entero,melena_kenosi,diatasi_kilias,kolostomia,sixnoouria," +
                " disouria,aimatouria,oligouria,Anouria,ipo_emokatharsi,ourokathetiras," +
                " ourokathetiras_eidos,ourokathetiras_no,ourokathetiras_date_topothetisi," +
                " autoexipiretisi,voithia_igiini,voithia_ntisimo,voithia_egersi,ms_ponos,ms_ponos_pou,ms_ponos_klimaka,vadisi," +
                " me_pi,me_pateritses,me_mpasouni,den_metakinite,katagma,katagma_pou,aisioodoxos,anisixos," +
                " fovismenos,apomonomenos,enimeros_gia_tin_noso,agnoi_tin_sovatorita,fovos_thanatou,epikinonia," +
                " imipligia,paralisi,epiliptikes_kriseis,lipothimia,adinamia,moudiasma,tromos,kefalalgia,zali," +
                " psosanatolismenos,koma,kapnisma,xrisi_inopnevmatos,alles_sinithies,ores_ipnou,zei_monos," +
                " dika_tou_farmaka,dikes_tou_exetaseis,aristeroxeiros,dexioxeiros,nosileftikes_diagnoseis) " +

                "values(" + transgroupid + " , " +
                values.substring(0, values.length()-1) + ");" ;
    }




    public static String getNOSILEUTIKO_ISTORIKO_UPDATE(String transgroupID, ArrayList<Nosil_IstorikoList> lista) {

        return "UPDATE Nursing_nosil_istoriko " +
                "SET Date = " + getTVvalue(lista,1)+ ", Eidos_eisagogis = " + getSPvalue(lista, 2) +
                "  ," + "Tropos_metaforas = " + getSPvalue(lista,3)+ ", info_provider = " + getSPvalue(lista, 4) +
                "  ," + "Alla_nosimata = '" + getETvalue(lista,5)+ "', Sakx_diavitis = " + getCHvalue(lista, 6) +
                "  ," + "Proigoumenes_nosileies = '"  + getETvalue(lista,7)+ "', Allergies = '" + getETvalue(lista, 8) + "'" +
                "  ," + "metra_loimoxeon = '" + getSPvalue(lista, 9) + "'" +

                "  ," + "simptomata_eisagogis = '"  + getETvalue(lista,10)+ "', sfixeis = " + getETvalue(lista, 11) +
                "  ," + "piesi1 = '"  + getETvalue(lista,12) + "'" + ", piesi2 = '" + getETvalue(lista, 13) + "'" +
                "  ," + "thermokrasia = " + getETvalue(lista,14)+ ", oximetria = " + getETvalue(lista, 15) +
                "  ," + "anapnoes = "  + getETvalue(lista,16)+ ", ponos = " + getETvalue(lista, 17) +

                "  ," + "varos = " + getETvalue(lista, 18) +
                "  ," + "ipsos = "  + getETvalue(lista,19)+ ", omilia = " + getSPvalue(lista, 21) +
                "  ," + "xeni_glossa = '"  + getETvalue(lista,22)+ "', akoi = " + getSPvalue(lista, 23) +
                "  ," + "akoustika = " + getSPvalue(lista, 24) +
                "  ," + "orasi = "  + getSPvalue(lista,25)+ ", derma = " + getSPvalue(lista, 26) +

                "  ," + "travma = '"  + getETvalue(lista,27)+ "', elki_kataklisis = '" + getETvalue(lista, 28) + "'" +
                "  ," + "anapnoi = "  + getSPvalue(lista,30)+ ", vixas = " + getSPvalue(lista, 31) +
                "  ," + "kardiakos_rithmos = "  + getSPvalue(lista,33)+ ", vimatodotis = '" + getETvalue(lista, 34) + "'" +
                "  ," + "esthima_palmon = "  + getCHvalue(lista,35)+ ", ipertasi = " + getCHvalue(lista, 36) +

                "  ," + "idimata = "  + getCHvalue(lista,37)+ ", flevitida = " + getCHvalue(lista, 38) +
                "  ," + "anaimia = "  + getCHvalue(lista,39)+ ", stoixeia_hkg = '" + getETvalue(lista, 40) +  "'" +
                "  ," + "dieta_eleftheri = "  + getCHvalue(lista,42)+ ", dieta_idiki = '" + getETvalue(lista, 43) +  "'" +

                "  ," + "orexi = "  + getSPvalue(lista,44)+ ", diskolia_sti_masisi = " + getCHvalue(lista, 45) +
                "  ," + "odontostixia = "  + getSPvalue(lista,46)+ ", rinogastrikos_solinas = '" + getETvalue(lista, 47) + "'" +
                "  ," + "nautia = "  + getCHvalue(lista,48)+ ", emetoi = " + getCHvalue(lista, 49) +
                "  ," + "diskolia_sti_kataposi = "  + getCHvalue(lista,50)+ ", gastrostomia = " + getCHvalue(lista, 51) +

                "  ," + "teleftea_kenosi = "  + getdateTVvalue(lista,52)+ ", paxi_entero = " + getSPvalue(lista, 53) +
                "  ," + "melena_kenosi = "  + getCHvalue(lista,54)+ ", diatasi_kilias = " + getCHvalue(lista, 55) +
                "  ," + "kolostomia = "  + getCHvalue(lista,56)+ ", sixnoouria = " + getCHvalue(lista, 58) +
                "  ," + "disouria = "  + getCHvalue(lista,59)+ ", aimatouria = " + getCHvalue(lista, 60) +

                "  ," + "oligouria = "  + getCHvalue(lista,61)+ ", Anouria = " + getCHvalue(lista, 62) +
                "  ," + "ipo_emokatharsi = "  + getCHvalue(lista,63)+ ", ourokathetiras = " + getSPvalue(lista, 64) +
                "  ," + "ourokathetiras_eidos = '"  + getETvalue(lista,65)+ "', ourokathetiras_no = '" + getETvalue(lista, 66) + "'" +
                "  ," + "ourokathetiras_date_topothetisi = '"  + getdateTVvalue(lista,67)+ "', autoexipiretisi = " + getCHvalue(lista, 69) +

                "  ," + "voithia_igiini = "  + getCHvalue(lista,70)+ ", voithia_ntisimo = " + getCHvalue(lista, 71) +
                "  ," + "voithia_egersi = "  + getCHvalue(lista,72)+ ", ms_ponos = " + getCHvalue(lista, 73) +
                "  ," + "ms_ponos_pou = '"  + getETvalue(lista,74)+ "', ms_ponos_klimaka = " + getETvalue(lista, 75) +
                "  ," + "vadisi = "  + getCHvalue(lista,76)+ ", me_pi = " + getCHvalue(lista, 77) +

                "  ," + "me_pateritses = "  + getCHvalue(lista,78)+ ", me_mpasouni = " + getCHvalue(lista, 79) +
                "  ," + "den_metakinite = "  + getCHvalue(lista,80)+ ", katagma = " + getCHvalue(lista, 81) +
                "  ," + "katagma_pou = '"  + getETvalue(lista,82)+ "', aisioodoxos = " + getCHvalue(lista, 84) +
                "  ," + "anisixos = "  + getCHvalue(lista,85)+ ", fovismenos = " + getCHvalue(lista, 86) +

                "  ," + "apomonomenos = "  + getCHvalue(lista,87)+ ", enimeros_gia_tin_noso = " + getCHvalue(lista, 88) +
                "  ," + "agnoi_tin_sovatorita = "  + getCHvalue(lista,89)+ ", fovos_thanatou = " + getCHvalue(lista, 90) +
                "  ," + "epikinonia = "  + getSPvalue(lista,91)+ ", imipligia = " + getCHvalue(lista, 93) +
                "  ," + "paralisi = "  + getCHvalue(lista,94)+ ", epiliptikes_kriseis = " + getCHvalue(lista, 95) +

                "  ," + "lipothimia = "  + getCHvalue(lista,96)+ ", adinamia = " + getCHvalue(lista, 97) +
                "  ," + "moudiasma = "  + getCHvalue(lista,98)+ ", tromos = " + getCHvalue(lista, 99) +
                "  ," + "kefalalgia = "  + getCHvalue(lista,100)+ ", zali = " + getCHvalue(lista, 101) +
                "  ," + "psosanatolismenos = "  + getCHvalue(lista,102)+ ", koma = " + getCHvalue(lista, 103) +

                "  ," + "kapnisma = "  + getCHvalue(lista,105)+ ", xrisi_inopnevmatos = " + getCHvalue(lista, 106) +
                "  ," + "alles_sinithies = '"  + getETvalue(lista,107)+ "', ores_ipnou = " + getETvalue(lista, 108) +
                "  ," + "zei_monos = "  + getCHvalue(lista,109)+ ", dika_tou_farmaka = " + getCHvalue(lista, 111) +
                "  ," + "dikes_tou_exetaseis = '"  + getCHvalue(lista,112)+ "', aristeroxeiros = " + getCHvalue(lista, 113) +
                "  ," + "dexioxeiros = "  + getCHvalue(lista,114)+ ", nosileftikes_diagnoseis = '" + getETvalue(lista, 116) + "'" +

                " WHERE transgroupid = " + transgroupID + " ;";
    }


    private static String getTVvalue( ArrayList<Nosil_IstorikoList> lista ,int position){

        String tmp = lista.get(position).getTextViewSForRV().getValue();
        if (tmp == null || tmp.equals(""))
            return  null;
        else
             return tmp ;


    }

    private static String getdateTVvalue( ArrayList<Nosil_IstorikoList> lista ,int position){

        String tmp = lista.get(position).getTextViewSForRV().getValue();
        if (tmp == null || tmp.equals(""))
            return  null;
        else
            return Utils.convertDateTomilliseconds(tmp) ;


    }

    private static String getETvalue( ArrayList<Nosil_IstorikoList> lista ,int position){

        String tmp = lista.get(position).getEditTextForRV().getValue();
        if (tmp == null || tmp.equals(""))
            return  null;
        else
            return tmp ;
    }

    private static String getCHvalue( ArrayList<Nosil_IstorikoList> lista ,int position){

        boolean tmp = lista.get(position).getCheckBoxesForRV().isChecked();
        if (tmp)
            return  "1";
        else
            return null ;

    }
    private static String getSPvalue( ArrayList<Nosil_IstorikoList> lista ,int position){

        String tmp = lista.get(position).getSpinnersForRV().getValue();
        if (tmp == null || tmp.equals(""))
            return  null;
        else
            return tmp ;

    }



    public static String getFARMAKA_POU_XRISIMOPOIOUNTAI_PERSON(String transgroupid){

        return "select id, eidos , dosi, dbo.DateTimeToString(teleftea_lipsi) as teleftea_lipsi ,paratiriseis " +
                "from Nursing_nosil_istoriko_farmaka where transgroupid = " + transgroupid;
    }

    public static String getFARMAKA_POU_XRISIMOPOIOUNTAI_DELETE(String id){

        return "delete Nursing_nosil_istoriko_farmaka where id = " + id;
    }


    public static String getFARMAKA_POU_XRISIMOPOIOUNTAI_INSERT(String userID,String transgroupid, String eidos, String dosi, String lastlipsi ,String paratiriseis){

        return "INSERT INTO Nursing_nosil_istoriko_farmaka(userID, transgroupid, eidos, dosi, teleftea_lipsi, paratiriseis) " +
                "values( '" + userID + "','"  + transgroupid + "','" + eidos + "','" + dosi + "', " +
                "dbo.timeToNum(CONVERT(datetime, " + "'" + lastlipsi + "' , 103)) , '" + paratiriseis + "')";
    }



    public static String getFARMAKA_POU_XRISIMOPOIOUNTAI_UPDATE(String id, String eidos, String dosi ,String paratiriseis){

        return "update Nursing_nosil_istoriko_farmaka " +
                " set eidos = " + "'" + eidos + "' , dosi = '" + dosi + "'" + " , paratiriseis  = '" + paratiriseis + "' " +
                " where id = " + id;
    }





    public static String getSNEL_PERSON(String transgroupid){

        return "select * from nursing_snel where transgroupid = " + transgroupid;
    }

    public static String getSNEL_sigkentrotika(){

        return "select p.name, s.* , \n" +
                " dbo.nameuser(userID) as username, \n" +
                " dbo.datetostr(tg.dateIn) as date \n" +
                "from Nursing_Snel s\n" +
                "join TransGroup tg on tg.id = s.transgroupid \n" +
                "join person p on p.id = tg.PatientID \n" +
                " where tg.dateout is null " +
                " and tg.category = 1\n" +
                " order by p.name";
    }

    public String getZOTIKA_24ORO_ANA_ORA_PERSON(String transgroupID, String date , String watch){

        return "select * from Nursing_Zotika_Simeia " +
                "where transgroupID = " + transgroupID +
                " and date = dbo.timeToNum(CONVERT(datetime, '" + date + "' , 103))" +
                " and watch = " + watch;
    }



    public static String getOLA_TA_PEDIA_PINAKA(String table){

        return "select COLUMN_NAME " +
                "from INFORMATION_SCHEMA.COLUMNS " +
                "where TABLE_NAME = '" + table + "' and TABLE_SCHEMA = 'dbo'";
    }





    public String getNOSILEUTIKES_LOGODOSIES(String transgroupID) {

        return  " select n.* , dbo.datetostr( n.date) as datestr, us.Name " +
                " from Nursing_Nosileutiki_logodosia n " +
                " left join Users us on us.ID = n.userid where transgroupid =  "  + transgroupID +
              //  " and date = dbo.timeToNum(CONVERT(datetime, '" + date + "' , 103)) " +
                " order by date desc";

    }



    public static String antigrafiProigoumenisMeras_farmaka() {
           return  "INSERT INTO Nursing_ores_xorigisis (date,xorigisiid,hour) "  +
                    "  SELECT (dbo.timeToNum(CONVERT(datetime,'" + Utils.getCurrentDate() + "' , 103))) , xorigisiid,hour  " +
                    "  from Nursing_ores_xorigisis n "  +
                    "  left join Nursing_xorigisi_farmakon f on f.ID = n.xorigisiID "  +
                    "  where f.diakopike is null "  +
                    " delete Table_checks "  +
                    " INSERT INTO Table_checks (date) values  (dbo.timeToNum(CONVERT(datetime, '" + Utils.getCurrentDate() + "' , 103)))";
    }

    public String getMedicines_PERSON(String transgroupID, String date){
        return "select n.* , dbo.datetostr(n.datestart) as dateS, n.diakopike, " +
                "dbo.datetostr(n.datestop) as datestop," +
                " i.name  from Nursing_xorigisi_farmakon n" +
                " left join item i on i.id = n.itemid " +
                " where transgroupid = " + transgroupID +
                " and (dateStart <= dbo.timeToNum(CONVERT(datetime, '" + date + " ' , 103))" +
                " and datestop is null " +
                " or datestop >= dbo.timeToNum(CONVERT(datetime, '" + date + "  ' , 103)) )" +
                " order by category";
    }

    public static String updateXorigisiFarmakon_xorigithike(String id, String checked){
       return   " update  Nursing_xorigisi_farmakon " +
                " set xorigithike = " + checked +
                " where id = " + id;
    }

    public static String updateXorigisiFarmakon_Diakopike(String id,String date, String diakopike){
        String dateStopStr = null;
        if (diakopike != null)
            dateStopStr = " dbo.timeToNum(CONVERT(datetime, ' " + date + "', 103)) ";


        return   " update  Nursing_xorigisi_farmakon " +
                " set  diakopike = " + diakopike +
                 ", datestop = " + dateStopStr +
                " where id = " + id;
    }


    public static String getKATHIMERINO_ZIGISMA_PERSON(String transgroupID,String date) {

        return  " select top 1 n1.ID, n1.TransGroupID,n1.date,n1.anaf_varos, " +
        " n1.varos,n1.pro_varos,n1.ipsos,  n1.day_text, dbo.datetostr(n1.date) as dateS  " +
        " from Nursing_Kathimerino_Zigisma n1 " +
        " where n1.transgroupid = " + transgroupID +
        " and date = dbo.timeToNum(CONVERT(datetime, '" + date.replace("-","/") + "' , 103))  ";

    }


    public static  String getNEURIKI_AKSIOLOGISI_SIGKENTROTIKA(String transgroupID, String date , boolean anaOra){

        return  "select * from Nursing_Parakolouthisi_Neurologiki " +
                " where transgroupid =  " + transgroupID +
                " and date = dbo.timeToNum(CONVERT(datetime, '" + date.replace("-","/") + "' , 103))  " +
                (anaOra ? " and len(watch) < 3" : " and len(watch) > 2");
    }


    public static String getDIAITOLOGIO_SIGKENTROTIKA() {
        return  "select  " +

                "f.Name as floorName, " +
                "b.Code , " +
             //   "md.transgroupid, " +
                "p.Name as patName, " +
                "c.Name as clinicName, " +
                "d.Dieta, " +
                "d.odigies, " +
                "d.Remarks, " +
                "d.SitisiSinodou, " +
                "dbo.timeToStr(datefrom) as tme, " +
                "tg.Ins1 as insurance1,  " +
                "tg.Ins2 as insurance2,   " +
                "tg.Ins3 as insurance3,   " +
                "tg.Ins4 as insurance4 " +

                "from Nursing_Diaitologio d " +
                "left join NursingGroup tg on tg.id = d.TransGroupID " +
                "inner join ( " +
                "    select transgroupid as transgroupid, max(datefrom) as MaxDate " +
                "    from Nursing_Diaitologio " +
                "   group by transgroupid " +
                ") md on d.TransGroupID = md.transgroupid and d.DateFrom = md.MaxDate " +

                "left join person p on p.id = tg.PatientID " +
                "left join bed b on b.ID = tg.BedID " +
                "left join room r on r.id = b.RoomID " +
                "left join floor f on f.id = r.FloorID " +
                "left join clinic c on c.ID = tg.ClinicID " +

                "group by   f.Name,b.Code,md.transgroupid,tg.DiagnosisIn, c.name, " +
                "dbo.nameperson(tg.PatientID)  ,d.datefrom, p.Name, d.Dieta,d.odigies, d.Remarks, d.SitisiSinodou, " +
                "tg.Ins1,tg.Ins2,tg.Ins3,tg.Ins4 " ;
    }


    public static String getDIAITOLOGIO_SIGKENTROTIKA_ASTHENI(String transgroupID) {
        return  "select * from Nursing_diaitologio " +
                " where transgroupid =  " + transgroupID ;
    }


    public static  String getKATHIMERINO_ZIGISMA_SIGKENTROTIKA(String transgroupID){

        return  "select top 20 dbo.dateToStr(date) as dateStr, day_text from Nursing_Kathimerino_Zigisma " +
                " where transgroupid =  " + transgroupID +
                " order by date desc";
    }


    public static  String getZOTIKA_SIMEIA_SIGKENTROTIKA_TON_ASETHENWN_SIGKEKRIMENI_HOUR(String date, String watch_ID){

        return  "select z.* ,\n" +
                " dbo.NAMEUSER(userID) as username, p.FirstName + ' ' + p.LastName as patName, \n" +
                " dbo.datetostr(z.date) as dateStr \n" +
                " from Nursing_Zotika_Simeia  z \n" +
                " join transgroup tg on tg.id = z.transgroupid\n " +
                " join person p on p.id = tg.PatientID\n " +
                " where \n" +
                " z.date = dbo.timeToNum(CONVERT(datetime, '" + date.replace("-","/") + "' , 103))  \n" +
                " and watch = "  + watch_ID +
                " order by watch";
    }

    public static  String getZOTIKA_SIMEIA_SIGKENTROTIKA_ANA_HOUR(String transgroupID,String date){

        return  "select * ,\n" +
                " dbo.NAMEUSER(userID) as username, p.FirstName + ' ' + p.LastName as patName, \n" +
                " dbo.datetostr(z.date) as dateStr \n" +
                " from Nursing_Zotika_Simeia z \n" +
                " join transgroup tg on tg.id = z.transgroupid\n " +
                " join person p on p.id = tg.PatientID\n " +
                " where z.transgroupid =  " + transgroupID +
                " and z.date = dbo.timeToNum(CONVERT(datetime, '" + date.replace("-","/") + "' , 103))  " +
                " and LEN(watch) < 3 " +
                " order by watch";
    }


    public static  String getZOTIKA_SIMEIA_SIGKENTROTIKA_ANA_3_ORO(String transgroupID,String date){

        return  "select *,\n" +
                " dbo.NAMEUSER(z.userID) as username, p.FirstName + ' ' + p.LastName as patName, \n" +
                " dbo.datetostr(z.date) as dateStr \n" +
                " from Nursing_Zotika_Simeia z\n" +
                " join transgroup tg on tg.id = z.transgroupid\n " +
                " join person p on p.id = tg.PatientID\n " +
                " where z.transgroupid =  " + transgroupID +
                " and z.date = dbo.timeToNum(CONVERT(datetime, '" + date.replace("-","/") + "' , 103))  " +
                " and LEN(watch) = 3 " +
                " order by watch";
    }

    public static String getMEDICAL_INSTRACTIONS_OLD(String cols, String patientid){
        int custID = Utils.getCustomerID(MyApplication.getAppContext());

        return  "select top 1 " + cols + " ,n.ID ,dbo.timetostr(duration) as durationTime , \n" +
                "ep.name as epoName ,  eid.name as eidosName, fil.name as filName,patientID, \n" +
                "ag.name as agogiName, dos.name as agogiDosisName," +
                (Customers.isFrontis(custID) ? " fe.name as feName, lcar.name as CarnitineName  ,vit.name as VitB_Name , " : "") +
                (Customers.isFrontis(custID) ?  " ipok.name as ipokName, other_meds," : "") +
                "vitD.name as VitD_Name,\n" +
                " d.name as dialeimaName,\n" +
              // " ipok.name as ipokName," +
                " dbo.datetostr(embolio_b) as embolio_b,\n" +
                " dbo.datetostr(embolio_antiFlu) as embolio_antiFlu,\n" +
                " dbo.NAMEDOCTOR(n.doctorid) as doctorName, y.name as yearName \n" +
                " from Nursing_Medical_Instructions n \n" +
                " inner join years y on y.id = n.year \n" +
                " left join  Nursing_items_med_instr_epoetin     ep    on ep.ID =  n.epoetinID \n" +
                " left join  Nursing_items_med_instr_eidos_aim    eid  on eid.ID = n.eidos_aim \n" +
                " left join  Nursing_items_med_instr_filter      fil on fil.ID = n.Filter \n" +
                " left join  Nursing_items_med_instr_agogi       ag  on ag.ID = n.agogi \n" +
                " left join  Med_instr_antipiktiki_dosh       dos  on dos.ID = n.agogiDosisID \n" +
                (Customers.isFrontis(custID)  ? " " +
                        "left join  Nursing_items_med_instr_agogi    ag120  on ag120.ID = n.agogi120 \n" +
                        "left join  Nursing_items_med_Fe_types    fe  on fe.ID = n.fe_ID \n " +
                        "left join  Nursing_items_med_L_carnitine    lcar  on lcar.ID = n.Carnitine_ID \n " +
                        "left join  Nursing_items_ogkos_ipok    ipok  on ipok.ID = n.ipok_ogkos \n" +
                        "left join  Nursing_items_med_Vit_B    vit  on vit.ID = n.vit_B_ID \n" : "" ) +
                " left join  Nursing_items_med_Vit_D    vitD  on vitD.ID = n.vit_D_ID \n" +
                " left join  Nursing_items_med_instr_dialima      d  on d.ID =  n.dialima \n" +
                " where n.patientID = " + patientid +
                " order by n.year , n.month desc , n.id desc";
    }


    public static String getMEDICAL_INSTRACTIONS_NEW(String cols, String patientid){

        return  "select top 1 " + cols + " ,n.ID , \n" +
                "eid.name as eidosName, fil.name as filName,patientID, \n" +
                "ag.name as agogiName, dos.name as agogiDosisName, dur.name as durationName,\n" +
                "\n" +
                " isnull(dbo.nameitem(n.epoetinMedsID),'')   + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.eopetinSinedries, 0) as varchar)\t + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(epodatein),' ') +   CHAR(10)  + 'ΕΩΣ: ' + isnull(dbo.datetostr(epodateout),' ') \t\t    + CHAR(10)  + 'Δόση: ' + isnull(cast(epoetinDose as varchar),'') + ' ' + isnull(epoetinMM,'') +  CHAR(10) + 'Συχνότητα: ' +   cast(isnull(epoetinFRQ, 0)as varchar ) + ' ' + isnull(fr1.name,'')  as epoName , \n" +
                " isnull(dbo.nameitem(n.vitB_MedsID),'')  + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.vitB_Sinedries, 0) as varchar)  + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(vitB_DateIn) ,' ')+   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(vitB_Dateout),' ')  + CHAR(10)  + 'Δόση: ' + isnull(cast(vit_BDose as varchar),'') + ' ' + isnull(vit_B_MM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(vit_B_FRQ, 0)as varchar )  + ' ' + isnull(fr2.name,'') as  vitB_Name, \n" +
                " isnull(dbo.nameitem(n.CarnitineMedsID),'')  + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.carnitSinedries, 0) as varchar)  + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(carnDatein),' ') +   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(carnDateout),' ')  + CHAR(10)  + 'Δόση: ' + isnull(cast(carnitineDose as varchar),'') + ' ' + isnull(CarnitineMM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(CarnitineFRQ, 0)as varchar ) + ' ' + isnull(fr3.name,'')  as  carnitine_Name,\n" +
                " isnull(dbo.nameitem(n.vitD_MedsID),'')  + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.vitD_Sinedries, 0) as varchar)  + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(vitD_DateIn) ,' ')+   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(vitB_Dateout),' ')  + CHAR(10)  + 'Δόση: ' + isnull(cast(vit_DDose as varchar),'') + ' ' + isnull(vit_D_MM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(vit_D_FRQ, 0)as varchar ) + ' ' + isnull(fr4.name,'')  as  vitD_Name,\n" +
                " isnull(dbo.nameitem(n.FeMedsID),'')  + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.feSinedries, 0) as varchar)   + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(fedatein),' ')+   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(fedateout)  + CHAR(10),' ')  + 'Δόση: ' + isnull(cast(FeDose as varchar),'') + ' ' + isnull(FeMM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(FeFRQ, 0)as varchar )  + ' ' + isnull(fr5.name,'') as  fe_Name,\n" +
                " isnull(dbo.nameitem(n.etelalcetideMedsID),'')   + CHAR(10) +  'Συνεδρίες: ' + cast(isnull(n.etelSinedries, 0) as varchar)     + CHAR(10) + 'ΑΠΟ: ' +  isnull(dbo.datetostr(etelDateIn),' ') +   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(etelDateout),' ')  + CHAR(10)    + 'Δόση: ' + isnull(cast(etelalcetideDose as varchar),'') + ' ' + isnull(etelalcetideMM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(etelalcetideFRQ, 0)as varchar )  + ' ' + isnull(fr6.name,'') as  etel_Name,\n" +
                " d.name as dialeimaName,\n" +
                " dbo.nameitems(allergiesMeds) as allergiesMeds ,\n" +
                " dbo.nameitems(farmAgogiMeds)  as farmAgogiMeds ,\n" +

                " dbo.datetostr(embolio_b) as embolio_b,\n" +
                " dbo.datetostr(embolio_antiFlu) as embolio_antiFlu,\n" +
                " dbo.NAMEDOCTOR(n.doctorid) as doctorName, y.name as yearName \n" +

                " from Nursing_Medical_Instructions n \n" +
                " inner join years y on y.id = n.year \n" +
                " left join  Nursing_items_med_instr_epoetin     ep    on ep.ID =  n.epoetinID \n" +
                " left join  Nursing_items_med_instr_eidos_aim    eid  on eid.ID = n.eidos_aim \n" +
                " left join  Nursing_items_med_instr_filter      fil on fil.ID = n.Filter \n" +
                " left join  Nursing_items_med_instr_agogi       ag  on ag.ID = n.agogi \n" +
                " left join  Nursing_duration_aim                dur  on dur.ID = n.duration_aim_ID \n" +
                " left join  Med_instr_antipiktiki_dosh       dos  on dos.ID = n.agogiDosisID \n" +
                " left join  Nursing_hemo_meds_periods fr1 on fr1.id = n.epoet_period\n" +
                " left join  Nursing_hemo_meds_periods fr2 on fr2.id = n.vitB_period \n" +
                " left join  Nursing_hemo_meds_periods fr3 on fr3.id = n.carnitine_period \n" +
                " left join  Nursing_hemo_meds_periods fr4 on fr4.id =  n.vitD_period \n" +
                " left join  Nursing_hemo_meds_periods fr5 on fr5.id =  n.fe_period \n" +
                " left join  Nursing_hemo_meds_periods fr6 on fr6.id =  n.etel_period\n" +

                " left join  Nursing_items_med_Vit_D    vitD  on vitD.ID = n.vit_D_ID \n" +
                " left join  Nursing_items_med_instr_dialima      d  on d.ID =  n.dialima \n" +
                " where n.patientID = " + patientid +
                " order by n.year desc, n.month desc , n.id desc";
    }

    public static  String getISOZIGIO_PRO_APOV_SIGKENTROTIKA_ANA_ORA(String transgroupID,String date){

        return  "select * from v_Nursing_isozigio_proslamvanomenon_apovallomenon " +
                "where transgroupID = " + transgroupID +
                " and date = dbo.timeToNum(CONVERT(datetime, ' " + date + "' , 103))  ";
    }

    public static  String getMethNosileutikosElegxos(String transgroupID,String date, String vardiaID){

        return  "select * ,dbo.nameuser(userID) as username from v_Nursing_meth_nosil_elegxos " +
                "where transgroupID = " + transgroupID  +
                " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + date + "' , 103)) " +
                " and vardiaID = " + vardiaID;
    }


    public static  String getNosileutikiLogodosiaMeth(String transgroupID,String date, String vardiaID){

        return  "select * ,dbo.nameuser(userID) as username  from Nursing_nosil_logodosia_Meth " +
                "where transgroupID = " + transgroupID  +
                " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + date + "' , 103)) " +
                " and vardiaID = " + vardiaID;
    }

    public static  String getZotikaMeth(String transgroupID,String date ){

        return  "select  * , dbo.NAMEUSER(userid) as username " +
                "from Nursing_Zotika_Simeia_Meth " +
                "where transgroupID = " + transgroupID +
                " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + date + "' , 103)) " +
                " order by  date desc" ;

    }


    public static  String getKathetiresValues_Meth(String transgroupID,String date ) {

        return "select *, dbo.dateToStr(datestart) as dateinStr ,dbo.dateToStr(datestop) as dateoutStr ,topos " +
                " from  Nursing_Kathethres_Topos where transgroupID = " + transgroupID +
                " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + date + "' , 103)) ";

    }


    public static String getTotalIsozigioMeth(String transgroupID,String date){

        return " select  sum(total_pros) as total_pros, " +
                " sum(total_apov) as total_apov, " +
                " sum(total_isozigio) as total_isozigio," +
                " (select count(*) from  Nursing_xorigisi_farmakon_isozigio_meth where transgroupID = " + transgroupID + "" +
                " and  dbo.datetostr(date) = dbo.datetostr(dbo.timeToNum(CONVERT(datetime, '" + date + "' , 103))))  as total_medicines " +
                " from v_Nursing_Isozigio_Meth where transgroupID = "  + transgroupID +
                " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + date + "' , 103)) ";
    }


    public static String getTotalIsozigio_Sigkentrotika_Meth(String transgroupID,String date){

        return "select * , dbo.NAMEUSER(userid) as username from v_Nursing_Isozigio_Meth " +
                " where transgroupid =  "  + transgroupID +
                " and dbo.DateTime2Date(date) =  dbo.timeToNum(CONVERT(datetime,  '" + date + "' , 103)) ";
    }


    public static String getIatrikes_Odigies_Medicines_Meth(String transgroupID,String date){

        return "select " +
                "x.ID , " +
                "x.UserID, " +
                "dbo.DateTimeToString(x.datetime) as datetimeStr, " +
                "dbo.NAMEITEM(io.itemid) as nameItem, " +
                "x.ml_hour, " +
                "x.dosi, " +
                "x.dosologia, " +
                "dbo.timeToStr(x.datetime) as timetoStr, " +
                "x.xorigithike, " +
                "dbo.NAMEDOCTOR(x.DoctorID) as doctorName " +

                "from Nursing_ores_xorigisis_meth x  " +
                "left join Nursing_xorigisi_farmakon_meth io on x.xorigisiID = io.ID " +
                " where  " +
                " io.transgroupid =  " + transgroupID +
                " and io.diakopike is null " +
                " and dbo.timeToNum( (CONVERT(datetime, dbo.datetostr(x.datetime) , 103))) = " +
                " dbo.timeToNum(CONVERT(datetime, '" + date + "' , 103)) " +
                " order by ml_hour";
    }




    public static String getPoreia_alles_eksetaseis_Meth(String transgroupID,String date){

        return  "select * , dbo.dateToStr(date) as dateStr ,  " +
                "dbo.NAMEUSER(UserID) as doctor " +

                " from Nursing_poreia_alles_eksetaseis_meth" +
                " where transgroupid = " + transgroupID +
                " and date = dbo.timeToNum(CONVERT(datetime, '" + date + "' , 103)) ";
    }



    public static String getEnimerotiko_Simeioma_sigkentrotika(String transgroupID){

        return "select * ,dbo.DateTimeToString(date) as datetimeStr" +
                " from Nursing_enimerotiko_simeioma_eksetasis where TransGroupid = "  + transgroupID +
                " where diakopike is null or " +
                " dbo.datetostr(datestop) =  dbo.datetostr(dbo.timeToNum(CONVERT(datetime,GETDATE() , 103))) " +
                " order by date desc";
    }


    public static String getSigkentrotika_Medical_instructions(String patientID, int custID){

        return "select  y.Name as yearName , \n" +
                "m.Name as monthName ,\n" +
                " n.DoctorID as  UserID,\n" +
                " dur.name as  durationName,\n" +
                " dbo.NAMEDOCTOR(Doctorid) as doctorName, \n" +
                " dbo.datetostr(embolio_b)  as emvolio_ip_b,\n" +
                " dbo.datetostr(embolio_antiFlu) as emvolio_antigrip, \n" +
                " dbo.datetostr(embolio_pneum) as emvolio_pneum_str, \n" +
                " dbo.datetostr(embolio_covid) as embolio_covid1_str, \n" +

                ( !Customers.isFrontis(custID) ?
                    " isnull(dbo.nameitem(n.epoetinMedsID),'')   + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.eopetinSinedries, 0) as varchar)\t + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(epodatein),' ') +   CHAR(10)  + 'ΕΩΣ: ' + isnull(dbo.datetostr(epodateout),' ') \t\t    + CHAR(10)  + 'Δόση: ' + isnull(cast(epoetinDose as varchar),'') + ' ' + isnull(epoetinMM,'') +  CHAR(10) + 'Συχνότητα: ' +   cast(isnull(epoetinFRQ, 0)as varchar ) + ' ' + isnull(fr1.name,'')  as epoName , \n" +
                    " isnull(dbo.nameitem(n.vitB_MedsID),'')  + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.vitB_Sinedries, 0) as varchar)  + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(vitB_DateIn) ,' ')+   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(vitB_Dateout),' ')  + CHAR(10)  + 'Δόση: ' + isnull(cast(vit_BDose as varchar),'') + ' ' + isnull(vit_B_MM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(vit_B_FRQ, 0)as varchar )  + ' ' + isnull(fr2.name,'') as  vitB_Name, \n" +
                    " isnull(dbo.nameitem(n.CarnitineMedsID),'')  + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.carnitSinedries, 0) as varchar)  + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(carnDatein),' ') +   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(carnDateout),' ')  + CHAR(10)  + 'Δόση: ' + isnull(cast(carnitineDose as varchar),'') + ' ' + isnull(CarnitineMM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(CarnitineFRQ, 0)as varchar ) + ' ' + isnull(fr3.name,'')  as  carnitine_Name,\n" +
                    " isnull(dbo.nameitem(n.vitD_MedsID),'')  + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.vitD_Sinedries, 0) as varchar)  + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(vitD_DateIn) ,' ')+   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(vitB_Dateout),' ')  + CHAR(10)  + 'Δόση: ' + isnull(cast(vit_DDose as varchar),'') + ' ' + isnull(vit_D_MM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(vit_D_FRQ, 0)as varchar ) + ' ' + isnull(fr4.name,'')  as  vitD_Name,\n" +
                    " isnull(dbo.nameitem(n.FeMedsID),'')  + CHAR(10)  + 'Συνεδρίες: ' + cast(isnull(n.feSinedries, 0) as varchar)   + CHAR(10)  + 'ΑΠΟ: ' +  isnull(dbo.datetostr(fedatein),' ')+   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(fedateout)  + CHAR(10),' ')  + 'Δόση: ' + isnull(cast(FeDose as varchar),'') + ' ' + isnull(FeMM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(FeFRQ, 0)as varchar )  + ' ' + isnull(fr5.name,'') as  fe_Name,\n" +
                    " isnull(dbo.nameitem(n.etelalcetideMedsID),'')   + CHAR(10) +  'Συνεδρίες: ' + cast(isnull(n.etelSinedries, 0) as varchar)     + CHAR(10) + 'ΑΠΟ: ' +  isnull(dbo.datetostr(etelDateIn),' ') +   CHAR(10)  + 'ΕΩΣ: ' +  isnull(dbo.datetostr(etelDateout),' ')  + CHAR(10)    + 'Δόση: ' + isnull(cast(etelalcetideDose as varchar),'') + ' ' + isnull(etelalcetideMM,'') + CHAR(10) + 'Συχνότητα: ' +   cast(isnull(etelalcetideFRQ, 0)as varchar )  + ' ' + isnull(fr6.name,'') as  etel_Name,\n"
                : "") +


                (Customers.isFrontis(custID) ? "dbo.datetostr(embolio_covid2) as embolio_covid2_str, \n" : "") +

                " dbo.timetostr(Duration) as durationStr,  \n" +
                " eidos.Name as eidos,\n" +
                " fil.name as fil ,\n" +
                " ag.Name as agogName,\n" +
                " dial.Name as dial,\n" +
                " dos.Name as agogiDosiName,\n" +
                " n.*  " +
                "from Nursing_Medical_Instructions  n \n" +
                "left join years y on y.ID = n.Year \n" +
                "left join v_Month m on m.ID = n.month\n " +
                "left join Nursing_duration_aim dur on dur.ID = n.duration_aim_ID\n " +

                " left join Nursing_items_med_instr_eidos_aim eidos on eidos.ID = n.eidos_aim \n" +
                " left join Nursing_items_med_instr_filter fil on fil.ID = n.Filter \n" +
                " left join Nursing_items_med_instr_agogi ag on ag.ID = n.agogi \n" +
                " left join Nursing_items_med_instr_dialima dial on dial.ID = n.dialima \n" +
                " left join  Med_instr_antipiktiki_dosh       dos  on dos.ID = n.agogiDosisID \n" +
                " left join  Nursing_hemo_meds_periods fr1 on fr1.id = n.epoet_period\n" +
                " left join  Nursing_hemo_meds_periods fr2 on fr2.id = n.vitB_period \n" +
                " left join  Nursing_hemo_meds_periods fr3 on fr3.id = n.carnitine_period \n" +
                " left join  Nursing_hemo_meds_periods fr4 on fr4.id =  n.vitD_period \n" +
                " left join  Nursing_hemo_meds_periods fr5 on fr5.id =  n.fe_period \n" +
                " left join  Nursing_hemo_meds_periods fr6 on fr6.id =  n.etel_period\n" +
                " where patientid =  " + patientID +
                " order by n.year desc , n.Month desc , n.id desc ";
    }


    public static String get_karta_xorigisis_farmakon_items(String transgroupID){

       return  "select id, dbo.nameitem(itemid) as item " +
            " from Nursing_xorigisi_farmakon " +
            " where transgroupID = " + transgroupID +
            " and diakopike  is null or " +
            " dbo.datetostr(datestop) =  dbo.datetostr(dbo.timeToNum(CONVERT(datetime,GETDATE() , 103))) " +
            " order by id ";
    }

    public static String get_karta_xorigisis_farmakon_items_general(String transgroupID,String tableID){

        return  "select id, dbo.nameitem(itemid) as item " +
                " from Nursing_xorigisi_farmakon_general " +
                " where transgroupID = " + transgroupID +
                " and tableID = " + tableID +
                " and diakopike  is null or " +
                " dbo.datetostr(datestop) =  dbo.datetostr(dbo.timeToNum(CONVERT(datetime,GETDATE() , 103))) " +
                " order by id ";
    }

    public static String getSigkentrotika_karta_xorigisis_farmakon(String transgroupID){
        return "select * , dbo.NAMEUSER(userid) as username from Nursing_xorigisi_farmakon where transgroupID = " + transgroupID;
    }

    public static String getSigkentrotika_karta_xorigisis_farmakon_general(String transgroupID, String tableID){
        return "select * , dbo.NAMEUSER(userid) as username " +
                "from Nursing_xorigisi_farmakon_general where transgroupID = " + transgroupID +
                " and tableID = " + tableID;
    }

    public static String getSigkentrotika_karta_xorigisis_farmakon_hours(String transgroupID, String medicineIDs){

        // ΚΟΙΤΑΖΕΙ ΑΝ ΥΠΑΡΧΟΥΝ ΕΓΓΡΑΦΕΣ ΜΕ ΤΙς ΣΗΜΕΡΙΝΕΣ ΩΡΕΣ ΓΙΑ ΤΑ ΦΑΡΜΑΚΑ ΠΟΥ ΔΕΝ ΕΧΟΥΝ ΔΙΑΚΟΠΕΙ
        // ΑΝ ΔΕΝ ΥΠΑΡΧΟΥΝ ΚΑΝΕΙ ΑΝΤΙΓΡΑΦΗ ΤΙΣ ΩΡΕς ΤΩΝ ΦΑΡΜΑΚΩΝ ΠΟΥ ΔΕΝ ΕΧΟΥΝ ΔΙΑΚΟΠΕΙ ΓΙΑ ΤΗ ΣΗΜΕΙΡΝΗ ΜΕΡΑ ΩΣΤΕ
        // ΝΑ ΜΠΟΡΕΙ ΝΑ ΔΗΛΩΣΕΙ Ο ΝΟΣΗΛΕΥΤΗΣ ΑΝ ΕΧΕΙ ΧΟΡΗΓΗΘΕΙ ΤΟ ΦΑΡΜΑΚΟ ΓΙΑ ΚΑΘΕ ΩΡΑ


        return "if  not  exists( " +
                "select top 1 x.id" +
                " from Nursing_ores_xorigisis x " +
                " left join Nursing_xorigisi_farmakon n on n.ID = x.xorigisiID " +
                " where dbo.datetostr(x.date) = dbo.datetostr(dbo.timeToNum(CONVERT(datetime, GETDATE() , 103))) " +
                " and n.transgroupID = " + transgroupID +
                ") " +
                "INSERT INTO Nursing_ores_xorigisis (date,xorigisiid,hour)  " +
                " SELECT (dbo.timeToNum(CONVERT(datetime, GETDATE() , 103))) , xorigisiid,hour " +
                " from Nursing_ores_xorigisis n     " +
                " left join Nursing_xorigisi_farmakon f on f.ID = n.xorigisiID     " +
                " where f.diakopike is null  " +
                " and transgroupid =   " + transgroupID +

                " select x.ID, dbo.timetostr(x.hour) as hourstr, " +
                " x.UserID, dbo.NAMEUSER(x.userid) as username ," +
                " x.xorigithike, " +
                " dbo.nameitem(n.itemid) as item" +
                " from Nursing_ores_xorigisis x " +
                " left join Nursing_xorigisi_farmakon n on n.ID = x.xorigisiID " +
                " where n.TransGroupID = "  +  transgroupID +
                " and x.xorigisiID in (" + medicineIDs + ") " +
                " and dbo.datetostr(x.date) =  dbo.datetostr(dbo.timeToNum(CONVERT(datetime,GETDATE() , 103))) " +
                " order by x.xorigisiID , hour " ;


    }


    public static String getSigkentrotika_karta_xorigisis_farmakon_hours_general(String transgroupID, String medicineIDs){

        // ΚΟΙΤΑΖΕΙ ΑΝ ΥΠΑΡΧΟΥΝ ΕΓΓΡΑΦΕΣ ΜΕ ΤΙς ΣΗΜΕΡΙΝΕΣ ΩΡΕΣ ΓΙΑ ΤΑ ΦΑΡΜΑΚΑ ΠΟΥ ΔΕΝ ΕΧΟΥΝ ΔΙΑΚΟΠΕΙ
        // ΑΝ ΔΕΝ ΥΠΑΡΧΟΥΝ ΚΑΝΕΙ ΑΝΤΙΓΡΑΦΗ ΤΙΣ ΩΡΕς ΤΩΝ ΦΑΡΜΑΚΩΝ ΠΟΥ ΔΕΝ ΕΧΟΥΝ ΔΙΑΚΟΠΕΙ ΓΙΑ ΤΗ ΣΗΜΕΙΡΝΗ ΜΕΡΑ ΩΣΤΕ
        // ΝΑ ΜΠΟΡΕΙ ΝΑ ΔΗΛΩΣΕΙ Ο ΝΟΣΗΛΕΥΤΗΣ ΑΝ ΕΧΕΙ ΧΟΡΗΓΗΘΕΙ ΤΟ ΦΑΡΜΑΚΟ ΓΙΑ ΚΑΘΕ ΩΡΑ


        return "if  not  exists( " +
                "select top 1 x.id" +
                " from Nursing_ores_xorigisis_general x " +
                " left join Nursing_xorigisi_farmakon_general n on n.ID = x.xorigisiID " +
                " where dbo.datetostr(x.date) = dbo.datetostr(dbo.timeToNum(CONVERT(datetime, GETDATE() , 103))) " +
                " and n.transgroupID = " + transgroupID +
                ") " +
                "INSERT INTO Nursing_ores_xorigisis_general (date,xorigisiid,hour)  " +
                " SELECT (dbo.timeToNum(CONVERT(datetime, GETDATE() , 103))) , xorigisiid,hour " +
                " from Nursing_ores_xorigisis_general n     " +
                " left join Nursing_xorigisi_farmakon_general f on f.ID = n.xorigisiID     " +
                " where f.diakopike is null  " +
                " and transgroupid =   " + transgroupID +

                " select x.ID, dbo.timetostr(x.hour) as hourstr, " +
                " x.UserID, dbo.NAMEUSER(x.userid) as username ," +
                " x.xorigithike, " +
                " dbo.nameitem(n.itemid) as item" +
                " from Nursing_ores_xorigisis_general x " +
                " left join Nursing_xorigisi_farmakon_general n on n.ID = x.xorigisiID " +
                " where n.TransGroupID = "  +  transgroupID +
                " and x.xorigisiID in (" + medicineIDs + ") " +
                " and dbo.datetostr(x.date) =  dbo.datetostr(dbo.timeToNum(CONVERT(datetime,GETDATE() , 103))) " +
                " order by x.xorigisiID , hour " ;


    }


    public static String getProgrammatismos_p_a(String date , String floorID , String companyID){
        return "DECLARE  @CUR_DATE BIGINT\n" +
                "SET @CUR_DATE = DBO.TIMETONUM(GETDATE()) " +
                " SELECT  \n" +
                " b.id as bedID,\n " +
                " b.code as bed,  \n  " +
                " fl.Name as floor,\n " +
                " r.id as roomID, \n" +
                " proi.name_p,\n " +
                " now.Name_a, \n" +
                " pr.* \n" +
                "    from bed b  \n" +
                "    LEFT JOIN Room r ON r.id=b.roomid  \n" +
                "    left join Floor fl on fl.id = r.FloorID \n " +
                "    left join Nursing_programmatismos_p_a pr on pr.BedID = b.ID \n" +
                "left join ( " +
                "select p.name as name_p , tg.BedID, tg.DateOut \n" +
                "from person p \n" +
                "left join transgroup tg on tg.PatientID = p.ID  \n" +
                "where  ( tg.DateOut > @CUR_DATE\n" +
                "and tg.DateOut <= @CUR_DATE) \n" +
                "OR (tg.DateOut is null and tg.DateIn <= dbo.timeToNum(CONVERT(datetime,'" + date +"', 103))) \n" +
                "and tg.BedID is not null )  proi on proi.BedID = b.id   left join ( select p.name as name_a, tg.BedID, tg.DateOut from person p\n" +
                "left join transgroup tg on tg.PatientID = p.ID \n" +
                " where  tg.dateout is null and tg.BedID is not null)  now on now.BedID = b.id  " +
                " where  fl.id  = " + floorID  + "\n" +
                " and b.companyID = " + companyID +
                " ORDER BY  fl.Name, b.code  ";
    }

        public static String getProgrammatismos_p_a_checklist(){
            return "select * , dbo.datetostr(date) as datestr, dbo.nameuser(userID) as username " +
                    " from Nursing_programmatismos_p_a_check_list " +
                    " where dbo.datetostr(date)  =  " +
                    " dbo.datetostr(dbo.timeToNum(CONVERT(datetime, '" + Utils.getCurrentDate() + "' ,103))) ";
        }

        public static String getSigkentrotika_meds_meth_types (String transgroupID, String type_medID){
            return "" +
                    "" +
                    "DECLARE @CUR_DATESTOP VARCHAR(32) \n" +
                    "SET @CUR_DATESTOP =  dbo.datetostr(dbo.timeToNum(CONVERT(datetime, GETDATE() , 103)))  \n" +
                    "select *,  \n" +
                    "dbo.nameuser(userID) as username, \n" +
                    " dbo.DateTimeToString(datestart) as datestr \n" +
                    " from Nursing_xorigisi_farmakon_isozigio_meth \n" +
                    " where transgroupID = " + transgroupID +
                    " and  datestart is not null \n" +
                    " and (datestop is null or  dbo.datetostr(datestop) = @CUR_DATESTOP) \n" +
                    " and  type_medID = " + type_medID;
        }

        public static String getSigkentrotika_metagiseis_meth (String transgroupID) {

            return "select *,dbo.datetostr(date) as dateStr, \n" +
                " dbo.nameuser(UserID_start)as UserID_start_name, \n" +
                " dbo.nameuser(UserID_finish) as UserID_finish_name, \n" +
                " dbo.namedoctor(doctorID) as DoctorName \n" +
                " from Nursing_metaggiseis \n" +
                " where transgroupID = " + transgroupID;
        }

        public static String getKatagrafiMetron_loimoksewn(String transgroupID) {

           return "select *, dbo.nameuser(userID) as username " +
                   " from Nursing_katagrafi_efarmogis_metron_elegxou_loimokseon_orofon_meth where transgroupid = " + transgroupID;

        }

         public static String getNotification_Messages(String companyID) {

            return "IF EXISTS (select top 1 id from Notification_messages where confirmation is null and companyID = 1 ) " +
                    "AND EXISTS (select top 1 id from Nursing_iatrikes_entoles where confirmation is null and companyID = " + companyID +") " +
             " SELECT 1 AS ID " +
                     "ELSE IF EXISTS (select top 1 id from Notification_messages where confirmation is null and companyID = " + companyID +" )  " +
                     " SELECT 2 AS ID " +
                     "ELSE IF EXISTS (select top 1 id from Nursing_iatrikes_entoles where confirmation is null and companyID = " + companyID +") " +
                     " SELECT 3 AS ID";

        }


        public static String insertIntoMedicalRecordsFile(String companyID,String patientID,String transGroupID,String doctorID,
                                  String fileData, String fileExt)
        {
            StringJoiner s = new StringJoiner(",");
            s.add(companyID).add("dbo.localtime()").add(patientID).add(transGroupID).add(doctorID).add("'" + fileExt + "'");
            return "DECLARE @inserted_id bigint \n" +
                    "exec dbo.disable_triggers\n" +
                    "INSERT INTO MedicalRecords(CompanyID,date,PatientID,TransGroupID,DoctorID,fileExt,fileData)\n" +
                    "values( "  + s.toString()
                    +" , convert(varbinary(max),0x" + fileData  + ") ) \n" +
                    "set @inserted_id = IDENT_CURRENT( 'MedicalRecords' )  \n" +
                    "UPDATE  Nursing_metaggiseis set medicalRecordID = @inserted_id \n" +
                    "exec dbo.enable_triggers";
        }


    public static String getImer_kat_diat(String transgroupID)
    {
        return "select * from Nursing_imerologio_katagrafis_diatrofis where transgroupID = " + transgroupID;

    }


}
