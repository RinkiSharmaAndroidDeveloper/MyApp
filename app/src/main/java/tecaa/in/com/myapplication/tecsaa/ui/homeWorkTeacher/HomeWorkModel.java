package tecaa.in.com.myapplication.tecsaa.ui.homeWorkTeacher;

import java.util.List;

public class HomeWorkModel {

    String title,desc,type,firstName,lastName,fileID,filePath;
    int id,schoolId,classId,sectionId,subjectId,userId;
    Data classDataList,schoolDataList,subjectect;


    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public Data getClassDataList() {
        return classDataList;
    }

    public void setClassDataList(Data classDataList) {
        this.classDataList = classDataList;
    }

    public Data getSchoolDataList() {
        return schoolDataList;
    }

    public void setSchoolDataList(Data schoolDataList) {
        this.schoolDataList = schoolDataList;
    }

    public Data getSubjectect() {
        return subjectect;
    }

    public void setSubjectect(Data subjectect) {
        this.subjectect = subjectect;
    }

    public HomeWorkModel() {

    }

    public HomeWorkModel(String title, String desc, String type, String firstName, String lastName, String fileID, String filePath, int id, int schoolId, int classId, int sectionId, int subjectId, int userId, Data classDataList, Data schoolDataList, Data subjectect) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fileID = fileID;
        this.filePath = filePath;
        this.id = id;
        this.schoolId = schoolId;
        this.classId = classId;
        this.sectionId = sectionId;
        this.subjectId = subjectId;
        this.userId = userId;
        this.classDataList = classDataList;
        this.schoolDataList = schoolDataList;
        this.subjectect = subjectect;
    }

    public static class Data{
        String name;
        int id;

        public Data() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Data(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }
}
