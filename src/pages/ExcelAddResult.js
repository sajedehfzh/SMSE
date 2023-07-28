import React, { useState } from "react";
import * as XLSX from "xlsx";
import axios from "axios";
import './addexcel.css';

const UploadExcel = () => {
  const [data, setData] = useState([]);
  const [editIndex, setEditIndex] = useState(null);

  const handleFileUpload = (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();
    reader.onload = (evt) => {
      const bstr = evt.target.result;
      const wb = XLSX.read(bstr, { type: "binary" });
      const wsname = wb.SheetNames[0];
      const ws = wb.Sheets[wsname];
      const data = XLSX.utils.sheet_to_json(ws, { header: 1 });
      setData(data);
    };
    reader.readAsBinaryString(file);
  };

  const deleteRow = (index) => {
    const newData = [...data];
    newData.splice(index, 1);
    setData(newData);
  };

  const editRow = (index) => {
    setEditIndex(index);
  };

  const saveRow = (index) => {
    setEditIndex(null);
  };

  const handleChange = (e, index, key) => {
    const newData = [...data];
    newData[index][key] = e.target.value;
    setData(newData);
  };

  const handleSubmit = async () => {
    try {
      const results = data.map((row) => ({
        "personCode": parseInt(row[0]),
        "firstname": row[1],
        "lastname": row[2],
        "scoreWE1": parseFloat(row[3]),
        "callWE1": parseInt(row[4]),
        "scoreWE2": parseFloat(row[5]),
        "callWE2": parseInt(row[6]),
        "scoreIT": parseFloat(row[7]),
        "callIT": parseInt(row[8]),
        "scoreRDD": parseFloat(row[9]),
        "callRDD": parseInt(row[10]),
        "scoreRP": parseFloat(row[11]),
        "callRP": parseInt(row[12]),
        }));
      const response = await axios.post("http://localhost:8080/uploadExcel", results);
      console.log(response);
    } catch (error) {
      console.error("Error uploading data:", error);
    }
  };

  return (
    <div>
      <input type="file" onChange={handleFileUpload} />
      <table >
        <thead>
          <tr>
            <th>Person Code</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Score WE1</th>
            <th>Call WE1</th>
            <th>Score WE2</th>
            <th>Call WE2</th>
            <th>Score IT</th>
            <th>Call IT</th>
            <th>Score RDD</th>
            <th>Call RDD</th>
            <th>Score RP</th>
            <th>Call RP</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {data.map((row, index) => (
            <tr key={index}>
              {Object.keys(row).map((key) => (
                <td key={key}>
                  {editIndex === index ? (
                    <input className="edit-input"
                      value={row[key]}
                      onChange={(e) => handleChange(e, index, key)}
                    />
                  ) : (
                    row[key]
                  )}
                </td>
              ))}
              <td>
                {editIndex === index ? (
                  <button className="save" onClick={() => saveRow(index)}>Save</button>
                ) : (
                  <button className="edit" onClick={() => editRow(index)}>Edit</button>
                )}
                <button  className="delete" onClick={() => deleteRow(index)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
};

export default UploadExcel;