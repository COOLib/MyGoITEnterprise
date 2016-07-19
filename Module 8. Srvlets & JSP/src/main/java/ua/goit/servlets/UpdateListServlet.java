package ua.goit.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doUpdate(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doUpdate(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        List<Task> myTasks = (List<Task>) request.getSession().getAttribute("myTasks");
        List<String> allDone = Arrays.asList(request.getParameterValues("doneButton"));
        List<Task> notDone = myTasks.stream().filter(t -> !allDone.contains(t.getName())).collect(Collectors.toList());

        request.getSession().setAttribute("myTasks", notDone);

        RequestDispatcher reqDispatcher = request.getRequestDispatcher("todoList.jsp");
        reqDispatcher.forward(request, response);
    }
}