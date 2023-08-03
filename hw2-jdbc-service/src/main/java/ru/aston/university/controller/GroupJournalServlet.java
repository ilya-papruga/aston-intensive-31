package ru.aston.university.controller;

import ru.aston.university.service.GroupJournalServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "GroupJournalServlet", urlPatterns = "/group-journal")
public class GroupJournalServlet extends HttpServlet {

    private final GroupJournalServiceImpl groupJournalServiceImpl;

    private ObjectMapper mapper;

    public GroupJournalServlet() {

        this.groupJournalServiceImpl = GroupJournalServiceImpl.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        String number = req.getParameter("number");

        String json;
        if (number != null && !number.isEmpty()) {
            json = mapper.writeValueAsString(groupJournalServiceImpl.readJournal(number));
        }
         else {
            json = mapper.writeValueAsString(groupJournalServiceImpl.readAll());
        }
        writer.write(json);
    }
}
