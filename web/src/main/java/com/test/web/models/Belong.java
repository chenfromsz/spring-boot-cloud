package com.test.web.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Belong {
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create;

    private Unit unit;
    private User user;

    public Belong() {
    }

    public Belong(String name, Unit unit, User user) {
        this.name = name;
        this.unit = unit;
        this.user = user;
        this.create = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}
