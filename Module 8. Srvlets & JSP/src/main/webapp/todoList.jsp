<%@page import="ua.goit.servlets.Task" %>
<%@page import="ua.goit.servlets.ToDoServlet" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ru" xml:lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html"/>
    <title>My Tasks</title>
    <link rel="stylesheet" type="text/css" href="styledform.css"/>
</head>

<body>

<div id="ToDoListForm">

    <div id="header">
        <img src="images/work.gif" alt="ToDo List image"/>
    </div>
    <h1>My ToDo List</h1>

    <form action="todoForm" method="post">
        <table class="table1">
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Complete</th>
            </tr>

            <c:forEach items="${myTasks}" var="task">

                <tr>
                    <td>${task.name}</td>
                    <td>${task.category}</td>
                    <td><input type="checkbox" name="doneButton" value="${task.name}"/></td>
                </tr>
            </c:forEach>

        </table>

        <input id="update" type="submit" value="Update tasks"/>

    </form>
    <form action="submitForm" method="post">

        <h2>
            Fill the form and click the button "Add task"
        </h2>

        <div id="inputDiv">
            Task name:<input class="textInput" type="text" name="taskName" placeholder="Enter name"/>
            <br/>
            Task category: <input class="textInput" type="text" name="taskCategory" placeholder="Enter category"/>
            <br/>
            <input class="Add" type="submit" value="Add task">
        </div>

        <h2>Session counter: "${sessionScope.get("myCounter")}"</h2>
    </form>

    <div id="footer">
        &copy; 2016, COOLib
        <br/>
        All trademarks and registered trademarks appearing on
        this site are the property of their respective owners.
    </div>

</div>
</body>
</html>
