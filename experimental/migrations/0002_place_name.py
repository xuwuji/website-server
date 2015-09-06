# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import datetime
from django.utils.timezone import utc


class Migration(migrations.Migration):

    dependencies = [
        ('experimental', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='place',
            name='name',
            field=models.CharField(max_length=200, default=datetime.datetime(2015, 9, 6, 7, 49, 43, 786462, tzinfo=utc)),
            preserve_default=False,
        ),
    ]
