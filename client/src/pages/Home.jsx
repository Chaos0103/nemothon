import {Link} from "react-router-dom";
import DailyPlan from "../components/DailyPlan";
import {useEffect} from "react";
import MonthPlan from "../components/MonthPlan";

const Home = () => {
    useEffect(() => {
        // 로컬스토리지에서 뽑아와서, 토큰으로 사용자 정보를 요청하는 api 함수
        // 리코일, 리덕스
    }, []);
    return (
        <div>
            <br/><br/><br/>
            <Link to="/login">login</Link>

            <Link to="/search">search</Link>

            {/*<DailyPlan/>*/}
            <MonthPlan/>

            <header className="App-header">
                <h1>test</h1>
            </header>
        </div>
    );
};

export default Home;