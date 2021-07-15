import http from "../helpers/http-common";
import { authHeader } from "./../helpers/auth-header";

class UserService {
  list = (query) => {
    return http.get("/user/list", { params: query, headers: authHeader() });
  };
}

export default new UserService();
