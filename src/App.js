import './App.css';
import  '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import ReportResult from './pages/ReportResult';
import { BrowserRouter as Router,Routes,Route} from 'react-router-dom';
import AddResult from './pages/AddResult';
import Home from './pages/Home';
import UniqueLoginForm from './pages/login/login';
import UploadExcel from './pages/ExcelAddResult';
import Report from './pages/ReportGeneralResult';
import StudentInfo from './pages/studentInfo';
import SearchBox from './pages/StudentResult';
import CreateAcademicYear from './pages/createNewAcedemicYear';
import AcademicYearSelector from './pages/setCurrentYear';


function App() {

  return (
    <div className="App">
      <Router>
        <Navbar/>
      <Routes>
      <Route exact path="/Home" element={<Home/>}/>
      <Route exact path="/report" element={<Report/>}/>  
      <Route exact path="/ReportResult" element={<ReportResult/>}/>
      <Route exact path="/addResult" element={<AddResult/>}/>
      <Route exact path="/" element={<UniqueLoginForm/>}/>
      <Route exact path='/uploadExcel' element={<UploadExcel/>}/>
      <Route exact path='/studentInfo' element={<StudentInfo/>}/>
      <Route exact path='/search' element={<SearchBox/>}/>
      <Route exact path='/createNewYear' element={<CreateAcademicYear/>}/>
      <Route exact path='/setCurrentYear' element={<AcademicYearSelector/>}/>
      </Routes>
      </Router>
      
    </div>
  );
}

export default App;
