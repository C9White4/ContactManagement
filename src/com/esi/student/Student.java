package com.esi.student;
public class Student {
    private static int idIncrement=0;
    public int id;
    public String firstName;
    public String lastName;
    private String cin;
    private String cne;
    private String email;
    private int phoneNumber;
    public Student() {
        this.id=++idIncrement;
        this.firstName="unknown";
        this.lastName="unknown";
        this.cin="XXXXXX";
        this.cne="XYYYYYYYYY";
        this.email="unknown@gmail.com";
        this.phoneNumber=0;
    }
    public Student(String fN,String lN,String cin,String cne,String email,int phN) {
        this.id=++idIncrement;
        this.firstName=fN;
        this.lastName=lN;
        this.cin=cin;
        this.cne=cne;
        this.email=email;
        this.phoneNumber=phN;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id=id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String fN) {
        this.firstName=fN;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lN) {
        this.lastName=lN;
    }
    public String getCin() {
        return this.cin;
    }
    public void setCin(String cin) {
        this.cin=cin;
    }
    public String getCne() {
        return this.cne;
    }
    public void setCne(String cne) {
        this.cne=cne;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email=email;
    }
    public int getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(int phN) {
        this.phoneNumber=phN;
    }
    public String fileUtilisateur() {
        return "{Full name: "+this.firstName+" "+this.lastName+", CIN: "+this.cin+", CNE: "+this.cne+"}";
    }
}
