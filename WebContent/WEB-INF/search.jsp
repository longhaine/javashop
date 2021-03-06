<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="header.jsp"></jsp:include>
<body>
<jsp:include page="banner.jsp"></jsp:include>
<%
	String q = request.getParameter("q");
	JSONArray categories = (JSONArray) request.getAttribute("categories");
	JSONArray products = (JSONArray) request.getAttribute("products");
	int productCount = products.length();
	String categoryParameter = (String)request.getAttribute("categoryParameter");
	String pageParameter =request.getParameter("page");
	if(pageParameter == null){pageParameter = "1";}
%>
    <!-- ##### Breadcumb Area Start ##### -->
    <div class="breadcumb_area bg-img" style="background-image: url(img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="page-title text-center" page="<%=pageParameter%>">
                        <h2><%=categoryParameter %></h2>
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
                            <p><a href="search?q=<%=q%>">All</a></p>
                            <!--  Catagories  -->
                            <div class="catagories-menu">
                                <ul id="menu-content2" class="menu-content collapse show">
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#View by Category">
                                        <a href="#">View by Category</a>
                                        <ul class="sub-menu collapse show" id="View by Category">
                                            <%
                                            	for(int i = 0,length=categories.length() ; i < length ; i++)
                                            	{
                                            		JSONObject category = categories.getJSONObject(i);
                                            		String name = category.getString("name");
                                            		
                                            %>
                                            <li><a href="?category=<%=name%>&q=<%=q%>"><%=name %></a></li>
                                            <%
                                            	}
                                            %>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <!-- ##### Single Widget ##### -->
                        <div class="widget price mb-50">
                            <!-- Widget Title -->
                            <h6 class="widget-title mb-30">Filter by</h6>
                            <!-- Widget Title 2 -->
                            <p class="widget-title2 mb-30">Price</p>

                            <div class="widget-desc">
                                <div class="slider-range">
                                    <div data-min="49" data-max="360" data-unit="$" class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" data-value-min="49" data-value-max="360" data-label-result="Range:">
                                        <div class="ui-slider-range ui-widget-header ui-corner-all"></div>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>
                                    </div>
                                    <div class="range-price">Range: $49.00 - $360.00</div>
                                </div>
                            </div>
                        </div>

                        <!-- ##### Single Widget ##### -->
                        <div class="widget color mb-50">
                            <!-- Widget Title 2 -->
                            <p class="widget-title2 mb-30">Color</p>
                            <div class="widget-desc">
                                <ul class="d-flex">
                                    <li><a href="#" class="color1"></a></li>
                                    <li><a href="#" class="color2"></a></li>
                                    <li><a href="#" class="color3"></a></li>
                                    <li><a href="#" class="color4"></a></li>
                                    <li><a href="#" class="color5"></a></li>
                                    <li><a href="#" class="color6"></a></li>
                                    <li><a href="#" class="color7"></a></li>
                                    <li><a href="#" class="color8"></a></li>
                                    <li><a href="#" class="color9"></a></li>
                                    <li><a href="#" class="color10"></a></li>
                                </ul>
                            </div>
                        </div>

                        <!-- ##### Single Widget ##### -->
                        <div class="widget brands mb-50">
                            <!-- Widget Title 2 -->
                            <p class="widget-title2 mb-30">Brands</p>
                            <div class="widget-desc">
                                <ul>
                                    <li><a href="#">Asos</a></li>
                                    <li><a href="#">Mango</a></li>
                                    <li><a href="#">River Island</a></li>
                                    <li><a href="#">Topshop</a></li>
                                    <li><a href="#">Zara</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-md-8 col-lg-9">
                    <div class="shop_grid_product_area">
                        <div class="row">
                            <div class="col-12">
                                <div class="product-topbar d-flex align-items-center justify-content-between">
                                    <!-- Total Products -->
                                    <div class="total-products">
                                        <p><span><%=productCount %></span> products found</p>
                                    </div>
                                    <!-- Sorting -->
                                    <div class="product-sorting d-flex">
                                        <p>Sort by:</p>
                                        <form action="#" method="get">
                                            <select name="select" id="sortByselect">
                                                <option value="best">Best Match</option>
                                                <option value="new">Newest</option>
                                                <option value="low">Price: $ - $$</option>
                                                <option value="high">Price: $$ - $</option>
                                            </select>
                                            <input type="submit" class="d-none" value="">
                                        </form>                                     
                                    </div>
                                    
                                </div>
                            </div>
                        </div>

                        <div class="row" id="mylist">
							<%
								for(int i = 0 ; i < productCount ; i++)
								{
									JSONObject product = products.getJSONObject(i);
									String name = product.getString("name");
									float price = product.getFloat("price");
									String image = product.getString("image");
									String nameB = product.getString("nameB");
									int id = product.getInt("id");
							%>
                            <!-- Single Product -->
                            <div class="col-12 col-sm-6 col-lg-4 items">
                                <div class="single-product-wrapper" id="<%=id%>" price="<%=price%>">
                                    <!-- Product Image -->
                                    <div class="product-img">
                                        <img src="<%=image%>1.jpg" alt="">
                                        <!-- Hover Thumb -->
                                        <img class="hover-img" src="<%=image%>2.jpg" alt="">

                                        <!-- Favourite -->
                                        <div class="product-favourite">
                                            <a href="#" class="favme fa fa-heart"></a>
                                        </div>
                                    </div>

                                    <!-- Product Description -->
                                    <div class="product-description">
                                        <span><%=nameB %></span>
                                        <a href="product?id=<%=id%>">
                                            <h6><%=name %></h6>
                                        </a>
                                        <p class="product-price">$<%=price %></p>

                                        <!-- Hover Content -->
                                        <div class="hover-content">
                                            <!-- Add to Cart -->
                                            <div class="add-to-cart-btn">
                                                <a href="#" onclick="addCart(<%=id%>,'<%=name%>','<%=price%>','<%=image%>1.jpg','<%=nameB%>')" class="btn essence-btn">Add to Cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
							<%	} %>
                        </div>
                    </div>
                    
                    <div class="text-center disabled">
                    <button class="btn btn-md" id="btnLoadMore">+ Load More</button>
                    </div>

                </div>

            </div>
        </div>
    </section>
    <!-- ##### Shop Grid Area End ##### -->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>