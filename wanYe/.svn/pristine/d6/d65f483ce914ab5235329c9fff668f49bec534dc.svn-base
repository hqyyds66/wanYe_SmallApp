function openWin(title, width, height, url) {
        var top_ = ($(window).height()) / 5;
        var left_ = ($(window).width()) / 5;
        var width_ = width;
        var height_ = height;
        var css = "<style>.box1{ width:" + (width - 4) + "px; border:1px solid #808080; padding:1px; margin:0 auto;} .box2{ width:" + (width - 20) + "px;background-color:#fff; padding:8px;}.box3{ width:" + (width - 20) + "px; margin:0 auto; }.box3 span{ font-size:13px; color:#6ba3b6; font-family:font-family:Microsoft YaHei; font-weight:bold;height: 24px;vertical-align: top;}.boxpad{float: right;}.boxpad img:hover{cursor: pointer;}.box4{ width:" + (width - 27) + "px;height:" + (height - 45) + "px; border:1px solid #b6cfd6; background-color:#fff;font-size:13px;font-family:font-family:Microsoft YaHei; line-height:22px;}</style>"
        var content = css + "<div class='box1'><div class='box2'><div class='box3' style='text-align: left;'><span id='reg_title' style='float: left;'>" + title + "</span><span class='boxpad'><img id='close_iframe_img' src='images/close.gif' align='middle'onclick='doClose()' /></span></div><iframe class='box4' style='width:" + (width - 32) + "px' src='" + url + "' width='100%' height='100%' topmargin='0' leftmargin='0' marginheight='0' scrolling='auto' marginwidth='0' frameborder='no' ></iframe></div></div>";
        $.blockUI({
                message: content,
                css: {
                        width: width_,
                        height: height_,
                        top: top_,
                        left: left_,
                        border: '0px solid #aaa'
                }
        });
}

function doClose() {
        $("#hidden_iframe").attr("src", "");
        try {
                $("div")[0].focus();
        } catch(e) {}
        $.unblockUI();
}