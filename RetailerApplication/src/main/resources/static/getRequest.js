GET:
$(document).ready(
		function() {
			
			//GET request
			$("#rewardslist").click(function(event) {
				//prevent the form from submitting it through browser
				event.preventDefault();
				ajaxGet();
			});


			function ajaxGet() {

			    $.ajax({
			          url: window.location + "rewardslist",
			          type: 'GET',
			          success: function(result) {
			     
			        	  $("#getRewardListDiv ul").empty();
			        	  var custList = "";
			        
			        	  
			        	  $.each(result, function(i, SummaryTransaction){
			        		
			        		  var customer = "<b>" + "Customer Name - " + "</b>" + SummaryTransaction.customerName
			        		  				  + "<b> </br>" + " TotalRewards - " + "</b>" + SummaryTransaction.totalRewards  + "</br>";
			        		  
			        		  		$("#getRewardListDiv .reward-list").append(customer) 
			        		  		
			        		  		$.each(SummaryTransaction.rewardsPerMonth, function (i, val) {

			        		  			var Monthly_Rewards = "<b>" + "Month : " + "</b>" + i + ", " + "<b>" + " Points - " + "</b>" + + val + "</br>"
			        		
			        		  			$("#getRewardListDiv .reward-list").append(Monthly_Rewards)
			        		  			
			        		  		});
			        		  		$("#getRewardListDiv .reward-list").append("</br>")
			        	  
			    });
				
			}
		})
	}
			
})