package ua.goit.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToDoServlet extends HttpServlet {

    private static List<Task> myTasks;

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

        String name = request.getParameter("taskName");
        String category = request.getParameter("taskCategory");

        Task task = new Task(name, category);

        if (request.getSession().getAttribute("myTasks") != null) {

            myTasks = (List<Task>) request.getSession().getAttribute("myTasks");
        } else {

            myTasks = new ArrayList<>();
        }

        if (name != "" && category != "" && !myTasks.contains(task)) {
            myTasks.add(task);
        }

        request.setAttribute("myTasks", myTasks);

        HttpSession session = request.getSession(true);
        session.setAttribute("myTasks", myTasks);

        Integer counter = (Integer) session.getAttribute("myCounter");

        if (counter == null) {
            counter = 1;
            session.setAttribute("myCounter", counter);
        } else {
            session.setAttribute("myCounter", ++counter);
        }

        RequestDispatcher reqDispatcher = request.getRequestDispatcher("todoList.jsp");
        reqDispatcher.forward(request, response);
    }
}