<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
    rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous"
  	/>
    <link rel="stylesheet" href="/EmployeeReimbursementApp/css/update-info.css">
    <title>Edit Information</title>
</head>
<body>
    <nav id="nav" class="navbar navbar-expand-lg navbar-light bg-light"></nav>
    <div class="top"></div>
    <div class="container">
        <div class="card border-0 text-center">
            <div class="card-body">
              <h5 class="card-title">Edit information</h5>
              <p class="card-text">Edit the fields you want to update</p>
              <form id="edit-info">
                <div class="form-group">
                    <input type="text" data-id="${id}"class="form-control update-field" id="update-firstname" name="firstname" placeholder="Change First Name" value="${firstName}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control update-field" id="update-lastname" name="lastname" placeholder="Change First Name" value="${lastName}">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control update-field" id="update-email" name="email" placeholder="Change Email" value="${email}">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control update-field" id="update-password" name="password" placeholder="Change Password">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control update-field" id="confirm-password" name="confirmpassword" placeholder="Confirm New Password">
                </div>
                <button type="submit" class="btn btn-primary" id="updateinfo-button">Update</button>
              </form>
              <div id="updated"></div>
            </div>
          </div>
        </div>
</body>
<script
src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
crossorigin="anonymous"
></script>
<script
src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
crossorigin="anonymous"
></script>
<script
src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
crossorigin="anonymous"
></script>
<script src="/EmployeeReimbursementApp/js/navbar.js"></script>
<script src="/EmployeeReimbursementApp/js/update-info.js"></script>
</html>
