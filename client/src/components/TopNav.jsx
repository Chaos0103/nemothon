import '../assets/scss/bottomSheet.scss';
import './topNav.scss';
import {Link} from "react-router-dom";
import Sheet from "react-modal-sheet";
import {useEffect, useState} from "react";
import axios from "axios";

const TopNav = () => {

    const [isOpen, setOpen] = useState(false);
    const [login, setLogin] = useState(false);
    const [category, setCategory] = useState('');

    const addCategory = () => {
        axios.post(`http://localhost:8080/categories/add`)
    }

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
            <section className='top-section header'>
                <header>
                    <div className='top-bar'>
                        <div className='tab title'>9월</div>
                        <div className='tab add-icon'>
                            <Link to='/add'>
                                <div className='plus-link plus-background-img'></div>
                            </Link>
                        </div>
                    </div>
                </header>
            </section>
        );
    } else {
        return null;
    }

};

export default TopNav;