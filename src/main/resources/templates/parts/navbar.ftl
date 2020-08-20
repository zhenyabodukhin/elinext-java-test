<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar navbar-dark bg-dark navbar-inverse navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Medical Center</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/reservation">Reservation</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/position">Position</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/room">Room</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
                <#if name!="unknown">
                <div class="navbar-text mr-2" style="color: dodgerblue">${name}</div>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profile</a>
                </li>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <li class="nav-item"><@l.logout/></li>
            <#else>
                <a class="btn btn-primary mr-2" href="/login">Sign In</a>
                <a class="btn btn-primary" href="/registration">Sign Up</a>
            </#if>
        </ul>
    </div>
</nav>
