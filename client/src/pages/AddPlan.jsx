import { RecoilRoot } from 'recoil';
import axios from "axios";
import {Link} from "react-router-dom";
import '../assets/scss/addPlan.scss';
import { useSetRecoilState, useRecolValue } from 'recoil';
import AddPlanForm from "../components/AddPlanForm";

const AddPlan = () => {

    const addPlan = () => {
        axios.post(`http://localhost:8080`);
    }

    return (
        <RecoilRoot>
            <div className='top-bar'></div>
            <AddPlanForm/>
        </RecoilRoot>
    );
}

export default AddPlan;