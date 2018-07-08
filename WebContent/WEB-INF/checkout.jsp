<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="header.jsp"></jsp:include>
<body>
<jsp:include page="banner.jsp"></jsp:include>
<%
	String message = (String) request.getAttribute("message");
	if(message == null){message = "default";}
	JSONObject user = (JSONObject) session.getAttribute("user");
	String name = "";
	String address = "";
	String phone = "";
	String disabled = "";
	JSONObject guest = (JSONObject) session.getAttribute("guest");
	if(user != null) // in case email existence 
	{
		name = user.getString("name");
		address = user.getString("address");
		phone = user.getString("phone");
		disabled = "disabled";
	}
	else if(guest!= null)
	{
		name = guest.getString("name");
		address = guest.getString("address");
		phone = guest.getString("phone");
	}
	JSONArray productList = (JSONArray) session.getAttribute("productList");
	float totalPrice = 0;
%>
   <!-- ##### Breadcumb Area Start ##### -->
    <div class="breadcumb_area bg-img" style="background-image: url(img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="page-title text-center">
                        <h2>Checkout</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Breadcumb Area End ##### -->

    <!-- ##### Checkout Area Start ##### -->
    <div class="checkout_area section-padding-80">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-6">
                    <div class="checkout_details_area mt-50 clearfix">

                        <div class="cart-page-heading mb-30">
                            <h5>Billing Address</h5>
                        </div>

                        <form action="check-out" method="post">
                            <div class="row">
                                <div class="col-12 mb-3">
                                    <label for="yourname">Your Name <span>*</span></label>
                                    <input type="text" class="form-control" id="yourname" name="name" value="<%=name %>" required <%=disabled %>>
                                </div>
                                <div class="col-12 mb-3">
                                    <label for="street_address">Address <span>*</span></label>
                                    <input type="text" class="form-control mb-3" id="street_address" name="address" value="<%=address %>" <%=disabled %>>
                                </div>
                                <div class="col-12 mb-3">
                                    <label for="phone_number">Phone No <span>*</span></label>
                                    <input type="number" class="form-control" id="phone_number" min="0" name="phone" value="<%=phone %>" <%=disabled %>>
                                </div>
                                <div class="col-12 mb-4">
 								<%
 									if(user!= null)
 									{
 								%>
                                    <span><a href="your-info">Edit your info here !</a></span>
                                <% }%>
                                	<input type="submit" class="btn essence-btn pull-right" value="Place Order">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="col-12 col-md-6 col-lg-5 ml-lg-auto">
                    <div class="order-details-confirmation">

                        <div class="cart-page-heading">
                            <h5>Your Order</h5>
                            <p>The Details</p>
                        </div>

                        <ul class="order-details-form mb-4">
                            <li><span>Product</span> <span>Total</span></li>
                            <%
                            	for(int i = 0,length=productList.length(); i < length ; i++)
                            	{
                            		JSONObject product = productList.getJSONObject(i);
                            		int id = product.getInt("id");
                            		String nameProduct = product.getString("name");
                            		float price = product.getFloat("price");
                            		totalPrice = totalPrice + price;
                            %>
                            <li id="checkoutc<%=id%>" price="<%=price%>"><span><%=nameProduct %></span> <span>$<%=price %></span></li>
                            <% }%>
                            <li><span>Subtotal</span> <span>$<%=totalPrice %></span></li>
                            <li><span>Shipping</span> <span>Free</span></li>
                            <li><span>Total</span> <span>$<%=totalPrice %></span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Checkout Area End ##### -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>