import React, { useContext } from 'react'
import { Container, Divider, Grid, Icon } from 'semantic-ui-react'
import { WordBreaker, Wrapper } from './Welcome.styles'

import SessionContext from '../../contexts/SessionContext'
import { useHistory } from 'react-router-dom'
import Logout from '../Logout/Logout'
import DateFormatter from '../DateFormatter/DateFormatter'

const WelcomeCard = ({ person }) => {
    const { setUser, setAuthenticated } = useContext(SessionContext);
    const history = useHistory();

    const handleOnClick = () => {
        setAuthenticated('');
        setUser({});
        history.push('/login')
    }

    return (
        <Wrapper>
            <Container textAlign='center'>Welcome</Container>
            <br />
            <Divider />
            <Grid>
                <Container textAlign='center'>Name <Icon name="user" /></Container>
                <WordBreaker>{person.fullname}</WordBreaker>
            </Grid>
            <Divider />
            <Grid>
                <Container textAlign='center'>Phone <Icon name="phone" /></Container>
                <WordBreaker>{person.phone}</WordBreaker>
            </Grid>
            <Divider />
            <Grid>
                {person.registrationDate ?
                    <>
                        <Container textAlign='center'>Registration Date <Icon name="calendar alternate outline" /></Container>
                        <WordBreaker>
                            <DateFormatter date={person.registrationDate} />
                        </WordBreaker>
                    </>
                    : null
                }
            </Grid>
            <Divider />
            <Logout handleOnClick={handleOnClick} />
        </Wrapper>
    )
}

export default WelcomeCard
