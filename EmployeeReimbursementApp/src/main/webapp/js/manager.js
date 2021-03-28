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
              </tr>
            `
          })
          requestData+= `</tbody></table>`;
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
              </tr>
            </thead>`
            )
          $("#requests").append(requestData);
        } else {
          $("#requests").empty();
          $("#requests").append(`<p id="zeroreqs" style="text-align: center">There are no requests</p>`)
        }
      })
}


$(document).ready(function () {
    getAllPendingRequests();
})