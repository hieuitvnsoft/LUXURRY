<%-- 
    Document   : addTypeNews
    Created on : Jan 3, 2020, 2:24:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

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
                        <li class="active">TypeNew</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <!-- Info boxes -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-header">
                                Kiểu bài viết
                            </div>
                            <div>   
                                <spring:form action="${pageContext.request.contextPath}/Admin/editTypeNews.htm" method="post" modelAttribute="typeNews" >
                                    <table id="subProductType" class="table table-striped table-bordered" cellspacing="0" width="100%">

                                        <tbody>
                                            <tr>
                                                <td>Mã</td>
                                                <td>
                                                    <spring:input type="text" path="typeNewId" />
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Thể loại bài viết</td>
                                                <td>
                                                    <spring:input type="text" path="typeNewName" />
                                                </td>
                                            </tr>


                                            <tr>
                                                <td class="text-center" colspan="2">
                                                    <button type="submit">Cập nhật</button>

                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>  
                                </spring:form>
                                <div>
                                    <a  href="${pageContext.request.contextPath}/Admin/listTypeNews.htm" value="">Về trang danh sách</a>
                                </div>
                            </div>
                            <!-- /.col -->
                        </div>
                        <!-- /.row -->


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


