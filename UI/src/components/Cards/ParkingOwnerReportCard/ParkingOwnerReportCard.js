import React, { useState } from 'react'
import { Button, Card, Divider, Icon } from 'semantic-ui-react'
import { createBlacklistItem, deleteEntranceExitLog, deleteReportListItem, deleteVehicle, updateParkArea, updateReportListItem } from '../../../services/api'
import DateFormatter from '../../DateFormatter/DateFormatter'
import { WordBreaker } from '../../WelcomeCard/Welcome.styles'

const ParkingOwnerReportCard = ({ content, vehicles, parkings, employees, updateReports, parkAreas }) => {
    const [rejectLoading, setRejectLoading] = useState(false);
    const [blacklistLoading, setBlacklistLoading] = useState(false);


    const rejectOnClickHandle = async (event) => {
        setRejectLoading(true);
        try {
            await updateReportListItem(content.id, { active: false })
            updateReports();
        } catch (err) {

        }
        setRejectLoading(false);
    }

    const addBlacklistOnClickHandle = async (event) => {
        setBlacklistLoading(true);
        try {

            await deleteVehicle(Number(content.vehicleId));
            await deleteReportListItem(Number(content.vehicleId));
            await deleteEntranceExitLog(Number(content.vehicleId));
            if (parkAreas.some((area) => Number(area.vehicleId) === Number(content.vehicleId) && area.full)) {
                const areaToUpdate = parkAreas.find((area) => Number(area.vehicleId) === Number(content.vehicleId) && area.full)
                await updateParkArea(Number(areaToUpdate.id), { vehicleId: 0, full: false })
            }
            await createBlacklistItem({
                parkingId: Number(content.parkingId),
                licensePlate: vehicles.find((vehicle) => Number(vehicle.id) === Number(content.vehicleId)).licensePlate
            })
        } catch (err) {
            console.log(err.toString());
        }
        updateReports();
        setBlacklistLoading(false);
    }

    return (
        <Card>
            <Card.Content >
                <Card.Header textAlign="center">{vehicles.find((vehicle) => Number(vehicle.id) === Number(content.vehicleId)).licensePlate}</Card.Header>
                <Divider />
                <Card.Description textAlign="center">
                    <WordBreaker>Parking name : <strong>{parkings.find((parking) => Number(parking.id) === Number(content.parkingId)).name}</strong></WordBreaker>
                    <WordBreaker>Reported By : <strong>{employees.find((employee) => Number(employee.id) === Number(content.employeeId)).fullName}</strong></WordBreaker>
                    <WordBreaker>Report Reason : <strong style={{ color: "red" }} >{content.reportReason}</strong></WordBreaker>
                    <WordBreaker>Report Time <br />
                        <DateFormatter date={new Date(content.date)} />
                    </WordBreaker>
                </Card.Description>
                <Card.Content extra textAlign="center">
                    <Button.Group>
                        <Button id={content.id} animated="fade" basic positive onClick={rejectOnClickHandle} loading={rejectLoading}>
                            <Button.Content visible>Reject</Button.Content>
                            <Button.Content hidden><Icon name="thumbs up outline" /></Button.Content>
                        </Button>
                        <Button id={content.id} animated="fade" basic negative onClick={addBlacklistOnClickHandle} loading={blacklistLoading}>
                            <Button.Content visible>Add Blacklist</Button.Content>
                            <Button.Content hidden><Icon name="times" /></Button.Content>
                        </Button>
                    </Button.Group>
                </Card.Content>
            </Card.Content>
        </Card>
    )
}

export default ParkingOwnerReportCard
