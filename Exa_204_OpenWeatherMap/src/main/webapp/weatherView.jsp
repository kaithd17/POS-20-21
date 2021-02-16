<%-- 
    Document   : weatherView
    Created on : 03.02.2021, 10:57:03
    Author     : kainz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <script src="https://kit.fontawesome.com/8b1b2bee53.js" crossorigin="anonymous"></script>
        <title>WeatherMap</title>
    </head>
    <body>
        <form action="./WeatherController" method="POST">
            <div class="headerContainer">
                <div class="header">
                    <h1>Open Weather Map</h1> 
                </div>
                <c:if test="${(sessionScope.responseState)}">
                    <div class="selectorContainer">
                        <select onchange="this.form.submit()" name="languageList" class="languageSelector">
                            <c:choose>
                                <c:when test="${sessionScope.currentLanguage == 'Deutsch'}">
                                    <option selected>Deutsch</option>
                                    <option>English</option>
                                </c:when>
                                <c:otherwise>
                                    <option>Deutsch</option>
                                    <option selected>English</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </c:if>
            </div>

            <div class="inputContainer">
                <div class="inputClass">
                    <c:choose>
                        <c:when test="${sessionScope.languageList.get(0) == null}">
                            <input type="text" name="inputField" value="" placeholder="Wie ist das Wetter in...?" class="inputField"/>
                        </c:when>
                        <c:otherwise>
                            <input type="text" name="inputField" value="" placeholder="${sessionScope.languageList.get(0)}" class="inputField"/>
                        </c:otherwise>
                    </c:choose>
                    <button type="submit" name="searchButton" value="search" class="searchButton"><i class="fas fa-search"></i></button>
                </div>
            </div>

            <c:choose>
                <c:when test="${(sessionScope.responseState)}">
                    <div class="viewContainer">
                        <div class="weatherDetailObject">
                            <h2>${sessionScope.currentWeather.getCity().getName()}</h2>
                            <div class="detailView">
                                <table border="0" class="detailView">
                                    <tbody>
                                        <tr>
                                            <td><span class="listHeader">${sessionScope.languageList.get(1)}</span></td>
                                            <td class="rightSide"><span class="listHeader">${sessionScope.languageList.get(2)}</span></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(3)}:</span> ${sessionScope.currentWeather.getCity().getId()}</li>
                                                    <li><span>${sessionScope.languageList.get(4)}:</span> ${sessionScope.currentWeather.getCity().getCountry()}</li>
                                                    <li><span>${sessionScope.languageList.get(5)}:</span>${sessionScope.currentWeather.getCity().getTimezone()}</li>
                                                </ul>
                                            </td>
                                            <td class="rightSide">
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(6)}:</span> ${sessionScope.currentWeather.getCity().getSun().getRise()}</li>
                                                    <li><span>${sessionScope.languageList.get(7)}:</span> ${sessionScope.currentWeather.getCity().getSun().getRise()}</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><span class="listHeader">${sessionScope.languageList.get(8)}</span></td>
                                            <td class="rightSide"><span class="listHeader">${sessionScope.languageList.get(9)}</span></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(10)}:</span> ${sessionScope.currentWeather.getCity().getCoord().getLon()}</li>
                                                    <li><span>${sessionScope.languageList.get(11)}:</span> ${sessionScope.currentWeather.getCity().getCoord().getLat()}</li>
                                                </ul>
                                            </td>
                                            <td class="rightSide">
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(12)}:</span> ${sessionScope.currentWeather.getClouds().getValue()}</li>
                                                    <li><span>${sessionScope.languageList.get(13)}:</span> ${sessionScope.currentWeather.getClouds().getName()}</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><span class="listHeader">${sessionScope.languageList.get(14)}</span></td>
                                            <td class="rightSide"><span class="listHeader">${sessionScope.languageList.get(15)}</span></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(16)}:</span> ${sessionScope.currentWeather.getHumidity().getValue()}</li>
                                                    <li><span>${sessionScope.languageList.get(17)}:</span> ${sessionScope.currentWeather.getHumidity().getUnit()}</li>
                                                </ul>
                                            </td>
                                            <td class="rightSide">
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(18)}:</span> ${sessionScope.currentWeather.getPressure().getValue()}</li>
                                                    <li><span>${sessionScope.languageList.get(19)}:</span> ${sessionScope.currentWeather.getPressure().getUnit()}</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><span class="listHeader">${sessionScope.languageList.get(20)}</span></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(21)}:</span> ${sessionScope.currentWeather.getTemperature().getValue()}</li>
                                                    <li><span>${sessionScope.languageList.get(22)}:</span> ${sessionScope.currentWeather.getTemperature().getMin()}</li>
                                                    <li><span>${sessionScope.languageList.get(23)}:</span> ${sessionScope.currentWeather.getTemperature().getMax()}</li>
                                                    <li><span>${sessionScope.languageList.get(24)}:</span> ${sessionScope.currentWeather.getTemperature().getUnit()}</li>
                                                </ul>
                                            </td>
                                            <td class="rightSide"> <img src='https://upload.wikimedia.org/wikipedia/commons/1/15/OpenWeatherMap_logo.png'></td>
                                        </tr>
                                        <tr>
                                            <td><span class="listHeader">${sessionScope.languageList.get(25)}</span></td>
                                        </tr>
                                        <tr>
                                            <td><span>${sessionScope.languageList.get(27)}:</span></td>
                                            <td class="rightSide"><span>${sessionScope.languageList.get(26)}:</span></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(31)}:</span> ${sessionScope.currentWeather.getWind().getDirection().getValue()}</li>
                                                    <li><span>${sessionScope.languageList.get(32)}:</span> ${sessionScope.currentWeather.getWind().getDirection().getCode()}</li>
                                                    <li><span>${sessionScope.languageList.get(33)}:</span> ${sessionScope.currentWeather.getWind().getDirection().getName()}</li>
                                                </ul>
                                            </td>
                                            <td class="rightSide">
                                                <ul>
                                                    <li><span>${sessionScope.languageList.get(28)}:</span> ${sessionScope.currentWeather.getWind().getSpeed().getValue()}</li>
                                                    <li><span>${sessionScope.languageList.get(29)}:</span> ${sessionScope.currentWeather.getWind().getSpeed().getUnit()}</li>
                                                    <li><span>${sessionScope.languageList.get(30)}:</span> ${sessionScope.currentWeather.getWind().getSpeed().getName()}</li>
                                                </ul>
                                            </td>
                                        </tr>
                                        <c:if test="${sessionScope.gusts}">
                                            <tr>
                                                <td><span>${sessionScope.languageList.get(34)}:</span> ${sessionScope.currentWeather.getWind().getGusts()}</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${(sessionScope.failure)}">
                    <div class="failureContainer">
                        <h2>No Results!</h2>
                    </div>
                </c:when>
            </c:choose>
            <div></div>
        </form>
    </body>
</html>
