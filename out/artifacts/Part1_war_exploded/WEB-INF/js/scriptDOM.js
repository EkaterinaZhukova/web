;(function(dom) {
    dom.createForm = function createForm(name, elements) {
        let form = document.forms[name];
        elements.forEach(function(element) {
            console.log(element);
            console.log(element.type);
            let blockDiv = document.createElement("div");
            switch (element.type) {
                case 'textline':
                    let label = document.createElement("label");
                    label.setAttribute('for',name + element.name);
                    label.textContent = element.text;
                    label.name = element.name;
                    label.id = element.id;
                    blockDiv.appendChild(label);
                    break;
                case 'input':
                    let inputField = document.createElement("input");
                    inputField.setAttribute('type', 'text');
                    inputField.classList.add(element.class);
                    inputField.classList.add("form-control");
                    inputField.id = element.name;
                    inputField.name = element.name;
                    blockDiv.appendChild(inputField);
                    break;
                case 'button':
                    let button = document.createElement("input");
                    button.classList.add("btn");
                    button.classList.add("btn-primary");
                    button.textContent = "Submit";
                    button.setAttribute('type','submit');
                    button.id = element.name;
                    button.name = element.name;
                    blockDiv.appendChild(button);
                    break;
                default:
                    break;
            }
            form.appendChild(blockDiv);

        });
    }

}(createTelephoneStationDOM = {}));