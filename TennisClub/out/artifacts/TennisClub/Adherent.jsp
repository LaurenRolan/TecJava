<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="bean.Adherent" %>
<%--
  Created by IntelliJ IDEA.
  User: lrolan
  Date: 27/03/19
  Time: 09:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <title>Tennis Club -- Adherent</title>
</head>
<body>
    <nav class="navbar navbar-dark bg-primary navbar-expand-lg">
        <h1 class="navbar-text text-white"> <a href="/Menu.jsp" class="text-white"> Tennis Club  </a></h1>
        <h4 class="nav-text"> Caen </h4>
        <p class="nav-text"> Bienvenue <%= session.getAttribute("nom") %>
            <%= session.getAttribute("prenom") %>
        </p>
    </nav>
    <div class="row">
    <div class="container">
        <div id="infosPersonnelles" class="jumbotron bg-primary col-md-6.">
            <%= ((Adherent) request.getAttribute("info")).getNom() %>  <%= ((Adherent) request.getAttribute("info")).getPrenom() %> <br>
            <%= ((Adherent) request.getAttribute("info")).getAdresse() %><br>
            <%= ((Adherent) request.getAttribute("info")).getTelephone() %> <br>
            <%= ((Adherent) request.getAttribute("info")).getEmail() %>
        </div>
        <div id="infosTournois" class="jumbotron bg-primary col-md-6">
            <table class="table">
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Date</th>
                    <th scope="col">Lieu</th>
                </tr>
                <jsp:useBean id="tournoiList" scope="request" type="java.util.List"/>
                <c:forEach var="element" items="${tournoiList}">
                    <tr>
                        <td scope="row">${element.getTournoi().getNom()}</td>
                        <td>${element.getTournoi().getDate()}</td>
                        <td>${element.getTournoi().getLieu()}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </div>
    </div>
</body>
</html>
