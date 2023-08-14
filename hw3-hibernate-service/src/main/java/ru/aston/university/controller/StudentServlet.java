package ru.aston.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import ru.aston.university.dto.request.StudentDtoRequest;
import ru.aston.university.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private static final StudentServlet instance = new StudentServlet();
    private final StudentServiceImpl studentServiceImpl;

    private ObjectMapper mapper;

    public StudentServlet() {

        this.studentServiceImpl = StudentServiceImpl.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        String json = mapper.writeValueAsString(studentServiceImpl.readAll());

        writer.write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        StudentDtoRequest student = mapper.readValue(req.getInputStream(), StudentDtoRequest.class);

        PrintWriter writer = resp.getWriter();
        studentServiceImpl.create(student);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        String rawId = (req.getParameter("id"));

        if (rawId != null && !rawId.isEmpty()) {
            long id = Long.parseLong(rawId);
            StudentDtoRequest student = mapper.readValue(req.getInputStream(), StudentDtoRequest.class);
            studentServiceImpl.update(student, id);
            writer.write("Информация успешно обновлена");
        } else {

            writer.write("Введите id студента для обновления информации");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        String rawId = (req.getParameter("id"));

        if (rawId != null && !rawId.isEmpty()) {

            long id = Long.parseLong(rawId);
            studentServiceImpl.delete(id);
            writer.write("Студент " + id + " успешно удалён");
        } else {

            writer.write("Введите корректный id студента для удаления");

        }
    }

    public static StudentServlet getInstance() {
        return instance;
    }

}
