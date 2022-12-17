<%@ page  language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="header.jsp" %>
<p></p>
<div class="container">
<div class="card">
  <div class="card-header">
    Recherche des Matieres
  </div>
  <div class="card-body">
      <form action="chercherMatieres" method="get">

        <input type="text" name="motCle" value="${model.motCle}"/>
        <button type="submit" class="btn btn-primary mb-2">Chercher</button>

      </form>   
		<div class="col d-flex justify-content-end align-items-end mb-2">
		<a href="saisieMatiere">
		  <button class="btn btn-primary" type="button">
		   	Ajouter
		  </button>
		  </a>
		</div> 
      
      <table class="table table-striped">
        <tr>
          <th>ID</th>
          <th>Nom Matiere</th>
          <th>Nb Heures</th>
          <th>Actions</th>
         </tr>
         <c:forEach items="${model.matieres}" var="p">
           <tr>
              <td>${p.idMatiere }</td>
              <td>${p.nomMatiere }</td>
              <td>${p.nbHeuresMat }</td>
              <td><a onclick="return confirm('Confirmer la suppression ?')" href="supprimerMatiere?id=${p.idMatiere }"><button type="button" class="btn btn-danger btn-sm">Supprimer</button></a>
               <a href="editerMatiere?id=${p.idMatiere }"><button type="button" class="btn btn-success btn-sm">Modifier</button></a></td>
           </tr>
         </c:forEach>        
      </table>
  </div>
</div>
</div>
</body>
</html>