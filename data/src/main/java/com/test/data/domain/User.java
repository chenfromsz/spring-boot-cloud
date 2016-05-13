package com.test.data.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class User {
    @GraphId
    private Long id;
    private String name;
    private String email;
    private Integer sex;
    @DateLong
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create;

    @Relationship(type="拥有")
    List<Owner> owners = new ArrayList<>();

    @Relationship(type = "隶属", direction = Relationship.INCOMING)
    Belong belong;

    public User() {
    }

    public Owner addOwner(Role role, String name){
        Owner owner = new Owner(this, role, name);
        this.owners.add(owner);
        return owner;
    }

    public Belong beBelong(Unit unit, String name){
        Belong belong = new Belong(name, unit, this);
        this.belong = belong;
        return belong;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public Belong getBelong() {
        return belong;
    }

    public void setBelong(Belong belong) {
        this.belong = belong;
    }
}
