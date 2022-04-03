import { useState, useEffect } from "react";
import { axpost } from "../../utility/Utility";

export default function (props) {

    const [summary, setSummary] = useState({ name: "", customerNo: "", balance: "", totalDebt: "" });

    useEffect(() => {
        fetchSummary();
    }, []);

    function fetchSummary() {
        axpost("/api/account/summary", {
            customerNo: props.customerNo.value,
            ticket: props.ticket.value
        }).then(res => setSummary(res.data));
    }

    function summaryToJsx() {
        return (
            <div className="relative-content">
                <div className="relative-center">
                    <table>
                        <tbody>
                            <tr><td>Name</td><td>:</td><td>{summary.name}</td></tr>
                            <tr><td>Customer NO</td><td>:</td><td>{summary.customerNo}</td></tr>
                            <tr><td>Balance</td><td>:</td><td>{summary.balance}</td></tr>
                            <tr><td>Total Debt</td><td>:</td><td>{summary.totalDebt}</td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }

    return summaryToJsx();

}