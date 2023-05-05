package com.dao;
import com.model.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeDao {
    public List<Type> typeList();
    public int typeAdd(String name);
    public int typeDelete(int id);
    public Type selectTypeByName(String name);
    public Type selectTypeById(int id);
    public int typeEdit(Type type);
}
