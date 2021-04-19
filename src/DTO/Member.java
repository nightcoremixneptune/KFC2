
package DTO;


/**
 *
 * @author Admin
 */
public class Member {
    String id_member, name_member,phonenumber,brand_member;
    int status_member;

    public Member(String id_member, String name_member, String phonenumber, String brand_member, int status_member) {
        this.id_member = id_member;
        this.name_member = name_member;
        this.phonenumber = phonenumber;
        this.brand_member = brand_member;
        this.status_member = status_member;
    }

    public void setId_member(String id_member) {
        this.id_member = id_member;
    }

    public void setName_member(String name_member) {
        this.name_member = name_member;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setBrand_member(String brand_member) {
        this.brand_member = brand_member;
    }

    public void setStatus_member(int status_member) {
        this.status_member = status_member;
    }

    public String getId_member() {
        return id_member;
    }

    public String getName_member() {
        return name_member;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getBrand_member() {
        return brand_member;
    }

    public int getStatus_member() {
        return status_member;
    }

   
    


}
