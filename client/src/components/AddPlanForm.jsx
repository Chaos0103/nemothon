import {Link} from "react-router-dom";
// import {useRecoilState} from "recoil";

const AddPlanForm = () => {

    // const [formData, setFormData] = useRecoilState(globalState);

    return (
        <div>
            <div className='form'>
                <div>
                    <input type='text' placeholder='일정 제목'/>
                </div>
                <div className='date-input-div'>
                    <input type='datetime-local'/>
                    <input type='datetime-local'/>
                </div>
                {/*출발지, 도착지*/}
                <div>
                    <Link to='/search'>출발지</Link>
                    <br/>
                    <Link to='/search'>도착지</Link>
                </div>
                {/*<div>장소(출발지, 도착지)</div>*/}
            </div>
        </div>
    );
};

export default AddPlanForm;