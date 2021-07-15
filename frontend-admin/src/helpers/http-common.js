import axios from "axios";
import history from "./history";
import queryString from "query-string";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: { "Content-type": "application/json" },
  paramsSerializer: (params) => queryString.stringify(params),
});

axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response.status === 401) {
      localStorage.removeItem("user");
      history.push("/login");
      return;
    }
    if (error.response.status === 403) {
      throw error;
    }
  }
);

export default axiosInstance;
