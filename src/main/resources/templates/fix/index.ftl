<@override name="title">维修列表</@override>
<@override name="header">
    <form class="form-inline">
        <input type="text" class="form-control" id="keyword">
        <button type="submit" class="btn btn-default" onclick="return search()">搜索</button>
        <button type="submit" class="btn btn-default" onclick="return exportFix()">导出</button>
    </form>
</@override>


<@override name="table">
    <table id="userTable" class="table table-striped table-bordered table-hover"
           cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>id</th>
            <th>维修编号</th>
            <th>维修人员</th>
            <th>提交时间</th>
            <th>使用单位</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</@override>

<@override name="script">
    <script>
        var table;
        $(document).ready(function () {
            table = $('#userTable').DataTable({
                "searching": false,
                'serverSide': true,
                // "paging": false,
                "ajax": {
                    url: "admin/fix",
                    dataSrc: "data"
                },
                columns: [
                    {data: 'id', defaultContent: '', "searchable": true},
                    {data: 'fixNo', defaultContent: '', "searchable": true},
                    {data: 'weixiurenyuan', defaultContent: '', "searchable": true},
                    {data: 'createTime', defaultContent: '', "searchable": true},
                    {data: 'shiyongdanwei', defaultContent: '', "searchable": true},
                    {
                        data: 'id', render: function (data) {
                            if (data === undefined) {
                                return '';
                            }
                            var deleteButton = "<a class='btn btn-danger J_ajax_content_modal' data-href='manager/fix/delete?id=" + data + "'>删除</a>";
                            var updateButton = "<a class='btn btn-success' href='#manager/fix/update?id=" + data + "'>编辑</a>";
                            return deleteButton + updateButton;
                        }, "searchable": true
                    }
                ],
                "language": {
                    url: "js/vendor/DataTables/Chinese.json"
                }
            });
        });

        function search() {
            table.ajax.url("admin/fix?keyword=" + $("#keyword").val()
            ).draw();
            return false;
        }

        function exportFix() {
            location.href = "admin/fix/export?keyword=" + $("#keyword").val();
            return false;
        }

    </script>
</@override>
<@extends name="../base/baseTable.ftl"/>