<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar navbar-default">
   <div class="navbar-header">
     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
     </button>
     <a class="navbar-brand" href="<c:url value="/"/>" data-pjax><img src="<c:url value="/resources/img/icon_9727.png"/>" style="width: 40px; height: 40px; margin-top: -10px;">&nbsp;&nbsp;<tiles:getAsString name="title" ignore="true" /></a>
   </div>
   <div class="navbar-collapse collapse">
     <ul class="nav navbar-nav">
       <li><a href="<c:url value="/about"/>" data-pjax>About</a></li>
     </ul>
     <ul class="nav navbar-nav navbar-right">
        <c:if test="${not empty pageContext.request.remoteUser}">
        	<li class="active"><a href="<c:url value="/profile"/>" data-pjax>${pageContext.request.remoteUser}</a></li>
        </c:if>
     </ul>
   </div><!--/.nav-collapse -->
 </div>

