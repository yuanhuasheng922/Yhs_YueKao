package com.example.yuan.yhs_yuekao.model;

import com.example.yuan.yhs_yuekao.callback.ICallback;
import com.example.yuan.yhs_yuekao.callback.MyCallBack;
import com.example.yuan.yhs_yuekao.okutils.OkHttp;

import java.util.Map;

public class IModelImple implements IModel {
    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params, final MyCallBack callBack) {
        OkHttp.getInstance().postEnqueue(url, clazz, params, new ICallback() {
            @Override
            public void onSuccess(Object obj) {
                callBack.getRequest(obj);
            }

            @Override
            public void onFail(Exception e) {
                callBack.getRequest(e.getMessage());
            }
        });
    }
}
