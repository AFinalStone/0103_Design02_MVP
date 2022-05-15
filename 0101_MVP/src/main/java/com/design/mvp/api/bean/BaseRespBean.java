package com.design.mvp.api.bean;

/**
 * 新的接口规范返回的基础json数据对象
 *
 * @param <T>
 */
public class BaseRespBean<T>{

    /**
     * status : 200
     * code : 200
     * message : 进行通话
     * data : {"chat_status":2,"say_status":0,"answer_status":1,"balance_tips":"","price":500,"chat_status_tips":"暂时无法与该用户通话，您可以联系其他在线用户","follow_status":2,"price_text":"收入5000米粒/分钟","goddess_list":[],"type":1,"answer_avatar":"https://mpic.prxymect.cn/static/avatar/default-avatar.jpg","answer_nickname":"用户14399","answer_fans":"0","answer_user_id":86965,"answer_yunxin_accid":"my86965","vip_level":1,"vip_tips":"对方为vip用户","answer_status_tips":"用户已暂停接听，可以私信他开启","system_tips":"严禁出现色情、赌博、政治等违反国家法律规定的内容，一经发现将做封号处理","beauty_status":1,"call_bg_status":3,"call_images":"https://mpic.prxymect.cn/call_images/20/08/12/da5cc18e62c4a09615de0e6f575a6496.jpg","call_video":"https://mpic.prxymect.cn/call_video/20/08/12/e56d81df29166dc17d94548add4d8ee0.mp4","call_id":3682,"room_id":"1_83245_20201023","rice_level":0}
     */

    private int status;
    private int code;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
