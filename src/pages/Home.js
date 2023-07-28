import React, { useState, useEffect } from 'react';

function Home() {
  const [currentYear, setCurrentYear] = useState('');
  
  useEffect(() => {
    // Fetch the current year from the server
    fetch('http://localhost:8080/showCurrentYear')
      .then(response => response.json())
      .then(data => setCurrentYear(data))
      .catch(error => console.error(error));
  }, []);

  return (
    <div>
      <header>
      <h2>Current academic year: {currentYear.yearTitle}</h2>
        
      </header>

      <div className='x1'>
      <main>
      <p>Rules:</p>
        <ul>
          <li>claculate the whole score from 32 points consist of the WE1,WE2,RDD,IT,RP.</li>
          <p></p>
          <p></p>
          <li>For ecah section student must get at least 9 points to effect on the total score. </li>
          <p></p>
          <p></p>
          <li>score of the WE1,W2 for check the effected score consider the score of last call.</li>
          <p></p>
          <p></p>
          <li>The first step is define the new academic year after difine the it's title generate 5 calls for that. </li>
          <p></p>
          <p></p>
          <li>The second step by considering the current academic-year on the home page set the current academic year.</li>
          <p></p>
          <p></p>
          <li>can insert the result by 2 way manually and with excel</li>
          <p></p>
          <p></p>
          <li>can edit the  result information  and student if necessary</li>
          <p></p>
          <p></p>
          <li>in report results you can see in final grade of student</li>


        </ul>
        {/* Your main content goes here */}
        
      </main>
      </div>
    </div>
  );
}

export default Home;
