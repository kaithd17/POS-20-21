/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function valid() {
    var deliveryAddress = document.getElementById("inputField").value;
    var valueArray = document.getElementsByClassName("selector");

    var amountOfPizzas = 0;
    for (let i = 0; i < valueArray.length; i++) {
        try {
            amountOfPizzas += valueArray[i].value;
        } catch (Error) {
            alert("Test");
        }

    }

    if (deliveryAddress == "") {
        alert("Bitte Lieferadresse eingeben!");
        return false;
    }

    if (amountOfPizzas == 0) {
        alert("Sie müssen mindestens eine Pizza bestellen.");
        return false;
    }
    return true;
}
