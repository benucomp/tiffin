$(document).ready(function(){
	
	$("#addUser").click( function(){

		var postData = {"userName":"test1",						
				"userEmail":"testEmail",
				"userPhone":"9191919191",						
				"userPwd":"pwd",
				"owner":"true"};

		var postData1 = JSON.stringify(postData);
		alert(postData1);

		$.ajax({
			url:"http://localhost:8080/tiffin/rest/Services/addUser", 
			type:"POST",
			beforeSend: function(request){
				request.setRequestHeader("Authorization", "Basic " + btoa("sachin" + ":" + "abc123"));
			},
			dataType: "json",
			contentType: 'application/json; charset=utf-8',
			data:postData1,			
			success: function(data) {				
				$("#title").text("User with Name " + data.userName + " & ID "+ data.userId);
				$("#title").append(" is successfully added in the database.");
				$("#title").append(" The added User's email ID is " + data.userEmail);
				$("#title").append(" and contact phone is " + data.userPhone);
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
