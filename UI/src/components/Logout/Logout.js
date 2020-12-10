import React from 'react'
import { Button, Icon } from 'semantic-ui-react'

const Logout = ({ handleOnClick }) => {
    return (
        <Button data-testid="logout" animated fluid negative onClick={handleOnClick}>
            <Button.Content visible>Logout <Icon name="sign out" /></Button.Content>
            <Button.Content hidden><Icon name="arrow right" /></Button.Content>
        </Button>
    )
}

export default Logout
