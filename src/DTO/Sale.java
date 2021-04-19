/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author thienan
 */
public class Sale {
    String saleId, saleEvent, dishId;
    int salePercent, status;

    public Sale(String saleId, String saleEvent, String dishId, int salePercent, int status) {
        this.saleId = saleId;
        this.saleEvent = saleEvent;
        this.dishId = dishId;
        this.salePercent = salePercent;
        this.status = status;
    }

    public String getSaleId() {
        return saleId;
    }

    public String getSaleEvent() {
        return saleEvent;
    }

    public String getDishId() {
        return dishId;
    }

    public int getSalePercent() {
        return salePercent;
    }

    public int getStatus() {
        return status;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public void setSaleEvent(String saleEvent) {
        this.saleEvent = saleEvent;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public void setSalePercent(int salePercent) {
        this.salePercent = salePercent;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}