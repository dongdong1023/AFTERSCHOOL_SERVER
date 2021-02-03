package com.wx.base.dao;

import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.after.Active;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IActiveDao extends IBaseDao<Active, Integer> {

    Active findActiveByCode(@Param("code") String code);
}
