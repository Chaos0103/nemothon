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

export default AreaCard;