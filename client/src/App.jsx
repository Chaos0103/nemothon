import './App.css';
import {Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Footer from "./components/Footer";
import './components/footer.css';

const App = () => {
    return (
        <div className="App">
            <div className=''>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/login" element={<Login/>}/>
                </Routes>
            </div>
            <Footer/>
        </div>
    );
};

export default App;
