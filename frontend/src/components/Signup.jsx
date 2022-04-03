import { Navigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useState } from "react";
import { axpost, displayErrors, gatherFormData } from "../utility/Utility"

export default function (props) {

    const [signedUp, setSignedUp] = useState(false);

    function trySignup(e) {
        e.preventDefault();
        axpost("/api/signup", gatherFormData()).then(response => {
            toast.success(response.data);
            setSignedUp(true);
        })
            .catch(err => displayErrors(err));
    }

    function render() {
        if (props.loggedIn.value == true) {
            return <Navigate to="/dashboard"></Navigate>;
        }
        else if (signedUp) {
            return <Navigate to="/login"></Navigate>;
        } else {
            return (
                <div className="absolute-center">
                    <form>
                        <label><input placeholder="Enter your name" name="name" type="text" /></label>
                        <label><input placeholder="Enter your identity number" type="text" name="id" /></label>
                        <label><input placeholder="Enter password" type="password" name="password" /></label>
                        <label><div className="label-text">Birthdate: </div><input type="date" name="birthdate" /> </label>
                        <center>
                            <div className="button" onClick={trySignup}>Signup</div>
                        </center>
                    </form>
                </div>
            )
        }
    }

    return (render());
}