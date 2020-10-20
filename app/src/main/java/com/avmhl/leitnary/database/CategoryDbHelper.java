package com.avmhl.leitnary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.avmhl.leitnary.entity.Card;
import com.avmhl.leitnary.entity.Category;

import java.util.ArrayList;

public class CategoryDbHelper extends BaseDbHelper{


    public CategoryDbHelper(@Nullable Context context) {
        super(context);
    }



    public long insert(String cat,int color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY, cat);
        cv.put(CATEGORY_COLOR,color);


        long row = db.insert(TABLE_NAME_CATEGORY, null, cv);
        Log.e(BaseDbHelper.class.getSimpleName(), "insert");
        db.close();
        cv.clear();
        return row;


    }


    public ArrayList<Category> selectall() {


        String categoryname;
        int colorcat;
        ArrayList<Category> categories=new ArrayList<Category>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_CATEGORY, new String[]{FIELD_ID, CATEGORY,CATEGORY_COLOR}, null, null, null, null, null);
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                categoryname = cursor.getString(1);
                colorcat=cursor.getInt(2);

                Category category=new Category(categoryname,colorcat);
                categories.add(category);


            }
        }
        Log.e(BaseDbHelper.class.getSimpleName(), "select all");
        db.close();
        cursor.close();

        return categories;
    }



}
