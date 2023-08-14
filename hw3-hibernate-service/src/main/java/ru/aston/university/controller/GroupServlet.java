package ru.aston.university.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import ru.aston.university.dto.request.GroupDtoRequest;
import ru.aston.university.service.GroupServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GroupServlet", urlPatterns = "/group")
public class GroupServlet extends HttpServlet {

    private static final GroupServlet instance = new GroupServlet();
    private final GroupServiceImpl groupServiceImpl;

    private ObjectMapper mapper;

    public GroupServlet() {

        this.groupServiceImpl = GroupServiceImpl.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        String json = mapper.writeValueAsString(groupServiceImpl.readAll());

        writer.write(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        GroupDtoRequest group = mapper.readValue(req.getInputStream(), GroupDtoRequest.class);

        PrintWriter writer = resp.getWriter();
        groupServiceImpl.create(group);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        String rawId = (req.getParameter("id"));

        if (rawId != null && !rawId.isEmpty()) {
            long id = Long.parseLong(rawId);
            GroupDtoRequest group = mapper.readValue(req.getInputStream(), GroupDtoRequest.class);
            groupServiceImpl.update(group, id);
            writer.write("Информация по id " + id + " успешно обновлена");
        } else {
            writer.write("Введите корректный id группы для обновления информации");
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
            groupServiceImpl.delete(id);
            writer.write("Группа " + id + " успешно удалена");
        } else {

            writer.write("Введите корректный id группы для удаления");

        }
    }

    public static GroupServlet getInstance() {
        return instance;
    }
}
