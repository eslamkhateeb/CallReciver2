package me.hlprhome.mngr.callreciver;

public class Person {

    private String mNumber;
    private String mCheck;


    public String getName() {
        return mNumber;
    }

    public void setName(String name) {
        this.mNumber = name;
    }

    public String getSurname() {
        return mCheck;
    }

    public void setSurname(String surname) {
        this.mCheck = surname;
    }
}
