<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <h1 style="text-align: center">Пользователи</h1><br>
    <table id="table-id" class="table table-hover"
           style="background-color: white">
        <thead>
        <tr>
            <th style="vertical-align: middle; text-align: center">Логин</th>
            <th style="vertical-align: middle; text-align: center">Позиция</th>
        </tr>
        </thead>
        <tbody>
        <#list users as u>
            <tr>
                <td style="vertical-align: middle; text-align: center">${u.username}</td>
                <td style="vertical-align: middle; text-align: center">${u.userPosition.positionName}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>