/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function valid() {
    var deliveryAddress = document.getElementById("inputField").value;
    var valueArray = document.getElementsByClassName("selector");
    var language = document.getElementById("language").value;

    var amountOfPizzas = 0;
    for (let i = 0; i < valueArray.length; i++) {
        try {
            amountOfPizzas += valueArray[i].value;
        } catch (Error) {
            alert("Test");
        }

    }

    if (deliveryAddress == "" && language == "Deutsch") {
        alert("Bitte Lieferadresse eingeben!");
        return false;
    } else if (deliveryAddress == "" && language == "English") {
        alert("Please enter your address!");
        return false;
    }

    if (amountOfPizzas == 0 && language == "Deutsch") {
        alert("Sie müssen mindestens eine Pizza bestellen.");
        return false;
    }else if(amountOfPizzas == 0 && language == "English"){
        alert("You must order at least one pizza.");
        return false;
    }
    return true;
}