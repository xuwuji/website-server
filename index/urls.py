from django.conf.urls import url
from . import views
from django.views.generic import RedirectView

urlpatterns = [
##the pattern is followed by the url pattern in urls.py in mysite
    url(r'^$', views.index, name='index'),
]
