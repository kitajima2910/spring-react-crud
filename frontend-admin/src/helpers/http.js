import axios from "axios";

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

export default axios.create({
  baseURL: "http://localhost:8080/api",
  headers: authHeader(),
});
