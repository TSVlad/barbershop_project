package com.tsvlad.barber_project.entity;

import com.tsvlad.barber_project.entity.complexKeys.OrderKey;

import javax.persistence.*;

@Entity
@Table(name = "active_orders")
public class ActiveOrder {
    @EmbeddedId
    private OrderKey orderKey;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Column(name = "order_comment")
    private String comment;

    public ActiveOrder(OrderKey orderKey, Client client, Service service, String comment) {
        this.orderKey = orderKey;
        this.client = client;
        this.service = service;
        this.comment = comment;
    }

    public ActiveOrder() {
    }

    public OrderKey getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(OrderKey orderKey) {
        this.orderKey = orderKey;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ActiveOrder{" +
                "orderKey=" + orderKey +
                ", client=" + client +
                ", service=" + service +
                ", comment='" + comment + '\'' +
                '}';
    }
}
