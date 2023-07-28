import React, { useState } from 'react';


function CreateAcademicYear() {
    const [year, setYear] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
  
    const handleChange = (event) => {
      setYear(event.target.value);
      setErrorMessage("");
    };

    const handleSave = () => {
        if (!year) {
          setErrorMessage("Please enter a valid academic year");
          return;
        }
        fetch("http://localhost:8080/createNewAcademicYear", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ year}), 
        })
          .then((response) => response.json())
          .then((data) => {
            console.log("Success:", data);
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      };

  return (
    <div>
      <form>
        <label>
          Academic Year Title:
          <p style={{ color: 'red' }}>Information! for academic year title use this format '2022-23' </p>
          <input type="text" id="year" value={year} onChange={handleChange} />
        </label>
        <button onClick={handleSave}>Save</button>
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
      </form>
      
    </div>
  );
}

export default CreateAcademicYear;
