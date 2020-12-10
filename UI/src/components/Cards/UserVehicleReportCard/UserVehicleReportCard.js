import React from 'react'
import { Card, Divider } from 'semantic-ui-react'
import DateFormatter from '../../DateFormatter/DateFormatter';
import { WordBreaker } from '../../WelcomeCard/Welcome.styles'

const UserVehicleReportCard = ({ content, vehicles, parkings, employees }) => {
    return (
        <Card>
            <div style={{ border: "5px solid", borderColor: content.active ? "greenyellow" : "red" }}>
                <Card.Content >
                    <Card.Header textAlign="center">{vehicles.find((vehicle) => Number(vehicle.id) === Number(content.vehicleId)).licensePlate}</Card.Header>
                    <Divider />
                    <Card.Description textAlign="center">
                        <WordBreaker>Parking name : <strong>{parkings.find((parking) => Number(parking.id) === Number(content.parkingId)).name.toUpperCase()}</strong></WordBreaker>
                        <WordBreaker>Reported By : <strong>{employees.find((employee) => Number(employee.id) === Number(content.employeeId)).fullName}</strong></WordBreaker>
                        <WordBreaker>Report Reason : <strong style={{ color: "red" }}>{content.reportReason}</strong></WordBreaker>
                        <WordBreaker>Report Time <br />
                            <DateFormatter date={new Date(content.date)} />
                        </WordBreaker>
                        <WordBreaker>Park Area Number : <strong>{content.id}</strong></WordBreaker>
                        <WordBreaker>Status : <strong>{content.active ? "Active" : "Forgiven"}</strong></WordBreaker>
                    </Card.Description>
                </Card.Content>
            </div>
        </Card>
    )
}

export default UserVehicleReportCard