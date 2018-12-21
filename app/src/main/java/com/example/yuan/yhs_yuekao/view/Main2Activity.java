package com.example.yuan.yhs_yuekao.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuan.yhs_yuekao.R;
import com.example.yuan.yhs_yuekao.adapter.UserAdapter;
import com.example.yuan.yhs_yuekao.apis.Api;

import com.example.yuan.yhs_yuekao.bean.UserBean;
import com.example.yuan.yhs_yuekao.presenter.IPresenterIMple;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements IView,View.OnClickListener {

    private ImageView activity2_saoyisao;
    private ImageView activity2_qiehuan;
    private XRecyclerView xRecyclerView;
    private boolean isShow=true;
    private UserAdapter saAdapter;
    private IPresenterIMple presenterIMple;
    private int  mPage=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        activity2_saoyisao = findViewById(R.id.activity2_saoyisao);

        activity2_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });

    }


    private void initView() {

        activity2_qiehuan = findViewById(R.id.activity2_qiehuan);
        xRecyclerView = findViewById(R.id.xrecyclerview);
        activity2_qiehuan.setOnClickListener(this);
        //p层
        presenterIMple = new IPresenterIMple(this);
        if (isShow)
        {
            StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        }
        else
        {
            StaggeredGridLayoutManager staggeredGridLayoutManager2=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            xRecyclerView.setLayoutManager(staggeredGridLayoutManager2);
        }

        isShow=!isShow;
        mPage=1;

        //适配器
        saAdapter = new UserAdapter(this,isShow);
        xRecyclerView.setAdapter(saAdapter);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                getShow();
            }

            @Override
            public void onLoadMore() {
                getShow();
            }
        });

        saAdapter.setOnLongLinstener(new UserAdapter.OnLongLinstener() {
            @Override
            public void osSuccess(String images) {
                Toast.makeText(Main2Activity.this,images+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getShow() {
        Map<String,String> params=new HashMap<>();
        params.put("keywords","手机");
        params.put("page","1");
        presenterIMple.getRequest(Api.TYPE_TEXT,UserBean.class,params);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity2_qiehuan:
                initView();
                getShow();
                break;
        }
    }

    @Override
    public void getRequest(Object data) {
        if (data instanceof UserBean)
        {
       UserBean userBean= (UserBean) data;
          if (mPage==1)
          {
              saAdapter.setmDatas(userBean.getData());
          }
          else
          {
              saAdapter.addmDatas(userBean.getData());
          }
          mPage++;
          xRecyclerView.loadMoreComplete();
          xRecyclerView.refreshComplete();

        }
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterIMple.ondes();
    }
}
