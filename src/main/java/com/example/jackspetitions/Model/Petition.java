package com.example.jackspetitions.Model;

import java.io.Serializable;

public class Petition implements Serializable {
    private String title;
    private String description;
    private String author;

    private long signatureCount = 0;





    public Petition() {
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }


    public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSignatureCount() {
        return signatureCount;
    }

    public void setSignatureCount(long signatureCount) {
        this.signatureCount = signatureCount;
    }
}
