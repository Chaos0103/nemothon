import {atom} from "recoil";

const globalState = atom({
    key: 'globalState',
    default: 'blah',
});