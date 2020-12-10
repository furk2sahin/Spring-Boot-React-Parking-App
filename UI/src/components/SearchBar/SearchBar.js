import React from 'react'
import { Input } from './SearchBar.styles'

const SearchBar = ({ userInput, onChange, placeholder }) => {
    return (
        <Input data-testid="input" value={userInput} onChange={onChange} placeholder={placeholder} />
    )
}

export default SearchBar
