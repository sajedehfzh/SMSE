import React, { useEffect, useState } from 'react';
import axios  from 'axios';
import "./cssFiles/home.css";
function ReportResult() {

  const [gresultsDTOs, setgResultsDTO] = useState([]);

  useEffect(() => {
    loadAllresult();
  }, []);

  const loadAllresult=async()=>{
    const result=await axios.get("http://localhost:8080/reportAllResults");
    setgResultsDTO(result.data);
  };

  return (
    <div className='container'>
      <div className='py-4'>
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">Person Code</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Academic Year</th>
              <th scope="col">WE1</th>
              <th scope="col">WE2</th>
               <th scope="col">IT</th>
              <th scope="col">RDD</th>
              <th scope="col">RP</th>
              <th scope="col">score</th>
              <th scope="col">status</th>
              <th scope="col">action</th>
            </tr>
          </thead>
        <tbody>
        {gresultsDTOs.map((gresultsDTO,index) => (
          <tr>
            <td>{gresultsDTO.studentCode}</td>
            <td>{gresultsDTO.studentFirstName}</td>
            <td>{gresultsDTO.studentLastName}</td>
            <td>{gresultsDTO.academicYear}</td>
            <td>{gresultsDTO.we1}</td>
            <td>{gresultsDTO.we2}</td>
            <td>{gresultsDTO.it}</td>
            <td>{gresultsDTO.rdd}</td>
            <td>{gresultsDTO.rp}</td>
            <td>{gresultsDTO.score}</td>
            <td>{gresultsDTO.status}</td>
            <td>
              <button className='btn btn-outline-primary mx-2'>Edit</button>
              <button className='btn btn-danger mx-2'>Delete</button>
            </td>
          </tr>
        ))}
      
     
      </tbody>
    </table>
    </div>
    </div>
  );
}
 
export default ReportResult;
