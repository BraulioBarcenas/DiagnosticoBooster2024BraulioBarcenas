import './App.css';
import '@fontsource/inter';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Main from './routes/Main';
import Attendance from './routes/Attendance';

function App() {


  
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Main></Main>}></Route>
        <Route path='/attendance/:id' element={<Attendance></Attendance>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
