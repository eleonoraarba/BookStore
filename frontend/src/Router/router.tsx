import { RouteObject, createBrowserRouter } from "react-router-dom";
import  SignIn  from "../Pages/Login";
import UserPage from "../Pages/UserPage";
import AdminPage from "../Pages/AdminPage";
import AdminUsersPage from "../Pages/AdminUsersPage";
import AdminBooksPage from "../Pages/AdminBooksPage";

const routes: RouteObject[] = [

    {
        path: "/Login",
        element: <SignIn />
    },

    {
        path: "/userPage",
        element: <UserPage />
    },

    {
        path: "/adminPage",
        element: <AdminPage />
    },

    {
        path: "/adminUsersPage",
        element: <AdminUsersPage />
    },

    {
        path: "/adminBooksPage",
        element: <AdminBooksPage />
    }

];

export const router = createBrowserRouter(routes)