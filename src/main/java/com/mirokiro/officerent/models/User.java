package com.mirokiro.officerent.models;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @Digits(integer = 10, fraction = 2)
    @Column(name = "total_price")
    private BigDecimal totalPrice = new BigDecimal(0);
    @Column(name = "is_paid")
    private boolean isPaid = false;
    @Column(name = "rented_offices")
    private int rentedOffices;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Office> offices = new ArrayList<>();



    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }
    @Override
    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public String roleToString(){
        List<Role> usersRole = getRoles();
        Set<String> stringSet = new HashSet<>();
        for (Role role: usersRole) {
            stringSet.add(role.getName());
        }
        return String.join(", ", stringSet);
    }


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getRentedOffices() {
        return rentedOffices;
    }

    public void setRentedOffices(int rentedOffices) {
        this.rentedOffices = rentedOffices;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }
    public String officesToString(){
        List<Office> usersOffices = getOffices();
        Set<String> stringSet = new HashSet<>();
        for (Office office: usersOffices) {
            stringSet.add(office.getName());
        }
        return String.join(", ", stringSet);
    }
}