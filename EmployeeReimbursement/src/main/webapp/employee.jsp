<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="style.css" />
    <title>Employee Reimbursement</title>
  </head>
  <body>
    <nav id="nav" class="navbar navbar-expand-lg navbar-light bg-light"></nav>
    <div class="container-fluid vh-100">
        <!-- we want to show only first 10? -->
            <div class="row h-100 justify-content-center align-items-center">
                    <div class="col-sm-6">
                      <div class="card border-0">
                        <div class="card-body">
                          <h5 class="card-title text-center"><%=request.getParameter("username") %></h5>
                          <p class="card-text text-center">Id: 5</p>
                          <p class="card-text text-center">Total Reimbursed: $320</p>
                        </div>
                      </div>
                    </div>
                    <div class="col-sm-6">
                        <table class="table borderless">
                            <thead">
                              <tr>
                                <th scope="col">#</th>
                                <th scope="col">Expense</th>
                                <th scope="col">Pending</th>
                                <th scope="col">Approved</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <th scope="row">1</th>
                                <td>$100</td>
                                <td>Done</td>
                                <td>Approved</td>
                              </tr>
                              <tr>
                                <th scope="row">2</th>
                                <td>$200</td>
                                <td>Pending</td>
                                <td></td>
                              </tr>
                              <tr>
                                <th scope="row">3</th>
                                <td>$150</td>
                                <td>Done</td>
                                <td>Denied</td>
                              </tr>
                              <tr>
                                <th scope="row">4</th>
                                <td>$150</td>
                                <td>Done</td>
                                <td>Denied</td>
                              </tr>
                              <tr>
                                <th scope="row">5</th>
                                <td>$150</td>
                                <td>Done</td>
                                <td>Denied</td>
                              </tr>
                              <tr>
                                <th scope="row">6</th>
                                <td>$150</td>
                                <td>Done</td>
                                <td>Denied</td>
                              </tr>
                              <tr>
                                <th scope="row">7</th>
                                <td>$150</td>
                                <td>Done</td>
                                <td>Denied</td>
                              </tr>
                              <tr>
                                <th scope="row">8</th>
                                <td>$150</td>
                                <td>Done</td>
                                <td>Denied</td>
                              </tr>
                            </tbody>
                          </table>
                      <!-- <div class="card border-0">
                        <div class="card-body">
                          <h5 class="card-title text-center">Table of Pending Applications</h5>
                          <p class="card-text"></p>
                      </div> -->
                    </div>
                  </div>
          </div>
    </div>
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
  <script src="navbar.js"></script>
</html>
