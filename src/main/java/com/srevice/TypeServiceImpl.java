package com.srevice;

import com.dao.GoodsDao;
import com.dao.TypeDao;
import com.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Type> typeList() {
        return typeDao.typeList();
    }

    @Override
    public int typeAdd(String name) {
        if(typeDao.selectTypeByName(name) != null){
            return 0;
        }
        return typeDao.typeAdd(name);
    }

    @Override
    public boolean typeDelete(int id) {
        if(goodsDao.countGoodsByTypeId(id) > 0){
            return false;
        }else {
            typeDao.typeDelete(id);
            return true;
        }
    }

    @Override
    public int typeEdit(Type type) {
        if(typeDao.selectTypeByName(type.getName()) != null){
            return 0;
        }
        return typeDao.typeEdit(type);
    }

    @Override
    public Type selectTypeById(int id) {
        if(id == 0) return null;
        return typeDao.selectTypeById(id);
    }
}
