<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">删除用户</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="modal-body" id="main-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">id</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" readonly value="${data.id!}" placeholder="id"
                       name="id">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">地址</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" readonly value="${data.url!}" placeholder="地址" name="url">
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-danger" onclick="return deleteUser('${data.id!}')">
            删除
        </button>
    </div>
</form>

<script>
    function deleteUser(id) {
        if (id === undefined || id === '') {
            alert("id不能为空");
            return false;
        }
        $.ajax({
            url: "admin/user/delete/" + id,
            method: "POST",
            data: {},
            success: function (res) {
                if (res.status === 0) {
                    location.reload();
                } else {
                    alert("更新失败:" + res.msg)
                }
            }
        });
        return false;
    }
</script>