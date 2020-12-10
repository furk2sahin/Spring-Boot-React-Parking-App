import React, { useContext, useEffect, useState } from 'react'
import { MoonLoader } from 'react-spinners';
import { Grid, Message, Ref, Sticky, Visibility } from 'semantic-ui-react';
import Contents from '../../components/Contents/Contents';
import Header from '../../components/Header/Header'
import { menuItems } from '../../MenuItems'
import SearchBar from '../../components/SearchBar/SearchBar';
import WelcomeCard from '../../components/WelcomeCard/WelcomeCard';
import SessionContext from '../../contexts/SessionContext';
import { getEntranceExitLogs, getParkAreas, getParkings, getReportList, getVehicles } from '../../services/api';
import { Wrapper } from '../Wrapper';

const EmployeePage = () => {
    const { user } = useContext(SessionContext);
    const [activeMenu, setActiveMenu] = useState(menuItems.employee[0]);
    const [userInput, setUserInput] = useState('');
    const [parkAreas, setParkAreas] = useState([]);
    const [vehicles, setVehicles] = useState([]);
    const [reports, setReports] = useState([]);
    const [logs, setLogs] = useState([]);
    const [loading, setLoading] = useState(false);
    const [parkings, setParkings] = useState([]);
    const [error, setError] = useState("");

    const contextRef = React.createRef();

    const handleHeaderOnClick = (event, { name }) => {
        setActiveMenu(name);
        setError("");
    }

    const handleSearhOnChange = (event) => {
        setUserInput(event.target.value);
    }

    useEffect(() => {
        setUserInput("");
    }, [activeMenu])

    const updateReports = async () => {
        try {
            const { data } = await getReportList();
            setReports(data);
        } catch (err) {
            setError(err);
        }
    }

    useEffect(() => {
        const init = async () => {
            setLoading(true);
            try {
                const { data } = await getVehicles();
                setVehicles(data);
            } catch (err) {
                setError(err);
            }

            try {
                const { data } = await getParkings();
                setParkings(data);
            } catch (err) {
                setError(err);
            }

            if (activeMenu === "Report A Car") {
                try {
                    let { data } = await getParkAreas();
                    data.filter((parkArea) => Number(parkArea.parkingId) === Number(user.parkingId));
                    setParkAreas(data);
                } catch (err) {
                    setError(err);
                }

                try {
                    const { data } = await getReportList();
                    setReports(data);
                } catch (err) {
                    setError(err);
                }
            } else if (activeMenu === "Entrance Exit Log") {
                try {
                    const { data } = await getEntranceExitLogs();
                    setLogs(data);
                } catch (err) {
                    setError(err);
                }
            }
            setLoading(false);
        }
        init();
    }, [activeMenu, user.parkingId])

    const person = {
        fullname: user.fullName,
        phone: "0" + user.phone,
        registrationDate: new Date(user.startingDate),
    }

    const getFilteredCars = () => {
        const filteredVehicles = vehicles.filter((vehicle) => vehicle.licensePlate.toLowerCase().indexOf(userInput.toLowerCase()) !== -1 && Number(vehicle.parkingId) === Number(user.parkingId))
            .map((vehicle) => Number(vehicle.id));
        return parkAreas.filter((area) => filteredVehicles.includes(Number(area.vehicleId)) && area.full);
    }

    const getFilteredLogs = () => {
        const filteredVehicles = vehicles.filter((vehicle) => vehicle.licensePlate.toLowerCase().indexOf(userInput.toLowerCase()) !== -1 && Number(vehicle.parkingId) === Number(user.parkingId))
            .map((vehicle) => Number(vehicle.id));
        return logs.filter((log) => filteredVehicles.includes(Number(log.vehicleId)));
    }

    return (
        <>
            <Header menuItems={menuItems.employee} activeMenu={activeMenu} handleHeaderOnClick={handleHeaderOnClick} />

            <Wrapper>
                <Sticky context={contextRef}>
                    <Grid centered>
                        {!loading ?
                            activeMenu === "Report A Car" ?
                                getFilteredCars().length === 0 && userInput === "" ?
                                    <Message negative header="There is car in park areas." />
                                    :
                                    <SearchBar userInput={userInput} onChange={handleSearhOnChange} placeholder="Search by license plate..." />
                                : activeMenu === "Entrance Exit Log" ?
                                    getFilteredLogs().length === 0 && userInput === "" ?
                                        <Message negative header="There is no log." />
                                        :
                                        <SearchBar userInput={userInput} onChange={handleSearhOnChange} placeholder="Search by license plate..." />
                                    : null
                            : null
                        }

                    </Grid>
                </Sticky>

                <Ref innerRef={contextRef}>
                    <Grid stackable>
                        <Grid.Column width="13">
                            {loading && !error ?
                                <div style={{ marginLeft: "50%" }}>
                                    <MoonLoader size="75px" color="#36D7B7"></MoonLoader>
                                </div>
                                : error ? <Message negative>{error.toString()}</Message> :
                                    <Visibility>
                                        {activeMenu === 'Report A Car' ?
                                            <Contents
                                                contents={getFilteredCars()}
                                                vehicles={vehicles}
                                                parkings={parkings}
                                                reports={reports}
                                                updateReports={updateReports}
                                                getContent="EmployeeReportCard"
                                            /> : activeMenu === "Entrance Exit Log" ?
                                                <Contents
                                                    contents={getFilteredLogs()}
                                                    parkings={parkings}
                                                    vehicles={vehicles}
                                                    getContent="LogCard"
                                                />
                                                : null
                                        }
                                    </Visibility>
                            }
                        </Grid.Column>
                        <Grid.Column width="3">
                            <Sticky context={contextRef}>
                                <WelcomeCard person={person} />
                            </Sticky>
                        </Grid.Column>
                    </Grid>
                </Ref>
            </Wrapper >
        </>
    )
}

export default EmployeePage
