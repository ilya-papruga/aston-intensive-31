package ru.aston.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import ru.aston.university.dto.request.CourseDtoRequest;
import ru.aston.university.service.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CourseServlet", urlPatterns = "/course")
public class CourseServlet extends HttpServlet {

    private static final CourseServlet instance = new CourseServlet();
    private final CourseServiceImpl courseServiceImpl;
    private ObjectMapper mapper;

    public CourseServlet() {

        this.courseServiceImpl = CourseServiceImpl.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        String json = mapper.writeValueAsString(courseServiceImpl.readAll());

        writer.write(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        CourseDtoRequest course = mapper.readValue(req.getInputStream(), CourseDtoRequest.class);

        courseServiceImpl.create(course);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        String rawId = (req.getParameter("id"));

        if (rawId != null && !rawId.isEmpty()) {
            long id = Long.parseLong(rawId);
            CourseDtoRequest course = mapper.readValue(req.getInputStream(), CourseDtoRequest.class);
            courseServiceImpl.update(course, id);
            writer.write("Информация по id " + id + " успешно обновлена");
        } else {
            writer.write("Введите корректный id курса для обновления информации");
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
            courseServiceImpl.delete(id);
            writer.write("Курс " + id + " успешно удалён");
        } else {

            writer.write("Введите корректный id группы для удаления");

        }
    }

    public static CourseServlet getInstance() {
        return instance;
    }
}
