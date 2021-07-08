import { LOGIN, LOGOUT } from "./../action-types/AuthType";

export const AuthLogin = (data) => (dispatch) => {
  dispatch({
    type: LOGIN,
    payload: data,
  });
};

export const AuthLogout = () => (dispatch) => {
  dispatch({
    type: LOGOUT,
  });
};
