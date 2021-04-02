function getRequests(url) {
    let requestData = "";
    fetch(url)
      .then(res => res.json())
      .then(data => {
        if(data.length) {
          data.forEach(r => {
            requestData += `
            <tbody name=${r.rId}>
              <tr>
                <td>${r.rId}</td>
                <td>${r.userId}</td>
                <td>${r.amount}</td>
                <td>${r.reason}</td>
                <td>${r.pending}</td>
                <td>${r.approved}</td>
                <td class="material-icons">
                    <span id="approve" data-id=${r.rId}>check_circle_outline</span> 
                    <span id="decline" data-id=${r.rId}>highlight_off</span>
                </td>
              </tr>
            `
          })
          requestData+= `</tbody></table>`;
          $("#requests").removeClass("loading");
          $("#requests").empty();
          $("#requests").append(
            `<thead id="table-head">
              <tr>
                <th scope="col">Request Id</th>
                <th scope="col">Employee Id</th>
                <th scope="col">Expenses</th>
                <th scope="col">Reason</th>
                <th scope="col">Pending</th>
                <th scope="col">Approved</th>
                <th scope="col"></th>
              </tr>
            </thead>`
            )
          $("#requests").append(requestData);
        } else {
          $("#requests").removeClass("loading");
          $("#requests").empty();
          $("#requests").append(`<p id="zeroreqs" style="text-align: center">There are no requests</p>`)
        }
      })
}

// modal to check if approve
$('#requests').on('click','#approve',function() {
    const id = $(this).attr('data-id');
    $('.modal-content').empty();
    $('.modal-content').append(`
    <h1>Approve Reimbursement</h1>
    <p>Are you sure you want to approve the reimbursement?</p>

    <div class="clearfix">
        <button type="button" class="modal-approve approve-req" data-id=${id} data-bool="true">Yes</button>
        <button type="button" class="modal-decline">No</button>
    </div>`)
    $('#confirmation-modal').css('display', 'block');
})
// modal to check if decline
$('#requests').on('click','#decline',function() {
    const id = $(this).attr('data-id');
    $('.modal-content').empty();
    $('.modal-content').append(`
    <h1>Decline Reimbursement</h1>
    <p>Are you sure you want to decline the reimbursement?</p>

    <div class="clearfix">
      <button type="button" class="modal-approve decline-req" data-id=${id} data-bool="false">Yes</button>
      <button type="button" id="testb" class="modal-decline">No</button>
    </div>`)
    $('#confirmation-modal').css('display', 'block');
})


var modal = document.getElementById('confirmation-modal');

// approve/deny request
$('.modal-content').on('click', '.modal-approve', function() {
    const id = $(this).attr('data-id');
    const approved = $(this).attr('data-bool');
    const updateRequest = {
        rId: id,
        approved: approved
    }
    modal.style.display = "none";
    fetch('http://localhost:8080/EmployeeReimbursementApp/api/post/review-request', {
    method:'post',
    body: JSON.stringify(updateRequest),
    headers: {
      'Content-Type': 'application/json',
      'Accept' : 'application/json'
    }})
    .then(res => res.json()) 
    .then(data => {
      if (data) {
        $(`tbody[name=${id}]`).remove();
        $(`td[name=${id}pending]`).empty().append("false")
        $(`td[name=${id}approved]`).empty().append(approved);
      }
    });
})

//close confirmation modal
$('.modal-content').on('click', '.modal-decline' ,function() {
    modal.style.display = "none";
})

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

$('#search-button').on('click', function(e) {
  e.preventDefault();
  let requestData = "";
  const id = $('#search-emp').val();
  fetch('http://localhost:8080/EmployeeReimbursementApp/api/get/employee/' + id)
  .then(res => res.json())
  .then(data => {
    if(data.length) {
      data.forEach(r => {
        requestData += `
        <tbody>
          <tr>
          <td>${r.rId}</td>
          <td>${id}</td>
          <td>${r.amount}</td>
          <td>${r.reason}</td>
          <td name="${r.rId}pending">${r.pending}</td>
          <td name="${r.rId}approved">${r.approved}</td>
          <td class="material-icons">
              <span id="approve" data-id=${r.rId}>check_circle_outline</span> 
              <span id="decline" data-id=${r.rId}>highlight_off</span>
          </td>
          </tr>
        `
      })
      requestData+= `</tbody></table>`;
      $("#requests").empty();
      $("#requests").append(
        `<thead id="table-head">
          <tr>
          <th scope="col">Request Id</th>
          <th scope="col">Employee Id</th>
          <th scope="col">Expenses</th>
          <th scope="col">Reason</th>
          <th scope="col">Pending</th>
          <th scope="col">Approved</th>
          <th scope="col"></th>
          </tr>
        </thead>`
        )
      $("#requests").append(requestData);
    } else {
      $("#requests").empty();
      $("#requests").append(`<p id="zeroreqs" style="text-align: center">This user has nothing`)
    }
  })
})

$('#all-employees').on('click', function() {
  let requestData = "";
  fetch('http://localhost:8080/EmployeeReimbursementApp/api/get/all-employees')
  .then(res => res.json())
  .then(data => {
    if (data.length) {
      data.forEach(emp => {
        requestData += `
        <tbody>
          <tr>
          <td>${emp.loginId}</td>
          <td>${emp.firstName}</td>
          <td>${emp.lastName}</td>
          <td>${emp.hireDate}</td>
          <td>${emp.birthDate}</td>
          <td>${emp.email}</td>
          </tr>
        `
      })
      requestData+= `</tbody></table>`;
      $("#requests").empty();
      $("#requests").append(
        `<thead id="table-head">
          <tr>
          <th scope="col">Login Id</th>
          <th scope="col">First Name</th>
          <th scope="col">Last Name</th>
          <th scope="col">Hire Date</th>
          <th scope="col">Birth Date</th>
          <th scope="col">Email</th>
          </tr>
        </thead>`
        )
      $("#requests").append(requestData);
    }
  })
})

$('#pending-transactions').on('click', function() {
  getRequests('http://localhost:8080/EmployeeReimbursementApp/api/get/all-pending-requests');
})

$('#resolved-transactions').on('click', function() {
  getRequests('http://localhost:8080/EmployeeReimbursementApp/api/get/all-completed-requests');
})

$('#all-transactions').on('click', function() {
  getRequests('http://localhost:8080/EmployeeReimbursementApp/api/get/all-requests');
})

$(document).ready(function () {
  //pending reqs
  getRequests('http://localhost:8080/EmployeeReimbursementApp/api/get/all-pending-requests');
})