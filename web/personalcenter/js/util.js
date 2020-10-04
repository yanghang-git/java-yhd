function getHrefParam(targetHref) {
	let paramMap = new Map();
	let param = targetHref.split('?')[1];
	if (param == null) return paramMap;
	let paramList = param.split('&');
	for (let item of paramList) {
		let tempArr = item.split('=');
		if (paramMap.has(tempArr[0])) {
			paramMap.get(tempArr[0]).push(tempArr[1]);
		} else {
			paramMap.set(tempArr[0], [tempArr[1]]);
		}
	}
	return paramMap;
}