package com.avmhl.leitnary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "leitnary";
    static final String TABLE_NAME = "cards";
    static final int DATA_BASE_VERSION = 1;
    static final String FIELD_ID = "id";
    static final String FIELD_ORDER = "orders";
    static final String FIELD_GROUP = "groups";
    static final String QUESTION_TEXT = "questext";
    static final String QUESTION_IMG_1 = "questimg1";
    static final String QUESTION_IMG_2 = "questimg2";
    static final String QUESTION_IMG_3 = "questimg3";
    static final String QUESTION_VOICE = "questvoice";
    static final String ANSWER_TEXT = "anstext";
    static final String ANSWER_IMG_1 = "ansimg1";
    static final String ANSWER_IMG_2 = "ansimg2";
    static final String ANSWER_IMG_3 = "ansimg3";
    static final String ANSWER_VOICE = "ansvoice";


    static final String FIELD_ISIMAGE = "isimage";





    public BaseDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        creattable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void creattable(SQLiteDatabase db) {


        String query = "create table if not exists " + TABLE_NAME + " ( " + FIELD_ID + " integer primary key , " + FIELD_GROUP + " varchar(60), " + FIELD_ORDER + " integer , " + QUESTION_TEXT + " varchar(800) , " + QUESTION_IMG_1 + " varchar(100) , " + QUESTION_IMG_2 + " varchar(100) , " + QUESTION_IMG_3 + " varchar(100) , " + QUESTION_VOICE + " varchar(100) , " + ANSWER_TEXT + " varchar(800) , " + ANSWER_IMG_1 + " varchar(100) , " + ANSWER_IMG_2 + " varchar(100) , " + ANSWER_IMG_3 + " varchar(100) , "  + ANSWER_VOICE + " varchar(100) )";
        db.execSQL(query);
    }



}
