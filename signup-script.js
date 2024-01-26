function validateForm() {
    var fullname = document.getElementById('fullname').value.trim();
    var email = document.getElementById('email').value.trim();
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;

    // Checking if all fields are filled
    if (!fullname || !email || !password || !confirmPassword) {
        alert('Please you must fill in all fields.');
        return false;
    }

    // Validating the full name for letters and spaces only
    if (!/^[a-zA-Z\s]+$/.test(fullname)) {
        alert('Full name must contain only letters and spaces.');
        return false;
    }

    // Validating the email format
    if (!/\S+@\S+\.\S+/.test(email)) {
        alert('Please enter a valid email address.');
        return false;
    }


    // Checking for password length
    if (password.length < 5) {
        alert('Password must be at least 5 characters long.');
        return false;
    }

    // Checking if passwords match
    if (password !== confirmPassword) {
        alert('Passwords do not match.');
        return false;
    }

    // All validations passed, showing success message
    alert('Signup successful!');



 return true;

}
