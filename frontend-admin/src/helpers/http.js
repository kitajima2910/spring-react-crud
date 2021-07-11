import axios from "axios";
import history from "./history";

export const authHeader = () => {
  const getUser = JSON.parse(localStorage.getItem("user"));
  const jsonHeader = { "Content-type": "application/json" };

  if (getUser && getUser.token) {
    return {
      ...jsonHeader,
      Authorization: "Bearer " + getUser.token,
    };
  } else {
    return jsonHeader;
  }
};

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: authHeader(),
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
