package com.epam.igor.entity;

import com.epam.igor.converter.DateTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2692253546761426748L;

    @Column(name = "name")
    private String        name;

    @Column(name = "rate")
    private Rate          rate;

    @Column(name = "base_price")
    private double        basePrice;

    @Column(name = "date_time")
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime dateTime;

    @Column(name = "auditorium_id")
    private Long auditoriumId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auditorium_id", insertable = false, updatable = false, nullable = false)
    private Auditorium auditorium;

    @Column(name = "movie_id")
    private Long movieId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(Long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

}
