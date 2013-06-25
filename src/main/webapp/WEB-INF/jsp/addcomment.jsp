<%@ taglib prefix="rr" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Comment</title>
    <link rel="stylesheet" type="text/css" href="/recipe_roots_styles.css">
</head>
<body>
<div id="banner">
    <div id="title">Recipe Roots</div>
    <rr:navigation></rr:navigation>
</div>
<div id="section_main">
    <h1>Leave A Comment</h1>
    <form action="/addcomment" method="post">

        User: ${user.name}<br>
        <br>
        <textarea cols="40" rows="16" name="comment">This is where your comment goes...</textarea>
        <br>
        <input type="submit" value="Submit">

    </form>
</div>

</body>
</html>