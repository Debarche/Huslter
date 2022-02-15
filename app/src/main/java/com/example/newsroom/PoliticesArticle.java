package com.example.newsroom;

import android.widget.EditText;

public class PoliticesArticle {
    private String PoliticsTitle;
    private String PoliticsContent;

    public PoliticesArticle(){}

    public String getPoliticsTitle() {
        return PoliticsTitle;
    }

    public void setPoliticsTitle(String politicsTitle) {
        PoliticsTitle = politicsTitle;
    }



    public String getPoliticsContent() {
        return PoliticsContent;
    }

    public void setPoliticsContent(String politicsContent) {
        PoliticsContent = politicsContent;
    }



}
