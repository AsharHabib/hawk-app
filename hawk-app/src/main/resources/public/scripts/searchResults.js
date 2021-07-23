			/* Ashar's Date Code */
			/*var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1; //January is 0!
            var yyyy = today.getFullYear();
             if(dd<10){
                    dd='0'+dd
                } 
                if(mm<10){
                    mm='0'+mm
                } 

            today = yyyy+'-'+mm+'-'+dd;
            document.getElementById("departureDate").setAttribute("min", today); */
			
			/* Raymond's Code */			
			let form = document.getElementById("form");
			
			let spanFlights = document.createElement("span");
			spanFlights.classList.add("traveller-cards");
			form.appendChild(spanFlights);
			
			let backBtn = document.createElement("button");
			backBtn.setAttribute("id", "backBtn");
			backBtn.setAttribute("type", "button");
			backBtn.classList.add("btn");
			backBtn.classList.add("btn-primary");
			backBtn.classList.add("btn-lg");
			backBtn.innerHTML = "Back";
			backBtn.addEventListener("click", function() {
				history.back();
			});
			
			let nextBtn = document.createElement("button");
			nextBtn.setAttribute("id", "button");
			nextBtn.setAttribute("type", "submit");
			nextBtn.classList.add("btn");
			nextBtn.classList.add("btn-primary");
			nextBtn.classList.add("btn-lg");
			nextBtn.innerHTML = "Next";
			
			form.appendChild(backBtn);
			form.appendChild(nextBtn);
			
			/* Ashar's Code */
			// var form = document.getElementById("form");
			var ol = document.getElementById("ol");
			var flights = null;
			
			//search-results?originLocationCode=IAH&destinationLocationCode=JFK&departureDate=2021-11-01&adults=1&travelClass=ECONOMY&nonStop=true&maxPrice=250
			//search-results?originLocationCode=TPA&destinationLocationCode=LAX&departureDate=2021-07-27&returnDate=&adults=1&nonStop=true&travelClass=ECONOMY&maxPrice=500

			async function search_results(query_string) {
				var request_access = await RequestAccessToken();
				
				var url = "https://test.api.amadeus.com/v2/shopping/flight-offers" + query_string + "&max=10"; //
				var access_token = request_access.access_token;
				var response = await fetch(url, {
					method: 'GET', 
					headers: {
						'Authorization': 'Bearer ' + access_token
					}
				});
				var json = await response.json();
				flights = json.data;
				var urlParams = new URLSearchParams(window.location.search);
				console.log(flights);
				for (let index in flights) {
					/* var li = document.createElement("li");
					var radio = document.createElement("input");
					radio.setAttribute("type", "radio");
					radio.setAttribute("name", "flightSelection");
					radio.setAttribute("value", index);
					
					var itineraries = flights[index].itineraries;
					var grand_total = flights[index].price.grandTotal;
					var h3 = document.createElement("h3");
					h3.innerText = `Grand Total $${grand_total}`;
					li.appendChild(h3);
					for (let itinerary_index in itineraries) {
						var div_itinerary = document.createElement("div");
						var segments = itineraries[itinerary_index].segments;
						var h4_duration = document.createElement("h4");
						var total_duration = itineraries[itinerary_index].duration;
						if (itinerary_index == 0) {
							h4_duration.innerText = `Total duration to the destination: ${total_duration}`;
						} else {
							h4_duration.innerText = `Total duration back to arrival: ${total_duration}`;
						}
						div_itinerary.appendChild(h4_duration);
						var ol_segment = document.createElement("ol");
						for (let segment_index in segments) {
							var li_segment = document.createElement("li");
							var carrier = segments[segment_index].carrierCode;
							var number = segments[segment_index].number;
							var duration = segments[segment_index].duration;
							var departure_time = segments[segment_index].departure.at;
							var departure_iata = segments[segment_index].departure.iataCode;
							var arrival_time = segments[segment_index].arrival.at;
							var arrival_iata = segments[segment_index].arrival.iataCode;
							var h4 = document.createElement("h4");
							h4.innerText = `Carrier information: ${carrier} ${number}`;
							var h5 = document.createElement("h5");
							h5.innerText = `Duration for this flight: ${duration}`;
							var p_departure = document.createElement("p");
							var p_arrival = document.createElement("p");
							p_departure.innerText = `Departing from ${departure_iata} at ${departure_time}`;
							p_arrival.innerText = `Arriving at ${arrival_iata} at ${arrival_time}`;
							li_segment.appendChild(h4);
							li_segment.appendChild(h5);
							li_segment.appendChild(p_departure);
							li_segment.appendChild(p_arrival);
							ol_segment.appendChild(li_segment);
						}
						div_itinerary.appendChild(ol_segment);
						li.appendChild(div_itinerary);
					}
					li.appendChild(radio);
					ol.appendChild(li); */
					
					
					let div1 = document.createElement("div");
					div1.classList.add("card")
					div1.classList.add("text-center")
					div1.classList.add("resultCards")
					
					let div2 = document.createElement("div");
					div2.classList.add("card-header");
					
					let cardHeader = document.createElement("h4");
					cardHeader.classList.add("cardHeader");
					cardHeader.innerHTML = "Flight Details";
					
					let radio = document.createElement("input");
					radio.classList.add("form-check-input");
					radio.classList.add("flightRadio");
					radio.setAttribute("type", "radio");
					radio.setAttribute("type", "radio");
					radio.setAttribute("name", "flightSelection");
					radio.setAttribute("value", index);
					
					cardHeader.appendChild(radio);
					div2.appendChild(cardHeader);
					div1.appendChild(div2);
					
					
					let div3 = document.createElement("div");
					div3.classList.add("card-body");
					
					let grand_total = flights[index].price.grandTotal;
					let itineraries = flights[index].itineraries;
					
					for (let itinerary_index in itineraries) {
						let segments = itineraries[itinerary_index].segments;
						let total_duration = itineraries[itinerary_index].duration;
						let departH4 = document.createElement("h4");
						departH4.classList.add("card-title");
						if (itinerary_index == 0) {
							departH4.innerHTML = "Departing Flight";
							div3.appendChild(departH4);			
						} else {
							departH4.innerHTML = "Returning Flight";
							div3.appendChild(departH4);		
						}
						
						
						for (let segment_index in segments) {
							let carrier = segments[segment_index].carrierCode;
							let number = segments[segment_index].number;
							let duration = segments[segment_index].duration;
							let departure_time = segments[segment_index].departure.at;
							let departure_iata = segments[segment_index].departure.iataCode;
							let arrival_time = segments[segment_index].arrival.at;
							let arrival_iata = segments[segment_index].arrival.iataCode;
							
							let div4 = document.createElement("div");
							div4.classList.add("departingDiv");
							
							let fromIataH5 = document.createElement("h5");
							fromIataH5.classList.add("fromIata");
							fromIataH5.innerHTML = `${departure_iata} `;
							
							let iconDep = document.createElement("i");
							iconDep.classList.add("fas");
							iconDep.classList.add("fa-plane-departure");
							
							fromIataH5.appendChild(iconDep);
							div4.appendChild(fromIataH5);
							
							let hr = document.createElement("hr");
							div4.appendChild(hr);
							
							let ariveIataH5 = document.createElement("h5");
							ariveIataH5.classList.add("ariveIata");
							
							let iconAr = document.createElement("i");
							iconAr.classList.add("fas");
							iconAr.classList.add("fa-plane-arrival");
							ariveIataH5.innerHTML = `${arrival_iata} `;
							ariveIataH5.appendChild(iconAr);
							div4.appendChild(ariveIataH5);
							
							let carrierH6 = document.createElement("h6");
							carrierH6.classList.add("departBody");
							carrierH6.classList.add("carInfo");
							carrierH6.innerHTML = `Carrier Information: ${carrier}${number}`;
							div4.appendChild(carrierH6);
							
							let durationP = document.createElement("p");
							durationP.classList.add("departBody");
							durationP.innerHTML = `Duration of Flight: ${duration}`;
							div4.appendChild(durationP);
							
							let divRow = document.createElement("div");
							divRow.classList.add("row");
							divRow.classList.add("rowIn");
							
							let div5 = document.createElement("div");
							div5.classList.add("col-6");
							div5.classList.add("departInfo");
							
							let departP = document.createElement("p");
							departP.classList.add("departBody");
							departP.innerHTML = `Departing from ${departure_iata} at ${departure_time}`;
							div5.appendChild(departP);
							divRow.appendChild(div5);
							
							let div6 = document.createElement("div");
							div6.classList.add("col-6");
							div6.classList.add("arriveInfo");
							
							let ariveP = document.createElement("p");
							ariveP.classList.add("departBody");
							ariveP.innerHTML = `Arriving at ${arrival_iata} at ${arrival_time}`;
							div6.appendChild(ariveP);
							divRow.appendChild(div6);
							div4.appendChild(divRow);
							div3.appendChild(div4);
							
							let indexCheck = segment_index + 1;
							if(indexCheck <= segments.length) {
								let connH5 = document.createElement("h5");
								connH5.classList.add("conn");
								connH5.innerHTML = "Connecting Flight";
								div3.appendChild(connH5);
							}
						}
					}
					let grandH5 = document.createElement("h5");
						grandH5.classList.add("grandTotal");
						grandH5.innerHTML = `Grand Total: $${grand_total}`;
						div3.appendChild(grandH5);
						div1.appendChild(div3);
						spanFlights.appendChild(div1);	
				}
			}
			
			async function RequestAccessToken() {
				var request_access = await fetch('https://test.api.amadeus.com/v1/security/oauth2/token', {
				    method: 'POST',
				    body: new URLSearchParams({
				        'grant_type': 'client_credentials',
				        'client_id': 'OGORt5hAGLuW5GzewaxGCPFVNrKiB0WV',
				        'client_secret': 'ik6GIT6Y7ui09KcA'
				    })
				});
				var access_json = await request_access.json();
				return access_json;
			}
			
			window.onload = function () {
				var query_string = window.location.search;
				search_results(query_string);
			}
			
			form.onsubmit = function () {
				var value = document.querySelector('input[name="flightSelection"]:checked').value;
				var JSON_string = JSON.stringify(flights[value]);
				document.cookie = `flight=${JSON_string}; SameSite=None; Secure`;
			}