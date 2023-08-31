import './bottomNav.css'
import calendar from '../images/calendar-date.svg'
import person from '../images/person-fill.svg';
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";

const BottomNav = () => {

    const [login, setLogin] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            setLogin(true);
        } else {
            setLogin(false);
        }
    }, []);
    if (login) {
        return (
            <aside className='footer'>
                <nav>
                    <Link to='/daily' className='link'>
                        <div className='tab-daily'>
                            <img src={calendar} className='tab-daily-icon' alt='test'/>
                            <span className='tab-daily-label'>daily</span>
                        </div>
                    </Link>
                    <div className='logo'>
                        <Link to='/month'>
                            <div className='logo-link logo-background-img'></div>
                        </Link>
                    </div>
                    <Link to='/my' className='link'>
                        <div className='tab-people'>
                            <img src={person} className='tab-people-icon' alt='test'/>
                            <span className='tab-people-label'>my</span>
                        </div>
                    </Link>
                </nav>
            </aside>
        );
    } else {
        return null;
    }

};

export default BottomNav;