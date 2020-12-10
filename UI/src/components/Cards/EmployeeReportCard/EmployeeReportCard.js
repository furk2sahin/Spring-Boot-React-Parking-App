import React, { useContext, useState } from 'react'
import { Button, Card, Divider, Form, Icon, Message, Modal } from 'semantic-ui-react'
import SessionContext from '../../../contexts/SessionContext';
import { createReportListItem } from '../../../services/api';

const EmployeeReportCard = ({ content, vehicles, parkings, reports, updateReports }) => {
    const { user } = useContext(SessionContext);
    const [reason, setReason] = useState("");
    const [reasonError, setReasonError] = useState(false);
    const [alreadyReported, setAlreadyReported] = useState(false);
    const [success, setSuccess] = useState(false);
    const [reportLoading, setReportLoading] = useState(false);
    const [modalOpen, setModalOpen] = useState(false);
    const licensePlate = vehicles.find((vehicle) => vehicle.id.toString() === content.vehicleId.toString()).licensePlate;
    const parkingName = parkings.find((parking) => parking.id.toString() === content.parkingId.toString()).name;

    const handleReasonChange = (event, { value }) => {
        if ((/^[a-zA-Z0-9., ]+$/.test(value) || value === '') && value.length < 50) {
            setReason(value);
            setAlreadyReported(false);
            if (reasonError)
                setReasonError(false);
        }
    }

    const reportCarOnClickHandle = async () => {
        if (!/^[.,0123456789 ]+$/.test(reason) && !(reason.trim().replace(/\s\s+/g, ' ').length < 5)) {
            if (reports.some((report) => report.reportReason.toUpperCase() === reason.toUpperCase() &&
                report.active && Number(report.vehicleId) === Number(content.vehicleId))) {
                setAlreadyReported(true);
                setModalOpen(true);
                setTimeout(() => {
                    setModalOpen(false);
                }, 1000);
            } else {
                setReportLoading(true);
                setSuccess(true);
                setModalOpen(true);
                setTimeout(() => {
                    setModalOpen(false);
                    setSuccess(false);
                }, 1000);
                try {
                    await createReportListItem({
                        parkingId: content.parkingId,
                        vehicleId: content.vehicleId,
                        employeeId: user.id,
                        reportReason: reason.trim().replace(/\s\s+/g, ' ').toUpperCase(),
                        active: true
                    });
                    setReason("");
                    updateReports();
                } catch (error) {
                    console.log(error.toString())
                }
                setReportLoading(false);
            }
        } else {
            setReasonError(true);
        }
    }


    return (
        <>
            <Card>
                <Card.Content >
                    <Card.Header textAlign="center">{licensePlate}</Card.Header>
                    <Divider />
                    <Card.Description textAlign="center">
                        Parking name <br /> <strong>{parkingName}</strong>
                        <br /><br />
                        <Form size="big">
                            <Form.Input
                                value={reason}
                                error={reasonError}
                                icon='times'
                                iconPosition='left'
                                label='Reason'
                                placeholder="Report reason (min. 5)"
                                onChange={handleReasonChange}
                            />
                        </Form>
                    </Card.Description>
                </Card.Content>
                <Card.Content extra textAlign="center">
                    <Button
                        loading={reportLoading}
                        animated="fade"
                        basic
                        color='red'
                        onClick={reportCarOnClickHandle}
                    >
                        <Button.Content visible>Report Car</Button.Content>
                        <Button.Content hidden><Icon name="clipboard outline" /></Button.Content>
                    </Button>
                </Card.Content>
            </Card>
            <Modal
                dimmer="blurring"
                open={modalOpen}
                onClose={() => setModalOpen(false)}
            >
                <Modal.Header>
                    {alreadyReported ?
                        <Message negative color="red" header="This car already reported for that reason." />
                        : success ? <Message positive header="Car reported successfully." /> : null
                    }
                </Modal.Header>
            </Modal>
        </>
    )
}

export default EmployeeReportCard
