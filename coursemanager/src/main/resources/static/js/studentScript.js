function goToStudentHome() {
	window.location.href = "/student/home";
}
function filterBySemesterStudent(event) {
	// console.log(event.target.value);
	window.location.href = "/student/semester/" + event.target.value;
}
function addCourseStudent() {
	window.location.href = "/student/add/course";
}