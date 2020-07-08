<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">更新维修单</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="form-group">
        <label class="col-sm-3 control-label">维修编号</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" placeholder="维修编号" readonly name="fixNo" value="${data.fixNo}">
        </div>
    </div>
    <#list inputList as item>

        <#if item.inputType==1>
            <div class="form-group">
                <label class="col-sm-3 control-label">${item.label}</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" placeholder="${item.label!}" name="${item.name!}"
                           value="${item.value!}">
                </div>
            </div>
        </#if>
        <#if item.inputType==2>
            <div class="form-group">
                <label class="col-sm-3 control-label">${item.label}</label>
                <div class="col-sm-6">
                    <select class="form-control" name="${item.name!}">
                        <option value="1" <#if item.value==1>selected</#if>>是</option>
                        <option value="0" <#if item.value==0>selected</#if>>否</option>
                    </select>
                </div>
            </div>
        </#if>


    </#list>

    <div class="form-group">
        <label class="col-sm-3 control-label">照片1</label>
        <div class="col-sm-9">
            <img src="${data.zhaopian1}" width="200px" height="200px" id="preview" class="J_ajax_content_modal" data-href='manager/fix/pic?url=${data.zhaopian1!}'/>
            <img src="${data.zhaopian2}" width="200px" height="200px" id="preview" class="J_ajax_content_modal" data-href='manager/fix/pic?url=${data.zhaopian2!}'/>
            <img src="${data.zhaopian3}" width="200px" height="200px" id="preview" class="J_ajax_content_modal" data-href='manager/fix/pic?url=${data.zhaopian3!}'/>
            <img src="${data.zhaopian4}" width="200px" height="200px" id="preview" class="J_ajax_content_modal" data-href='manager/fix/pic?url=${data.zhaopian4!}'/>
            <img src="${data.zhaopian5}" width="200px" height="200px" id="preview" class="J_ajax_content_modal" data-href='manager/fix/pic?url=${data.zhaopian5!}'/>
            <img src="${data.zhaopian6}" width="200px" height="200px" id="preview" class="J_ajax_content_modal" data-href='manager/fix/pic?url=${data.zhaopian6!}'/>
            <button  class="btn btn-default J_ajax_content_modal" data-href='manager/fix/pic'>上传</button>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label"></label>
        <div class="col-sm-6">
            <button type="button" class="btn btn-success" onclick="return updateFix()">保存</button>
            <a type="button" class="btn btn-success" href="/pdf/preview?fixNo=${data.fixNo!}">打印预览</a>
            <a type="button" class="btn btn-success" href="/pdf/download?fixNo=${data.fixNo!}">下载打印</a>
        </div>
    </div>
</form>

<script>

    function updateFix() {

        const data = {};
        $('input,select').each(function () {
            data[$(this).attr("name")] = $(this).val();
        })
        data['id'] = '${data.id!0}'
        $.ajax({
                url: "admin/fix/update",
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