package com.wx.base.dao.support;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import imba.game.base.entity.support.BaseEntity;

@NoRepositoryBean
public interface IBaseDao<T extends BaseEntity, ID extends Serializable>
extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>,QueryDslPredicateExecutor<T> {

}
