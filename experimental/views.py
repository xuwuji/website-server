from django.shortcuts import render,get_object_or_404
from django.utils import timezone
from django.shortcuts import render_to_response  
from .models import Place
import os
import re
# Create your views here.
def experimental(request):
    return render(request, 'experimental/experimental.html', {})

def detail(request):
    return render(request, 'experimental/experimental_details.html', {})

def experimental_detail(request, place_name):
    place=get_object_or_404(Place, name=place_name)
    image_path = (os.path.join(os.path.dirname(__file__), "static", "images", place_name))
    fileNames = os.listdir(image_path) 
    print(fileNames)
    # print(image_path)
    pair_list=[]
    for i in fileNames:
        tag = re.search('^[a-z]', i)
        if tag:
            s1 = str(i)
            small_img=s1[4:]
            pair_list.append([s1,small_img])
    return render(request, 'experimental/experimental_details.html', {'pair_list':pair_list,'place':place})

def experimental_detail_sanya(request):
    image_path = (os.path.join(os.path.dirname(__file__), "static", "images", "sanya"))
    roma_image_path = (os.path.join(os.path.dirname(__file__), "static", "images", "roma"))
    fileNames = os.listdir(image_path) 
    print(fileNames)
    # print(image_path)
    pair_list=[]
    for i in fileNames:
        tag = re.search('^[a-z]', i)
        if tag:
            s1 = str(i)
            small_img=s1[4:]
            pair_list.append([s1,small_img])
            #print(pair_list)  
    #print(pair_list)
    return render(request, 'experimental/experimental_details_sanya.html', {'pair_list':pair_list})


def experimental_detail_roma(request):
    return render(request, 'experimental/experimental_details_roma.html', {})
def experimental_detail_copenhagen(request):
    return render(request, 'experimental/experimental_details_copenhagen.html', {})
def experimental_detail_milan(request):
    return render(request, 'experimental/experimental_details_milan.html', {})
def experimental_detail_esbjerg(request):
    return render(request, 'experimental/experimental_details_esbjerg.html', {})
def experimental_detail_barcelona(request):
    return render(request, 'experimental/experimental_details_barcelona.html', {})
