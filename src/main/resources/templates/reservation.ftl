<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
    <h1 style="text-align: center">Бронирование помещений</h1><br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th style="vertical-align: middle; text-align: center">Помещение</th>
            <th style="vertical-align: middle; text-align: center">Тип помещения</th>
            <th style="vertical-align: middle; text-align: center">Пользователь</th>
            <th style="vertical-align: middle; text-align: center">Название манипуляции</th>
            <th style="vertical-align: middle; text-align: center">Описание манипуляции</th>
            <th style="vertical-align: middle; text-align: center">Время начала</th>
            <th style="vertical-align: middle; text-align: center">Время конца</th>
        </tr>
        </thead>
        <tbody>
        <#list reservations as r>
            <tr>
                <td style="vertical-align: middle; text-align: center">${r.roomReservation.getName()}</td>
                <td style="vertical-align: middle; text-align: center">${r.roomReservation.getType()}</td>
                <td style="vertical-align: middle; text-align: center">${r.userReservation.getUsername()}</td>
                <td style="vertical-align: middle; text-align: center">${r.operation}</td>
                <td style="vertical-align: middle; text-align: center">${r.operationDescription}</td>
                <td style="vertical-align: middle; text-align: center">${r.startTime}</td>
                <td style="vertical-align: middle; text-align: center">${r.endTime}</td>
            </tr>
        </#list>
        </tbody>
    </table>

    <a class="btn btn-primary ml-4" data-toggle="collapse"
       href="#collapseExample" role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Reserve
    </a>
    <div class="collapse ml-4" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/reservation" method="post">
                <div class="form-row">
                    <div class="col-md-3 mb-3">
                        <label for="inputRoomId">Room</label>
                        <select id="inputRoomId" name="roomName" class="form-control form-control-sm" required>
                            <option selected></option>
                            <#list rooms as r>
                                <option>${r}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="inputOperation">Operation</label>
                        <input type="text" class="form-control form-control-sm" id="inputOperation" name="operation"
                               placeholder="Enter Operation" required>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="inputDescription">Description</label>
                        <input type="text" class="form-control form-control-sm" id="inputDescription"
                               name="operationDescription"
                               placeholder="Enter description" required>
                    </div>
                </div>
                <div class="form-row ml-1 mb-3">
                    <div id="datetimepicker4" class="datepicker-to-from">
                        <input type="time" id="startTimestamp" name="startTime" class="date-picker" required>

                        <input type="time" id="endTimestamp" name="endTime" class="date-picker" required>
                    </div>
                </div>

                <script>
                    $(function () {
                        $('#datetimepicker4').datetimepicker(
                            {
                                format: 'HH:mm',
                                locale: 'ru',
                            }
                        );
                    });
                </script>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary" type="submit">
                    Reserve medical ofiice
                </button>

            </form>
        </div>
    </div>
</@c.page>