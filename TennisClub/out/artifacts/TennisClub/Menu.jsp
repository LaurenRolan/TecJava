<%--
  Created by IntelliJ IDEA.
  User: lrolan
  Date: 27/03/19
  Time: 09:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tennis Club -- Menu</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-dark bg-primary navbar-expand-lg">
        <h1 class="navbar-text text-white"> <a href="/Menu.jsp" class="text-white"> Tennis Club  </a> </h1>
        <h4 class="nav-text"> Caen </h4>
        <p class="nav-text"> Bienvenue <%= session.getAttribute("nom") %>
                        <%= session.getAttribute("prenom") %>
        </p>
    </nav>
    <div id="center">
        <div>
            <a href="/action?code=A">
                Consultation de votre dossier adhérent
            </a>
        </div>
        <div>
            <a href="/action?code=I">
                Inscription à un tournoi
            </a>
        </div>
    </div>
</body>
</html>
