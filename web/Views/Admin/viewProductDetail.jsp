<%-- 
    Document   : home
    Created on : Jul 18, 2019, 3:09:30 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>LUXURY Admin | Dashboard</title>
        <!-- Tell the browser to be responsive to screen width -->
        <link rel="stylesheet" type="text/css" href="/LUXURY/Views/Admin/DataTables/datatables.min.css"/>

        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.css"/>

        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/datatables.min.js"></script>

        <script type="text/javascript" src="/LUXURY/Views/Admin/DataTables/datatables.min.js"></script>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="/LUXURY/Views/Admin/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="/LUXURY/Views/Admin/bower_components/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="/LUXURY/Views/Admin/bower_components/Ionicons/css/ionicons.min.css">
        <link rel="stylesheet" href="/LUXURY/Views/Admin/bower_components/jvectormap/jquery-jvectormap.css">
        <link rel="stylesheet" href="/LUXURY/Views/Admin/dist/css/AdminLTE.min.css">
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
                        <li class="active">Product Details</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <!-- Info boxes -->

                    <!-- /.row -->

                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-header">
                                Chi tiết sản phẩm
                            </div>
                            <div>    
                                <table id="myTable" class="table table-striped table-bordered" cellspacing="0" width="100%">


                                    <tbody>



                                        <tr>
                                            <td style="width: 100px; text-align: center">Mã chi tiết sản phẩm</td>
                                            <td style="width: 200px;text-align: center">${productDetail.id}</td>
                                        </tr>
                                        <tr>


                                            <td style="width: 100px; text-align: center">Tên sản phẩm</td>
                                            <td style="width: 200px;text-align: center"> ${productDetail.productId.productName}</td>

                                        </tr>
                                        <tr>


                                            <td style="width: 100px; text-align: center">Giá</td>
                                            <td style="width: 200px;text-align: center"> <fmt:formatNumber currencyCode="vi_VN" value = "${productDetail.priceNew}" /> vnđ</td>

                                        </tr>
                                        <tr>


                                            <td style="width: 100px; text-align: center">Màu sắc</td>
                                            <td style="width: 200px;text-align: center"><div style="background-color:${productDetail.colorId.colorCode}"> ${productDetail.colorId.colorName}</div></td>

                                        </tr>
                                        <tr>


                                            <td style="width: 100px; text-align: center">Sale</td>
                                            <td style="width: 200px;text-align: center"> <div style=""> ${productDetail.sale}%</div></td>

                                        </tr>
                                        <tr>


                                            <td style="width: 100px; text-align: center">Số lượng</td>
                                            <td style="width: 200px;text-align: center"> <div style=""> ${productDetail.quantity}</div></td>

                                        </tr>
                                        <tr>


                                            <td style="width: 100px; text-align: center">Ảnh</td>
                                            <td style="width: 200px;text-align: center"><img width="200px" height="300px" src=" /${productDetail.imageProduct}"/></td>

                                        </tr>


                                    </tbody>
                                </table>  

                                <script>$(document).ready(function () {

                                        //$('#myTable').DataTable()();
                                        alert("pppppppppppppppppppppp");
                                    });</script>

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
