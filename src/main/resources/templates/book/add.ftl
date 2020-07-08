register<#setting number_format="#">
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                class="sr-only">Close</span></button>
    <h4 class="modal-title" id="myModalLabel">添加书籍</h4>
</div>
<form class="form-horizontal J_ajaxForm" role="form" action="">
    <div class="modal-body" id="main-body">

        <div class="form-group">
            <label class="col-sm-3 control-label">书名</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="书名" name="name">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">作者</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="作者" name="author">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">ISBN</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="ISBN" name="isbn">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">出版日期</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="出版日期" name="pubDate">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">页数</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="页数" name="pages">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">出版社</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" placeholder="出版社" name="press">
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
            "name": $("input[name='name']").val(),
            "press": $("input[name='press']").val(),
            "pubDate": $("input[name='pubDate']").val(),
            "isbn": $("input[name='isbn']").val(),
            "author": $("input[name='author']").val(),
            "pages": $("input[name='pages']").val(),
        };
        $.ajax({
                url: "admin/book/add",
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