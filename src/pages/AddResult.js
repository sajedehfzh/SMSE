import { useState } from "react";
import axios from "axios";
import './addexcel.css';
function Table() {
  const [results, setResults] = useState([{ personCode: "", firstname: "", lastname: "", scoreWE1: "", callWE1: "", scoreWE2: "", callWE2: "",  scoreIT: "", callIT: "", scoreRDD: "", callRDD: "", scoreRP: "",callRP: "", }]);

  const handleAddRow = () => {
    setResults([...results, { personCode: "", firstname: "", lastname: "", scoreWE1: "", callWE1: "", scoreWE2: "", callWE2: "", scoreIT: "", callIT: "", scoreRDD: "",callRDD: "", scoreRP: "",callRP: "", }]);
  };

  const handleDeleteRow = (index) => {
    const newResults = [...results];
    newResults.splice(index, 1);
    setResults(newResults);
  };

  const handleInputChange = (event, index) => {
    const { name, value } = event.target;
    const newResults = [...results];
    newResults[index][name] = value;
    setResults(newResults);
  };


  const handleDropdownChange = (event, index) => {
    const { name, value } = event.target;
    const intValue = parseInt(value);
  
    setResults((prevResults) => {
      const newResults = [...prevResults];
      newResults[index] = {
        ...newResults[index],
        [name]: isNaN(intValue) ? 1 : intValue,
      };
      return newResults;
    });
  };
  


  const handleSubmit = async (event) => {
    event.preventDefault();
  
    // Convert personCode and call fields to integer
    const formattedResults = results.map((result) => {
      return {
        ...result,
        "personCode": parseInt(result.personCode),
        "firstname": result.firstname,
        "lastname": result.lastname,
        "scoreWE1": isNaN(parseFloat(result.scoreWE1)) ? 0.0 : parseFloat(result.scoreWE1),
        "callWE1": isNaN(parseInt(result.callWE1))? 1 : parseInt(result.callWE1),
        "scoreWE2": isNaN(parseFloat(result.scoreWE2)) ? 0.0 : parseFloat(result.scoreWE2),
        "callWE2": isNaN(parseInt(result.callWE2))? 1 : parseInt(result.callWE2),
       "scoreIT": isNaN(parseFloat(result.scoreIT)) ? 0.0 :parseFloat(result.scoreIT),
        "callIT": isNaN(parseInt(result.callIT))? 1 : parseInt(result.callIT),
        "scoreRDD": isNaN(parseFloat(result.scoreRDD)) ? 0.0 : parseFloat(result.scoreRDD),
        "callRDD": isNaN(parseInt(result.callRDD))? 1: parseInt(result.callRDD),
        "scoreRP": isNaN(parseFloat(result.scoreRP)) ? 0.0 : parseFloat(result.scoreRP),
        "callRP": isNaN(parseInt(result.callRP))? 1 : parseInt(result.callRP),
      };
    });


  
    try {
    const response = await axios.post("http://localhost:8080/addResult", formattedResults);
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
};
  
  

  return (
    <form onSubmit={handleSubmit}>
      <table>
        <thead>
          <tr>
            <th>Person Code</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>ScoreWE1</th>
            <th>CallWE1</th>
            <th>ScoreWE2</th>
            <th>CallWE2</th>
            <th>ScoreIT</th>
            <th>CallIT</th>
            <th>ScoreRDD</th>
            <th>CallRDD</th>
            <th>ScoreRP</th>
            <th>CallRP</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {results.map((result, index) => (
            <tr key={index}>
              <td>
                <input type="text" name="personCode" value={result.personCode} onChange={(event) => handleInputChange(event, index)} />
              </td>
              <td>
                <input type="text" name="firstname" value={result.firstname} onChange={(event) => handleInputChange(event, index)} />
              </td>
              <td>
                <input type="text" name="lastname" value={result.lastname} onChange={(event) => handleInputChange(event, index)} />
              </td>
              <td>
                <input type="text" name="scoreWE1" value={result.scoreWE1} onChange={(event) => handleInputChange(event, index)}/>
                </td>
                <td>
                <select name="callWE1" value={result.callWE1} onChange={(event) => handleDropdownChange(event, index)}>
                                  {[1, 2, 3, 4, 5].map((num) => (
                                    <option key={num} value={num}>{num}</option>
                                  ))}
                                </select>
                              </td>
                              <td>
                                <input type="text" name="scoreWE2" value={result.scoreWE2} onChange={(event) => handleInputChange(event, index)} />
                              </td>
                              <td>
                                <select name="callWE2" value={result.callWE2} onChange={(event) => handleDropdownChange(event, index)}>
                                  {[1, 2, 3, 4, 5].map((num) => (
                                    <option key={num} value={num}>{num}</option>
                                  ))}
                                </select>
                              </td>
                              <td>
                                <input type="text" name="scoreIT" value={result.scoreIT} onChange={(event) => handleInputChange(event, index)} />
                              </td>
                              <td>
                                <select name="callIT" value={result.callIT} onChange={(event) => handleDropdownChange(event, index)}>
                                  {[1, 2, 3, 4, 5].map((num) => (
                                    <option key={num} value={num}>{num}</option>
                                  ))}
                                </select>
                              </td>
                              <td>
                                <input type="text" name="scoreRDD" value={result.scoreRDD} onChange={(event) => handleInputChange(event, index)} />
                              </td>
                              <td>
                                <select name="callRDD" value={result.callRDD} onChange={(event) => handleDropdownChange(event, index)}>
                                  {[1, 2, 3, 4, 5].map((num) => (
                                    <option key={num} value={num}>{num}</option>
                                  ))}
                                </select>
                              </td>
                              <td>
                                <input type="text" name="scoreRP" value={result.scoreRP} onChange={(event) => handleInputChange(event, index)} />
                              </td>
                              <td>
                                <select name="callRP" value={result.callRP} onChange={(event) => handleDropdownChange(event, index)}>
                                  {[1, 2, 3, 4, 5].map((num) => (
                                    <option key={num} value={num}>{num}</option>
                                  ))}
                                </select>
                              </td>
                              <td>
                                <button type="button" onClick={() => handleDeleteRow(index)}>-</button>
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                      <button type="button" onClick={()=>handleAddRow()}>+</button>
      <button type="submit" >Submit</button>
    </form>
  );
}

export default Table;
                