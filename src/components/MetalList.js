import { useEffect, useState } from "react";
import API from "../services/api";

function MetalList() {
  const [metals, setMetals] = useState([]);

  useEffect(() => {
    API.get("/metals")
      .then(res => setMetals(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Available Metals</h2>
      <ul>
        {metals.map((metal, index) => (
          <li key={index}>{metal}</li>
        ))}
      </ul>
    </div>
  );
}

export default MetalList;