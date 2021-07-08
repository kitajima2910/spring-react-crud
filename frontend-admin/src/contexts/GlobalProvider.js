import React, { createContext, useReducer, useEffect } from "react";
import { useHistory } from "react-router-dom";
import AuthReducer from "./reducers/AuthReducer";

// Create Global Context
export const GlobalContext = createContext({});

const GlobalProvider = ({ children }) => {
  // Get User localStorage
  const getUser = JSON.parse(localStorage.getItem("user"));
  const history = useHistory();

  // Auth
  const [authState, authDispatch] = useReducer(AuthReducer, {
    user: null,
  });

  useEffect(() => {
    // Clear Route Previous
    window.history.pushState(null, null, window.location.href);
    window.onpopstate = function () {
      history.go(1);
    };

    // Check Login
    if (getUser) {
      history.push("/");
    } else {
      history.push("/login");
    }
  }, [getUser, history]);

  return (
    <GlobalContext.Provider
      value={{
        authState,
        authDispatch,
      }}
    >
      {children}
    </GlobalContext.Provider>
  );
};

export default GlobalProvider;
