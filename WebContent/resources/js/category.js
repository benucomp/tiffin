
$(document).ready(function(){	
	$("#category_add").click( function(){
		alert("1");
		var postData = {"name":$("#name").val(),
				"rank":$("#rank").val(),
				"description":$("#description").val()};		
		var postData1 = JSON.stringify(postData);
		alert(postData1);
		$.ajax({
			url:"http://localhost:8080/proj4/rest/Services/category_add", 
			type:"POST",  
			dataType: "json",
			contentType: 'application/json; charset=utf-8',
			data:postData1,			
			success: function(data) {				
				$("#title").text("Student with Name " + data.name + " & ID "+ data.id);
				$("#title").append(" is successfully added in the database.");
				$("input:text").val("");				
			},
			error: function(r) {				
				$("#title").text("ERROR: " + r.status + " & " + r.responseText);				
			}					
		});		
	});		
});
