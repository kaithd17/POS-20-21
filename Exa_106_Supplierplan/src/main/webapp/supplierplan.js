/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validInput() {
    var teacher = document.getElementById("teacherField").value;
    var subject = document.getElementById("subjectField").value;
    if (teacher == "" && subject == "") {
        document.getElementById("teacherField").style.borderBottom = "solid 3px red";
        document.getElementById("subjectField").style.borderBottom = "solid 3px red";
        document.getElementById("subjectError").style.display = "inline";
        document.getElementById("teacherError").style.display = "inline";
        return false;
    }
    if (teacher == "") {
        document.getElementById("teacherField").style.borderBottom = "solid 3px red";
        document.getElementById("subjectField").style.borderBottom = "solid 3px #C5C3C6";
        document.getElementById("subjectError").style.display = "none";
        return false;
    }
    if (subject == "") {
        document.getElementById("subjectField").style.borderBottom = "solid 3px red";
        document.getElementById("teacherField").style.borderBottom = "solid 3px #C5C3C6";
        document.getElementById("teacherError").style.display = "none";
        return false;
    }

    document.getElementById("subjectField").style.borderBottom = "solid 3px #C5C3C6";
    document.getElementById("teacherField").style.borderBottom = "solid 3px #C5C3C6";
    document.getElementById("subjectError").style.display = "none";
    document.getElementById("teacherError").style.display = "none";
    return true;
}