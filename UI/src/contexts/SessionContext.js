import React from 'react';

const SessionContext = React.createContext({
    user: {},
    setUser: () => { },

    authenticated: '',
    setAuthenticated: () => { }
})

export default SessionContext;