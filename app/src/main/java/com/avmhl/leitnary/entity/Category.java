package com.avmhl.leitnary.entity;

import android.system.StructTimespec;

public class Category {

    private String category;
    private int categorycolor;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategorycolor() {
        return categorycolor;
    }

    public void setCategorycolor(int categorycolor) {
        this.categorycolor = categorycolor;
    }

    public Category(String category, int categorycolor) {
        this.category = category;
        this.categorycolor = categorycolor;
    }
}
