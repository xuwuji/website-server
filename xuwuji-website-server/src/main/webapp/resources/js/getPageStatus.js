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
		.getJSON(
				info_url,
				function(data) {
					var content = new StringBuffer();
					$
							.each(
									data,
									function(i, article) {
										console.log(article.title);

										content
												.append('<!-- one article begins here...--><div class=\"blog-post\"><h2 class="blog-post-title">'
														+ article.title
														+ '</h2><p class="blog-post-meta">'
														+ article.time
														+ '<a href="#">Mark</a></p><p>This blog post shows a few different types of content that\'s supported and styled with Bootstrap. Basic typography, images, and code are all supported.</p><hr><p>Cum sociis natoque penatibus et magnis <a href="#">dis parturient montes</a>, nascetur ridiculus mus. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Sed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.</p><blockquote><p>Curabitur blandit tempus porttitor. <strong>Nullam quis risus eget urna mollis</strong> ornare vel eu leo. Nullam id dolor id nibh ultricies vehicula ut id elit.</p></blockquote><p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p></div><!-- one article ends here... -->');

									});

					// console.log(content.toString());
					var $content_div = $('#content_div');
					$content_div.html(content.toString());

				});
