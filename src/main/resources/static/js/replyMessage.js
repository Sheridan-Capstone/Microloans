document.querySelectorAll(".replyMessageBtn").forEach(btn => btn.addEventListener("click", function(e){
    e.preventDefault();

    const sender = this.getAttribute("data-sender");
    const receiver = this.getAttribute("data-receiver");
    const body = this.getAttribute("data-body");
    const messageBox = document.querySelector("#message")

    const replyBody = `
    -------------------------
    From: ${sender} 
    To: ${receiver}

    ${body}
    `;

    const studentSelectBox = document.querySelector("#student");
    const senderIndex = Array.from(studentSelectBox.options).filter(x => x.value === sender);

    if(senderIndex.length > 0){

        studentSelectBox.selectedIndex = senderIndex[0].index;
        // studentSelectBox.disabled = true;

        messageBox.value = replyBody;
        messageBox.rows = 10;
        messageBox.cols = 50;
        messageBox.focus();
        messageBox.selectionEnd = 0;
    }
}));