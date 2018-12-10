var num2stars = function (num) {
    var rating_ceil = Math.ceil(num);
    var rating_floor = Math.floor(num);
    for (var cnt = 0; cnt < rating_floor;cnt++)
        document.write("<i class='icon-star'></i>");

    if(num > rating_floor)
        document.write("<i class='icon-star-half-full'></i>");


    for(var cnt = 0; cnt < 5 - rating_ceil; cnt++)
        document.write("<i class='icon-star-o'></i>");
};