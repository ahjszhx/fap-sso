package rest;



public class FapRequestException extends Exception{

    private Integer code; //100 传输错误 200fap服务端业务报错

    public FapRequestException(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
