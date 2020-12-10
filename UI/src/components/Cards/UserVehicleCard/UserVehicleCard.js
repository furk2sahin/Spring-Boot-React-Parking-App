import React, { useState } from 'react'
import { Button, Card, Divider, Header, Icon, Modal } from 'semantic-ui-react'
import { deleteEntranceExitLog, deleteReportListItem, deleteVehicle, updateParkArea } from '../../../services/api';


const UserVehicleCard = ({ content, parkings, updateVehicles, parkAreas, setListedVehicleId, setListLogs, setListReports }) => {
    const parkingName = parkings.find((parking) => Number(parking.id) === Number(content.parkingId)).name;
    const [deleteCarLoading, setDeleteCarLoading] = useState(false);
    const [modalOpen, setModalOpen] = useState(false);

    const listLogClickHandler = (event, { id }) => {
        setListedVehicleId(id);
        setListLogs(true);
    }

    const listReportsClickHandler = (event, { id }) => {
        setListedVehicleId(id);
        setListReports(true);
    }

    const deleteCarClickHandler = async (event, { id }) => {
        setDeleteCarLoading(true);
        try {
            await deleteVehicle(id);
            await deleteReportListItem(Number(id));
            await deleteEntranceExitLog(Number(id));
            if (parkAreas.some((area) => Number(area.vehicleId) === Number(id) && area.full)) {
                const areaToUpdate = parkAreas.find((area) => Number(area.vehicleId) === Number(id) && area.full)
                await updateParkArea(Number(areaToUpdate.id), { vehicleId: 0, full: false })
            }
        } catch (error) {
        }
        setDeleteCarLoading(false);
        updateVehicles();
    }

    return (
        <>
            <Card>
                <Card.Content >
                    <Card.Header textAlign="center">{content.licensePlate}</Card.Header>
                    <Divider />
                    <Card.Description textAlign="center">
                        Parking name <br /> <strong>{parkingName.toUpperCase()}</strong>
                    </Card.Description>
                </Card.Content>
                <Card.Content extra textAlign="center">
                    <Button.Group>
                        <Button id={content.id} animated="fade" basic color='green' onClick={listLogClickHandler}>
                            <Button.Content visible>List Logs</Button.Content>
                            <Button.Content hidden><Icon name="list" /></Button.Content>
                        </Button>
                        <Button id={content.id} animated="fade" basic color='blue' onClick={listReportsClickHandler}>
                            <Button.Content visible>List Reports</Button.Content>
                            <Button.Content hidden><Icon name="list" /></Button.Content>
                        </Button>
                    </Button.Group>
                    <Button
                        animated="fade"
                        basic
                        color='red'
                        onClick={() => setModalOpen(true)}
                    >
                        <Button.Content visible>Delete Car</Button.Content>
                        <Button.Content hidden><Icon name="trash alternate" /></Button.Content>
                    </Button>
                </Card.Content>
            </Card>
            <Modal
                basic
                dimmer="blurring"
                open={modalOpen}
                onClose={() => setModalOpen(false)}
            >
                <Header icon><Icon name="car" /> Delete this car?</Header>
                <Modal.Actions>
                    <Button negative onClick={() => setModalOpen(false)}>
                        No
                                    </Button>
                    <Button id={content.id} positive onClick={deleteCarClickHandler} loading={deleteCarLoading}>
                        Yes
                                    </Button>
                </Modal.Actions>
            </Modal>
        </>
    )
}

export default UserVehicleCard
