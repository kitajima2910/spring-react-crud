import { useSelector } from "react-redux";
import { Route, Redirect } from "react-router-dom";
import HeaderFooter from "./../HeaderFooter";

const PrivateRoute = ({ children, ...rest }) => {
  const auth = useSelector((state) => state.auth);

  return (
    <Route
      {...rest}
      render={() =>
        auth.user ? (
          <HeaderFooter>{children}</HeaderFooter>
        ) : (
          <Redirect to="/login" />
        )
      }
    />
  );
};

export default PrivateRoute;
