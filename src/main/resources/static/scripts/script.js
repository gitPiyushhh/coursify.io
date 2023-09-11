var illustration1 = document.getElementById('illustration1');
var illustration2 = document.getElementById('illustration2');
var form1 = document.getElementById('form1');
var form2 = document.getElementById('form2');
var form3 = document.getElementById('form3');
var form4 = document.getElementById('form4');
var addCourseBtn = document.getElementById('addCourseBtn');
var addStudentBtn = document.getElementById('addStudentBtn');
var deleteCourseBtn = document.getElementById('deleteCourseBtn');
var courseIdVal = document.getElementById('courseIdVal');
var getCourseWithIdCTA = document.getElementById('getCourseWithIdCTA');

var tableAll = document.getElementById("courseTableAll");
var tableOne = document.getElementById("courseTableOne");

var deleteCourseBtnCTA = document.getElementById("deleteCourseBtnCTA");

var deleteCourseBlock = document.getElementById("deleteCourseBlock");
var mainBlock = document.getElementById("mainBlock");
var deleteCourseBtn = document.getElementById("deleteCourseBtn");

var linkIllust = document.getElementById("linkIllust");

addCourseBtn.addEventListener('click', function () { createCourseUI() });
addStudentBtn.addEventListener('click', function () { createUserUI() });

function createCourseUI() {
    mainBlock.classList.remove("remove");
    deleteCourseBlock.classList.add("remove");
    form2.classList.add('hide');
    form3.classList.add('hide');
    form4.classList.add('hide');
    form1.classList.remove('hide');
    illustration2.classList.remove('hide');
    illustration1.classList.add('hide');
}

function createUserUI() {
    mainBlock.classList.remove("remove");
    deleteCourseBlock.classList.add("remove");
    form2.classList.remove('hide');
    form1.classList.add('hide');
    illustration1.classList.remove('hide');
    illustration2.classList.add('hide');
}

function createUserUIFromDiffPage() {
    window.location.href = 'index.html';
    createUserUI();
}

function createCourse() {
    $("#createCourseBtn").click(function () {
        var courseName = $("#courseNameVal").val();
        var formData = {
            "courseName": courseName,
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/courses",
            data: JSON.stringify(formData),
            success: function (response) {
                console.log('Course creation successful.');
                $("#courseNameVal").val('');
                $("#result").text("Course created successfully.");
            },
            error: function (error) {
                $("#result").text("An error occurred while creating the course.");
            }
        });
    });
}

function deleteCourse() {
    let courseIdToDelete = 0;

    deleteCourseBtnCTA.addEventListener('click', function (event) {
        event.preventDefault();
        courseIdToDelete = parseInt($("#courseIdInput").val(), 10);
        deleteCourse(courseIdToDelete);

        $.ajax({
            type: "DELETE",
            url: "/api/courses/" + courseIdToDelete,
            success: function (response) {
                console.log('Course deletion successful.');
                alert("Course deleted successfully..");
            },
            error: function (error) {
                console.error('An error occurred while deleting the course.');

                $("#result").text("An error occurred while deleting the course.");
            }
        });
    });

    deleteCourseBtn.addEventListener("click", function () {
        deleteCourseBlock.classList.remove("remove");
        mainBlock.classList.add("remove");
    })
}

function createUser() {
    $("#createUserBtn").click(function () {
        var firstName = $("#studentFirstNameVal").val();
        var lastName = $("#studentLastNameValue").val();
        var email = $("#studentEmailVal").val();

        var formData = {
            "firstName": firstName,
            "lastName": lastName,
            "email": email
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/students",
            data: JSON.stringify(formData),
            success: function (response) {
                $("#studentFirstNameVal").val('');
                $("#studentLastNameValue").val('');
                $("#studentEmailVal").val('');
                $("#result").text("Course created successfully.");
                firstName = '';

            },
            error: function (error) {
                $("#result").text("An error occurred while creating the course.");
            }
        });
    });
}


$(document).ready(function () {
    loadCourses();
});


$(document).ready(function () {
    createCourse();
    createUser();
    deleteCourse();

    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has("executeFunction") && urlParams.get("executeFunction") === "true") {
        createUserUI();
    }
});

