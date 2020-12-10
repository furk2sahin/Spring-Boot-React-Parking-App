import React, { useState } from 'react';
import { render, fireEvent } from '@testing-library/react';
import LicensePlateInput from './LicensePlateInput';

const SimpleLicensePlateInput = () => {
    const [licensePlate, setLicensePlate] = useState("1234");
    const onChangeHandler = (event) => {
        const checkedLisencePlate = event.target.value.trim().replace(/\s\s+/g, ' ');
        if (!(checkedLisencePlate.split(' ').length === 3) ||
            isNaN(checkedLisencePlate.split(' ')[0]) ||
            checkedLisencePlate.split(' ')[0].length > 2 ||
            !(/^[a-zA-Z]+$/.test(checkedLisencePlate.split(' ')[1])) ||
            checkedLisencePlate.split(' ')[1].length > 3 ||
            isNaN(checkedLisencePlate.split(' ')[2]) ||
            checkedLisencePlate.split(' ')[2].length > 4
        ) {
            setLicensePlate("error");
        } else {
            setLicensePlate(event.target.value);
        }
    }

    return <LicensePlateInput
        licensePlate={licensePlate}
        licensePlateError={false}
        handleLicensePlateChange={onChangeHandler}
    />
}

test("check license plate format", () => {
    const { getByPlaceholderText } = render(<SimpleLicensePlateInput />);

    const licensePlateInput = getByPlaceholderText("34 ABC 1234");
    fireEvent.change(licensePlateInput, { target: { value: "124124124124" } });
    expect(licensePlateInput).toHaveValue("error");

    fireEvent.change(licensePlateInput, { target: { value: "12 abc 34" } });
    expect(licensePlateInput).toHaveValue("12 abc 34");

    fireEvent.change(licensePlateInput, { target: { value: "123 abc 34" } });
    expect(licensePlateInput).toHaveValue("error");
});