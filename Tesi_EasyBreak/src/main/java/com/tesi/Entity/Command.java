package com.tesi.Entity;

import java.util.Date;

public class Command {
    private int idCommand;
    private Date orderDate;
    private String status;
    private int idDish;
    private int idClient;
    private int idAdmin;
    private int dishesNumber;

    public Command(int idCommand, Date orderDate, String status, int idDish, int idClient, int idAdmin, int dishesNumber) {
        this.idCommand = idCommand;
        this.orderDate = orderDate;
        this.status = status;
        this.idDish = idDish;
        this.idClient = idClient;
        this.idAdmin = idAdmin;
        this.dishesNumber = dishesNumber;
    }

    public Command( Date orderDate,  int idDish, int idCommand, int dishesNumber) {
        this.idCommand = idCommand;
        this.orderDate = orderDate;
        this.idDish = idDish;
        this.dishesNumber = dishesNumber;
    }

    public int getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(int idCommand) {
        this.idCommand = idCommand;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getDishesNumber() {
        return dishesNumber;
    }

    public void setDishesNumber(int dishesNumber) {
        this.dishesNumber = dishesNumber;
    }
}
