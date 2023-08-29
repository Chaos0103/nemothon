import './App.css';
import {Route, Routes} from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import BottomNav from "./components/BottomNav";
import './components/bottomNav.css';
import DailyPlan from "./components/DailyPlan";
import MyPage from "./pages/MyPage";

const App = () => {
    return (
        <div className="App">
            <div className='wrapper'>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/daily" element={<DailyPlan/>}/>
                    <Route path="/my" element={<MyPage/>}/>
                </Routes>
            </div>
            <BottomNav/>
        </div>
    );
};

export default App;
