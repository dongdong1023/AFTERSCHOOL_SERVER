package com.wx.base.service;

import imba.game.base.entity.admin.Resource;
import imba.game.base.service.support.IBaseService;
import imba.game.base.vo.ZtreeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * <p>
 * 资源服务类
 * </p>
 *
 * @author 东东
 * @since 2016-12-28
 */
public interface IResourceService extends IBaseService<Resource, Integer> {

    /**
     * 获取角色的权限树
     *
     * @param roleId
     * @return
     */
    List<ZtreeView> tree(int roleId);

    /**
     * 修改或者新增资源
     *
     * @param resource
     */
    void saveOrUpdate(Resource resource);

    /**
     * 关键字分页
     *
     * @param searchText
     * @param pageRequest
     * @return
     */
    Page<Resource> findAllByLike(String searchText, PageRequest pageRequest);


    /**
     * 父级的子级数据
     *
     * @param parentId
     * @return
     */
    List<Resource> children(Integer parentId);

}
