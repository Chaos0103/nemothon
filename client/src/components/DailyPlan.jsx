import React, {useState} from "react";

import Calendar from '@toast-ui/react-calendar';
import 'tui-calendar/dist/tui-calendar.css';

import 'tui-date-picker/dist/tui-date-picker.css';
import 'tui-time-picker/dist/tui-time-picker.css';
//         // eventView:  'task',
//     },
//     calendars: [
//         {
//             id: 'cal1',
//             name: '개인',
//             backgroundColor: '#03bd9e',
//         },
//         {
//             id: 'cal2',
//             name: '직장',
//             backgroundColor: '#00a9ff',
//         },
//     ],
// };
//
// const container = document.getElementById('calendar');
// const calendar = new Calendar(container, options);
//
// calendar.render();
//
// calendar.createEvents([
//     {
//         id: 'event1',
//         calendarId: 'cal2',
//         title: '주간 회의',
//         start: '2023-08-29T09:00:00',
//         end: '2023-08-29T10:00:00',
//         goingDuration: 70,
//     },
//     {
//         id: 'event2',
//         calendarId: 'cal1',
//         title: '점심 약속',
//         start: '2023-08-29T12:00:00',
//         end: '2023-08-29T13:00:00',
//     },
// ]);

const DailyPlan = () => {
    // docutc. ~~~~ 지양
    // 요소선택 = useRef

    return (
        <div>
            {/*<div id="calendar" style={{height: "600px"}}></div>*/}
            <Calendar
                view={"day"}
                height="700px"
                calendars={[
                    {
                        id: '0',
                        name: 'Private',
                        bgColor: '#9e5fff',
                        borderColor: '#9e5fff'
                    },
                    {
                        id: '1',
                        name: 'Company',
                        bgColor: '#00a9ff',
                        borderColor: '#00a9ff'
                    }
                ]}
                isReadOnly={true}
                events={[
                    {
                        id: 'event1',
                        calendarId: 'cal2',
                        title: '주간 회의',
                        start: '2023-08-31T09:00:00',
                        end: '2023-08-31T10:00:00',
                        goingDuration: 70,
                    }]
                }
            />
        </div>
    )
}

export default DailyPlan;