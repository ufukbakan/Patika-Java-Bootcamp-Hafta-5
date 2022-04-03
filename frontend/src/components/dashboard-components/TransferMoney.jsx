import { toast } from "react-toastify";
import { axpost, displayErrors, gatherFormData } from "../../utility/Utility";

export default function (props) {

    function transferMoney() {
        console.log(gatherFormData());
        axpost("/api/payment/send", gatherFormData() ).then(r => toast.success(r.data)).catch(err => displayErrors(err));
    }

    return (
        <div className="relative-content">
            <div className="relative-center">
                <form>
                    <input className="hide" type="password" readOnly={true} value={props.customerNo.value} name="fromCustomerNo" />
                    <input className="hide" type="password" readOnly={true} value={props.ticket.value} name="fromCustomerTicket" />
                    <label><input placeholder="Enter receiver's customer number" className="" type="text" name="toCustomerNo" /></label>
                    <label><input placeholder="Enter amount" type="number" step={0.01} name="amount" /></label>
                    <div className="button" onClick={transferMoney}>Transfer Money</div>
                </form>
            </div>
        </div>
    )
}