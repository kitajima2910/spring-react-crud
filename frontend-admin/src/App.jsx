import React, { Suspense } from "react";
import "./App.css";
import { CircularProgress } from "@material-ui/core";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import PrivateRoute from "./components/PrivateRoute";

const Login = React.lazy(() => import("./components/Login"));
const UserList = React.lazy(() => import("./components/UserList"));

const App = () => {
  return (
    <Router>
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
          <Route exact path={["/", "/login"]}>
            <Login />
          </Route>
          <PrivateRoute path="/dashboard">
            <UserList />
          </PrivateRoute>
          <Route path="*">
            <h1>404 Không tìm thấy trang</h1>
          </Route>
        </Switch>
      </Suspense>
    </Router>
  );
};

export default App;
