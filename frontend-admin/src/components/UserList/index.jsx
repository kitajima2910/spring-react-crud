import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useHistory } from "react-router-dom";
import { AuthLogout } from "../../store/actions/AuthAction";
import {
  Button,
  Container,
  CssBaseline,
  Grid,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  TextField,
  Typography,
} from "@material-ui/core";
import Pagination from "@material-ui/lab/Pagination";
import { UserGetAll } from "./../../store/actions/UserAction";
import { useState } from "react";
import TableHeader from "../TableHeader";

const UserList = () => {
  const history = useHistory();
  const dispatch = useDispatch();
  const { users, totalPages } = useSelector((state) => state.user);
  const [page, setPage] = useState(1);
  const [valueToOrderBy, setValueToOrderBy] = useState("id");
  const [valueToSortDir, setValueToSortDir] = useState("asc");
  const [keyword, setKeyword] = useState("");
  const [name, setName] = useState("");

  const handleLogout = () => {
    dispatch(AuthLogout());
    history.push("/");
  };

  useEffect(() => {
    dispatch(
      UserGetAll({
        page,
        sortField: valueToOrderBy,
        sortDir: valueToSortDir,
        keyword,
      })
    );
  }, [dispatch, page, valueToOrderBy, valueToSortDir, keyword]);

  const handleRequestSort = (property) => {
    const isAscending =
      Object.is(valueToOrderBy, property) && Object.is(valueToSortDir, "asc");
    setValueToOrderBy(property);
    setValueToSortDir(isAscending ? "desc" : "asc");
  };

  const handleSearch = (e) => {
    e.preventDefault();
    setKeyword(name);
  };

  return (
    <div>
      <button onClick={handleLogout}>Logout</button>
      <hr style={{ marginBottom: "20px" }} />
      <CssBaseline />
      <Container>
        <Grid
          style={{
            display: "flex",
            flexDirection: "row",
          }}
        >
          <Grid
            item
            xl={6}
            sm={6}
            style={{ display: "flex", alignItems: "flex-end" }}
          >
            <Typography variant="h4" component="h4">
              Danh sách nhân viên
            </Typography>
          </Grid>
          <Grid item xl={6} sm={6}>
            <form
              onSubmit={handleSearch}
              style={{
                display: "flex",
                flexDirection: "row-reverse",
                alignItems: "center",
              }}
            >
              <TextField
                label="Tìm kiếm"
                margin="normal"
                onChange={(e) => setName(e.target.value)}
              />
              <Button
                type="submit"
                variant="contained"
                color="primary"
                style={{ height: 36, marginRight: 10, marginTop: 16 }}
              >
                Tìm Kiếm
              </Button>
            </form>
          </Grid>
        </Grid>

        <TableContainer component={Paper}>
          <Table style={{ minWidth: 650 }} aria-label="simple table">
            <TableHeader
              valueToOrderBy={valueToOrderBy}
              valueToSortDir={valueToSortDir}
              handleRequestSort={handleRequestSort}
            />
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
          count={totalPages}
          showFirstButton
          showLastButton
          onChange={(event, value) => setPage(value)}
        />
      </Container>
      <hr style={{ marginTop: "20px" }} />
    </div>
  );
};

export default UserList;
