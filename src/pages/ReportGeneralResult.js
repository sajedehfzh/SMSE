import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Report() {
  const [academicYearList, setAcademicYearList] = useState([]);
  const [selectedAcademicYear, setSelectedAcademicYear] = useState('');
  const [reportResultList, setReportResultList] = useState([]);

  useEffect(() => {
    //Fetch the academic year list from the API
    axios.get('http://localhost:8080/getAcademicList')
      .then(response => {
        setAcademicYearList(response.data);
      })
      .catch(error => {
        console.error('Error fetching academic year list:', error);
      });
  }, []);

  const handleAcademicYearChange = (event) => {
    // Update the selected academic year when the user selects a new one
    setSelectedAcademicYear(event.target.value);
  };

  const handleGenerateReport = () => {
    // Call the API to generate the report with the selected academic year
    axios.post('http://localhost:8080/getGeneralReport', {
      yearTitle: selectedAcademicYear
    })
      .then(response => {
        setReportResultList(response.data);
      })
      .catch(error => {
        console.error('Error generating report:', error);
      });
  };

  return (
    <div>
      <h1>Report</h1>
      <label htmlFor="academicYear">Academic Year:</label>
      <select id="academicYear" value={selectedAcademicYear} onChange={handleAcademicYearChange}>
      {academicYearList.map(academicYearObj => (
    <option key={academicYearObj.id} value={academicYearObj.yearTitle}>
      {academicYearObj.yearTitle}</option>
        ))}
      </select>
      <button disabled={!selectedAcademicYear} onClick={handleGenerateReport}>Generate Report</button>
      {reportResultList.length > 0 && (
        <table>
          <thead>
            <tr>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Person Code</th>
              <th>score WE1</th>
              <th>call WE</th>
              <th>score WE2</th>
              <th>call WE2</th>
              <th>score IT</th>
              <th>call IT</th>
              <th>score RDD</th>
              <th>call RDD</th>
              <th>score RP</th>
              <th>call RP</th>
              <th>Score</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {reportResultList.map(result => (
              <tr key={result.studentCode}>
                <td>{result.studentFirstName}</td>
                <td>{result.studentLastName}</td>
                <td>{result.studentCode}</td>
                <td>{result.scoreWE1}</td>
                <td>{result.callWE}</td>
                <td>{result.scoreWE2}</td>
                <td>{result.callWE2}</td>
                <td>{result.scoreIT}</td>
                <td>{result.callIT}</td>
                <td>{result.scoreRDD}</td>
                <td>{result.callRDD}</td>
                <td>{result.scoreRP}</td>
                <td>{result.callRP}</td>
                <td>{result.score}</td>
                <td>{result.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default Report;