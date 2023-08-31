import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

const AddPlanForm = () => {

    const [title, setTitle] = useState("");
    const [startData, setStartData] = useState("");
    const [endData, setEndData] = useState("");
    const [categoryId, setCategoryId] = useState("");

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
        // startExpectedTime(start.x, start.y, end.x, end.y);
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
                <div>
                    <Link to='/search/start'>출발지</Link>
                    <br/>
                    <Link to='/search/end'>도착지</Link>
                </div>
                <div>
                    <select>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                    </select>
                </div>
                {/*<div>장소(출발지, 도착지)</div>*/}
            </div>
            <button onClick={save}>버튼</button>
        </div>
    );
};

export default AddPlanForm;