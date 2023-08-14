package ru.aston.university.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import ru.aston.university.dto.CrossCourseStudentDto;
import ru.aston.university.service.CrossCourseStudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CrossCourseStudentServlet", urlPatterns = "/cross-course-student")
public class CrossCourseStudentServlet extends HttpServlet {

    private final CrossCourseStudentServiceImpl crossServiceImpl;

    private ObjectMapper mapper;

    public CrossCourseStudentServlet() {

        this.crossServiceImpl = CrossCourseStudentServiceImpl.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        String json = mapper.writeValueAsString(crossServiceImpl.readAll());

        writer.write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        CrossCourseStudentDto cross = mapper.readValue(req.getInputStream(), CrossCourseStudentDto.class);

        crossServiceImpl.create(cross);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CrossCourseStudentDto cross = mapper.readValue(req.getInputStream(), CrossCourseStudentDto.class);

        crossServiceImpl.delete(cross);

    }
}
