# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import datetime
from django.utils.timezone import utc


class Migration(migrations.Migration):

    dependencies = [
        ('coding', '0002_post_tag'),
    ]

    operations = [
        migrations.AddField(
            model_name='post',
            name='image',
            field=models.ImageField(upload_to='', default=datetime.datetime(2015, 8, 31, 11, 31, 19, 404638, tzinfo=utc)),
            preserve_default=False,
        ),
    ]
