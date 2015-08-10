<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload</title>
</head>
<body>
	File should be minimum 320px by 240px
	<br />
	<form id="vcard" method="post" enctype="multipart/form-data">
		Select file to upload: <input id="card" type="file" name="front" /> <br />
		<br /> <input type="submit" value="Upload" />
	</form>
	<div id="status"></div>
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#vcard").submit(function(event){
				event.preventDefault();
				$("#status").html("<b>Uploading, please wait...</b>");
				console.log('submitted');
				//var formData = $(this).serialize();
				var formData = new FormData($('form')[0]);
				var url = 'https://api.fullcontact.com/v2/cardReader?webhookUrl=http%3A%2F%2Finterns.teamchat.com%3A8085%2FFullContact%2FFullContactCard%3Froom%3D<%=request.getParameter("room")%>&apiKey='
				$.ajax({
	                type: "POST",
	                url: url,
	                data: formData,
	                async: false,
	                cache: false,
	                contentType: false,
	                processData: false,
	                success: function(resp) {
	                    console.log("success",resp);
;	                    var successText = "<br/><br/><b>Request Id: </b>" + resp.id + "<br/><b>Estimated wait time: </b>" + resp.estimatedWaitTimeMinutes + " minutes <br/><h2><b>Go back to teamchat</b></h2>"
	                    $("#status").html(successText);
	                },
	                error: function(resp) {
	                     console.log("error",resp);
	                     $("#status").html(JSON.stringify(resp));
	                }
	            });
				return false;
			});
		});
	</script>
</body>
</html>
