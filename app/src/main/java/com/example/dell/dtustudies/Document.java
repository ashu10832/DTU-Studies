package com.example.dell.dtustudies;

/**
 * Created by Dell on 16-Dec-16.
 */

public class Document {
    private String subject;
    private String type;
    private String url;
    private String title;
    private String description;

    public Document(String subject,String type,String url,String title,String description)
    {
        this.subject = subject;
        this.title = title;
        this.type = type;
        this.description = description;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
