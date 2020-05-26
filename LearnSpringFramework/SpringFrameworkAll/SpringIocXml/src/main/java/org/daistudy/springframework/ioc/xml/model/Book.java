package org.daistudy.springframework.ioc.xml.model;

public class Book {
    private Integer id;
    private String name;
    private Float price;
    public Book(){
        System.out.println("----------book（无参） init----------");
    }

    public Book(Integer id, String name){
        System.out.println("----------book（有参--id/name） init----------");
        this.id = id;
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Book(Integer id, Float price){
        System.out.println("----------book（有参--id/price） init----------");
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        System.out.println("----------setId----------");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("----------setName----------");
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id:"+this.id +", name:"+ this.name+", price:"+ this.price+"}";
    }
}
