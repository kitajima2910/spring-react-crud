export const authHeader = () => {
  const getUser = JSON.parse(localStorage.getItem("user"));

  if (getUser && getUser.token) {
    return {
      Authorization: "Bearer " + getUser.token,
    };
  } else {
    return {};
  }
};
