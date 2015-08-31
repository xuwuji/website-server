from django.shortcuts import render,get_object_or_404
from django.utils import timezone
from .models import Post
from django.template import Context, Template
# Create your views here.
def coding(request):
    post_list=Post.objects.filter(published_date__lte=timezone.now()).order_by('-published_date')
    return render(request, 'coding/coding.html',{'list': post_list})

def detail(request):
    #post_list=Post.objects.filter(published_date__lte=timezone.now()).order_by('-published_date')
    return render(request, 'coding/details.html',{})

def post_detail(request, pk):
    post = get_object_or_404(Post, pk=pk)
    return render(request, 'coding/details_test.html', {'post': post})


def test(request):
    # reverse time order
    posts = Post.objects.filter(published_date__lte=timezone.now()).order_by('-published_date')
    new_post_list=[]
    sublist = []
    for index in range(len(posts)):  
        if(index == (len(posts) - 1)):
             sublist.append(posts[index])
             new_post_list.append(sublist)
        elif(index == 0 or index % 3 != 0):
            sublist.append(posts[index])
        else:
            new_post_list.append(sublist)
            sublist = []
            sublist.append(posts[index])
    for group in new_post_list:
        print(group)
    c = Context({"loop_times": range(len(new_post_list))})
    #context['loop_times'] = range(len(new_post_list))
    return render(request, 'coding/coding_test.html',{'list': new_post_list},c)