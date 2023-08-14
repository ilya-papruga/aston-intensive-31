package ru.aston.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import ru.aston.university.service.CourseJournalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CourseJournalServlet", urlPatterns = "/course-journal")
public class CourseJournalServlet extends HttpServlet {

    private final CourseJournalServiceImpl courseJournalServiceImpl;

    private ObjectMapper mapper;

    public CourseJournalServlet() {

        this.courseJournalServiceImpl = CourseJournalServiceImpl.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        String title = req.getParameter("title");
        String name = req.getParameter("name");

        String json;
        if (title != null && !title.isEmpty()) {
            json = mapper.writeValueAsString(courseJournalServiceImpl.readCourse(title));
        } else if (name != null && !name.isEmpty()) {
            json = mapper.writeValueAsString(courseJournalServiceImpl.readStudent(name));
        } else {
            json = mapper.writeValueAsString(courseJournalServiceImpl.readAll());
        }
        writer.write(json);
    }
}
