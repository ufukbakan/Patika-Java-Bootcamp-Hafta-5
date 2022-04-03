import { useState, useEffect } from "react"
import { toast } from "react-toastify";
import { axpost, displayErrors } from "../../utility/Utility";

export default function (props) {

    const [credits, setCredits] = useState([]);
    const [effector, setEffector] = useState(false);
    const [creditAmount, setCreditAmount] = useState(1000);

    useEffect(() => {
        axpost("/api/account/details/credits", {
            customerNo: props.customerNo.value,
            ticket: props.ticket.value
        }).then(res => {
            setCredits(res.data);
        }).catch(err => displayErrors(err));
    }, [effector]);

    function listCredits() {
        if (credits.length > 0) {
            return (
                <table className="my-credits">
                    <tbody>
                        <tr> <th>Credit Id</th><th>Amount</th><th>Amount Paid Back</th><th>Debt</th> </tr>
                        {
                            credits.map(c =>
                                <tr> <td>{c.id}</td> <td>{c.amount}</td> <td>{c.paidBack}</td> <td>{c.amount - c.paidBack}</td> 
                                    <td> <input type="number" defaultValue={0} step={0.1} /> <div onClick={(e)=>payBack(e,c.id)} className="button">Pay Back Amount</div> </td>
                                </tr>)
                        }
                    </tbody>
                </table>
            )
        } else {
            return (
                <div className="my-credits">
                    You don't used any credits
                </div>
            )
        }
    }

    function applyForCredit() {
        axpost("/api/apply/credit", {
            customerNo: props.customerNo.value,
            ticket: props.ticket.value,
            amount: creditAmount
        }).then(response => {
            toast.success(response.data);
            setEffector(!effector);
        }).catch(err => displayErrors(err));
    }

    function handleCAC(e) {
        setCreditAmount(e.target.value);
    }

    function payBack(e, creditId){
        axpost("/api/payment/credit", {
            customerNo: props.customerNo.value,
            ticket: props.ticket.value,
            amount: e.target.parentNode.querySelector("input").value,
            creditId: creditId
        }).then(response=>{
            toast.success(response.data);
            setEffector(!effector);
        }).catch(err => displayErrors(err));
    }


    return (
        <div className="relative-content">
            <div className="relative-center">
                <center>
                    <input type="number" value={creditAmount} onChange={handleCAC} />
                    <br/>
                    <div onClick={applyForCredit} className="button">Apply for a new credit</div>
                </center>
                {listCredits()}
            </div>
        </div>
    )
}