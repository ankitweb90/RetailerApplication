$(document).ready(
		function() {
			
			//SUBMIT form
			$("#Transaction").submit(function(event) {
				//prevent the form from submitting it through browser
				event.preventDefault();
				ajaxPost();
			});

		
			function ajaxPost() {
				
				//prepare form data
				var formData = {
						
						customerName : $("#customerName").val(),
						transactionAmount : $("#transactionAmount").val(),
						transactionDate : $("#transactionDate").val()
				
				}
			
			    $.ajax({
			          url: window.location + "Transaction",
			          type: 'POST',
			          data: JSON.stringify(formData),
			          contentType: 'application/json',
			          dataType : 'json',
			          success: function(result) {
			        
			        	  	return alert("Transaction Successfull")

			        	  console.log(result);
			          },
			          error : function(e){
			
			        	  return alert("Error!")
			       
			          }
			    });
				
			}
		}
)