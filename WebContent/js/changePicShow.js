/**
 * 
 */

function ckphoto(e) {
	var myHead = document.getElementById("album_pic");
	let exAllowed = [ "bmp", "gif", "png", "jpg", "ico","jpeg" ];
	let fileStr = e.target.value;
	let fileName, fileExt;

	let dot = fileStr.lastIndexOf(".");
	fileExt = fileStr.substr(dot + 1);

	if (exAllowed.indexOf(fileExt) == -1) {
		document.getElementById("showpic").innerHTML = "非圖檔 ,格式不合<br>";
	} else {
		let photo = URL.createObjectURL(myHead.files[0]);
		document.getElementById("showpic").innerHTML = "<img src =" + photo
				+ " width='200px' height='170px'>";
	}
}
document.getElementById("album_pic").onchange = ckphoto;



