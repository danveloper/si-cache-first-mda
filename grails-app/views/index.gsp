<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <style>
        .post {
            max-width: 350px;
            background-color: #ccc;
            border: 1px solid #000;
            margin-top: 25px;
            min-height: 125px;
        }
    </style>
    <r:require module="application" />
    <r:script>
        function newPost() {
            var txt = $("#content").val();
            $.post("/post/create", {content: txt})
                    .error(
                        function() {
                          $("#error").show();
                        }
                    );
        }

        _.templateSettings = {
            interpolate: /\{\{(.+?)\}\}/g
        };

        var tmpl = _.template($("#post-tmpl").html());
        function callback(resp) {
            if (resp.status == 200) {
                var msg = resp.responseBody;
                try {
                  var obj = JSON.parse(msg);
                  $("#posts").append(tmpl(obj));
                } catch (e) {}
            }
        }
        $.atmosphere.subscribe("ws://localhost:8080/atmosphere/ws", callback,
                $.atmosphere.request = {transport:'websocket', fallbackTransport:'streaming'});

        $("#post").click(newPost);
        $("#close").click(function() {$("#error").hide()});

    </r:script>
    <r:layoutResources/>
</head>
<body>

<div id="error" style="display: none">
    There was an error.
    <a href="#" id="close">&times;</a>
</div>

<div class="new-post">
    <h3>New Post</h3>
    <input type="text" id="content">
    <a href="#" id="post">Post!</a>
</div>

<div id="posts">

</div>

<script type="text/x-underscore-template" id="post-tmpl">
    <div class="post" id="post-{{id}}">
        <h3>Post</h3>
        <span>{{content}}</span>
    </div>
</script>
<r:layoutResources />

</body>
</html>