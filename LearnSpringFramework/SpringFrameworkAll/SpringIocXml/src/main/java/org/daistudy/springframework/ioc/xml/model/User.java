package org.daistudy.springframework.ioc.xml.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class User {
    private Integer id;
    private String name;
    private Cat cat;
    private String[] favorities;
    private List<Cat> cats;
    private Map<String, Object> map;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String[] getFavorities() {
        return favorities;
    }

    public void setFavorities(String[] favorities) {
        this.favorities = favorities;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private Properties properties;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cat=" + cat +
                ", favorities=" + Arrays.toString(favorities) +
                ", cats=" + cats +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }
}
