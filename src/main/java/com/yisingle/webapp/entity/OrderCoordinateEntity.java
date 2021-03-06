package com.yisingle.webapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jikun on 17/7/27.
 */
@JsonIgnoreProperties({"orderEntity"})
@Entity
@Table(name = "t_order_coordinate")
public class OrderCoordinateEntity {

    @Id
    @GeneratedValue()
    private int id;

    @NotEmpty(message = "纬度不能空")
    private String latitude;


    @NotEmpty(message = "经度不能空")
    private String longitude;


    private long time;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity orderEntity;

    private int calculateType = CalculateType.NOT_CALCULATE.value();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public int getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(int calculateType) {
        this.calculateType = calculateType;
    }

    public static enum CalculateType {

        IS_CALCULATE(1), NOT_CALCULATE(2);

        int type;

        CalculateType(int type) {
            this.type = type;
        }


        public int value() {
            return type;
        }
    }
}
