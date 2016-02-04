<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/justified-nav.css">

</head>
<body>
	<div class="form-group">
		<input type="text" class="form-control" id="txtSearch" name="keyword"
			autocomplete="off">
	</div>
	<div id="div1"></div>
<script>
	//將資料存到陣列中
	var datas = [ '' ];
	var show;
	window.addEventListener("load", init, false);
	function init() {
		var txt = document.getElementById("txtSearch");
		txt.addEventListener("keyup", getData, false);
		show = document.getElementById("div1");
	}
	function getData() {
		show.style.display = "block";
		if (show.childNodes.length > 0) {
			show.removeChild(show.childNodes[0]);
		}
		var xml = new XMLHttpRequest();
		var Search = document.getElementById("txtSearch").value;
		if (Search.trim() == "") {
			datas = [ '' ];
		} else if (Search != null) {
			xml.onreadystatechange = function() {
				if (xml.readyState == 4) {
					if (xml.status == 200) {
						datas = JSON.parse(xml.responseText);
						var eleDiv = document.createElement("div");
						eleDiv.className = "list-group";
						for (var j = 0, max = datas.length; j < max; j++) {
							var txtBtn = document.createTextNode(datas[j]);
							var eleBtn = document.createElement("text");
							eleBtn.className = "list-group-item";
							eleBtn.setAttribute("type", "text");
							eleBtn.appendChild(txtBtn);
							eleBtn.addEventListener("click",
								function() {
									document.myData.keyword.value = this.firstChild.nodeValue;
									show.style.display = "none";
								}, false)
							eleDiv.appendChild(eleBtn);
						}
						show.appendChild(eleDiv);
					}
				}
			}
		}
		xml.open("get", "/projHY20160201/JsonSimpleDemo?keyword=" + Search, true);
		xml.send();
	}
</script>

</body>
</html>