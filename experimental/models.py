from django.db import models

# Create your models here.
class Place(models.Model):
    #author = models.ForeignKey('auth.User')
    name= models.CharField(max_length=200)
    title = models.CharField(max_length=200)
    introduction=models.TextField()
    date = models.CharField(max_length=200)
    #content = models.TextField()
    #tag= models.CharField(max_length=200)
    #image=models.ImageField(upload_to='upload')
    logo = models.ImageField(upload_to='place_logo_image', blank=True)

    def post(self):
        self.save()

    def __str__(self):
        return self.name