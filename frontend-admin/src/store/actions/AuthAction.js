import AuthService from "../../services/AuthService";
import { login, logout } from "../reducers/AuthReducer";

export const AuthLogin = (data) => async (dispatch) => {
  try {
    const res = await AuthService.login(data);
    dispatch(login(res !== undefined ? res.data : { error: 401 }));
  } catch (error) {
    console.error(error);
  }
};

export const AuthLogout = () => (dispatch) => {
  AuthService.logout();
  dispatch(logout());
};
