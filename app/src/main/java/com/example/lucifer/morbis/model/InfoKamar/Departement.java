package com.example.lucifer.morbis.model.InfoKamar;

import com.example.lucifer.morbis.model.InfoKamar.Department.DepartmentCategory;
import com.example.lucifer.morbis.model.InfoKamar.Department.Hospital;

/**
 * Created by lucifer on 4/25/2016.
 */
public class Departement {

    int pk;
    String name, sourceID;

    DepartmentCategory departmentCategory;
    Hospital hospital;

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

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public DepartmentCategory getDepartmentCategory() {
        return departmentCategory;
    }

    public void setDepartmentCategory(DepartmentCategory departmentCategory) {
        this.departmentCategory = departmentCategory;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
