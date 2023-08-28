import {Link} from "react-router-dom";

const Home = () => {
    return (
        <div>
            <Link to="/login">login</Link>
            <header className="App-header">
                <h1>test</h1>
            </header>
        </div>
    );
};

export default Home;