package com.example.myapplication.students.entity;

import com.example.myapplication.filieres.entity.Filiere;
import com.example.myapplication.roles.entity.Role;

import java.io.Serializable;
import java.util.List;

public class Student  implements Serializable {

    private int id;

    public Student() {
    }

    private String name;
    private String email;
    private String phone;
    private Filiere filiere;
    private List<Role> roles;

    public Student(int id, String name, String email, String phone, Filiere filiere, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.filiere = filiere;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
