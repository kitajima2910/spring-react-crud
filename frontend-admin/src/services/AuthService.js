import http from "../helpers/http-common";

class AuthService {
  login = (data) => {
    return http.post("/auth/signin", data);
  };

  register = (data) => {
    return http.post("/auth/signup", data);
  };

  logout = () => {
    localStorage.removeItem("user");
  };
}

export default new AuthService();
