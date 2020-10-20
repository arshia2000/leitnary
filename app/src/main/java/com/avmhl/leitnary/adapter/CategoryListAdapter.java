package com.avmhl.leitnary.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.avmhl.leitnary.R;
import com.avmhl.leitnary.database.CategoryDbHelper;
import com.avmhl.leitnary.entity.Category;
import com.avmhl.leitnary.ui.AddCard;

import java.util.ArrayList;

public class CategoryListAdapter extends BaseAdapter {


    Context context;
    ArrayList<Category> categories=new ArrayList<>();

    CardView cardView;
    TextView tv_custom_list;
    ImageView img_custom_list;
    RelativeLayout re_custom_list;
    AddCard addCard;




    public CategoryListAdapter(Context context, ArrayList<Category> category) {
        this.context = context;
        this.categories = category;
        addCard=new AddCard();


    }


    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {



                view = LayoutInflater.from(context).inflate(R.layout.custom_category_list, null);
        }

        final Category category=categories.get(position);

        cardView=view.findViewById(R.id.cd_custom_list);
        tv_custom_list=view.findViewById(R.id.tv_custom_list);
        img_custom_list=view.findViewById(R.id.img_custom_list_category);
        //re_custom_list=view.findViewById(R.id.re_custom_list);


      img_custom_list.setBackgroundColor(category.getCategorycolor());
        tv_custom_list.setText(category.getCategory());




        return view;
    }
}
