package com.example.yuan.yhs_yuekao.presenter;

import com.example.yuan.yhs_yuekao.callback.MyCallBack;
import com.example.yuan.yhs_yuekao.model.IModelImple;
import com.example.yuan.yhs_yuekao.view.IView;

import java.util.Map;

public class IPresenterIMple implements IPresenter{
    //v层 和model实现类
    private IView mIView;
    private IModelImple mImodelImple;

    public IPresenterIMple(IView mIView) {
        this.mIView = mIView;
        mImodelImple=new IModelImple();
    }

    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params) {
        mImodelImple.getRequest(url, clazz, params, new MyCallBack() {
            @Override
            public void getRequest(Object data) {
                mIView.getRequest(data);
            }
        });
    }

    public void ondes()
    {
        mImodelImple=null;
        mIView=null;
    }

}
