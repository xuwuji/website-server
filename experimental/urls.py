from django.conf.urls import url
from . import views

urlpatterns = [
##the pattern is followed by the url pattern in urls.py in mysite
    url(r'^$', views.experimental, name='experimental'),
]
