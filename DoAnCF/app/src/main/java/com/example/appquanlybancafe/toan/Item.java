package com.example.appquanlybancafe.toan;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    String id;
    @SerializedName("value")
    String value;

    public Item() {
    }

    public Item(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
