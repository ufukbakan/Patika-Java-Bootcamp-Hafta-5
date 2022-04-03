import { Link } from "react-router-dom";
export default function (props) {
    function loggedInNavbar() {
        return (
            <nav>
                <ul>
                    <li> <div onClick={props.logoutFunc} className="button">Logout</div> </li>
                </ul>
            </nav>
        )
    }

    function notLoggedInNavbar() {
        return (
            <nav>
                <ul>
                    <li>
                        <Link to="/signup"><div className="button">Signup Page</div></Link>
                    </li>
                    <li>
                        <Link to="/login"><div className="button">Login Page</div></Link>
                    </li>
                </ul>
            </nav>
        )
    }

    return props.loggedIn.value == true ? loggedInNavbar() : notLoggedInNavbar();

}