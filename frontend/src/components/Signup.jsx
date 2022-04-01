import axios from "axios";
import { toast } from "react-toastify";
import { gatherFormData } from "../utility/Utility"

export default function () {

    const Iaxios = axios.create({timeout: 4000});

    function trySignup() {
        Iaxios.post("/api/people", gatherFormData()).then(
            response => toast(response)
        ).catch( err => toast.error(err.message));
    }

    return (
        <div className="absolute-center">
            <form>
                <label><input placeholder="Enter your name" name="name" type="text" /></label>
                <label><input placeholder="Enter your identity number" type="text" name="id" /></label>
                <label><input placeholder="Enter password" type="password" name="password" /></label>
                <center>
                    <div className="button" onClick={trySignup}>Signup</div>
                </center>
            </form>
        </div>
    )
}