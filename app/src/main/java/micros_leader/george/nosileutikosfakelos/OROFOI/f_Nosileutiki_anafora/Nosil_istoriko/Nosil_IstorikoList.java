package micros_leader.george.nosileutikosfakelos.OROFOI.f_Nosileutiki_anafora.Nosil_istoriko;


import micros_leader.george.nosileutikosfakelos.ClassesForRV.CheckBoxesForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.EditTextForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.SpinnersForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.TextViewSForRV;
import micros_leader.george.nosileutikosfakelos.ClassesForRV.TitlesForRV;

public class Nosil_IstorikoList {

    public TitlesForRV titlesForRV;
    public TextViewSForRV textViewSForRV;
    public CheckBoxesForRV checkBoxesForRV;
    public EditTextForRV editTextForRV;
    public SpinnersForRV spinnersForRV;


    public Nosil_IstorikoList(TitlesForRV titlesForRV) {
        this.titlesForRV = titlesForRV;
    }

    public Nosil_IstorikoList(TextViewSForRV textViewSForRV) {
        this.textViewSForRV = textViewSForRV;
    }



    public Nosil_IstorikoList(CheckBoxesForRV checkBoxesForRV) {
        this.checkBoxesForRV = checkBoxesForRV;
    }


    public Nosil_IstorikoList(EditTextForRV editTextForRV) {
        this.editTextForRV = editTextForRV;
    }


    public Nosil_IstorikoList(SpinnersForRV spinnersForRV) {
        this.spinnersForRV = spinnersForRV;
    }

    public Nosil_IstorikoList() {

    }



    public CheckBoxesForRV getCheckBoxesForRV() {
        return checkBoxesForRV;
    }

    public void setCheckBoxesForRV(CheckBoxesForRV checkBoxesForRV) {
        this.checkBoxesForRV = checkBoxesForRV;
    }

    public EditTextForRV getEditTextForRV() {
        return editTextForRV;
    }

    public void setEditTextForRV(EditTextForRV editTextForRV) {
        this.editTextForRV = editTextForRV;
    }

    public SpinnersForRV getSpinnersForRV() {
        return spinnersForRV;
    }

    public void setSpinnersForRV(SpinnersForRV spinnersForRV) {
        this.spinnersForRV = spinnersForRV;
    }


    public TitlesForRV getTitlesForRV() {
        return titlesForRV;
    }

    public void setTitlesForRV(TitlesForRV titlesForRV) {
        this.titlesForRV = titlesForRV;
    }

    public TextViewSForRV getTextViewSForRV() {
        return textViewSForRV;
    }

    public void setTextViewSForRV(TextViewSForRV textViewSForRV) {
        this.textViewSForRV = textViewSForRV;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals( Object obj) {
        return super.equals(obj);
    }


}
