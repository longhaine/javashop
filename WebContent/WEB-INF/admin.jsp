<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="header.jsp"></jsp:include>
<%
	JSONArray productList = (JSONArray) request.getAttribute("productList");
	JSONArray columns = (JSONArray) request.getAttribute("columns");
	JSONArray categories = (JSONArray) request.getAttribute("categories");
	JSONArray brands = (JSONArray) request.getAttribute("brands");
	String pageParameter =request.getParameter("page");
	if(pageParameter == null){pageParameter = "1";}
%>
<body>
    <header class="header_area">
        <div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
            <!-- Classy Menu -->
            <nav class="classy-navbar" id="essenceNav">
                <!-- Logo -->
                <a class="nav-brand" href="index.html"><img src="img/logo/tlogovogue.png" alt=""></a>
             </nav>
        </div>
    </header>
<!-- ##### Breadcumb Area Start ##### -->
    <div class="breadcumb_area bg-img" style="background-image: url(img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="page-title text-center">
                        <h2>dresses</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Breadcumb Area End ##### -->

    <!-- ##### Shop Grid Area Start ##### -->
    <section class="shop_grid_area section-padding-80">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3">
                    <div class="shop_sidebar_area">

                        <!-- ##### Single Widget ##### -->
                        <div class="widget catagory mb-50">
                            <!-- Widget Title -->
                            <h6 class="widget-title mb-30">Catagories</h6>

                            <!--  Catagories  -->
                            <div class="catagories-menu">
                                <ul id="menu-content2" class="menu-content collapse show">
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#clothing">
                                        <a href="#">clothing</a>
                                        <ul class="sub-menu collapse show" id="clothing">
                                            <li><a href="#">All</a></li>
                                            <li><a href="#">Bodysuits</a></li>
                                            <li><a href="#">Dresses</a></li>
                                            <li><a href="#">Hoodies &amp; Sweats</a></li>
                                            <li><a href="#">Jackets &amp; Coats</a></li>
                                            <li><a href="#">Jeans</a></li>
                                            <li><a href="#">Pants &amp; Leggings</a></li>
                                            <li><a href="#">Rompers &amp; Jumpsuits</a></li>
                                            <li><a href="#">Shirts &amp; Blouses</a></li>
                                            <li><a href="#">Shirts</a></li>
                                            <li><a href="#">Sweaters &amp; Knits</a></li>
                                        </ul>
                                    </li>
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#shoes" class="collapsed">
                                        <a href="#">shoes</a>
                                        <ul class="sub-menu collapse" id="shoes">
                                            <li><a href="#">All</a></li>
                                            <li><a href="#">Bodysuits</a></li>
                                            <li><a href="#">Dresses</a></li>
                                            <li><a href="#">Hoodies &amp; Sweats</a></li>
                                            <li><a href="#">Jackets &amp; Coats</a></li>
                                            <li><a href="#">Jeans</a></li>
                                            <li><a href="#">Pants &amp; Leggings</a></li>
                                            <li><a href="#">Rompers &amp; Jumpsuits</a></li>
                                            <li><a href="#">Shirts &amp; Blouses</a></li>
                                            <li><a href="#">Shirts</a></li>
                                            <li><a href="#">Sweaters &amp; Knits</a></li>
                                        </ul>
                                    </li>
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#accessories" class="collapsed">
                                        <a href="#">accessories</a>
                                        <ul class="sub-menu collapse" id="accessories">
                                            <li><a href="#">All</a></li>
                                            <li><a href="#">Bodysuits</a></li>
                                            <li><a href="#">Dresses</a></li>
                                            <li><a href="#">Hoodies &amp; Sweats</a></li>
                                            <li><a href="#">Jackets &amp; Coats</a></li>
                                            <li><a href="#">Jeans</a></li>
                                            <li><a href="#">Pants &amp; Leggings</a></li>
                                            <li><a href="#">Rompers &amp; Jumpsuits</a></li>
                                            <li><a href="#">Shirts &amp; Blouses</a></li>
                                            <li><a href="#">Shirts</a></li>
                                            <li><a href="#">Sweaters &amp; Knits</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
				</div>
				
                <div class="col-12 col-md-8 col-lg-9">
                    <div class="shop_grid_product_area">
                        <div class="row">
							<table class="table" page="<%=pageParameter%>">
								<caption>
									<h6>List of users</h6>
									<ul class="pagination pagination-a">
			                            <li class="previous-a"><a class="page-link" href="#"><i class="fa fa-angle-left"></i></a></li>
		                        	</ul>
		                        	</caption>
								<thead>
									<tr>
									  <%
									  	for(int i = 0,length=columns.length(); i < length ; i++)
									  		{
									  			JSONObject column = columns.getJSONObject(i);
									  			String field = column.getString("Field");
									  %>
								      <th scope="col"><%=field%></th>
								      <%} %>
								      <th scope="col"><a href="#" data-toggle="modal" data-target="#addProduct">Add<i title="add"class="fa fa-plus-circle"></i></a></th>
								    </tr>
								 </thead>
								  <tbody>
								  <%
								  	for(int i =0, length=productList.length(); i <length ; i++)
								  	{
								  		JSONObject product = productList.getJSONObject(i);
								  		int id = product.getInt("id");
								  		String name = product.getString("name");
								  		int id_categories = product.getInt("id_categories");
								  		float price = product.getFloat("price");
								  		String image = product.getString("image");
								  		String gender = product.getString("gender");
								  		int id_brands = product.getInt("id_brands");
								  %>
								    <tr>
								      <th scope="row"><%=id %></th>
								      <td><%=name %></td>
								      <td><%=id_categories %></td>
								      <td><%=price %></td>
								      <td><img style="max-width:50%;" src="<%=image %>1.jpg"></td>
								      <td><%=gender %></td>
								      <td><%=id_brands %></td>
								      <td>
									      <a href="#" class="id<%=id%>" table="products" data-toggle="modal" data-target="#editProduct"><i title="edit"class="fa fa-file"></i></a>
									      <a href="#" class="id<%=id%>" table="products"><i title="delete" class="fa fa-close"></i></a>
								      </td>
								    </tr>
									<%}%>
						    
								  </tbody>
								  
								</table>                                         	
                        </div>
                    </div>
                </div>				
				
				
			</div>
          </div>
    </section>
<div id="addProduct" class="modal fade" role="dialog">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<h4 class="modal-title">Add new Product</h4>
</div>
<div class="modal-body">
<div class="container">
<div class="col-12 col-md-offset-12">
  <form class="form-horizontal center" action="upload" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">Name:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="">
      </div>
    </div>
    <div class="row">
    	<div class="col-sm-3">
      		<label class="control-label col-sm-12" for="id_categories">Category:</label>
     	</div>
      	<div class="col-sm-9 product-sorting">
        	<select name="id_categories">
        		<%
        			for(int i = 0, length = categories.length() ; i < length ; i++)
        			{
        				JSONObject category = categories.getJSONObject(i);
        				int id = category.getInt("id");
        				String name = category.getString("name");
        		%>
        		<option value="<%=id%>"><%=name %></option>
        		<%} %>
        	</select>
      	</div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="price">Price:</label>
      <div class="col-sm-10">
        <input type="number" class="form-control" id="price" placeholder="Enter price" name="price" value="" min="0" step="0.01">
      </div>
    </div>
    <div class="row">
    	<div class="col-sm-3">
      		<label class="control-label col-sm-12" for="gender">Gender:</label>
     	</div>
      	<div class="col-sm-9 product-sorting">
        	<select name="gender">
        		<option value="women">Women</option>
        		<option value="men">men</option>
        	</select>
      	</div>
    </div>
    <div class="row">
    	<div class="col-sm-3">
      		<label class="control-label col-sm-12" for="id_brands">Brand:</label>
     	</div>
      	<div class="col-sm-9 product-sorting">
        	<select name="id_brands">
        		<%
        			for(int i=0,length = brands.length(); i < length ; i++)
        			{
        				JSONObject brand = brands.getJSONObject(i);
        				int id = brand.getInt("id");
        				String name = brand.getString("name");
        		%>
        		<option value="<%=id%>"><%=name %></option>
        		<%} %>
        	</select>
      	</div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label class="control-label col-sm-12" for="image">4 Images:</label>
          <label><input type="file" name="file" value="file" multiple="multiple"></label>
        </div>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>    
  </form>
  </div>
</div>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
</div>
</div>
</div>
</div>
<div id="editProduct" class="modal fade" role="dialog">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<h4 class="modal-title">Modal Header</h4>
</div>
<div class="modal-body">
<p>Some text in the edit.</p>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
</div>
</div>
</div>
</div>
    <!-- ##### Shop Grid Area End ##### -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>