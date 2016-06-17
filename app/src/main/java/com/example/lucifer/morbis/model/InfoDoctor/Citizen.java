package com.example.lucifer.morbis.model.InfoDoctor;

/**
 * Created by lucifer on 4/25/2016.
 */
public class Citizen {

    int pk, identity_number;
    String name, gender, place_of_birth, date_of_birth, blood_type;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public int getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(int identity_number) {
        this.identity_number = identity_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }
}
