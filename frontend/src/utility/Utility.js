import axios from "axios";
import { toast } from "react-toastify";

const IAxios = axios.create({ timeout: 4000 });

export const axpost = IAxios.post;
export const axget = IAxios.get;

export function gatherFormData() {
    const inputs = document.querySelectorAll("form input");
    let data = {};
    inputs.forEach(input => {
        data[input.name] = input.value;
    });
    return data;
}

export function displayErrors(err) {
    if (err.response && err.response.data && err.response.data.errors) {
        err.response.data.errors.forEach(error => { toast.error(error.defaultMessage); });
    } else if (err.response && err.response.data && err.response.data.message) {
        toast.error(err.response.data.message);
    } else if(err.response && err.response.data){
        toast.error(err.response.data);
    }else{
        toast.error(err.message);
    }
}