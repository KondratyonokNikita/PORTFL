/**
 * Created by Samsung on 26.04.2017.
 */
document.getElementById("upload_widget_opener").addEventListener("click", function () {
    cloudinary.openUploadWidget({
            cloud_name: 'kondrat', upload_preset: 'tduqgmt1',
            sources: ['local', 'url'], keep_widget_open: false,
            resource_type: 'image'
        },
        function (error, result) {
            if (result != undefined) {
                result.forEach(function (entry) {
                    Algorithmia.client("sim1hfbFteryaPuLDik5zQ03nUu1")
                        .algo("algo://sfw/NudityDetection/1.1.6")
                        .pipe(entry.url)
                        .then(function (output) {
                            console.log(output);
                            if (output.result.nude == 'false') {
                                console.log('loading');
                                $.ajax({
                                    url: "/loadPhoto",
                                    data: '[' + JSON.stringify(entry) + ']',
                                    type: "POST",
                                    dataType: "json",
                                    contentType: "application/json"
                                });
                            }
                        });
                });
            }
            console.log(error, result);
        }
    );
}, false);