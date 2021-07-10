import { LOGIN, LOGOUT } from "./../action-types/AuthType";
import AuthService from "../../services/AuthService";

export const AuthLogin = (data) => async (dispatch) => {
  try {
    const res = await AuthService.login(data);

    localStorage.setItem("user", JSON.stringify(res.data));

    dispatch({
      type: LOGIN,
      payload: res.data,
    });
  } catch (error) {
    console.error(error);
  }
};

export const AuthLogout = () => (dispatch) => {
  AuthService.logout();

  dispatch({
    type: LOGOUT,
  });
};
