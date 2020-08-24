<%-- 
    Document   : home
    Created on : Jul 18, 2019, 3:09:30 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>LUXURY Admin | Dashboard</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="/LUXURY/Views/Admin/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="/LUXURY/Views/Admin/bower_components/font-awesome/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="/LUXURY/Views/Admin/bower_components/Ionicons/css/ionicons.min.css">
        <!-- jvectormap -->
        <link rel="stylesheet" href="/LUXURY/Views/Admin/bower_components/jvectormap/jquery-jvectormap.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="/LUXURY/Views/Admin/dist/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
        <link rel="stylesheet" href="/LUXURY/Views/Admin/dist/css/skins/_all-skins.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <jsp:include page="header_menu.jsp" flush="true"/>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <!--Bodyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy-->
                <section class="content-header">
                    <h1>
                        Dashboard
                        <small>Version 2.0</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Product</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <!-- Info boxes -->

                    <!-- /.row -->

                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-header">
                                Danh sách chi tiết
                            </div>
                            <div><a class="btn btn-primary" href="${pageContext.request.contextPath}/Admin/addProductDetail.htm?Id=${id}">Thêm mới</a></div>
                            <div>    
                                <table id="product" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>

                                            <th>Mã chi tiết</th>
                                            <th style="width:100px;height:100px;text-align:center;">Ảnh</th>
                                            <th>Giá mới</th>
                                            <th>Màu sắc</th>
                                            <th>Số lượng</th>
                                            <th>Trạng thái</th>

                                            <th></th>
                                        </tr>
                                    </thead>

                                    <tbody>


                                        <c:forEach items="${listProductDetail}" var="p">
                                            <tr>


                                                <td>${p.id}  </td>

                                                <td>
                                                    <img width="90px" height="160px" src="http://localhost:8080/${p.imageProduct}"/><br/>
                                                </td>

                                                <td>
                                                    <fmt:setLocale value = "vi_VN"/>
                                                    <fmt:formatNumber value = "${p.priceNew}" type = "currency"/> vnđ

                                                </td>



                                                <td>
                                                    ${p.colorId.colorName}
                                                    <div style="background-color: ${p.colorId.colorCode}">&#160;</div>
                                                </td>
                                                <td>
                                                    ${p.quantity}
                                                </td>


                                                <td>
                                                    <div class="hidden-sm hidden-xs action-buttons">
                                                        <a class="blue" href="viewProductDetail.htm?Id=${p.id}">
                                                            <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                                        </a>

                                                        <a class="green" href="UpdateProductDetail.htm?Id=${p.id}">
                                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                        </a>

                                                        <a class="red" href="deleteProductDetail.htm?Id=${p.id}">
                                                            <i class="ace-icon fa fa-trash-o bigger-130"></i>
                                                        </a>
                                                    </div>


                                                </td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>  



                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->

                        <!-- Main row -->

                        <!-- /.row -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->



            <!-- Control Sidebar -->

            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <jsp:include page="footer.jsp" flush="true"/>

        </div>
        <!-- ./wrapper -->

        <!-- jQuery 3 -->
        <script src="/LUXURY/Views/Admin/bower_components/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="/LUXURY/Views/Admin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="/LUXURY/Views/Admin/bower_components/fastclick/lib/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="/LUXURY/Views/Admin/dist/js/adminlte.min.js"></script>
        <!-- Sparkline -->
        <script src="/LUXURY/Views/Admin/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
        <!-- jvectormap  -->
        <script src="/LUXURY/Views/Admin/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="/LUXURY/Views/Admin/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <!-- SlimScroll -->
        <script src="/LUXURY/Views/Admin/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!-- ChartJS -->
        <script src="/LUXURY/Views/Admin/bower_components/chart.js/Chart.js"></script>
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="/LUXURY/Views/Admin/dist/js/pages/dashboard2.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="/LUXURY/Views/Admin/dist/js/demo.js"></script>
    </body>
</html>
