<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<link rel="stylesheet" href="/EmployeeReimbursementApp/css/employee.css" />
<link rel="stylesheet"
	href="/EmployeeReimbursementApp/css/reimburse.css" />
<title>Employee Reimbursement</title>
</head>
<body>
	<nav id="nav" class="navbar navbar-expand-lg navbar-light bg-light"></nav>
	<form class="add-form">
		<input type="text" class="add-form-input" id="amount"
			placeholder="Enter amount to start new expense" autocomplete="off">
		<div id="new-req-form" class="hide-add">
			<textarea placeholder="Reason for expense" id="reason"></textarea>
			<button class="submit add-submit" type="submit">Submit</button>
		</div>
	</form>
	<div class="container-fluid">
		<div class="row h-100 justify-content-center">
			<div class="left-card col-sm-6">
				<div class="card border-0">
					<div class="card-body">
						<h5 class="card-title text-center">${firstName} ${lastName}</h5>
						<p class="card-text text-center">Id: ${id}</p>
						<p class="card-text text-center">Total Reimbursed: $320</p>
						<p class="card-text text-center">Date of Birth: 03/24/1993</p>
					</div>
				</div>
			</div>
			<!-- add ability to filter thru all/pending/done -->
			<div class="right-card col-sm-6">
				<table id="requests" value="${id}" class="table borderless loading">
					
				</table>
			</div>
		</div>
	</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="/EmployeeReimbursementApp/js/navbar.js"></script>
  <script src="/EmployeeReimbursementApp/js/table-loading.js"></script>
</html>
