import Navbar from "../components/utils/Navbar";
import Footer from "../components/utils/Footer";
import {AboutUser} from "../components/user/AboutUser";
import {AssignedEvents} from "../components/user/AssignedEvents";

export default function UserPage() {



    return(
        <div>
            <Navbar/>
            <AboutUser userId={"b0c79ff6-6a47-4541-acbc-a2cc0e9138cd"}/>
            {/*<AssignedEvents userId={"b0c79ff6-6a47-4541-acbc-a2cc0e9138cd"}/>*/}
            <Footer/>
        </div>
    )
}