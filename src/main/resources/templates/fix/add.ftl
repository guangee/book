<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">添加食物</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="modal-body" id="main-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">图片</label>
            <div class="col-sm-5">
                <input type="file" class="form-control" placeholder="图片" id="file">
                <input type="hidden" name="picture">
            </div>
            <button class="col-sm-3 btn btn-success" onclick="return doUpload()">上传图片</button>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">预览</label>
            <div class="col-sm-9">
                <img src="" width="200px" height="200px" id="preview">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">名字</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="名字" name="name">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">详情</label>
            <div class="col-sm-9">
                <textarea rows="5" class="form-control" placeholder="详情介绍" name="detail"></textarea>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-success" onclick="return addFood()">
            添加
        </button>
    </div>
</form>

<script>
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
                        $("#preview").attr("src",res.data);
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
        var data={
            "name":$("input[name='name']").val(),
            "imageId":$("input[name='picture']").val(),
            "description":$("textarea[name='detail']").val()
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