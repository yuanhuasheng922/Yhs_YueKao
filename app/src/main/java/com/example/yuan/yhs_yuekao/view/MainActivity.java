package com.example.yuan.yhs_yuekao.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yuan.yhs_yuekao.R;
import com.example.yuan.yhs_yuekao.apis.Api;
import com.example.yuan.yhs_yuekao.bean.ImageBean;
import com.example.yuan.yhs_yuekao.presenter.IPresenterIMple;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {

    private Banner banner;
    private IPresenterIMple presenterIMple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        getDatas();
    }

    private void initView() {
        //控件
        banner = findViewById(R.id.banner);
        //p层
        presenterIMple = new IPresenterIMple(this);
        //样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
               ImageBean.DataBean dataBean= (ImageBean.DataBean) path;
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(dataBean.getIcon(),imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                return imageView;
            }
        });


    }
    private void getDatas() {
        Map<String,String> param =new HashMap<>();
        presenterIMple.getRequest(Api.TYPE_IMAGE,ImageBean.class,param);
    }
    @Override
    public void getRequest(Object data) {
   if (data instanceof ImageBean)
   {
      ImageBean imageBean= (ImageBean) data;
       banner.setImages(imageBean.getData());
       banner.start();
   }
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);

    }
}
