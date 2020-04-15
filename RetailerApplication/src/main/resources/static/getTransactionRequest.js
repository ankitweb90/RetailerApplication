GET:
$(document).ready(
		function() {
			
			//GET request
			$("#transactionlist").click(function(event) {
				//prevent the form from submitting it through browser
				event.preventDefault();
				ajaxGet();
			});

		
			function ajaxGet() {

			    $.ajax({
			          url: window.location + "transactions",
			          type: 'GET',
			          success: function(result) {
			     
			        	  $("#getTransactionListDiv ul").empty();
			        	  var custList = "";
			        
			        	  
			        	  $.each(result, function(i, Transaction){
			        		
			        		  var customerName = "Customer Name - " + i + "</br>";
			        		  		//		+ ", totalRewards - " + SummaryTransaction.totalRewards + "</br>";
			        		  
			        		  		$("#getTransactionListDiv .transaction-list").append(customerName) 
			        		  		
			        		  		$.each(Transaction, function (i, val) {

			        		  			var Transaction_Details = "Date : " + i + ", Transaction Amount - " + val + "</br>"
			        		
			        		  			$("#getTransactionListDiv .transaction-list").append(Transaction_Details)
			        		  			
			        		  		});
			        		  		$("#getTransactionListDiv .transaction-list").append("</br>")
			        	  
			    });
				
			}
		})
	}
			
})