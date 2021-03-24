let x = `<a class="navbar-brand" href="#">Employee Reimbursement</a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
  <span class="navbar-toggler-icon"></span>
</button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
  <ul class="navbar-nav mr-auto justify-content-center">
    <li class="nav-item active">
      <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="">Settings</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input id="query" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
    </form>
    <div>
  <a class="" href="">Logout</a>
  </div>
</div>`;
document.getElementById("nav").innerHTML = x;

function search() {
  let q = document.getElementById("query").value;
  alert(q);
}

let query = document.getElementById("query");
query.addEventListener("keydown", (e) => { 
  if (e.key === 'Enter') {
    e.preventDefault();
    search();
  }
})