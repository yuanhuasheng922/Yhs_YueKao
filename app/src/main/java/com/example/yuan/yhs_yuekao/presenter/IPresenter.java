package com.example.yuan.yhs_yuekao.presenter;

import java.util.Map;

public interface IPresenter {
    void getRequest(String url, Class clazz, Map<String,String> params);
}
