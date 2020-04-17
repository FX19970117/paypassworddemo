package com.example.paypassworddemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paypassworddemo.pay.PassValitationPopwindow;

/**
 * @author 冯旭
 * 邮箱：876111689@qq.com
 * CSDN: https://blog.csdn.net/weixin_40611659
 * github：https://github.com/FX19970117
 */
public class MainActivity extends AppCompatActivity {
    TextView tvMainPop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //去支付按钮
        tvMainPop = findViewById(R.id.tv_main_pop);
        tvMainPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //屏幕阴影变暗
                setDarkWindow(true);
                PassValitationPopwindow mPassValitationPopwindow = new PassValitationPopwindow(MainActivity.this, tvMainPop, new PassValitationPopwindow.OnInputNumberCodeCallback() {
                    @Override
                    public void onSuccess(String code) {
                        Toast.makeText(MainActivity.this, "您输入的密码为：" + code, Toast.LENGTH_SHORT).show();
                        //todo  去做业务逻辑  code为输入的6位密码

                    }
                });
                mPassValitationPopwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //去除背景变暗
                        setDarkWindow(false);
                    }
                });

            }
        });
    }


    private View mDialogView;

    /**
     * window背景是否变暗
     *
     * @param isDark
     */
    public void setDarkWindow(boolean isDark) {

        if (mDialogView == null) {
            mDialogView = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            mDialogView.setLayoutParams(layoutParams);
            mDialogView.setVisibility(View.VISIBLE);
            mDialogView.setBackgroundColor(Color.parseColor("#000000"));
            mDialogView.setAlpha(0.6f);
            FrameLayout rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            rootView.addView(mDialogView);
        }
        //添加一个半透明的view 通过显示隐藏的方式来产生背景变暗效果
        if (isDark) {
            mDialogView.setVisibility(View.VISIBLE);
        } else {
            mDialogView.setVisibility(View.GONE);
        }
    }
}
