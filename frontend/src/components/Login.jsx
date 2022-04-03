import { Navigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useState } from "react";
import { axpost, displayErrors, gatherFormData } from "../utility/Utility";

export default function (props) {

    const [ticket, setTicket] = useState(randomClientTicket);

    function randomClientTicket() {
        const tchars = "!#$%&'()*+,-./0123456789:=?@ABCDEFGHIJKLMNOPQRSTUVWXYZ\^_`abcdefghijklmnopqrstuvwxyz|~";
        let result = "";
        for (let i = 0; i < 32; i++) {
            result += tchars[Math.round(Math.random() * (tchars.length - 1))];
        }
        return result;
    }

    function tryLogin(e) {
        e.preventDefault();
        axpost("/api/login", gatherFormData()).then(
            response => {
                if (response.data != -1) {
                    toast.success("Logged in");
                    props.ticket.set(ticket);
                    props.customerNo.set(response.data);
                    props.loggedIn.set(true);
                } else {
                    toast.warn("Couldn't find any customer matching credentials");
                }
            }
        ).catch(err => displayErrors(err));
    }

    return props.loggedIn.value == true ? (<Navigate to='/dashboard'></Navigate>) :
        (
            <div className="absolute-center">
                <form>
                    <label><input placeholder="Enter your id" type="text" name="id" /></label>
                    <label><input placeholder="Enter your password" type="password" name="password" /></label>
                    <label><input type="password" name="ticket" id="ticket" className="hide" readOnly={true} value={ticket} /></label>
                    <center><div className="button" onClick={tryLogin}>Login</div></center>
                </form>
            </div>
        )
}