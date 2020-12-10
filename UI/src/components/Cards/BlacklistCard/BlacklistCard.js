import React, { useState } from 'react'
import { Button, Card, Divider, Icon } from 'semantic-ui-react'
import { deleteBlacklistItem } from '../../../services/api';
import DateFormatter from '../../DateFormatter/DateFormatter';
import { WordBreaker } from '../../WelcomeCard/Welcome.styles'

const BlacklistCard = ({ content, updateBlacklist }) => {
    const [removeLoading, setRemoveLoading] = useState(false);

    const removeOnClickHandle = async () => {
        setRemoveLoading(true);
        try {
            await deleteBlacklistItem(Number(content.id));
            updateBlacklist();
        } catch (err) {

        }
        setRemoveLoading(false);
    }

    return (
        <Card>
            <Card.Content >
                <Card.Header textAlign="center">{content.licensePlate}</Card.Header>
                <Divider />
                <Card.Description textAlign="center">
                    <WordBreaker>Block Time <br />
                        <DateFormatter date={new Date(content.date)} />
                    </WordBreaker>
                </Card.Description>
                <Card.Content extra textAlign="center">
                    <Button id={content.id} animated="fade" basic positive onClick={removeOnClickHandle} loading={removeLoading}>
                        <Button.Content visible>Remove from blacklist</Button.Content>
                        <Button.Content hidden><Icon name="thumbs up outline" /></Button.Content>
                    </Button>
                </Card.Content>
            </Card.Content>
        </Card>
    )
}

export default BlacklistCard
