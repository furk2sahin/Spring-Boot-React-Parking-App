import React from 'react'
import { Card, Divider } from 'semantic-ui-react'
import { WordBreaker } from '../../WelcomeCard/Welcome.styles'

const ParkingOwnerVehicleCard = ({ content, users, parkings }) => {
    return (
        <Card>
            <Card.Content >
                <Card.Header textAlign="center">{content.licensePlate}</Card.Header>
                <Divider />
                <Card.Description textAlign="center">
                    <WordBreaker>Parking name : <strong>{parkings.find(parking => Number(parking.id) === Number(content.parkingId)).name}</strong></WordBreaker>
                    <WordBreaker>Vehicle ID : <strong>{content.id}</strong></WordBreaker>
                    <WordBreaker>User ID : <strong>{content.userId}</strong></WordBreaker>
                    <WordBreaker>User Name : <strong>{users.find(user => Number(user.id) === Number(content.userId)).fullName}</strong></WordBreaker>
                    <WordBreaker>Phone : <strong>{users.find(user => Number(user.id) === Number(content.userId)).phone}</strong></WordBreaker>
                </Card.Description>
            </Card.Content>
        </Card>
    )
}

export default ParkingOwnerVehicleCard
