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

const AdminBooksPage = () => {
  const [books, setBooks] = useState<Book[]>([]);
  const [selectedBook, setSelectedBook] = React.useState<Book | null>(null);

  const handleSelectionChange = (selectionModel: any): void => {
    if (selectionModel.length > 0) {
      const selectedBookId = selectionModel[0];
      const book = books.find((c) => c.id === selectedBookId) || null;
      setSelectedBook(book);
      console.log(book);
    } else {
      setSelectedBook(null);
      console.log(null);
    }
  };

  useEffect(() => {
    axios
      .get('http://localhost:8080/adminPage/showBooks')
      .then((response) => {
        setBooks(response.data);
      })
      .catch((error) => {
        console.error('Error fetching books:', error);
      });
  }, []);

  const columns: GridColDef[] = [
    { field: 'id', headerName: 'ID', width: 90 },
    { field: 'title', headerName: 'Title', width: 150 },
    { field: 'author', headerName: 'Author', width: 150 },
    { field: 'publishedDate', headerName: 'Published Date', width: 150 },
    { field: 'stock', headerName: 'Stock', width: 110 },
    { field: 'price', headerName: 'Price', width: 110 },
  ];
 
  const [title, setTitle] = React.useState<string>("");
  const onChangeTitle = (event: any): void => {
    setTitle(event.target.value)
  }

  const [author, setAuthor] = React.useState<string>("");
  const onChangeAuthor = (event: any): void => {
    setAuthor(event.target.value)
  }

  const [publishedDate, setPublishedDate] = React.useState<string>("");
  const onChangePublishedDate = (event: any): void => {
    setPublishedDate(event.target.value)
  }

  const [price, setPrice] = React.useState<string>("");
  const onChangePrice = (event: any): void => {
    setPrice(event.target.value)
  }

  const [stock, setStock] = React.useState<string>("");
  const onChangeStock = (event: any): void => {
    setStock(event.target.value)
  }

  const AddBook = (event: any): void => {
    const newBook = {
      title: title,
      author: author,
      stock: stock,
      price: price
    };
  
    axios
      .post(`http://localhost:8080/adminPage/insertBook?publishedDate=${publishedDate}`, newBook, {
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
  
  const DeleteBook = () => {
    if (selectedBook !== null) {
      const bookId = selectedBook.id;

      axios
        .post(`http://localhost:8080/adminPage/deleteBook?id=${bookId}`)
        .then((response) => {
          console.log(response);
          alert('Deleted Successfully');
        })
        .catch((error) => {
          console.error(error);
          const errorMessage = error.response?.data?.message || 'Unknown error';
          alert(`Error: ${errorMessage}`);
        });
    } else {
      alert(`Error: Select a book!`);
    }
  };

  const [updatetitle, setTitleU] = React.useState<string>("");
  const onChangeUpdateTitle = (event: any): void => {
    setTitleU(event.target.value)
  }

  const [updateauthor, setAuthorU] = React.useState<string>("");
  const onChangeUpdateAuthor = (event: any): void => {
    setAuthorU(event.target.value)
  }

  const [updatepublishedDate, setPublishedDateU] = React.useState<string>("");
  const onChangeUpdatePublishedDate = (event: any): void => {
    setPublishedDateU(event.target.value)
  }

  const [updateprice, setPriceU] = React.useState<string>("");
  const onChangeUpdatePrice = (event: any): void => {
    setPriceU(event.target.value)
  }

  const [updatestock, setStockU] = React.useState<string>("");
  const onChangeUpdateStock = (event: any): void => {
    setStockU(event.target.value)
  }


  const UpdateBook = (event: any): void => {
    if (selectedBook !== null) {
      const bookId = selectedBook.id;
  
      const updatedBook = {
        title: updatetitle,
        author: updateauthor,
        publishedDate: updatepublishedDate,
        stock: updatestock,
        price: updateprice,
      };
  
      axios
        .post(`http://localhost:8080/adminPage/updateBook?id=${bookId}&publishedDate=${updatepublishedDate}`, updatedBook, {
          headers: { "Content-Type": "application/json" },
        })
        .then((response: any): void => {
          console.log(response);
          alert('Updated Successfully');
        })
        .catch((error) => {
          console.error(error);
          const errorMessage = error.response?.data?.message || 'Unknown error';
          alert(`Error: ${errorMessage}`);
        });
    } else {
      alert(`Error: Select a book for updating!`);
    }
  };
  
  return (
    <div>
      <h1>Books Page</h1>
      <Box sx={{ height: 400, width: '100%' }}>
        <DataGrid
          rows={books}
          columns={columns}
          autoPageSize
          onRowSelectionModelChange={handleSelectionChange}
        />
      </Box>
      <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-start', marginBottom: 2 }}>
        <TextField
          label="Title"
          name="title"
          variant="outlined"
          margin="normal"
          onChange={onChangeTitle}
          sx={{ marginRight: 5 }}
        />
        <TextField
          label="Author"
          name="author"
          variant="outlined"
          margin="normal"
          onChange={onChangeAuthor}
          sx={{ marginRight: 5 }}
        />
        <TextField
          label="Published Date"
          name="publishedDate"
          variant="outlined"
          onChange={onChangePublishedDate}
          sx={{ marginRight: 5 }}
        />
        <TextField
          label="Stock"
          name="stock"
          variant="outlined"
          margin="normal"
          onChange={onChangeStock}
          sx={{ marginRight: 5 }}
        />
        <TextField
          label="Price"
          name="price"
          variant="outlined"
          margin="normal"
          onChange={onChangePrice}
          sx={{ marginRight: 5 }}
        />
        <Button variant="contained" style={{ backgroundColor: 'green', color: 'white' }} onClick={AddBook}>
          Add Book
        </Button>
      </Box>
      <Box sx={{ marginLeft: 50, marginTop: "-53px", marginRight: 5 }}>
        <Button variant="contained" style={{ backgroundColor: 'red', color: 'white' }} onClick={DeleteBook}>
          Delete Book
        </Button>
      </Box>
      <Box sx={{ display: 'flex', marginTop:"-410px", flexDirection: 'column', marginLeft: 85 }}>
        <TextField
          label="Update Title"
          name="updateTitle"
          variant="outlined"
          margin="normal"
          onChange={onChangeUpdateTitle}
          sx={{ marginRight: 35 }}
        />
        <TextField
          label="Update Author"
          name="updateAuthor"
          variant="outlined"
          margin="normal"
          onChange={onChangeUpdateAuthor}
          sx={{ marginRight: 35 }}
        />
        <TextField
          label="Update Published Date"
          name="updatePublishedDate"
          variant="outlined"
          margin="normal"
          onChange={onChangeUpdatePublishedDate}
          sx={{ marginRight: 35 }}
        />
        <TextField
          label="Update Stock"
          name="updateStock"
          variant="outlined"
          margin="normal"
          onChange={onChangeUpdateStock}
          sx={{ marginRight: 35 }}
        />
        <TextField
          label="Update Price"
          name="updatePrice"
          variant="outlined"
          margin="normal"
          onChange={onChangeUpdatePrice}
          sx={{ marginRight: 35 }}
        />
        <Button variant="contained" style={{ backgroundColor: 'green', color: 'white', width:150  }} onClick={UpdateBook}>
          Update Book
        </Button>
      </Box>
    </div>
  );
};

export default AdminBooksPage;
