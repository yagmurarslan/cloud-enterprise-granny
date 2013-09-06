<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"> </script>
	
	<script type="text/javascript">
	//create an object to store our functions and variables
	
	var app = {
	     //populate the list box
	    getAllAddresses: function () {
	        $.getJSON("addresses", function(data){
	            // data = data.data;
	            
	            var entries = [];
	            entries.push("<option value='' />");
	            $.each(data, function(){
	                entries.push("<option name='"+ this.name + "' value='"+ this._id+"'>"+ this.name +"</option>");
	            });
	            
	            $("#addressList").empty();
	            $(entries.join("")).appendTo("#addressList");
	        });
	        
	    },
	     //save addresses
	    saveAddress: function(){
	        var address = app.makeAddress();
	        if(address._id === "" || address._id.length <1) { app.createAddress(address); return;}
	        $.ajax({
	            url:"update",
	            type:"put",
	            contentType:"application/json",
	            processData: false,
	            data: JSON.stringify(address),
	            success: function() { app.getAllAddresses(); }
	        });
	        
	    },
	    createAddress: function(address) {
	//          delete address._id;
	          $.ajax({
	                  url:"create",
	                  type:"post",
	                  contentType:"application/json",
	                  processData: false,
	                  data: JSON.stringify(address),
	                  success: function() { app.getAllAddresses(); }
	           });
	    },
	    makeAddress: function(){
	        var addressObject = {
	        	_id: $("#selectedIndexField").attr("value"),
	        	name: $("#nameField").attr("value"),
	            address: $("#addressField").attr("value"),
	            phone: $("#phoneField").attr("value"),
	            email: $("#emailField").attr("value")           
	        };
	        return addressObject;
	     }, 
	    emptyUser: {
	    		_id:"",
	            name: "",
	            address: "",
	            phone: "",
	            email: ""
	    },
	    getAddressById: function (id){
	        if(id){
	             $.ajax({
	                 url:"get/"+id,
	                 type:"get",
	                 success: function(data) { app.populateFields(data); }
	             });
	        }
	        else{
	            app.populateFields(app.emptyUser);
	            
	        }
	    },
	    
	    populateFields: function(addressJSON){
	        $("#nameField").attr("value", addressJSON.name);
	        $("#addressField").attr("value", addressJSON.address);
	        $("#phoneField").attr("value", addressJSON.phone);
	        $("#emailField").attr("value", addressJSON.email);
	        $("#selectedIndexField").val(addressJSON._id || "");
	    },
	    
	    deleteAddress: function(){
	        var address = app.makeAddress();
	     // var currentName = $("#nameField").attr("value");
	        $.ajax({
	            url:"delete",
	            type:"delete",
	            contentType:"application/json",
	            data: JSON.stringify(address),
	            success: function() {
	                app.getAllAddresses();
	                app.populateFields(app.emptyUser);
	            }
	        });
	    }
	
	
	};
	
	//after the page loads
	$(function () {
	    app.getAllAddresses();
	    $("#addressList").on("click","option", function(){ app.getAddressById(this.value); });
	});
	</script>