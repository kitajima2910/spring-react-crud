import React, { useContext } from "react";
import { Grid, TextField, Button, InputAdornment } from "@material-ui/core";
import { AccountCircle, LockRounded } from "@material-ui/icons";
import logo from "./../../assets/img/avatar.jpg";
import { GlobalContext } from "./../../contexts/GlobalProvider";
import { useHistory } from "react-router-dom";

const Login = () => {
  const { authDispatch: dispatch } = useContext(GlobalContext);
  const history = useHistory();

  const handleLogin = (e) => {
    e.preventDefault();
    localStorage.setItem("user", JSON.stringify({ roles: "ROLE_USER" }));
    history.push("/");
  };

  return (
    <>
      <Grid container style={{ minHeight: "100vh" }}>
        <Grid
          container
          item
          xs={12}
          sm={12}
          alignItems="center"
          direction="column"
          justifyContent="center"
          style={{ padding: 10 }}
        >
          <form
            onSubmit={handleLogin}
            style={{
              display: "flex",
              flexDirection: "column",
              maxWidth: 400,
              minWidth: 300,
            }}
          >
            <Grid container justifyContent="center">
              <img src={logo} alt="logo" loading="lazy" width="100" />
            </Grid>
            <TextField
              label="Tài khoản"
              autoComplete="off"
              margin="normal"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <AccountCircle />
                  </InputAdornment>
                ),
              }}
            />
            <TextField
              label="Mật khẩu"
              type="password"
              autoComplete="current-password"
              margin="normal"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <LockRounded />
                  </InputAdornment>
                ),
              }}
            />
            <Button
              type="submit"
              variant="contained"
              color="primary"
              style={{ marginTop: 20 }}
            >
              Đăng Nhập
            </Button>
          </form>
        </Grid>
      </Grid>
    </>
  );
};

export default Login;
