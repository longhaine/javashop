var category = $('div .page-title').children('h2').text();
var limitItems = 3;
var itemslength = $(".items").length;
$(".items:gt(" + (limitItems + -1) + ")").hide();
var productCount = $('div .total-products').find('span').text();
var totalPages = Math.ceil(productCount / limitItems);
// load pagination by totalPages
if (totalPages < 1) {
	totalPages = 1;
}
var pageParameter = $('div .page-title').attr('page');
if(pageParameter > totalPages){ pageParameter = totalPages;}
var btnLoadMore = $('#btnLoadMore');
btnLoadMore.on('click',function(){
	if(pageParameter<totalPages)
		{
		pageParameter++;
		loadProductsByPage(pageParameter);
		}
});
function loadProductsByPage(pageParameter){
	if(pageParameter <= totalPages){
		productPage = limitItems * pageParameter;
		$(".items:gt(0)").hide();
		for( var i = 0 ; i < productPage ; i++)
			{
			$(".items:eq(" + i + ")").show();
			}
		if(pageParameter != 1){updateURL(pageParameter);}
		if(pageParameter == totalPages)
			{
			$('#btnLoadMore').toggleClass('disabled');
			}
	}
}
loadProductsByPage(pageParameter);

//sort products
$("#sortByselect").on("change", function() {
	sortUsingNestedText($('#mylist'), '.items', 'div', this.value,pageParameter);
});
function sortUsingNestedText(parent, childSelector, keySelector, type,pageParameter) {
	var items = parent.children(childSelector).sort(
			function(a, b) {
				if (type === "best") {
					var vA = $(keySelector, a).attr('id');
					var vB = $(keySelector, b).attr('id');
					return (parseInt(vA) < parseInt(vB)) ? -1
							: (parseInt(vA) > parseInt(vB)) ? 1 : 0;
				}
				if (type === "new") {
					var vA = $(keySelector, a).attr('id');
					var vB = $(keySelector, b).attr('id');
					return (parseInt(vA) > parseInt(vB)) ? -1
							: (parseInt(vA) < parseInt(vB)) ? 1 : 0;
				}
				if (type === "low") {
					var vA = $(keySelector, a).attr('price');
					var vB = $(keySelector, b).attr('price');
					return (parseFloat(vA) < parseFloat(vB)) ? -1
							: (parseFloat(vA) > parseFloat(vB)) ? 1 : 0;
				}
				if (type === "high") {
					var vA = $(keySelector, a).attr('price');
					var vB = $(keySelector, b).attr('price');
					return (parseFloat(vA) > parseFloat(vB)) ? -1
							: (parseFloat(vA) < parseFloat(vB)) ? 1 : 0;
				}
			});
	parent.append(items);
	loadProductsByPage(pageParameter);
	if(pageParameter >= totalPages){$('#btnLoadMore').toggleClass('disabled')} // for toggleClass two times
}
function updateURL(page) {
	if (history.pushState) {
		var newurl = "";
		if (category === "all") {
			var url = window.location.href.split('?page')[0];
			newurl = url + "?page=" + page;
		} else {
			var url = window.location.href.split('&page')[0];
			newurl = url + "&page=" + page;
		}

		window.history.pushState({
			path : newurl
		}, '', newurl);
	}
}
// add cart jquery and add cart in session
function addCart(id, name, price, img, brand) {
	// add cart jquery
	var cardList = $('div .cart-list');
	var card = "<!-- Single Cart Item --><div class='single-cart-item' id='c"
			+ id
			+ "'><a href='#' class='product-image'><img src='"
			+ img
			+ "' class='cart-thumb' alt=''><!-- Cart Item Desc --><div class='cart-item-desc'><span class='product-remove' cid=c"
			+ id
			+ "><i class='fa fa-close' aria-hidden='true'></i></span><span class='badge'>"
			+ brand
			+ "</span><h6>"
			+ name
			+ "</h6><p class='size'>Size: S</p><p class='color'>Color: Red</p><p class='price'>$"
			+ price + "</p></div></a></div>";
	cardList.append(card);
	alert(name + " is added!");
	caculatingCards();
	caculatingSummary();
	// add cart in session
	$.ajax({
		type : 'get',
		url : 'addcart?product=' + id
	});
	return false;
}

// remove cart jquery and remove card in session
$(document).on('click', '.product-remove', function(e) {
	var id = $(this).attr('cid');
	$('#' + id + '').remove();
	caculatingCards();
	caculatingSummary();
	caculatingCheckout(id);
	//remove card in session
	$.ajax({
		type : 'get',
		url : 'removecart?product=' + id.substring(1)
	});
	return false;
});
// count carts
function countCarts(){
	var x = document.getElementsByClassName("single-cart-item").length;
	return x
}
// count carts and add value to icon
function caculatingCards() {
	var x = countCarts();
	var a = $('#rightSideCart').children('span');
	var b = $('#essenceCartBtn').children('span');
	a.text(x);
	b.text(x);
	if(x <= 0){
		$('.checkout-btn .essence-btn').hide();
	}
	else{
		$('.checkout-btn .essence-btn').show();
	}
}
caculatingCards();
// count total bill
function caculatingSummary() {
	var subTotal = 0;
	$("div .single-cart-item").each(
			function(index) {
				subTotal = subTotal
						+ parseFloat($(this).find('p').last().text().substring(
								1));
			});
	var liSubtotal = $('div .cart-amount-summary').find('li').first();
	liSubtotal.children('span').last().text("$" + subTotal);
	var liTotal = $('div .cart-amount-summary').find('li').last();
	liTotal.children('span').last().text("$" + subTotal);
}
caculatingSummary();
// link login, register, history, logout
$('#login').on('click',function(){
	location.href = "http://localhost:8080/WebShop/login";
});
$('#register').on('click',function(){
	location.href = "http://localhost:8080/WebShop/register";
});
$('#yourinfo').on('click',function(){
	location.href = "http://localhost:8080/WebShop/your-info";
});
$('#changepassword').on('click',function(){
	location.href = "http://localhost:8080/WebShop/change-pass";
});
$('#history').on('click',function(){
	location.href = "http://localhost:8080/WebShop/orders";
});
$('#logout').on('click',function(){
	$.ajax({
		type : 'post',
		url : 'logout'
	});
	location.href = "http://localhost:8080/WebShop/"
});
//---------------------------

//-----------------login page
var message = $('#checkValid');
if(message.attr('message') != 'default')
	{
		message.append("<h4 class='text-center'>"+message.attr('message')+"</h4>");
	}
//-----------------register page
// checkout caculating

////////////////////////////////JS CHECKOUT PAGE
function caculatingCheckout(id){
	$('#checkout'+id).remove();
	var price = 0;
	var li = $('div .order-details-confirmation').children('ul').children('li');
	var length = li.length;
	for(var i = 1 ; i < length-3 ; i++){
		price = price + parseFloat(li.eq(i).attr('price'));
	}
	li.eq(length-3).children('span').last().text('$'+price);
	li.eq(length-1).children('span').last().text('$'+price);
}


////////////////////////////////JS ORDER PAGE
// move to your order when click order list and load order list
var idOrder = 0;
$("div .order-list").children('ul').first().children('li').click(function() {
    $('html, body').animate({
        scrollTop: $("#your-order").offset().top
    }, 500);
    var id = $(this).attr('id');
    if(idOrder != id){ //check id already loaded orderlist
       idOrder = id;
       $.ajax({
       	type : 'get',
           url:"orderdetails?id="+id,  
           success:function(data) {
             $('div .order-details-confirmation').find('li:gt(0)').remove();
             $('div .order-details-confirmation').find('li:eq(0)').after(data);
           }
       }); 
    }
});

//move to order list when click pagination

var orderPage = $('div .cart-page-heading').attr('page');
$('span.page-order').text('Page :'+orderPage);
var orders = $('div .cart-page-heading').attr('count');
var litmitOrders = 5;
$(".ul-list li:gt("+(litmitOrders -1)+")").hide();
var totalNumbers = Math.ceil(orders/litmitOrders);
if(totalNumbers < 1){totalNumbers = 1;}
for (var i = 1; i <= totalNumbers; i++) {
	$(".pagination-order").append("<li class='li-number'><a href='#'>"+i+"</a></li>");
}
$(".pagination-order").append("<li class='next'><a href='#'><i class='fa fa-angle-right'></i></a></li>");
$(".pagination-order li:eq(1)").addClass('active');
$(".pagination-order .li-number").on("click", function() {
	if($(this).hasClass("active")){
		return false;
	}
	else{
	var currentPage = $(this).index();
	updateURLOrder(currentPage);
	$('.pagination-order li').removeClass('active');
	$(this).addClass('active');
	var grandTotal = litmitOrders * currentPage;
	$(".ul-list li").hide();
	for (var i = grandTotal - litmitOrders; i < grandTotal; i++) {
		$(".ul-list li:eq(" + i + ")").show();
	}
}
});
//next pagination button click
$(".next").on("click", function(){
	var currentPage = $(".pagination-order .active").index();
	if (currentPage === totalNumbers){
		return false
	}
	else{
		currentPage++;
		updateURLOrder(currentPage);
		$('.pagination-order li').removeClass('active');
		$(".pagination-order li:eq(" + currentPage + ")").addClass('active');
		var grandTotal = litmitOrders * currentPage;
		$(".ul-list li").hide();
		for (var i = grandTotal - litmitOrders; i < grandTotal; i++){
			$(".ul-list li:eq(" + i + ")").show();
}
	}
});
//previouse pagination button click
$(".previous").on("click", function(){
	var currentPage = $(".pagination-order .active").index();
	if (currentPage === 1){
		return false
	}
	else{
		currentPage--;
		updateURLOrder(currentPage);
		$('.pagination-order li').removeClass('active');
		$(".pagination-order li:eq(" + currentPage + ")").addClass('active');
		var grandTotal = litmitOrders * currentPage;
		$(".ul-list li").hide();
		for(var i = grandTotal - litmitOrders; i < grandTotal; i++) {
			$(".ul-list li:eq(" + i + ")").show();
		}
	}
});
function updateURLOrder(page) {
	if (history.pushState) {
		var newurl = "";
		var url = window.location.href.split('?page')[0];
		newurl = url + "?page=" + page;
		window.history.pushState({path : newurl}, '', newurl);
	}
	$('span.page-order').text('Page :'+page);
}
function loadOrdersByPage(page) {
	if (page == null || page < 1){
		page = 1;
	}
	if (page > totalNumbers){
		page = totalNumbers;
	}
	$('.pagination-order li').removeClass('active');
	$(".pagination-order li:eq(" + page + ")").addClass('active');
	var grandTotal = litmitOrders * page;
	$(".ul-list li").hide();
	for (var i = grandTotal - litmitOrders; i < grandTotal; i++) {
		$(".ul-list li:eq(" + i + ")").show();
	}
}
//load Items by page parameter
loadOrdersByPage(orderPage);
//////////////////////////// END JS ORDER PAGE








////////////////////////// JS ADMIN PAGE

var pageAdmin = $('table.table').attr('page');
var records = $('tbody tr').length;
var litmitRecords = 5;
$("tbody tr:gt("+(litmitRecords -1)+")").hide();
var totalNumbersA = Math.ceil(records/litmitRecords);
if(totalNumbersA < 1){totalNumbersA = 1;}
for (var i = 1; i <= totalNumbersA; i++) {
	$(".pagination-a").append("<li class='li-number'><a class='page-link' href='#'>"+i+"</a></li>");
}
$(".pagination-a").append("<li class='next-a'><a class='page-link' href='#'><i class='fa fa-angle-right'></i></a></li>");
$(".pagination-a li:eq(1)").addClass('active');
$(".pagination-a .li-number").on("click", function() {
	if($(this).hasClass("active")){
		return false;
	}
	else{
	var currentPage = $(this).index();
	updateURLAdmin(currentPage);
	$('.pagination-a li').removeClass('active');
	$(this).addClass('active');
	var grandTotal = litmitRecords * currentPage;
	$("tbody tr").hide();
	for (var i = grandTotal - litmitRecords; i < grandTotal; i++) {
		$("tbody tr:eq(" + i + ")").show();
	}
}
});
//next pagination button click
$(".next-a").on("click", function(){
	var currentPage = $(".pagination-a .active").index();
	if (currentPage === totalNumbersA){
		return false
	}
	else{
		currentPage++;
		updateURLAdmin(currentPage);
		$('.pagination-a li').removeClass('active');
		$(".pagination-a li:eq(" + currentPage + ")").addClass('active');
		var grandTotal = litmitRecords * currentPage;
		$("tbody tr").hide();
		for (var i = grandTotal - litmitRecords; i < grandTotal; i++){
			$("tbody tr:eq(" + i + ")").show();
}
	}
});
//previouse pagination button click
$(".previous-a").on("click", function(){
	var currentPage = $(".pagination-a .active").index();
	if (currentPage === 1){
		return false
	}
	else{
		currentPage--;
		updateURLAdmin(currentPage);
		$('.pagination-a li').removeClass('active');
		$(".pagination-a li:eq(" + currentPage + ")").addClass('active');
		var grandTotal = litmitRecords * currentPage;
		$("tbody tr").hide();
		for(var i = grandTotal - litmitRecords; i < grandTotal; i++) {
			$("tbody tr:eq(" + i + ")").show();
		}
	}
});
function updateURLAdmin(page) {
	if (history.pushState) {
		var newurl = "";
		var url = window.location.href.split('?page')[0];
		newurl = url + "?page=" + page;
		window.history.pushState({path : newurl}, '', newurl);
	}
}
function loadOrdersByPageA(page) {
	if (page == null || page < 1){
		page = 1;
	}
	if (page > totalNumbersA){
		page = totalNumbersA;
	}
	$('.pagination-a li').removeClass('active');
	$(".pagination-a li:eq(" + page + ")").addClass('active');
	var grandTotal = litmitRecords * page;
	$("tbody tr").hide();
	for (var i = grandTotal - litmitRecords; i < grandTotal; i++) {
		$("tbody tr:eq(" + i + ")").show();
	}
}
loadOrdersByPageA(pageAdmin);
// button admin page
var deleteBtn = $('i.fa-close');
deleteBtn.click(function(){
	var id = $(this).parent().attr('class').substring(2);
	var table = $(this).parent().attr('table');
	var r = confirm('Do you want delete this?');
	var that = $(this).closest('tr');
	if(r == true)
		{
	       $.ajax({
	          	type : 'get',
	              url:"delete?table="+table+"&id="+id,  
	              success:function(data) {
	            	  if(data == "success"){
	            		  that.remove();
	            	  }
	            	  else{
	            		  alert(data);
	            	  }
	              }
	          }); 
		}
})