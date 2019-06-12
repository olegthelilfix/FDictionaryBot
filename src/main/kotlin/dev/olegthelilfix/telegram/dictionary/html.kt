package dev.olegthelilfix.telegram.dictionary

val htmlCode = "<html>\n" +
        "<style>\n" +
        "    @import url(\"https://fonts.googleapis.com/css?family=Open+Sans:100,200,400,400i,700\");\n" +
        "\n" +
        "    body {\n" +
        "        display: flex;\n" +
        "        height: 100vh;\n" +
        "        width: 100vw;\n" +
        "        background: #fffff9;\n" +
        "    }\n" +
        "    .card {\n" +
        "        width: 400px;\n" +
        "        height: 240px;\n" +
        "        margin: auto;\n" +
        "        background-color: #d2d2d8;\n" +
        "        background-image: url('data:image/svg+xml,%3Csvg xmlns=\"http://www.w3.org/2000/svg\" width=\"4\" height=\"4\" viewBox=\"0 0 8 8\"%3E%3Cg fill=\"%239C92AC\" fill-opacity=\"0.14\"%3E%3Cpath fill-rule=\"evenodd\" d=\"M0 0h4v4H0V0zm4 4h4v4H4V4z\"/%3E%3C/g%3E%3C/svg%3E');\n" +
        "        box-sizing: border-box;\n" +
        "        overflow: hidden;\n" +
        "        position: relative;\n" +
        "        transition: 0.3333333s;\n" +
        "        /* \ttransition-timing-function: ease-in; */\n" +
        "        box-shadow: 1px 1px 2px #aaa;\n" +
        "        border-radius: 3px;\n" +
        "    }\n" +
        "    .card:hover{\n" +
        "        background-color: #d4d4da;\n" +
        "        box-shadow: 7px 4px 15px #aaa;\n" +
        "        transform: translate(-2px, -2px) rotate(-1.5deg);\n" +
        "        cursor: pointer;\n" +
        "    }\n" +
        "    .content {\n" +
        "        position: absolute;\n" +
        "        top: 44px;\n" +
        "        left: 90px;\n" +
        "        color: #828292;\n" +
        "        font-family: 'Open sans', sans-serif;\n" +
        "    }\n" +
        "    .content h2 {\n" +
        "        color: #efe9ca;\n" +
        "        line-height: 1em;\n" +
        "        letter-spacing: 1px;\n" +
        "        font-weight: bold;\n" +
        "        font-size: 1.6em;\n" +
        "        margin-bottom: 8px;\n" +
        "        text-shadow: -1px -1px 1px #d8d8da, 1px 1px 1px #aaa;\n" +
        "    }\n" +
        "    .content .job-title {\n" +
        "        font-style: italic;\n" +
        "    }\n" +
        "    .content .contact-details {\n" +
        "        margin-top: 22px;\n" +
        "        font-weight: 100;\n" +
        "        line-height: 1.85em;\n" +
        "        font-size: 0.9em;\n" +
        "    }\n" +
        "    .contact-details-item:before {\n" +
        "        font-family: \"Font Awesome 5 Free\";\n" +
        "        font-weight: 900;\n" +
        "        font-size: 1.5em;\n" +
        "        color: #efe9ca;\n" +
        "        padding-right: 10px;\n" +
        "        text-shadow: -1px -1px 1px #d8d8da, 1px 1px 1px #aaa;\n" +
        "    }\n" +
        "    .email:before {\n" +
        "        content: \"\\f0e0\";;\n" +
        "    }\n" +
        "    .phone:before {\n" +
        "        content: \"\\f095\";\n" +
        "    }\n" +
        "    .corner {\n" +
        "        content: \"\";\n" +
        "        display: block;\n" +
        "        width: 80px;\n" +
        "        height: 80px;\n" +
        "        position: relative;\n" +
        "        border-radius: 50%;\n" +
        "        background: #efe9ca;\n" +
        "        transition: background 0.3333333s;\n" +
        "    }\n" +
        "    .top-left {\n" +
        "        top: -40px;\n" +
        "        right: 40px;\n" +
        "    }\n" +
        "    .top-right {\n" +
        "        top: -120px;\n" +
        "        left: 360px;\n" +
        "    }\n" +
        "    .bottom-left {\n" +
        "        top: 40px;\n" +
        "        right: 40px;\n" +
        "    }\n" +
        "    .bottom-right {\n" +
        "        top: -40px;\n" +
        "        left: 360px;\n" +
        "    }\n" +
        "    .corner:hover {\n" +
        "        background: #bfbfcf\n" +
        "    }\n" +
        "</style>\n" +
        "<body>\n" +
        "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.8.2/css/all.css\" integrity=\"sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay\" crossorigin=\"anonymous\">\n" +
        "\n" +
        "<div class=\"card\">\n" +
        "    <div class=\"corner top-left\"></div>\n" +
        "    <div class=\"corner top-right\"></div>\n" +
        "    <div class=\"content\">\n" +
        "        <h2 class=\"name\">Andrew Card</h2>\n" +
        "        <span class=\"job-title\">Web Developer</span>\n" +
        "        <div class=\"contact-details\">\n" +
        "            <span class=\"contact-details-item email\">a.card@webdevs-r-us.org</span><br/>\n" +
        "            <span class=\"contact-details-item phone\">+44 7654 123456</span>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"corner bottom-left\"></div>\n" +
        "    <div class=\"corner bottom-right\"></div>\n" +
        "</div>\n" +
        "</body>\n" +
        "</html>"