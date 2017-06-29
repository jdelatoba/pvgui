var loadAjaxContent = function (target, urlBase) {
    $(target).load(urlBase, function (response, status, xhr) {
        if (status == "error") {
            var msg = "Sorry but there was an error: ";
            $("#ajax-content").html("<div class='col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main'>" + msg + xhr.status + " " + xhr.statusText + "</div>");
        }
        $(target).hide().fadeIn('slow');
    });
};
var updateContent = function (State) {
    loadAjaxContent('#ajax-content', State.url);
};

$(function () {
    $.fn.dataTable.pipeline = function (opts) {
        var conf = $.extend({
            pages: 5,
            url: '',
            data: null,
            method: 'GET'
        }, opts);
        var cacheLower = -1;
        var cacheUpper = null;
        var cacheLastRequest = null;
        var cacheLastJson = null;

        return function (request, drawCallback, settings) {
            var ajax = false;
            var requestStart = request.start;
            var drawStart = request.start;
            var requestLength = request.length;
            var requestEnd = requestStart + requestLength;

            if (settings.clearCache) {
                ajax = true;
                settings.clearCache = false;
            }
            else if (cacheLower < 0 || requestStart < cacheLower || requestEnd > cacheUpper) {
                ajax = true;
            }
            else if (JSON.stringify(request.order) !== JSON.stringify(cacheLastRequest.order) ||
                    JSON.stringify(request.columns) !== JSON.stringify(cacheLastRequest.columns) ||
                    JSON.stringify(request.search) !== JSON.stringify(cacheLastRequest.search)
                    ) {
                ajax = true;
            }

            cacheLastRequest = $.extend(true, {}, request);

            if (ajax) {
                if (requestStart < cacheLower) {
                    requestStart = requestStart - (requestLength * (conf.pages - 1));

                    if (requestStart < 0) {
                        requestStart = 0;
                    }
                }

                cacheLower = requestStart;
                cacheUpper = requestStart + (requestLength * conf.pages);

                request.start = requestStart;
                request.length = requestLength * conf.pages;

                if ($.isFunction(conf.data)) {
                    var d = conf.data(request);
                    if (d) {
                        $.extend(request, d);
                    }
                }
                else if ($.isPlainObject(conf.data)) {
                    $.extend(request, conf.data);
                }

                settings.jqXHR = $.ajax({
                    "type": conf.method,
                    "url": conf.url,
                    "data": request,
                    "dataType": "json",
                    "cache": false,
                    "success": function (json) {
                        cacheLastJson = $.extend(true, {}, json);

                        if (cacheLower != drawStart) {
                            json.data.splice(0, drawStart - cacheLower);
                        }
                        json.data.splice(requestLength, json.data.length);

                        drawCallback(json);
                    }
                });
            }
            else {
                json = $.extend(true, {}, cacheLastJson);
                json.draw = request.draw; // Update the echo for each response
                json.data.splice(0, requestStart - cacheLower);
                json.data.splice(requestLength, json.data.length);

                drawCallback(json);
            }
        }
    };
    $.fn.dataTable.Api.register('clearPipeline()', function () {
        return this.iterator('table', function (settings) {
            settings.clearCache = true;
        });
    });

    /*
     $.extend($.fn.dataTableExt.oStdClasses, {
     "sFilterInput": "form-control",
     });
     */
    $(document).on('click', '[data-toggle="offcanvas"]', function (e) {
        $('.row-offcanvas').toggleClass('active');
        if ($('.row-offcanvas').hasClass('active')) {
            $('.main').removeClass('col-md-12').addClass('col-md-10');
            $('.main').removeClass('col-sm-12').addClass('col-sm-9');
        }
        else {
            $('.main').removeClass('col-md-10').addClass('col-md-12');
            $('.main').removeClass('col-sm-9').addClass('col-sm-12');
        }
        e.preventDefault();
    });
    $(".acc a").click(function (e) {
        if ($(this).siblings("ul").length == 1) {
            var d = $(this).siblings("ul").css('display');
            if (d == 'none') {
                $(this).find("span").removeClass('glyphicon glyphicon-menu-right').addClass('glyphicon glyphicon-menu-down');
            }
            else {
                $(this).find("span").removeClass('glyphicon glyphicon-menu-down').addClass('glyphicon glyphicon-menu-right');
            }
            $(this).siblings("ul").stop().slideToggle("slow");
            e.preventDefault();
        }
    });

    $(document).ajaxSend(function () {
        $('#loading-indicator').show();
    });
    $(document).ajaxComplete(function () {
        $('#loading-indicator').hide();
    });
    var History = window.History;
    
    if (History.enabled) {
        var rootUrl = History.getRootUrl();
        History.Adapter.bind(window, 'statechange', function () {
            updateContent(History.getState());
        });
        $(document).on('click', 'a', function (e) {
            if ($(this).hasClass("ajax")) {
                var urlPath = $(this).prop("href");
                var title = $(this).text();
                History.pushState({urlPath: urlPath}, urlPath.replace(rootUrl,''), urlPath);
                e.preventDefault();
                return false;
            }
        });
    }
});
