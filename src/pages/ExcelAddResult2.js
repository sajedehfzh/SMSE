import React, { useState } from 'react';
import axios from 'axios';
import * as XLSX from 'xlsx';

function ExcelUploader() {
  const [data, setData] = useState([]);
  const [editingIndex, setEditingIndex] = useState(null);

  const handleFileUpload = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (e) => {
      const data1 = e.target.result;
      const workbook = XLSX.read(data1, { type: 'binary' });
      const sheetName = workbook.SheetNames[0];
      const worksheet = workbook.Sheets[sheetName];
      const data = XLSX.utils.sheet_to_json(worksheet, { header: 1 });

      setData(data);
    };

    reader.readAsBinaryString(file);
  };

  const handleRowDelete = (index) => {
    const newData = [...data];
    newData.splice(index, 1);
    setData(newData);
  };

  const handleRowEdit = (index) => {
    setEditingIndex(index);
  };

  const handleEditSubmit = (event, index) => {
    event.preventDefault();
    const form = event.target;
    const updatedRow = Array.from(form.elements).slice(0, -1).map((input) => input.value);
    const newData = [...data];
    newData[index] = updatedRow;
    setData(newData);
    setEditingIndex(null);
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.post('http://localhost:8080/submit', data);
      console.log(response);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <input type="file" onChange={handleFileUpload} />
      <table>
        <thead>
          <tr>
            {data[0]?.map((header, index) => (
              <th key={index}>{header}</th>
            ))}
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {data.slice(1).map((row, index) => (
            <tr key={index}>
              {editingIndex === index ? (
                <form onSubmit={(e) => handleEditSubmit(e, index)}>
                  {row.map((cell, index2) => (
                    <td key={index2}>
                      <input defaultValue={cell} />
                    </td>
                  ))}
                  <td>
                    <button type="submit">Save</button>
                  </td>
                </form>
              ) : (
                <>
                  {row.map((cell, index2) => (
                    <td key={index2}>{cell}</td>
                  ))}
                  <td>
                    <button onClick={() => handleRowDelete(index)}>Delete</button>
                    <button onClick={() => handleRowEdit(index)}>Edit</button>
                  </td>
                </>
              )}
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
}

export default ExcelUploader;
