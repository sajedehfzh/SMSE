import React, { useState } from 'react';
import axios from 'axios';

function StudentInfo() {
  const [searchTerm, setSearchTerm] = useState('');
  const [studentInfo, setStudentInfo] = useState(null);
  const [editMode, setEditMode] = useState(false);
  const [updatedInfo, setUpdatedInfo] = useState(null);

  const handleSearch = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.get(`http://localhost:8080/showStudentInfo/${searchTerm}`);
      setStudentInfo(response.data);
      setEditMode(false);
    } catch (error) {
      console.log(error);
    }
  };

  const handleInputChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleEdit = () => {
    setEditMode(true);
    setUpdatedInfo({ ...studentInfo });
  };

  const handleSave = async () => {
    try {
      await axios.post('http://localhost:8080/updateStudentInfo', updatedInfo);
      setStudentInfo(updatedInfo);
      setEditMode(false);
    } catch (error) {
      console.log(error);
    }
  };

  const handleCancel = () => {
    setEditMode(false);
    setUpdatedInfo(null);
  };

  const handleUpdate = (event) => {
    setUpdatedInfo({
      ...updatedInfo,
      [event.target.name]: event.target.value,
    });
  };

  return (
    <div>
      <form onSubmit={handleSearch}>
        <label>
          Person Code:
          <input type="text" value={searchTerm} onChange={handleInputChange} />
        </label>
        <button type="submit">Search</button>
      </form>
      {studentInfo && (
        <div>
          <h2>Student Information</h2>
          {!editMode ? (
            <div>
              <p>
                <strong>First Name:</strong> {studentInfo.firstName}
              </p>
              <p>
                <strong>Last Name:</strong> {studentInfo.lastName}
              </p>
              <p>
                <strong>Student Code:</strong> {studentInfo.studentCode}
              </p>
              <button onClick={handleEdit}>Edit</button>
            </div>
          ) : (
            <div>
             
              <label>
                First Name:
                <input type="text" name="firstName" value={updatedInfo.firstName} onChange={handleUpdate} />
              </label>
              <br />
              <label>
                Last Name:
                <input type="text" name="lastName" value={updatedInfo.lastName} onChange={handleUpdate} />
              </label>
              <br />
              <label>
                Student Code:
                <input type="text" name="studentCode" value={updatedInfo.studentCode} onChange={handleUpdate} />
              </label>
              <br />
              <button onClick={handleSave}>Save</button>
              <button onClick={handleCancel}>Cancel</button>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default  StudentInfo;
