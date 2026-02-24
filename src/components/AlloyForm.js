import { useState } from "react";
import API from "../services/api";

function AlloyForm() {
  const [elements, setElements] = useState("");
  const [result, setResult] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    API.post("/alloy/calculate", {
      elements: elements.split(",").map(e => e.trim())
    })
      .then(res => setResult(res.data))
      .catch(err => console.error(err));
  };

  return (
    <div>
      <h2>Alloy Calculator</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter metals (Fe,Ni,Cr)"
          value={elements}
          onChange={(e) => setElements(e.target.value)}
          style={{ padding: "8px", width: "300px" }}
        />
        <button type="submit" style={{ marginLeft: "10px" }}>
          Calculate
        </button>
      </form>

      {result && (
        <div style={{ marginTop: "20px" }}>
          <h3>Result</h3>
          <pre>{JSON.stringify(result, null, 2)}</pre>
        </div>
      )}
    </div>
  );
}

export default AlloyForm;