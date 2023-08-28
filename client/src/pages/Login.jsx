import React, { useState } from 'react';
import axios from 'axios';
import { TextField, Button } from '@mui/material';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loggedIn, setLoggedIn] = useState(false);

    const handelLogin = () => {
        const apiUrl = '백엔드_API_URL';
        // 백에서 아이디 비번 일치 여부 판단
        const requestData = {
            username: username,
            password: password
        };

        // ajax 요청 전송
        axios.post(apiUrl, requestData)
            .then(response => {
                if (response.data.success) {
                    setLoggedIn(true);
                } else {
                    alert('로그인 실패');
                }
            })
            .catch(error => {
                console.error('에러 발생', error);
            });
        
        if (username === 'user' && password === 'password') {
            setLoggedIn(true);
        }
    };

    const handleLogout = () => {
        setLoggedIn(false);
        setUsername('');
        setPassword('');
    }

    if (loggedIn) {
        return (
            <div>
                <h1>Welcome, {username}!</h1>
                <button onClick={handleLogout}>Logout</button>
            </div>
        );
    }

    return (
        <div>
            <h1>Login</h1>
            <TextField 
                id="outlined-basic" 
                label="username" 
                variant="outlined" 
                value={username} 
                onChange={(e) => setUsername(e.target.value)}
            />
            <TextField
                id="outlined-password-input"
                label="Password"
                variant='outlined'
                value={password}
                type="password"
                autoComplete="current-password"
                onChange={(e) => setPassword(e.target.value)}
            />
            <Button onClick={handelLogin} variant="contained">Login</Button>
        </div>
    );
};

export default Login;