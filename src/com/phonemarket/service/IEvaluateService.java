package com.phonemarket.service;

import com.github.pagehelper.PageInfo;
import com.phonemarket.entity.Evaluate;

import java.util.List;

public interface IEvaluateService {
    Integer addEvaluate(Evaluate eva, String[] imgs);

    List<Evaluate> findEvaluateByGoodsId(Integer goodsId);

    PageInfo<Evaluate> findAllEvaluate(Integer page, Integer limit, String keyword);

    Integer deleteEvaluate(Integer evaId);

    Evaluate findEvaluteById(Integer evaId);
}
