import { Sidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
export default function AdminNavbar() {



    return (
    <Sidebar>
        <Menu pagewrapid={"menuId"}>
            <main id="menuId">
            <SubMenu label="User">
                <MenuItem> User stats </MenuItem>
            </SubMenu>
            <MenuItem> <a href="http://localhost:8080/">Spring boot logs</a> </MenuItem>
            <MenuItem> <a href="https://sentry.io/organizations/event-helper/issues/?referrer=sidebar">React Logs</a> </MenuItem>
            <MenuItem> <a href="/home">Home</a> </MenuItem>
            <MenuItem> <a href="http://localhost:8080/api/events/data">Update Wroclaw data</a> </MenuItem>
            </main>
        </Menu>
    </Sidebar>




    )
}