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
                        <li class="active">News</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <!-- Info boxes -->
                    <div class="row"><a class="btn btn-primary" href="${pageContext.request.contextPath}/Admin/addNew.htm">Thêm mới</a></div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-header">
                                Danh sách bài viết
                            </div>
                            <div>    
                                <table id="myTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>

                                            <th>Mã bài viết</th>
                                            <th style="width:100px;height:100px;text-align:center;">Ảnh</th>
                                            <th>Tiêu đề</th>
                                            <th>Ngày viết</th>
                                            <th>Người viết</th>
                                            <th>Loại bài viết</th>
                                            <th>Trạng thái</th>

                                            <th></th>
                                        </tr>
                                    </thead>

                                    <tbody>


                                        <c:forEach items="${listNews}" var="n">
                                            <tr>


                                                <td style="width: 100px; text-align: center">${n.id}  </td>
                                                <td style="width: 200px;text-align: center"> <img width="90px" height="160px" src="/${n.newImage}"/></td>
                                                <td style="width: 400px">

                                                    ${n.title}</td>

                                                <td>
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value = "${n.dateWrite}" type="date" />

                                                </td>
                                                <td>
                                                    ${n.adminId.fullName}

                                                </td>
                                                <td>
                                                    ${n.typeId.typeNewName}
                                                    <%-- 
                                        <c:forEach items="${listTypeNews}" var="p">

                                                    //    <c:if test="${n.typeId} == ${p.typeNewId}">

                                                            ${p.typeNewName}
                                                        </c:if>

                                                    </c:forEach>
                                                    --%>

                                                </td>




                                                <c:if test="${n.status == 'True'}">
                                                    <td style="width: 200px">
                                                        Kích hoạt
                                                    </td>
                                                </c:if>
                                                <c:if test="${n.status == 'False'}">
                                                    <td style="width: 200px">
                                                        Không kích hoạt
                                                    </td>
                                                </c:if>

                                                <td>
                                                    <div class="hidden-sm hidden-xs action-buttons">
                                                        <a class="blue" href="newDetail.htm?Id=${n.id}">
                                                            <i class="ace-icon fa fa-search-plus bigger-130"></i>
                                                        </a>
                                                        &#160;&#160;&#160;&#160;&#160;&#160;&#160;
                                                        <a class="green" href="updateNew.htm?Id=${n.id}">
                                                            <i class="ace-icon fa fa-pencil bigger-130"></i>
                                                        </a>
                                                        &#160;&#160;&#160;&#160;&#160;&#160;&#160;
                                                        <a class="green" href="deleteNew.htm?Id=${n.id}">
                                                            <i class="ace-icon fa fa-close bigger-130"></i>
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
        <script src="/LUXURY/Views/Admin/DataTables/datatables.min.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Admin/DataTables/datatables.js" type="text/javascript"></script>
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
        <script src="/LUXURY/Views/Admin/js/jquery.js" type="text/javascript"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="/LUXURY/Views/Admin/dist/js/demo.js"></script>
        <script src="/LUXURY/Views/Admin/js/jquerymin.js" type="text/javascript"></script>
        <script>
            $(function () {

                $('#myTable').DataTable();
                //alert("pppppppppppppppppppppp");
            });

//    $().ready(function () {
//
//                                        //$('#myTable').DataTable()();
//                                        alert("pppppppppppppppppppppp");
//                                    });</script>
    </body>
</html>
