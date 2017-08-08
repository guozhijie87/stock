package com.sxht.model;

import java.util.Date;

public class User {
    private String userid;

    private String password;

    private String mail;

    private String firstname;

    private String lastname;

    private Boolean hassignin;

    private Date createtime;

    private Date lastupgradetime;

    private String createuser;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname == null ? null : firstname.trim();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname == null ? null : lastname.trim();
    }

    public Boolean getHassignin() {
        return hassignin;
    }

    public void setHassignin(Boolean hassignin) {
        this.hassignin = hassignin;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupgradetime() {
        return lastupgradetime;
    }

    public void setLastupgradetime(Date lastupgradetime) {
        this.lastupgradetime = lastupgradetime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }
}