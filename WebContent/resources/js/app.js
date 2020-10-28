$(document).ready(function(){	
	$("#add").click( function(){
		
		var postData = {"name":$("#name").val(),						
						"age":$("#age").val()};
		var postData1 = JSON.stringify(postData);
		alert(postData1);
		
		$.ajax({
			url:"http://localhost:8080/tiffin/rest/Services/add", 
			type:"POST",
			beforeSend: function(request){
				request.setRequestHeader("AUTH", "Basic " + btoa("sachin" + ":" + "abc123"));
			},
			dataType: "json",
			contentType: 'application/json; charset=utf-8',
			data:postData1,			
			 success: function(data) {				
				$("#title").text("Student with Name " + data.name + " & ID "+ data.id);
				$("#title").append(" is successfully added in the database.");
				$("input:text").val("");			
			},
			 error: function($xhr) {
				 var data = $xhr.responseJSON;  				
				$("#title").text("Student with Name " + data.name);				
				$("#title").append(" could not be added in the database.");
				$("input:text").val("");			
		    }	
		});		
	});		
});
