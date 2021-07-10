import React, { useContext } from "react";
import { GlobalContext } from "../../contexts/GlobalProvider";
import { AuthLogout } from "./../../contexts/actions/AuthAction";

const UserList = () => {
  const { authDispatch: dispatch } = useContext(GlobalContext);

  const handleLogout = () => {
    AuthLogout()(dispatch);
  };

  return (
    <div>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default UserList;
