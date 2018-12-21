package com.example.yuan.yhs_yuekao.callback;

public interface ICallback<T> {
    void onSuccess(Object obj);
    void onFail(Exception e);
}
