import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import { useNavigate } from "react-router-dom";

const AdminPage = () => {

  const navigate = useNavigate();

  const goToUsersPage = () => {
    navigate("/adminUsersPage")
  };

  const goToBooksPage = () => {
    navigate("/adminBooksPage")
  };

 
  return (
    <div>
      <h1>AdminPage</h1>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'row',
          alignItems: 'flex-start',
          marginLeft: 2,
          backgroundImage: 'url("book-background-old-books-library-600nw-2187036905.jpg")', 
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat', 
          height: '100vh',
          margin: 0,
          color:'white'
        }}
      >
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '50px' }}>
        <Button variant="contained" color="primary" onClick={goToUsersPage}  sx={{ fontSize: '1.5rem' }}>
          UsersPage
        </Button>
        <Button variant="contained" color="primary" onClick={goToBooksPage} style={{ marginTop: '20px' }}  sx={{ fontSize: '1.5rem' }}>
          BooksPage
        </Button>
      </Box>
      </Box>
    </div>
  );
};

export default AdminPage;
