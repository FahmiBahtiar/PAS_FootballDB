package com.example.footballdb;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class modelmovierealm extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String judul;
    private String desc;
    private String releaseDate;
    private String path;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }
}