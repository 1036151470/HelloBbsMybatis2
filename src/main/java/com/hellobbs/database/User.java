package com.hellobbs.database;

public class User {
    private int id;
    private String username;
    private String password;
    private String userpics;
    private String messages;
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

    public String getUserroles() {
        return userroles;
    }

    public void setUserroles(String userroles) {
        this.userroles = userroles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userpics='" + userpics + '\'' +
                ", messages='" + messages + '\'' +
                ", userroles='" + userroles + '\'' +
                '}';
    }
}
