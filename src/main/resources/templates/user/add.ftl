<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">添加用户</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="modal-body" id="main-body">

        <div class="form-group">
            <label class="col-sm-3 control-label">账号</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="账号" name="username">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">密码</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="密码" name="password">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">姓名</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="姓名" name="nickname">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">手机号</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="手机号" name="phone">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">用户身份</label>
            <div class="col-sm-9">
                <select class="form-control" name="type">
                    <option value="1">管理员 可以查看注册码</option>
                    <option value="2">超级管理员，可以管理用户</option>
                </select>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-success" onclick="return addUser()">
            添加
        </button>
    </div>
</form>

<script>
    function addUser() {
        var data = {
            "username": $("input[name='username']").val(),
            "password": $("input[name='password']").val(),
            "nickname": $("input[name='nickname']").val(),
            "type": $("select[name='nickname']").val(),
            "phone": $("input[name='phone']").val()
        };
        $.ajax({
                url: "admin/user/add",
                type: "POST",
                data: data,
                success: function (res) {
                    if (res.status === 0) {
                        location.reload();
                    } else {
                        alert("添加失败:" + res.msg)
                    }
                }
            }
        );
        return false;
    }
</script>