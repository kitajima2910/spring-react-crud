import { LOGIN, LOGOUT } from "./../action-types/AuthType";

const AuthReducer = (state, action) => {
  const { type, payload } = action;

  switch (type) {
    case LOGIN:
      return {
        ...state,
        isLogined: true,
        user: payload,
      };
    case LOGOUT:
      return {
        ...state,
        isLogined: false,
        user: null,
      };
    default:
      return state;
  }
};

export default AuthReducer;
