package org.example.model;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private Integer age;
    private Long pesel;
    private Long accountId;

    public User(Long id, String name, Integer age, Long pesel, Long accountId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.pesel = pesel;
        this.accountId = accountId;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", pesel=" + pesel +
                ", accountId=" + accountId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
