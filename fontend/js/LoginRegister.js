function login() {
    const username = document.getElementById('masv').value;
    const password = document.getElementById('password').value;
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
                window.location.href = "Home.html";
            } else {
                alert('Login failed: ' + (data.message || 'Unknown error'));
            }
        })
        .catch(error => {
            alert('Login error: ' + error.message);
        });
}