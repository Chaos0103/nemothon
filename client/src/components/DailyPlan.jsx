import React, {useEffect, useState} from "react";

import Calendar from '@toast-ui/react-calendar';
import 'tui-calendar/dist/tui-calendar.css';

import 'tui-date-picker/dist/tui-date-picker.css';
import 'tui-time-picker/dist/tui-time-picker.css';
import axios from "axios";

class DailyPlan extends React.Component {
    // const [categories, setCategories] = useState([]);

    // state = {
    //     events: []
    // }

    calendarRef = React.createRef();

    constructor() {
        super();
        // axios.get(`http://localhost:8080/api/event/day/2023-08-31`, {
        //     headers: {
        //         Authorization: localStorage.getItem("token")
        //     }
        // })
        //     .then((response) => {
        //         console.log("이벤트 목록 = ", response.data)
        //         setEvents(response.data);
        //     });
        this.state = {
            events: [],
            CalendarInfo: []
        }
    }


    handleClickNextButton = () => {
        const calendarInstance = this.calendarRef.current.getInstance();

        calendarInstance.next();
    };

    handleClickPrevButton = () => {
        const calendarInstance = this.calendarRef.current.getInstance();

        calendarInstance.prev();
        let showDate = calendarInstance.getDate().toUTCString();
        console.log("표시날짜 = {}", showDate);
    }

    async componentDidMount() {
        const calendarInstance = this.calendarRef.current.getInstance();
        //TODO: 상단바 동기 변경
        const showDate = toStringByFormatting(calendarInstance.getDate());
        console.log("componentDidMount 실행 달력 표시날짜 = ", showDate);
        let token = localStorage.getItem("token");

        // 카테고리 가져오기
        // await axios
        //     .get(`/api/categories`, {
        //         headers: {
        //             Authorization: `Bearer ${token}`
        //         }
        //     }).then((response) => {
        //         console.log("등록된 카테고리 불러옴", response.data.data)
        //         this.state.calendars = response.data.data;
        //         //카테고리 여러개 왔음
        //         response.data.data.map(function (i) {
        //             //id:String, name:String 으로 CalendarInfo 만듦
        //             this.state.CalendarInfo.push()
        //             //CalendarInfo 배열을 아래코드로 라이브러리에 등록
        //             // calendarInstance.setCalendars(this.state.CalendarInfo)
        //         })
        //         //반복 끝나면 다시 배열 돌리면서 색상 정해줘야함
        //         response.data.data.map(function (i) {
        //             calendarInstance.setCalendarColor(this.state.CalendarInfo.id, "#" + response.data.data.color);
        //         })
        //     });

        //일정 부분 완료됨
        await axios
            .get(`/api/event/day/${showDate}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            .then((response) => {
                console.log("이벤트 목록 =", response.data.data)
                this.state.events = response.data.data;
                // this.setState({events: response.data.data});
            }).then(() => {
                calendarInstance.createEvents(this.state.events);
                console.log("스테이트 저장함 이벤트 목록 =", this.state.events)
            })
        // console.log(this.state.events);

    }

    render() {
        return (
            <div>
                <div className='top-bar'></div>
                <Calendar
                    ref={this.calendarRef}
                    view={"day"}
                    height="700px"
                    // calendars={[
                    //     {
                    //         id: '0',
                    //         name: 'Private',
                    //         bgColor: '#9e5fff',
                    //         borderColor: '#9e5fff',
                    //         color: '#9e5fff'
                    //     },
                    //     {
                    //         id: '1',
                    //         name: 'Company',
                    //         bgColor: '#00a9ff',
                    //         borderColor: '#00a9ff'
                    //     }
                    // ]}
                    isReadOnly={true}
                    // events={[
                    //     {
                    //         id: 'event1',
                    //         calendarId: '0',
                    //         title: '주간 회의',
                    //         start: '2023-08-31T09:00:00',
                    //         end: '2023-08-31T10:00:00',
                    //         goingDuration: 70,
                    //     },
                    //     {
                    //         id: 'event1',
                    //         calendarId: '0',
                    //         title: '네모톤 참여',
                    //         start: '2023-09-01T09:00:00',
                    //         end: '2023-09-01T14:00:00',
                    //         goingDuration: 20,
                    //     },
                    //     {
                    //         id: 'event1',
                    //         calendarId: 'cal2',
                    //         title: '인천에서 놀기',
                    //         start: '2023-09-01T18:00:00',
                    //         end: '2023-09-01T20:00:00',
                    //         goingDuration: 120,
                    //     }]
                    // }
                    useDetailPopup={true}
                    week={{
                        // dayNames: ["일", "월", "화", "수", "목", "금", "토"],
                        taskView: false
                    }}
                />
                <button onClick={this.handleClickPrevButton}>prev</button>
                <button onClick={this.handleClickNextButton}>next</button>
            </div>
        );
    }
}

function leftPad(value) {
    if (value >= 10) {
        return value;
    }

    return `0${value}`;
}

function toStringByFormatting(source, delimiter = '-') {
    const year = source.getFullYear();
    const month = leftPad(source.getMonth() + 1);
    const day = leftPad(source.getDate());

    return [year, month, day].join(delimiter);
}

export default DailyPlan;