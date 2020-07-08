<@override name="title">用户列表</@override>
<@override name="header">
    <form class="form-inline">
        <button type="submit" class="btn btn-default J_ajax_content_modal" data-href='manager/user/add'>添加维修人员
        </button>
    </form>
</@override>


<@override name="table">
    <table id="userTable" class="table table-striped table-bordered table-hover"
           cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>id</th>
            <th>账号</th>
            <th>手机号</th>
            <th>权限</th>
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
                    url: "admin/user",
                    dataSrc: "data"
                },
                columns: [
                    {data: 'id', defaultContent: '', "searchable": true},
                    {data: 'username', defaultContent: '', "searchable": true},
                    {data: 'phone', defaultContent: '', "searchable": true},
                    {
                        data: 'type', render: function (data) {
                            if (data === undefined || data === '') {
                                return '';
                            }
                            if (data === 0) {
                                return '维修人员';
                            }
                            if (data === 1) {
                                return '管理员';
                            }
                            if (data === 2) {
                                return '超级管理员';
                            }
                            return '';

                        }, "searchable": true
                    },
                    {
                        data: 'id', render: function (data) {
                            if (data === undefined) {
                                return '';
                            }
                            var deleteButton = "<a class='btn btn-danger J_ajax_content_modal' data-href='manager/user/delete?id=" + data + "'>删除</a>";
                            var updateButton = "<a class='btn btn-success J_ajax_content_modal' data-href='manager/user/update?id=" + data + "'>更新</a>";
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
            table.ajax.url("admin/user?name=" + $("#realname").val()
            ).draw();
            return false;
        }

    </script>
</@override>
<@extends name="../base/baseTable.ftl"/>