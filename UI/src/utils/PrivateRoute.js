import { useContext } from 'react';
import SessionContext from '../contexts/SessionContext';

const { Route } = require('react-router-dom');

const PrivateRoute = ({ children, isAuthenticated, ...rest }) => {
    const { authenticated } = useContext(SessionContext);
    return (
        <Route
            {...rest}
            render={({ location }) =>
                isAuthenticated === authenticated ? (
                    children
                ) : null
            } />
    )
}

export default PrivateRoute