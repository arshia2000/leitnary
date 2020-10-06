package com.avmhl.leitnary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.avmhl.leitnary.database.CardsDbHelper;
import com.avmhl.leitnary.entity.Card;
import com.avmhl.leitnary.ui.AddCard;

public class MainActivity extends AppCompatActivity {


    Button addCardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     init();


    }

    private void init(){
        addCardButton=findViewById(R.id.btn_addcard);
        CardsDbHelper cardsDbHelper=new CardsDbHelper(MainActivity.this);
       // cardsDbHelper.insert(new Card(0,"",""," ","fjfj","fjf","fhfj","","","","",""));
        Toast.makeText(MainActivity.this,"job done",Toast.LENGTH_LONG).show();
    }

    public void addCardClick(View view) {
        Intent intent=new Intent(MainActivity.this, AddCard.class);
        startActivity(intent);
    }
}