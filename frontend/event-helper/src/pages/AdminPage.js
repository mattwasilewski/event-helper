import AdminNavbar from "../components/AdminNavbar";
import {ProSidebarProvider} from "react-pro-sidebar";
import EventList from "../components/event/EventList";

export default function AdminPage() {




    return (
        <EventList/>
        // <ProSidebarProvider>
        //     <AdminNavbar/>
        // </ProSidebarProvider>
    )
}