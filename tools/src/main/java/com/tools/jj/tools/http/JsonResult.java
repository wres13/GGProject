package com.tools.jj.tools.http;


public class JsonResult<T> {

    public int statueCode;

    public String message;

    public T results;

    public int getStatueCode() {
        return statueCode;
    }

    public void setStatueCode(int statueCode) {
        this.statueCode = statueCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "statueCode=" + statueCode +
                ", message='" + message + '\'' +
                ", results=" + results +
                '}';
    }
}
