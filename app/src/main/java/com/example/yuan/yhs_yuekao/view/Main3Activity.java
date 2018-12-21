package com.example.yuan.yhs_yuekao.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuan.yhs_yuekao.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class Main3Activity extends AppCompatActivity implements QRCodeView.Delegate {

    private ZXingView zXingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        zXingView = findViewById(R.id.zxing);
        //代理
        zXingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //打开相机
        zXingView.startCamera();
        //显示扫描框
        zXingView.startSpotAndShowRect();
        //打开闪光灯
        zXingView.openFlashlight();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingView.startCamera();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
