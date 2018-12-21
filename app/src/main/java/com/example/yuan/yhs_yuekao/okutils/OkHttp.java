package com.example.yuan.yhs_yuekao.okutils;

import android.os.Handler;

import com.example.yuan.yhs_yuekao.callback.ICallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp {
    //单例
    public static OkHttp instance;
    private final OkHttpClient client;

    public static OkHttp getInstance()
    {
        if (instance==null)
        {
            synchronized (OkHttp.class)
            {
                instance=new OkHttp();
            }
        }
        return instance;
    }
    //handler
    private Handler handler=new Handler();

    public OkHttp()
    {
        //读取,写,超时
        client = new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();
    }
    //post
    public void postEnqueue(String url, final Class clazz, Map<String,String> params, final ICallback callback)
    {
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String> entry : params.entrySet())
        {
            builder.add(entry.getKey(),entry.getValue());
        }

      RequestBody body = builder.build();
        Request request=new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(final Call call, final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFail(e);
                        }
                    });
            }
                //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(s, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(o);
                    }
                });
            }
        });
    }

}
