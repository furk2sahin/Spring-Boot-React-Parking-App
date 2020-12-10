import React, { useState } from 'react'
import { Button, Card, Divider, Icon } from 'semantic-ui-react';
import { deleteEmployee, updateEmployee } from '../../../services/api';
import { WordBreaker } from '../../WelcomeCard/Welcome.styles';

const EmployeeRequestCard = ({ content, updateEmployees }) => {
    const [loading, setLoading] = useState(false);
    const acceptOnClickHandle = async () => {
        setLoading(true);
        try {
            await updateEmployee(Number(content.id), { accepted: true });
        } catch (err) {
        }
        setLoading(false);
        updateEmployees();
    }

    const rejectOnClickHandle = async () => {
        setLoading(true);
        try {
            await deleteEmployee(Number(content.id));
        } catch (err) {
        }
        setLoading(false);
        updateEmployees();
    }

    return (
        <Card>
            <Card.Content >
                <Card.Header textAlign="center">{content.fullName}</Card.Header>
                <Divider />
                <Card.Description textAlign="center">
                    <WordBreaker>Phone : <strong>{content.phone}</strong></WordBreaker>
                    <br /><br /><h3>Wants to work with you.</h3>
                </Card.Description>
                <Card.Content extra textAlign="center">
                    <Button.Group>
                        <Button id={content.id} animated="fade" basic positive onClick={acceptOnClickHandle} loading={loading}>
                            <Button.Content visible>Accept</Button.Content>
                            <Button.Content hidden><Icon name="thumbs up outline" /></Button.Content>
                        </Button>
                        <Button id={content.id} animated="fade" basic negative onClick={rejectOnClickHandle} loading={loading}>
                            <Button.Content visible>Reject</Button.Content>
                            <Button.Content hidden><Icon name="times" /></Button.Content>
                        </Button>
                    </Button.Group>
                </Card.Content>
            </Card.Content>
        </Card>
    )
}

export default EmployeeRequestCard
