package com.wx.base.vo;

import com.wx.base.entity.admin.Role;
import com.wx.base.entity.admin.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

public class UserVO {

    private User user = new User();

    private MultipartFile poster;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MultipartFile getPoster() {
        return poster;
    }

    public void setPoster(MultipartFile poster) {
        this.poster = poster;
    }

    public Integer getId() {
        return user.getId();
    }

    public void setId(Integer id) {
        this.user.setId(id);
    }

    public String getUserName() {
        return user.getUserName();
    }

    public void setUserName(String userName) {
        this.user.setUserName(userName);
    }

    public String getNickName() {
        return user.getNickName();
    }

    public void setNickName(String nickName) {
        this.user.setNickName(nickName);
    }

    public String getAvatar() {
        return user.getAvatar();
    }

    public void setAvatar(String avatar) {
        this.user.setAvatar(avatar);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    public Integer getSex() {
        return user.getSex();
    }

    public void setSex(Integer sex) {
        this.user.setSex(sex);
    }

    public Date getBirthday() {
        return user.getBirthday();
    }

    public void setBirthday(Date birthday) {
        this.user.setBirthday(birthday);
    }

    public String getTelephone() {
        return user.getTelephone();
    }

    public void setTelephone(String telephone) {
        this.user.setTelephone(telephone);
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    public String getAddress() {
        return user.getAddress();
    }

    public void setAddress(String address) {
        this.user.setAddress(address);
    }

    public Integer getDeleteStatus() {
        return user.getDeleteStatus();
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.user.setDeleteStatus(deleteStatus);
    }

    public Integer getLocked() {
        return user.getLocked();
    }

    public void setLocked(Integer locked) {
        this.user.setLocked(locked);
    }

    public String getDescription() {
        return user.getDescription();
    }

    public void setDescription(String description) {
        this.user.setDescription(description);
    }

    public Date getCreateTime() {
        return user.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        this.user.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return user.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.user.setUpdateTime(updateTime);
    }

    public Set<Role> getRoles() {
        return user.getRoles();
    }

    public void setRoles(Set<Role> roles) {
        this.user.setRoles(roles);
    }
}
