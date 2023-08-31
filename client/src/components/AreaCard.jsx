import '../assets/scss/areaCard.scss';

const AreaCard = ({data}) => {

    const selectArea = () => {
        console.log("select");
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
            {/*<p>{data.x}</p>*/}
            {/*<p>{data.y}</p>*/}
        </div>
    );
};

{/*
            id: number
address_name: "서울 송파구 잠실동 40-1"
lace_name: "롯데월드"
road_address_name: "서울 송파구 올림픽로 240"
x, y
*/
}
export default AreaCard;