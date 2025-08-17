package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import lombok.Data;

import java.lang.reflect.Type;

/*
@Data annotation is provided by lombok library which genereates getter,setter
equals(),hashcode(),toString() methods & constructors at compile time.
This makes our code short and clean
 */

@Data
@Entity
@Table(name="holidays")
public class Holiday extends BaseEntity{

    @Id
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type{
        FESTIVAL,FEDERAL
    }

    /*
    public Holiday(String day, String reason, Type type) {
        this.day = day;
        this.reason = reason;
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public String getReason() {
        return reason;
    }

    public Type getType() {
        return type;
    }

     */
}
