<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">还书</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="form-group">
        <label class="col-sm-3 control-label">书名</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" placeholder="书名" readonly name="name" value="${data.book.name!}">
        </div>
    </div>

    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <#if data.status==0>
            <button type="button" class="btn btn-success" onclick="return returnBook()">
                还书
            </button>
        </#if>
    </div>
</form>

<script>

    function returnBook() {

        const data = {};
        data['id'] = '${data.id!0}'
        $.ajax({
                url: "admin/borrow/return",
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
        )

        return false;
    }

    function doUpload() {
        var formData = new FormData();
        formData.append("file", $("#file")[0].files[0]);

        $.ajax({
                url: "api/user/upload",
                type: "POST",
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (res) {
                    if (res.status === 0) {
                        $("#preview").attr("src", res.data);
                        $("input[name='picture']").val(res.data);
                    } else {
                        alert("更新失败:" + res.msg)
                    }
                }
            }
        )
        return false;
    }

    function addFood() {
        var data = {
            "name": $("input[name='name']").val(),
            "imageId": $("input[name='picture']").val(),
            "description": $("textarea[name='detail']").val()
        };
        $.ajax({
                url: "admin/food/add",
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
        )
        return false;
    }
</script>