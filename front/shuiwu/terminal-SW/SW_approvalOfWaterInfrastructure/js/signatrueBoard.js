/**
 * by:zjj
 */
! function () {
    "use strict"
    var global;
    /**
     * 挂载全局
     */
    global = (function () {
        return this || (0, eval)('this');
    }());
    if (typeof module !== "undefined" && module.exports) {
        module.exports = SignatureBoardPlug;
    } else if (typeof define === "function" && define.amd) {
        define(function () {
            return SignatureBoardPlug;
        });
    } else {
        !('SignatureBoardPlug' in global) && (global.SignatureBoardPlug = SignatureBoardPlug);
    }
    /**
     * config<Object>
     *       canvas<ele>:"",                    //元素类型必须为canvas
     *       width:"",                          //画布宽度 一般不传，页面中设置
     *       height:"",                         //画布高度 一般不传，页面中设置
     *       color< rgb,keyword,rgba,16 >:"",   //画笔颜色  合法颜色值
     *       getSigntrue< ele >:"",             //获取签名  
     *       clearBtn< ele >:"",                //清除画布
     *       lineWidth< Number >:"",            //默认 5
     *       capStyle<Canvas lineCap Type>:"",  //默认 round
     *       joinStyle<Canvas lineCap Type>:"", //默认 round
     *       penType:"touch"                    //默认 mouse鼠标  touch触屏手指
     * 
     *  < ele > => 元素选择，接受id和class选择器，使用class选择器须保证class的唯一性，多个class默认第一个元素
     *      推荐使用id选择器
     */
    function SignatureBoardPlug(config) {
        var _this = this;
        var clearBtn = this.getElement(config.clearBtn);
        var getSigntrue = this.getElement(config.getSigntrue);
        var eventName = this.eventType(config.penType);
        this.canvas = this.getElement(config.canvas);
        this.Signatrue = null;
        this.ctx = this.canvas.getContext("2d");
        this.config = config;
        this.isMousedown = false;
        this.canvas.style.userSelect = "none";
        config.width !== undefined ? this.canvas.width = parseInt(config.width, 10) : "";
        config.height !== undefined ? this.canvas.height = parseInt(config.height, 10) : "";
        this.lastPos = {};
        this.startPos = {};
        global.addEventListener(eventName.move, function (e) {
            if (e && e.preventDefault) {
                e.preventDefault();
            } else {
                window.event.returnValue = false;
            }
            if (_this.canvas === e.target && _this.isMousedown === true) {
                config.penType === "touch" ? e = e.touches[0] : "";
                _this.getRelativePos(e.clientX, e.clientY);
                _this.drawSignatrue();
            } else {
                _this.isMousedown = false;
            }
        });
        this.canvas.addEventListener(eventName.down, function (e) {
            config.penType === "touch" ? e = e.touches[0] : "";
            _this.getRelativePos(e.clientX, e.clientY);
            _this.isMousedown = true;
        });
        this.canvas.addEventListener(eventName.up, function () {
            _this.isMousedown = false;
        });
        try {
            getSigntrue.addEventListener(eventName.down, function (e) {
                _this.Signatrue = _this.canvas.toDataURL("image/png");
            });
        } catch (error) {}
        try {
            clearBtn.addEventListener(eventName.down, function (e) {
                _this.ctx.clearRect(0, 0, _this.canvas.width, _this.canvas.height);
            });
        } catch (error) {};
    };
    SignatureBoardPlug.prototype.drawSignatrue = function () { //进行签名
        this.ctx.lineWidth = this.config.lineWidth || 5;
        this.ctx.lineCap = this.config.capStyle || "round";
        this.ctx.lineJoin = this.config.joinStyle || "round";
        this.ctx.beginPath();
        this.ctx.moveTo(this.lastPos.x, this.lastPos.y);
        this.ctx.lineTo(this.startPos.x, this.startPos.y);
        this.ctx.strokeStyle = this.config.color || "#999";
        this.ctx.stroke();
        this.lastPos = JSON.parse(JSON.stringify(this.startPos));
    };
    SignatureBoardPlug.prototype.getRelativePos = function (x, y) { //获取鼠标相对画布位置
        var pos = this.canvas.getBoundingClientRect();
        if (this.isMousedown === false) {
            this.lastPos.x = Math.round(x - pos.left);
            this.lastPos.y = Math.round(y - pos.top);
        }
        this.startPos.x = Math.round(x - pos.left);
        this.startPos.y = Math.round(y - pos.top);
    }
    SignatureBoardPlug.prototype.getElement = function (selector) { //选择器 支持id和class
        if (!selector) {
            return false;
        }
        selector = selector.trim();
        var eleType = selector.slice(0, 1);
        var selectorName = selector.substring(1);
        if (selectorName.length <= 0) {
            return false;
        }
        if (eleType === "#") {
            return document.getElementById(selectorName);
        } else if (eleType === ".") {
            return document.getElementsByClassName(selectorName)[0];
        } else {
            console.error("This selector is not supported. by getElement(selector)");
            return false;
        }
    };
    SignatureBoardPlug.prototype.eventType = function (event) { //指定移动端或者PC端
        if (event === "click" || !event) {
            return {
                down: "mousedown",
                move: "mousemove",
                up: "mouseup"
            }
        } else if (event === "touch") {
            return {
                down: "touchstart",
                move: "touchmove",
                up: "touchend"
            }
        } else {
            console.error("This event is not supported. by eventType(event)");
            return;
        }
    }
}();