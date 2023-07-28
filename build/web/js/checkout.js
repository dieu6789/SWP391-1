
var products = JSON.parse(localStorage.getItem("products"));
var productsDiv = document.getElementById("products");

for (var i = 0; i < products.length; i++) {
  var product = products[i];

  var productDiv = document.createElement("div");
  var title = document.createElement("input");
  var img = document.createElement("img");
  var price = document.createElement("input");
  var quantity = document.createElement("input");

  title.type = "readonly";
  title.name = "title";
  title.value = product.title;

  img.src = product.imgSrc;

  price.type = "readonly";
  price.name = "price";
  price.value = String(product.price);

  quantity.type = "readonly";
  quantity.name = "quantity";
  quantity.value = String(product.quantity || "");

  productDiv.appendChild(title);
  productDiv.appendChild(img);
  productDiv.appendChild(price);
  productDiv.appendChild(quantity);

  productsDiv.appendChild(productDiv);
}

var subtotal = localStorage.getItem("cartTotal");
document.getElementById('subtotal').value = subtotal + ".000 VND";

const payOnReceiveRadio = document.getElementById("pay-on-receive");
const cardPaymentRadio = document.getElementById("card-payment");
const cardDetailsDiv = document.getElementById("card-details");

// Hide card payment details initially
cardDetailsDiv.style.display = "none";

// Add event listener for payment radio buttons
payOnReceiveRadio.addEventListener("change", () => {
  if (payOnReceiveRadio.checked) {
    cardDetailsDiv.style.display = "none";
  }
});

cardPaymentRadio.addEventListener("change", () => {
  if (cardPaymentRadio.checked) {
    cardDetailsDiv.style.display = "block";
  } else {
    cardDetailsDiv.style.display = "none";
  }
});