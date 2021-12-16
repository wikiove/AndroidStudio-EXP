package com.example.diary;

public class Note {
    private long id;//主键
    private String content;//笔记内容
    private String time;//笔记时间
    private String title;//笔记标题
    private String author;//作者
    private int tog;//笔记标签

    public Note() { }

    public Note(String content, String time, String title, String author, int tog) {
        this.content = content;
        this.time = time;
        this.title = title;
        this.author = author;
        this.tog = tog;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTog() {
        return tog;
    }

    public void setTog(int tog) {
        this.tog = tog;
    }

}
