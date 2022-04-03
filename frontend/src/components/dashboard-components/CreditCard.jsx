import { toast } from "react-toastify"
import { useEffect, useState } from "react";
import { axpost, displayErrors } from "../../utility/Utility"

export default function (props) {

    const [creditCards, setCreditCards] = useState([]);
    const [effector, setEffector] = useState(false);

    useEffect(() => {
        axpost("/api/account/details/creditCards", {
            customerNo: props.customerNo.value,
            ticket: props.ticket.value
        }).then(res => {
            setCreditCards(res.data);
        }).catch(err => displayErrors(err));
    }, [effector]);

    function payCCDebt(e, cardNumber) {
        const amount = e.target.parentNode.querySelector("input[name='amount']").value;
        axpost("/api/payment/creditCard", {
            customerNo: props.customerNo.value,
            ticket: props.ticket.value,
            cardNumber: cardNumber,
            amount: amount
        }).then(response => {
            toast.success(response.data);
            setEffector(!effector);
        }).catch(err => displayErrors(err));
    }

    function spendCC(cardNumber, amount) {
        axpost("/api/payment/spend", {
            customerNo: props.customerNo.value,
            ticket: props.ticket.value,
            cardNumber: cardNumber,
            amount: amount
        }).then(response => {
            toast.success(response.data);
            setEffector(!effector);
        }).catch(err => displayErrors(err));
    }

    function listCreditCards() {
        if (creditCards.length > 0) {
            return (
                <table className="my-credit-cards">
                    <tbody>
                        <tr> <th>Card number</th> <th>Card Balance</th> </tr>
                        {creditCards.map(c =>
                            <tr className="credit-card">
                                <td>{c.cardNumber}</td> <td>{c.balance}</td>
                                <td> <div className="button" onClick={() => spendCC(c.cardNumber, 100)}>-100 $</div> </td>
                                <td> <input name="amount" type="number" defaultValue={0} step={0.1} /> <div className="button" onClick={(e) => payCCDebt(e, c.cardNumber)}> Pay Debts</div> </td>
                            </tr>)}
                    </tbody>
                </table>
            )
        } else {
            return (
                <div className="my-credit-cards">You don't have any credit cards yet.</div>
            )
        }
    }

    function applyForCC() {
        axpost("/api/apply/creditCard", {
            customerNo: props.customerNo.value,
            ticket: props.ticket.value
        }).then(res => {
            toast.success(res.data);
            setEffector(!effector);
        }).catch(err => displayErrors(err));
    }

    return (
        <div className="relative-content">
            <div className="relative-center">
                <center>
                    <div className="button" onClick={applyForCC}>Apply for a new credit card</div>
                    {listCreditCards()}
                </center>
            </div>
        </div>
    )
}