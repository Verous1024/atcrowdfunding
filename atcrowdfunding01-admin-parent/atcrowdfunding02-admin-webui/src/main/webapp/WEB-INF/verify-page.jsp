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
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript" src="crowd/my-verify.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(function () {
        window.pageNum = 1;
        window.pageSize = 5;
        window.keyword = "";
        generatePage();

        $("#searchBtn").click(function () {
            window.keyword = $("#keywordInput").val();
            generatePage();
        });

        $("#verifyPageBody").on("click", ".lookBtn", function () {
            //获取当前角色的id,并存放在全局上，方便后面执行更新按钮时获取该值
            window.memberId = $(this).attr("memberId");
            //获取需要回显的数据
            $.ajax({
                url: "member/get/detail/member.json",
                data: {
                    "memberId": memberId
                },
                type: "post",
                dataType: "json",
                success: function (response) {
                    //回显--填充数据
                    console.log(response);
                    var data = response.data;
                    $("#loginacct").empty().val(data.loginacct);
                    $("#username").empty().val(data.username);
                    $("#email").empty().val(data.email);
                    $("#phonenum").empty().val(data.phonenum);
                    $("#realname").empty().val(data.realname);
                    $("#cardnum").empty().val(data.cardnum);
                    if (data.usertype == 0) {
                        $("#usertype").empty().val("个体用户");
                    }else{
                        $("#usertype").empty().val("企业用户");
                    }
                    switch (data.accttype) {
                        case(0):$("#accttype").empty().val("企业认证");break;
                        case(1):$("#accttype").empty().val("个体认证");break;
                        case(2):$("#accttype").empty().val("个人认证");break;
                        case(3):$("#accttype").empty().val("政府认证");break;
                    }
                    switch (data.authstatus) {
                        case(0):$("#authstatus").empty().val("未实名认证");break;
                        case(1):$("#authstatus").empty().val("实名认证申请中");break;
                        case(2):$("#authstatus").empty().val("已实名认证");break;
                        case(3):$("#authstatus").empty().val("实名认证申请失败");break;
                        case(4):$("#authstatus").empty().val("账号异常，冻结账号");break;
                    }
                    $("#lookModal").modal("show");
                },
                error: function(reponse){
                    layer.msg(response.message);
                }
            })
        });

        //审核通过
        $(".reviewBtn").click(function(){
            var aggreestatus = $(this).attr("status");
            $.ajax({
                url:"member/do/examination/pass.json",
                data:{
                    "memberId":window.memberId,
                    "status":aggreestatus
                },
                type:"post",
                dataType:"json",
                success:function (response) {
                    var result = response.result;
                    if (result == "SUCCESS") {
                        switch (aggreestatus) {
                            case 1: layer.msg("审核通过，申请成功！");
                            case 3: layer.msg("审核失败，申请成功！");
                            case 4: layer.msg("账号异常，申请冻结成功！");
                        }
                        $("#lookModal").modal("hide");
                        generatePage();
                    }else{
                        layer.msg("请求失败！请重新尝试");
                        $("#lookModal").modal("hide");
                    }
                },
                error:function (response) {
                    layer.msg(response.message);
                }
            })
        })
    });



</script>

<body>
<%@ include file="/WEB-INF/modal-memebr-edit.jsp" %>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">

        <%@ include file="/WEB-INF/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">

                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>

                    <br>
                    <hr style="clear:both;">
                    <%--清除浮动效果--%>

                    <div class="table-responsive">
                        <table class="table  table-bordered table-hover">

                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th>用户名称</th>
                                <th>手机号码</th>
                                <th>真实姓名</th>
                                <th>身份证号码</th>
                                <th>用户类型</th>
                                <th>认证类型</th>
                                <th>认证状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>

                            <tbody id="verifyPageBody">

                            </tbody>

                            <tfoot>
                            <tr>
                                <td colspan="12" align="center">
                                    <div id="Pagination" class="pagination"/> <!-- 这里显示分页 -->
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>


