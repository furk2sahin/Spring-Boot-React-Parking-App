import axios from 'axios';

const config = {
    headers: {
        authorization: 'Basic dXNlcjpEYXJrU2VjcmV0cw==',
    }
}

const instance = axios.create({
    baseURL: 'https://furk2sahin-parking-app-backend.herokuapp.com/api'
})

//parking_owner

export const getParkingOwners = () => {
    return instance.get('/parking_owner', config);
}

//parking

export const getParkings = () => {
    return instance.get('/parking', config);
}

export const createParking = (parking) => {
    return instance.post('/parking', parking, config);
}

//blacklist

export const getBlacklist = () => {
    return instance.get('/blacklist', config);
}

export const createBlacklistItem = (blacklist) => {
    return instance.post('/blacklist', blacklist, config);
}

export const deleteBlacklistItem = (blacklistId) => {
    return instance.delete(`/blacklist/${blacklistId}`, config);
}

//employees

export const getEmployees = () => {
    return instance.get('/employees', config);
}

export const createEmployee = (employee) => {
    return instance.post('/employees', employee, config);
}

export const updateEmployee = (employeeId, employee) => {
    return instance.put(`/employees/${employeeId}`, employee, config);
}

export const deleteEmployee = (employeeId) => {
    return instance.delete(`/employees/${employeeId}`, config);
}

//entrance_exit_log

export const getEntranceExitLogs = () => {
    return instance.get('/entrance_exit_log', config);
}

export const createEntranceExitLog = (entranceExitLog) => {
    return instance.post('/entrance_exit_log', entranceExitLog, config);
}

export const updateEntranceExitLog = (entranceExitLogId, entranceExitLog) => {
    return instance.put(`/entrance_exit_log/${entranceExitLogId}`, entranceExitLog, config);
}

export const deleteEntranceExitLog = (vehicleId) => {
    return instance.delete(`/entrance_exit_log/${vehicleId}`, config);
}

//park_area

export const getParkAreas = () => {
    return instance.get('/park_area', config);
}

export const updateParkArea = (parkAreaId, parkArea) => {
    return instance.put(`/park_area/${parkAreaId}`, parkArea, config);
}

//report_list

export const getReportList = () => {
    return instance.get('/report_list', config);
}

export const createReportListItem = (reportList) => {
    return instance.post('/report_list', reportList, config);
}

export const updateReportListItem = (reportListId, reportList) => {
    return instance.put(`/report_list/${reportListId}`, reportList, config);
}

export const deleteReportListItem = (vehicleId) => {
    return instance.delete(`/report_list/${vehicleId}`, config);
}

//users

export const getUsers = () => {
    return instance.get('/users', config);
}

export const createUser = (user) => {
    return instance.post('/users', user, config);
}

//vehicles

export const getVehicles = () => {
    return instance.get('/vehicles', config);
}

export const createVehicle = (vehicle) => {
    return instance.post('/vehicles', vehicle, config);
}

export const deleteVehicle = (vehicleId) => {
    return instance.delete(`/vehicles/${vehicleId}`, config);
}