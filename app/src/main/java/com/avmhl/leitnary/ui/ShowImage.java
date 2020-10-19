package com.avmhl.leitnary.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.avmhl.leitnary.R;
import com.avmhl.leitnary.helpclass.Plate;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ShowImage extends AppCompatActivity {

    ImageView img_show;
    Button btn_delete_img;
    int onclicked=0;
    int number=7;
    AddCard addCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        init();
        gettingIntent();

    }

    private void init(){

        img_show=findViewById(R.id.img_show);
        btn_delete_img=findViewById(R.id.btn_delete_img);
       btn_delete_img.animate().alpha(1f).setDuration(1000);
       addCard=new AddCard();

    }

    private void gettingIntent(){

        Intent intent=getIntent();
        String path=intent.getStringExtra("path");
        Bitmap bitmap= BitmapFactory.decodeFile(path);
      // img_show.setImageBitmap(bitmap);
        File file=new File(path);
        Picasso.get().load(file).into(img_show);


        Intent intent1=getIntent();
        number=intent1.getIntExtra("number",0);
        Toast.makeText(ShowImage.this,path,Toast.LENGTH_LONG).show();



    }


    public void ImageClick(View view) {

        if(onclicked==0){
            btn_delete_img.animate().alpha(0f).setDuration(1000);
            onclicked=1;
        }else if(onclicked==1){
            btn_delete_img.animate().alpha(1f).setDuration(1000);
            onclicked=0;
        }

    }

    public void deleteClick(View view) {

      //  Intent intent=new Intent(ShowImage.this,AddCard.class);
//        intent.putExtra("number",number);
     //   startActivity(intent);


        Plate.number=number;
        finish();

    }

    @Override
    public void onBackPressed() {
        Plate.number=7;
        finish();
        super.onBackPressed();

    }
}