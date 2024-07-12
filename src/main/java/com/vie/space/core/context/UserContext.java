package com.vie.space.core.context;

public class UserContext {
    private Long id;
    private String username;
    private String session;

    public UserContext() {}
    
    public UserContext(Long id, String username, String session) {
        this.id = id;
        this.username = username;
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSession() {
        return session;
    }
}
