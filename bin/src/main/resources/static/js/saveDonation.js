const saveDonation = donation => {
    const url = "/secure/utility/saveDonation";

    fetch(url, {
        method: 'post',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: donation
    });
}

console.log('test');