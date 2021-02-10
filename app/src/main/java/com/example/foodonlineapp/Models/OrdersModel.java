package com.example.foodonlineapp.Models;

public class OrdersModel {
    int orderimage;
    String solditemname, price, ordernumber;

    public OrdersModel(int orderimage, String solditemname, String price, String ordernumber) {
        this.orderimage = orderimage;
        this.solditemname = solditemname;
        this.price = price;
        this.ordernumber = ordernumber;
    }

    public OrdersModel() {

    }

    public int getOrderimage() {
        return orderimage;
    }

    public void setOrderimage(int orderimage) {
        this.orderimage = orderimage;
    }

    public String getSolditemname() {
        return solditemname;
    }

    public void setSolditemname(String solditemname) {
        this.solditemname = solditemname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }
}
