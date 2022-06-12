package org.chobit.cm.common.model;


/**
 * @author robin
 */
public class ResultWrapper<T> {

    private int code = 200;

    private String message;

    private T content;

    public ResultWrapper() {
    }

    public ResultWrapper(T content) {
        this.content = content;
    }

    public ResultWrapper(int code, T content) {
        this.code = code;
        this.content = content;
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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResultWrapper{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + content +
                '}';
    }
}
