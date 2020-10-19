package com.avmhl.leitnary.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avmhl.leitnary.MainActivity;
import com.avmhl.leitnary.R;
import com.avmhl.leitnary.database.BaseDbHelper;
import com.avmhl.leitnary.database.CardsDbHelper;
import com.avmhl.leitnary.entity.Card;
import com.avmhl.leitnary.helpclass.Plate;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class AddCard extends AppCompatActivity {


    ImageView photoImageView, img1_ans, img2_ans, img3_ans, img_voice_ans, img1_qu, img2_qu, img3_qu, img_voice_qu;
    EditText et_textEditor_qu, et_textEditor_ans;
    TextView tv_question_ask, tv_error;
    Button takePicButton, savePic, loadPic, btn_save;
    Chronometer chronometer_qu, chronometer_ans;
    MediaRecorder myRecorder;
    File dir, dir1, dir2, dir3;
    int record_state = 0;
    MediaPlayer myPlayer;
    Chronometer chorqu,chorans;
    int duration;

    OutputStream outputStream;

    String[] imgNames;
    String voiceName_qu = "", voiceName_ans = "", ImagName = "", groupName = "", errotext = "";


    int cardState = 0, number = 7;

    boolean error = false;

  //  Bitmap captureImage;
    CardsDbHelper cardsDbHelper;
    String currentImagepath;
    Bitmap bitmap;
    File file;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        init();
        takePermision();
        //tv_question_ask.setTextSize(18);
        //tv_question_ask.setText(makeCardAddress(1,"salam.jpg"));
        //  btn_save.seton

    }

    @Override
    protected void onResume() {
        super.onResume();


        gettingIntent();


    }

    private void init() {

//        photoImageView=findViewById(R.id.img_take_pic);
//        takePicButton=findViewById(R.id.btn_take_pic);
//        savePic=findViewById(R.id.btn_save_pic);
//        loadPic=findViewById(R.id.btn_load_pic);

        initUi();
        cardsDbHelper = new CardsDbHelper(AddCard.this);

        imgNames = new String[]{"", "", "","","",""};



    }


    private void initUi() {
        img1_ans = findViewById(R.id.img1_ans);
        img2_ans = findViewById(R.id.img2_ans);
        img3_ans = findViewById(R.id.img3_ans);
        img_voice_ans = findViewById(R.id.im_voice_ans);

        img1_qu = findViewById(R.id.img1_qu);
        img2_qu = findViewById(R.id.img2_qu);
        img3_qu = findViewById(R.id.img3_qu);
        img_voice_qu = findViewById(R.id.im_voice_qu);

        et_textEditor_qu = findViewById(R.id.et_text_editor);
        et_textEditor_ans = findViewById(R.id.et_text_editor_ans);

        tv_question_ask = findViewById(R.id.tv_qu_ask);
        tv_error = findViewById(R.id.tv_error);
        btn_save = findViewById(R.id.btn_save);
        chorans=findViewById(R.id.chor_voice_ans);
        chorqu=findViewById(R.id.chor_voice_qu);

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

        if (ContextCompat.checkSelfPermission(AddCard.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddCard.this, new String[]{Manifest.permission.RECORD_AUDIO}, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
           // captureImage = (Bitmap) data.getExtras().get("data");

            switch (requestCode) {
                case 100:
                     file=new File(currentImagepath);
                    Picasso.get().load(file).into(img1_qu);
                    imgNames[0]=currentImagepath;

                    break;

                case 200:
                     file=new File(currentImagepath);
                    Picasso.get().load(file).into(img2_qu);
                    imgNames[1]=currentImagepath;
                    break;

                case 300:
                    file=new File(currentImagepath);
                    Picasso.get().load(file).into(img3_qu);
                    imgNames[2]=currentImagepath;
                    break;

                case 400:
                    file=new File(currentImagepath);
                    Picasso.get().load(file).into(img1_ans);
                    imgNames[3]=currentImagepath;
                    break;

                case 500:
                    file=new File(currentImagepath);
                    Picasso.get().load(file).into(img2_ans);
                    imgNames[4]=currentImagepath;
                    break;

                case 600:
                    file=new File(currentImagepath);
                    Picasso.get().load(file).into(img3_ans);
                    imgNames[5]=currentImagepath;
                    break;
            }
        } else {


        }

    }


    private File makeCardAddress() {


        File filepath = Environment.getExternalStorageDirectory();
        dir = new File(filepath.getAbsolutePath() + "/leitnary/");
        if (!dir.exists()) {
            dir.mkdir();
        }

//             dir2 = new File(dir.getAbsolutePath() + "/card " + String.valueOf(id) + "/");
//            if (!dir2.exists()) {
//                dir2.mkdir();
//            }
        dir3 = new File(dir.getAbsolutePath() + "/images/");
        if (!dir3.exists()) {
            dir3.mkdir();
        }

      //  ImagName = filename;
        File file = new File(dir3, ImagName);

//        try {
//            outputStream = new FileOutputStream(file);
//
//            captureImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//
//            outputStream.flush();
//            outputStream.close();
//
//        } catch (Exception e) {
//
//            Toast.makeText(AddCard.this, "Error", Toast.LENGTH_LONG).show();
//        }
        //String address = dir3.getAbsolutePath();
        return dir3;

    }

    private String makevoiceAddress(int id, String filename) {

        File filepath = Environment.getExternalStorageDirectory();
        dir = new File(filepath.getAbsolutePath() + "/leitnary/");
        if (!dir.exists()) {
            dir.mkdir();
        }


        dir3 = new File(dir.getAbsolutePath() + "/voice/");
        if (!dir3.exists()) {
            dir3.mkdir();
        }

        ImagName = filename;
        File file = new File(dir3, ImagName);


//      try {
//          outputStream = new FileOutputStream(file);
//
//          captureImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//
//          outputStream.flush();
//          outputStream.close();
//
//      } catch (Exception e) {
//
//          Toast.makeText(AddCard.this, "Error", Toast.LENGTH_LONG).show();
//      }
        String address = file.getAbsolutePath();
        return address;

    }


    public void takesaveClick(View view) {

        // BitmapDrawable bitmapDrawable=(BitmapDrawable)photoImageView.getDrawable();
        // Bitmap bitmap=bitmapDrawable.getBitmap();


    }

    public void takeloadClick(View view) {

        File filepath = Environment.getExternalStorageDirectory();
        File dir = new File(filepath.getAbsolutePath() + "/leitnary/");
        File dir2 = new File(dir.getAbsolutePath() + "/card/");
        File file = new File(dir2, ImagName);

        Picasso.get().load(file).into(photoImageView);
    }

    public void saveButtonClick(View view) {

        tv_error.setText("");

        if (cardState == 0) {

            if (et_textEditor_qu.getText().toString().length() > 800 || et_textEditor_ans.getText().toString().length() > 800) {
                error = true;
                errotext += " question or answer text is too long.\n";

            }
            if (TextUtils.isEmpty(et_textEditor_qu.getText().toString()) && TextUtils.isEmpty(imgNames[0]) && TextUtils.isEmpty(imgNames[1]) && TextUtils.isEmpty(imgNames[2]) && TextUtils.isEmpty(voiceName_qu)) {
                error = true;
                errotext += " you must fill one field at least in question section\n";
            }
            if (TextUtils.isEmpty(et_textEditor_ans.getText().toString()) && TextUtils.isEmpty(imgNames[3]) && TextUtils.isEmpty(imgNames[4]) && TextUtils.isEmpty(imgNames[5]) && TextUtils.isEmpty(voiceName_ans)) {
                error = true;
                errotext += " you must fill one field at least in answer section\n";
            }
            if (!error) {


            } else {
                tv_error.setText(errotext);
                error = false;
            }
        }

    }

    public void img1Qu(View view) {


        if (imgNames[0] =="") {

            imgNames[0] = String.valueOf(System.currentTimeMillis());
            File storageDirectory =makeCardAddress();
            try {
                File imagefile = File.createTempFile(imgNames[0], ".jpg", storageDirectory);
                currentImagepath = imagefile.getAbsolutePath();
                Toast.makeText(AddCard.this,currentImagepath,Toast.LENGTH_LONG).show();
                Uri imageuri = FileProvider.getUriForFile(AddCard.this, "com.avmhl.leitnary.fileprovider", imagefile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(intent, 100);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Intent intent = new Intent(AddCard.this, ShowImage.class);
            intent.putExtra("path", currentImagepath);
            intent.putExtra("number", 0);
            startActivity(intent);
        }


    }

    public void img2Qu(View view) {

        if (imgNames[1] =="") {

            imgNames[1] = String.valueOf(System.currentTimeMillis());
            File storageDirectory =makeCardAddress();
            try {
                File imagefile = File.createTempFile(imgNames[1], ".jpg", storageDirectory);
                currentImagepath = imagefile.getAbsolutePath();
                Uri imageuri = FileProvider.getUriForFile(AddCard.this, "com.avmhl.leitnary.fileprovider", imagefile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(intent, 200);
            } catch (IOException e) {
                e.printStackTrace();
            }



        } else {
            Intent intent = new Intent(AddCard.this, ShowImage.class);
            intent.putExtra("path", currentImagepath);
            intent.putExtra("number", 1);
            startActivity(intent);
        }
    }

    public void img3Qu(View view) {
        if (imgNames[2] =="") {

            imgNames[2] = String.valueOf(System.currentTimeMillis());
            File storageDirectory =makeCardAddress();
            try {
                File imagefile = File.createTempFile(imgNames[2], ".jpg", storageDirectory);
                currentImagepath = imagefile.getAbsolutePath();
                Uri imageuri = FileProvider.getUriForFile(AddCard.this, "com.avmhl.leitnary.fileprovider", imagefile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(intent, 300);
            } catch (IOException e) {
                e.printStackTrace();
            }



        } else {
            Intent intent = new Intent(AddCard.this, ShowImage.class);
            intent.putExtra("path", currentImagepath);
            intent.putExtra("number", 2);
            startActivity(intent);
        }
    }

    public void img1Ans(View view) {
        if (imgNames[3] =="") {

            imgNames[3] = String.valueOf(System.currentTimeMillis());
            File storageDirectory =makeCardAddress();
            try {
                File imagefile = File.createTempFile(imgNames[3], ".jpg", storageDirectory);
                currentImagepath = imagefile.getAbsolutePath();
                Uri imageuri = FileProvider.getUriForFile(AddCard.this, "com.avmhl.leitnary.fileprovider", imagefile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(intent, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }



        } else {
            Intent intent = new Intent(AddCard.this, ShowImage.class);
            intent.putExtra("path", currentImagepath);
            intent.putExtra("number", 3);
            startActivity(intent);
        }
    }

    public void img2Ans(View view) {
        if (imgNames[4] =="") {

            imgNames[4] = String.valueOf(System.currentTimeMillis());
            File storageDirectory =makeCardAddress();
            try {
                File imagefile = File.createTempFile(imgNames[4], ".jpg", storageDirectory);
                currentImagepath = imagefile.getAbsolutePath();
                Uri imageuri = FileProvider.getUriForFile(AddCard.this, "com.avmhl.leitnary.fileprovider", imagefile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(intent, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }



        } else {
            Intent intent = new Intent(AddCard.this, ShowImage.class);
            intent.putExtra("path", currentImagepath);
            intent.putExtra("number", 4);
            startActivity(intent);
        }
    }

    public void img3Ans(View view) {
        if (imgNames[5] =="") {

            imgNames[5] = String.valueOf(System.currentTimeMillis());
            File storageDirectory =makeCardAddress();
            try {
                File imagefile = File.createTempFile(imgNames[5], ".jpg", storageDirectory);
                currentImagepath = imagefile.getAbsolutePath();
                Uri imageuri = FileProvider.getUriForFile(AddCard.this, "com.avmhl.leitnary.fileprovider", imagefile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(intent, 600);
            } catch (IOException e) {
                e.printStackTrace();
            }



        } else {
            Intent intent = new Intent(AddCard.this, ShowImage.class);
            intent.putExtra("path", currentImagepath);
            intent.putExtra("number", 1);
            startActivity(intent);
        }
    }

    private void gettingIntent() {


        if (Plate.number != 7) {


            switch (Plate.number) {

                case 0:
                    img1_qu.setImageResource(R.drawable.addpicture);
                    file=new File(imgNames[0]);
                    file.delete();
                    imgNames[0] = "";

                    break;

                case 1:

                    img2_qu.setImageResource(R.drawable.addpicture);
                    file=new File(imgNames[1]);
                    file.delete();
                    imgNames[1] = "";
                    break;

                case 2:

                    img3_qu.setImageResource(R.drawable.addpicture);
                    file=new File(imgNames[2]);
                    file.delete();
                    imgNames[2] = "";
                    break;

                case 3:

                    img1_ans.setImageResource(R.drawable.addpicture);
                    file=new File(imgNames[3]);
                    file.delete();
                    imgNames[3] = "";
                    break;

                case 4:

                    img2_ans.setImageResource(R.drawable.addpicture);
                    file=new File(imgNames[4]);
                    file.delete();
                    imgNames[4] = "";
                    break;

                case 5:

                    img3_ans.setImageResource(R.drawable.addpicture);
                    file=new File(imgNames[5]);
                    file.delete();
                    imgNames[5] = "";
                    break;


            }
        }

    }

    private void recorderInit(String outputFile) {
        myRecorder = new MediaRecorder();
        myRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        myRecorder.setAudioChannels(1);
        myRecorder.setAudioEncodingBitRate(128000);
        myRecorder.setAudioSamplingRate(44100);
        //  myRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myRecorder.setOutputFile(outputFile);
    }

    private void startRecorder() {
        try {
            myRecorder.prepare();
            myRecorder.start();



        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void recorderFinish() {
        try {


            myRecorder.stop();
            myRecorder.release();
            myRecorder = null;
        } catch (Exception e) {

        }
    }


    public void recorAns(View view) {

        if (record_state == 0) {
            voiceName_ans = String.valueOf(System.currentTimeMillis()) + ".3gpp";
            String address = makevoiceAddress(1, voiceName_ans);
            recorderInit(address);
            startRecorder();
            img_voice_ans.setImageResource(R.drawable.stopbutton);
            record_state = 1;
            chorans.start();

        } else if (record_state == 1) {

            recorderFinish();
            img_voice_ans.setImageResource(R.drawable.play);
            record_state = 2;
            chorans.stop();

        } else if (record_state == 2) {

            String file = makevoiceAddress(1, voiceName_ans);
            play(file);
            duration=myPlayer.getDuration();
            chorans.setBase(SystemClock.elapsedRealtime());
            chorans.start();
            new CountDownTimer(duration,1000){
                @Override
                public void onTick(long millisUntilFinished){}

                @Override
                public void onFinish(){

                    if(myPlayer!=null) {
                        myPlayer.stop();
                        myPlayer.release();
                        myPlayer = null;
                        img_voice_ans.setImageResource(R.drawable.play);
                        record_state = 2;
                        chorans.stop();
                        chorans.setBase(SystemClock.elapsedRealtime());
                    }

                }
            }.start();
          //  Toast.makeText(AddCard.this,String.valueOf(duration),Toast.LENGTH_SHORT).show();
            img_voice_ans.setImageResource(R.drawable.pause);
            record_state = 3;

        } else if (record_state == 3) {

            stop();
            chorans.stop();
            chorans.setBase(SystemClock.elapsedRealtime());
            img_voice_ans.setImageResource(R.drawable.play);
            record_state = 2;

        }

    }

    public void recordQu(View view) {

        // recorderInit();
        startRecorder();
        img_voice_ans.setImageResource(R.drawable.stopbutton);
    }

    private void play(String outputFile) {

        try {
            myPlayer = new MediaPlayer();
            myPlayer.setDataSource(outputFile);
            myPlayer.prepare();
            myPlayer.start();


            //Toast.makeText(AddCard.this,String.valueOf(duration),Toast.LENGTH_SHORT).show();





        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void stop() {
        try {
            if (myPlayer != null) {
                myPlayer.stop();
                myPlayer.release();
                myPlayer = null;
                record_state=2;



            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}