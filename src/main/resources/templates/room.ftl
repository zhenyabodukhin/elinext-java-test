<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">

<@common.page>

    <h1 style="text-align: center">Помещения</h1><br>
    <table id="table-id" class="table table-hover"
           style="background-color: white">
        <thead>
        <tr>
            <th style="vertical-align: middle; text-align: center">Название помещения</th>
            <th style="vertical-align: middle; text-align: center">Тип помещения</th>
        </tr>
        </thead>
        <tbody>
        <#list rooms as r>
            <tr>
                <td style="vertical-align: middle; text-align: center">${r.name}</td>
                <td style="vertical-align: middle; text-align: center">${r.type}</td>
            </tr>
        </#list>
        </tbody>
    </table>


    <a class="btn btn-primary ml-4" data-toggle="collapse"
       href="#collapseExample" role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Add new room
    </a>
    <div class="collapse ml-1" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/room" method="post">
                <div class="form-row mx-2">
                    <div class="col-md-3 mb-3">
                        <label for="inputRoomName">Room name</label>
                        <input type="text" class="form-control form-control-sm" id="inputRoomName"
                               name="roomName"
                               placeholder="Enter Room Name" required>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="inputRoomType">Room type</label>
                        <input type="text" class="form-control form-control-sm" id="inputRoomType"
                               name="roomType"
                               placeholder="Enter Room Type" required>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary ml-3"  type="submit">
                    Add room
                </button>

            </form>
        </div>
    </div>

</@common.page>
