<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/index.css">   
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"> 
    <title>Order Management App</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
		if(session.getAttribute("username") != null) {
			response.sendRedirect("dashboard?page=1");
		}
	%>
	<c:if test="${not empty loginError}">
		<script>
			window.addEventListener("load", function(){
				alert("${loginError}");
			});
		</script>
	</c:if>
	<div class="container">
        <div class="hero-container">
        	<header class="header">          
            	<img src="images/hrc-logo.svg" alt="hrc-logo" class="hrc-logo">
            </header>
            <div>            
            	<img src="images/human-machine-hand-homepage.svg" alt="hrc-bg" class="hrc-bg">
            </div>
            <div class="hero-title">
                <h2>ORDER MANAGEMENT APPLICATION</h2>
            </div>
            <div class="hero-content">
                <p class="form-title">Sign in</p>
                <form action="login" method="post">
                    <div class="label">
                        <p>Username</p>
                        <input type="text" name="username" required>
                    </div>
                    <div class="label">
                        <p>Password</p>
                        <input type="password" name="password" required>
                    </div>
                    <input type="submit" value="Sign in" class="btn">
                </form>
            </div>
        </div>
    </div>
</body>
</html>