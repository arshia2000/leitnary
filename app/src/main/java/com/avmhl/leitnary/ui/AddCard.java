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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avmhl.leitnary.MainActivity;
import com.avmhl.leitnary.R;
import com.avmhl.leitnary.database.BaseDbHelper;
import com.avmhl.leitnary.database.CardsDbHelper;
import com.avmhl.leitnary.entity.Card;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AddCard extends AppCompatActivity {


    ImageView photoImageView, img_addpic, img_addvoice;
    EditText et_textEditor;
    TextView tv_question_ask;
    Button takePicButton, savePic, loadPic, btn_save;

    OutputStream outputStream;

    String[] imgNames;
    String voiceName="", ImagName = "",groupName="";


    int cardState = 0;

    boolean error = false;

    Bitmap captureImage;

    CardsDbHelper cardsDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        init();
        takePermision();


    }

    private void init() {

//        photoImageView=findViewById(R.id.img_take_pic);
//        takePicButton=findViewById(R.id.btn_take_pic);
//        savePic=findViewById(R.id.btn_save_pic);
//        loadPic=findViewById(R.id.btn_load_pic);


        cardsDbHelper= new CardsDbHelper(AddCard.this);
        img_addpic = findViewById(R.id.img_take_pic);
        img_addvoice = findViewById(R.id.img_record_voice);
        et_textEditor = findViewById(R.id.et_text_editor);
        tv_question_ask = findViewById(R.id.tv_qu_ask);
        btn_save = findViewById(R.id.btn_save);
        imgNames = new String []{"","",""};



    }

    public void takePicClick(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);


    }


    private void takePermision() {
        if (ContextCompat.checkSelfPermission(AddCard.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddCard.this, new String[]{Manifest.permission.CAMERA}, 100);
        }

        if (ContextCompat.checkSelfPermission(AddCard.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddCard.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        captureImage = (Bitmap) data.getExtras().get("data");
        // photoImageView.setImageBitmap(captureImage);


    }


    public void takesaveClick(View view) {

        // BitmapDrawable bitmapDrawable=(BitmapDrawable)photoImageView.getDrawable();
        // Bitmap bitmap=bitmapDrawable.getBitmap();

        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath() + "/leitnary/");
        dir.mkdir();
        File dir2 = new File(dir.getAbsolutePath() + "/card/");
        dir2.mkdir();
        ImagName = String.valueOf(System.currentTimeMillis()) + ".jpg";
        File file = new File(dir2, ImagName);

        try {
            outputStream = new FileOutputStream(file);

            captureImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {

            Toast.makeText(AddCard.this, "Error", Toast.LENGTH_LONG).show();
        }

    }

    public void takeloadClick(View view) {

        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath() + "/leitnary/");
        File dir2 = new File(dir.getAbsolutePath() + "/card/");
        File file = new File(dir2, ImagName);

        Picasso.get().load(file).into(photoImageView);
    }

    public void saveButtonClick(View view) {

        if (cardState == 0) {

            if (et_textEditor.getText().toString().length() > 800) {
                error = true;

            } else if (TextUtils.isEmpty(et_textEditor.getText().toString()) && TextUtils.isEmpty(imgNames[0]) && TextUtils.isEmpty(imgNames[1]) &&TextUtils.isEmpty(imgNames[2]) && TextUtils.isEmpty(voiceName)){
                error = true;
            }
            if (!error) {

                cardState=1;
               btn_save.setText("save card");
               tv_question_ask.setText("Answer");
               et_textEditor.setText("");
               et_textEditor.setHint(" Type answer...");

            }else {
                Toast.makeText(AddCard.this,"You must fill at least one field",Toast.LENGTH_LONG).show();
                error=false;
            }
        }

    }
}