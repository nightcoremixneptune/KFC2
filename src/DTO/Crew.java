
package DTO;


/**
 *
 * @author Admin
 */
public class Crew {
    String  id_crew,name_crew,phone,sex	,position,shift,img;
    int status_crew, salary;
    
    public Crew(String id_crew, String name_crew, String phone, String sex, String img, int salary, String shift, String position, int status_crew) {
        this.id_crew = id_crew;
        this.name_crew = name_crew;
        this.phone = phone;
        this.sex = sex;
        this.img = img;
        this.position = position;
        this.salary = salary;
        this.shift = shift;
        this.status_crew = status_crew;

    }
    
   

    public String getId_crew() {
        return id_crew;
    }

    public String getName_crew() {
        return name_crew;
    }

    public String getPhone() {
        return phone;
    }

    public String getSex() {
        return sex;
    }
    public String getImg() {
        return img;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public String getShift() {
        return shift;
    }

    public int getStatus_crew() {
        return status_crew;
    }

    public void setId_crew(String id_crew) {
        this.id_crew = id_crew;
    }

    public void setName_crew(String name_crew) {
        this.name_crew = name_crew;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setStatus_crew(int status_crew) {
        this.status_crew = status_crew;
    }



}
