package com.epam.igor.entity;


import com.epam.igor.util.CsvUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "auditoriums")
public class Auditorium extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2692253546761426748L;

    @Column(name = "name")
    private String name;

    @Column(name = "seats_number")
    private int    seatsNumber;

    @Column(name = "vip_seats")
    private String vipSeats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getVipSeats() {
        return vipSeats;
    }

    public List<Integer> getVipSeatsList() {
        return CsvUtil.fromCsvToList(vipSeats, Integer:: valueOf);
    }

    public void setVipSeatsList(List<Integer> vipSeats) {
        this.vipSeats = CsvUtil.fromListToCsv(vipSeats);
    }

    public void setVipSeats(String vipSeats) {
        this.vipSeats = vipSeats;
    }

}
