package com.avmhl.leitnary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.avmhl.leitnary.database.CardsDbHelper;

import com.avmhl.leitnary.menu.add;
import com.avmhl.leitnary.menu.other;
import com.avmhl.leitnary.menu.review;
import com.avmhl.leitnary.sharedPreferences.Save;
import com.avmhl.leitnary.start.Loading_splash;
import com.avmhl.leitnary.ui.AddCard;
import com.avmhl.leitnary.ui.Setting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView bottomNavigationView;

    @Override



    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottonNavMetod);
getSupportFragmentManager().beginTransaction().replace(R.id.container,new review()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottonNavMetod= new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment=null;

                   switch (menuItem.getItemId())
                   {
                       case R.id.review:
                           fragment=new review();
                           break;
                       case R.id.add:
                           fragment=new add();
                           break;
                       case R.id.other:
                           fragment=new other();
                           break;
                   }
                   getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    return true;
                }
            };
}
