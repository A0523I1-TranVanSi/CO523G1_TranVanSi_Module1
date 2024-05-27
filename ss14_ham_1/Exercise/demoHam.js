function square(number){
    let result = number * number
    return result 
}
let squareOf2 = square(2)
document.write(squareOf2+"<br>")
let squareOf5 = square(5)
document.write(squareOf5+"<br>")

function caculate(){
    let input = document.getElementById("input").value
    let result = square(input)
    document.getElementById("result").innerHTML = result
}