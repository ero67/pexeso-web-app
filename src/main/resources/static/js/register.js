document.getElementById('register-form').addEventListener('submit', async (event) => {
  event.preventDefault();
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  const user = {
    username: username,
    password: password,
    registeredAt: new Date().toISOString(),
  };

  try {
    const response = await fetch('/api/user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(user),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    alert('User registered successfully!');
    window.location.href = '/login'; // Redirect to the login page
  } catch (error) {
    console.error('Failed to register user:', error);
    alert('Error: Could not register user.');
  }
});
 