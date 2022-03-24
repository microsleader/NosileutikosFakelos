package micros_leader.george.nosileutikosfakelos.ClassesForRV;

import java.io.Serializable;

public class PatientsOfTheDay implements Serializable {

    public String firstName;
    public String lastName;
    public String fatherName;
    public String  patCode;
    public String age;
    public String datebirth;
    public String height;
    public int transgroupID;
    public int patientID;
    public String datein;
    public int vardiaID;
    public int sex;
    public String isEmergency;
    public String code ,amka,bed;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getDatein() {
        return datein;
    }

    public void setDatein(String datein) {
        this.datein = datein;
    }

    public int getVardiaID() {
        return vardiaID;
    }

    public void setVardiaID(int vardiaID) {
        this.vardiaID = vardiaID;
    }

    public int getTransgroupID() {
        return transgroupID;
    }

    public void setTransgroupID(int transgroupID) {
        this.transgroupID = transgroupID;
    }


    public String getDatebirth() {
        return datebirth;
    }

    public void setDatebirth(String datebirth) {
        this.datebirth = datebirth;
    }

    public String getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(String isEmergency) {
        this.isEmergency = isEmergency;
    }
    public String getPatCode() {
        return patCode;
    }

    public void setPatCode(String patCode) {
        this.patCode = patCode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCode() {
        return code;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }
}
