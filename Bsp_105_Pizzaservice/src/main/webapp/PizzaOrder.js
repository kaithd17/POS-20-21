/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function isValid() {
    var text = document.getElementById("inputField").value;
    var valueArray = document.getElementsByClassName("selector");
    
    var amountOfPizzas = 0;
    for(let i = 0; i < valueArray.length; i++){
        amountOfPizzas += valueArray[i].value;
    }
    
    let nodes = document.getElementById("right").childNodes;
    let address = document.createElement('p');
    let pizzen = document.createElement('p');

    if (text == "") {
        address.innerHTML = "Bitte Lieferadresse eingeben!";
        address.id = "noAddress";
        if (!nodeAlreadyThere(nodes, address.id)) {
            document.getElementById("right").appendChild(address);
        }
        return false;
    } 

    if (amountOfPizzas == 0) {
        pizzen.innerHTML = "Mindestens eine Pizza";
        pizzen.id = "noPizza";
        if (!nodeAlreadyThere(nodes, pizzen.id)) {
            document.getElementById("right").appendChild(pizzen);
        }
        return false;
    }
    return true;
}

function nodeAlreadyThere(nodes, type) {
    for (let i = 0; i < nodes.length; i++) {
        if (nodes[i].id == type) {
            return true;
        }
    }
    return false;
}