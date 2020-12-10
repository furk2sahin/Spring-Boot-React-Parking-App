import React from 'react'
import { Menu } from 'semantic-ui-react'
import { Wrapper, Logo } from './Header.styles'

const Header = ({ menuItems, activeMenu, handleHeaderOnClick }) => {
    return (
        <Wrapper >
            <Menu inverted size="huge" widths={menuItems.length + 1} pointing stackable>
                <Menu.Item>
                    <Logo src="logo256.png" />
                </Menu.Item>
                {menuItems.map((item, index) =>
                    <Menu.Item
                        name={item}
                        key={index}
                        onClick={handleHeaderOnClick}
                        active={activeMenu === item ? true : false}
                        color={activeMenu === item ? "violet" : "black"}
                    />
                )}
            </Menu>
        </Wrapper>
    )
}

export default Header
