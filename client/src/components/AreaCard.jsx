import '../assets/scss/areaCard.scss';
import {useNavigate} from "react-router-dom";

const AreaCard = ({data, command}) => {

    const navigate = useNavigate();

    const selectArea = () => {
        //지역 데이터
        const areaData = {
            'placeName': data.place_name,
            'roadAddressName': data.road_address_name,
            'x': data.x,
            'y': data.y
        }
        //도착지
        if (command === 'start') {
            sessionStorage.setItem("endArea", JSON.stringify(areaData));
            let item = sessionStorage.getItem("endArea");
            console.log(item);
        } else {
            sessionStorage.setItem("startArea", JSON.stringify(areaData));
            console.log(areaData)
        }
        navigate(-1); //뒤로가기
    }

    return (
        <div className='card' onClick={selectArea}>
            <div className='content'>
                <div>
                    {data.place_name}
                </div>
                <div className='road-address-name'>
                    {data.road_address_name}
                </div>
            </div>
        </div>
    );
};

export default AreaCard;