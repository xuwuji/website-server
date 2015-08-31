from django.shortcuts import render
from django.utils import timezone
# Create your views here.
def experimental(request):
    return render(request, 'experimental/experimental.html',{})