package com.avmhl.leitnary.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.avmhl.leitnary.R;
import com.avmhl.leitnary.sharedPreferences.Save;

public class Setting extends AppCompatActivity {

    Button button1, button2, button3, button4, button5;
    SeekBar seekBar;
    TextView textView1, textView2, textView3;
    SharedPreferences sharedPreferences;

    String lanquage, cart;
    int carte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        inet();
        SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        cart = share.getString(Save.cart, "");
        textView3.setText(cart);


    }


    private void inet() {
        textView3 = findViewById(R.id.carteee);
        textView1 = findViewById(R.id.text_halat);
        textView2 = findViewById(R.id.text_font);
        button1 = findViewById(R.id.btn_fa);
        button2 = findViewById(R.id.btn_en);
        button5 = findViewById(R.id.btn_apply);
        seekBar = findViewById(R.id.seek);

    }


    public void en(View view) {
        lanquage = "english";
        SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString(Save.language, lanquage);
        edit.commit();

    }

    public void fa(View view) {

        lanquage = "persian";
        SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString(Save.language, lanquage);
        edit.commit();
    }


    public void mines(View view) {
        carte = Integer.parseInt(cart);
        if (carte == 1) {
            Toast.makeText(Setting.this, "کم تر نمیشود", Toast.LENGTH_SHORT).show();
        } else {
            carte = carte - 1;
            cart = String.valueOf(carte);
            String sdcart = cart;
            textView3.setText(cart);
            SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
            SharedPreferences.Editor edit = share.edit();
            edit.putString(Save.cart, sdcart);
            edit.commit();
        }

    }

    public void pluse(View view) {
        carte = Integer.parseInt(cart);
        if (carte == 10) {
            Toast.makeText(Setting.this, "بیش تر نمی شود ", Toast.LENGTH_SHORT).show();
        } else {
            carte = carte + 1;
            cart = String.valueOf(carte);
            textView3.setText(cart);
            SharedPreferences share = getSharedPreferences("share", MODE_PRIVATE);
            SharedPreferences.Editor edit = share.edit();
            edit.putString(Save.cart, cart);
            edit.commit();
        }


    }
}
