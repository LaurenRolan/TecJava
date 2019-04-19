<%--
  Created by IntelliJ IDEA.
  User: lrolan
  Date: 27/03/19
  Time: 09:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>Tennis Club -- Inscription</title>
</head>
<body>
    <nav class="navbar navbar-dark bg-primary navbar-expand-lg">
        <h1 class="navbar-text text-white"> <a href="/Menu.jsp" class="text-white"> Tennis Club  </a> </h1>
        <h4 class="nav-text"> Caen </h4>
        <p class="nav-text"> Bienvenue <%= session.getAttribute("nom") %>
            <%= session.getAttribute("prenom") %>
        </p>
    </nav>
    <div>
        <table id="tounois" class="table">
            <tr>
                <th scope="col">Code</th>
                <th scope="col">Nom</th>
                <th scope="col">Date</th>
                <th scope="col">Lieu</th>
                <th scope="col"></th>
            </tr>
                <jsp:useBean id="tournoiList" scope="request" type="java.util.List"/>
                <c:forEach var="tournoi" items="${tournoiList}">
                    <tr>
                        <td scope="row">${tournoi.getCodeTournoi()}</td>
                        <td>${tournoi.getNom()}</td>
                        <td>${tournoi.getDate()}</td>
                        <td>${tournoi.getLieu()}</td>
                        <td><a href="/inscription?tournoi=${tournoi.getCodeTournoi()}">
                            Inscription</a></td>
                    </tr>
        </c:forEach>
        </table>
    </div>
</body>
</html>
