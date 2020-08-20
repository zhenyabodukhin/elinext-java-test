<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">

<@common.page>

    <h1 style="text-align: center">Участники</h1><br>
    <table id="table-id" class="table table-hover"
           style="background-color: white">
        <thead>
        <tr>
            <th style="vertical-align: middle; text-align: center">Должность участника</th>
        </tr>
        </thead>
        <tbody>
        <#list positions as p>
            <tr>
                <td style="vertical-align: middle; text-align: center">${p.positionName}</td>
            </tr>
        </#list>
        </tbody>
    </table>


    <a class="btn btn-primary ml-4"  data-toggle="collapse"
       href="#collapseExample" role="button"
       aria-expanded="false"
       aria-controls="collapseExample">
        Add new position
    </a>
    <div class="collapse ml-1" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/position" method="post">
                <div class="form-row mx-2">
                    <div class="col-md-3 mb-3">
                        <label for="inputPositionName">Position name</label>
                        <input type="text" class="form-control form-control-sm" id="inputPositionName"
                               name="positionName"
                               placeholder="Enter Position" required>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary ml-3"  type="submit">
                    Add position
                </button>

            </form>
        </div>
    </div>

</@common.page>
