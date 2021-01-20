package com.wx.base.controller;

import imba.game.base.common.DateEditor;
import imba.game.base.common.utils.CharUtils;
import imba.game.base.common.utils.DateUtils;
import imba.game.base.common.utils.IpUtils;
import imba.game.base.common.utils.MultipartFileUtils;
import imba.game.base.config.SystemConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;
    
    @Autowired
    protected SystemConfig systemConfig;
    

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
    }

    public static Object copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
        return target;
    }
    
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    
    /**
     * 带参重定向
     *
     * @param path
     * @return
     */
    protected String redirect(String path) {
        return "redirect:" + path;
    }

    /**
     * 不带参重定向
     *
     * @param response
     * @param path
     * @return
     */
    protected String redirect(HttpServletResponse response, String path) {
        try {
            response.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取分页请求
     *
     * @return
     */
    protected PageRequest getPageRequest() {
        int page = 0;
        int size = 20;
        String sortName = request.getParameter("sort");
        Sort sort = null;
		//包含多个排序---规则 http://XXXXXX/AAA.action?sort=id,desc:time,desc
        List<Order> orders=new ArrayList<Order>();
        if (StringUtils.isNoneBlank(sortName)) {
        	if(sortName.contains(CharUtils.COLON)) {
        		String[] sortNames = sortName.split(CharUtils.COLON);
        		for(int i = 0; i < sortNames.length; i ++) {
        			if(sortNames[i].split(CharUtils.COMMA)[1].equalsIgnoreCase("desc")) {
        				orders.add(new Order(Direction.DESC,sortNames[i].split(CharUtils.COMMA)[0]));
        			}else {
        				orders.add(new Order(Direction.ASC,sortNames[i].split(CharUtils.COMMA)[0]));
        			}
        		}
        	}else {
    			if(sortName.split(CharUtils.COMMA)[1].equalsIgnoreCase("desc")) {
    				orders.add(new Order(Direction.DESC,sortName.split(CharUtils.COMMA)[0]));
    			}else {
    				orders.add(new Order(Direction.ASC,sortName.split(CharUtils.COMMA)[0]));
    			}
        	}
        }
        String pageNumber = request.getParameter("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            page = Integer.parseInt(pageNumber) - 1;
        }
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        if(orders != null && orders.size() > 0) {
        	sort = new Sort(orders);
        }
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return pageRequest;
    }

    /**
     * 获取分页请求
     *
     * @param sort 排序条件
     * @return
     */
    protected PageRequest getPageRequest(Sort sort) {
        int page = 0;
        int size = 20;
        String sortName = request.getParameter("sortName");
        String sortOrder = request.getParameter("sortOrder");
        if (StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)) {
            if (sortOrder.equalsIgnoreCase("desc")) {
                sort.and(new Sort(Direction.DESC, sortName));
            } else {
                sort.and(new Sort(Direction.ASC, sortName));
            }
        }
        String pageNumber = request.getParameter("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            page = Integer.parseInt(pageNumber) - 1;
        }
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return pageRequest;
    }

	/**
	 * 上传海报图片
	 * 
	 * @param folderName
	 * @param pictureFile
	 */
	public String upLoadPictureFile(String folderName, MultipartFile pictureFile) throws Exception {
		try {
			String path = "";
			if (pictureFile != null && pictureFile.getSize() > 0) {
				String layout = pictureFile.getOriginalFilename().substring(pictureFile.getOriginalFilename().lastIndexOf(CharUtils.SPOT));
				path = CharUtils.BACKSLASH + folderName + CharUtils.BACKSLASH + DateUtils.getCurrentDate() + CharUtils.BACKSLASH
						+ UUID.randomUUID().toString() + layout;
				File destFile = getPicturePath(path);
				MultipartFileUtils.getInstance().save(pictureFile, destFile);
				return systemConfig.getPicPath()+path;
			}else {
				return path;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	protected File getPicturePath(String path) {
		try {
			return imba.game.base.common.utils.FileUtils.getInstance().getFileByPath(systemConfig.getPicFullPath(path));
		} catch (Exception e) {
			return null;
		}
	}

    /**
     * 获取用户IP
     * @return
     */
	public String getIp(){
	    return IpUtils.getIpAddr(request);
    }
}
