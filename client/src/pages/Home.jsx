import { Link } from "react-router-dom";
import { useEffect } from "react";
import MonthPlan from "../components/MonthPlan";
import Notification from "../components/Notification";
import TopNav from "../components/TopNav";

const Home = () => {
  useEffect(() => {
    // 로컬스토리지에서 뽑아와서, 토큰으로 사용자 정보를 요청하는 api 함수
    // 리코일, 리덕스
  }, []);

<<<<<<< HEAD
  return (
    <div>
      <TopNav />
      <div className="top-bar"></div>
      <Notification></Notification>
      {/* <Link to="/search">search</Link> */}
      <MonthPlan />
    </div>
  );
=======

    return (
        <div>
            <div className='top-bar'></div>
            {/*<Notification></Notification>*/}
            <MonthPlan/>
        </div>
    );
>>>>>>> a3ff2782a3936dd9512672036d3bcd5c149df17f
};

export default Home;
