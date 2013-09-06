<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title><tiles:getAsString name="title" ignore="true" /></title>
	
	<tiles:insertAttribute name="meta" ignore="true"/>
	<tiles:insertAttribute name="stylesheets" ignore="true"/>
</head>

<body>
	<div id="outer">
	<div id="inner">

	<tiles:insertAttribute name="content" ignore="true"/>

	</div><!-- end inner -->
	</div><!-- end outer -->
	
<tiles:insertAttribute name="footer" ignore="true"/>
<tiles:insertAttribute name="js" ignore="true"/>

</body>
</html>
