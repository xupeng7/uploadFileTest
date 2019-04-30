package hello.model;

import java.io.Serializable;
import java.util.Objects;

public class ResultVo implements Serializable{
    private int code;
    private String message;


    public ResultVo(int code, String message) {
        this.code = code;
        this.message = message;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultVo resultVo = (ResultVo) o;
        return code == resultVo.code &&
                Objects.equals(message, resultVo.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, message);
    }
}
