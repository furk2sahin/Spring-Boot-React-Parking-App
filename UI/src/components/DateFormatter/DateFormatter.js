import React from 'react'

const DateFormatter = ({ date }) => {

    const formatter = (number) => {
        if (number < 10)
            return "0" + number;
        else
            return number;
    }

    return (
        <strong data-testid="dateFormatter">
            {formatter(date.getDate())}.{formatter(date.getMonth() + 1)}.{date.getFullYear()}<br />
            {formatter(date.getHours())}:{formatter(date.getMinutes())}:{formatter(date.getSeconds())}
        </strong>)
}

export default DateFormatter
