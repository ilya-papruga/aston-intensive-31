package ru.aston.university.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServletTest {

    private final StudentServlet studentServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PrintWriter printWriter;
    private StringWriter stringWriter;

    public StudentServletTest() {

        this.studentServlet = StudentServlet.getInstance();

    }

    @BeforeEach
    public void beforeEach() {

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @Test
    public void doGetTest() throws IOException, ServletException {
        when(request.getPathInfo()).thenReturn(null);
        when(response.getWriter()).thenReturn(printWriter);
        studentServlet.doGet(request, response);

        assertThat(stringWriter.toString()).contains("Vlad");
    }

}
