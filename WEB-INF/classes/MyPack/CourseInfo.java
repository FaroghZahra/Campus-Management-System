package MyPack;

import java.io.Serializable;

public class CourseInfo implements Serializable {
    private String Course;
    private int Semester;
    private String teacher;

    public CourseInfo(){
        Course = "";
        Semester = 0;
        teacher = "";
    }

    public void setCourse(String course) {
        Course = course;
    }

    public void setSemester(int semester) {
        Semester = semester;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourse() {
        return Course;
    }

    public int getSemester() {
        return Semester;
    }

    public String getTeacher() {
        return teacher;
    }
}
