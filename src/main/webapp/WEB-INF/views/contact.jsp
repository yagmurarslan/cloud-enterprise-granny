<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <div class="row-fluid">
    <div class="span9">
      <form:form id="contact" name="contact" class="form-horizontal" servletRelativeAction="/contact" method="POST" enctype="application/x-www-form-urlencoded" autocomplete="on" modelAttribute="contact">
     
          <fieldset>
                <input id="selectedIndexField" name="selectedIndex" type="hidden" value=""/>
                <input id="oldNameField" name="oldName" type="hidden" value=""/>
              
              	<form:hidden path="id"/>
              	<form:hidden path="createdAt"/>
              	<form:hidden path="createdBy"/>
              	<form:hidden path="version"/>
              
                <legend>Main data</legend>
              
                <div class="control-group ">
                    <label class="control-label" for="salutation">Salutation</label>
                    <div class="controls">
                        <form:select path="salutation">
                            <form:option value="" label=""/>
                            <form:options/>
                        </form:select>
                    </div>
                </div>
                
                <div class="control-group ">
                    <label class="control-label" for="title">Title</label>
                    <div class="controls">
                        <form:select path="title">
                            <form:option value="" label=""/>
                            <form:options/>
                        </form:select>
                    </div>
                </div>
              
                <t:input path="firstName"/>
              
                <t:input path="lastName"/>
              
                <!-- addresses -->
                <legend>Address data<button class="btn btn-mini pull-right" type="submit" id="addAddress" name="addAddress" value="addAddress"><i class="icon-plus"></i> Add</button></legend>
                <c:forEach items="${contact.addresses}" var="address" varStatus="addressStatus">
               		<form:hidden path="addresses[${addressStatus.index}].id"/>
	              	<form:hidden path="addresses[${addressStatus.index}].createdAt"/>
	              	<form:hidden path="addresses[${addressStatus.index}].createdBy"/>
	              	<form:hidden path="addresses[${addressStatus.index}].version"/>
                
                    <t:input path="addresses[${addressStatus.index}].street" label="Street"/>
                    
                    <t:input path="addresses[${addressStatus.index}].street2" label="Street2"/>
              
                    <t:input path="addresses[${addressStatus.index}].city" label="City"/>
                  
                    <t:input path="addresses[${addressStatus.index}].zipCode" label="ZIP Code"/>
                  
                    <t:input path="addresses[${addressStatus.index}].state" label="State"/>
                  
                    <div class="control-group ">
                        <label class="control-label" for="country">Country</label>
                        <div class="controls">
                            <form:select path="addresses[${addressStatus.index}].country">
                                <form:option value="" label=""/>
                                <form:options items="${countryList}"/>
                            </form:select>
                        </div>
                    </div>
                </c:forEach>
                
                <!-- phone numbers -->
                <legend>Phone numbers<button class="btn btn-mini pull-right" type="submit" id="addPhoneNumber" name="addPhoneNumber" value="addPhoneNumber"><i class="icon-plus"></i> Add</button></legend>
                <c:forEach items="${contact.phoneNumbers}" var="phoneNumber" varStatus="phoneNumberStatus">
                    <form:hidden path="phoneNumbers[${phoneNumberStatus.index}].id"/>
	              	<form:hidden path="phoneNumbers[${phoneNumberStatus.index}].createdAt"/>
	              	<form:hidden path="phoneNumbers[${phoneNumberStatus.index}].createdBy"/>
	              	<form:hidden path="phoneNumbers[${phoneNumberStatus.index}].version"/>
                    
                    <div class="control-group">
                        <label class="control-label" for="phoneNumbers[${phoneNumberStatus.index}].number">Number</label>
                        <div class="controls">
                            <form:input path="phoneNumbers[${phoneNumberStatus.index}].number" />
                            <c:if test="${phoneNumberStatus.index > 0}">
                            	<button class="btn btn-mini" type="submit" id="deletePhoneNumber" name="deletePhoneNumber" value="${phoneNumberStatus.index}"><i class="icon-minus"></i>&nbsp;Delete</button>
                            </c:if>
                        </div>
                    </div>
                    
                </c:forEach>
              
                <!-- emails -->
                <legend>Email<button class="btn btn-mini pull-right" type="submit" id="addEmail" name="addEmail" value="addEmail"><i class="icon-plus"></i> Add</button></legend>
                <c:forEach items="${contact.emailAddresses}" var="emailAddress" varStatus="emailAddressStatus">
                    <form:hidden path="emailAddresses[${emailAddressStatus.index}].id"/>
	              	<form:hidden path="emailAddresses[${emailAddressStatus.index}].createdAt"/>
	              	<form:hidden path="emailAddresses[${emailAddressStatus.index}].createdBy"/>
	              	<form:hidden path="emailAddresses[${emailAddressStatus.index}].version"/>
                    
                    <div class="control-group">
                        <label class="control-label" for="pemailAddresses[${emailAddressStatus.index}].email">Email</label>
                        <div class="controls">
                            <form:input path="emailAddresses[${emailAddressStatus.index}].email" type="email" />
                            <c:if test="${emailAddressStatus.index > 0}">
                            	<button class="btn btn-mini" type="submit" id="deleteEmail" name="deleteEmail" value="${emailAddressStatus.index}"><i class="icon-minus"></i>&nbsp;Delete</button>
                            </c:if>
                        </div>
                    </div>
                    
                </c:forEach>
             
        </fieldset>
        <div class="form-actions">
          <button type="submit" id="saveButton" name="save" value="Save" class="btn btn-primary">Save</button>
          <button type="submit" id="deleteButton" name="delete" value="Delete" class="btn btn-danger">Delete</button>
        </div>
	 </form:form>
    </div><!--/span-->
    <div class="span3">
      <div class="well sidebar-nav">
        <ol class="nav nav-list">
           <c:forEach items="${contactList}" var="contact" varStatus="contactStatus">
          	<li><a href="<c:url value="/contact/${contact.id}"/>">${contact.firstName} ${contact.lastName}</a></li>
          </c:forEach>
        </ol>
      </div><!--/.well -->
    </div><!--/span-->
  </div><!--/row-->
