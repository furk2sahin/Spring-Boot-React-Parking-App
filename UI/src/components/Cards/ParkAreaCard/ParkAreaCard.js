import React, { useContext, useState } from 'react'
import { Button, Card, Divider, Icon } from 'semantic-ui-react'
import SessionContext from '../../../contexts/SessionContext'
import { createEntranceExitLog, updateEntranceExitLog, updateParkArea } from '../../../services/api'
import { WordBreaker } from '../../WelcomeCard/Welcome.styles'

const ParkAreaCard = ({ content, vehicles, index, updateVehicles, setCarError, selectedCar, allLogs }) => {
    const { user, authenticated } = useContext(SessionContext);
    const [entranceExitLoading, setEntranceExit] = useState(false);

    const handleParkHereOnClick = async () => {
        if (selectedCar !== "") {
            const id = vehicles.find((vehicle) => selectedCar === vehicle.licensePlate).id;
            try {
                setEntranceExit(true);
                await updateParkArea(content.id, { vehicleId: id, full: true });
                await createEntranceExitLog({ vehicleId: id, parkingId: content.parkingId, parkAreaNumber: Number(index) + 1 });
            } catch (error) {
            }
            updateVehicles();
            setEntranceExit(true)
        } else {
            setCarError(true);
        }
    }

    const handleExitOnClick = async () => {
        try {
            setEntranceExit(true);
            const logId = allLogs.find((log) => Number(log.vehicleId) === Number(content.vehicleId) && !log.exitTime).id;
            await updateParkArea(content.id, { vehicleId: 0, full: false });
            await updateEntranceExitLog(logId, { exitTime: new Date() });
        } catch (error) {
            console.log(error);
        }
        updateVehicles();
        setEntranceExit(false);
    }

    return (
        <Card>
            <div style={{ border: "5px solid", borderColor: !content.full ? "greenyellow" : "red" }} >
                <Card.Content >
                    <Card.Header textAlign="center">Park Area <strong>{index + 1}</strong></Card.Header>
                    <Divider />
                    <Card.Description textAlign="center">
                        <WordBreaker>License Plate <br />
                            <h2>
                                {content.full && vehicles.some((vehicle) => Number(vehicle.id) === Number(content.vehicleId)) ?
                                    <div style={{ border: "1px solid red" }}>
                                        {
                                            vehicles.find((vehicle) => Number(vehicle.id) === Number(content.vehicleId)).licensePlate
                                        }
                                        <Icon name="car" />
                                    </div>
                                    :
                                    "None"
                                }
                            </h2>
                        </WordBreaker>
                    </Card.Description>
                    {authenticated === "User" ?
                        <Card.Content extra textAlign="center">
                            {!content.full ?
                                <Button fluid animated="fade" positive onClick={handleParkHereOnClick} loading={entranceExitLoading}>
                                    <Button.Content visible>Park here</Button.Content>
                                    <Button.Content hidden><Icon name="add" /></Button.Content>
                                </Button>
                                : vehicles.some((vehicle) => Number(vehicle.id) === Number(content.vehicleId) && Number(vehicle.userId) === Number(user.id)) ?
                                    <Button fluid animated="fade" primary onClick={handleExitOnClick} loading={entranceExitLoading}>
                                        <Button.Content visible>Exit</Button.Content>
                                        <Button.Content hidden><Icon name="sign-out" /></Button.Content>
                                    </Button>
                                    :
                                    <Button fluid disabled negative>Full</Button>
                            }
                        </Card.Content>
                        : null
                    }
                </Card.Content>
            </div>
        </Card>
    )
}

export default ParkAreaCard
