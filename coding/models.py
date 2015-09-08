from django.db import models
from django.utils import timezone
import os


class Post(models.Model):
    #author = models.ForeignKey('auth.User')
    title = models.CharField(max_length=200)
    introduction=models.TextField()
    content = models.TextField()
    tag= models.CharField(max_length=200)
    logo=models.ImageField(upload_to='coding_post_logo', blank=True,default='0')
    post_image=models.ImageField(upload_to='coding_post_img', blank=True,default='0')
    published_date = models.DateTimeField(
            blank=True, null=True)

    def post(self):
        self.published_date = timezone.now()
        self.save()

    def __str__(self):
        return self.title
    
    
class Comment(models.Model):
    post= models.ForeignKey(Post, related_name='post_comment')
    name=models.CharField(max_length=200)
    email=models.CharField(max_length=200)
    content=models.TextField()
    
    
    def post(self):
        self.save()

    def __str__(self):
        return post.title
    
    