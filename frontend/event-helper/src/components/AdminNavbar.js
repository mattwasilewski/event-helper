import { Sidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
import {useNavigate} from "react-router-dom";
import "../css/App.css";
export default function AdminNavbar() {



    const wroclawApi = async () =>{
        window.alert("LOADING DATA FROM WROCLAW API");
       await fetch("http://localhost:8080/api/events/data");
        window.alert("DATA LOADED SUCCESFULLY");
    }



    const globalApi = async () =>{
        window.alert("LOADING DATA FROM GLOBAL API");
        await fetch("http://localhost:8080/api/events/global-data");
        window.alert("DATA LOADED SUCCESFULLY");
    }


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
            <MenuItem> <button id="wroclawButton" onClick={wroclawApi}>Update Wroclaw data</button></MenuItem>
            <MenuItem> <button id="globalButton" onClick={globalApi}>Update Global data</button></MenuItem>
            </main>
        </Menu>
    </Sidebar>




    )
}