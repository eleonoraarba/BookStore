import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import { DataGrid, GridColDef} from '@mui/x-data-grid';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Button from '@mui/material/Button';
import axios from 'axios';

interface User {
  id:number;
  username: String;
  password: String; 
  
}

const AdminUsersPage = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [selectedUser, setselectedUser] = React.useState<User | null>(null);

  const handleSelectionChange = (selectionModel: any): void => {
    if (selectionModel.length > 0) {
      const selectedUserId = selectionModel[0]; 
      const user = users.find((c) => c.id === selectedUserId) || null;
      setselectedUser(user);
      console.log( user);
    } else {
      setselectedUser(null);
      console.log(null);
    }
  };

  const [username, setUsername] = React.useState<string>("");
  const onChangeUsername = (event: any): void => {
      setUsername(event.target.value)
  }
  
  const [password, setPassword] = React.useState<string>("");
  const onChangePassword = (event: any): void => {
      setPassword(event.target.value)
  }

  const [admin, setAdmin] = React.useState<boolean>(false);

  const onChangeAdmin = (event: any): void => {
      setAdmin(event.target.checked)
  };

  const AddUser = (event: any): void => {
    const newUser = {
      username: username,
      password: password,
    };
  
    axios
     .post(`http://localhost:8080/adminPage/insertUser?admin=${admin}`, newUser, {
        headers: { "Content-Type": "application/json" },
      })
      .then((response: any): void => {
        console.log(response);
        alert("Inserted Successfully");
      })
      .catch((error) => {
        console.error(error);
        const errorMessage = error.response.data.message || 'Unknown error';
        alert(`Error: ${errorMessage}`);
      });
  };
  

  useEffect(() => {
    axios.get('http://localhost:8080/adminPage/showUsers')
      .then((response) => {
        setUsers(response.data);
      })
      .catch((error) => {
        console.error('Error fetching users:', error);
      });
  }, []);

  const columns: GridColDef[] = [
    { field: 'id', headerName: 'ID', width: 90 },
    { field: 'username', headerName: 'Username', width: 150 },
    { field: 'password', headerName: 'Password', width: 150 },
  
  ];

  const DeleteUser = () => {
    if (selectedUser !== null) {
      const userId = selectedUser.id;

      axios
        .post(`http://localhost:8080/adminPage/deleteUser?id=${userId}`)
        .then((response) => {
          console.log(response);
          alert('Deleted Successfully');
        })
        .catch((error) => {
          console.error(error);
          const errorMessage = error.response?.data?.message || 'Unknown error';
          alert(`Error: ${errorMessage}`);
        });
    }
    else{
      alert(`Error: "Select an user!"`);
     }
    }
  const [usernameUpdate, setUsernameU] = React.useState<string>("");
  const onChangeUsernameUpdate = (event: any): void => {
      setUsernameU(event.target.value)
  }
  
  const [passwordUpdate, setPasswordU] = React.useState<string>("");
  const onChangePasswordUpdate = (event: any): void => {
      setPasswordU(event.target.value)
  }

  const [adminUpdate, setAdminU] = React.useState<boolean>(false);
  const onChangeAdminUpdate = (event: any): void => {
    setAdminU(event.target.checked);
  };

  const UpdateUser = (event: any): void => {
    if (selectedUser !== null) {
        const userId = selectedUser.id;
    
        const updatedUser = {
          username: usernameUpdate,
          password: passwordUpdate,
        };
        axios
        .post(`http://localhost:8080/adminPage/updateUser?id=${userId}&admin=${adminUpdate}`,updatedUser, {
          headers: { "Content-Type": "application/json" },
        })
        .then((response) => {
          console.log(response);
          alert('Updated Successfully');
        })
        .catch((error) => {
          console.error(error);
          const errorMessage = error.response?.data?.message || 'Unknown error';
          alert(`Error: ${errorMessage}`);
        });   
  }
  else {
    alert(`Error: "Select an user!"`);
  }
}
  return (
    <div>
      <h1>Users Page</h1>
      
      <Box sx={{ height: 400, width: '100%' }}>
        <DataGrid rows={users} columns={columns} autoPageSize onRowSelectionModelChange={handleSelectionChange}/>
      </Box>
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-start', marginBottom: 2 }}>
        <TextField
          label="Username"
          name="username"
          variant="outlined"
          margin="normal"
          onChange={onChangeUsername}
          sx={{ marginRight: 5 }}
        />
        <TextField
          label="Password"
          name="password"
          variant="outlined"
          margin="normal"
          onChange={onChangePassword}
          sx={{ marginRight: 5 }}
        />
        <FormControlLabel
          control={<Checkbox name="admin" onChange={onChangeAdmin} />}
          label="Admin"
          sx={{ marginRight:5 }}
        />
        <Button variant="contained" style={{ backgroundColor: 'green', color: 'white' }} onClick={AddUser}>
          Add User
        </Button>
      </Box>
      <Box sx={{ marginLeft:50, marginTop:"-53px", marginRight: 5 }}>
      <Button variant="contained" style={{ backgroundColor: 'red', color: 'white' }}  onClick={DeleteUser}>
        Delete User
      </Button>
      </Box >
      <Box  sx={{ display: 'flex', marginTop:"-240px", flexDirection: 'column', marginLeft: 85 }}>
      <TextField
          label="Update Username"
          name="usernameUpdate"
          variant="outlined"
          margin="normal"
          onChange={onChangeUsernameUpdate}
          sx={{ marginRight: 35 }}
        />
        <TextField
          label="Update Password"
          name="passwordUpdate"
          variant="outlined"
          margin="normal"
          onChange={onChangePasswordUpdate}
          sx={{ marginRight: 35 }}
        />
        <FormControlLabel
          control={<Checkbox name="adminUpdate" onChange={onChangeAdminUpdate} style={{ margin: '0' }}  />}
          label="Admin"
          
        />
        <Button variant="contained" style={{ backgroundColor: 'green', color: 'white', width:150 }} onClick={UpdateUser}>
          Update User
        </Button>
      </Box>
    </div>
  );
}


export default AdminUsersPage;