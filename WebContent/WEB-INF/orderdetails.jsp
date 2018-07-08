<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%
	JSONArray productList = (JSONArray) request.getAttribute("productList");
	float totalPrice = 0;
%>
<%
	for(int i=0,length = productList.length(); i < length; i++)
	{
		JSONObject product = productList.getJSONObject(i);
		String name = product.getString("name");
		float price = product.getFloat("price");
		totalPrice = totalPrice + price;
%>
<li><span><%=name %></span> <span>$<%=price %></span></li>
<%}%>
<li><span>Subtotal</span> <span>$<%=totalPrice %></span></li>
<li><span>Shipping</span> <span>Free</span></li>
<li><span>Total</span> <span>$<%=totalPrice %></span></li>