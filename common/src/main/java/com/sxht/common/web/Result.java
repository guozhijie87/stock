package com.sxht.common.web;

import java.util.Date;

/**
 * Created by lmm on 2016/11/01.
 */
public class Result {
    public static Result success() {
        return success(null);
    }

    public static Result success(Object data) {
        Result r = new Result();

        r.setTime(new Date());
        r.setStatus("success");
        r.setData(data);

        return r;
    }

    public static Result error(Exception e) {
        return error(e.getMessage());
    }

    public static Result error(String errorMessage) {
        Result r = new Result();

        r.setTime(new Date());
        r.setStatus("error");
        r.setErrorMessage(errorMessage);

        return r;
    }

    private String status;
    private Object data;
    private Date time;
    private String errorMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
