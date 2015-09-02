from django.db import models
from django.utils import timezone
import os


class Post(models.Model):
    #author = models.ForeignKey('auth.User')
    title = models.CharField(max_length=200)
    introduction=models.TextField()
    content = models.TextField()
    tag= models.CharField(max_length=200)
    #image=models.ImageField(upload_to='upload')
    published_date = models.DateTimeField(
            blank=True, null=True)

    def post(self):
        self.published_date = timezone.now()
        self.save()

    def __str__(self):
        return self.title
    
    def save(self):
        base, ext = os.path.splitext(os.path.basename(self.image.path))
        thumb_pixbuf = make_thumb(os.path.join(MEDIA_ROOT, self.image.name))
        relate_thumb_path = os.path.join(THUMB_ROOT, base + '.thumb' + ext)
        thumb_path = os.path.join(MEDIA_ROOT, relate_thumb_path)
        thumb_pixbuf.save(thumb_path)
        self.thumb = ImageFieldFile(self, self.thumb, relate_thumb_path)
        super(Media, self).save()    