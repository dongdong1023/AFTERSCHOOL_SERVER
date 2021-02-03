package com.wx.base.entity.after;

import com.wx.base.common.utils.AESUtils;
import com.wx.base.common.utils.DateUtils;
import com.wx.base.entity.support.BaseEntity;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 东东
 * @date 2021/1/28 11:51
 */
@Entity
@Table(name = "after_user")
public class AfterUserInfo extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "subscribe", length = 1, columnDefinition = "INT default 0")
    private Integer subscribe;      //用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。

    @Column(name = "openid", length = 128, nullable = false)
    private String openid;          //用户的标识，对当前公众号唯一

    @Column(name = "unionid", length = 128)
    private String unionid;          //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。

    @Column(name = "nickname", length = 521, nullable = false)
    private String nickname;          //用户的昵称(转码)

    @Column(name = "sex", length = 1)
    private Integer sex;      //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知

    @Column(name = "city", length = 64)
    private String city;          //用户所在城市

    @Column(name = "country", length = 64)
    private String country;          //用户所在国家

    @Column(name = "province", length = 64)
    private String province;          //用户所在省份

    @Column(name = "language", length = 32)
    private String language;          //用户的语言，简体中文为zh_CN

    @Column(name = "headimgurl", length = 255)
    private String headimgurl;          //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。

    @Transient
    private long subscribeTime;         //用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间

    @Column(name = "subscribeTime")
    private Date subscribeTimeDate;

    @Column(name = "remark", length = 255)
    private String remark;              //公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注

    @Column(name = "groupid")
    private Integer groupid;        //用户所在的分组ID（兼容旧的用户分组接口）

    @Column(name = "tagidList", length = 255)
    private String tagidList;

    //返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENE_PROFILE_LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_WECHAT_ADVERTISEMENT 微信广告，ADD_SCENE_OTHERS 其他
    @Column(name = "subscribeScene", length = 64)
    private String subscribeScene;

    @Column(name = "qrScene", length = 64)
    private String qrScene;             //二维码扫码场景（开发者自定义）

    @Column(name = "qrSceneStr", length = 255)
    private String qrSceneStr;          //二维码扫码场景描述（开发者自定义）

    //////////////////////微信公众平台提供/////////////////////////////


    @Column(name = "tags", length = 128)
    private String tags;         //标签(二次元、电竞、其他)

    @Column(name = "creatTime", nullable = false)
    private Date creatTime;

    @Column(name = "updateTime")
    private Date updateTime;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "realname", length = 64)
    private String realname;       //真实姓名

    @Column(name = "telNum", length = 32)
    private String telNum;         //手机号

    @Column(name = "idCard", length = 32)
    private String idCard;         //身份证号码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickname() {
        return StringUtils.isNotBlank(nickname) ? AESUtils.unicode2String(nickname) : nickname;
    }

    public void setNickname(String nickname) {
        nickname = StringUtils.isNotBlank(nickname) ? AESUtils.stringUnicode(nickname) : nickname;
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getSubscribeTimeDate() {
        return subscribeTimeDate;
    }

    public void setSubscribeTimeDate(Date subscribeTimeDate) {
        subscribeTimeDate = StringUtils.isNotBlank(subscribeTime + "") ? DateUtils.stampToDate(this.getSubscribeTime()) : subscribeTimeDate;
        this.subscribeTimeDate = subscribeTimeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList;
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public String getQrScene() {
        return qrScene;
    }

    public void setQrScene(String qrScene) {
        this.qrScene = qrScene;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

}
