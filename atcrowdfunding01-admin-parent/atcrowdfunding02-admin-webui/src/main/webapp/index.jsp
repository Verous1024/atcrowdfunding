admin-main.jsp<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/16
  Time: 上午 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="secutiry" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">

<%@ include file="/WEB-INF/include-head.jsp" %>

<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>

<div class="container-fluid">
    <div class="row">

        <%@ include file="/WEB-INF/include-sidebar.jsp"%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">控制面板</h1>

            <div class="row placeholders">

                <security:authorize access="hasAuthority('user:get')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <a href="admin/get/page.html">
                            <img src="img/label-1.jpg" class="img-responsive" alt="图片加载失败" width="200px">
                            <h4>管理员维护</h4>
                            <span class="text-muted">Administrator maintenance</span>
                        </a>
                    </div>
                </security:authorize>

                <security:authorize access="hasAuthority('role:get')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <a href="role/to/page.html">
                            <img src="img/label-2.jpg" class="img-responsive" alt="图片加载失败" width="200px">
                            <h4>角色维护</h4>
                            <span class="text-muted">Role maintenance</span>
                        </a>
                    </div>
                </security:authorize>

                <security:authorize access="hasAuthority('menu:get')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <a href="menu/to/page.html">
                            <img src="img/label-3.jpg" class="img-responsive" alt="图片加载失败" width="200px">
                            <h4>菜单维护</h4>
                            <span class="text-muted">Menu maintenance</span>
                        </a>
                    </div>
                </security:authorize>

                <security:authorize access="hasAuthority('apply:get')">

                    <div class="col-xs-6 col-sm-3 placeholder">
                        <a href="member/to/page.html">
                            <img src="img/label-4.jpg" class="img-responsive" alt="图片加载失败" width="200px">
                            <h4>实名认证审核</h4>
                            <span class="text-muted">Real-name certification audit</span>
                        </a>
                    </div>
                </security:authorize>

                <security:authorize access="hasAuthority('project:get')">
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <a href="project/to/page.html">
                            <img src="img/label-5.jpg" class="img-responsive" alt="图片加载失败" width="200px">
                            <h4>项目审核</h4>
                            <span class="text-muted">Project review</span>
                        </a>
                    </div>
                </security:authorize>
            </div>
        </div>
    </div>
</div>

</body>
</html>


