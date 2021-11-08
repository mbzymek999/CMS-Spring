//package api.owner.entities;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "product")
//public class Product {
//    @Id
//    @GeneratedValue(generator = "inc")
//    @GenericGenerator(name = "inc", strategy = "increment")
//    private int id;
//    private String name;
//    private String description;
//    private double price;
//    private String category;
//    private String height;
//    private String width;
//    private String weight;
//    private String color;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getHeight() {
//        return height;
//    }
//
//    public void setHeight(String height) {
//        this.height = height;
//    }
//
//    public String getWidth() {
//        return width;
//    }
//
//    public void setWidth(String width) {
//        this.width = width;
//    }
//
//    public String getWeight() {
//        return weight;
//    }
//
//    public void setWeight(String weight) {
//        this.weight = weight;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//}
