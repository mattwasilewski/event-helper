import Navbar from "../components/utils/Navbar";
import Footer from "../components/utils/Footer";
import {AboutUser} from "../components/user/AboutUser";

export default function UserPage() {



    return(
        <div>
            <Navbar/>
            <AboutUser userId={"ef077324-4b6f-4919-9c2b-7dbe55fdcc70"}/>
            {/*<AssignedEvents userId={"b0c79ff6-6a47-4541-acbc-a2cc0e9138cd"}/>*/}
            <Footer/>
        </div>
    )
}