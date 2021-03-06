<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/2/16
  Time: 下午 01:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="col-sm-3 col-md-2 sidebar">
    <div class="tree">
        <ul style="padding-left:0px;" class="list-group">
            <li class="list-group-item tree-closed" >
                <a href="admin/to/main/page.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a>
            </li>
            <li id="authority_management_li" class="list-group-item tree-closed">
                <span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span>
                <ul id="authority_management_ul" style="margin-top:10px;display:none;">
                    <li style="height:30px;">
                        <a id="admin_label"  href="admin/get/page.html"><i class="glyphicon glyphicon-user"></i> 管理员维护</a>
                    </li>
                    <li style="height:30px;">
                        <a id="role_label" href="role/to/page.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a>
                    </li>
                    <li style="height:30px;">
                        <a id="menu_label" href="menu/to/page.html"><i class="glyphicon glyphicon-lock"></i> 菜单维护</a>
                    </li>
                </ul>
            </li>
            <li id="business_li" class="list-group-item tree-closed">
                <span><i class="glyphicon glyphicon-ok"></i> 业务审核 <span class="badge" style="float:right">2</span></span>
                <ul id="business_ul" style="margin-top:10px;display:none;">
                    <li style="height:30px;">
                        <a id="member_label" href="member/to/page.html"><i class="glyphicon glyphicon-check"></i> 实名认证审核</a>
                    </li>
                  <%--  <li style="height:30px;">
                        <a href="auth_adv.html"><i class="glyphicon glyphicon-check"></i> 广告审核</a>
                    </li>--%>
                    <li style="height:30px;">
                        <a id="project_label"  href="project/to/page.html"><i class="glyphicon glyphicon-check"></i> 项目审核</a>
                    </li>
                </ul>
            </li>
          <%--  <li class="list-group-item tree-closed">
                <span><i class="glyphicon glyphicon-th-large"></i> 业务管理 <span class="badge" style="float:right">7</span></span>
                <ul style="margin-top:10px;display:none;">
                    <li style="height:30px;">
                        <a href="cert.html"><i class="glyphicon glyphicon-picture"></i> 资质维护</a>
                    </li>
                    <li style="height:30px;">
                        <a href="type.html"><i class="glyphicon glyphicon-equalizer"></i> 分类管理</a>
                    </li>
                    <li style="height:30px;">
                        <a href="process.html"><i class="glyphicon glyphicon-random"></i> 流程管理</a>
                    </li>
                    <li style="height:30px;">
                        <a href="advertisement.html"><i class="glyphicon glyphicon-hdd"></i> 广告管理</a>
                    </li>
                    <li style="height:30px;">
                        <a href="message.html"><i class="glyphicon glyphicon-comment"></i> 消息模板</a>
                    </li>
                    <li style="height:30px;">
                        <a href="project_type.html"><i class="glyphicon glyphicon-list"></i> 项目分类</a>
                    </li>
                    <li style="height:30px;">
                        <a href="tag.html"><i class="glyphicon glyphicon-tags"></i> 项目标签</a>
                    </li>
                </ul>
            </li>
            <li class="list-group-item tree-closed" >
                <a href="param.html"><i class="glyphicon glyphicon-list-alt"></i> 参数管理</a>
            </li>--%>
        </ul>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var url = window.location.href;
        if(/admin/.test(url) && !(/admin.to.main.page.html/.test(url)) ){
            $("#admin_label").css("color","red");
            $("#authority_management_ul").css("display","block");
            $("#authority_management_li").removeClass("tree-closed");
        }

        if(/role/.test(url)){
            $("#role_label").css("color","red");
            $("#authority_management_ul").css("display","block");
            $("#authority_management_li").removeClass("tree-closed");
        }

        if(/menu/.test(url)){
            $("#menu_label").css("color","red");
            $("#authority_management_ul").css("display","block");
            $("#authority_management_li").removeClass("tree-closed");
        }

        if(/member/.test(url)){
            $("#member_label").css("color","red");
            $("#business_ul").css("display","block");
            $("#business_li").removeClass("tree-closed");
        }

        if(/project/.test(url)){
            $("#project_label").css("color","red");
            $("#business_ul").css("display","block");
            $("#business_li").removeClass("tree-closed");
        }


    })
</script>
