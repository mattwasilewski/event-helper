import { Sidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
export default function Admin() {
    return (
    <Sidebar>
        <Menu pageWrapId={"menuId"}>
            <main id="menuId">
            <SubMenu label="User">
                <MenuItem> <a href="/home">User manager</a> </MenuItem>
                <MenuItem> User stats </MenuItem>
            </SubMenu>
            <MenuItem> <a href="http://localhost:8080/">Spring boot logs</a> </MenuItem>
            <MenuItem> <a href="https://sentry.io/organizations/event-helper/issues/?referrer=sidebar">React Logs</a> </MenuItem>
            <MenuItem> <a href="/home">Home</a> </MenuItem>
            </main>
        </Menu>
    </Sidebar>
    )
}