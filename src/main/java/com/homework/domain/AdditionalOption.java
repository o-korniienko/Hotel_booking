package com.homework.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "additional_options")
public class AdditionalOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String option_name;
    private long option_price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public long getOption_price() {
        return option_price;
    }

    public void setOption_price(long option_price) {
        this.option_price = option_price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalOption that = (AdditionalOption) o;
        return id == that.id &&
                option_price == that.option_price &&
                Objects.equals(option_name, that.option_name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, option_name, option_price);
    }

    @Override
    public String toString() {
        return "com.homework.domain.AdditionalOption{" +
                "id=" + id +
                ", option_name='" + option_name + '\'' +
                ", option_price=" + option_price +
                '}';
    }
}
