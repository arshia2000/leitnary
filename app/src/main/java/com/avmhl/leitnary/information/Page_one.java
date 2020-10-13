package com.avmhl.leitnary.information;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.avmhl.leitnary.MainActivity;
import com.avmhl.leitnary.R;
import com.avmhl.leitnary.sharedPreferences.Save;
import com.avmhl.leitnary.ui.Setting;

public class Page_one extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_one);


    }


    public void skip(View view)
    {

        SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString(Save.color_back,"#FFFFFFF");
        edit.putString(Save.color_text,"color");
        edit.putString(Save.language,"language");
        edit.putString(Save.text_size,"sdewad");
        edit.putString(Save.share,"share");
        edit.putString(Save.cart,"5");
        edit.commit ();
        Intent intent = new Intent(Page_one.this, MainActivity.class);
        startActivity(intent);


    }

    public void next1(View view)
    {
        Intent intent = new Intent(Page_one.this, Page_two.class);
        startActivity(intent);

    }
}
