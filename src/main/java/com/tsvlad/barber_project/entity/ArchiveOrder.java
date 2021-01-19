package com.tsvlad.barber_project.entity;

import com.tsvlad.barber_project.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "archive_orders")
public class ArchiveOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Column(name = "order_date")
    private LocalDate date;

    @Column(name = "order_hour")
    private int hour;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status")
    private OrderStatus status;

    public ArchiveOrder(ActiveOrder order) {
        this.client = order.getClient();
        this.barber = order.getOrderKey().getBarber();
        this.service = order.getService();
        this.date = order.getOrderKey().getDate();
        this.hour = order.getOrderKey().getHour();
    }

    public ArchiveOrder(ActiveOrder order, OrderStatus status) {
        this(order);
        this.status = status;
    }

    public ArchiveOrder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ArchiveOrder{" +
                "id=" + id +
                ", client=" + client +
                ", barber=" + barber +
                ", service=" + service +
                ", date=" + date +
                ", hour=" + hour +
                ", status=" + status +
                '}';
    }
}
