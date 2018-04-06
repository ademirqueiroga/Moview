package com.ademir.mooview.data.models;

import android.arch.persistence.room.Entity;

@Entity
public class Category {

    public int id;
    public String name;

    public Category() {

    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
