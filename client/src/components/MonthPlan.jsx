import React, { useEffect, useState } from "react";

import Calendar from "@toast-ui/react-calendar";
import "tui-calendar/dist/tui-calendar.css";

import "tui-date-picker/dist/tui-date-picker.css";
import "tui-time-picker/dist/tui-time-picker.css";

import "@toast-ui/calendar/dist/toastui-calendar.min.css";
import axios from "axios";

// import TopNav from "./TopNav";

class MonthPlan extends React.Component {
  constructor() {
    super();
    this.state = {
      events: [],
    };
  }

  calendarRef = React.createRef();

  async componentDidMount() {
    const calendarInstance = this.calendarRef.current.getInstance();
    const start = toStringByFormatting(calendarInstance.getDateRangeStart());
    const end = toStringByFormatting(calendarInstance.getDateRangeEnd());
    console.log("componentDidMount 실행 달력 표시날짜 = ", start, end);
    let token = localStorage.getItem("token");

    //일정 부분 완료됨
    await axios
      .get(`/api/event/month/${start}_${end}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        console.log("이벤트 목록 =", response.data.data);
        this.state.events = response.data.data;
        // this.setState({events: response.data.data});
      })
      .then(() => {
        calendarInstance.createEvents(this.state.events);
        console.log("스테이트 저장함 이벤트 목록 =", this.state.events);
      });
  }

  render() {
    return (
      <div>
        <div className="top-bar"></div>
        <Calendar
          ref={this.calendarRef}
          view={"month"}
          height="700px"
          // calendars={[
          //     {
          //         id: '0',
          //         name: 'Private',
          //         bgColor: '#9e5fff',
          //         borderColor: '#9e5fff'
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
          //         calendarId: 'cal2',
          //         title: '주간 회의',
          //         start: '2023-08-31T09:00:00',
          //         end: '2023-08-31T10:00:00',
          //         goingDuration: 70,
          //     }]
          // }
          useDetailPopup={true}
          week={{
            // dayNames: ["일", "월", "화", "수", "목", "금", "토"],
            taskView: false,
          }}
        />
        {/*<AddCategorySheet/>*/}
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

function toStringByFormatting(source, delimiter = "-") {
  const year = source.getFullYear();
  const month = leftPad(source.getMonth() + 1);
  const day = leftPad(source.getDate());

  return [year, month, day].join(delimiter);
}
export default MonthPlan;
