import React from 'react';
import { render, cleanup } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import Logout from './Logout';

afterEach(cleanup);

test("onClick event trigger", () => {

    const mockFn = jest.fn();
    const { getByTestId } = render(<Logout handleOnClick={mockFn} />)
    const button = getByTestId('logout');
    userEvent.click(button);
    expect(mockFn)
        .toHaveBeenCalledTimes(1);
})