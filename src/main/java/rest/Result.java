package rest;

import java.io.Serializable;


public class Result implements Serializable {

    private static final long serialVersionUID = -8430749200056770740L;

    private Boolean suc; // 返回结果
    private String msg; // 错误信息
    private String emsg; // 异常信息
    private int code = 1000; //异常编码 1000 以上  4001  未授权  4002  未登录， 8000盘点中;8001库存不足
    private Object data;
    private long ct;

    public static Result initResult() {
        Result result = new Result();
        result.suc = false;
        return result;
    }

    public Result() {
    }

    public Result(Boolean suc) {
        this.suc = suc;
    }

    public Result(Boolean suc, String msg) {
        this.suc = suc;
        this.msg = msg;
    }

    public Result(Boolean suc, Object data) {
        this.suc = suc;
        this.data = data;
    }

    public Boolean getSuc() {
        return suc;
    }

    public void setSuc(Boolean suc) {
        this.suc = suc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCt() {
        return System.currentTimeMillis() / 1000;
    }

    public Result fail(String msg) {
        this.setSuc(false);
        this.setEmsg(msg);
        this.setData("");
        return this;
    }

    public Result fail(String msg, Object data) {
        this.setSuc(false);
        this.setEmsg(msg);
        this.setData(data);
        return this;
    }

    public Result fail(String msg, Object data, int code) {
        this.setSuc(false);
        this.setEmsg(msg);
        this.setData(data);
        this.setCode(code);
        return this;
    }

    public Result suc(Object data) {
        this.setSuc(true);
        this.setData(data);
        return this;
    }

    public Result suc(String msg) {
        this.setSuc(true);
        this.setData("");
        this.setMsg(msg);
        return this;
    }
}
