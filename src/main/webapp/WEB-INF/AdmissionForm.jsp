<html>
	<head>AdmissionForm</head>
	<body>
		<h1>Congratulations!!</h1>
		<h2>${msg}</h2>
		<a href="/SpringProject/admissionForm.html?sitelanguage=en" >English</a>
		<form action="/SpringProject/submitAdmissionForm.html" method="post">
			<p>Student's Name: <input type="text" name="studentName" /></p>
			<p>Student's Hobby: <input type="text" name="studentHobby" /></p>
			<input type="submit" value="Submit this form by clicking here" />
		</form>
	</body>
</html>