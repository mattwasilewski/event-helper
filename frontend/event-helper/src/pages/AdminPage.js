import Admin from "../components/Admin";
import {ProSidebarProvider} from "react-pro-sidebar";

export default function AdminPage() {



    return(
        <ProSidebarProvider>
            <Admin/>
        </ProSidebarProvider>
    )
}