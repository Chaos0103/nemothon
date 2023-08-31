import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import './addPlanForm.scss';

const AddPlanForm = () => {
    const navigate = useNavigate();

    const [title, setTitle] = useState("");
    const [startData, setStartData] = useState("");
    const [endData, setEndData] = useState("");
    const [categoryId, setCategoryId] = useState("");
    const [nameOne, setNameOne] = useState(null);
    const [nameTwo, setNameTwo] = useState(null);

    useEffect(() => {
        const title = sessionStorage.getItem('title');
        setTitle(title);
        const startTime = sessionStorage.getItem('startTime');
        setStartData(startData);
        const endTime = sessionStorage.getItem('endTime');
        setEndData(endData);

        const startArea = sessionStorage.getItem('startArea');
        const endArea = sessionStorage.getItem('endArea');
        let start = JSON.parse(startArea);
        let end = JSON.parse(endArea);
        if (start !== null) {
            setNameOne(start.placeName);
        }
        if (end !== null) {
            setNameTwo(end.placeName);
        }


    }, []);

    const startExpectedTime = (startX, startY, endX, endY) => {
        axios.get(`https://apis-navi.kakaomobility.com/v1/directions?origin=${startX},${startY}&destination=${endX},${endY}`, {
            headers: {
                Authorization: `KakaoAK f54a220961d18663f4733831562a4d56`,
            }
        })
            .then((response) => {
                console.log(response);
            });
    };

    const save = () => {
        const startArea = sessionStorage.getItem('startArea');
        const endArea = sessionStorage.getItem('endArea');
        let start = JSON.parse(startArea);
        let end = JSON.parse(endArea);
        startExpectedTime(start.x, start.y, end.x, end.y);
        const json = {
            'title': title,
            'startTime': startData,
            'endTime': endData,
            'goingTime': 1,
            'arrivalName': end.placeName,
            'arrivalLatitude': end.y,
            'arrivalLongitude': end.x,
            'departureLatitude': start.y,
            'departureLongitude': start.x,
            'categoryId': 1
        };

        console.log(json);

        const token = localStorage.getItem("token");
        axios.post("/api/event", json, {
            headers: {
                Authorization: `Bearer ${token}`,
            }
        })
            .then(() => {
                navigate("/month");
            });

    }

    return (
        <div>
            <div className='form'>
                <div>
                    <input type='text' placeholder='일정 제목' value={title} onChange={(e) => {
                        setTitle(e.target.value);
                        sessionStorage.setItem("title", e.target.value);
                    }}/>
                </div>
                <div className='date-input-div'>
                    <input type='datetime-local' onChange={(e) => {
                        setStartData(e.target.value);
                        sessionStorage.setItem("startTime", e.target.value);
                    }}/>
                    <input type='datetime-local' onChange={(e) => {
                        setEndData(e.target.value);
                        sessionStorage.setItem("endTime", e.target.value);
                    }}/>
                </div>
                {/*출발지, 도착지*/}
                <div className='address-div'>
                    <Link to='/search/start'>
                        <input type='text' value={nameOne} className='address' placeholder='출발지'/>
                    </Link>
                    <br/>
                    <Link to='/search/end'>
                        <input type='text' value={nameTwo} className='address' placeholder={'도착지'}/>
                    </Link>
                </div>
            </div>
            <div className='btn-div'>
                <button className='button' onClick={save}>등록</button>
            </div>

        </div>
    );
};

export default AddPlanForm;