/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validate(){
    let nickname = document.getElementById("nickname").value;
    let email = document.getElementById("email").value;
    //var email document.getElementsByName(String)[] --> Hier bekommt ein Array deswegen Klammern nicht vergessen (.innerHTML --> zum Text hinzufügen)
    //Die Indizes werden nach der Reihenfolge im JSP vergeben
    val1 = 12;
    val2 = "12";
    //val1 == val2 kommt true heraus, hier wird nur der Wert überprüft
    //val1 === val2 kommt false heraus, hier wird alles überprüft
    if (!nickname) {
        return false;
    }
    
    if (!email) {
        return false;
    }
    return true;
}

function fill(element, value) {
    if (element.value == "") {
        element.value = value;
    }
}

function test() {
 //   alert("function called");
 //res = confirm("ok?"); Hier kann der User auswählen ok oder abbrechen; Die Variable ist true bei ok und false beim abbrechen
 //res = prompt("please enter value", "12") Hier hat ein Eingabefeld; 1.Parameter = Frage; 2.Parameter = Default Value
 
 /* try{
     Gleich wie in Java
    }catch (e) {
    Die Exception wird ausgegeben
     alert(e.toString());
    }*/
    
    //var feld = [1,2,3] Ausgabe: 1,2,3
    //alert(feld.join(" + ")) Ausgabe: 1 + 2 + 3
    //Feld Operationen --> push, pop(oberste Element wird zugegriffen), length, slice (Stücke heraus schneiden)
}