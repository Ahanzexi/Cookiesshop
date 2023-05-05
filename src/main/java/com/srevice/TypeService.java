package com.srevice;

import com.model.Type;

import java.util.List;

public interface TypeService {
    public List<Type> typeList();
    public int typeAdd(String name);
    public boolean typeDelete(int id);
    public int typeEdit(Type type);
    public Type selectTypeById(int id);
}
