import { getUsers } from "./../reducers/UserReducer";
import UserService from "./../../services/UserService";

export const UserGetAll = (query) => async (dispatch) => {
  try {
    console.log(query);
    const res = await UserService.list(query);
    console.log(res.data);
    dispatch(getUsers(res.data));
  } catch (error) {
    console.error(error);
  }
};
