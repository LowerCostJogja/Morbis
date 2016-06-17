package com.example.lucifer.morbis.model.InfoDoctor;

/**
 * Created by lucifer on 4/25/2016.
 */
public class Doctor {

    int pk;
    Specialization specialization;
    Citizen citizen;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }
}
