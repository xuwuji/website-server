# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('coding', '0006_auto_20150908_1452'),
    ]

    operations = [
        migrations.AlterField(
            model_name='post',
            name='logo',
            field=models.ImageField(blank=True, default='0', upload_to='coding_post_logo'),
        ),
        migrations.AlterField(
            model_name='post',
            name='post_image',
            field=models.ImageField(blank=True, default='0', upload_to='coding_post_img'),
        ),
    ]
