import {useState} from "react";
import {Events} from "./Events";

export default function RecommendedEvents() {

    const [popularButtonStyle, setPopularButtonStyle] = useState("active-button");
    const [musicButtonStyle, setMusicButtonStyle] = useState("disabled-button");
    const [festivalsButtonStyle, setFestivalsButtonStyle] = useState("disabled-button");

    function popularButtonClick() {
        setPopularButtonStyle(function (prevState) {
                if (prevState === "disabled-button")
                    setFestivalsButtonStyle("disabled-button");
                setMusicButtonStyle("disabled-button");

                return "active-button";
            }
        )

    }

    function musicButtonClick() {
        setMusicButtonStyle(function (prevState) {
                if (prevState === "disabled-button")
                    setPopularButtonStyle("disabled-button");
                setFestivalsButtonStyle("disabled-button");
                return "active-button";
            }
        )

    }

    function festivalsButtonClick() {
        setFestivalsButtonStyle(function (prevState) {
                if (prevState === "disabled-button")
                    setPopularButtonStyle("disabled-button");
                setMusicButtonStyle("disabled-button");
                return "active-button";
            }
        )

    }


    return (
        <>
            <div className="recommended-events-buttons">
                <button className={popularButtonStyle} onClick={popularButtonClick}>Expo</button>
                <button className={musicButtonStyle} onClick={musicButtonClick}>Concert</button>
                <button className={festivalsButtonStyle} onClick={festivalsButtonClick}>Festival</button>
            </div>
            <Events/>
        </>
    )
}