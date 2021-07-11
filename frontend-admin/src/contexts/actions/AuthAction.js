import { LOGIN, LOGOUT } from "./../action-types/AuthType";
import AuthService from "../../services/AuthService";

export const AuthLogin = (data) => async (dispatch) => {
  try {
    const res = await AuthService.login(data);

    dispatch({
      type: LOGIN,
      payload: res !== undefined ? res.data : null,
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
