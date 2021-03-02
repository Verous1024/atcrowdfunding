//执行分页，生成页面效果，任何时候调用这个函数都会重新加载页面
function generatePage() {
    var pageInfo = getPageInfoRemote();
    fillTableBody(pageInfo);
}

//获取访问服务器端程序获取pageinfo数据
function getPageInfoRemote() {

    var ajaxResult = $.ajax({
        url: "project/get/page/info.json",
        data: {
            "pageNum": window.pageNum,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        type: "POST",
        async: false,
        dataType: "json"
    });

    //判断当前状态码
    var statusCode = ajaxResult.status;

    // 如果当前请求失败，请求码非200 --- 这个请求码200是网络传过来的response的，不是resultentity的
    if (statusCode != 200) {
        layer.msg("失败！响应状态码=" + statusCode + "说明信息=" + ajaxResult.statusText,
            {
                time: 2000,//2s后自动关闭
                btn: ['知道了', '关闭'],
                icon: 5
            });
        return null;
    }

    //如果响应码为200，表名成功
    var resultEntity = ajaxResult.responseJSON;
    var result = resultEntity.result;

    if (result == "FAILED") { //失败，获取数据错误
        layer.msg(resultEntity.message,
            {
                time: 2000,//2s后自动关闭
                btn: ['知道了', '关闭'],
                icon: 2
            });
    }
    var pageInfo = resultEntity.data; //成功，获取其中的pageInfo
    return pageInfo;
}

//填充表格
function fillTableBody(pageInfo) {
    $("#projectPageBody").empty(); //表格体清空
    $("#Pagination").empty(); //分页条清空

    //判断pageInfo是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
        $("#projectPageBody").append("<tr><td colspan='4'> 抱歉！没有查询到您搜索的数据！</td></tr>"); //在rolePageBody中展示
        return;
    }

    //使用pageInfo填充表格 list是pageInfo用来存储数据的地方
    for (var i = 0; i < pageInfo.list.length; i++) {
        var project = pageInfo.list[i];

        var projectId = project.id;
        var projectName = project.projectName;
        var launchMember = project.launchMember;
        var money = project.money;
        var day = project.day;
        var createdate = project.createdate;
        var status = project.status;

        var td1 = "<td>" + (i + 1) + "</td>"; //角色名序号
        var td2 = "<td><input projectId='" + projectId + "' type='checkbox' class='itemBox'></td>"; //复选框，携带roleId属性值，自定义
        var td3 = "<td>" + projectName +"</td>";  //角色名
        var td4 = "<td>" + launchMember + "</td>";
        var td5 = "<td>" + money + "</td>";
        var td6 = "<td>" + day + "</td>";
        var td7 = "<td>" + createdate + "</td>";
        var td8;
        if (status == 0) {
            td8 = "<td class='warning'>" + "待审核" + "</td>";
        } else if (status == 1) {
            td8 = "<td class='info'>" + "众筹中" + "</td>";
        } else if (status == 2) {
            td8 = "<td class='danger'>" + "众筹失败" + "</td>";
        } else if (status == 3) {
            td8 = "<td class='success'>" + "众筹成功" + "</td>";
        } else if (status == 4) {
            td8 = "<td class='danger'>" + "审核失败" + "</td>";
        }

        var checkBtn = "<button projectId='" + projectId + "' type='button' class='btn btn-success btn-xs lookBtn'><i class=' glyphicon glyphicon-eye-open'></i></button>";
        /* 自定义属性 roleId， 自定义cllas标签 pencilBtn*//*
        var pencilBtn = "<button projectId='" + projectId + "' type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button projectId='" + projectId + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";*/

        var td9 = "<td>" + checkBtn + "</td>"; //按钮组 ，也需要携带roleId属性值，自定义
        var tr = "<tr>" + td1 + td2 + td3 + td4 + td5 + td6 + td7 + td8 + td9 + "</tr>"; //组合为一行的表格
        $("#projectPageBody").append(tr); //添加到库中
    }

    generateNavigator(pageInfo); //填充完表格后，生成分页条
}

//生成分页的导航条
function generateNavigator(pageInfo) {
    //总记录数
    var totalRecord = pageInfo.total; //pageInfo的自带total属性

    //相关属性设置
    var properties = {
        num_edge_entries: 3, //左右两侧显示的
        items_per_page: pageInfo.pageSize,
        num_display_entries: 5, //显示的页数
        current_page: pageInfo.pageNum - 1, //当前页码的展示
        next_text: "下一页", //上一页的显示
        prev_text: "上一页", //下一页的显示
        callback: paginationCallBack //当按下的分页条时触发的回调函数
    };

    //调用pagination函数
    $("#Pagination").pagination(totalRecord, properties); //根据 总记录数 和 相关属性设置拆分
}

//分页按钮的callBack函数
function paginationCallBack(pageIndex, jQuery) {
    var pageNum = pageIndex + 1; //pageNum必pageInf大一个
    window.pageNum = pageNum;
    generatePage(); //点击分页按钮后需要重新分页
    //取消按钮的默认行为
    return false;
}