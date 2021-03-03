<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div id="lookModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">项目审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <h4>项目信息</h4>
                    <div class="form-group">
                        <label for="projectName" class="col-sm-2 control-label">项目名字</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="projectName" placeholder="项目的名字">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="projectDescription" class="col-sm-2 control-label">项目描述</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="projectDescription" placeholder="关于产品的描述">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="money" class="col-sm-2 control-label">筹集金额</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="money" placeholder="该项目需要的筹集金额">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="day" class="col-sm-2 control-label">筹集日期</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="day" placeholder="该项目需要的筹集日期">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createdate" class="col-sm-2 control-label">创建日期</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="createdate" placeholder="该项目的创建日志">
                        </div>
                    </div>
                    <div id="headerPicturePath"></div>
                    <%--<div class="form-group">
                        <label for="headerPicturePath" class="col-sm-2 control-label">项目头图</label>
                    </div>
                    <h5><img class="form-control" id="headerPicturePath" alt="图片加载失败!"/></h5>--%>
                    <div id="detailPicturePath"></div>
                    <%--<div class="form-group">
                        <label for="detailPicturePath" class="col-sm-2 control-label">项目详情图</label>
                        <div class="col-sm-10" id="detailPicturePath">
                        </div>
                    </div>--%>

                    <h4>发起人信息</h4>
                    <div class="form-group">
                        <label for="descriptionSimple" class="col-sm-2 control-label">简介</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="descriptionSimple" placeholder="发起人介绍">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="descriptionDetail" class="col-sm-2 control-label">详细介绍</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="descriptionDetail" placeholder="发起人详细介绍">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phoneNum" class="col-sm-2 control-label">电话号码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="phoneNum" placeholder="电话号码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="serviceNum" class="col-sm-2 control-label">客服号码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="serviceNum" placeholder="客服号码">
                        </div>
                    </div>

                    <h4 >回报信息</h4>
                    <h5 id="returnIndex"></h5>
                    <div id="return"></div>
                    <%--     <div class="form-group">
                             <label for="content" class="col-sm-2 control-label">回报内容</label>
                             <div class="col-sm-10">
                                 <input type="text" class="form-control" id="content" placeholder="回报内容">
                             </div>
                         </div>
                         <div class="form-group">
                             <label for="descPicPath" class="col-sm-2 control-label">回报</label>
                             <div class="col-sm-10">
                                 <input type="text" class="form-control" id="descPicPath" placeholder="回报">
                             </div>
                         </div>--%>
                    <h4>会员身份</h4>
                    <div class="form-group">
                        <label for="loginacct" class="col-sm-2 control-label">用户账号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="loginacct" placeholder="用户账号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="realname" class="col-sm-2 control-label">真实姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="realname" placeholder="真实姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cardnum" class="col-sm-2 control-label">身份证</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="cardnum" placeholder="身份证">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="usertype" class="col-sm-2 control-label">用户类型</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="usertype" placeholder="用户类型">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="accttype" class="col-sm-2 control-label">账号类型</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="accttype" placeholder="账号类型">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="authstatus" class="col-sm-2 control-label">账号状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="authstatus" placeholder="账号状态">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button status="1" type="button" class="btn btn-primary reviewBtn">审核通过！</button>
                <button status="3" type="button" class="btn btn-danger reviewBtn">审核失败！</button>
                <button status="5" type="button" class="btn btn-info reviewBtn">项目异常暂停项目！</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

