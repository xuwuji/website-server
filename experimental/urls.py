from django.conf.urls import url
from . import views

urlpatterns = [
# #the pattern is followed by the url pattern in urls.py in mysite
    url(r'^$', views.experimental, name='experimental'),
    url(r'^detail$', views.detail, name='detail'),
    url(r'^experimental_detail/(?P<place_name>sanya)/$', views.experimental_detail, name='experimental_detail_sanya'),
    url(r'^experimental_detail/(?P<place_name>roma)/$', views.experimental_detail, name='experimental_detail_roma'),
    url(r'^experimental_detail/(?P<place_name>copenhagen)/$', views.experimental_detail, name='experimental_detail_copenhagen'),
    url(r'^experimental_detail/(?P<place_name>milan)/$', views.experimental_detail, name='experimental_detail_milan'),
    url(r'^experimental_detail/(?P<place_name>esbjerg)/$', views.experimental_detail, name='experimental_detail_esbjerg'),
    url(r'^experimental_detail/(?P<place_name>barcelona)/$', views.experimental_detail, name='experimental_detail_barcelona'),
                
]
