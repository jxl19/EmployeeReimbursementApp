function updateInfo() {
    const id = $('#update-firstname').attr('data-id') || null,
          firstName = $('#update-firstname').val() || null,
          lastName = $('#update-lastname').val() || null,
          email = $('#update-email').val() || null,
          password = $('#update-password').val() || null;
    const updateInfoData = {
        firstName : firstName,
        lastName : lastName,
        email : email,
        password : password
    }
    
    console.log({id, updateInfoData});
    fetch(`http://localhost:8080/EmployeeReimbursementApp/api/post/update-user/${id}`, {
        method:'put',
        body: JSON.stringify(updateInfoData),
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }})
        .then(res => res.json())
        .then(data => {
            console.log(data);
            if (data) {
                $('#updated').append(`<h3 style="color:#4CAF50">Successfully Updated</h3>`);
            } else {
                $('#updated').append(`<h1 style="color:#fb5347c4">There was an error with update</h1>`);
            }
        })
}


$('#updateinfo-button').on('click' ,function(e) {
    e.preventDefault();
    console.log('clicked');
    updateInfo();
})