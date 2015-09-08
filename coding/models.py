from django.db import models
from django.utils import timezone
import os


class Post(models.Model):
    #author = models.ForeignKey('auth.User')
    title = models.CharField(max_length=200)
    introduction=models.TextField()
    content = models.TextField()
    tag= models.CharField(max_length=200)
    image=models.ImageField(upload_to='coding_post_img_logo', blank=True)
    published_date = models.DateTimeField(
            blank=True, null=True)

    def post(self):
        self.published_date = timezone.now()
        self.save()

    def __str__(self):
        return self.title
    