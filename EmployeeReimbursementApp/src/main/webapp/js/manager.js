function getAllPendingRequests() {
    let requestData = "";
    fetch('http://localhost:8080/EmployeeReimbursementApp/api/get/all-requests')
      .then(res => res.json())
      .then(data => {
        if(data.length) {
          data.forEach(r => {
            requestData += `
            <tbody>
              <tr>
                <td>${r.rId}</td>
                <td>${r.userId}</td
                <td>${r.amount}</td>
                <td>${r.reason}</td>
                <td>${r.pending}</td>
                <td>${r.approved}</td>
                <td class="material-icons">
                    <span id="approve">check_circle_outline</span> 
                    <span id="decline">highlight_off</span>
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
                <th scope="col">User Id</th
                <th scope="col">Expense</th>
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

$('#requests').on('click','#approve',function() {
    
    $('.modal-content').empty();
    console.log('clicked')
    $('.modal-content').append(`
    <h1>Approve Reimbursement</h1>
    <p>Are you sure you want to approve the reimbursement?</p>

    <div class="clearfix">
    <button type="button" class="modal-approve">Yes</button>
    <button type="button" class="modal-decline">No</button>
    </div>`)
    $('#confirmation-modal').css('display', 'block');
})

$('#requests').on('click','#decline',function() {
    console.log('clicked2')
    $('.modal-content').empty();
    $('.modal-content').append(`
    <h1>Decline Reimbursement</h1>
    <p>Are you sure you want to decline the reimbursement?</p>

    <div class="clearfix">
      <button type="button" class="modal-approve">Yes</button>
      <button type="button" id="testb" class="modal-decline">No</button>
    </div>`)
    $('#confirmation-modal').css('display', 'block');
})


var modal = document.getElementById('confirmation-modal');

$('.modal-content').on('click', '.modal-approve' ,function() {
    console.log('approved');
    modal.style.display = "none";
})

$('.modal-content').on('click', '.modal-decline' ,function() {
    console.log('declined');
    modal.style.display = "none";
})

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

$(document).ready(function () {
    getAllPendingRequests();
})