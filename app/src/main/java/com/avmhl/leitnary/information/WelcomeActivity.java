package com.avmhl.leitnary.information;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.avmhl.leitnary.MainActivity;
import com.avmhl.leitnary.R;
import com.avmhl.leitnary.adapter.MyPageAdapter;
import com.avmhl.leitnary.sharedPreferences.Save;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout LayoutDot;
    private TextView[] dotstv;
    private int[] layout;
    private Button btnSpik,btnNext;
    private MyPageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isFirstTimeStartApp())
        {
            startMainActivity();
            finish();

        }
        setContentView(R.layout.activity_welcome);
        LayoutDot=findViewById(R.id.dotlayout);
        viewPager=findViewById(R.id.viewpager);
        btnNext=findViewById(R.id.btn_next);
        btnSpik=findViewById(R.id.btn_skip);

        setStatusBarTransParent();
        btnSpik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
                SharedPreferences.Editor edit = share.edit();
                edit.putString(Save.language, "persian");
                edit.putString(Save.text_size, "26");
                edit.putString(Save.color_text, "#000000");
                edit.putString(Save.cart, "5");
                edit.putString(Save.share, "share");
                edit.putString(Save.color_back, "#FFFFFF");
                edit.commit();
                startMainActivity();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
                SharedPreferences.Editor edit = share.edit();
                edit.putString(Save.language, "persian");
                edit.putString(Save.text_size, "26");
                edit.putString(Save.color_text, "#000000");
                edit.putString(Save.cart, "5");
                edit.putString(Save.share, "share");
                edit.putString(Save.color_back, "#FFFFFF");
                edit.commit();
                int cureentPage=viewPager.getCurrentItem()+1;
                if(cureentPage < layout.length)
                {
                    viewPager.setCurrentItem(cureentPage);

                }else
                    {
                        startMainActivity();
                    }

            }
        });
        layout = new int[]{R.layout.slide_1,R.layout.slide_2,R.layout.slide_3};
        pageAdapter= new MyPageAdapter(layout,getApplicationContext());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == layout.length - 1) {
                    btnNext.setText("شروع");
                    btnSpik.setVisibility(View.GONE);
                } else
                    {

                        btnNext.setText("بعدی");
                        btnSpik.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);

    }
    private boolean isFirstTimeStartApp()
    {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSloierApp", Context.MODE_PRIVATE);
        return ref.getBoolean("FrstTimeStartFlag",true);

    }
    private void setFirstTimeStartStatus(boolean stt)
    {
        SharedPreferences ref = getApplicationContext().getSharedPreferences("IntroSloierApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ref.edit();
        editor.putBoolean("FrstTimeStartFlag",stt);
        editor.commit();
    }

    private void  setDotStatus(int page)
    {
        LayoutDot.removeAllViews();
        dotstv = new TextView[layout.length];
        for(int i =0; i < dotstv.length;i++)
        {
            dotstv[i]=new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226;"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            LayoutDot.addView(dotstv[i]);


        }

        if(dotstv.length>0)
        {
            dotstv[page].setTextColor(Color.parseColor("#FFFFFF"));
        }
    }


    private void startMainActivity()
    {
        setFirstTimeStartStatus(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    private void setStatusBarTransParent()
    {
        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);

            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        }
    }
}