<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/16
  Time: 下午 01:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="admin/to/main/page.html">西大众筹平台 - 后台系统</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li style="padding-top:8px;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
                            <i class="glyphicon glyphicon-user"></i>
                            <%--${sessionScope.loginAdmin.userName}--%>
                            <text id="userIcon"><security:authentication property="principal.originalAdmin.userName"/></text>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a id="goToMyAdmin" ><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
                            <%--<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>--%>
                            <li class="divider"></li>
                            <li><a href="security/do/logout.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                        </ul>
                    </div>
                </li>
                <li style="margin-left:10px;padding-top:8px;">
                    <button type="button" class="btn btn-default btn-danger">
                        <span class="glyphicon glyphicon-tag"></span> 管理员
                    </button>
                </li>
            </ul>
            <%--<form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="查询">
            </form>--%>
        </div>
    </div>
</nav>
<script>
    $(function () {
        $("#goToMyAdmin").click(function () {
            var username = $.trim($("#userIcon").text());
            window.location.href = "admin/get/page.html?keyword="+username;
        })
    })
</script>
