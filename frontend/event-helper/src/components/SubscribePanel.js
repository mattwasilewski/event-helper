export default function SubscribePanel() {


    return (
        <div className="subscribe-panel">
            <div className="letter-icon">
                <div className="send-icon">
                    <span className="material-symbols-rounded">send</span>
                </div>
            </div>
            <div className="subscription-text">Subscribe to get information and latest events on our website</div>
            <input type="email" id="emails" placeholder="✉  Your email" name="emails" multiple></input>
            <button className="submit-email" type="submit">Submit</button>
        </div>
    )
}