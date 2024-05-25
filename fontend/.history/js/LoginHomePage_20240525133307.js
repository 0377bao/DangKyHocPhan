function login() {
    const username = document.getElementById('masv').value.trim();
    const password = document.getElementById('password').value.trim();
    const masvError = document.getElementById('masvError');
    const passwordError = document.getElementById('passwordError');
    
    // Clear previous errors
    masvError.textContent = '';
    passwordError.textContent = '';
    
    // Validate empty fields
    if (!username) {
        showError(masvError, 'Mã số sinh viên không được để trống');
        return;
    }
    if (!password) {
        showError(passwordError, 'Mật khẩu không được để trống');
        return;
    }

    const url = `http://localhost:8080/api/user/login/accounts?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            if (data.status === "Success") {
                console.log(data);
                // Store the user ID in localStorage
                localStorage.setItem('userId', username);
                window.location.href = "HomePage.html";
            } else {
                if (data.message.includes("password")) {
                    showError(passwordError, 'Mật khẩu không đúng');
                } else if (data.message.includes("username")) {
                    showError(masvError, 'Mã số sinh viên không đúng');
                } else {
                    showError(masvError, 'Login failed: ' + (data.message || 'Unknown error'));
                }
            }
        })
        .catch(error => {
            showError(masvError, 'Login error: ' + error.message);
        });
}

function showError(element, message) {
    element.innerHTML = `${message} <span class="error-dot">!</span>`;
    setTimeout(() => {
        element.textContent = '';
    }, 5000);
}