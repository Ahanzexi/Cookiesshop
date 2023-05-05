package com.srevice;

import com.dao.GoodsDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public int addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    @Override
    public int countGoodsByTypeId(int typeId) {
        return goodsDao.countGoodsByTypeId(typeId);
    }

    @Override
    public PageInfo<Goods> getGoodsByPage(int pageNum, int recommend) {
        PageHelper.startPage(pageNum,10);
        List<Goods> goodsList = new ArrayList<Goods>();
        if(recommend == 0){
//            全部商品
            goodsList = goodsDao.getAllGoods();
        }else {
            goodsList = goodsDao.getAllGoodsByRecommend(recommend);
        }
        for (Goods goods:goodsList) {
            if(isRecommend(goods.getId(),1)) goods.setScroll(true);
            if(isRecommend(goods.getId(),2)) goods.setHot(true);
            if(isRecommend(goods.getId(),3)) goods.setNew(true);
        }
        return new PageInfo<Goods>(goodsList);
    }

    @Override
    public int deleteGoods(int goodsId) {
        return goodsDao.deleteGoods(goodsId);
    }

    @Override
    public int removeRecommend(int recommend, int goodsId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("recommend",recommend);
        map.put("gid",goodsId);
        return goodsDao.removeRecommend(map);
    }

    @Override
    public int addRecommend(int recommend, int goodsId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("recommend",recommend);
        map.put("gid",goodsId);
        return goodsDao.addRecommend(map);
    }

    @Override
    public boolean isRecommend(int goodsId, int recommend) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("recommend",recommend);
        map.put("gid",goodsId);
        if(goodsDao.isRecommend(map) != null)
            return true;
        return false;
    }

    @Override
    public List<Goods> getAllGoodsByRecommend(int recommend) {
        return goodsDao.getAllGoodsByRecommend(recommend);
    }

    @Override
    public Goods goodsDetail(int id) {
        return goodsDao.goodsDetail(id);
    }

    @Override
    public PageInfo<Goods> getGoodsByPageAndTypeId(int pageNum, int typeId) {
        PageHelper.startPage(pageNum,12);
        List<Goods> goodsList = new ArrayList<Goods>();
        if(typeId == 0){
//            全部商品
            goodsList = goodsDao.getAllGoods();
        }else {
            goodsList = goodsDao.getAllGoodsByTypeId(typeId);
        }
        return new PageInfo<Goods>(goodsList);
    }
}
