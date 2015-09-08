from django.shortcuts import render,get_object_or_404
from django.utils import timezone
from .models import Post
from django.template import Context, Template
from .forms import CommentForm
# Create your views here.

#coding index page view
def coding(request):
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
    #for group in new_post_list:
    #    print(group)
    #c = Context({"loop_times": range(len(new_post_list))})
    return render(request, 'coding/coding.html',{'list': new_post_list})


#post detail page view
def post_detail(request, pk):
    post = get_object_or_404(Post, pk=pk)
    return render(request, 'coding/detail.html', {'post': post})

from rango.forms import CategoryForm

def add_comment(request):
    # A HTTP POST?
    if request.method == 'POST':
        form = CommentForm(request.POST)

        # Have we been provided with a valid form?
        if form.is_valid():
            # Save the new category to the database.
            form.save(commit=True)

            # Now call the index() view.
            # The user will be shown the homepage.
            return index(request)
        else:
            # The supplied form contained errors - just print them to the terminal.
            print form.errors
    else:
        # If the request was not a POST, display the form to enter details.
        form = CommentForm()

    # Bad form (or form details), no form supplied...
    # Render the form with error messages (if any).
    return render(request, 'rango/add_category.html', {'form': form})

