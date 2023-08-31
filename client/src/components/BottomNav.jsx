import './bottomNav.css'
import calendar from '../images/calendar-date.svg'
import person from '../images/person-fill.svg';
import {Link} from "react-router-dom";

const BottomNav = () => {
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
                    <Link to='/'>
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
};

export default BottomNav;