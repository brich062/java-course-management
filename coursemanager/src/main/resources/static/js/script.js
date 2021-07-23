function goToAdminHome() {
	window.location.href = "/admin/home";
}

function filterBySemester(event) {
	// console.log(event.target.value);
	window.location.href = "/admin/semester/" + event.target.value;
}

function createCourse() {
	window.location.href = "/admin/courses/add";
}

function editCourse(id) {
	window.location.href = "/admin/courses/" + id + "/edit";
}

function deleteCourse(id) {
	window.location.href = "/admin/courses/" + id + "/delete";
}
