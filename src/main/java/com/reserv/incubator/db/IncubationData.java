package com.reserv.incubator.db;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incubation_data", schema = "public", catalog = "incubator")
public class IncubationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String point;
    private int dayNumber;
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "day_number", nullable = false)
    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "point", nullable = false, length = 10)
    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    @Basic
    @Column(name = "value", nullable = false, precision = 0)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncubationData that = (IncubationData) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (point != null ? !point.equals(that.point) : that.point != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result.intValue();
    }

}
