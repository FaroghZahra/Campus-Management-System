package MyPack;

import MyPack.CourseInfo;
import MyPack.StudentInfo;
import java.sql.*;
import java.util.*;

public class ProjectDAO {
    private Connection con;
    public ProjectDAO() throws ClassNotFoundException, SQLException{
        establishConnection();
    }

    private void establishConnection() throws  ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/project";
        con = DriverManager.getConnection(url, "root", "root");
    }

    public int authenticate(String username, String password, int userype) throws SQLException {
        int result = 0;
        String query = "SELECT * FROM authentication WHERE username = ? AND password = ? AND usertype = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,username);
        pst.setString(2,password);
        pst.setInt(3,userype);

        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            result = 1;
        }
        return result;
    }

    public int addcourse(String name, int semester) throws SQLException{
        String query = "INSERT INTO courses (Course,Semester) VALUES(?,?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,name);
        pst.setInt(2,semester);
        int result = pst.executeUpdate();
        return result;
    }

    public int assigncourse(String name, int semester, String teacher) throws SQLException{
        int result = 0;
        String query = "UPDATE courses SET Teacher = ? WHERE Course = ? AND Semester = ?";
        PreparedStatement psts = con.prepareStatement(query);
        psts.setString(1,teacher);
		psts.setString(2,name);
		psts.setInt(3,semester);
        int res = psts.executeUpdate();
            if(res == 1)
                result = 1;
        return result;
    }

	
        public ArrayList<CourseInfo> searchcourse(String course)throws SQLException{
            ArrayList<CourseInfo> CourseList = new ArrayList<CourseInfo>();
            String query = "SELECT * FROM courses WHERE Course LIKE ?";
            PreparedStatement pst = con.prepareStatement(query);
            String C = "%" + course + "%";
            pst.setString(1,C);
            ResultSet rs = pst.executeQuery();
            String Course;
            int Semester;
            String teacher;
            while (rs.next()){
                Course = rs.getString(1);
                Semester = rs.getInt(2);
                teacher = rs.getString(3);
                CourseInfo coursebean = new CourseInfo();
                coursebean.setCourse(Course);
                coursebean.setSemester(Semester);
                coursebean.setTeacher(teacher);

                CourseList.add(coursebean);
            }
            return CourseList;
        }

        public ArrayList<CourseInfo> allcourse()throws SQLException{
            ArrayList<CourseInfo> CourseList = new ArrayList<CourseInfo>();
            String query = "SELECT * FROM courses";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            String Course;
            int Semester;
            String teacher;
            while (rs.next()){
                Course = rs.getString(1);
                Semester = rs.getInt(2);
                teacher = rs.getString(3);
                CourseInfo coursebean = new CourseInfo();
                coursebean.setCourse(Course);
                coursebean.setSemester(Semester);
                coursebean.setTeacher(teacher);
                CourseList.add(coursebean);
            }
            return CourseList;
        }
    
        public int deletestudent(String email) throws SQLException{
            int result = 0;
            String query = "SELECT * FROM profile WHERE Email = ? ";
            PreparedStatement psts = con.prepareStatement(query);
            psts.setString(1,email);
            ResultSet res = psts.executeQuery();
            if(res.next()){
                String name = res.getString(1);
                String query1 = "DELETE FROM profile WHERE Name = ?";
                String query2 = "DELETE FROM authentication WHERE username = ?";
                PreparedStatement pst1 = con.prepareStatement(query1);
                PreparedStatement pst2 = con.prepareStatement(query2);
                pst1.setString(1, name);
                pst2.setString(1,name);
                int rs1 = pst1.executeUpdate();
                int rs2 = pst2.executeUpdate();
                if(rs1 == 1 && rs2 == 1){
                    result = 1;
                } 
            }
            return result;
        }

        public int deletecourse(String name) throws SQLException{
            int result = 0;
            String query = "DELETE FROM  courses WHERE Course = ? ";
            PreparedStatement psts = con.prepareStatement(query);
            psts.setString(1,name);
            int res = psts.executeUpdate();
                if(res == 1)
                    result = 1;
            return result;
        }

        public int SignUp(String username,String email,String password,int semester,String dob,String phone) throws SQLException{
            int result = 0;
            String query = "INSERT INTO profile (Name,Email,Password,Semester,dob,phone) VALUES(?,?,?,?,?,?)";
            PreparedStatement psts = con.prepareStatement(query);
            psts.setString(1,username);
            psts.setString(2,email);
            psts.setString(3,password);
            psts.setInt(4,semester);
            psts.setString(5,dob);
            psts.setString(6,phone);
            int res = psts.executeUpdate();
                if(res == 1){
                    String query2 = "INSERT INTO authentication (username,password,usertype) VALUES(?,?,?)";
                    PreparedStatement pst = con.prepareStatement(query2);
                    pst.setString(1,username);
                    pst.setString(2,password);
                    pst.setInt(3,1);
                    int res2 = pst.executeUpdate();
                    if(res2 == 1)
                        result = 1;
                }
            return result;
        }

        public StudentInfo StudentInformation(String name)throws SQLException{
            StudentInfo student = null;
            String query = "SELECT * FROM profile WHERE Name = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            String Name;
            String email;
            String Password;
            int Semester;
            String dob;
            String phone;
            if (rs.next()){
                Name = rs.getString(1);
                email = rs.getString(2);
                Password = rs.getString(3);
                Semester = rs.getInt(4);
                dob = rs.getString(5);
                phone = rs.getString(6);
                student = new StudentInfo();
                student.setName(Name);
                student.setEmail(email);
                student.setPassword(Password);
                student.setSemester(Semester);
                student.setDob(dob);
                student.setPhone(phone);
            }
            return student;
        }

        public ArrayList<CourseInfo> ViewCourse(int Semester)throws SQLException{
            ArrayList<CourseInfo> CourseList = new ArrayList<CourseInfo>();
            String query = "SELECT * FROM courses WHERE Semester = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1,Semester);
            ResultSet rs = pst.executeQuery();
            String Course;
            String teacher;
            while (rs.next()){
                Course = rs.getString(1);
                Semester = rs.getInt(2);
                teacher = rs.getString(3);
                CourseInfo coursebean = new CourseInfo();
                coursebean.setCourse(Course);
                coursebean.setSemester(Semester);
                coursebean.setTeacher(teacher);

                CourseList.add(coursebean);
            }
            return CourseList;
        }
    
    public int getSemester(String name) throws SQLException{
            int semester = 0;
            String query = "SELECT *  FROM profile WHERE Name = ? ";
            PreparedStatement psts = con.prepareStatement(query);
            psts.setString(1,name);
            ResultSet rs = psts.executeQuery();
            if(rs.next()){
                semester = rs.getInt(4);
            }
            return semester;
    }

    public int getID(String username) throws SQLException{
        int ID = 0;
        String query = "SELECT * FROM profile WHERE Name = ?";
        PreparedStatement psts = con.prepareStatement(query);
        psts.setString(1, username);
        ResultSet res = psts.executeQuery();
            if(res.next()){
                ID = res.getInt(7);
            }
        return ID;
    }

    public int Update(String username,String email,String password,int s,String dob,String phone, int ID) throws SQLException{
        int result = 0;
        String query = "UPDATE profile SET Name = ? , Email = ?, Password = ?, Semester = ?, dob = ?, phone = ? WHERE stid = ?";
        PreparedStatement psts = con.prepareStatement(query);
        psts.setString(1,username);
		psts.setString(2,email);
        psts.setString(3,password);
		psts.setInt(4,s);
        psts.setString(5,dob);
        psts.setString(6,phone);
        psts.setInt(7,ID);
        int res = psts.executeUpdate();
            if(res == 1)
                result = 1;
        return result;
    }

}