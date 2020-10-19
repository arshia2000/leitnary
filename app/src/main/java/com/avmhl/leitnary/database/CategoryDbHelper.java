package com.avmhl.leitnary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.avmhl.leitnary.entity.Card;

public class CategoryDbHelper extends BaseDbHelper{


    public CategoryDbHelper(@Nullable Context context) {
        super(context);
    }



    public long insert(String cat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY, cat);

        long row = db.insert(TABLE_NAME_CATEGORY, null, cv);
        Log.e(BaseDbHelper.class.getSimpleName(), "insert");
        db.close();
        cv.clear();
        return row;


    }


    public Card selectByid(int id) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_Card, new String[]{FIELD_ID,CATEGORY}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
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
