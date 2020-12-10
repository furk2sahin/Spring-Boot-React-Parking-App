import React from 'react'
import { Form } from 'semantic-ui-react'

const LicensePlateInput = ({ licensePlate, handleLicensePlateChange, licensePlateError }) => {
    return (
        <Form.Input
            data-testid="licensePlateInput"
            value={licensePlate}
            onChange={handleLicensePlateChange}
            required
            icon='car'
            iconPosition='left'
            label='License Plate'
            placeholder='34 ABC 1234'
            error={licensePlateError ? "Wrong format" : null}
        />
    )
}

export default LicensePlateInput
