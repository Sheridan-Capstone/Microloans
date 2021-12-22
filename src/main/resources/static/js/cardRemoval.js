const cardRemoval = url => btnSelector => {
    const removeFavouriteBtn = document.querySelectorAll(btnSelector);

    removeFavouriteBtn.forEach(btn => {
        btn.addEventListener('click', e => {
           const id = btn.getAttribute('data-id');

           fetch(url, {
               method: 'post',
               headers: {
                   'Content-Type': 'text/plain'
               },
               body: id
           }).then(() => {
                btn.parentNode.parentNode.remove();
           });
        });
    });
};