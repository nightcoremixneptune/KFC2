
package DTO;


/**
 *
 * @author Admin
 */
public class Bill_Detail{
    String id_bill,date,id_member;
    int price_combo,sale,quantity_combo;

    public Bill_Detail(String id_bill, int quantity_combo, String date, String id_member, int price_combo, int sale) {
        this.id_bill = id_bill;
        this.quantity_combo = quantity_combo;
        this.date = date;

        this.id_member = id_member;
        this.price_combo = price_combo;
        this.sale = sale;
    }



    public void setId_member(String id_member) {
        this.id_member = id_member;
    }

    public String getId_member() {
        return id_member;
    }


    public void setId_bill(String id_bill) {
        this.id_bill = id_bill;
    }

    public void setQuantity_combo(int quantity_combo) {
        this.quantity_combo = quantity_combo;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public void setPrice_combo(int price_combo) {
        this.price_combo = price_combo;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getId_bill() {
        return id_bill;
    }

    public int getQuantity_combo() {
        return quantity_combo;
    }

    public String getDate() {
        return date;
    }



    public int getPrice_combo() {
        return price_combo;
    }

    public int getSale() {
        return sale;
    }

    
    
    

    

}
