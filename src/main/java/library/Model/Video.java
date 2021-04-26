package library.Model;

import javax.validation.constraints.NotEmpty;

public class Video {
    private int id;
    private int time;
    @NotEmpty(message = "select name")
    private String name;
    @NotEmpty(message = "select link")
    private String link;
    @NotEmpty(message = "select issue")
    private String issue;

    public Video( int time, String name, String link, String issue) {
        this.time = time;
        this.name = name;
        this.link = link;
        this.issue = issue;
    }

    public Video() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
