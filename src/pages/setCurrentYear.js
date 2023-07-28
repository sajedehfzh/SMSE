import React, { useState, useEffect } from "react";
import axios from "axios";

const AcademicYearSelector = () => {
  const [academicYears, setAcademicYears] = useState([]);
  const [selectedYear, setSelectedYear] = useState("");


  const handleYearChange = (event) => {
    setSelectedYear(event.target.value);
  };

  useEffect(() => {
    //Fetch the academic year list from the API
    axios.get('http://localhost:8080/getAcademicList')
      .then(response => {
        setAcademicYears(response.data);
      })
      .catch(error => {
        console.error('Error fetching academic year list:', error);
      });
  }, []);

  const handleSetCurrentYear = () => {
    axios
      .post("http://localhost:8080/setCurentAcademicYear", {
        academicYear: selectedYear,
      })
      .then((response) => console.log(response))
      .catch((error) => console.error(error));
  };

  return (
    <div>
      <label htmlFor="academic-year">Select academic year:</label>
      <select id="academic-year" value={selectedYear} onChange={handleYearChange}>
        <option value="">-- Select --</option>
        {academicYears.map((year) => (
          <option key={year.id} value={year.yearTitle}>
            {year.yearTitle}
          </option>
        ))}
      </select>
      <button disabled={!selectedYear} onClick={handleSetCurrentYear}>
        Set current year
      </button>
    </div>
  );
};

export default AcademicYearSelector;
