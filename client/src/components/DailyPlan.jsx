import React, {useState} from "react";

import Calendar from '@toast-ui/calendar';
/* Node.js 환경에서 ES6 모듈 */
import '@toast-ui/calendar/dist/toastui-calendar.min.css'; // Calendar 스타일


const options = {
    defaultView: 'day',
    timezone: {
        zones: [
            {
                timezoneName: 'Asia/Seoul',
                displayLabel: 'Seoul',
            },
        ],
    },
    calendars: [
        {
            id: 'cal1',
            name: '개인',
            backgroundColor: '#03bd9e',
        },
        {
            id: 'cal2',
            name: '직장',
            backgroundColor: '#00a9ff',
        },
    ],
};

const container = document.getElementById('calendar');
const calendar = new Calendar(container, options);

// calendar.render();

calendar.createEvents([
    {
        id: 'event1',
        calendarId: 'cal2',
        title: '주간 djWjrn',
        start: '2023-08-28T09:00:00',
        end: '2023-08-28T10:00:00',
    },
    {
        id: 'event2',
        calendarId: 'cal1',
        title: '점심 약속',
        start: '2023-08-28T12:00:00',
        end: '2023-08-28T13:00:00',
    },
    {
        id: 'event3',
        calendarId: 'cal2',
        title: '휴가',
        start: '2022-06-08',
        end: '2022-06-10',
        isAllday: true,
        category: 'allday',
    },
]);

const DailyPlan = () => {
    // docutc. ~~~~ 지양
    // 요소선택 = useRef

    return (
        <div>
            <div id="calendar" style={{height: "600px"}}></div>
        </div>
    )
}

export default DailyPlan;