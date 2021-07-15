import Footer from "../../Footer";
import Header from "../Header";

const HeaderFooter = ({ children }) => {
  return (
    <>
      <Header />
      {children}
      <Footer />
    </>
  );
};

export default HeaderFooter;
