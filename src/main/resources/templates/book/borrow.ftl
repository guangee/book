<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">借书</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="modal-body" id="main-body">
        <input type="hidden" name="bookId" value="${data.id}">

        <div class="form-group">
            <label class="col-sm-3 control-label">书名</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="书名" readonly name="name" value="${data.name!}">
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-success" onclick="return addCode()">
            借阅
        </button>
    </div>
</form>

<script>
    function addCode() {
        var data = {
            "bookId": $("input[name='bookId']").val()
        };
        $.ajax({
                url: "admin/borrow/add",
                type: "POST",
                data: data,
                success: function (res) {
                    if (res.status === 0) {
                        location.reload();
                    } else {
                        alert("借阅失败:" + res.msg)
                    }
                }
            }
        )
        return false;
    }
</script>