package ua.goit.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ToDoServlet extends HttpServlet {

    private List<Task> myTasks = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        someActions(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        someActions(request, response);
    }

    private void someActions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String name = request.getParameter("taskName");
        String category = request.getParameter("taskCategory");

        Task task = new Task(name, category);

        request.setAttribute("task", task);

        RequestDispatcher reqDispatcher = request.getRequestDispatcher("todoList.jsp");
        reqDispatcher.forward(request, response);
    }
}