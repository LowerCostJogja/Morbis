package com.example.lucifer.morbis.model.InfoDoctor;

/**
 * Created by lucifer on 4/25/2016.
 */
public class Hospital {

    int pk;
    String name, address;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
