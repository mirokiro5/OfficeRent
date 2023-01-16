package com.mirokiro.officerent.models;

import jakarta.persistence.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "office")
public class Office implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @Size(min = 10, max = 300)
    @Column(name = "description", length = 300)
    private String description;
    @Column(name = "image")
    private String image;
    @Digits(integer = 5, fraction = 2)
    @Column(name = "price")
    private BigDecimal price;
    @ManyToMany(mappedBy = "offices")
    private List<User> users = new ArrayList<>();
    @OneToOne(fetch = FetchType.EAGER)
    private RentedDate rentedDate;

    public Office() {
    }
    public Office(String name, String address, String description, String image, BigDecimal price) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.image = image;
        this.price = price;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<User> getCustomers() {
        return users;
    }

    public void setCustomers(List<User> customers) {
        this.users = users;
    }

    public RentedDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(RentedDate rentedDate) {
        this.rentedDate = rentedDate;
    }
}