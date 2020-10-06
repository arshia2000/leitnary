package com.avmhl.leitnary.entity;

public class Card {

    private int id;
    private int order;
    private String group;
    private String qustion_text;
    private String qustion_image1;
    private String qustion_image2;
    private String qustion_image3;
    private String qustion_voice;
    private String answer_text;
    private String answer_img1;
    private String answer_img2;
    private String answer_img3;
    private String answer_voice;


    public Card( String group, int order, String qustion_text, String qustion_image1, String qustion_image2, String qustion_image3, String qustion_voice, String answer_text, String answer_img1, String answer_img2, String answer_img3, String answer_voice) {

        this.order = order;
        this.group = group;
        this.qustion_text = qustion_text;
        this.qustion_image1 = qustion_image1;
        this.qustion_image2 = qustion_image2;
        this.qustion_image3 = qustion_image3;
        this.qustion_voice = qustion_voice;
        this.answer_text = answer_text;
        this.answer_img1 = answer_img1;
        this.answer_img2 = answer_img2;
        this.answer_img3 = answer_img3;
        this.answer_voice = answer_voice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getQustion_text() {
        return qustion_text;
    }

    public void setQustion_text(String qustion_text) {
        this.qustion_text = qustion_text;
    }

    public String getQustion_image1() {
        return qustion_image1;
    }

    public void setQustion_image1(String qustion_image1) {
        this.qustion_image1 = qustion_image1;
    }

    public String getQustion_image2() {
        return qustion_image2;
    }

    public void setQustion_image2(String qustion_image2) {
        this.qustion_image2 = qustion_image2;
    }

    public String getQustion_image3() {
        return qustion_image3;
    }

    public void setQustion_image3(String qustion_image3) {
        this.qustion_image3 = qustion_image3;
    }

    public String getQustion_voice() {
        return qustion_voice;
    }

    public void setQustion_voice(String qustion_voice) {
        this.qustion_voice = qustion_voice;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public String getAnswer_img1() {
        return answer_img1;
    }

    public void setAnswer_img1(String answer_img1) {
        this.answer_img1 = answer_img1;
    }

    public String getAnswer_img2() {
        return answer_img2;
    }

    public void setAnswer_img2(String answer_img2) {
        this.answer_img2 = answer_img2;
    }

    public String getAnswer_img3() {
        return answer_img3;
    }

    public void setAnswer_img3(String answer_img3) {
        this.answer_img3 = answer_img3;
    }

    public String getAnswer_voice() {
        return answer_voice;
    }

    public void setAnswer_voice(String answer_voice) {
        this.answer_voice = answer_voice;
    }
}
