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
        <a class="nav-link" href="">Expense Request</a>
      </li>
    <li class="nav-item">
        <a class="nav-link" href="">Settings</a>
    </li>
    </ul>
    <div>
  <a href="logout">Logout</a>
  </div>
</div>`;

document.getElementById("nav").innerHTML = x;
let employeeId = $('#requests').attr('value');
let formOpened = false;
$('.add-form-input').on('click', function () {
    $('#new-req-form').removeClass('hide-add');
    formOpened = true;
})

$(window).on('click', function () {
    let focused = $('#new-req-form').children().is(':focus') || $('#new-req-form').children().children().is(':focus');
    let openForm = $('.add-form-input').is(':focus');
    if(formOpened){
        if(focused || openForm) {
            return;
        }
        else{
            $('#new-req-form').addClass('hide-add');
            formOpened = false;
        }
    }
})

function loadRequestData(employeeId) {
  let requestData = "";
  fetch('http://localhost:8080/EmployeeReimbursementApp/api/get/employee/' + employeeId)
    .then(res => res.json())
    .then(data => {
      if(data.length) {
        $("#requests").append(
        `<thead id="table-head">
          <tr>
            <th scope="col">Request Id</th>
            <th scope="col">Expense</th>
            <th scope="col">Reason</th>
            <th scope="col">Pending</th>
            <th scope="col">Approved</th>
          </tr>
        </thead>`
        )
        data.forEach(r => {
          requestData += `
          <tbody>
            <tr>
              <th scope="row">${r.rId}</th>
              <td>${r.amount}</td>
              <td>${r.reason}</td>
              <td>${r.pending}</td>
              <td>${r.approved}</td>
            </tr>
          `
        })
        requestData+= `</tbody></table>`;
        $("#requests").append(requestData);
      } else {
        $("#requests").append(`<p id="zeroreqs" style="text-align: center">You have no expense requests`)
      }
    })
}

function postNewRequest() {
  let amount = $('#amount').val(),
      reason = $('#reason').val();

  const newPost = {
    rId: employeeId,
    amount: amount,
    reason: reason
  }

  fetch('http://localhost:8080/EmployeeReimbursementApp/api/post/createrequest', {
    method:'post',
    body: JSON.stringify(newPost),
    headers: {
      'Content-Type': 'application/json',
      'Accept' : 'application/json'
    }
  }).then(res => res.json())
  .then(data => {
    if($("#table-head").is(":hidden")) $("#table-head").show();
    ($("#zeroreqs").remove());
    $('#requests').append(`          
    <tbody>
    <tr>
      <th scope="row">${data.rId}</th>
      <td>${data.amount}</td>
      <td>${data.reason}</td>
      <td>${data.pending}</td>
      <td>${data.approved}</td>
    </tr>`)
  })
}

$('.add-form').submit(function (e) {
  e.preventDefault();
  postNewRequest();
})

$(document).ready(function () { 
  $("#table-head").hide();
  loadRequestData(employeeId);
})