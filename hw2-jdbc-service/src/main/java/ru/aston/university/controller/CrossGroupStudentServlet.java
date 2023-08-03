package ru.aston.university.controller;


import ru.aston.university.dto.CrossGroupStudentDto;
import ru.aston.university.service.CrossGroupStudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CrossGroupStudentServlet", urlPatterns = "/cross-group-student")
public class CrossGroupStudentServlet extends HttpServlet {

    private final CrossGroupStudentServiceImpl crossServiceImpl;

    private ObjectMapper mapper;

    public CrossGroupStudentServlet() {

        this.crossServiceImpl = CrossGroupStudentServiceImpl.getInstance();
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

        CrossGroupStudentDto cross = mapper.readValue(req.getInputStream(), CrossGroupStudentDto.class);

        crossServiceImpl.create(cross);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CrossGroupStudentDto cross = mapper.readValue(req.getInputStream(), CrossGroupStudentDto.class);

        crossServiceImpl.delete(cross);

    }
}
