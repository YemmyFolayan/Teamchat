<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logged in</title>
<script>
function getParameterByName(name) {
    res = window.location.href.split("=");
    location.href = "/Yammer/yammerlogin?token="+res[1];
}
</script>
</head>
<body onload="getParameterByName('access_token')">
</body>
</html>