<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="Enter Login" required/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password : </label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Enter password" required/>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="col-md-3 mb-3">
                <label for="inputPosition">Positions</label>
                <select id="inputPosition" name="positionName" class="form-control form-control-sm" required>
                    <option selected></option>
                    <#list positions as p>
                        <option>${p}</option>
                    </#list>
                </select>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Registration<#else>Sign In</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>

