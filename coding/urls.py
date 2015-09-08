from django.conf.urls import url
from . import views

urlpatterns = [
##the pattern is followed by the url pattern in urls.py in mysite
    url(r'^$', views.coding, name='coding'),
    #url(r'^detail', views.detail, name='detail'),
    url(r'^detail/(?P<pk>[0-9]+)/$', views.post_detail, name='post_detail'),
    url(r'^add_comment/$', views.add_comment, name='add_comment'),
]
