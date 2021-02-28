$().ready(function () {

    $(".nav>#btn_home").click(function () {
        window.location.href = "./home.html";
    });

    $(".nav>#btn_personal").mouseenter(function () {
        var $sub = $(this).children(".sub");
        $sub.stop();
        $sub.slideDown(500);

    });

    $(".nav>#btn_personal").mouseleave(function () {
        var $sub = $(this).children(".sub");

        $sub.stop();
        $sub.slideUp(500);
    });

    $(".sub>#info").click(function () {
        window.location.href = "./info.html";
    });


    $(".sub>#order").click(function () {
        window.location.href = "./order.html";
    });

console.log(new Date())
    $.ajax({
        type: "POST",
        url: "/cuisine/findByMerchantId",
        data: {
            id: getCookie("merchantid")
        },
        success: (result) => {
            console.log(result); //打印服务端返回的数据(调试用)
            $.each(result, function (index, value) {
                var id = value.id;
                var cuisinename = value.cuisinename;
                var description = value.description;
                var unitprice = value.unitprice;
                var merchantid = value.merchantid;
                var imgpath = value.imgpath;
                console.log(imgpath);
                var cuisine = createEle(id, cuisinename, description, unitprice, merchantid,imgpath);
                $("#messageList").prepend(cuisine);
            });

            $('body').ctshop();

        },
        error: function (e) {
            console.log(e);
            alert(e);
        },
    });

    function createEle(id, cuisinename, description, unitprice, merchantid,imgpath) {
        var $cuisine = $('<div class="col-sm-4"><div class="cuisine" cuisineid="'+id+'"><div class="image"><img src="/imgresources/'+imgpath+'" alt=""></div><div class="inner"><a href="#" class="btn btn-motive cuisine-button">🛒</a><h2 class="cuisine-name">' + cuisinename + '</h2><p class="cuisine-description">' + description + '</p><span class="cuisine-unitprice">￥' + unitprice + '</span></div></div></div>');
        return $cuisine;
    };

})

! function (t, e, a, n) {
    "use strict";

    function i(e, a) {
        this.element = t(e),
            this.cart = this.element.find(".cart"),
            this.cuisine = this.element.find(".cuisine"),
            this.init()
    }
    var r = "ctshop";
    t.extend(i.prototype, {
        init: function () {
            var a = this;

            if (a.cart = a.cart.append("<ol/>").find("ol"), e.sessionStorage.cart) {
                a.cart.find("li").each(function () {
                    t(this).remove()
                }), a.remove_cart_buttons(), a.storage_clear()
            }
            a.cart_empty(), a.cuisine.each(function (e) {
                var n = t(this),
                    i = n.find(".cuisine-name"),
                    r = n.find(".cuisine-unitprice"),
                    s = n.find(".cuisine-button"),
                    c = i.text(),
                    o = r.text().replace(",", "").replace(/[^\d.]/g, "");
                s.on("click touchend", function (t) {
                    t.preventDefault(), c = a.check_if_title(i, c), o = a.check_if_price(r, o);
                    var s, u = n.attr("cuisineid"),
                        l = a.cart.find("li"),
                        _ = a.cart.find("[cuisineid=" + u + "]"),
                        p = _.attr("cuisineid"),
                        d = l.find(".cart-input");
                    d.val();
                    return 0 !== l.length ? p !== u ? a.add_to_cart(c, o, u)
                        : _.find("input").val(function (t, e) { return parseInt(e, 10) + 1 })
                        : (a.add_to_cart(c, o, u), a.add_cart_buttons(), a.append_total()), l = a.cart.find("li"), d = l.find(".cart-input"), s = l.length, a.quantity_change(d), a.calculate_total_value(), a.remove_item()
                })
            })
        },
        add_to_cart: function (t, e, a) {
            var n = this;
            n.cart.append('<li class="animated fadeIn" cuisineid=' + a + "><span class=cart-name>" + t + "</span><span class=cart-price>￥" + e + '</span><input type="number" min="1" value="1" class=' + "cart-input><button class=cart-remove>&times;</button></li>");
            n.storage_set();
        },
        add_cart_buttons: function () {
            var t = this;
            t.cart.parent().append('<button class="cart-clear">清空</button>'), t.cart.parent().append('<button type="submit" class="cart-checkout">提交</button>');
            t.clear_cart();
            t.checkout_button();
        },
        checkout_button: function () {
            var a = this;
            t(".cart-checkout").unbind().on("click", function () {
                a.storage_set();
                var order = {
                    customerid : getCookie("id"),
                    merchantid : getCookie("merchantid"),
                    totalprice : parseFloat(a.cart.parent().find(".cart-total").text().replace(/^\D+/g, "")),
                    orderItems : a.storage_get()
                };
                $.ajax({
                    type:"POST",
                    url: "/order/addOrder",
                    data: JSON.stringify(order),
                    contentType: "application/json",
                    success: (result) => {
                        alert("提交成功"); //打印服务端返回的数据(调试用)
                    },
                    error: function (e) {
                        console.log(e);
                        alert("提交失败");
                    },
                })

            })
        },
        remove_item: function () {
            var a = this;
            t(".cart-remove").unbind().on("click", function () {
                var e = t(this),
                    n = a.cart.find("li").length;
                e.parent().remove(), a.calculate_total_value(), a.storage_set(), 1 === n && (a.remove_cart_buttons(), a.total.remove(), a.storage_clear())
            })
        },
        clear_cart: function () {
            var e = this;
            t(".cart-clear").on("click", function () {
                e.cart.find("li").each(function () {
                    t(this).remove()
                }), e.remove_cart_buttons(), e.total.remove(), e.storage_clear(), e.cart_empty()
            })
        },
        remove_cart_buttons: function () {
            var t = this;
            t.cart.parent().find("button").remove()
        },
        check_if_title: function (t, e) {
            return 0 !== t.find("select").length ? e = 0 !== t.find("span").length ? t.find("span").text() + " - " + t.find("option:selected").val() : t.find("option:selected").text() : 0 !== t.find("input").length ? e = t.find("input").val() : t.is(":empty") && (e = t.val()), e
        },
        check_if_price: function (t, e) {
            return 0 !== t.find("select").length ? e = parseFloat(t.find("option:selected").val().replace(/^\D+/g, "")) : 0 !== t.find("input").length && (e = parseFloat(t.find("input").val().replace(/^\D+/g, ""))), e
        },
        calculate_total_value: function (e) {
            var a = this,
                n = 0,
                i = 0;
            return a.cart.find("li").each(function () {
                var e = parseFloat(t(this).find(".cart-price").text().replace(/^\D+/g, "")),
                    r = t(this).find("input").val();
                i += e * r, n = parseFloat(i.toString()).toFixed(2)
            }), a.total.html("￥" + n), a.cart_empty()
        },
        quantity_change: function (t, e) {
            var a = this;
            t.change(function () {
                a.calculate_total_value(e), a.storage_set()
            })
        },
        append_total: function () {
            var t = this;
            t.total = t.cart.parent().prepend('<span class="cart-total">').find(".cart-total");
        },
        storage_set: function () {
            var t = this,
                orderItems = [];
            $(t.cart).find("li").each(function () {
                var e = $(this),
                    n = e.attr("cuisineid"),
                    r = parseFloat(e.find(".cart-price").text().replace(/^\D+/g, "")),
                    s = e.find("input").val(),
                    c = {
                        cuisineid: n,
                        cuisinenum: s,
                        price: r
                    };
                orderItems.push(c);
            });
            e.sessionStorage.setItem("cart", JSON.stringify(orderItems));
        },
        storage_get: function () {
            var t = e.sessionStorage.cart;
            return JSON.parse(t)
        },
        storage_clear: function () {
            e.sessionStorage.removeItem("cart")
        },
        cart_empty: function () {
            var t = this;
            t.cart.find("li").length ? $(".cart-empty").remove() : t.cart.parent().prepend('<span class="cart-empty">' + "购物车为空</span>")
        }
    }), t.fn[r] = function (e) {
        return this.each(function () {
            t.data(this, "plugin_" + r) || t.data(this, "plugin_" + r, new i(this, e))
        })
    }
}(jQuery, window, document);
