let x = `<a class="navbar-brand" href="#">Employee Reimbursement</a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
  <span class="navbar-toggler-icon"></span>
</button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
  <ul class="navbar-nav mr-auto justify-content-center">
    <li class="nav-item active">
      <a class="nav-link" href="/EmployeeReimbursementApp/homepage">Home <span class="sr-only">(current)</span></a>
    </li>
    <li class="nav-item">
        <a href="/EmployeeReimbursementApp/updateinfo" class="nav-link" href="">Settings</a>
    </li>
    </ul>
    <div>
  <a href="/EmployeeReimbursementApp/logout">Logout</a>
  </div>
</div>`;

document.getElementById("nav").innerHTML = x;