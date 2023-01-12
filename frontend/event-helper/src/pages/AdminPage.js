import AdminNavbar from "../components/AdminNavbar";
import {ProSidebarProvider} from "react-pro-sidebar";

export default function AdminPage() {

    return (
        <ProSidebarProvider>
            <AdminNavbar/>
        </ProSidebarProvider>
    )
}