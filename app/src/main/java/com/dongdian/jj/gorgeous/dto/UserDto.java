package com.dongdian.jj.gorgeous.dto;

/**
 * Created by Jiajun he on 2018/4/16.
 * Email:1021661582@qq.com
 * des: 用户实体
 * version: 1.0.0
 */

public class UserDto {


    /**
     * id : 1
     * account : admin
     * avatar : splash.jpg
     * password : DEMCLGDCB@FCF@FGDGG
     * nickname : GG管理员
     * sign : 1.本站内容来源自网络，图片版权属于原作者，本站转载仅供大家学习和交流，切勿用于任何商业活动。
     2.本站不承担用户因使用这些资源对自己和他人造成任何形式的损失或伤害。
     3.如果侵害了您的合法权益，请您及时与我们,我们会在第一时间删除相关内容.
     4.联系邮箱1125120704@qq.com
     * gender : 1
     * focus : 0
     * fans : 0
     */

    private int id;
    private String account;
    private String avatar;
    private String nickname;
    private String sign;
    private int gender;
    private int focus;
    private int fans;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sign='" + sign + '\'' +
                ", gender=" + gender +
                ", focus=" + focus +
                ", fans=" + fans +
                '}';
    }
}
