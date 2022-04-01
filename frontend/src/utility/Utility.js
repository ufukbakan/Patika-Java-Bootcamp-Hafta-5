export function gatherFormData(){
    const inputs = document.querySelectorAll("form input");
    let data = {};
    inputs.forEach(input => {
        data[input.name] = input.value;
    });
    return data;
}