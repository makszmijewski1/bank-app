package org.example.model;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private Integer age;
    private Long pesel;

    public User(Long id, String name, Integer age, Long pesel) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.pesel = pesel;
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

}
