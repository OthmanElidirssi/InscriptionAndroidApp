package com.example.myapplication.dao;

import java.util.List;

public interface IDao<T> {

    List<T> getList();
    boolean addItem(T o);
}
