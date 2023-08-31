import React, { useState } from "react";
import axios from "axios";
import { TextField, Button, Container, Box, Grid } from "@mui/material";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loggedIn, setLoggedIn] = useState(false);

  const login = () => {
    const json = {
      email: email,
      password: password,
    };

    console.log(json);

    axios.post("/api/login", json).then((response) => {
      const tokenInfo = response.data.data;
      localStorage.setItem("token", tokenInfo.accessToken);
      console.log(tokenInfo);
      setLoggedIn(true);
    });
  };

  if (loggedIn) {
    navigate("/month", { state: { email: email } });
  }

  return (
    <div>
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
          <h1>login</h1>
          <Grid xs={8} sx={{ m: 2 }}>
            <TextField
              id="outlined-basic"
              label="username"
              variant="outlined"
              value={email}
              type="textfield"
              onChange={(e) => setEmail(e.target.value)}
            />
          </Grid>
          <Grid xs={8} sx={{ m: 2 }}>
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
          <Grid xs={8} sx={{ mt: 0.5, mb: 2 }}>
            <Button onClick={login} variant="contained">
              Login
            </Button>
          </Grid>
        </Box>
      </Container>
    </div>
  );
};

export default Login;
