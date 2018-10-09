<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>	
	
	
<h2>Order</h2>
<div class="table-responsive">
  <table class="table table-striped table-bordered table-sm" >
    <thead>
      <tr align="center">
        <th>#</th>
        <th>狀態</th>
        <th>金額</th>
        <th>買家名稱</th>
        <th>創建時間</th>
        <th>支付時間</th>
        <th>操作</th>
      </tr>
    </thead>
    
    <tbody>
      <c:forEach items="${os}" var="o">
      <tr>
        <td>${ o.id }</td>
        <td>${ o.statusDesc}</td>
        <td align="right">$<fmt:formatNumber type="number" value="${o.total}" minFractionDigits="2"/></td>
		<td align="center">${o.user.email}</td>
		
		<td align="center"><fmt:formatDate value="${o.dateOrdered}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td align="center"><fmt:formatDate value="${o.datePaid}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

        <td class="text-center">
            <button oid=${o.id} class="orderPageCheckOrderItems btn btn-primary btn-sm">查看詳情</button>
        </td>
      </tr>
      <tr class="orderPageOrderItemTR"  oid=${o.id} style="display: none">
	      <td colspan="10" align="center">
		              
	          <div  class="orderPageOrderItem">
	              <table width="800px" align="center" class="orderPageOrderItemTable">
	                  <c:forEach items="${o.orderItems}" var="oi">
	                      <tr>
	                          <td align="left">
	                              <img width="40px" height="40px" src="img/productSingle/${oi.product.firstProductImage.id}.jpg">
	                          </td> 
	                              
	                          <td>
	                              <a href="foreproduct?pid=${oi.product.id}">
	                                  <span>${oi.product.name}</span>
	                              </a>                                          
	                          </td>
	                          <td align="right">
	                              <span class="text-muted">${oi.quantity}個</span>                                               
	                          </td>
	                          <td align="right">
	                              <span class="text-muted">單價 : $${oi.product.price}</span>                                              
	                          </td>
		
	                      </tr>
	                  </c:forEach>
		                  
	              </table>
	           </div>
		          
	      </td>
	  </tr>
      </c:forEach>
    </tbody>
  </table>
</div>


<!-- Pagination -->
<div>
	<%@include file="../include/admin/adminPage.jsp" %>
</div>	


<script>
$(function(){
    $("button.orderPageCheckOrderItems").click(function(){
        var oid = $(this).attr("oid");
        $("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();
    });
});
</script>

<%@include file="../include/admin/adminFooter.jsp"%>