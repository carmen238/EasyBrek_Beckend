package com.tesi.Entity;

public class Dish {
    String dishName;
    String category;
    int dishPrice ;
    int numberDishes;
    int id;
    int idAdmin;


    public  Dish(String dishName, String category, int dishPrice,int id, int numberDishes) {
        this.dishName = dishName;
        this.category = category;
        this.dishPrice = dishPrice;
        this.id = id;
        this.numberDishes= numberDishes;

    }

    public  Dish(String dishName, int dishPrice, String category) {
        this.dishName = dishName;
        this.category = category;
        this.dishPrice = dishPrice;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(int dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getId() {
        return id;
    }

    public void setIdAdmin(int id) {
        this.id = id;
    }

    public int getDishesNumber() {
        return numberDishes;
    }

    public void setDishesNumber(int numberDishes) {
        this.numberDishes = numberDishes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdmin() {
        return idAdmin;
    }
}
