package com.hellobbs.database;

public class Userwith {
    private int id;
    private String username;
    private String password;
    private String userpics;
    private String messages;
    private String title;
    private String context;
    private String createdtime;
    private String changetime;
    private String user;
    private String userroles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserpics() {
        return userpics;
    }

    public void setUserpics(String userpics) {
        this.userpics = userpics;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getChangetime() {
        return changetime;
    }

    public void setChangetime(String changetime) {
        this.changetime = changetime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserroles() {
        return userroles;
    }

    public void setUserroles(String userroles) {
        this.userroles = userroles;
    }

    @Override
    public String toString() {
        return "Userwith{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userpics='" + userpics + '\'' +
                ", messages='" + messages + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", createdtime='" + createdtime + '\'' +
                ", changetime='" + changetime + '\'' +
                ", user='" + user + '\'' +
                ", userroles='" + userroles + '\'' +
                '}';
    }
}
