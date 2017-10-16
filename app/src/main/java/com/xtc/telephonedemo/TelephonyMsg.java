package com.xtc.telephonedemo;

/**
 * Created by ouyangfan on 2017/8/16.
 */

public class TelephonyMsg {

    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TelephonyMsg{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
