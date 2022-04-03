import { useState } from "react";
import { Navigate } from "react-router-dom";
import Credit from "./dashboard-components/Credit";
import CreditCard from "./dashboard-components/CreditCard";
import Summary from "./dashboard-components/Summary";
import TransferMoney from "./dashboard-components/TransferMoney";

export default function (props) {

    const [content, setContent] = useState(<Summary customerNo={{value: props.customerNo.value}} ticket={{value: props.ticket.value}}></Summary>);

    function options() {
        return (
            <div>
                <div onClick={() => setContent(<Summary customerNo={{value: props.customerNo.value}} ticket={{value: props.ticket.value}}></Summary>)} className="button">Summary</div>
                <div onClick={() => setContent(<TransferMoney customerNo={{value: props.customerNo.value}} ticket={{value: props.ticket.value}}></TransferMoney>)} className="button">Transfer Money Page</div>
                <div onClick={() => setContent(<CreditCard customerNo={{value: props.customerNo.value}} ticket={{value: props.ticket.value}}></CreditCard>)} className="button">Credit Card Page</div>
                <div onClick={() => setContent(<Credit customerNo={{value: props.customerNo.value}} ticket={{value: props.ticket.value}}></Credit>)} className="button">Credit Page</div>
            </div>
        )
    }

    return props.loggedIn.value == false ? <Navigate to='/'></Navigate> :
        (
            <div>
                {options()}
                {content}
            </div>
        )
}