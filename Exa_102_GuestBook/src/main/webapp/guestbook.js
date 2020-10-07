/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validate(){
    let nickname = document.getElementById("nickname").value;
    let email = document.getElementById("email").value;
    if (!nickname) {
        return false;
    }
    
    if (!email) {
        return false;
    }
    return true;
}

