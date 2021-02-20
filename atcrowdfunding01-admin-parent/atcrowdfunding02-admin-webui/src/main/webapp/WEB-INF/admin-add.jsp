<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/16
  Time: 上午 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <%@ include file="/WEB-INF/include-head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#addLoginAcct").change(function () {
                $("#add_account_reminder").empty();
                var loginAcct = $("#addLoginAcct").val();
                $.ajax({
                    url:"admin/verifyLoginAcct.json",
                    data:"loginAcct="+loginAcct,
                    type:"POST",
                    success: function (result) {
                        if (result) {
                            $("#add_account_reminder").removeClass().addClass("text-danger").append("用户名已被占用！");
                        } else {
                            $("#add_account_reminder").removeClass().addClass("text-success").append("用户名可用！");
                        }
                    }
                })
            })
        })
    </script>

<body>
    <%@ include file="/WEB-INF/include-nav.jsp" %>


<div class="container-fluid">
    <div class="row">

        <%@ include file="/WEB-INF/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/to/main/page.html">首页</a></li>
                <li><a href="admin/get/page.html">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form action="admin/save.html" method="post" role="form">
                       <%-- <p>${requestScope.exception.message}</p>--%>
                        <div class="form-group">
                            <label for="addLoginAcct">登录账号</label>
                            <input name="loginAcct" type="text" class="form-control" id="addLoginAcct" placeholder="请输入登录账号">
                            <p id="add_account_reminder"></p>
                        </div>
                        <div class="form-group">
                            <label for="addUserPswd">登录密码</label>
                            <input name="userPswd" type="password" class="form-control" id="addUserPswd" placeholder="请输入登录密码">
                        </div>
                        <div class="form-group">
                            <label for="addUserName">用户昵称</label>
                            <input name="userName" type="text" class="form-control" id="addUserName" placeholder="请输入用户昵称">
                        </div>
                        <div class="form-group">
                            <label for="addEmail">邮箱地址</label>
                            <input name="email" type="email" class="form-control" id="addEmail" placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>


