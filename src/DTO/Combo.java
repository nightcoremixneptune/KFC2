
package DTO;


/**
 *
 * @author Admin
 */
public class Combo {
    String  id_combo,name_combo,id_dish,price_combo;
    int status_combo;

    public Combo(String id_combo, String name_combo, String id_dish, String price_combo, int status_combo) {
        this.id_combo = id_combo;
        this.name_combo = name_combo;
        this.id_dish = id_dish;
        this.price_combo = price_combo;
       
        this.status_combo = status_combo;
    }

    public void setId_combo(String id_combo) {
        this.id_combo = id_combo;
    }

    public void setName_combo(String name_combo) {
        this.name_combo = name_combo;
    }

    public void setId_dish(String id_dish) {
        this.id_dish = id_dish;
    }

    public void setPrice_combo(String price_combo) {
        this.price_combo = price_combo;
    }



    public void setStatus_combo(int status_combo) {
        this.status_combo = status_combo;
    }

    public String getId_combo() {
        return id_combo;
    }

    public String getName_combo() {
        return name_combo;
    }

    public String getId_dish() {
        return id_dish;
    }

    public String getPrice_combo() {
        return price_combo;
    }



    public int getStatus_combo() {
        return status_combo;
    }
    
    

}
