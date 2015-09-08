from django import forms
from .models import Comment

class CommentForm(forms.ModelForm):
    name = forms.CharField(max_length=200)
    email = forms.EmailField()
    content = forms.TextField()

    # An inline class to provide additional information on the form.
    class Meta:
        # Provide an association between the ModelForm and a model
        model = Comment
        fields = ('name',)