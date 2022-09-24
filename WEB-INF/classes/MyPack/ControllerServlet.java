package MyPack;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ControllerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        String userAction = request.getParameter("action");
        if(userAction.equals("loginAdmin"))
            LoginAdmin(request, response);

        else if(userAction.equals("AddCourse"))
            response.sendRedirect("AddCourse.jsp");

        else if(userAction.equals("AssignCourse"))
            response.sendRedirect("AssignCourse.jsp");

        else if(userAction.equals("SearchCourse"))
            response.sendRedirect("SearchCourse.jsp");

        else if(userAction.equals("DeleteCourse"))
            response.sendRedirect("DeleteCourse.jsp");

        else if(userAction.equals("DeleteStudent"))
            response.sendRedirect("DeleteStudent.jsp");

	    else if(userAction.equals("AddedCourseinDB"))
            AddedCourseinDB(request, response);

        else if(userAction.equals("AssignedCourseinDB"))
            AssignedCourseinDB(request,response);

	    else if(userAction.equals("SearchCourseinDB"))
            SearchCourseinDB(request,response);

        else if(userAction.equals("AllRecords"))
            AllCourseinDB(request,response);

        else if(userAction.equals("DeleteCourseinDB"))
            DeleteCourseinDB(request,response);

        else if(userAction.equals("DeleteStudentinDB"))
            DeleteStudentinDB(request,response);    

        else if(userAction.equals("Logout"))
            response.sendRedirect("Logout.jsp");

        else if(userAction.equals("loginStudent"))
            loginStudent(request, response);

        else if(userAction.equals("SignupStudent"))
            SignupStudent(request, response);
        
        else if(userAction.equals("ViewInfo"))
            ViewInfo(request, response);

        else if(userAction.equals("ViewCourses"))
            ViewCourses(request, response);

        else if(userAction.equals("UpdateInfo"))
            UpdateInfo(request, response);

        else if(userAction.equals("BacktoMain"))
            BacktoMain(request,response);   
        
        else if(userAction.equals("UpdateInfoInDB"))
            UpdateInfoInDB(request,response);   
    }

    private void LoginAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            ProjectDAO loginadmin = new ProjectDAO();
            String  username = request.getParameter("username");
            String  password = request.getParameter("password");
            int result = loginadmin.authenticate(username,password,0);
            if(result == 1){
                int admin = 0;
                HttpSession session = request.getSession(true);
                session.setAttribute("UserType", admin);
                RequestDispatcher rd = request.getRequestDispatcher("AdminMain.jsp");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("message","invalid Password or UserName");
		    RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
                rd.forward(request, response);
            }
        }catch (Exception e) {
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void AddedCourseinDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            ProjectDAO addcourse = new ProjectDAO();
            String name = request.getParameter("Course");
            String semester = request.getParameter("semester");
            int s = Integer.parseInt(semester);
            int result = addcourse.addcourse(name,s);
            if(result == 1){
                request.setAttribute("message","Course Added Successfully");
                RequestDispatcher rd = request.getRequestDispatcher("AddCourse.jsp");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("message","Course Insertion Unsuccessful");
                RequestDispatcher rd = request.getRequestDispatcher("AddCourse.jsp");
                rd.forward(request, response);
            }
        }catch(Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }


    private void AssignedCourseinDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            ProjectDAO assign = new ProjectDAO();
            String name = request.getParameter("Course");
            String semester = request.getParameter("semester");
            String teacher = request.getParameter("teacher");
            int s = Integer.parseInt(semester);
            int result = assign.assigncourse(name,s,teacher);
            if(result == 1){
                request.setAttribute("message","Course Assigned to Teacher Successfully");
                RequestDispatcher rd = request.getRequestDispatcher("AssignCourse.jsp");
                rd.forward(request, response);
            }
	    else if(result == 0){
                request.setAttribute("message","Course assignment unsuccessful");
                RequestDispatcher rd = request.getRequestDispatcher("AssignCourse.jsp");
                rd.forward(request, response);
            }
        }catch(Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void SearchCourseinDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            ProjectDAO search = new ProjectDAO();
            String course = request.getParameter("Course");
            ArrayList courseList = search.searchcourse(course);
            if(courseList.isEmpty()){
		        request.setAttribute("message","No Records with given name");
                RequestDispatcher rd = request.getRequestDispatcher("SearchCourse.jsp");
                rd.forward(request, response);
            }
            request.setAttribute("list", courseList);
            RequestDispatcher rd = request.getRequestDispatcher("ShowCourses.jsp");
            rd.forward(request,response);
        }catch (Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void AllCourseinDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            ProjectDAO searchall = new ProjectDAO();
            ArrayList courseList = searchall.allcourse();
            if(courseList.isEmpty()){
		        request.setAttribute("message","No Records, Add Courses first");
                RequestDispatcher rd = request.getRequestDispatcher("SearchCourse.jsp");
                rd.forward(request, response);
            }
            request.setAttribute("list", courseList);
            RequestDispatcher rd = request.getRequestDispatcher("ShowCourses.jsp");
            rd.forward(request,response);
        }catch (Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    
    private void DeleteCourseinDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            ProjectDAO deletecourse = new ProjectDAO();
            String name = request.getParameter("Course");
            int result = deletecourse.deletecourse(name);
            if(result == 1){
                request.setAttribute("message","Course Deleted Successfully");
                RequestDispatcher rd = request.getRequestDispatcher("DeleteCourse.jsp");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("message","Course Deletion Unsuccessful");
                RequestDispatcher rd = request.getRequestDispatcher("DeleteCourse.jsp");
                rd.forward(request, response);
            }
        }catch(Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void DeleteStudentinDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            ProjectDAO deletestudent = new ProjectDAO();
            String name = request.getParameter("Student");
            int result = deletestudent.deletestudent(name);
            if(result == 1){
                request.setAttribute("message","Student Deleted Successfully");
                RequestDispatcher rd = request.getRequestDispatcher("DeleteStudent.jsp");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("message","Student Deletion Unsuccessful, Make Sure Student Exists");
                RequestDispatcher rd = request.getRequestDispatcher("DeleteStudent.jsp");
                rd.forward(request, response);
            }
        }catch(Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }
    //Student 
    private void loginStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            ProjectDAO loginStudent = new ProjectDAO();
            String  username = request.getParameter("username");
            String  password = request.getParameter("password");
            int result = loginStudent.authenticate(username,password,1);
            if(result == 1){
                int student = 1;
                HttpSession session = request.getSession(true);
                session.setAttribute("UserType", student);
                request.setAttribute("username",username);
                RequestDispatcher rd = request.getRequestDispatcher("StudentMain.jsp");
                rd.forward(request, response);
            } 
            else{
                request.setAttribute("message","invalid Password or UserName");
		        RequestDispatcher rd = request.getRequestDispatcher("Student.jsp");
                rd.forward(request, response);
            }
        }catch (Exception e) {
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void SignupStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            ProjectDAO SignStudent = new ProjectDAO();
            String  username = request.getParameter("username");
            String  email = request.getParameter("email");
            String  password = request.getParameter("password");
            String  semester = request.getParameter("semester");
            int s = Integer.parseInt(semester);
            String  dob = request.getParameter("dob");
            String  phone = request.getParameter("phone");
            int result = SignStudent.SignUp(username,email,password,s,dob,phone);
            if(result == 1){
                int student = 1;
                HttpSession session = request.getSession(true);
                session.setAttribute("UserType", student);
                request.setAttribute("username",username);
                RequestDispatcher rd = request.getRequestDispatcher("StudentMain.jsp");
                rd.forward(request, response);
            }
            else{
                request.setAttribute("message","SignUp Unsuccessful");
		        RequestDispatcher rd = request.getRequestDispatcher("Student.jsp");
                rd.forward(request, response);
            }
        }catch (Exception e) {
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void ViewCourses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            String username = request.getParameter("username");
            ProjectDAO getsem = new ProjectDAO();
            int s = getsem.getSemester(username);
            ProjectDAO viewcourses = new ProjectDAO();
            ArrayList courses = viewcourses.ViewCourse(s);
            request.setAttribute("username",username);
            if(courses.isEmpty()){
		        request.setAttribute("message","No Courses Assigned yet");
                RequestDispatcher rd = request.getRequestDispatcher("ViewCourses.jsp");
                rd.forward(request, response);
            }
            request.setAttribute("Courses", courses);
            RequestDispatcher rd = request.getRequestDispatcher("ViewCourses.jsp");
            rd.forward(request,response);
        }catch (Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }



    private void ViewInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            String username = request.getParameter("username");
            ProjectDAO viewinfo = new ProjectDAO();
            StudentInfo s = viewinfo.StudentInformation(username);
            request.setAttribute("username",username);
            request.setAttribute("info", s);
            RequestDispatcher rd = request.getRequestDispatcher("ViewInfo.jsp");
            rd.forward(request,response);
        }catch (Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void UpdateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            String username = request.getParameter("username");
            request.setAttribute("username",username);
            RequestDispatcher rd = request.getRequestDispatcher("UpdateInfo.jsp");
            rd.forward(request,response);
        }catch (Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void BacktoMain(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            String username = request.getParameter("username");
            request.setAttribute("username",username);
            RequestDispatcher rd = request.getRequestDispatcher("StudentMain.jsp");
            rd.forward(request,response);
        }catch (Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }

    private void UpdateInfoInDB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
            String username = request.getParameter("username");
            ProjectDAO getID = new ProjectDAO();
            int ID = getID.getID(username);

            ProjectDAO UpdateStudent = new ProjectDAO();
            String  user = request.getParameter("username");
            String  email = request.getParameter("email");
            String  password = request.getParameter("password");
            String  semester = request.getParameter("semester");
            int s = Integer.parseInt(semester);
            String  dob = request.getParameter("dob");
            String  phone = request.getParameter("phone");
            int result = UpdateStudent.Update(username,email,password,s,dob,phone, ID);
            if(result == 1){
                request.setAttribute("message","Information Updated Successfully");
                request.setAttribute("username",user);
                RequestDispatcher rd = request.getRequestDispatcher("UpdateInfo.jsp");
                rd.forward(request, response);
            }
        }catch (Exception e){
            request.setAttribute("error" , e); 
            RequestDispatcher rd = request.getRequestDispatcher("Error.jsp");
            rd.forward(request,response); 
        }
    }
}
