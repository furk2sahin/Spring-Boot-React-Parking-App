import React from 'react';
import ReactDOM from 'react-dom';
import DateFormatter from './DateFormatter';
import { render } from '@testing-library/react';


test("renders without crash", () => {
    const date = new Date();
    const formatter = document.createElement("strong");
    ReactDOM.render(<DateFormatter date={date} />, formatter);
})

test("format a date object", () => {
    const date = new Date();

    const formatter = (number) => {
        if (number < 10)
            return "0" + number;
        else
            return number;
    }

    const { getByTestId } = render(<DateFormatter date={date} />)

    expect(getByTestId('dateFormatter'))
        .toHaveTextContent(formatter(date.getDate()) + "." + formatter(date.getMonth() + 1) +
            "." + date.getFullYear() + formatter(date.getHours()) + ":" + formatter(date.getMinutes()) + ":" + formatter(date.getSeconds()))
})