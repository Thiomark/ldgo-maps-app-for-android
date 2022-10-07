package com.example.ldgo.entities;

public class User {













    private String username; //"username": "joe",
    private String id; //"id": 4,
    private String email; // "email": "joe@gmail.com",
    private String provider; //"provider": "local",
    private Boolean confirmed; //"confirmed": true,
    private Boolean blocked; // "blocked": false,
    private String createdAt; //"createdAt": "2022-10-06T18:39:26.389Z",
    private String updatedAt; //"updatedAt": "2022-10-06T18:39:26.389Z",
    private String name; //"name": "Itumeleng Doe",
    private String language; //"language": "English",
    private Boolean useMetric; //"useMetric": true,
    private String landmark; //"landmark": "modern"
    private String jwt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getUseMetric() {
        return useMetric;
    }

    public void setUseMetric(Boolean useMetric) {
        this.useMetric = useMetric;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
