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
</head>
<body>
    <div id="nomUtilisateur"> Bienvenue <%= session.getAttribute("nom") %> <%= session.getAttribute("prenom") %> </div>
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
</body>
</html>
