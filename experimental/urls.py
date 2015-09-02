from django.conf.urls import url
from . import views

urlpatterns = [
# #the pattern is followed by the url pattern in urls.py in mysite
    url(r'^$', views.experimental, name='experimental'),
    url(r'^detail$', views.detail, name='detail'),
    url(r'^experimental_detail/sanya', views.experimental_detail_sanya, name='experimental_detail_sanya'),
    url(r'^experimental_detail/roma', views.experimental_detail_roma, name='experimental_detail_roma'),
    url(r'^experimental_detail/copenhagen', views.experimental_detail_copenhagen, name='experimental_detail_copenhagen'),
    url(r'^experimental_detail/milan', views.experimental_detail_milan, name='experimental_detail_milan'),
    url(r'^experimental_detail/esbjerg', views.experimental_detail_esbjerg, name='experimental_detail_esbjerg'),
    url(r'^experimental_detail/barcelona', views.experimental_detail_barcelona, name='experimental_detail_barcelona'),
                
]
