package MyPack;
import java.io.Serializable;

public class StudentInfo implements Serializable {
    private String name;
    private String email;
    private String password;
    private int semester;
    private String dob;
    private String phone;

    public StudentInfo(){
        name = "";
        email = "";
        password = "";
        semester = 0;
        dob = "";
        phone = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getSemester() {
        return semester;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }
    public String toString(){
        return " " + name + " " + email + " " + semester;
    }
}
