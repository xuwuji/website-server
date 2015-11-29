<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/RequestUrl.js"></script>
<title>Add an Article</title>
</head>
<body>

	<h1>
		Add Articles
		</hi>
		<div>

			<form>

				Title:<input type="text" name="title" id="title" rows="10" cols="80">
				</input><br> 
				Category:<input type="text" name="category" id="category"
					rows="10" cols="80"> </input><br> 
				Tags:<input type="text"
					name="tags" id="tags" rows="10" cols="80"> </input><br>
				<textarea name="editor1" id="editor1" rows="10" cols="80">
                This is my textarea to be replaced with CKEditor.</textarea>
				<script>
					// Replace the <textarea id="editor1"> with a CKEditor
					// instance, using default configuration.
					CKEDITOR.replace('editor1');
				</script>
			</form>
		</div>
		<button type='button' onclick='getData()' class="btn btn-default">save</button>
</body>


<script>
	function getData() {
		var data = CKEDITOR.instances.editor1.getData();
		$.ajax({
			url : save_url,
			type : 'POST',
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({
				"title" : $('#title').val(),
				"content" : data,
				"category" : $('#category').val(),
				"tags" : $('#tags').val(),
			}),
		}).done(function() {
			console.log("success");
		}).fail(function() {
			console.log("error");
		}).always(function() {
			console.log("complete");
		});

		console.log(data);

	}
</script>
</html>