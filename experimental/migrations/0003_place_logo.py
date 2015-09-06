# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('experimental', '0002_place_name'),
    ]

    operations = [
        migrations.AddField(
            model_name='place',
            name='logo',
            field=models.ImageField(upload_to='place_logo_image', blank=True),
        ),
    ]
