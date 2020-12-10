import React from 'react';
import { render, cleanup } from '@testing-library/react';
import SearchBar from './SearchBar';

afterEach(cleanup);


test("renders SearchBar input tag", () => {

    const { getByDisplayValue } = render(
        <SearchBar userInput="test" placeholder="User input" onChange={() => { }} />
    )
    const input = getByDisplayValue(/test/);

    expect(input.tagName).toBe("INPUT");
});

test("check style", () => {

    const { getByTestId } = render(
        <SearchBar userInput="test" placeholder="User input" onChange={() => { }} />
    )
    const input = getByTestId("input");

    expect(input).toHaveStyle("padding:5px");
});