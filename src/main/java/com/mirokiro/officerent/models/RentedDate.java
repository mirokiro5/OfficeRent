package com.mirokiro.officerent.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rented_date")
public class RentedDate {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "rented_date_id", nullable = false)
    private Long id;
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "office_id")
    private Office office;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public RentedDate(LocalDate startDate, LocalDate endDate, Office newOffice, User user) {
    }
    public RentedDate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
