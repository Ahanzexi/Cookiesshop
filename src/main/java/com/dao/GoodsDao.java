package com.dao;

import com.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface GoodsDao {
    public int addGoods(Goods goods);
    public int countGoodsByTypeId(int typeId);
//    全部商品
    public List<Goods> getAllGoods();
//    指定标记的商品
    public List<Goods> getAllGoodsByRecommend(int recommend);
    public int deleteGoods(int goodsId);
    public int removeRecommend(Map<String,Object> map);
    public Goods goodsDetail(int id);
    public Goods isRecommend(Map<String,Object> map);
    public int addRecommend(Map<String,Object> map);
    public List<Goods> getAllGoodsByTypeId(int typeId);
}
