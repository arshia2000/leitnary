package com.avmhl.leitnary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.avmhl.leitnary.database.CardsDbHelper;
import com.avmhl.leitnary.information.Page_one;
import com.avmhl.leitnary.sharedPreferences.Save;
import com.avmhl.leitnary.ui.AddCard;
import com.avmhl.leitnary.ui.Setting;

public class MainActivity extends AppCompatActivity {



    Button addCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    private void init() {
        addCardButton = findViewById(R.id.btn_addcard);
        CardsDbHelper cardsDbHelper = new CardsDbHelper(MainActivity.this);
        // cardsDbHelper.insert(new Card(0,"",""," ","fjfj","fjf","fhfj","","","","",""));

        Toast.makeText(MainActivity.this, "job done", Toast.LENGTH_LONG).show();
    }

    public void addCardClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddCard.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.order_badge: {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
                break;
            }

        }
            return super.onOptionsItemSelected(item);
        }


    public void ds(View view)
    {
        Intent intent = new Intent(MainActivity.this, Page_one.class);
        startActivity(intent);
    }
}
