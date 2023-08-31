import '../assets/scss/areaSearch.scss'
import {useState} from "react";
import axios from "axios";
import AreaCard from "../components/AreaCard";

const AreaSearch = () => {

    const [keyword, setKeyword] = useState('');
    const [responses, setResponses] = useState([]);

    const pressEnterKey = e => {
        if (e.key === 'Enter') {
            searchArea();
        }
    };

    const searchArea = () => {
        axios
            .get(`https://dapi.kakao.com/v2/local/search/keyword?query=${keyword}`,
                {
                    headers: {
                        Authorization: `KakaoAK f54a220961d18663f4733831562a4d56`
                    }
                })
            .then(response => {
                console.log(keyword);
                setResponses(response.data.documents);
                console.log(response.data.documents);
                console.log(responses.length);
            });
    }

    return (
        <div>
            <div className='top-bar'></div>
            <div className='form'>
                <input type='text' value={keyword} onChange={e => setKeyword(e.target.value)} onKeyUp={pressEnterKey}/>
            </div>
            <div className='result-div'>
                <div className='result'>장소결과</div>
            </div>
            <div>
                {responses.map((response) => <AreaCard key={response.id} data={response}/>)}
            </div>
        </div>
    );
};

export default AreaSearch;