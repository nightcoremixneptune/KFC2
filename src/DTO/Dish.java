
package DTO;


/**
 *
 * @author Admin
 */
public class Dish {
    String id_dish, name_dish,id_food,food_consume;
    int status_dish;

    public Dish(String id_dish, String name_dish, String id_food, String food_consume, int status_dish) {
        this.id_dish = id_dish;
        this.name_dish = name_dish;
        this.id_food = id_food;
        this.food_consume = food_consume;
        this.status_dish = status_dish;
    }

    public void setId_dish(String id_dish) {
        this.id_dish = id_dish;
    }

    public void setName_dish(String name_dish) {
        this.name_dish = name_dish;
    }

    public void setId_food(String id_food) {
        this.id_food = id_food;
    }

    public void setFood_consume(String food_consume) {
        this.food_consume = food_consume;
    }

    public void setStatus_dish(int status_dish) {
        this.status_dish = status_dish;
    }

    public String getId_dish() {
        return id_dish;
    }

    public String getName_dish() {
        return name_dish;
    }

    public String getId_food() {
        return id_food;
    }

    public String getFood_consume() {
        return food_consume;
    }

    public int getStatus_dish() {
        return status_dish;
    }
    
    


}
