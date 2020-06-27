package tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher;

public class AttendanceModel {
    String firstName,lastName,type,date;
    int id,userid;


    public AttendanceModel(String firstName, String lastName, String type, String date, int id, int userid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.date = date;
        this.id = id;
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
