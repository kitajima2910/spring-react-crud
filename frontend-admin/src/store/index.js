import { configureStore } from "@reduxjs/toolkit";
import thunk from "redux-thunk";
import AuthReducer from "./reducers/AuthReducer";
import UserReducer from "./reducers/UserReducer";

const store = configureStore({
  reducer: {
    auth: AuthReducer,
    user: UserReducer,
  },
  devTools:
    window.__REDUX_DEVTOOLS_EXTENSION__ &&
    window.__REDUX_DEVTOOLS_EXTENSION__(),
  middleware: [thunk],
});

export default store;
