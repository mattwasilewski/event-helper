import React, {useEffect, useRef, useState} from 'react'
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
import '../css/ChatRoom.css';
import authSerivce from "../auth.serivce";

let stompClient =null;
const EventChat = (props) => {
    const eventId = props.eventId;
    const isLoggedIn = authSerivce.getCurrentUser();
    let userDetails = authSerivce.parseJwt(isLoggedIn.value);
    const [publicChats, setPublicChats] = useState([]);
    const [tab,setTab] =useState("CHATROOM");
    const [userData, setUserData] = useState({
        username: userDetails.sub,
        receivername: eventId,
        connected: false,
        message: ''
    });
    useEffect(() => {
        console.log(userData);
    }, [userData]);

    useEffect(() => {
        scrollToNewestMessage();
    }, [publicChats]);

    const scrollToNewestMessage = () => {
        const chatMessagesList = document.querySelector('.chat-messages');
        if (chatMessagesList) {
            const lastMessage = chatMessagesList.lastChild;
            if (lastMessage) {
                lastMessage.scrollIntoView();
            }
        }
    };

    const loadChatFromDb = async () => {
        const response = await fetch(`http://localhost:3000/api/chat/event/${props.eventId}`);
        const data = await response.json();
        setPublicChats(data);
    }

    const connect =()=>{
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = over(Sock);
        stompClient.connect({},onConnected, onError);
    }

    const onConnected = async () => {
        setUserData({...userData, "connected": true});
        stompClient.subscribe('/user/' + eventId + '/private', onPrivateMessage);
        await loadChatFromDb();
        userJoin();
    }

    const userJoin=()=>{
        let chatMessage = {
            senderName: userData.username,
            status:"JOIN"
        };
        stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
    }


    const onPrivateMessage = (payload)=>{
        let payloadData = JSON.parse(payload.body);
        switch(payloadData.status){
            case "JOIN":
                break;
            case "MESSAGE":
                setPublicChats(prevPublicChats => [...prevPublicChats, payloadData]);
                break;
        }
    }

    const onError = (err) => {
        console.log(err);

    }

    const handleMessage =(event)=>{
        const {value}=event.target;
        setUserData({...userData,"message": value});
    }


    const sendPrivateValue=()=>{
        if (stompClient) {
            let chatMessage = {
                senderName: userData.username,
                receiverName:eventId,
                message: userData.message,
                status:"MESSAGE"
            };
            stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
            setUserData({...userData,"message": ""});
        }
    }
    const registerUser=()=>{
        connect();
    }
    return (
        <div className="container">
            {userData.connected?
                <div className="chat-box">
                    {tab==="CHATROOM" && <div className="chat-content">
                        <ul className="chat-messages">
                            {publicChats.map((chat,index)=>(
                                <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                                    {chat.senderName !== userData.username && <div className="avatar">{chat.senderName}</div>}
                                    <div className="message-data">{chat.message}</div>
                                    {chat.senderName === userData.username && <div className="avatar self">{chat.senderName}</div>}
                                </li>
                            ))}
                        </ul>

                        <div className="send-message">
                            <input type="text" className="input-message" placeholder="enter the message" value={userData.message} onChange={handleMessage} />
                            <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
                        </div>
                    </div>}
                </div>
                :
                    <button type="button" onClick={registerUser} className="send-button">
                        Join Chat
                    </button>
            }
        </div>
    )
}

export default EventChat