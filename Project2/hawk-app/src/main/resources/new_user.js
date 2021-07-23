function rowfade(){
    let rows = document.querySelectorAll('tbody > tr')
    rows.item(0).setAttribute('id', 'done')
    rows.forEach((row) => {
        row.addEventListener('animationend', ()=>{
            console.log("animation ended")
            row.nextElementSibling.setAttribute('id', 'done')
        })
    })
}

window.onload = function(){
    this.rowfade()
}