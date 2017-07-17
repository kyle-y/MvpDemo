package com.example.administrator.mvpdemo.m.database.daos;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/7/12.
 */
@Entity
public class Movies {
    @Id
    private Long id;
    private String name;
    private int year;
    private String type;
    @Generated(hash = 2083219120)
    public Movies(Long id, String name, int year, String type) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.type = type;
    }
    @Generated(hash = 2138365087)
    public Movies() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return this.year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }


}
