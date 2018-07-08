<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="header.jsp"></jsp:include>
<body>
<jsp:include page="banner.jsp"></jsp:include>
<%
	JSONArray product = (JSONArray) request.getAttribute("product");
%>
	<!-- ##### Single Product Details Area Start ##### -->
	<section class="single_product_details_area d-flex align-items-center">
		<%
			for(int i = 0, length = product.length(); i < length; i ++)
			{
				JSONObject object = product.getJSONObject(i);
				int id = object.getInt("id");
				String name = object.getString("name");
				String nameB = object.getString("nameB");
				float price = object.getFloat("price");
				String image = object.getString("image");
		%>
		<!-- Single Product Thumb -->
		<div class="single_product_thumb clearfix">
			<div class="product_thumbnail_slides owl-carousel">
				<img src="<%=image%>1.jpg" alt="">
				<img src="<%=image%>2.jpg" alt="">
				<img src="<%=image%>3.jpg" alt="">
				<img src="<%=image%>4.jpg" alt="">
			</div>
		</div>
		<!-- Single Product Description -->
		<div class="single_product_desc clearfix">
			<span><%=nameB %></span>
				<h2><%=name %></h2>
			<p class="product-price">$<%=price %></p>
			<p class="product-desc">Online shopping is not currently offered in your market. Please visit your local H&M store for availability.
Please note that some products, colors and sizes shown Online may not be available in your country or store.</p>

			<!-- Form -->
			<form class="cart-form clearfix" method="post">
				<!-- Select Box -->
				<div class="select-box d-flex mt-50 mb-30">
					<select name="select" id="productSize" class="mr-5">
						<option value="value">Size: XL</option>
						<option value="value">Size: X</option>
						<option value="value">Size: M</option>
						<option value="value">Size: S</option>
					</select> <select name="select" id="productColor">
						<option value="value">Color: Black</option>
						<option value="value">Color: White</option>
						<option value="value">Color: Red</option>
						<option value="value">Color: Purple</option>
					</select>
				</div>
				<!-- Cart & Favourite Box -->
				<div class="cart-fav-box d-flex align-items-center">
					<!-- Cart -->
					<button name="addtocart" onclick="addCart(<%=id%>,'<%=name%>','<%=price%>','<%=image%>1.jpg','<%=nameB%>')" class="btn essence-btn">Add to cart</button>
					<!-- Favourite -->
					<div class="product-favourite ml-4">
						<a href="#" class="favme fa fa-heart"></a>
					</div>
				</div>
			</form>
		</div>
		<%	} %>
	</section>
	<!-- ##### Single Product Details Area End ##### -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>