import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav style={styles.nav}>
      <h2>High Entropy Alloy Calculator</h2>
      <div>
        <Link to="/" style={styles.link}>Home</Link>
        <Link to="/metals" style={styles.link}>Metals</Link>
        <Link to="/calculator" style={styles.link}>Calculator</Link>
      </div>
    </nav>
  );
}

const styles = {
  nav: {
    padding: "15px",
    background: "#222",
    color: "white",
    display: "flex",
    justifyContent: "space-between"
  },
  link: {
    margin: "0 10px",
    color: "white",
    textDecoration: "none"
  }
};

export default Navbar;