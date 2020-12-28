<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/dashboard.css">   
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"> 
    <title>Order Management App</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
		if(session.getAttribute("username") == null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<header class="header">
    	<img src="images/hrc-logo.svg" alt="hrc-logo" class="hrc-logo">
        <img src="images/abc-logo.png" alt="abc-logo" class="abc-logo">
    </header>
    <div class="container">
        <div class="hero-container">
            <div class="hero-header">
            	<c:if test="${level == 'Level 1'}">
            		<div class="btn-div">
                    	<button id="btn-add" class="btn btn-add" onClick="openAddModal();">ADD</button>
                    	<button id="btn-edit" class="btn btn-edit" onClick="openEditModal();" disabled>EDIT</button>
                	</div>
                	<script>
                		function handleCheckbox(checkbox) {
                			var btnEdit = document.getElementById("btn-edit");
                			var tableRow = checkbox.parentNode.parentNode;
                			if(checkbox.checked) {
                				btnEdit.disabled = false;
                				btnEdit.classList.add("btn-active");
                				tableRow.classList.add("table-active");
                				var inputField1 = document.getElementById("edit-modal-orderID");
                				var inputField2 = document.getElementById("edit-modal-orderAmount");
                				var inputField3 = document.getElementById("edit-modal-notes");
                				var inputField4 = document.getElementById("edit-modal-approvedBy");
                				inputField1.value = tableRow.cells[2].innerHTML;
                				inputField2.value = tableRow.cells[5].innerHTML;
                				inputField3.value = tableRow.cells[8].innerHTML;
                				inputField4.value = tableRow.cells[7].innerHTML;
                			}
                			else {
                				btnEdit.disabled = true;
                				btnEdit.classList.remove("btn-active");
                				tableRow.classList.remove("table-active");
                			}
                			var chk = checkbox.parentNode.parentNode.parentNode;
                			var checkList = chk.getElementsByTagName("input");
                			for(var i = 0; i < checkList.length; i++) {
                				if(checkList[i] != checkbox && checkbox.checked) {
                					checkList[i].checked = false;
                					checkList[i].parentNode.parentNode.classList.remove("table-active");
                				}
                			}
                		}
                	</script>
            	</c:if>
                <c:if test="${level == 'Level 2' || level == 'Level 3'}">
            		<div class="btn-div">
            			<form action="approve" method="post">
            				<input type="hidden" name="orderID" id="approve">
            				<button type="submit" id="btn-approve" class="btn btn-approve" disabled>APPROVE</button>
            			</form>
            			<form action="reject" method="post">
            				<input type="hidden" name="orderID" id="reject">
            				<button type="submit" id="btn-reject" class="btn btn-reject" disabled>REJECT</button>
            			</form>
                	</div>
                	<script>
                		function handleCheckbox(checkbox) {
                			var btnApprove = document.getElementById("btn-approve");
                			var btnReject = document.getElementById("btn-reject");
                			var tableRow = checkbox.parentNode.parentNode;
                			console.log(tableRow);
                			if(checkbox.checked) {
                				btnApprove.disabled = false;
                				btnApprove.classList.add("btn-active");
                				btnReject.disabled = false;
                				btnReject.classList.add("btn-active");
                				tableRow.classList.add("table-active");
                				var inputField1 = document.getElementById("approve");
                				var inputField2 = document.getElementById("reject");
                				inputField1.value = tableRow.cells[2].innerHTML;
                				inputField2.value = tableRow.cells[2].innerHTML;
                			}
                			else {
                				btnApprove.disabled = true;
                				btnApprove.classList.remove("btn-active");
                				btnReject.disabled = true;
                				btnReject.classList.remove("btn-active");
                				tableRow.classList.remove("table-active");
                			}
                			var chk = checkbox.parentNode.parentNode.parentNode;
                			var checkList = chk.getElementsByTagName("input");
                			for(var i = 0; i < checkList.length; i++) {
                				if(checkList[i] != checkbox && checkbox.checked) {
                					checkList[i].checked = false;
                					checkList[i].parentNode.parentNode.classList.remove("table-active");
                				}
                			}
                		}
                	</script>
            	</c:if>
                <div class="search-div">
                	<div class="i">
                        <i class="fas fa-search"></i>
                    </div>
                    <form action="search" method="post">
                        <input type="text" name="search" class="search" value="${search}" placeholder="search">
                    </form>
                </div>
            </div>
            <div class="hero-table">
				<table class="table">
					<thead>
						<tr>
							<th></th>
							<th>Order_Date</th>
							<th>Order_ID</th>
							<th>Customer_ID</th>
							<th>Customer_Name</th>
							<th>Order_Amount</th>
							<th>Approval_Status</th>
							<th>Approved_By</th>
							<th>Notes</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orders}">
							<tr>
								<td><input type="checkbox" id="checkbox" onclick="handleCheckbox(this);"></td>
								<td><c:out value="${order.orderDate}"/></td>
								<td><c:out value="${order.orderID}"/></td>
								<td><c:out value="${order.customerID}"/></td>
								<td><c:out value="${order.customerName}"/></td>
								<td><c:out value="${order.orderAmount}"/></td>
								<td><c:out value="${order.approvalStatus}"/></td>
								<td><c:out value="${order.approvedBy}"/></td>
								<td><c:out value="${order.notes}"/></td>	
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="hero-footer">
				<nav class="nav">
  					<ul class="pagination">
  						<c:if test="${numOfPages gt 1}">
  						<c:if test="${currentPage != 1}">
  							<li class="page-item">
      							<a class="page-link" href="dashboard?page=1"><i class="fas fa-angle-double-left"></i></a>      				
    						</li>
    						<li class="page-item">
      							<a class="page-link" href="dashboard?page=${currentPage - 1}"><i class="fas fa-angle-left"></i></a>      				
    						</li>
    					</c:if>
    						<p>Page</p>
    						<li class="page-item">
    							<a class="page-link">${currentPage}</a>
    						</li>
    						<p>of</p>
    						<li class="page-item">
    							<p>${numOfPages}</p>
    						</li>
    					<c:if test="${currentPage != numOfPages}">
    						<li class="page-item">
      							<a class="page-link" href="dashboard?page=${currentPage + 1}"><i class="fas fa-angle-right"></i></a>      				
    						</li>
    						<li class="page-item">
      							<a class="page-link" href="dashboard?page=${numOfPages}"><i class="fas fa-angle-double-right"></i></a>      				
    						</li>
    					</c:if>
  						</c:if>
  					</ul>
				</nav>
				<div class="text">
					Customers ${(currentPage - 1) * recordsPerPage + 1} - ${currentPage * recordsPerPage} of ${totalRows}
				</div>
			</div>
		</div>
	</div>
	<div id="add-modal" class="modal-container">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-text">
                    <h2>ADD ORDER</h2>
                </div>                
                <button class="close-btn" onClick="closeModal();">&times;</button>
            </div>
            <div>
                <form action="add" method="post" class="modal-form" onSubmit="validateForm();" name="myForm">
                    <div>
                        <p>Order ID</p>
                        <input type="text" name="orderID">
                    </div>
                    <div>
                        <p>Order Date</p>
                        <input type="date" name="orderDate">
                    </div>
                    <div>
                        <p>Customer ID</p>
                        <input type="text" name="customerID">
                    </div>
                    <div>
                        <p>Customer Name</p>
                        <input type="text" name="customerName">
                    </div>
                    <div>
                        <p>Order Amount</p>
                        <input type="text" name="orderAmount">
                    </div>
                    <div>
                        <p>Notes</p>
                        <input type="text" name="notes">
                    </div>
                    <input type="submit" value="ADD" class="modal-btn">
                </form>
            </div>
        </div>
    </div>
    <div id="edit-modal" class="modal-container">
        <div class="modal-content">
            <div class="modal-header">
                <div class="modal-text">
                    <h2>EDIT ORDER</h2>
                </div>                
                <button class="close-btn" onClick="closeModal();">&times;</button>
            </div>
            <div>
                <form action="edit" method="post" class="modal-form">
                    <div>
                        <p>Order ID</p>
                        <input type="text" name="orderID" id="edit-modal-orderID" readonly>
                    </div>
                    <div>
                        <p>Order Amount</p>
                        <input type="text" name="orderAmount" id="edit-modal-orderAmount" onInput="handleInput(this);">
                    </div>
                    <div>
                        <p>Notes</p>
                        <input type="text" name="notes" id="edit-modal-notes">
                    </div>
                    <div>
                        <p>Approved By</p>
                        <input type="text" name="approvedBy" id="edit-modal-approvedBy">
                    </div>
                    <input type="submit" value="SUBMIT" class="modal-btn">
                </form>
            </div>
        </div>
    </div>
    <script src="js/dashboard.js"></script>
</body>
</html>