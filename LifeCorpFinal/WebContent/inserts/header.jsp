<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="header">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
					</button>
					<a class="navbar-brand" href="index.jsp">LifeCorp</a>
				</div>	
								
					<div id="navbar" class="collapse navbar-collapse">
						<ul class="nav navbar-nav">
						<c:if test="${admin == null || admin == false}">
							<li class="active"><a href="index.jsp">Home</a></li>
						</c:if>
						<c:if test="${admin == true}">
							<li class="active"><a href="admin.jsp">Admin Home</a></li>
						</c:if>
							<c:if test="${customer != null && admin != true}">
								<li><a href="/OrderHistory">Order History</a></li>
							</c:if>
							<c:if test="${customer == null || admin == null}">
								<li><a href="/login.jsp">Login</a></li>
							</c:if>
						</ul>
					</div><!--/.nav-collapse -->	
			</div>
		</nav>
		<br />
		<br />
		<br />
		<br />
	</header>