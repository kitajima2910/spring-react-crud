import axios from "axios";

const user = JSON.parse(localStorage.getItem("user"));

const jsonHeader = { "Content-type": "application/json" };

const authHeader = () => {
  if (user && user.accessToken) {
    return {
      Authorization: "Bearer " + user.accessToken,
    };
  } else {
    return jsonHeader;
  }
};

export default axios.create({
  baseURL: "http://localhost:8080",
  headers: { ...jsonHeader, authHeader },
});
