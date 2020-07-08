<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">更新用户</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="modal-body" id="main-body">

        <div class="form-group">
            <label class="col-sm-3 control-label">账号</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" readonly placeholder="账号" name="username"
                       value="${data.username!}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">密码</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="密码" name="password" value="${data.password!}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">姓名</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="姓名" name="nickname" value="${data.nickname!}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">手机号</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="手机号" name="phone" value="${data.phone!}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">用户身份</label>
            <div class="col-sm-9">
                <select class="form-control" name="type">
                    <option value="1" <#if data.type==1>selected</#if>>管理员 可以修改内容</option>
                    <option value="2" <#if data.type==2>selected</#if>>超级管理员，可以管理用户</option>
                </select>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-success" onclick="return addUser()">
            更新
        </button>
    </div>
</form>

<script>
    function addUser() {
        var data = {
            "id": '${data.id}',
            "password": $("input[name='password']").val(),
            "nickname": $("input[name='nickname']").val(),
            "type": $("select[name='type']").val(),
            "phone": $("input[name='phone']").val()
        };
        $.ajax({
                url: "admin/user/update",
                type: "POST",
                data: data,
                success: function (res) {
                    if (res.status === 0) {
                        location.reload();
                    } else {
                        alert("更新失败:" + res.msg)
                    }
                }
            }
        );
        return false;
    }
</script>