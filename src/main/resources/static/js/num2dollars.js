var num2dollars = function (num) {
    for (var cnt = 0; cnt < num;cnt++){
        var node = document.createElement("i");
        node.className = "icon-dollar";
        document.currentScript.parentNode.appendChild(node);
    }

};