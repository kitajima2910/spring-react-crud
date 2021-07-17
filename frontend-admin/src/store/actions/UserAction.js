import { getUsers } from "./../reducers/UserReducer";
import UserService from "./../../services/UserService";

export const UserGetAll = (query) => async (dispatch) => {
  try {
    const res = await UserService.list(query);
    dispatch(getUsers(res.data));
  } catch (error) {
    console.error(error);
  }
};
