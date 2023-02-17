package reindeer.base.bean;

import net.sf.json.JSONObject;

public class WdfResult {
    public static final String RESULT_SUCCESS = "200";
    public static final String RESULT_ERROR = "500";
    private static final long serialVersionUID = 1L;
    private String code = "500";
    private String msg = "失败";
    private String version = "0.1";
    private Object data = "";

    private WdfResult() {
    }

    public static WdfResult getResult() {
        return new WdfResult();
    }

    public static WdfResult getSuccessResult() {
        return (new WdfResult()).success();
    }

    public WdfResult success() {
        this.code = "200";
        this.msg = "成功";
        return this;
    }

    public WdfResult failed() {
        this.code = "500";
        this.msg = "失败";
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public WdfResult setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public WdfResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public WdfResult setVersion(String version) {
        this.version = version;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public WdfResult setData(Object data) {
        this.data = data;
        return this;
    }

    public String toString() {
        JSONObject jso = new JSONObject();
        jso.put("code", this.code);
        jso.put("msg", this.msg);
        jso.put("version", this.version);
        jso.put("data", this.data);
        return jso.toString();
    }
}
