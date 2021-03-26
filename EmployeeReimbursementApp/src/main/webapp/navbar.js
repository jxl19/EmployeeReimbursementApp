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

let formOpened = false;
$('.add-form-input').on('click', function () {
    $('#new-req-form').removeClass('hide-add');
    formOpened = true;
})

$(window).on('click', function () {
    console.log('asd')
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
function postNewRequest() {
  console.log('starting post')
  let amount = $('#amount').val(),
      reason = $('#reason').val();
  
  const newPost = {
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
  }).then(function(res) {
    return res.json();
  }).then(function(data) {
    console.log(data);
    $('#x').append(`<p>${data.amount}</p><p>${data.reason}</p>`)
  })
}

$('.add-form').submit(function (e) {
  e.preventDefault();
  postNewRequest();
})