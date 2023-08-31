// import React, { useEffect, useState } from "react";
// import { toast, ToastContainer } from "react-toastify";
// import "react-toastify/dist/ReactToastify.css";
// import { EventSourcePolyfill } from "event-source-polyfill";
//
// const Notification = () => {
//   const [userId, setUserId] = useState("");
//   // TODO: userId의 정보를 어디서 가져올지를 생각해봐야할 듯
//
//   useEffect(() => {
//     // const eventSource = new EventSource("http://localhost:8080/notifications/subscribe/1");
//
//     // const eventSource = new EventSource("http://localhost:8080/notifications/subscribe/" + userId);
//
//     const eventSource = new EventSourcePolyfill(`http://localhost:8080/notifications/subscribe`, {
//       headers: {
//         "Access-Control-Allow-Origin": "http://localhost:3000/",
//         "Access-Control-Allow-Headers": "Authorization",
//         Authorization: localStorage.getItem("token"),
//       },
//     });
//
//     eventSource.addEventListener("sse", (event) => {
//       if (event.data !== "EventStream Created. [userId=1]") {
//         console.log(event.data);
//
//         // 알림 보여주기
//         toast(event.data, {
//           position: toast.POSITION.TOP_RIGHT,
//         });
//       }
//     });
//
//     return () => {
//       eventSource.close();
//     };
//   }, []);
//
//   return (
//     <div>
//       <ToastContainer
//         position="top-right" // 알람 위치 지정
//         autoClose={5000} // 자동 off 시간
//         hideProgressBar={false} // 진행시간바 숨김
//         closeOnClick // 클릭으로 알람 닫기
//         rtl={false} // 알림 좌우 반전
//         pauseOnFocusLoss // 화면을 벗어나면 알람 정지
//         draggable // 드래그 가능
//         pauseOnHover // 마우스를 올리면 알람 정지
//         theme="light"
//         limit={1} // 알람 개수 제한
//         width="1"
//       />
//     </div>
//   );
// };
//
// export default Notification;
