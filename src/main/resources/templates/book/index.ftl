<@override name="title">图书列表</@override>
<@override name="header">
    <form class="form-inline">
        <input type="text" class="form-control" id="keyword">
        <button type="submit" class="btn btn-default" onclick="return search()">搜索</button>
        <#if user.type==1>
            <button type="submit" class="btn btn-default J_ajax_content_modal" data-href='manager/book/add'>添加书籍
            </button>
        </#if>
    </form>
</@override>


<@override name="table">
    <table id="userTable" class="table table-striped table-bordered table-hover"
           cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>id</th>
            <th>书名</th>
            <th>ISBN</th>
            <th>作者</th>
            <th>页数</th>
            <th>出版社</th>
            <th>出版日期</th>
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
        var userType = '${user.type}';
        $(document).ready(function () {
            table = $('#userTable').DataTable({
                "searching": false,
                'serverSide': true,
                // "paging": false,
                "ajax": {
                    url: "admin/book",
                    dataSrc: "data"
                },
                columns: [
                    {data: 'id', defaultContent: '', "searchable": true},
                    {data: 'name', defaultContent: '', "searchable": true},
                    {data: 'isbn', defaultContent: '', "searchable": true},
                    {data: 'author', defaultContent: '', "searchable": true},
                    {data: 'pages', defaultContent: '', "searchable": true},
                    {data: 'press', defaultContent: '', "searchable": true},
                    {data: 'pubDate', defaultContent: '', "searchable": true},
                    {
                        data: 'status', render: function (data) {
                            if (data === 1) {
                                return "<button class='btn btn-success'>可借阅</button>"
                            }
                            if (data === 0) {
                                return "<button class='btn btn-warning'>不可借阅</button>";
                            }
                            return "<button class='btn btn-success'>可借阅</button>";
                        }, "searchable": true
                    },
                    {
                        data: 'id', render: function (data) {
                            if (data === undefined) {
                                return '';
                            }

                            var deleteButton = "<a class='btn btn-danger J_ajax_content_modal' data-href='manager/book/delete?id=" + data + "'>删除</a>";
                            var borrowButton = "<a class='btn btn-default J_ajax_content_modal' data-href='manager/book/borrow?id=" + data + "'>借阅</a>";

                            if (userType === '0') {
                                return borrowButton;
                            }
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
            table.ajax.url("admin/book?keyword=" + $("#keyword").val()
            ).draw();
            return false;
        }

    </script>
</@override>
<@extends name="../base/baseTable.ftl"/>