import React, { useState } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { routes } from '../../config/Router'
import PrivateRoute from '../../utils/PrivateRoute'
import SessionContext from '../../contexts/SessionContext'
import { Wrapper } from './Container.styles'

const Container = () => {
    const [authenticated, setAuthenticated] = useState('');
    const [user, setUser] = useState({});

    if (((window.location.pathname === "/user" || window.location.pathname === "/employee" || window.location.pathname === "/parkingowner")
        && authenticated === '') || (!routes.some(route => route.path === window.location.pathname) && window.location.pathname !== "/register")) {
        window.location.pathname = "/login";
    }

    return (
        <Wrapper>
            <Router>
                <Switch>
                    <SessionContext.Provider value={{ user, setUser, authenticated, setAuthenticated }}>
                        {
                            routes.map((route, index) =>
                                route.isPrivate ? (
                                    <PrivateRoute
                                        exact={route.exact}
                                        path={route.path}
                                        isAuthenticated={route.authenticated}
                                        authenticated={authenticated}
                                        key={index}
                                    >
                                        {route.component()}
                                    </PrivateRoute>
                                ) : (
                                        < Route exact={route.exact} path={route.path} key={index}>
                                            {route.component()}
                                        </Route>
                                    )
                            )
                        }
                    </SessionContext.Provider>
                </Switch>
            </Router>
        </Wrapper >
    )
}

export default Container
