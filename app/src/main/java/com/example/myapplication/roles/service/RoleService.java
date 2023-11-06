package com.example.myapplication.roles.service;

import com.example.myapplication.dao.IDao;
import com.example.myapplication.roles.entity.Role;

import java.util.List;

public class RoleService implements IDao<Role> {

    private List<Role> roles;


    public RoleService(List<Role> roles){
        this.roles=roles;
    }
    @Override
    public List<Role> getList() {
        return this.roles;
    }
    @Override
    public boolean addItem(Role o) {
        return roles.add(o);
    }
}
