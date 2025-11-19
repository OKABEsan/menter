"use strict"
const modalCancelBtn=document.querySelector(".modal-cancel");
const deleteBtns=document.querySelectorAll(".delete-btn");
const modalOverlay=document.querySelector(".modal-overlay");
const modalUserId=document.getElementById("modalUserId");

deleteBtns.forEach((btn)=>{
	
	btn.addEventListener("click",()=>{
	const id=btn.getAttribute("data-id");
	modalUserId.value=id;
	modalOverlay.classList.add("present");
	
  });
});

modalCancelBtn.addEventListener("click",()=>{
	
　modalOverlay.classList.remove("present");
 
});