package com.example.appquanlybancafe.len;

import java.util.List;

public class mix {
    datBan db;
    List<CTDatBan> ct;

    public datBan getDb() {
        return db;
    }

    public void setDb(datBan db) {
        this.db = db;
    }

    public List<CTDatBan> getCt() {
        return ct;
    }

    public void setCt(List<CTDatBan> ct) {
        this.ct = ct;
    }

    public mix(datBan db, List<CTDatBan> ct) {
        this.db = db;
        this.ct = ct;
    }
}
