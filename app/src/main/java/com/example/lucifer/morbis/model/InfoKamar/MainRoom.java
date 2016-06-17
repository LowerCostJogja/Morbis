package com.example.lucifer.morbis.model.InfoKamar;

/**
 * Created by lucifer on 4/25/2016.
 */
public class MainRoom {

    int pk;
    String status, name, ward_type, total_empty, total_filled;

    WardClass wardClass;
    Departement departement;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getTotal_empty() {
        return total_empty;
    }

    public void setTotal_empty(String total_empty) {
        this.total_empty = total_empty;
    }

    public String getTotal_filled() {
        return total_filled;
    }

    public void setTotal_filled(String total_filled) {
        this.total_filled = total_filled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public WardClass getWardClass() {
        return wardClass;
    }

    public void setWardClass(WardClass wardClass) {
        this.wardClass = wardClass;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
