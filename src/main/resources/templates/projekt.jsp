<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>PPJ projekt</title>

    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.4/css/bootstrap.min.css"
          th:href="@{/webjars/bootstrap/3.3.4/css/bootstrap.min.css}"
          rel="stylesheet" media="screen"/>

    <link rel="stylesheet" type="text/css" href="/css/style.css"/>

    <script src="http://cdn.jsdelivr.net/webjars/jquery/2.1.4/jquery.min.js"
            th:src="@{/webjars/jquery/2.1.4/jquery.min.js}"></script>

</head>
<body>
<div class="container">
    <div class="row row-centered bottom-buffer">
        <h1>PPJ projekt</h1>
    </div>
    <div class="row row-centered text-center bottom-buffer">
        <img class="img-rounded" th:src="@{${path+''+image.location}}"/>
    </div>
    <div class="row row-centered text-center">
        <div class="col-md-4 col-md-offset-4">
            <div class="well well-sm">
                <p th:text="${image.name + ' - ' + image.user.name+ ', ' + image.createdDate}"></p>
                <p>
                <div th:each="mytag, row : ${image.tagSet}">
                    <span th:text="${'#'+mytag.value}"></span>
                </div>
                </p>
            </div>
        </div>
    </div>
    <div class="row row-centered text-center bottom-buffer">
        <div class="btn-group">
            <a class="btn btn-info" th:text="'Likes ('+${image.likesCount}+')'"
               th:href="${'/likeImg?id='+image.id}"></a>
            <a class="btn btn-info" th:text="'Dislikes ('+${image.dislikesCount}+')'"
               th:href="${'/dislikeImg?id='+image.id}"></a>
            <a class="btn btn-info" th:text="Next" th:href="${'/projekt?id='+actual}"></a>
        </div>
    </div>
    <div class="row row-centered text-center">
        <div class="col-md-8 col-md-offset-2">
            <table class="table table-striped">
                <tr th:each=" comment : ${image.commentSet}">
                    <td>
                        <p th:text="${comment.comment + ' - ' + comment.author.name+ ', ' + comment.createdDate}"></p>
                    </td>
                    <td>
                        <div class="btn-group">
                            <a class="btn btn-info" th:text="'Likes ('+${comment.likesCount}+')'"
                               th:href="@{'/likeComment'(id=${image.id}, commentId=${comment.id})}"></a>
                            <a class="btn btn-info" th:text="'Dislikes ('+${comment.dislikesCount}+')'"
                               th:href="@{'/dislikeComment'(id=${image.id}, commentId=${comment.id})}"></a>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>