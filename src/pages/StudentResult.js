import React, { useState } from 'react';
import axios from 'axios';

function SearchBox() {
  const [personCode, setPersonCode] = useState('');
  const [resultList, setResultList] = useState(null);
 

  const handleSearch = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/studentResult/${personCode}`);
      setResultList(response.data);

    } catch (error) {
      console.log(error);
    }
  };

  const handleSave = (id) => {
    const updatedResultList = resultList.results.map(result => {
      if (result.resultId === id) {
        return {...result, editing: !result.editing};
      }
      return result;
    });
    setResultList({...resultList, results: updatedResultList});
  };

  const handleDelete = (id) => {
    setResultList(prevState => {
      const updatedResults = prevState.results.filter(result => result.resultId !== id);
      return {
        ...prevState,
        results: updatedResults
      };
    });
  };

  const handleEdit = (id, field, newValue) => {
    const updatedResultList = resultList.results.map(result => {
      if (result.resultId === id) {
        return {...result, [field]: newValue, editing: true};
      }
      return result;
    });
    setResultList({...resultList, results: updatedResultList});
  };

  const handleSubmit = async () => {
    try {

        const resultListDTO = {
            "studentId": resultList.studentId,
            "firstName": String(resultList.firstName),
            "lastName": String(resultList.lastName),
            "personCode": parseInt(resultList.personCode),
            "results": resultList.results
          };
     const response= await axios.post('http://localhost:8080/updateStudentResult', resultListDTO);
      // handle success
      console.log(response);
    } catch (error) {
      console.error("Error uploading data:", error);
    }
  };

  return (
    <div>
      <label>
        Person Code:
        <input type="number" value={personCode} onChange={(e) => setPersonCode(e.target.value)} />
      </label>
      <button onClick={handleSearch}>Search</button>
  
      {resultList && (
        <div>
          <h2>{resultList.firstName} {resultList.lastName} ({resultList.personCode})</h2>
          <table>
            <thead>
              <tr>
                <th>Academic Year</th>
                <th>Academic Call</th>
                <th>Type</th>
                <th>Score</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {resultList.results.map(result => (
                <tr key={result.resultId}>
                  <td>
                    {result.editing ? (
                      <input type="text" value={result.academicYear} onChange={(e) => handleEdit(result.resultId, 'academicYear', e.target.value)} />
                    ) : (
                      result.academicYear
                    )}
                  </td>
                  <td>
                    {result.editing ? (
                      <input type="text" value={result.academicCall} onChange={(e) => handleEdit(result.resultId, 'academicCall', e.target.value)} />
                    ) : (
                      result.academicCall
                    )}
                  </td>
                  <td>
                    {result.editing ? (
                      <select value={result.type} onChange={(e) => handleEdit(result.resultId, 'type', e.target.value)}>
                        <option value="WE1">WE1</option>
                        <option value="WE2">WE2</option>
                        <option value="RDD">RDD</option>
                        <option value="IT">IT</option>
                        <option value="RP">RP</option>
                      </select>
                    ) : (
                      result.type
                    )}
                  </td>
                  <td>
                    {result.editing ? (
                      <input type="text" value={result.score} onChange={(e) => handleEdit(result.resultId, 'score', e.target.value)} />
                    ) : (
                      result.score
                    )}
                  </td>
                  <td>
                    {result.editing ? (
                      <button onClick={() => handleSave(result.resultId)}>Save</button>
                    ) : (
                      <button onClick={() => handleEdit(result.resultId)}>Edit</button>
                    )}
                    <button onClick={() => handleDelete(result.resultId)}>Delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <button onClick={handleSubmit}>Submit</button>
        </div>
      )}
    </div>
  );
  
                  

}

export default SearchBox;
