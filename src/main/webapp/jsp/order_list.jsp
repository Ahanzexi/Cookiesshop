<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>我的订单</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/layer.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>


<!--header-->
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="flag" value="5"/>
</jsp:include>
<!--//header-->


<!--cart-items-->
<div class="cart-items">
    <div class="container">


        <h2> <span style="font-style: oblique">${username}</span>  的订单</h2>
        <ul class="nav nav-tabs"  >
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/order_list?status=0">所有订单</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/order_list?status=1">待付款</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/order_list?status=2">待发货</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/order_list?status=3">待收货</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/order_list?status=4">待评价</a></li>
        </ul>
        <table class="table table-bordered table-hover" style="margin-top: 10px">

            <tr>
                <th width="10%">ID</th>
                <th width="10%">总价</th>
                <th width="20%">商品详情</th>
                <th width="30%">收货信息</th>
                <th width="10%">订单状态</th>
                <th width="10%">支付方式</th>
                <th width="10%">下单时间</th>
            </tr>

            <c:forEach items="${page.list }" var="order">

                <tr>
                    <td><p>${order.id }</p></td>
                    <td><p>${order.total }</p></td>
                    <td>
                        <c:forEach items="${order.itemList }" var="item">
                            <p>${item.goodsName }(${item.price }) x ${item.amount }</p>
                        </c:forEach>

                    </td>
                    <td>
                        <p>${order.name }</p>
                        <p>${order.phone }</p>
                        <p>${order.address }</p>
                    </td>
                    <td>
                        <p>
                            <c:if test="${order.status==1 }"><span style="color:red;">未付款</span></c:if>
                            <c:if test="${order.status==2 }"><span style="color:red;">已付款</span></c:if>
                            <c:if test="${order.status==3 }"><span style="color:green;">已发货</span></c:if>
                            <c:if test="${order.status==4 }"><span style="color:black;">已完成</span></c:if>


                        </p>
                    </td>
                    <td>
                        <p>

                            <c:if test="${order.paytype==1 }">微信</c:if>
                            <c:if test="${order.paytype==2 }">支付宝</c:if>
                            <c:if test="${order.paytype==3 }">货到付款</c:if>

                        </p>
                    </td>
                    <td><p>${order.datetime }</p></td>
                </tr>

            </c:forEach>


        </table>
        <jsp:include page="/jsp/page.jsp">
            <jsp:param name="url" value="/order_list"/>
            <jsp:param name="param" value="&status=${status}"/>
        </jsp:include>
    </div>
</div>
<!--//cart-items-->
<!--footer-->
<jsp:include page="footer.jsp"/>
<!--//footer-->
</body>
<script>
    let tabs = document.querySelectorAll('.nav-tabs li');
    tabs.forEach(function(tab) {
        tab.addEventListener('click', function(event) {
            tabs.forEach(function(otherTab) {
                otherTab.classList.remove('active');
            });
            event.target.classList.add('active')
            window.location.href = event.target.getAttribute('href');
        });
    });
</script>
</html>