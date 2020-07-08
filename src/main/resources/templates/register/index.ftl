<@override name="title">维修列表</@override>
<@override name="header">
    <form class="form-inline">
        <input type="text" class="form-control" id="keyword">
        <button type="submit" class="btn btn-default" onclick="return search()">搜索</button>
        <button type="submit" class="btn btn-default J_ajax_content_modal" data-href='manager/register/add'>生成注册码
        </button>
    </form>
</@override>


<@override name="table">
    <table id="userTable" class="table table-striped table-bordered table-hover"
           cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>id</th>
            <th>注册码</th>
            <th>机器号</th>
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
                    url: "admin/register",
                    dataSrc: "data"
                },
                columns: [
                    {data: 'id', defaultContent: '', "searchable": true},
                    {data: 'code', defaultContent: '', "searchable": true},
                    {data: 'machineCode', defaultContent: '', "searchable": true},
                    {
                        data: 'id', render: function (data) {
                            if (data === undefined) {
                                return '';
                            }
                            var deleteButton = "<a class='btn btn-danger J_ajax_content_modal' data-href='manager/register/delete?id=" + data + "'>删除</a>";
                            return deleteButton;
                        }, "searchable": true
                    }
                ],
                "language": {
                    url: "js/vendor/DataTables/Chinese.json"
                }
            });
        });

        function search() {
            table.ajax.url("admin/register?keyword=" + $("#keyword").val()
            ).draw();
            return false;
        }

    </script>
</@override>
<@extends name="../base/baseTable.ftl"/>