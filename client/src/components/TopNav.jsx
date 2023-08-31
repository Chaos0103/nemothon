import './topNav.scss';
import {Link} from "react-router-dom";

const TopNav = () => {
    return (
        <section className='top-section header'>
            <header>
                <div className='top-bar'>
                    <div className='tab title'>8월</div>
                    <div className='tab add-icon'>
                        <Link to='/add'>
                            <div className='plus-link plus-background-img'></div>
                        </Link>
                    </div>
                </div>
            </header>
        </section>
    );
};

export default TopNav;