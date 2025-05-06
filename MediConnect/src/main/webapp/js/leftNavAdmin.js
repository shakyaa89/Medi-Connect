const dropdownBtns = document.querySelectorAll(".dropdown-btn");
dropdownBtns.forEach((btn) => {
	btn.addEventListener("click", () => {
    const content = btn.nextElementSibling;
	content.style.display =
	content.style.display === "block" ? "none" : "block";
	});
});

function clearProfileFields(){
	const form = document.getElementById("editProfileForm");
	form.querySelectorAll("input, select").forEach(field => {
	            if (field.type !== "submit" && field.type !== "button") {
	                field.value = "";
	            }
	        });
}

function clearProfileFieldsDoctor() {
    const form = document.getElementById("doctorEditForm");
    form.querySelectorAll("input, select").forEach(field => {
        if (field.type !== "submit" && field.type !== "button") {
            if (field.type === "checkbox" || field.type === "radio") {
                field.checked = false;  // Uncheck checkboxes and radio buttons
            } else {
                field.value = "";  // Clear text-based input fields and selects
            }
        }
    });
}
