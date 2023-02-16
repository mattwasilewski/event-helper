import React, {useState} from "react";

export default function GalleryPage() {



    const [user, setUser] = useState({name: ""});
    const [error, setError] = useState("");



    return (
        <>
            <div>
                <h1>GALLERY</h1>
            </div>
        </>
    )

}