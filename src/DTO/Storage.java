
package DTO;


/**
 *
 * @author Admin
 */
public class Storage{
    String id_food, brand_food,quantity_food,expiry_date;
    int status_food;

    public Storage(String id_food, String brand_food, String quantity_food, String expiry_date, int status_food) {
        this.id_food = id_food;
        this.brand_food = brand_food;
        this.quantity_food = quantity_food;
        this.expiry_date = expiry_date;
        this.status_food = status_food;
    }

    public void setId_food(String id_food) {
        this.id_food = id_food;
    }

    public void setBrand_food(String brand_food) {
        this.brand_food = brand_food;
    }

    public void setQuantity_food(String quantity_food) {
        this.quantity_food = quantity_food;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public void setStatus_food(int status_food) {
        this.status_food = status_food;
    }

    public String getId_food() {
        return id_food;
    }

    public String getBrand_food() {
        return brand_food;
    }

    public String getQuantity_food() {
        return quantity_food;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public int getStatus_food() {
        return status_food;
    }
    

    

}
