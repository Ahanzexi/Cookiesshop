package com.srevice;

import com.github.pagehelper.PageInfo;
import com.model.Goods;

import java.util.List;

public interface GoodsService {
    public int addGoods(Goods goods);
    public int countGoodsByTypeId(int typeId);
    public PageInfo<Goods> getGoodsByPage(int pageNum,int recommend);
    public int deleteGoods(int goodsId);
    public int removeRecommend(int recommend,int goodsId);
    public int addRecommend(int recommend,int goodsId);
    public List<Goods> getAllGoodsByRecommend(int recommend);
    public Goods goodsDetail(int id);
    public boolean isRecommend(int goodsId,int recommend);
    public PageInfo<Goods> getGoodsByPageAndTypeId(int pageNum,int typeId);
}
