package com.newsportal.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name="news_admin")
public class NewsAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Login is mandatory")
    @Column(name = "login")
    private String login;

    @NotBlank(message = "email is mandatory")
    @Column(name="email")
    private String email;

    @NotBlank(message = " password is mandatory")
    @Column(name = "password")
    private String password;

    public NewsAdmin() {
    }

    public NewsAdmin(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        return "NewsAdmin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsAdmin newsAdmin = (NewsAdmin) o;
        return id == newsAdmin.id &&
                login.equals(newsAdmin.login) &&
                email.equals(newsAdmin.email) &&
                password.equals(newsAdmin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, password);
    }
}
