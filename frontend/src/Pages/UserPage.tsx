import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import axios from 'axios';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

interface Book {
  id: number;
  title: string;
  author: string;
  publishedDate: string;
  stock: number;
  price: number;
}

const UserPage = () => {
  const columns: GridColDef[] = [
    { field: 'id', headerName: 'ID', width: 90 },
    { field: 'title', headerName: 'Title', width: 150 },
    { field: 'author', headerName: 'Author', width: 150 },
    { field: 'publishedDate', headerName: 'Published Date', width: 160 },
    { field: 'stock', headerName: 'Stock', type: 'number', width: 110 },
    { field: 'price', headerName: 'Price', type: 'number', width: 110 },
  ];

  const [books, setBooks] = useState<Book[]>([]);
  const [selectedBook, setSelectedBook] = React.useState<Book | null>(null);
  const [quantity, setQuantity] = useState<number>(0);
  const [total, setTotal] = useState<number>(0);

  useEffect(() => {
    axios.get('http://localhost:8080/adminPage/showBooks')
      .then((response) => {
        setBooks(response.data);
      })
      .catch((error) => {
        console.error('Error fetching books:', error);
      });
  }, []);

  const handleSelectionChange = (selectionModel: any): void => {
    if (selectionModel.length > 0) {
      const selectedBookId = selectionModel[0];
      const book = books.find((c) => c.id === selectedBookId) || null;
      setSelectedBook(book);
      console.log("Selected book", book);
    } else {
      setSelectedBook(null);
      alert("No row selected");
    }
  };

  const handleQuantityChange = (event: React.ChangeEvent<HTMLInputElement>): void => {
    const newQuantity = parseInt(event.target.value, 10) || 0;
    setQuantity(newQuantity);
  };


  const addToCart = (): void => {
    if (selectedBook && quantity > 0 && quantity < selectedBook.stock) {
    
      axios.post(`http://localhost:8080/storePage/addToCart?quantity=${quantity}`, selectedBook)
        .then((response) => {
          alert(response.data); 
        })
        .catch((error) => {
          console.error('Error adding to cart:', error);
        });
    } else if(selectedBook===null) {
      alert("Add a book to the cart!!");
    }
    else if(quantity > selectedBook.stock){
      alert("Quantity exceeds available stock!");
    }
  };
  

  const calculateTotal = (): void => {
    axios.get('http://localhost:8080/storePage/Total')
      .then((response) => {
        setTotal(response.data);
      })
      .catch((error) => {
        console.error('Error fetching total:', error);
      });
  };
  
  const buyBooks = (): void => {
    axios.get('http://localhost:8080/storePage/buyBooks')
      .then((response) => {
        alert(response.data);
      })
      .catch((error) => {
        console.error('Error fetching total:', error);
      });
  };
  
  return (
    <div>
      <h1>BookStore</h1>
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
      <Box sx={{ display: 'flex', flexDirection: 'row', alignItems: 'flex-start', marginLeft: 2 }}>
        <Box sx={{ height: 400, width: '80%',  backgroundColor: 'rgba(128, 128, 128, 0.8)' }}>
          <DataGrid
            rows={books}
            columns={columns}
            autoPageSize
            onRowSelectionModelChange={handleSelectionChange}
            getRowId={(row) => row.id}
            sx={{ color: 'white' }}
          />
        </Box>
        <Box sx={{ marginLeft: 20, display: 'flex', flexDirection: 'column', alignItems: 'flex-start' }}>
          <TextField
            label="Quantity"
            variant="outlined"
            margin="normal"
            type="number"
            value={quantity}
            onChange={handleQuantityChange}
            InputLabelProps={{ style: { color: 'white' } }}
            InputProps={{ style: { color: 'white' } }}
          />
          <Button variant="contained" style={{ backgroundColor: 'blue', color: 'white', marginTop: 10 }} onClick={addToCart}>
            AddToCart
          </Button>
          <Button variant="contained" style={{ backgroundColor: 'blue', color: 'white', marginTop: 10 }} onClick={calculateTotal}>
            Total
          </Button>
          <p>Total: {total}</p>
          <Button variant="contained" style={{ backgroundColor: 'blue', color: 'white', marginTop: 10 }} onClick={buyBooks}>
            BuyBooks
          </Button>
        </Box>
      </Box>
      </Box>
    </div>
  );
};

export default UserPage;
