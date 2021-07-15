import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  userGlobal: {},
  users: [],
};

const UserSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    getUsers: (state, action) => {
      state.userGlobal = action.payload;
      state.users = action.payload.content;
    },
  },
});

const { reducer, actions } = UserSlice;
export const { getUsers } = actions;
export default reducer;
