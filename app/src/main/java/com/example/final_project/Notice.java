package com.example.final_project;

public class Notice {
    String upload_date;
    String kind;
    String num;

    public Notice(String upload_date, String kind, String num) {
        this.upload_date = upload_date;
        this.kind = kind;
        this.num = num;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
