import { useEffect, useState } from "react";
import API from "../services/api";

function HomePage() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    API.get("/metal-count")
      .then(res => setCount(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div style={{ textAlign: "center", marginTop: "40px" }}>
      <h1>High Entropy Alloy Calculator</h1>
      <h2>{count} Metals in Dataset</h2>
      <p>Thermodynamic evaluation platform for multi-component alloys.</p>
    </div>
  );
}

export default HomePage;