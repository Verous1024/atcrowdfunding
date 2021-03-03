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
<link href="ztree/zTreeStyle.css" rel="stylesheet"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-project.js" charset="UTF-8"></script>
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

        //点击look标签回显数据的模态框
        $("#projectPageBody").on("click", ".lookBtn", function () {

            //获取当前角色的id,并存放在全局上，方便后面执行更新按钮时获取该值
            window.projectId = $(this).attr("projectId");
            //获取需要回显的数据
            $.ajax({
                url: "project/get/detail/project.json",
                data: {
                    "projectId": projectId
                },
                type: "post",
                dataType: "json",
                success: function (response) {
                    //回显--填充数据
                    console.log(response);
                    var data = response.data;
                    $("#projectName").empty().val(data.projectName);
                    $("#projectDescription").empty().val(data.projectDescription);
                    $("#money").empty().val(data.money);
                    $("#day").empty().val(data.day);
                    $("#createdate").empty().val(data.createdate);
                    var headerHtml = "<div class='form-group'>\n" +
                        "                             <label for='descPicPath' class='col-sm-2 control-label'>项目头图</label>\n" +
                        "                             <div class='col-sm-10'>\n" +
                        "                                 <img width='500px'  src='"+data.headerPicturePath+"' alt='加载失败！'>\n" +
                        "                             </div>\n" +
                        "                         </div>"
                    $("#headerPicturePath").empty().append(headerHtml);

                    $("#detailPicturePath").empty();
                    for(var i=0;i<data.detailPicturePath.length;i++){
                        var detailPicPath = data.detailPicturePath[i];
                        var num = i+1;
                        var imgHtml = "<div class='form-group'>\n" +
                            "                             <label for='descPicPath' class='col-sm-2 control-label'>项目图片"+num+"</label>\n" +
                            "                             <div class='col-sm-10'>\n" +
                            "                                 <img width='500px' src='"+detailPicPath+"' alt='加载失败！'>\n" +
                            "                             </div>\n" +
                            "                         </div>"
                        $("#detailPicturePath").append(imgHtml);
                    }

                    var memberLaunchInfo = data.memberLaunchInfo;
                    $("#descriptionSimple").empty().val(memberLaunchInfo.descriptionSimple);
                    $("#descriptionDetail").empty().val(memberLaunchInfo.descriptionDetail);
                    $("#phoneNum").empty().val(memberLaunchInfo.phoneNum);
                    $("#serviceNum").empty().val(memberLaunchInfo.serviceNum);

                    var returnList = data.listReturn;
                    $("#return").empty()
                    for(var i=0;i<returnList.length;i++){
                        var returnSolo = returnList[i];
                        var num = i+1;
                        var longHtml = "  <h5 id='returnIndex'>"+"第"+num+"种回报"+"</h5>\n" +
                            "                    <div class='form-group'>\n" +
                            "                        <label for='content' class='col-sm-2 control-label'>回报内容</label>\n" +
                            "                        <div class='col-sm-10'>\n" +
                            "                            <input type='text' class='form-control' value='"+returnSolo.content+"'>\n" +
                            "                        </div>\n" +
                            "                    </div>\n" +
                            "                    <div class='form-group'>\n" +
                            "                        <label for='descPicPath' class='col-sm-2 control-label'>回报显示图片</label>\n" +
                            "                        <div class='col-sm-10'>\n" +
                            "                            <img width='500px' src='"+returnSolo.descPicPath+"' alt='加载失败！'>\n" +
                            "                        </div>\n" +
                            "                    </div>";
                        $("#return").append(longHtml);
                    }

                    var member = data.member;
                    $("#loginacct").empty().val(member.loginacct);
                    $("#realname").empty().val(member.realname);
                    $("#cardnum").empty().val(member.cardnum);
                    if (member.usertype == 0) {
                        $("#usertype").empty().val("个体用户");
                    }else{
                        $("#usertype").empty().val("企业用户");
                    }
                    switch (member.accttype) {
                        case(0):$("#accttype").empty().val("企业");break;
                        case(1):$("#accttype").empty().val("个体");break;
                        case(2):$("#accttype").empty().val("个人");break;
                        case(3):$("#accttype").empty().val("政府");break;
                    }
                    switch (member.authstatus) {
                        case(0):$("#authstatus").empty().val("未实名认证");break;
                        case(1):$("#authstatus").empty().val("实名认证申请中");break;
                        case(2):$("#authstatus").empty().val("已实名认证");break;
                        case(3):$("#authstatus").empty().val("实名认证申请失败");break;
                        case(4):$("#authstatus").empty().val("账号异常，冻结账号");break;
                    }

                    $("#lookModal").modal("show");
                }
            })
        });

        //审核通过
        $(".reviewBtn").click(function(){
            var aggreestatus = $(this).attr("status");
            $.ajax({
                url:"project/do/examination/pass.json",
                data:{
                    "projectId":window.projectId,
                    "status":aggreestatus
                },
                type:"post",
                dataType:"json",
                success:function (response) {
                   var result = response.result;
                    if (result == "SUCCESS") {
                        switch (aggreestatus) {
                            case 1: layer.msg("审核通过申请成功！");
                            case 3: layer.msg("审核失败申请成功！");
                            case 4: layer.msg("项目异常，申请中止成功！");
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

    })
</script>

<body>
<%@ include file="/WEB-INF/modal-project-edit.jsp" %>
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
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>项目名称</th>
                                <th>发起人</th>
                                <th>目标金额</th>
                                <th>众筹周期</th>
                                <th>创建时间</th>
                                <th>状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>

                            <tbody id="projectPageBody">

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


