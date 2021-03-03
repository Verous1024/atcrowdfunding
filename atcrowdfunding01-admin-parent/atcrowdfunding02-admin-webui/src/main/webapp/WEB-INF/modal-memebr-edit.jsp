<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div id="lookModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">会员审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="loginacct" class="col-sm-2 control-label">用户名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="loginacct" placeholder="用户名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户昵称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" placeholder="用户昵称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="email" placeholder="邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phonenum" class="col-sm-2 control-label">手机号码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="phonenum" placeholder="手机号码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="realname" class="col-sm-2 control-label">真实姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="realname" placeholder="真实姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cardnum" class="col-sm-2 control-label">身份证号码</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="cardnum" placeholder="身份证号码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="usertype" class="col-sm-2 control-label">用户类型</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="usertype" placeholder="用户类型">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="accttype" class="col-sm-2 control-label">认证类型</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="accttype" placeholder="认证类型">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="authstatus" class="col-sm-2 control-label">认证状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="authstatus" placeholder="认证状态">
                        </div>
                    </div>

                    <div id="detailPicturePath"></div>
                    <%--<div class="form-group">
                        <label for="detailPicturePath" class="col-sm-2 control-label">项目详情图</label>
                        <div class="col-sm-10" id="detailPicturePath">
                        </div>
                    </div>--%>


                </form>
            </div>
            <div class="modal-footer">
                <button status="2" type="button" class="btn btn-primary reviewBtn">审核通过！</button>
                <button status="3" type="button" class="btn btn-danger reviewBtn">审核失败！</button>
                <button status="4" type="button" class="btn btn-info reviewBtn">账号异常，冻结账号！</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

