package tecaa.in.com.myapplication.tecsaa.ui.classWork;

public class ClassWorkModel {
    String date, day,TeacherName,Time,Title,SubjectName,Link,SubjectText,Image;

    public ClassWorkModel(String date, String day, String teacherName, String time, String title, String subjectName, String link, String subjectText, String image) {
        this.date = date;
        this.day = day;
        TeacherName = teacherName;
        Time = time;
        Title = title;
        SubjectName = subjectName;
        Link = link;
        SubjectText = subjectText;
        Image = image;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public String getTime() {
        return Time;
    }

    public String getTitle() {
        return Title;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getLink() {
        return Link;
    }

    public String getSubjectText() {
        return SubjectText;
    }

    public String getImage() {
        return Image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public void setLink(String link) {
        Link = link;
    }

    public void setSubjectText(String subjectText) {
        SubjectText = subjectText;
    }

    public void setImage(String image) {
        Image = image;
    }
}
