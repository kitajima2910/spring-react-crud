import React, { createContext, useReducer, useEffect } from "react";
import { useState } from "react";
import { useHistory } from "react-router-dom";
import AuthReducer from "./reducers/AuthReducer";

// Create Global Context
export const GlobalContext = createContext({});

const GlobalProvider = ({ children }) => {
  // Get User localStorage
  const history = useHistory();
  const [messageLogin, setMessageLogin] = useState("");

  // Auth
  const [authState, authDispatch] = useReducer(AuthReducer, {
    isLogined: false,
    user: JSON.parse(localStorage.getItem("user")),
  });

  useEffect(() => {
    // Clear Route Previous
    window.history.pushState(null, null, window.location.href);
    window.onpopstate = function () {
      history.go(1);
    };

    console.log(authState);

    // Check Login
    if (authState.isLogined) {
      if (authState.user === null) {
        setMessageLogin("Tài khoản hoặc mật khẩu không đúng");
        history.push("/login");
        return;
      }
      if (authState.user.roles.includes("ROLE_ADMIN")) {
        localStorage.setItem("user", JSON.stringify(authState.user));
        history.push("/");
      } else {
        setMessageLogin("Tài khoản không có quyền truy cập");
        history.push("/login");
      }
    } else {
      setMessageLogin("");
      history.push("/login");
    }
  }, [authState, history]);

  return (
    <GlobalContext.Provider
      value={{
        messageLogin,
        authState,
        authDispatch,
      }}
    >
      {children}
    </GlobalContext.Provider>
  );
};

export default GlobalProvider;
