/**
 * by:zhang
 * 
 * EXTPaging只接收一个参数<Object> ,参数有两个key
 *      {
 *          data< Object || Array>:"",//你要对其进行分页的数据
 *          quantity< number >:""//指定多少条数据为一页
 *      }
 * 返回值<Object> 
 *      {
 *          data< Array >:arr, //分好页的参数
 *          length< Number >: ,//原数据总长度
 *          pages< Number >:,  //总页数  
 *      }
 */
(function (undefined) {
    "use strict"
    var _global;
    var EXTPaging = function (object) {
        var LaterData = [];
        var Total = Math.ceil(object.data.length / object.quantity);
        if (!object.data) {

            console.log("分页数据不合法！")
            return false;

        }
        if ((object.data instanceof Object) === false) {

            console.log("分页数据格式不支持！")
            return false;

        }
        if ((object.data instanceof Array) === true) {
            var arrList = object.data;
            if (Total <= 1) {

                LaterData = object.data;

                return {
                    pages: Total,
                    length: object.data.length,
                    data: LaterData
                };

            } else if (Total > 1) {

                for (var i = 0; i < Total; i++) {

                    LaterData[i] = [];

                    for (var j = i * object.quantity; j < (i + 1) * object.quantity; j++) {
                        if (j === arrList.length - 1) {
                            LaterData[i].push(arrList[j]);
                            return {
                                pages: Total,
                                length: object.data.length,
                                data: LaterData
                            };
                        }
                        if (object.data[j] === undefined || j === object.data.length - 1) {
                            return {
                                pages: Total,
                                length: object.data.length,
                                data: LaterData
                            };
                        } else {

                            LaterData[i].push(object.data[j]);

                        }

                    }

                }
            }
        }
        if ((object.data instanceof Array) === false && (object.data instanceof Object) === true) {
            //返回数据为对象时
            var list = Object.keys(object.data);
            Total = Math.ceil(list.length / object.quantity);

            if (Total <= 1) {
                LaterData[0] = {};
                for (var i = 0; i < object.quantity + 1; i++) {

                    if (object.data[list[i]] === undefined || i === list.length) {

                        return {
                            pages: Total,
                            length: list.length,
                            data: LaterData
                        };

                    } else {

                        LaterData[0][list[i]] = object.data[list[i]];

                    }
                }
            } else {
                for (var k = 0; k < Total; k++) {

                    LaterData[k] = {};

                    for (var l = k * object.quantity; l < (k + 1) * object.quantity; l++) {
                        if (list.length % object.quantity === 0 && l === list.length - 1) {
                            LaterData[k][list[l]] = object.data[list[l]];
                            return {
                                pages: Total,
                                length: list.length,
                                data: LaterData
                            };
                        }
                        if (object.data[list[l]] === undefined || l === list.length) {

                            return {
                                pages: Total,
                                length: list.length,
                                data: LaterData
                            };

                        } else {

                            LaterData[k][list[l]] = object.data[list[l]];

                        }
                    }
                }
            }
        }
    }

    _global = (function () {
        return this || (0, eval)('this');
    }());
    if (typeof module !== "undefined" && module.exports) {
        module.exports = EXTPaging;
    } else if (typeof define === "function" && define.amd) {
        define(function () {
            return EXTPaging;
        });
    } else {
        !('EXTPaging' in _global) && (_global.EXTPaging = EXTPaging);
    }
})();