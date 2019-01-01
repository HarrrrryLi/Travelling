var num2stars = function (num) {
    var rating_ceil = Math.ceil(num);
    var rating_floor = Math.floor(num);
    for (var cnt = 0; cnt < rating_floor;cnt++){
        var node = document.createElement("i");
        node.className = "icon-star";
        document.currentScript.parentNode.appendChild(node);
    }

    if(num > rating_floor){
        var node = document.createElement("i");
        node.className = "icon-star-half-full";
        document.currentScript.parentNode.appendChild(node);
    }

    for(var cnt = 0; cnt < 5 - rating_ceil; cnt++){
        var node = document.createElement("i");
        node.className = "icon-star-o";
        document.currentScript.parentNode.appendChild(node);
    }
};