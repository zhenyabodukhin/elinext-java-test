<#import "parts/common.ftl" as c>
<#--<#include "parts/security.ftl">-->

<@c.page>
    <h1 style="text-align: center">Ваши забронированные помещения</h1><br>
<table class="table table-hover">
    <thead>
    <tr>
        <th style="vertical-align: middle; text-align: center">Помещение</th>
        <th style="vertical-align: middle; text-align: center">Пользователь</th>
        <th style="vertical-align: middle; text-align: center">Название манипуляции</th>
        <th style="vertical-align: middle; text-align: center">Описание манипуляции</th>
        <th style="vertical-align: middle; text-align: center">Время начала</th>
        <th style="vertical-align: middle; text-align: center">Время конца</th>
        <th style="vertical-align: middle; text-align: center">Действие</th>
    </tr>
    </thead>
    <tbody>
    <#list reservations as r>
        <tr>
            <td style="vertical-align: middle; text-align: center">${r.roomReservation.getType()}</td>
            <td style="vertical-align: middle; text-align: center">${r.userReservation.getUsername()}</td>
            <td style="vertical-align: middle; text-align: center">${r.operation}</td>
            <td style="vertical-align: middle; text-align: center">${r.operationDescription}</td>
            <td style="vertical-align: middle; text-align: center">${r.startTime}</td>
            <td style="vertical-align: middle; text-align: center">${r.endTime}</td>
            <td style="vertical-align: middle; text-align: center">
                <form action="/reservation/delete"/>
                <input type="hidden" name="id" value="${r.getId()}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" class="btn btn-outline-danger" value="Закончить бронирование">
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

</@c.page>