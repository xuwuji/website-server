<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${article.title}</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/RequestUrl.js"></script>
<link
	href="${pageContext.request.contextPath}/resources/css/article_index.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="blog-masthead">
		<div class="container">
			<nav class="blog-nav"> <a class="blog-nav-item " href="#">Home</a>
			<a class="blog-nav-item active" href="#">Coding</a> <a
				class="blog-nav-item" href="#">School</a> <a class="blog-nav-item"
				href="#">Experimental</a> <a class="blog-nav-item" href="#">Project</a>
			</nav>
		</div>
	</div>

	<div class="container">
		<br> <br>

		<div class="row">

			<!-- Article Main -->

			<div class="col-sm-8 blog-main">
				<!-- blog post STARTS HERE... -->
				<div class="blog-post">
					<h2 class="blog-post-title">${article.title}</h2>
					<p class="blog-post-meta">
						${article.time} <a href="#">&emsp;&emsp;&emsp;${article.category}
						</a><br>
					<p id="article_tags"></p>
					<hr>
					</p>
					<p>${article.content}</p>
				</div>

				<script type="text/javascript">
					var tags = "${article.tags}";
					tags = tags.split(" ");
					function StringBuffer() {
						this.__strings__ = [];
					};
					StringBuffer.prototype.append = function(str) {
						this.__strings__.push(str);
					};
					StringBuffer.prototype.toString = function() {
						return this.__strings__.join('');
					};

					var line = new StringBuffer();
					$
							.each(
									tags,
									function(index, tag) {
										line
												.append('<button type="button" class="btn btn-sm btn-default">'
														+ tag
														+ '</button>&emsp;');
									});
					$('#article_tags').html(line.toString());
				</script>
				<!-- blog post ENDS HERE... -->



				<h2 class="page-header">Comments</h2>
				<!--add comment STARTS HERE....-->

				<div>
					Email:<br> <input type="text" id="new_comment_username">
					<br> Say something:<br>
					<div class="input-group">
						<input class="form-control" placeholder="Add a comment ;)"
							type="text"> <span class="input-group-addon"> <a
							href="#" onclick=""><i class="fa fa-edit"></i></a>
						</span>
					</div>
					<br>
					<button onclick="add_comment()">submit</button>
				</div>

				<!--add comment ENDS HERE....-->

				<br> <br>



				<div>
					<!--show comment STARTS HERE....-->
					<div class="container" style="padding-left: 0px;">
						<div class="col-sm-8" style="padding-left: 0px;">
							<div class="panel panel-white post panel-shadow">
								<div class="post-heading">
									<div class="pull-left image">
										<img src="http://bootdey.com/img/Content/user_1.jpg"
											class="img-circle avatar" alt="user profile image">
									</div>
									<div class="pull-left meta">
										<div class="title h5">
											<a href="#"><b>Ryan Haywood</b></a> made a post.
										</div>
										<h6 class="text-muted time">1 minute ago</h6>
									</div>
								</div>
								<div class="post-description">
									<p>Bootdey is a gallery of free snippets resources
										templates and utilities for bootstrap css hmtl js framework.
										Codes for developers and web designers</p>
									<div class="stats">
										<a href="#" class="btn btn-default stat-item"> <i
											class="fa fa-thumbs-up icon"></i>2
										</a> <a href="#" class="btn btn-default stat-item"> <i
											class="fa fa-share icon"></i>12
										</a>
									</div>
								</div>
								<div class="post-footer">
									<div class="input-group">
										<input class="form-control" placeholder="Add a comment"
											type="text"> <span class="input-group-addon">
											<a href="#"><i class="fa fa-edit"></i></a>
										</span>
									</div>
									<ul class="comments-list">
										<li class="comment"><a class="pull-left" href="#"> <img
												class="avatar"
												src="http://bootdey.com/img/Content/user_1.jpg" alt="avatar">
										</a>
											<div class="comment-body">
												<div class="comment-heading">
													<h4 class="user">Gavino Free</h4>
													<h5 class="time">5 minutes ago</h5>
												</div>
												<p>Sure, oooooooooooooooohhhhhhhhhhhhhhhh</p>
											</div>
											<ul class="comments-list">
												<li class="comment"><a class="pull-left" href="#">
														<img class="avatar"
														src="http://bootdey.com/img/Content/user_3.jpg"
														alt="avatar">
												</a>
													<div class="comment-body">
														<div class="comment-heading">
															<h4 class="user">Ryan Haywood</h4>
															<h5 class="time">3 minutes ago</h5>
														</div>
														<p>Relax my friend</p>
													</div></li>
												<li class="comment"><a class="pull-left" href="#">
														<img class="avatar"
														src="http://bootdey.com/img/Content/user_2.jpg"
														alt="avatar">
												</a>
													<div class="comment-body">
														<div class="comment-heading">
															<h4 class="user">Gavino Free</h4>
															<h5 class="time">3 minutes ago</h5>
														</div>
														<p>Ok, cool.</p>
													</div></li>
											</ul></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!--show comment ENDS HERE....-->
				</div>






























				<!--show comment STARTS HERE....-->
				<div class="container" style="padding-left: 0px;">
					<div class="col-sm-8" style="padding-left: 0px;">
						<div class="panel panel-white post panel-shadow" id="comment_div">


						</div>
					</div>
				</div>
				<!--show comment ENDS HERE....-->
			</div>





			<!--sideBar STARTS HERE....-->
			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="sidebar-module sidebar-module-inset">
					<h4>About</h4>
					<p>
						Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras
						mattis consectetur purus sit amet fermentum. Aenean lacinia
						bibendum nulla sed consectetur.
					</p>
				</div>
				<div class="sidebar-module">
					<h4>Archives</h4>
					<ol class="list-unstyled">
						<li><a href="#">March 2014</a></li>
						<li><a href="#">February 2014</a></li>
						<li><a href="#">January 2014</a></li>
						<li><a href="#">December 2013</a></li>
						<li><a href="#">November 2013</a></li>
						<li><a href="#">October 2013</a></li>
						<li><a href="#">September 2013</a></li>
						<li><a href="#">August 2013</a></li>
						<li><a href="#">July 2013</a></li>
						<li><a href="#">June 2013</a></li>
						<li><a href="#">May 2013</a></li>
						<li><a href="#">April 2013</a></li>
					</ol>
				</div>
				<div class="sidebar-module">
					<h4>Elsewhere</h4>
					<ol class="list-unstyled" id="categories_li">
					</ol>
				</div>
			</div>
			<!--sideBar ENDS HERE....-->

		</div>

	</div>

</body>
<script type="text/javascript">
	function StringBuffer() {
		this.__strings__ = [];
	};
	StringBuffer.prototype.append = function(str) {
		this.__strings__.push(str);
	};
	StringBuffer.prototype.toString = function() {
		return this.__strings__.join('');
	};

	window.onload = getComment();

	function add_comment() {
		var username = $('#new_comment_username').val();
		var content = $('#new_comment_content').val();
		var article_id = '${article.id}'
		$.ajax({
			url : add_new_comment_url,
			type : 'POST',
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({
				"article_id" : article_id,
				"content" : content,
				"user" : username,
			}),
		}).done(function() {
			console.log("success");
		}).fail(function() {
			console.log("error");
		}).always(function() {
			console.log("complete");
			getComment();
		});
	}

	function getComment() {
		var line = new StringBuffer();
		var url = get_comment_url + "${article.id}";
		$
				.getJSON(
						url,
						function(data) {
							$
									.each(
											data,
											function(index, comment) {
												var up_id = "up_" + comment.id;
												//var down_id = comment.id + "_down";
												line
														.append(' <div class="post-heading"><div class="pull-left image"><img src="http://bootdey.com/img/Content/user_1.jpg"class="img-circle avatar"alt="user profile image"></div><div class="pull-left meta">'
																+ '<div class="title h5"><a href="#"><b>'
																+ comment.user
																+ '</b></a> made a post.</div><h6 class="text-muted time">'
																+ comment.time
																+ '</h6></div></div><div class="post-description"><p>'
																+ comment.content
																+ '</p><div class="stats">'
																+ '<a  class="btn btn-default stat-item" id=\''
																+ up_id
																+ '\' onclick=\'up('
																+ comment.id
																+ ')\'> <i class="fa fa-thumbs-up icon"></i>'
																+ comment.up
																+ '</a> <a href="#" class="btn btn-default stat-item"> <i class="fa fa-share icon"></i>12</a></div></div><hr>');
											});
							//console.log(line.toString());
							$('#comment_div').html(line.toString());
						});
	}

	function up(commentId) {
		var url = comment_up_url + commentId;
		//var id = '#' + commentId + "_up";
		//console.log(id);
		var $up_place = $("#up_" + commentId);
		var value = parseInt($up_place.text()) + 1;
		console.log(value);
		$.getJSON(url, function(data) {
			console.log("value");
			$up_place.text(value);
		});
	}
</script>

</html>