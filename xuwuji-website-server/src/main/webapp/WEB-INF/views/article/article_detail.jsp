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



				<div class="col-md-6">
					<div class="widget-area no-padding blank">
						<div class="status-upload">
							<form>
								<textarea placeholder="Ask our Therapist a question now.."></textarea>
								<button type="submit" class="btn btn-success green">
									Chat Now
								</button>
							</form>
						</div>
						<!-- Status Upload  -->
					</div>
					<!-- Widget Area -->
				</div>

			</div>

			<!-- blog comment STARTS HERE... -->
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<h2 class="page-header">Comments</h2>
						<section class="comment-list"> <!-- First Comment --> <article
							class="row">
						<div class="col-md-2 col-sm-2 hidden-xs">
							<figure class="thumbnail"> <img class="img-responsive"
								src="http://www.keita-gaming.com/assets/profile/default-avatar-c5d8ec086224cb6fc4e395f4ba3018c2.jpg" />
							<figcaption class="text-center">username</figcaption> </figure>
						</div>
						<div class="col-md-10 col-sm-10">
							<div class="panel panel-default arrow left">
								<div class="panel-body">
									<header class="text-left">
									<div class="comment-user">
										<i class="fa fa-user"></i> That Guy
									</div>
									<time class="comment-date" datetime="16-12-2014 01:05">
									<i class="fa fa-clock-o"></i> Dec 16, 2014</time> </header>
									<div class="comment-post">
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit, sed do eiusmod tempor incididunt ut labore et dolore
											magna aliqua. Ut enim ad minim veniam, quis nostrud
											exercitation ullamco laboris nisi ut aliquip ex ea commodo
											consequat.</p>
									</div>
									<p class="text-right">
										<a href="#" class="btn btn-default btn-sm"><i
											class="fa fa-reply"></i> reply</a>
									</p>
								</div>
							</div>
						</div>
						</article> <!-- Second Comment Reply --> <article class="row">
						<div
							class="col-md-2 col-sm-2 col-md-offset-1 col-sm-offset-0 hidden-xs">
							<figure class="thumbnail"> <img class="img-responsive"
								src="http://www.keita-gaming.com/assets/profile/default-avatar-c5d8ec086224cb6fc4e395f4ba3018c2.jpg" />
							<figcaption class="text-center">username</figcaption> </figure>
						</div>
						<div class="col-md-9 col-sm-9">
							<div class="panel panel-default arrow left">
								<div class="panel-heading right">Reply</div>
								<div class="panel-body">
									<header class="text-left">
									<div class="comment-user">
										<i class="fa fa-user"></i> That Guy
									</div>
									<time class="comment-date" datetime="16-12-2014 01:05">
									<i class="fa fa-clock-o"></i> Dec 16, 2014</time> </header>
									<div class="comment-post">
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit, sed do eiusmod tempor incididunt ut labore et dolore
											magna aliqua. Ut enim ad minim veniam, quis nostrud
											exercitation ullamco laboris nisi ut aliquip ex ea commodo
											consequat.</p>
									</div>
									<p class="text-right">
										<a href="#" class="btn btn-default btn-sm"><i
											class="fa fa-reply"></i> reply</a>
									</p>
								</div>
							</div>
						</div>
						</article> </section>
					</div>
				</div>
			</div>

			<!-- blog comment ENDS HERE... -->
		</div>

		<!-- /.blog-main -->

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
		<!-- /.blog-sidebar -->

	</div>
	<!-- /.row -->

	</div>
	<!-- /.container -->

</body>
</html>