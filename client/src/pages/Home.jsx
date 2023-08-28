import {Link} from "react-router-dom";
import DailyPlan from "../components/DailyPlan";

const Home = () => {
    return (
        <div>
            <Link to="/login">login</Link>
            <DailyPlan/>
            <header className="App-header">
                <h1>test</h1>
            </header>
        </div>
    );
};

export default Home;