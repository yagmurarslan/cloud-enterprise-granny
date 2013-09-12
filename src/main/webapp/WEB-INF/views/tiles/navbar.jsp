<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container-fluid">
      <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="brand" href="<c:url value="/"/>"><tiles:getAsString name="title" ignore="true" /></a>
      <div class="nav-collapse collapse">
        <p class="navbar-text pull-right">
          <!-- Logged in as <a href="#" class="navbar-link">Username</a>  -->
        </p>
        <ul class="nav">
          <li><a href="<c:url value="/about"/>">About</a></li>
        </ul>
      </div><!--/.nav-collapse -->
    </div>
  </div>
</div>