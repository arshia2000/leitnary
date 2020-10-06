package com.avmhl.leitnary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.avmhl.leitnary.entity.Card;

public class CardsDbHelper extends BaseDbHelper {



    public CardsDbHelper(@Nullable Context context) {
        super(context);
    }


    public long insert(Card product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
       // cv.put(FIELD_ID, product.getId());
        cv.put(FIELD_ORDER, product.getOrder());
        cv.put(FIELD_GROUP, product.getGroup());
        cv.put(QUESTION_TEXT, product.getQustion_text());
        cv.put(QUESTION_IMG_1, product.getQustion_image1());
        cv.put(QUESTION_IMG_2, product.getQustion_image2());
        cv.put(QUESTION_IMG_3, product.getAnswer_img3());
        cv.put(QUESTION_VOICE, product.getQustion_voice());
        cv.put(ANSWER_TEXT, product.getAnswer_text());
        cv.put(ANSWER_IMG_1, product.getAnswer_img1());
        cv.put(ANSWER_IMG_2, product.getAnswer_img2());
        cv.put(ANSWER_IMG_3, product.getAnswer_img3());
        cv.put(ANSWER_VOICE, product.getAnswer_voice());

        long row = db.insert(TABLE_NAME, null, cv);
        Log.e(BaseDbHelper.class.getSimpleName(), "insert");
        db.close();
        cv.clear();
        return row;


    }



    public Card selectByid(int id) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{FIELD_ID, FIELD_GROUP, FIELD_ORDER, QUESTION_TEXT, QUESTION_IMG_1, QUESTION_IMG_2, QUESTION_IMG_3,QUESTION_VOICE, ANSWER_TEXT, ANSWER_IMG_1, ANSWER_IMG_2,ANSWER_IMG_3,ANSWER_VOICE}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                String group = cursor.getString(1);
                int order = cursor.getInt(2);
                String qusestext = cursor.getString(3);
                String qustimg1 = cursor.getString(4);
                String questimg2 = cursor.getString(5);
                String questimg3  = cursor.getString(6);
                String questvoice = cursor.getString(7);
                String answertext = cursor.getString(8);
                String answerimg1 = cursor.getString(9);
                String answerimg2 = cursor.getString(10);
                String answerimg3 = cursor.getString(11);
                String answervoice = cursor.getString(12);


               Card card = new Card(group,order,qusestext, qustimg1, questimg2, questimg3, questvoice,answertext,answerimg1,answerimg2,answerimg3,answervoice);

                return card;

            }
        }

        db.close();
        cursor.close();
        return null;
    }






}
