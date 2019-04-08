<%--
  Created by IntelliJ IDEA.
  User: lrolan
  Date: 08/04/19
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestion banque</title>
</head>
<body>
Compte numero : <% request.getParameter("id"); %>
Solde actuel : <%  %>

<form>
    montant à créditer: <input type="text" name="cre"> <br/>
    montant à débiter: <input type="text" name="deb"> <br/>
    <input type="submit" value="Mettre à jour"/>
</form>
</body>
</html>
