package com.wonder.vo;

import lombok.Data;

@Data
public class HttpResp {
    Integer status;
    Object data;
    String msg;

    public HttpResp() {
        this.status = -1;
    }

    public enum HttpRespCode {
        SUCCESS(0,"成功"),
        FAIL(-1,"失败");

        Integer status;
        String msg;

        public Integer getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }

        HttpRespCode(Integer status, String msg){
            this.status = status;
            this.msg = msg;
        }
    }


}
