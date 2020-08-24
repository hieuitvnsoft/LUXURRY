<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
    <head>
        <title>Luxury Watches A Ecommerce Category Flat Bootstrap Resposive Website Template | Home :: w3layouts</title>
        <link href="/LUXURY/Views/Fontend/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <!--jQuery(necessary for Bootstrap's JavaScript plugins)-->
        <script src="/LUXURY/Views/Fontend/js/jquery-1.11.0.min.js"></script>
        <!--Custom-Theme-files-->
        <!--theme-style-->


        <link href="/LUXURY/Views/Fontend/boottrap/dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="/LUXURY/Views/Fontend/boottrap/dist/js/bootstrap.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/alert.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/button.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/carousel.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/collapse.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/dropdown.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/index.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/modal.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/popover.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/scrollspy.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/tab.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/tooltip.js" type="text/javascript"></script>
        <script src="/LUXURY/Views/Fontend/boottrap/js/dist/util.js" type="text/javascript"></script>
        <link href="/LUXURY/Views/Fontend/css/style.css" rel="stylesheet" type="text/css" media="all" />	
        <!--//theme-style-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Luxury Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--start-menu-->
        <script src="/LUXURY/Views/Fontend/js/simpleCart.min.js"></script>
        <link href="/LUXURY/Views/Fontend/css/memenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="/LUXURY/Views/Fontend/js/memenu.js"></script>
        <script>$(document).ready(function () {
                $(".memenu").memenu();
            });</script>	
        <!--dropdown-->
        <script src="/LUXURY/Views/Fontend/js/jquery.easydropdown.js"></script>	
        <style>
            .dropbtn {
                background-color: #3498DB;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropbtn:hover, .dropbtn:focus {
                background-color: #2980B9;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                overflow: auto;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown a:hover {background-color: #ddd;}

            .show {display: block;}
        </style>
    </head>
    <body> 
        <div class="top-header">
            <div class="container">
                <div class="top-header-main">
                    <div class="col-md-6 top-header-left">
                        <c:choose>
                            <c:when test ="${loginUser==null}">
                                <div class="drop">
                                    <button type="button" class="btn btn-info btn-default" data-toggle="modal" data-target="#login">Đăng nhập</button>
                                    <button type="button" class="btn btn-info btn-default" data-toggle="modal" data-target="#register">Đăng ký</button>

                                    <div class="modal fade" id="login" role="dialog">
                                        <div class="modal-dialog">

                                            <!-- Modal content-->
                                            <div class="modal-content">
                                                <form method="POST"  class="login100-form validate-form" action="${pageContext.request.contextPath}/login.htm">
                                                    <div class="modal-header">
                                                        <h3>Đăng nhập</h3>
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>

                                                    </div>
                                                    <div class="container">
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <label for="uname"><b>Username</b></label>
                                                            </div>
                                                            <div class="col-md-8">        <input type="text" name="username" placeholder="Nhập tên đăng nhập" path="userName" value="tronghoang123" id="username" required>

                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-md-4">
                                                                <label for="psw"><b>Password</b><br/></label>
                                                            </div>
                                                            <div class="col-md-8">         
                                                                <input type="password" name="password" placeholder="Nhập mật khẩu" value="abc123" path="" id="password" required>

                                                            </div>
                                                        </div>





                                                    </div>
                                                    <button style="float: right" type="submit" class="btn btn-primary">
                                                        Đăng nhập
                                                    </button>
                                                </form>

                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="modal fade" id="register" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h3>Đăng ký</h3>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>

                                            </div>
                                            <div class="modal-body">
                                                <form method="POST"  class="login100-form validate-form" action="${pageContext.request.contextPath}/register.htm" modelAttribute="user" enctype="multipart/form-data">
                                            </div>
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <label for="fullName"><b>Họ và tên</b></label>

                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" name="fullName" placeholder="Nhập tên đăng nhập" path="fullName" value="" id="fullName" required>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <label for="phone"><b>Số điện thoại</b></label>
                                                    </div>
                                                    <div class="col-md-8">    <input type="text" name="phone" placeholder="Nhập tên đăng nhập" path="phone" value="" id="phone" required>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <label for="address"><b>Địa chỉ</b></label>

                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" name="address" placeholder="Nhập tên đăng nhập" path="address" value="" id="fullName" required>
                                                    </div>
                                                </div>
                                                <div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label for="username"><b>Tên đăng nhập</b></label>
                                                        </div>
                                                        <div class="col-md-8">         <input type="text" name="username" placeholder="Nhập tên đăng nhập" path="username" value="" id="username" required>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label for="psw"><b>Mật khẩu</b><br/></label>
                                                        </div>
                                                        <div class="col-md-8">            <input type="password" name="password" placeholder="Nhập mật khẩu" value="Abc1234" path="" id="password" required>

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label for="email"><b>Email</b></label>
                                                        </div>
                                                        <div class="col-md-8">               <input type="email" name="email" placeholder="Nhập tên đăng nhập" path="email" value="" id="email" required>

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label for="birthday"><b>Ngày sinh</b></label>
                                                        </div>
                                                        <div class="col-md-8">         <input type="date" name="birthday" placeholder="Nhập ngày sinh" path="birthday" value="" id="birthday" required>

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label for="sex"><b>Giới tính</b></label>
                                                        </div>
                                                        <div class="col-md-8">         <input type="radio" name="sex"  path="sex"  value="1" id="sex1" checked="true" >Nam
                                                            <input type="radio"  name="sex" path="sex" value="0" id="sex2" >Nữ

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <label for="sex"><b>Ảnh đại diện</b></label>
                                                        </div>
                                                        <div class="col-md-8">       <input type="file" path="avartar" name="avartar" path="avartar" id="avartar" >
                                                        </div>
                                                    </div>




                                                </div>
                                                <button style="float: right" type="submit" class="btn btn-primary">
                                                    Đăng ký
                                                </button>
                                                </form>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div style="color:red">${messs}
                                    <%
                                        if (request.getParameter("messs") == null) {

                                        } else {
                                            out.println(request.getParameter("messs"));
                                        }
                                    %>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </c:when>
                        <c:otherwise>


                            <img  src="/${loginUser.avatar}" alt="Avatar" style="width:60px   ;border-radius: 50%;">&#160;&#160;&#160;&#160;&#160;&#160;<span class="" style="color: white">Xin chào: ${loginUser.fullName}</span>
                            <span><a href="${pageContext.request.contextPath}/changePassword.htm" class="btn btn-outline">Đổi mật khẩu</a><a href="${pageContext.request.contextPath}/checkOder.htm" class="btn btn-outline">Kiểm tra đơn hàng</a> <a href="${pageContext.request.contextPath}/logout.htm" class="btn btn-outline">Đăng xuất</a></span>
                            <div style="color:red">${messs}
                                <%
                                    if (request.getParameter("messs") == null) {

                                    } else {
                                        out.println(request.getParameter("messs"));
                                    }
                                %>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-6 top-header-left">
                    <div class="cart box_1">
                        <c:if test="${listCart==null}">
                            <a href="${pageContext.request.contextPath}/checkout.htm">
                                <div class="total">
                                    <span class="simpleCart_total"></span></div>
                                <img src="/LUXURY/Views/Fontend/images/cart-1.png" alt="" />

                                <p style="color: white">0 có sản phẩm</p></a>
                            <div class="clearfix"> </div></c:if>
                        <c:if test="${listCart!=null}">
                            <a href="${pageContext.request.contextPath}/checkout.htm">
                                <div class="total">
                                    <span class="">${totalAmount}</span></div>
                                <img src="/LUXURY/Views/Fontend/images/cart-1.png" alt="" />

                                <p style="color: white">${cartquantity} có sản phẩm</p></a>
                            <div class="clearfix"> </div>
                        </c:if>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <!--top-header-->
    <!--start-logo-->
    <div class="logo">
        <a href="${pageContext.request.contextPath}/home.htm"><h1>Luxury Watches</h1></a>
    </div>
    <!--start-logo-->
    <!--bottom-header-->
    <div class="header-bottom">
        <div class="container">
            <div class="header">
                <div class="col-md-9 header-left">
                    <div class="top-nav">
                        <ul class="memenu skyblue"><li class="active"><a href="${pageContext.request.contextPath}/home.htm">Trang chủ</a></li>
                            <li class="grid"><a href="#">Thương hiệu</a>
                                <div class="mepanel">
                                    <div class="row">
                                        <div class="col1 me-one">

                                            <ul>
                                                <c:forEach items="${listTrademark}" var="trademark">
                                                    <li><a href="${pageContext.request.contextPath}/Trademark.htm?id=${trademark.trademarkId}">${trademark.trademark}</a></li>
                                                    </c:forEach>

                                            </ul>
                                        </div>

                                    </div>
                                </div>
                            </li>
                            <c:forEach items="${listType}" var="types">

                                <li><a href="${pageContext.request.contextPath}/TypeProduct.htm?id=${types.id}">${types.typeName}</a></li>
                                </c:forEach>

                            <li class="grid"><a href="${pageContext.request.contextPath}/News.htm">Bài viết</a>
                            </li>
                            <li class="grid"><a href="${pageContext.request.contextPath}/Contact.htm">Liên hệ</a>
                            </li>
                        </ul>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="col-md-3 header-right"> 
                    <div class="search-bar">
                        <form action="${pageContext.request.contextPath}/checkout.htm" method="get">
                            <input type="text" name="search" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'Search';
                                    }">
                            <input type="submit" value=""></form>
                    </div>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
    <!--top-header-->
    <div class="bnr" id="home">
        <div class="container center-block">
            Danh sách sản phẩm : ${typeProduct}
        </div>
    </div>
    <!--banner-ends--> 
    <!--Slider-Starts-Here-->


    <!--End-slider-script-->
    <!--about-starts-->

    <!--about-end-->
    <!--product-starts-->
    <div class="product"> 
        <div class="container">
            <div class="product-top">
                <div class="product-one">

                    <c:forEach items="${listTypeProduct}" var="p">
                        <div class="col-md-3 product-left">
                            <div class="product-main simpleCart_shelfItem">
                                <a href="${pageContext.request.contextPath}/ProductDetail.htm?productId=${p.productId}" class="mask"><img class="img-responsive zoom-img" src="/LUXURY/Views/Fontend/images/p-1.png" alt="" /></a>
                                <div class="product-bottom">
                                    <h3>${p.productName}</h3>
                                    <p>Thương hiệu : ${p.trademarkId.trademark}</p>
                                    <h4><a class="item_add" href="#"><i></i></a> <span class=" item_price"><fmt:formatNumber currencyCode="vi_VN" value = "${p.priceOlder}" /> vnđ</span></h4>
                                </div>
                                <!--                                <div class="srch">
                                                                    <span>-50%</span>
                                                                </div>-->
                            </div>
                        </div>
                    </c:forEach>
                    <div class="clearfix"></div>
                </div>

            </div>
        </div>
    </div>
    <!--product-end-->
    <!--information-starts-->
    <jsp:include page="footer.jsp" flush="true"/>
    <!--footer-end-->	
</body>
</html>

