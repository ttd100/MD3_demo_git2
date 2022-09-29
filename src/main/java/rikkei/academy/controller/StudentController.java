package rikkei.academy.controller;

import rikkei.academy.model.Students;
import rikkei.academy.service.IStudentService;
import rikkei.academy.service.StudentServiceIMPL;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value ={"/", "/students"})
public class StudentController extends HttpServlet {
    IStudentService studentService = new StudentServiceIMPL();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "create":
                formCreateStudent(request, response);
                break;
            default:
                formShowListStudent(request, response);
        }
       
    }

    private void formCreateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action) {
            case "create":
                actionCreate(request, response);
                break;
        }

    }

    private void actionCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        Students students = new Students(name, age);
        studentService.save(students);
        request.setAttribute("message","create student success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/create.jsp");
        dispatcher.forward(request,response);
    }

    private void formShowListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Students> studentsList = studentService.findAll();
        request.setAttribute("listStudent", studentsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/list.jsp");
        dispatcher.forward(request, response);
    }
}