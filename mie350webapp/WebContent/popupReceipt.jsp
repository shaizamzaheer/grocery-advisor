<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.mie.model.*, java.text.SimpleDateFormat"%>
    

  <div id="popup-receipt-container">
    <h1 id="popup-receipt-title">Receipt</h1>
    <div id="popup-receipt-close"><i class="material-icons">close</i></div>

    <div id="popup-receipt-contents">
    
    <% List<ReceiptItem> receipt = (ArrayList<ReceiptItem>)session.getAttribute("receipt"); 
    	double total = 0.00;
    	if (receipt != null && !receipt.isEmpty()) {
    		
    	for (ReceiptItem i : receipt) { total += i.getPrice() * i.getQuantity(); }
  		
		for (ReceiptItem item : receipt) {
    
    %>
      <div class="popup-receipt-item">
        <p class="popup-receipt-quantity">
          <%= item.getQuantity() %>
        </p><!--
        --><p class="popup-receipt-info"><%= item.getItemName() %>, <%= item.getAmount() %></p><!--
        --><p class="popup-receipt-cost">$<%= item.getPrice() %></p>
        <% if (item.getSaleEnd() != null) { 
        	String dateSaleEnd = new SimpleDateFormat("MM/dd/yyyy").format(item.getSaleEnd()); %><span>Sale Ends On <%= dateSaleEnd %></span><% } %>
      </div>
      
      <% } //end of for 
      } //end of if%>
      
    </div>
    
    <div id="popup-receipt-total">
      <p><span>Total:</span>  <span>$<%= String.format("%.2f", total) %></span></p>
    </div>
  </div>
  
