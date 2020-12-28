var addModal = document.getElementById("add-modal");
var editModal = document.getElementById("edit-modal");

function openAddModal() {
	addModal.style.display = "block";
}

function openEditModal() {
	editModal.style.display = "block";
}

function closeModal() {
	addModal.style.display = "none";
	editModal.style.display = "none";
}

function handleInput(val) {
	var x = document.getElementById("edit-modal-approvedBy");
	console.log(val);
	if(val.value <= 10000)
		x.value = "David Lee";
	else if(val.value > 10000 && val.value <= 50000)
		x.value = "Laura Smith";
	else
		x.value = "Matthew Vance";
}

function validateForm() {
	var x = document.forms["myForm"]["orderID"].value;
	if (x == ""|| x == null || x == "0") {
	   alert("Order Id must be filled out");
	   return false;
	}
}