async function rowfade(){
    let rows = document.querySelectorAll('tbody > tr')
    rows.item(0).setAttribute('id', 'done')
    rows.forEach((row) => {
        row.addEventListener('animationend', ()=>{
            console.log("animation ended")
            row.nextElementSibling.setAttribute('id', 'done')
        })
    })
}

async function get_previous_bookings(){

    const url = 'endpoint'
    const response = await fetch(url)
    const my_json = await response.json()
    let booking = document.querySelector('#previous_bookings > tbody')
    rowfade()
}

window.onload = () => {
    this.get_previous_bookings()
}