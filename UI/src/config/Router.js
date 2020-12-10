import Register from '../pages/Register/Register';
import Login from '../pages/Login/Login';
import UserPage from '../pages/UserPage/UserPage';
import EmployeePage from '../pages/EmployeePage/EmployeePage';
import ParkingOwnerPage from '../pages/ParkingOwnerPage/ParkingOwnerPage';



export const routes = [
    {
        path: "/login",
        exact: true,
        component: () => <Login />,
        isPrivate: false,
        authenticated: ''
    },
    {
        path: '/register',
        exact: true,
        component: () => <Register />,
        isPrivate: false,
        authenticated: ''
    },
    {
        path: '/user',
        exact: true,
        component: () => <UserPage />,
        isPrivate: true,
        authenticated: 'User',
    },
    {
        path: '/employee',
        exact: true,
        component: () => <EmployeePage />,
        isPrivate: true,
        authenticated: 'Employee',
    },
    {
        path: '/parkingowner',
        exact: true,
        component: () => <ParkingOwnerPage />,
        isPrivate: true,
        authenticated: 'ParkingOwner',
    },
]