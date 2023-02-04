import AdminNavbar from "../components/AdminNavbar";
import {ProSidebarProvider} from "react-pro-sidebar";
import EventList from "../components/event/EventList";
import Navbar from "../components/utils/Navbar";
import UserList from "../components/user/UserList";

export default function AdminPage() {




    return (
        <div>
        <Navbar/>
        <EventList/>
        </div>
        // <ProSidebarProvider>
        //     <AdminNavbar/>
        // </ProSidebarProvider>
    )
}