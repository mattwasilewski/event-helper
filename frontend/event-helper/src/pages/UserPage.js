import Navbar from "../components/utils/Navbar";
import Footer from "../components/utils/Footer";
import {AboutUser} from "../components/user/AboutUser";
import {OrganizedByUser} from "../components/event/OrganizedByUser";

export default function UserPage() {


    return(
        <div>
            <Navbar/>
            <AboutUser userId={"bf199137-647e-4808-ae17-9110a1072e90"}/>
            {/*<OrganizedByUser userId={"bf199137-647e-4808-ae17-9110a1072e90"}/>*/}
            <Footer/>
        </div>
    )
}


