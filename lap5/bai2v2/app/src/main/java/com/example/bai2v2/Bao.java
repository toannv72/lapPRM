package com.example.bai2v2;

public class Bao {
    String title;
    String destile;
    String demuc;
    String img;

    public Bao(String title, String destile, String demuc, String img) {
        this.title = title;
        this.destile = destile;
        this.demuc = demuc;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestile() {
        return destile;
    }

    public void setDestile(String destile) {
        this.destile = destile;
    }

    public String getDemuc() {
        return demuc;
    }

    public void setDemuc(String demuc) {
        this.demuc = demuc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

