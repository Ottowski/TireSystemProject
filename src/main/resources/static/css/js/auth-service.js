
function saveToken(token) {
  localStorage.setItem('jwtToken', token);
}

function getToken() {
  return localStorage.getItem('jwtToken');
}

function clearToken() {
  localStorage.removeItem('jwtToken');
}

function isLoggedIn() {
  const token = getToken();
  return token != null;
}

function logoutUser() {
  clearToken();
}

function setupAxiosInterceptors() {
  axios.interceptors.request.use((config) => {
    const token = getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });
}
