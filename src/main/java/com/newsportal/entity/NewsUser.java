package com.newsportal.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "news_user")
public class NewsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    public NewsUser() {
    }

    public NewsUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewsUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsUser newsUser = (NewsUser) o;
        return id == newsUser.id &&
                email.equals(newsUser.email) &&
                password.equals(newsUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }
}
