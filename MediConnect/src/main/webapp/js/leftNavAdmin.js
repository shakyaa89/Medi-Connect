const dropdownBtns = document.querySelectorAll(".dropdown-btn");
dropdownBtns.forEach((btn) => {
	btn.addEventListener("click", () => {
    const content = btn.nextElementSibling;
	content.style.display =
	content.style.display === "block" ? "none" : "block";
	});
});
