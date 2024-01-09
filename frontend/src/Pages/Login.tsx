    import * as React from 'react';
    import Avatar from '@mui/material/Avatar';
    import Button from '@mui/material/Button';
    import CssBaseline from '@mui/material/CssBaseline';
    import TextField from '@mui/material/TextField';
    import Grid from '@mui/material/Grid';
    import Box from '@mui/material/Box';
    import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
    import Typography from '@mui/material/Typography';
    import Container from '@mui/material/Container';
    import { createTheme, ThemeProvider } from '@mui/material/styles';
import axios from 'axios';

import { useNavigate } from "react-router-dom";

    const defaultTheme = createTheme();

    interface User {
        id:number;
        username: String;
        password: String; 
    
      }

    const SignIn = () => {

    const [username, setEmail] = React.useState<string>("");
    const onChangeEmail = (event: any): void => {
        setEmail(event.target.value)
    }
    
    const [password, setPassword] = React.useState<string>("");
    const onChangePassword = (event: any): void => {
        setPassword(event.target.value)
    }
    
    const navigate = useNavigate();

    const onInsert = (event: any): void => {
        console.log(username)
        console.log(password)
    
        const User = {
          
          username: username,
          password: password
        }
    
        axios
          .post("http://localhost:8080/users/insert", User, {
            headers: {"Content-Type": "application/json"}
          })
          .then((response: any): void => {
            console.log(response);
            if (response.data === "Account Created") {
              alert("Account Created");
            } else {
              alert("Error: " + response.data);
            }
          })
          .catch((error) => {
            console.error(error.response.data);
            alert('Error: ' + error.response.data);
          });
      }

      const onSignIn = (event: any): void => {
        console.log(username)
        console.log(password)
    
        const User = {
          
          username: username,
          password: password
        }
    
        axios
        .post("http://localhost:8080/users/signIn", User, {
          headers: { "Content-Type": "application/json" },
        })
        .then((response: any): void => {
          console.log(response);
          console.log("SignIn Successful");
         
           const userRole = response.data;

           if (userRole === "admin") {
               navigate("/adminPage");
           } else if (userRole === "user") {
               navigate("/userPage");
           }
         
        })
        .catch((error) => {
          console.error(error.response.data);
          alert(`Error: ${error.response.data}`);
        });
      
    
      }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        console.log({
        email: data.get('email'),
        password: data.get('password'),
        });
    };

    return (
        <ThemeProvider theme={defaultTheme}>
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <Box
            sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
            }}
            >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
                Sign in
            </Typography>
            <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                <TextField
                margin="normal"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                autoFocus
                onChange={onChangeEmail}
                />
                <TextField
                margin="normal"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                onChange={onChangePassword}
                />
                
                <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                onClick={onSignIn}
                >
                Sign In
                </Button>
                <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                onClick={onInsert}
                >
                Create Account
                </Button>
                <Grid container>
                <Grid item>
                   
                </Grid>
                </Grid>
            </Box>
            </Box>
          
        </Container>
        </ThemeProvider>
    );
    }
    export default SignIn;