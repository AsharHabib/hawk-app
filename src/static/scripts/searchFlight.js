const oneRadio = document.getElementById("inlineRadio1");
const roundRadio = document.getElementById("inlineRadio2");
const returnDate = document.getElementById("returnDate");

oneRadio.addEventListener("click", function () {
  returnDate.classList.add("invisible");
});

roundRadio.addEventListener("click", function () {
  returnDate.classList.remove("invisible");
});

// ECONOMY, PREMIUM_ECONOMY, BUSINESS CLASS, FIRST CLASS

// Ashar's Code
