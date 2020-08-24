<%-- 
    Document   : menu
    Created on : Jan 2, 2020, 3:07:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

 <div class="top-header">
            <div class="container">
                <div class="top-header-main">
                    <div class="col-md-6 top-header-left">

                        <div class="drop">
                            <div class="box">
                                <a href="#" class="btn btn-primary">Login</a>
                            </div>
                            <div class="box1">
                                <a href="#" class="btn btn-primary">Register</a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="col-md-6 top-header-left">
                        <div class="cart box_1">
                            <a href="checkout.html">
                                <div class="total">
                                    <span class="simpleCart_total"></span></div>
                                <img src="/LUXURY/Views/Fontend/images/cart-1.png" alt="" />
                            </a>
                            <p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <!--top-header-->
        <!--start-logo-->
        <div class="logo">
            <a href="index.html"><h1>Luxury Watches</h1></a>
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
                                                    <c:forEach items="${listTrademark}" var="t">
                                                        <li><a href="${pageContext.request.contextPath}/Trademark.htm?id=${t.trademarkId}">${t.trademark}</a></li>
                                                    </c:forEach>

                                                </ul>
                                            </div>

                                        </div>
                                    </div>
                                </li>
                                <c:forEach items="${listType}" var="tt">

                                    <li><a href="${pageContext.request.contextPath}/TypeProduct.htm?id=${tt.id}">${tt.typeName}</a></li>
                                </c:forEach>

                                <li class="grid"><a href="${pageContext.request.contextPath}/News.htm">Bài viết</a>
                                </li>
                                <li class="grid"><a href="contact.html">Contact</a>
                                </li>
                            </ul>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="col-md-3 header-right"> 
                        <div class="search-bar">
                            <input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'Search';
                                    }">
                            <input type="submit" value="">
                        </div>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>