function isExtModule(){
    var strPath = window.document.location.pathname;
    var path = strPath.substring(strPath.indexOf("/", 2), strPath.length);
    var basePath = "../";
    if (path.indexOf("ext") > 0) {
        basePath = "../../../";
    }
    return basePath;
}
