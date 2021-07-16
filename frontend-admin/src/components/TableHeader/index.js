import {
  TableCell,
  TableHead,
  TableRow,
  TableSortLabel,
} from "@material-ui/core";

const TableHeader = ({ valueToOrderBy, valueToSortDir, handleRequestSort }) => {
  const createSortHandler = (property) => (event) => {
    handleRequestSort(property);
  };

  const fields = [
    { name: "id", lable: "Mã Người Dùng", dir: "asc" },
    { name: "username", lable: "Tài Khoản", dir: "asc" },
    { name: "fullName", lable: "Họ & tên", dir: "asc" },
    { name: "phone", lable: "Số điện thoại", dir: "asc" },
    { name: "email", lable: "Email", dir: "asc" },
    { lable: "Hành Động" },
  ];

  return (
    <TableHead>
      <TableRow>
        {fields.map((field, index) => (
          <TableCell key={index}>
            {field.name ? (
              <TableSortLabel
                active={Object.is(valueToOrderBy, field.name)}
                direction={
                  Object.is(valueToOrderBy, field.name)
                    ? valueToSortDir
                    : field.dir
                }
                onClick={createSortHandler(field.name)}
              >
                {field.lable}
              </TableSortLabel>
            ) : (
              field.lable
            )}
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  );
};

export default TableHeader;
