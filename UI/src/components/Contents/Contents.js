import React from 'react'
import { Grid } from 'semantic-ui-react'
import AddNewVehicleCard from '../Cards/AddNewVehicleCard/AddNewVehicleCard'
import BlacklistCard from '../Cards/BlacklistCard/BlacklistCard'
import EmployeeReportCard from '../Cards/EmployeeReportCard/EmployeeReportCard'
import EmployeeRequestCard from '../Cards/EmployeeRequestCard/EmployeeRequestCard'
import LogCard from '../Cards/LogCard/LogCard'
import ParkAreaCard from '../Cards/ParkAreaCard/ParkAreaCard'
import ParkingOwnerReportCard from '../Cards/ParkingOwnerReportCard/ParkingOwnerReportCard'
import ParkingOwnerVehicleCard from '../Cards/ParkingOwnerVehicleCard/ParkingOwnerVehicleCard'
import UserVehicleCard from '../Cards/UserVehicleCard/UserVehicleCard'
import UserVehicleReportCard from '../Cards/UserVehicleReportCard/UserVehicleReportCard'

const Contents = ({
    vehicles,
    contents,
    getContent,
    parkings,
    parkAreas,
    updateVehicles,
    setListedVehicleId,
    setListLogs,
    setListReports,
    employees,
    setCarError,
    selectedCar,
    allLogs,
    reports,
    updateReports,
    blacklist,
    updateEmployees,
    users,
    updateBlacklist
}) => {
    contents.sort((content1, content2) => { return Number(content1.id) - Number(content2.id) })
    return (
        <Grid stackable>
            {
                contents.map((content, index) =>
                    <Grid.Column width="4" key={index}>
                        {getContent === 'BlacklistCard' ?
                            <BlacklistCard
                                content={content}
                                vehicles={vehicles}
                                updateBlacklist={updateBlacklist}
                            />
                            : getContent === 'EmployeeReportCard' ?
                                <EmployeeReportCard
                                    content={content}
                                    vehicles={vehicles}
                                    parkings={parkings}
                                    reports={reports}
                                    updateReports={updateReports}
                                />
                                : getContent === 'EmployeeRequestCard' ?
                                    <EmployeeRequestCard
                                        content={content}
                                        updateEmployees={updateEmployees}
                                    />
                                    : getContent === 'LogCard' ?
                                        <LogCard
                                            content={content}
                                            vehicles={vehicles}
                                            parkings={parkings}
                                        />
                                        : getContent === 'ParkAreaCard' ?
                                            <ParkAreaCard
                                                content={content}
                                                allLogs={allLogs}
                                                selectedCar={selectedCar}
                                                vehicles={vehicles}
                                                updateVehicles={updateVehicles}
                                                index={index}
                                                setCarError={setCarError}
                                            />
                                            : getContent === 'ParkingOwnerReportCard' ?
                                                <ParkingOwnerReportCard
                                                    content={content}
                                                    vehicles={vehicles}
                                                    parkings={parkings}
                                                    employees={employees}
                                                    updateReports={updateReports}
                                                    parkAreas={parkAreas}
                                                />
                                                : getContent === 'ParkingOwnerVehicleCard' ?
                                                    <ParkingOwnerVehicleCard
                                                        content={content}
                                                        parkings={parkings}
                                                        users={users}
                                                    />
                                                    : getContent === 'UserVehicleCard' ?
                                                        <UserVehicleCard
                                                            content={content}
                                                            parkAreas={parkAreas}
                                                            parkings={parkings}
                                                            updateVehicles={updateVehicles}
                                                            setListedVehicleId={setListedVehicleId}
                                                            setListLogs={setListLogs}
                                                            setListReports={setListReports}
                                                        />
                                                        : getContent === 'UserVehicleReportCard' ?
                                                            <UserVehicleReportCard
                                                                content={content}
                                                                vehicles={vehicles}
                                                                parkings={parkings}
                                                                employees={employees}
                                                            />
                                                            : null
                        }
                    </Grid.Column>
                )
            }
            {
                getContent === 'UserVehicleCard' ?
                    <Grid.Column width="4">
                        <AddNewVehicleCard
                            vehicles={contents}
                            parkings={parkings}
                            updateVehicles={updateVehicles}
                            blacklist={blacklist}
                        />
                    </Grid.Column> : null
            }
        </Grid >
    )
}

export default Contents
