async function get_seatmap(){

    const url = 'endpoint'
    const response = await fetch(url)
    const my_json = await response.json()
    let map = document.querySelector('div.col-lg-auto > div')
    const columns = my_json["data"][0]["decks"][0]["deckConfiguration"]["width"]
    const seats = my_json["data"][0]["decks"][0]["deckConfiguration"]["length"]
    const column_div = document.createElement('div').setAttribute('class', 'row row-cols-' + columns)
    map.appendChild(column_div)
    let coordinate = document.createElement('div').setAttribute('class', 'col')
    for (let i = 0; i <= seats; i++){
        column_div.appendChild(coordinate).setAttribute('id', '(' + Math.floor(i / columns) + ',' + i % columns + ')')
    }

    const facilities = my_json["data"][0]["decks"][0]["facilities"]
    for (facility in facilities){
        let x = facility["x"]
        let y = facility["y"]
        let type = facility["code"]
        let position = document.getElementById('('+ x +','+ y +')')
        if (type == "LA"){
            position.classList.add('lavatory')
        }else if (type == "GN"){
            position.classList.add('galley')
        }else {
            position.classList.add(String(type))
        }
    }

    const seats = my_json["data"][0]["decks"][0]["seats"]
    for (seat in seats){
        let x = seat["x"]
        let y = seat["y"]
        let position = document.getElementById('('+ x +','+ y +')')
        position.classList.add('seat')
        const code = seat["characteristicsCodes"]
        if ("H" in code){
            position.classList.add('handicap')
        }
    }
}




window.onload = function(){
    this.get_seatmap()
}
