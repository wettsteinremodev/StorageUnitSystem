import { useState, useEffect } from "react";
import { fetchStorageUnits } from "../../api/api";
import RentingForm from "../Rentings/RentingForm";
import useNotify from "../Notification/useNotify";

export default function StorageUnitList() {
  const [storageUnits, setStorageUnits] = useState([]);
  const [loading, setLoading] = useState(true);
  const [sortKey, setSortKey] = useState("name");

  // Change here: track selected storage unit to rent
  const [selectedStorageUnit, setSelectedStorageUnit] = useState(null);

  const notify = useNotify();

  useEffect(() => {
    fetchStorageUnits()
      .then((res) => {
        setStorageUnits(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching storage units:", err);
        notify.error("Failed to fetch storage units.");
        setLoading(false);
      });
  }, []);

  const sortedUnits = [...storageUnits].sort((a, b) => {
    switch (sortKey) {
      case "name":
        return a.name.localeCompare(b.name);
      case "price":
        return a.pricePerMonth - b.pricePerMonth;
      case "size":
        return a.sizeInM2 - b.sizeInM2;
      case "availability":
        return b.available === a.available ? 0 : b.available ? 1 : -1;
      default:
        return 0;
    }
  });

  if (loading) {
    return <p>Loading storage units...</p>;
  }

  if (storageUnits.length === 0) {
    return <p>No storage units available.</p>;
  }

  // New handler to open renting form with selected unit
  function handleRentNowClick(unit) {
    setSelectedStorageUnit(unit);
  }

  async function refreshList() {
    try {
      const response = await fetchStorageUnits();
      setStorageUnits(response.data);
      setSelectedStorageUnit(null); // close modal after refresh
    } catch (err) {
      notify.error("Failed to refresh storage units: " + err.message);
    }
  }

  function handleClose() {
    setSelectedStorageUnit(null);
  }

  return (
    <div>
      <h2>Storage Units</h2>
      <label htmlFor="sort-select" style={{ marginRight: "0.5rem" }}>
        Sort by:
      </label>
      <select
        id="sort-select"
        value={sortKey}
        onChange={(e) => setSortKey(e.target.value)}
        style={{ marginBottom: "1rem" }}
      >
        <option value="name">Name (A-Z)</option>
        <option value="price">Price (Low to High)</option>
        <option value="size">Size (Small to Large)</option>
        <option value="availability">Availability</option>
      </select>

      <div className="storage-unit-list">
        {sortedUnits.map((unit) => (
          <div key={unit.id} className="storage-unit-card">
            <strong>{unit.name}</strong>
            <p>Size: {unit.sizeInM2} m²</p>
            <p>Price: ${unit.pricePerMonth}/month</p>
            <p>
              Status:{" "}
              <span
                style={{
                  color: unit.available ? "#4CAF50" : "#f44336",
                  fontWeight: "bold",
                }}
              >
                {unit.available ? "Available" : "Not Available"}
              </span>
            </p>
            <div style={{ marginTop: "1rem" }}>
              {unit.available && (
                <button onClick={() => handleRentNowClick(unit)}>
                  Rent Now
                </button>
              )}
            </div>
          </div>
        ))}
      </div>

      {/* Show form modal only when selectedStorageUnit is set */}
      {selectedStorageUnit && (
        <div className="modal-backdrop">
          <div className="modal-content">
            <RentingForm
              editingRenting={null} // new renting, so no editing renting
              preselectedStorageUnit={selectedStorageUnit} // pass selected storage unit
              onClose={handleClose}
              onSave={refreshList}
            />
          </div>
        </div>
      )}
    </div>
  );
}
