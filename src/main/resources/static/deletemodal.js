"use strict"
const modalCancelBtn=document.querySelector(".modal-cancel");
const deleteBtns=document.querySelectorAll(".delete-btn");
const modalOverlay=document.querySelector(".modal-overlay");

deleteBtns.forEach((btn)=>{
	
	btn.addEventListener("click",()=>{
	
	modalOverlay.classList.add("present");
	
  });
});

modalCancelBtn.addEventListener("click",()=>{
	
　window.location.href="/student/index";	
 
});