window.addEventListener('DOMContentLoaded', function(){
    const addToFavouriteBtn = document.querySelectorAll(".addToFavourite");

    addToFavouriteBtn.forEach(btn => {
        btn.addEventListener('click', e => {
           const id = btn.getAttribute('data-id');

           fetch('/secure/utility/addToFavourite', {
               method: 'post',
               headers: {
                   'Content-Type': 'text/plain'
               },
               body: id
           });
        });
    });
});

