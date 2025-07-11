import { useState, useEffect } from "react";
import { createStorageUnit, updateStorageUnit } from "../../api/api"; // Make sure these functions exist
import useNotify from "../Notification/useNotify";

export default function StorageUnitForm({ editingUnit, onClose, onSave }) {
  const [formData, setFormData] = useState({
    name: "",
    sizeInM2: "",
    pricePerMonth: "",
    available: true,
  });

  const notify = useNotify();

  // Populate form with existing data if editing
  useEffect(() => {
    if (editingUnit) {
      setFormData({
        name: editingUnit.name || "",
        sizeInM2: editingUnit.sizeInM2 || "",
        pricePerMonth: editingUnit.pricePerMonth || "",
        available: editingUnit.available ?? true,
      });
    }
  }, [editingUnit]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    const newValue = type === "checkbox" ? checked : value;

    setFormData((prev) => ({
      ...prev,
      [name]: type === "number" ? parseFloat(newValue) : newValue,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      if (editingUnit) {
        await updateStorageUnit(editingUnit.id, formData);
        notify.success("Storage unit updated successfully.");
      } else {
        await createStorageUnit(formData);
        notify.success("Storage unit created successfully.");
      }

      onSave(); // Refresh list
      onClose(); // Close modal
    } catch (err) {
      console.error("Save failed:", err);
      notify.error("Failed to save storage unit.");
    }
  };

  return (
    <div>
      <h3>{editingUnit ? "Edit Storage Unit" : "Create New Storage Unit"}</h3>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          <input
            name="name"
            type="text"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Size (m²):</label>
          <input
            name="sizeInM2"
            type="number"
            value={formData.sizeInM2}
            onChange={handleChange}
            required
            min="0"
            step="0.1"
          />
        </div>

        <div>
          <label>Price per month ($):</label>
          <input
            name="pricePerMonth"
            type="number"
            value={formData.pricePerMonth}
            onChange={handleChange}
            required
            min="0"
            step="0.01"
          />
        </div>

        <div>
          <label>
            <input
              name="available"
              type="checkbox"
              checked={formData.available}
              onChange={handleChange}
            />
            Available
          </label>
        </div>

        <div style={{ marginTop: "1rem" }}>
          <button type="submit">{editingUnit ? "Update" : "Create"}</button>
          <button
            type="button"
            onClick={onClose}
            style={{ marginLeft: "1rem" }}
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}
