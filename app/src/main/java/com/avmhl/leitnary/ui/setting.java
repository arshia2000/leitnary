package com.avmhl.leitnary.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.avmhl.leitnary.R;
import com.avmhl.leitnary.sharedPreferences.Save;

public class setting extends AppCompatActivity {

    Button button1, button2, button3, button4, button5;
    SeekBar seekBar;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        inet();


    }



    private void inet() {

        textView1 = findViewById(R.id.text_halat);
        textView2 = findViewById(R.id.text_font);
        button1 = findViewById(R.id.btn_fa);
        button2 = findViewById(R.id.btn_en);
        button3 = findViewById(R.id.btn_darck);
        button4 = findViewById(R.id.btn_whait);
        button5 = findViewById(R.id.btn_apply);
        seekBar = findViewById(R.id.seek);

    }


    public void en(View view) {
    }
    public void fa(View view) {
    }

    public void aplly(View view)
    {
        SharedPreferences share = getSharedPreferences ( "share",MODE_PRIVATE );
        SharedPreferences.Editor edit = share.edit ();
        edit.putString ( Save.color_text,"#000000" );
        edit.putString ( Save.color_back,"#FFFFFF" );
        edit.putString ( Save.text_size,"0");
        edit.putString(Save.language,"persian");
        edit.commit ();


    }

}
