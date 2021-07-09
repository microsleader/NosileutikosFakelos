package micros_leader.george.nosileutikosfakelos.Interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import micros_leader.george.nosileutikosfakelos.ClassesForRV.PatientsOfTheDay;

public interface IData {


    void taskCompleteGetFloors(HashMap <String , Integer> mapFloor);
    void taskCompleteMedicinesLista(ArrayList medicinesLista);
    void taskCompleteMedicinesHashMap(HashMap<String,Integer> medicinesHashMap);
    void taskCompletePlanoKlinonGetPatients(ArrayList<PatientsOfTheDay> lista);

    void taskCompleteGetPatients(ArrayList<PatientsOfTheDay> lista);
    void deleteResult(String str);


}
