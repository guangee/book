register<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">添加食物</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="modal-body" id="main-body">

        <div class="form-group">
            <label class="col-sm-3 control-label">数量</label>
            <div class="col-sm-9">
                <input type="number" class="form-control" placeholder="数量" name="num">
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-success" onclick="return addCode()">
            添加
        </button>
    </div>
</form>

<script>
    function addCode() {
        var data = {
            "num": $("input[name='num']").val()
        };
        $.ajax({
                url: "admin/borrow/add",
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
        )
        return false;
    }
</script>