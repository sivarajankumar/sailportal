<html>
    <head>
        <title>Upload a file please</title>
    </head>
    <body>
    
    <img src="/webapp/${userDetails.profilePictureUrl}"/>
    ${hei}
    <table>
        <tr>
            <td>Name</td>
            <td>${userDetails.firstname}</td>
        </tr>
    </table>

        <h1>Please upload a file</h1>
        <form method="post" action="upload.html" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit"/>
        </form>
    </body>
</html>