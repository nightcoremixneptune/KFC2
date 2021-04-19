
package DTO;


/**
 *
 * @author Admin
 */
public class Bill{
    String id_bill,id_combo,id_crew,id_sale;
    int sum;

    public Bill(String id_bill, String id_combo, String id_crew, String id_sale, int sum) {
        this.id_bill = id_bill;
        this.id_combo = id_combo;
        this.id_crew = id_crew;
        this.id_sale = id_sale;
        this.sum = sum;
    }

    public void setId_bill(String id_bill) {
        this.id_bill = id_bill;
    }

    public void setId_combo(String id_combo) {
        this.id_combo = id_combo;
    }

    public void setId_crew(String id_crew) {
        this.id_crew = id_crew;
    }

    public void setId_sale(String id_sale) {
        this.id_sale = id_sale;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getId_bill() {
        return id_bill;
    }

    public String getId_combo() {
        return id_combo;
    }

    public String getId_crew() {
        return id_crew;
    }

    public String getId_sale() {
        return id_sale;
    }

    public int getSum() {
        return sum;
    }

    
    

    

}
