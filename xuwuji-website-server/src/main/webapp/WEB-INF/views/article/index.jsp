<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Coding</title>
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

		<div class="blog-header">
			<h1 class="blog-title">The Bootstrap Blog</h1>
			<p class="lead blog-description">The official example template of
				creating a blog with Bootstrap.</p>
		</div>

		<div class="row">
			<div class="col-sm-8 blog-main">
				<div id="content_div"></div>
				<div class="col-sm-8 blog-main">
					<nav id="nav_id">
					<ul class="pager">
						<li><a id="previous_page" onclick='previousPage()'>Previous</a></li>
						<span id='current_page'></span>
						<li><a id="next_page" onclick='nextPage()'>Next</a></li>
					</ul>
					</nav>

				</div>
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
					<h4>Category</h4>
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

	var line = new StringBuffer();

	/*by defualt, show the latest 10 articles starts here....*/
	$
			.getJSON(
					"http://localhost:8080/xuwuji-website-server/api/article/front/getArticles/page/"
							+ "1",
					function(data) {
						var content = new StringBuffer();
						$
								.each(
										data,
										function(i, article) {
											//console.log(article.title);
											var href = 'http://localhost:8080/xuwuji-website-server/api/article/front/detail/'
													+ article.id
											content
													.append('<!-- one article begins here...--><div class=\"blog-post\"><a href=\''+href+'\'><h5 class="blog-post-title">'
															+ article.title
															+ '</h5></a><p class="blog-post-meta">'
															+ article.time
															+ '&emsp;&emsp;&emsp;<a href="#">'
															+ article.category
															+ '</a></p><p>'
															+article.content.split("</p>")[0]+article.content.split("</p>")[1]+"<br><br><br>......"
															+ '</p></div><hr><!-- one article ends here... -->');

										});
						// console.log(content.toString());
						var $content_div = $('#content_div');
						$content_div.html(content.toString());
					});
	setInfo(1);

//get the categories
	$
			.getJSON(
					'http://localhost:8080/xuwuji-website-server/api/article/front/getInfo/categories',
					function(data) {
						//console.log(data);
						var category_line = new StringBuffer();
						$
								.each(
										data,
										function(index, category) {
											//console.log('category:' + category);
											
												category_line
														.append('<li><a onclick="getArticlesByCategory(\''
																+ category
																+ '\')">'
																+ category
																+ '</a></li>');
											
										});
						var $categories_li = $('#categories_li');
						// console.log(category_line.toString());
						$categories_li.html(category_line.toString());
					});

	/*by defualt, show the latest 10 articles ends here...*/

	/*  set the current general info starts here ...*/
	function setInfo(pageNum) {
		var url = 'http://localhost:8080/xuwuji-website-server/api/article/front/getInfo/'
				+ pageNum;
		$.getJSON(url, function(data) {
			var current_page_number = data.current_page_number;
			var $current_page = $('#current_page');
			var max_page = data.max_page;
			$current_page.text(current_page_number + ' / ' + max_page);
		});
	}
	/*  set the general info function ends here ...*/

	/* show a perticular article page function starts here ...*/
	function getPage(pagenumber) {
		var line = new StringBuffer();
		var url = 'http://localhost:8080/xuwuji-website-server/api/article/front/getArticles/page/'
				+ pagenumber;
		//console.log(url);
		$
				.getJSON(
						url,
						function(data) {
							$
									.each(
											data,
											function(index, article) {
												var href = 'http://localhost:8080/xuwuji-website-server/api/article/front/detail/'
														+ article.id
												line
														.append('<!-- one article begins here...--><div class=\"blog-post\"><a href=\''+href+'\'><h5 class="blog-post-title">'
																+ article.title
																+ '</h5><a><p class="blog-post-meta">'
																+ article.time
																+ '&emsp;&emsp;&emsp;<a href="#">'
																+ article.category
																+ '</a></p><p>'
																+article.content.split("</p>")[0]+article.content.split("</p>")[1]+"<br><br><br>......"
																+ '</p></div><hr><!-- one article ends here... -->');
											});
							//console.log(line.toString());
							var $content_div = $('#content_div');
							$content_div.html(line.toString());
						});
	}
	/* show a perticular article page function ends here ...*/

	/* show a previous article page function starts here ...*/
	function previousPage() {
		var $current_page = $('#current_page');
		var current_page_number = $current_page.text();
		current_page_number = current_page_number.slice(0, current_page_number
				.indexOf('/'));
		if (parseInt(current_page_number) == 1) {
		} else {
			getPage(parseInt(current_page_number) - 1);
			setInfo(parseInt(current_page_number) - 1);
		}
	}

	/* show a previous article page function ends here ...*/

	/* show a next article page function starts here ...*/
	function nextPage() {
		var $current_page = $('#current_page');
		var current_page_number = $current_page.text();
		current_page_number = current_page_number.slice(0, current_page_number
				.indexOf('/'));
		var url = 'http://localhost:8080/xuwuji-website-server/api/article/front/getInfo/'
				+ current_page_number;
		$.getJSON(url, function(data) {
			var max_page = data.max_page;
			if (parseInt(current_page_number) == parseInt(max_page)) {
			} else {
				getPage(parseInt(current_page_number) + 1);
				setInfo(parseInt(current_page_number) + 1);
			}
		});
	}
	/* show a next article page function ends here ...*/

	/* show article pages of a category function starts here ...*/

	function getArticlesByCategory(category) {
		$
				.getJSON(
						'http://localhost:8080/xuwuji-website-server/api/article/front/getArticles/category/'
								+ category,
						function(data) {
							var line = new StringBuffer();
							$
									.each(
											data,
											function(index, article) {
												line
														.append('<!-- one article begins here...--><div class=\"blog-post\"><h5 class="blog-post-title">'
																+ article.title
																+ '</h5><p class="blog-post-meta">'
																+ article.time
																+ '&emsp;&emsp;&emsp;<a href="#">'
																+ article.category
																+ '</a></p><p>'
																+article.content.split("</p>")[0]+article.content.split("</p>")[1]+"<br><br><br>......"
																+ '</p></div><!-- one article ends here... -->');
											});
							var $content_div = $('#content_div');
							$content_div.html(line.toString());
							$('#nav_id').text("");
						});
	}

	/* show article pages of a category function ends here ...*/
</script>

</html>
