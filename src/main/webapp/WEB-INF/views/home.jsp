<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>
    <tiles:getAsString name="title" ignore="true" />
</h2>

<div id="content">
    <form id="addressForm" name="addressForm">
        <input id="selectedIndexField" name="selectedIndex" type="hidden" value=""/>
        <input id="oldNameField" name="oldName" type="hidden" value=""/>
        <div id="nameEntry">
            <div id="nameLabel" class="label">Name</div><input id="nameField" name="name" type="text" value=""/>
        </div>
        <div id="addressEntry">
            <div id="addressLabel" class="label">Address</div><input id="addressField" name="address" type="text" value=""/>
        </div>  
        <div id="phoneEntry">
            <div id="phoneLabel" class="label">Phone</div><input id="phoneField" name="phone" type="text" value=""/>
        </div>   
        <div id="emailEntry">
            <div id="emailLabel" class="label">Email</div><input id="emailField" name="email" type="text" value=""/>
        </div>
        <div id="saveEntry">
            <input id="saveButton" value="Save" type="button" onClick="app.saveAddress()"/>
        </div>  
        <div id="listEntry"> 
            <div id="list">
                <select size="10" id="addressList" name="addressList">                  
                </select>
            </div>
        </div>
        <div id="deleteEntry">
            <input id="deleteButton" value="Delete" onClick="app.deleteAddress()" type="button"/>
        </div>  
    </form>
</div>

