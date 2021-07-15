import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { AuthLogout } from "../../store/actions/AuthAction";
import {
  Button,
  Container,
  CssBaseline,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@material-ui/core";
import Pagination from "@material-ui/lab/Pagination";
import { UserGetAll } from "./../../store/actions/UserAction";
import { useState } from "react";

const UserList = () => {
  const history = useHistory();
  const dispatch = useDispatch();
  const { users, userGlobal } = useSelector((state) => state.user);
  const [page, setPage] = useState(1);

  const handleLogout = () => {
    dispatch(AuthLogout());
    history.push("/");
  };

  useEffect(() => {
    dispatch(UserGetAll({ page }));
  }, [dispatch, page]);

  return (
    <div>
      <button onClick={handleLogout}>Logout</button>
      <hr style={{ marginBottom: "200px" }} />
      <CssBaseline />
      <Container>
        <h1>Danh sách nhân viên</h1>
        <TableContainer component={Paper}>
          <Table style={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Mã Người Dùng</TableCell>
                <TableCell>Tài Khoản</TableCell>
                <TableCell>Họ & tên</TableCell>
                <TableCell>Số điện thoại</TableCell>
                <TableCell>Email</TableCell>
                <TableCell>Hành động</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {users.map((u) => (
                <TableRow key={u.id}>
                  <TableCell component="th" scope="row">
                    {u.id}
                  </TableCell>
                  <TableCell>{u.username}</TableCell>
                  <TableCell>{u.fullName}</TableCell>
                  <TableCell>{u.phone}</TableCell>
                  <TableCell>{u.email}</TableCell>
                  <TableCell>
                    <Button
                      style={{ marginRight: 10 }}
                      variant="contained"
                      color="secondary"
                    >
                      Xoá
                    </Button>
                    <Button variant="contained" color="primary">
                      Sửa
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
        <Pagination
          style={{ marginTop: 100 }}
          variant="outlined"
          shape="rounded"
          count={userGlobal?.totalPages}
          showFirstButton
          showLastButton
          onChange={(event, value) => setPage(value)}
        />
      </Container>
      <hr style={{ marginTop: "200px" }} />
    </div>
  );
};

export default UserList;
