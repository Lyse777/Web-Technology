function validateForm() {
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    // Checking if all fields are filled
    if (email == "" || password == "") {
        alert("Please you must fill in all fields.");
        return false;
    }

    // Validating email format
    if (!/\S+@\S+\.\S+/.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }

     // Checking for password length
    if (password.length < 5) {
        alert('Your Password should be Exactly 5 Characters long.');
        return false;
    }

    // when  all validations are passed
    alert('Login successful!');

       window.location.href = 'dashboard.html';
        return false; 
}

