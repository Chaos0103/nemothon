import React, {useState} from "react";
import axios from "axios";
import {TextField, Button, Alert, Container, Box, Grid} from "@mui/material";
import {useNavigate} from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    // const [emptyInput, setEmptyInput] = useState(false);
    const [loggedIn, setLoggedIn] = useState(false);
    // const [loggedSuccess, setLoggedSuccess] = useState(true);

    // const handelLogin = () => {
    //   if (username.length === 0 || password.length === 0) {
    //     setLoggedSuccess(true);
    //     setEmptyInput(true);
    //   } else {
    //     setEmptyInput(false);
    //     const apiUrl = "백엔드_API_URL";
    //     // 백에서 아이디 비번 일치 여부 판단
    //     const requestData = {
    //       username: username,
    //       password: password,
    //     };
    //
    //     // ajax 요청 전송
    //     axios
    //       .post(apiUrl, requestData)
    //       .then((response) => {
    //         if (response.data.success) {
    //           // 로컬스토리지에 저장하는 로직
    //           // localStorage.setItem("AccessToken", token)
    //           setLoggedIn(true);
    //           setLoggedSuccess(true);
    //         } else {
    //           setLoggedSuccess(false);
    //           alert("로그인 실패");
    //         }
    //       })
    //       .catch((error) => {
    //         setLoggedSuccess(false);
    //         console.error("에러 발생", error);
    //       });
    //
    //     if (username === "user" && password === "password") {
    //       setLoggedIn(true);
    //       setLoggedSuccess(true);
    //     }
    //   }
    // };

    const login = () => {

        const json  = {
            'email': email,
            'password': password
        }

        console.log(json);

        axios
            .post('http://localhost:8080/login', json)
            .then((response) => {
                const tokenInfo = response.data.data;
                localStorage.setItem("token", tokenInfo.accessToken);
                console.log(tokenInfo);
                setLoggedIn(true);
            })
    };

    if (loggedIn) {
        navigate("/", {state: {email: email}});
    }

    return (
        <Container>
            <Box
                sx={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    alignItems: "center",
                    alignContent: "center",
                }}
            >
                <h1>Login</h1>
                {/*<input onChange={(e) => setUsername()}></input>*/}
                <Grid xs={8} sx={{m: 2}}>
                    <TextField
                        id="outlined-basic"
                        label="username"
                        variant="outlined"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </Grid>
                <Grid xs={8} sx={{m: 2}}>
                    <TextField
                        id="outlined-password-input"
                        label="Password"
                        variant="outlined"
                        value={password}
                        type="password"
                        autoComplete="current-password"
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Grid>
                <Grid xs={8} sx={{mt: 0.5, mb: 2}}>
                    <Button onClick={login} variant="contained">
                        Login
                    </Button>
                </Grid>
                {/*<div xs={8} sx={{m: 2}}>*/}
                {/*    {emptyInput && <Alert severity="info">empty info</Alert>}*/}
                {/*</div>*/}
                {/*<div xs={8} sx={{m: 2}}>*/}
                {/*    {!loggedSuccess && <Alert severity="warning">login failed</Alert>}*/}
                {/*</div>*/}
            </Box>
        </Container>
    );
};

export default Login;
