import React, { Suspense } from "react";
import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import GlobalProvider from "./contexts/GlobalProvider";
import { CircularProgress } from "@material-ui/core";

const Login = React.lazy(() => import("./components/Login"));
const UserList = React.lazy(() => import("./components/UserList"));

function App() {
  return (
    <>
      <Router>
        <GlobalProvider>
          <Suspense
            fallback={
              <CircularProgress
                disableShrink
                style={{
                  position: "absolute",
                  top: "50%",
                  left: "50%",
                }}
              />
            }
          >
            <Switch>
              <Route exact path={["/", "/user-list"]} component={UserList} />
              <Route path="/login" component={Login} />
            </Switch>
          </Suspense>
        </GlobalProvider>
      </Router>
    </>
  );
}

export default App;
