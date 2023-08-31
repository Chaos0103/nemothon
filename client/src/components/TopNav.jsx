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
                            <div onClick={() => setOpen(true)}>
                                <div className='tag-icon tag-background-img'></div>
                            </div>
                        </div>
                    </div>
                </header>
                <Sheet isOpen={isOpen} onClose={() => setOpen(false)}>
                    <Sheet.Container>
                        <Sheet.Header/>
                        <Sheet.Content>
                            <div className='title-div'>
                                <div className='title'>새 카테고리 만들기</div>
                            </div>
                            <div className='input-form'>
                                <input type='text' placeholder='카테고리 이름'/>
                            </div>
                        </Sheet.Content>
                    </Sheet.Container>
                    <Sheet.Backdrop/>
                </Sheet>
            </section>
        );
    } else {
        return null;
    }

};

export default TopNav;