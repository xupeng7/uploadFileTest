package hello.model;

import java.io.Serializable;

/*
* 前端需要返回的参数
* */
public class Result implements Serializable {

    private int uid;
    private String url;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
