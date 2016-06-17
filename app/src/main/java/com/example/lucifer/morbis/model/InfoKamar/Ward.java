package com.example.lucifer.morbis.model.InfoKamar;

/**
 * Created by lucifer on 4/25/2016.
 */
public class Ward {

    int pk;
    String name;
    String ward_type;

    Departement departement;
    WardClass wardClass;

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

    public String getWard_type() {
        return ward_type;
    }

    public void setWard_type(String ward_type) {
        this.ward_type = ward_type;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public WardClass getWardClass() {
        return wardClass;
    }

    public void setWardClass(WardClass wardClass) {
        this.wardClass = wardClass;
    }
}
