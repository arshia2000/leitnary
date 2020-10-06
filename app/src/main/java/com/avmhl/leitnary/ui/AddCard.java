package com.avmhl.leitnary.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.avmhl.leitnary.MainActivity;
import com.avmhl.leitnary.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AddCard extends AppCompatActivity {


    ImageView photoImageView;
    Button takePicButton,savePic,loadPic;
    String ImagName="";
    OutputStream outputStream;

    Bitmap captureImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

          init();
          takePermision();











    }

    private void init(){

        photoImageView=findViewById(R.id.img_take_pic);
        takePicButton=findViewById(R.id.btn_take_pic);
        savePic=findViewById(R.id.btn_save_pic);
        loadPic=findViewById(R.id.btn_load_pic);
    }

    public void takePicClick(View view) {

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       startActivityForResult(intent,100);



    }


    private void takePermision(){
        if(ContextCompat.checkSelfPermission(AddCard.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddCard.this,new String[]{Manifest.permission.CAMERA},100);
        }

        if(ContextCompat.checkSelfPermission(AddCard.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AddCard.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        captureImage= (Bitmap) data.getExtras().get("data");
       // photoImageView.setImageBitmap(captureImage);



    }


    public void takesaveClick(View view) {

       // BitmapDrawable bitmapDrawable=(BitmapDrawable)photoImageView.getDrawable();
       // Bitmap bitmap=bitmapDrawable.getBitmap();

        File filepath=Environment.getExternalStorageDirectory();
        File dir=new File(filepath.getAbsolutePath()+"/leitnary/");
        dir.mkdir();
        File dir2=new File(dir.getAbsolutePath()+"/card/");
        dir2.mkdir();
        ImagName=String.valueOf(System.currentTimeMillis())+".jpg";
        File file=new File(dir2,ImagName);

        try {
            outputStream = new FileOutputStream(file);

            captureImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            outputStream.flush();
            outputStream.close();

        }catch (Exception e){

            Toast.makeText(AddCard.this,"Error",Toast.LENGTH_LONG).show();
        }

    }

    public void takeloadClick(View view) {

        File filepath=Environment.getExternalStorageDirectory();
        File dir=new File(filepath.getAbsolutePath()+"/leitnary/");
        File dir2=new File(dir.getAbsolutePath()+"/card/");
        File file=new File(dir2,ImagName);

        Picasso.get().load(file).into(photoImageView);
    }
}