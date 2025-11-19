"use strict"
const modalCancelBtn=document.querySelector(".modal-cancel");
const deleteBtns=document.querySelectorAll(".delete-btn");
const modalOverlay=document.querySelector(".modal-overlay");
const deleteUserId=document.getElementById("deleteUserId");

deleteBtns.forEach((btn)=>{
	
	btn.addEventListener("click",()=>{
	const id=btn.getAttribute("delete-id");
	deleteUserId.value=id;
	modalOverlay.classList.add("present");
	
  });
});

modalCancelBtn.addEventListener("click",()=>{
	
　window.location.href="/student/index";	
 
});