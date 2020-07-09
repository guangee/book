<@override name="title">维修列表</@override>
<@override name="header">
    <form class="form-inline">
        <input type="text" class="form-control" id="keyword">
        <button type="submit" class="btn btn-default" onclick="return search()">搜索</button>
    </form>
</@override>


<@override name="table">
    <table id="userTable" class="table table-striped table-bordered table-hover"
           cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>id</th>
            <th>书名</th>
            <th>借阅人</th>
            <th>借阅时间</th>
            <th>归还时间</th>
            <th>状态</th>
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
                    url: "admin/borrow",
                    dataSrc: "data"
                },
                columns: [
                    {data: 'id', defaultContent: '', "searchable": true},
                    {data: 'book.name', defaultContent: '', "searchable": true},
                    {data: 'user.username', defaultContent: '', "searchable": true},
                    {data: 'borrowTime', defaultContent: '', "searchable": true},
                    {data: 'returnTime', defaultContent: '', "searchable": true},
                    {
                        data: 'status', render: function (data) {
                            if (data === 1) {
                                return "<button class='btn btn-success'>已归还</button>"
                            }
                            if (data === 0) {
                                return "<button class='btn btn-warning'>待归还</button>";
                            }
                            return "<button class='btn btn-success'>待归还</button>";
                        }, "searchable": true
                    },
                    {
                        data: 'id', render: function (data) {
                            if (data === undefined) {
                                return '';
                            }
                            return "<a class='btn btn-success J_ajax_content_modal' data-href='manager/borrow/return?id=" + data + "'>归还</a>";
                        }, "searchable": true
                    }
                ],
                "language": {
                    url: "js/vendor/DataTables/Chinese.json"
                }
            });
        });

        function search() {
            table.ajax.url("admin/borrow?keyword=" + $("#keyword").val()
            ).draw();
            return false;
        }

    </script>
</@override>
<@extends name="../base/baseTable.ftl"/>