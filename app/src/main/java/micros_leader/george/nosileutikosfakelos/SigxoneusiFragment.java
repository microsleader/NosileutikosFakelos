package micros_leader.george.nosileutikosfakelos;

import static micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.ZOTIKA_METH;
import static micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.KATHIMERINO_ZIGISMA;
import static micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.ISOZIGIO_METH;
import static micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.NEURIKI_AKSIOLOGISI;
import static micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS.KATHETIRES;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Isozigio_Meth.Isozigio_Meth_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.Kathetires_Activity;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Kathetires_kalliergies.Kathetires_Activity_NEW;
import micros_leader.george.nosileutikosfakelos.METH.METH_MAP.f_Zotika_simeia.Zotika_Activity_Meth;
import micros_leader.george.nosileutikosfakelos.Main_menu.SigxoneusiFiladiwnActivity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Aksiologiseis.NeurikiAksiologisi3Activity;
import micros_leader.george.nosileutikosfakelos.OROFOI.f_Kathimerino_zigisma.Kathimerino_Zigisma_Activity;


public class SigxoneusiFragment extends Fragment {

    View view;
    SigxoneusiFiladiwnActivity parentAct;
    Bundle bundle;
    SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS filladio;

    public SigxoneusiFragment() {

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bundle = getArguments();
        if (bundle != null)
            filladio = (SigxoneusiFiladiwnActivity.FILLADIO_SIGXONEUSIS) bundle.get(SigxoneusiFiladiwnActivity.FILLADIO_KEY);


        if (filladio == ZOTIKA_METH)
            view =  inflater.inflate(R.layout.activity_zotika___meth, container, false);
        else if (filladio == KATHIMERINO_ZIGISMA)
            view =  inflater.inflate(R.layout.activity_kathimerino__zigisma_, container, false);
        else if (filladio == ISOZIGIO_METH)
            view =  inflater.inflate(R.layout.activity_isozigio__meth_, container, false);
        else if (filladio == NEURIKI_AKSIOLOGISI)
            view =  inflater.inflate(R.layout.activity_neuriki_aksiologisi3, container, false);
        else if (filladio == KATHETIRES)
            view =  inflater.inflate(R.layout.activity_kathetires_meth, container, false);


         parentAct = (SigxoneusiFiladiwnActivity) getActivity();
         return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (filladio == ZOTIKA_METH)
            runZotika_meth();
        else if (filladio == KATHIMERINO_ZIGISMA)
            run_kathimerino_zigisma();
        else if (filladio == ISOZIGIO_METH)
            run_isozigio_meth();
        else if (filladio == NEURIKI_AKSIOLOGISI)
            run_neuriki_aksiologisi();
        else if (filladio == KATHETIRES)
            run_kathetires_paroxeteuseis();


    }


    private void runZotika_meth(){

        Zotika_Activity_Meth  act = new Zotika_Activity_Meth();
        act.floorSP = view.findViewById(R.id.floorsSP);
        act.patientsTV = view.findViewById(R.id.patientsTV);
        act.timeTV = view.findViewById(R.id.hoursTV);
        act.hoursSP = view.findViewById(R.id.hoursSP);

        act.fabMenu = view.findViewById(R.id.fabMenu);
        act.recyclerView = view.findViewById(R.id.zotikaMethRV);

        act.dateTV = view.findViewById(R.id.dateTV);
        act.toolbar = view.findViewById(R.id.toolbar);
        act.updateButton = view.findViewById(R.id.updateButton);
        act.diagramBT = view.findViewById(R.id.diagramBT);


        parentAct.setAct(parentAct);
        parentAct.setActFromSigxoneusi(act);

        act.setAct(parentAct);
        act.setActFromSigxoneusi(act);
        act.neaEggrafiBT = view.findViewById(R.id.neaEggrafiBT);
        act.initialize();
    }



    private void run_kathimerino_zigisma() {

        Kathimerino_Zigisma_Activity act = new Kathimerino_Zigisma_Activity();
        act.table = "Nursing_Kathimerino_Zigisma";

        act.floorSP = view.findViewById(R.id.floorsSP);
        act.patientsTV = view.findViewById(R.id.patientsTV);
        act.timeTV = view.findViewById(R.id.timeTV);
        act.fab = view.findViewById(R.id.fab);
        act.recyclerView = view.findViewById(R.id.zotikaMethRV);

        act.dateTV = view.findViewById(R.id.dateTV);
        act.toolbar = view.findViewById(R.id.toolbar);

        parentAct.setAct(parentAct);
        parentAct.setActFromSigxoneusi(act);

        act.anaf_varosET = view.findViewById(R.id.anafVarosET);
        act.varosET = view.findViewById(R.id.varosET);
        act.proVarosET = view.findViewById(R.id.proVarosET);
        act.ipsosET = view.findViewById(R.id.ipsosET);
        act.bmiET = view.findViewById(R.id.bmiET);
        act.textET = view.findViewById(R.id.textET);
        act.fab = view.findViewById(R.id.fab);

        act.setAct(parentAct);
        act.setActFromSigxoneusi(act);
        act.initialize();
    }


    private void run_isozigio_meth() {
        Isozigio_Meth_Activity  act = new Isozigio_Meth_Activity();

        act.floorSP = view.findViewById(R.id.floorsSP);
        act.patientsTV = view.findViewById(R.id.patientsTV);
        act.fabMenu = view.findViewById(R.id.fabMenu);
        act.recyclerView = view.findViewById(R.id.isozigioRV);
        act.neaEggrafiBT = view.findViewById(R.id.neaEggrafiBT);
        act.dateTV = view.findViewById(R.id.dateTV);
        act.timeTV = view.findViewById(R.id.timeTV);
        act.thereIsDatePicker(R.id.dateTV);
        act.thereIsTimePicker(R.id.timeTV);
        act.total_pros = view.findViewById(R.id.total_pros);
        act.total_apov = view.findViewById(R.id.total_apov);
        act.total_isozigio = view.findViewById(R.id.total_isozigio);


        act.dateTV = view.findViewById(R.id.dateTV);
        act.toolbar = view.findViewById(R.id.toolbar);

        parentAct.setAct(parentAct);
        parentAct.setActFromSigxoneusi(act);
        act.fab = view.findViewById(R.id.fab);
        act.setAct(parentAct);
        act.setActFromSigxoneusi(act);
        act.initialize();

    }




    private void run_neuriki_aksiologisi() {
        NeurikiAksiologisi3Activity  act = new NeurikiAksiologisi3Activity();

        act.floorSP = view.findViewById(R.id.floorsSP);
        act.patientsTV = view.findViewById(R.id.patientsTV);
        act.timeTV = view.findViewById(R.id.timeTV);

        act.buttonEnimerosi = view.findViewById(R.id.updateButton);
        act.editTextList = new ArrayList<>();


        act.infoimage1 = view.findViewById(R.id.infoIV1);
        act.infoimage2 = view.findViewById(R.id.infoIV2);
        act.infoimage3 = view.findViewById(R.id.infoIV3);
        act.infoimage4 = view.findViewById(R.id.infoIV4);
        act.infoimage5 = view.findViewById(R.id.egrigorsiIV);
        act.infoimage6 = view.findViewById(R.id.monthAgeIV);
        act.infoimage8 = view.findViewById(R.id.eyesHandsIV);
        act.infoimage9 = view.findViewById(R.id.ofthalmokinIV);
        act.infoimage10 = view. findViewById(R.id.imianopsiaIV);
        act.infoimage11 = view. findViewById(R.id.paresiFaceIV);
        act.infoimage12 = view. findViewById(R.id.ptwsiAnwAkrouIV);
        act.infoimage13 = view. findViewById(R.id.ptwsiKatwAkrouIV);
        act.infoimage14 = view. findViewById(R.id.ataxiaIV);
        act.infoimage15 = view. findViewById(R.id.astheticIV);
        act.infoimage16 = view. findViewById(R.id.afasiaNihssIV);
        act.infoimage17 = view. findViewById(R.id.disarthriaIV);
        act.infoimage18 = view. findViewById(R.id.neglectIV);
        act.wraProseleusisNewTV = view.findViewById(R.id.wraProseleusisNewTV);
        act. wraProtokollouTV = view.findViewById(R.id.wraProtokollouTV);
        act.wraThromvolisisTV = view.findViewById(R.id.wraThromvolisisTV);

        act.egrigorsiET = view.findViewById(R.id.egrigorsiET);
        act.monthAgeET = view.findViewById(R.id.monthAgeET);
        act.eyesHandsET = view.findViewById(R.id.eyesHandsET);
        act.ofthalmokinET = view.findViewById(R.id.ofthalmokinET);
        act.imianopsiaET = view.findViewById(R.id.imianopsiaET);
        act.paresiFaceET = view.findViewById(R.id.paresiFaceET);
        act.ptwsiAnwAkrouET = view.findViewById(R.id.ptwsiAnwAkrouET);
        act.ptwsiKatwAkrouET = view.findViewById(R.id.ptwsiKatwAkrouET);
        act.ataxiaET = view.findViewById(R.id.ataxiaET);
        act.astheticET = view.findViewById(R.id.astheticET);
        act.afasiaNihssET = view.findViewById(R.id.afasiaNihssET);
        act.disarthriaET = view.findViewById(R.id.disarthriaET);
        act.neglectET = view.findViewById(R.id.neglectET);
        act. kinAnoAristerouAkrouET = view.findViewById(R.id.kinAnoAristerouAkrouET);
        act. kinKatoAristerouAkrouET = view.findViewById(R.id.kinKatoAristerouAkrouET);
        act.kinAnoDexiouAkrouET = view.findViewById(R.id.kinAnoDexiouAkrouET);
        act.kinKatoAkrouDexiouET = view.findViewById(R.id.kinKatoAkrouDexiouET);


        act.dateTV = view.findViewById(R.id.dateTV);
        act.toolbar = view.findViewById(R.id.toolbar);

        parentAct.setAct(parentAct);
        parentAct.setActFromSigxoneusi(act);

        act.hoursSP = view.findViewById(R.id.hoursSP);
        act.epipSinidisisET = view.findViewById(R.id.epipSinidisisET);
        act.afasiaET = view.findViewById(R.id.afasiaET);
        act.timeCH = view.findViewById(R.id.timeCH);
        act.buttonEnimerosi = view.findViewById(R.id.updateButton);
        act.synoloTV = view.findViewById(R.id.synoloTV);



        act.fab = view.findViewById(R.id.fab);

        act.setAct(parentAct);
        act.setActFromSigxoneusi(act);
        act.initialize();

    }


    private void run_kathetires_paroxeteuseis() {
        Kathetires_Activity_NEW act = new Kathetires_Activity_NEW();

        act.floorSP = view.findViewById(R.id.floorsSP);
        act.patientsTV = view.findViewById(R.id.patientsTV);
        act.timeTV = view.findViewById(R.id.timeTV);
        act.fabMenu = view.findViewById(R.id.fabMenu);
        act.recyclerView = view.findViewById(R.id.kathetiresRV);
        act.neaEggrafiBT = view.findViewById(R.id.newEntryBT);


        act.dateTV = view.findViewById(R.id.dateTV);
        act.toolbar = view.findViewById(R.id.toolbar);

        parentAct.setAct(parentAct);
        parentAct.setActFromSigxoneusi(act);
        act.fab = view.findViewById(R.id.fab);
        act.setAct(parentAct);
        act.setActFromSigxoneusi(act);
        act.initialize();
    }








}