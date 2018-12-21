package com.example.yuan.yhs_yuekao.model;

import com.example.yuan.yhs_yuekao.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void getRequest(String url, Class clazz, Map<String,String> params, MyCallBack callBack);
}
