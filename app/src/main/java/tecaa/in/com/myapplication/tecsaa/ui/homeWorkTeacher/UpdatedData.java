package tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher;

public class UpdatedData {
    String id,title,desc;
    int classId,subjectId,sectionId;

    public UpdatedData(String id,String title, String desc, int classId, int subjectId, int sectionId) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.classId = classId;
        this.subjectId = subjectId;
        this.sectionId = sectionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getClassId() {
        return classId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getSectionId() {
        return sectionId;
    }

}
