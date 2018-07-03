package com.example.q.project1ver3.database.model;

public class Contact {

    public static final String TABLE_NAME = "contacts";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";

    private int id;
    private String name;
    private String phone_number;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " NAME,"
                    + COLUMN_PHONE_NUMBER + " PHONE NUMBER "
                    + ")";

    public Contact() {
    }

    public Contact(int id, String name, String phone_number) {
        this.id = id;
        this.name   = name;
        this.phone_number= phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }



}
