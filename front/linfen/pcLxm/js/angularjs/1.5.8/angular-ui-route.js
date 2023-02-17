/**
 * State-based routing for AngularJS 1.x
 * NOTICE: This monolithic bundle also bundles the @uirouter/core code.
 *         This causes it to be incompatible with plugins that depend on @uirouter/core.
 *         We recommend switching to the ui-router-core.js and ui-router-angularjs.js bundles instead.
 *         For more information, see https://ui-router.github.io/blog/uirouter-for-angularjs-umd-bundles
 * @version v1.0.19
 * @link https://ui-router.github.io
 * @license MIT License, http://www.opensource.org/licenses/MIT
 */
! function (t, e) {
    "object" == typeof exports && "undefined" != typeof module ? e(exports, require("angular")) : "function" == typeof define && define.amd ? define(["exports", "angular"], e) : e(t["@uirouter/angularjs"] = {}, t.angular)
}(this, function (d, t) {
    "use strict";
    var e = angular,
        b = t && t.module ? t : e;

    function c(r) {
        var t = [].slice.apply(arguments, [1]),
            n = r.length;
        return function t(e) {
            return e.length >= n ? r.apply(null, e) : function () {
                return t(e.concat([].slice.apply(arguments)))
            }
        }(t)
    }

    function r() {
        var r = arguments,
            n = r.length - 1;
        return function () {
            for (var t = n, e = r[n].apply(this, arguments); t--;) e = r[t].call(this, e);
            return e
        }
    }

    function s() {
        for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
        return r.apply(null, [].slice.call(arguments).reverse())
    }
    var w = function (e) {
            return function (t) {
                return t && t[e]
            }
        },
        y = c(function (t, e, r) {
            return r && r[t] === e
        }),
        R = function (t) {
            return s.apply(null, t.split(".").map(w))
        },
        h = function (r) {
            return function () {
                for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
                return !r.apply(null, t)
            }
        };

    function n(r, n) {
        return function () {
            for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
            return r.apply(null, t) && n.apply(null, t)
        }
    }

    function i(r, n) {
        return function () {
            for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
            return r.apply(null, t) || n.apply(null, t)
        }
    }
    var f = function (r) {
            return function (t) {
                return t.reduce(function (t, e) {
                    return t && !!r(e)
                }, !0)
            }
        },
        l = function (r) {
            return function (t) {
                return t.reduce(function (t, e) {
                    return t || !!r(e)
                }, !1)
            }
        },
        p = function (e) {
            return function (t) {
                return null != t && t.constructor === e || t instanceof e
            }
        },
        o = function (e) {
            return function (t) {
                return e === t
            }
        },
        v = function (t) {
            return function () {
                return t
            }
        };

    function a(e, r) {
        return function (t) {
            return t[e].apply(t, r)
        }
    }

    function m(r) {
        return function (t) {
            for (var e = 0; e < r.length; e++)
                if (r[e][0](t)) return r[e][1](t)
        }
    }
    var u = Object.prototype.toString,
        g = function (e) {
            return function (t) {
                return typeof t === e
            }
        },
        _ = g("undefined"),
        E = h(_),
        $ = function (t) {
            return null === t
        },
        S = i($, _),
        C = g("function"),
        T = g("number"),
        P = g("string"),
        k = function (t) {
            return null !== t && "object" == typeof t
        },
        O = Array.isArray,
        x = function (t) {
            return "[object Date]" === u.call(t)
        },
        V = function (t) {
            return "[object RegExp]" === u.call(t)
        };

    function j(t) {
        if (O(t) && t.length) {
            var e = t.slice(0, -1),
                r = t.slice(-1);
            return !(e.filter(h(P)).length || r.filter(h(C)).length)
        }
        return C(t)
    }
    var I = n(k, s(w("then"), C)),
        H = function (t) {
            return function () {
                throw new Error(t + "(): No coreservices implementation for UI-Router is loaded.")
            }
        },
        A = {
            $q: void 0,
            $injector: void 0
        },
        q = "object" == typeof self && self.self === self && self || "object" == typeof global && global.global === global && global || void 0,
        D = q.angular || {},
        F = D.fromJson || JSON.parse.bind(JSON),
        N = D.toJson || JSON.stringify.bind(JSON),
        U = D.forEach || function (e, r, t) {
            if (O(e)) return e.forEach(r, t);
            Object.keys(e).forEach(function (t) {
                return r(e[t], t)
            })
        },
        L = Object.assign || Pt,
        M = D.equals || kt;

    function B(t) {
        return t
    }

    function G() {}

    function W(e, n, r, t, i) {
        void 0 === i && (i = !1);
        var o = function (t) {
            return e()[t].bind(r())
        };
        return (t = t || Object.keys(e())).reduce(function (t, e) {
            var r;
            return t[e] = i ? (r = e, function () {
                return n[r] = o(r), n[r].apply(null, arguments)
            }) : o(e), t
        }, n)
    }
    var z = function (t, e) {
            return L(Object.create(t), e)
        },
        J = c(Q);

    function Q(t, e) {
        return -1 !== t.indexOf(e)
    }
    var K = c(Y);

    function Y(t, e) {
        var r = t.indexOf(e);
        return 0 <= r && t.splice(r, 1), t
    }
    var Z = c(X);

    function X(t, e) {
        return t.push(e), e
    }
    var tt = function (e) {
        return e.slice().forEach(function (t) {
            "function" == typeof t && t(), K(e, t)
        })
    };

    function et(t) {
        for (var e = [], r = 1; r < arguments.length; r++) e[r - 1] = arguments[r];
        var n = L.apply(void 0, [{}].concat(e.reverse()));
        return L(n, it(t || {}, Object.keys(n)))
    }
    var rt = function (t, e) {
        return L(t, e)
    };

    function nt(t, e) {
        var r = [];
        for (var n in t.path) {
            if (t.path[n] !== e.path[n]) break;
            r.push(t.path[n])
        }
        return r
    }

    function it(t, e) {
        var r = {};
        for (var n in t) - 1 !== e.indexOf(n) && (r[n] = t[n]);
        return r
    }

    function ot(r, t) {
        return Object.keys(r).filter(h(J(t))).reduce(function (t, e) {
            return t[e] = r[e], t
        }, {})
    }

    function at(t, e) {
        return ft(t, w(e))
    }

    function ut(t, r) {
        var e = O(t),
            n = e ? [] : {},
            i = e ? function (t) {
                return n.push(t)
            } : function (t, e) {
                return n[e] = t
            };
        return U(t, function (t, e) {
            r(t, e) && i(t, e)
        }), n
    }

    function st(t, r) {
        var n;
        return U(t, function (t, e) {
            n || r(t, e) && (n = t)
        }), n
    }
    var ct = ft;

    function ft(t, r, n) {
        return n = n || (O(t) ? [] : {}), U(t, function (t, e) {
            return n[e] = r(t, e)
        }), n
    }
    var ht = function (e) {
            return Object.keys(e).map(function (t) {
                return e[t]
            })
        },
        lt = function (t, e) {
            return t && e
        },
        pt = function (t, e) {
            return t || e
        },
        vt = function (t, e) {
            return t.concat(e)
        },
        dt = function (t, e) {
            return O(e) ? t.concat(e.reduce(dt, [])) : mt(t, e)
        };

    function mt(t, e) {
        return t.push(e), t
    }
    var yt = function (t, e) {
            return J(t, e) ? t : mt(t, e)
        },
        gt = function (t) {
            return t.reduce(vt, [])
        },
        wt = function (t) {
            return t.reduce(dt, [])
        },
        _t = St,
        $t = St;

    function St(r, n) {
        return void 0 === n && (n = "assert failure"),
            function (t) {
                var e = r(t);
                if (!e) throw new Error(C(n) ? n(t) : n);
                return e
            }
    }
    var bt = function (e) {
        return Object.keys(e).map(function (t) {
            return [t, e[t]]
        })
    };

    function Rt() {
        for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
        if (0 === t.length) return [];
        for (var r = t.reduce(function (t, e) {
                return Math.min(e.length, t)
            }, 9007199254740991), n = [], i = function (e) {
                switch (t.length) {
                    case 1:
                        n.push([t[0][e]]);
                        break;
                    case 2:
                        n.push([t[0][e], t[1][e]]);
                        break;
                    case 3:
                        n.push([t[0][e], t[1][e], t[2][e]]);
                        break;
                    case 4:
                        n.push([t[0][e], t[1][e], t[2][e], t[3][e]]);
                        break;
                    default:
                        n.push(t.map(function (t) {
                            return t[e]
                        }))
                }
            }, o = 0; o < r; o++) i(o);
        return n
    }

    function Et(t, e) {
        var r, n;
        if (O(e) && (r = e[0], n = e[1]), !P(r)) throw new Error("invalid parameters to applyPairs");
        return t[r] = n, t
    }

    function Ct(t) {
        return t.length && t[t.length - 1] || void 0
    }

    function Tt(t, e) {
        return e && Object.keys(e).forEach(function (t) {
            return delete e[t]
        }), e || (e = {}), L(e, t)
    }

    function Pt(t) {
        for (var e = 1; e < arguments.length; e++) {
            var r = arguments[e];
            if (r)
                for (var n = Object.keys(r), i = 0; i < n.length; i++) t[n[i]] = r[n[i]]
        }
        return t
    }

    function kt(t, e) {
        if (t === e) return !0;
        if (null === t || null === e) return !1;
        if (t != t && e != e) return !0;
        var r = typeof t;
        if (r !== typeof e || "object" !== r) return !1;
        var n, i, o = [t, e];
        if (f(O)(o)) return i = e, (n = t).length === i.length && Rt(n, i).reduce(function (t, e) {
            return t && kt(e[0], e[1])
        }, !0);
        if (f(x)(o)) return t.getTime() === e.getTime();
        if (f(V)(o)) return t.toString() === e.toString();
        if (f(C)(o)) return !0;
        if ([C, O, x, V].map(l).reduce(function (t, e) {
                return t || !!e(o)
            }, !1)) return !1;
        var a = {};
        for (var u in t) {
            if (!kt(t[u], e[u])) return !1;
            a[u] = !0
        }
        for (var u in e)
            if (!a[u]) return !1;
        return !0
    }
    var Ot, xt = function (t) {
            return t.catch(function (t) {
                return 0
            }) && t
        },
        Vt = function (t) {
            return xt(A.$q.reject(t))
        },
        jt = function () {
            function e(t) {
                this.text = t, this.glob = t.split(".");
                var e = this.text.split(".").map(function (t) {
                    return "**" === t ? "(?:|(?:\\.[^.]*)*)" : "*" === t ? "\\.[^.]*" : "\\." + t
                }).join("");
                this.regexp = new RegExp("^" + e + "$")
            }
            return e.is = function (t) {
                return !!/[!,*]+/.exec(t)
            }, e.fromString = function (t) {
                return e.is(t) ? new e(t) : null
            }, e.prototype.matches = function (t) {
                return this.regexp.test("." + t)
            }, e
        }(),
        It = function () {
            function t(t, e) {
                void 0 === t && (t = []), void 0 === e && (e = null), this._items = t, this._limit = e, this._evictListeners = [], this.onEvict = Z(this._evictListeners)
            }
            return t.prototype.enqueue = function (t) {
                var e = this._items;
                return e.push(t), this._limit && e.length > this._limit && this.evict(), t
            }, t.prototype.evict = function () {
                var e = this._items.shift();
                return this._evictListeners.forEach(function (t) {
                    return t(e)
                }), e
            }, t.prototype.dequeue = function () {
                if (this.size()) return this._items.splice(0, 1)[0]
            }, t.prototype.clear = function () {
                var t = this._items;
                return this._items = [], t
            }, t.prototype.size = function () {
                return this._items.length
            }, t.prototype.remove = function (t) {
                var e = this._items.indexOf(t);
                return -1 < e && this._items.splice(e, 1)[0]
            }, t.prototype.peekTail = function () {
                return this._items[this._items.length - 1]
            }, t.prototype.peekHead = function () {
                if (this.size()) return this._items[0]
            }, t
        }();
    (Ot = d.RejectType || (d.RejectType = {}))[Ot.SUPERSEDED = 2] = "SUPERSEDED", Ot[Ot.ABORTED = 3] = "ABORTED", Ot[Ot.INVALID = 4] = "INVALID", Ot[Ot.IGNORED = 5] = "IGNORED", Ot[Ot.ERROR = 6] = "ERROR";
    var Ht = 0,
        At = function () {
            function n(t, e, r) {
                this.$id = Ht++, this.type = t, this.message = e, this.detail = r
            }
            return n.isRejectionPromise = function (t) {
                return t && "function" == typeof t.then && p(n)(t._transitionRejection)
            }, n.superseded = function (t, e) {
                var r = new n(d.RejectType.SUPERSEDED, "The transition has been superseded by a different transition", t);
                return e && e.redirected && (r.redirected = !0), r
            }, n.redirected = function (t) {
                return n.superseded(t, {
                    redirected: !0
                })
            }, n.invalid = function (t) {
                return new n(d.RejectType.INVALID, "This transition is invalid", t)
            }, n.ignored = function (t) {
                return new n(d.RejectType.IGNORED, "The transition was ignored", t)
            }, n.aborted = function (t) {
                return new n(d.RejectType.ABORTED, "The transition has been aborted", t)
            }, n.errored = function (t) {
                return new n(d.RejectType.ERROR, "The transition errored", t)
            }, n.normalize = function (t) {
                return p(n)(t) ? t : n.errored(t)
            }, n.prototype.toString = function () {
                var t, e = (t = this.detail) && t.toString !== Object.prototype.toString ? t.toString() : Bt(t);
                return "Transition Rejection($id: " + this.$id + " type: " + this.type + ", message: " + this.message + ", detail: " + e + ")"
            }, n.prototype.toPromise = function () {
                return L(Vt(this), {
                    _transitionRejection: this
                })
            }, n
        }();

    function qt(t, e) {
        return e.length <= t ? e : e.substr(0, t - 3) + "..."
    }

    function Dt(t, e) {
        for (; e.length < t;) e += " ";
        return e
    }

    function Ft(t) {
        return t.replace(/^([A-Z])/, function (t) {
            return t.toLowerCase()
        }).replace(/([A-Z])/g, function (t) {
            return "-" + t.toLowerCase()
        })
    }

    function Nt(t) {
        var e = Ut(t),
            r = e.match(/^(function [^ ]+\([^)]*\))/),
            n = r ? r[1] : e,
            i = t.name || "";
        return i && n.match(/function \(/) ? "function " + i + n.substr(9) : n
    }

    function Ut(t) {
        var e = O(t) ? t.slice(-1)[0] : t;
        return e && e.toString() || "undefined"
    }
    var Lt = At.isRejectionPromise,
        Mt = m([
            [_, v("undefined")],
            [$, v("null")],
            [I, v("[Promise]")],
            [Lt, function (t) {
                return t._transitionRejection.toString()
            }],
            [function (t) {
                return k(t) && !O(t) && t.constructor !== Object && C(t.toString)
            }, function (t) {
                return t.toString()
            }],
            [j, Nt],
            [v(!0), B]
        ]);

    function Bt(t) {
        var e = [];

        function r(t) {
            if (k(t)) {
                if (-1 !== e.indexOf(t)) return "[circular ref]";
                e.push(t)
            }
            return Mt(t)
        }
        return _(t) ? r(t) : JSON.stringify(t, function (t, e) {
            return r(e)
        }).replace(/\\"/g, '"')
    }
    var Gt = function (r) {
            return function (t) {
                if (!t) return ["", ""];
                var e = t.indexOf(r);
                return -1 === e ? [t, ""] : [t.substr(0, e), t.substr(e + 1)]
            }
        },
        Wt = new RegExp("^(?:[a-z]+:)?//[^/]+/"),
        zt = function (t) {
            return t.replace(/\/[^/]*$/, "")
        },
        Jt = Gt("#"),
        Qt = Gt("?"),
        Kt = Gt("="),
        Yt = function (t) {
            return t ? t.replace(/^#/, "") : ""
        };

    function Zt(t) {
        var e = new RegExp("(" + t + ")", "g");
        return function (t) {
            return t.split(e).filter(B)
        }
    }

    function Xt(t, e) {
        return P(Ct(t)) && P(e) ? t.slice(0, -1).concat(Ct(t) + e) : mt(t, e)
    }

    function te(t) {
        if (!t) return "ui-view (defunct)";
        var e = t.creationContext ? t.creationContext.name || "(root)" : "(none)";
        return "[ui-view#" + t.id + " " + t.$type + ":" + t.fqn + " (" + t.name + "@" + e + ")]"
    }

    function ee(t) {
        return T(t) ? d.Category[t] : d.Category[d.Category[t]]
    }
    var re, ne = Function.prototype.bind.call(console.log, console),
        ie = C(console.table) ? console.table.bind(console) : ne.bind(console);
    (re = d.Category || (d.Category = {}))[re.RESOLVE = 0] = "RESOLVE", re[re.TRANSITION = 1] = "TRANSITION", re[re.HOOK = 2] = "HOOK", re[re.UIVIEW = 3] = "UIVIEW", re[re.VIEWCONFIG = 4] = "VIEWCONFIG";
    var oe = R("$id"),
        ae = R("router.$id"),
        ue = function (t) {
            return "Transition #" + oe(t) + "-" + ae(t)
        },
        se = function () {
            function t() {
                this._enabled = {}, this.approximateDigests = 0
            }
            return t.prototype._set = function (e, t) {
                var r = this;
                t.length || (t = Object.keys(d.Category).map(function (t) {
                    return parseInt(t, 10)
                }).filter(function (t) {
                    return !isNaN(t)
                }).map(function (t) {
                    return d.Category[t]
                })), t.map(ee).forEach(function (t) {
                    return r._enabled[t] = e
                })
            }, t.prototype.enable = function () {
                for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
                this._set(!0, t)
            }, t.prototype.disable = function () {
                for (var t = [], e = 0; e < arguments.length; e++) t[e] = arguments[e];
                this._set(!1, t)
            }, t.prototype.enabled = function (t) {
                return !!this._enabled[ee(t)]
            }, t.prototype.traceTransitionStart = function (t) {
                this.enabled(d.Category.TRANSITION) && console.log(ue(t) + ": Started  -> " + Bt(t))
            }, t.prototype.traceTransitionIgnored = function (t) {
                this.enabled(d.Category.TRANSITION) && console.log(ue(t) + ": Ignored  <> " + Bt(t))
            }, t.prototype.traceHookInvocation = function (t, e, r) {
                if (this.enabled(d.Category.HOOK)) {
                    var n = R("traceData.hookType")(r) || "internal",
                        i = R("traceData.context.state.name")(r) || R("traceData.context")(r) || "unknown",
                        o = Nt(t.registeredHook.callback);
                    console.log(ue(e) + ":   Hook -> " + n + " context: " + i + ", " + qt(200, o))
                }
            }, t.prototype.traceHookResult = function (t, e, r) {
                this.enabled(d.Category.HOOK) && console.log(ue(e) + ":   <- Hook returned: " + qt(200, Bt(t)))
            }, t.prototype.traceResolvePath = function (t, e, r) {
                this.enabled(d.Category.RESOLVE) && console.log(ue(r) + ":         Resolving " + t + " (" + e + ")")
            }, t.prototype.traceResolvableResolved = function (t, e) {
                this.enabled(d.Category.RESOLVE) && console.log(ue(e) + ":               <- Resolved  " + t + " to: " + qt(200, Bt(t.data)))
            }, t.prototype.traceError = function (t, e) {
                this.enabled(d.Category.TRANSITION) && console.log(ue(e) + ": <- Rejected " + Bt(e) + ", reason: " + t)
            }, t.prototype.traceSuccess = function (t, e) {
                this.enabled(d.Category.TRANSITION) && console.log(ue(e) + ": <- Success  " + Bt(e) + ", final state: " + t.name)
            }, t.prototype.traceUIViewEvent = function (t, e, r) {
                void 0 === r && (r = ""), this.enabled(d.Category.UIVIEW) && console.log("ui-view: " + Dt(30, t) + " " + te(e) + r)
            }, t.prototype.traceUIViewConfigUpdated = function (t, e) {
                this.enabled(d.Category.UIVIEW) && this.traceUIViewEvent("Updating", t, " with ViewConfig from context='" + e + "'")
            }, t.prototype.traceUIViewFill = function (t, e) {
                this.enabled(d.Category.UIVIEW) && this.traceUIViewEvent("Fill", t, " with: " + qt(200, e))
            }, t.prototype.traceViewSync = function (t) {
                if (this.enabled(d.Category.VIEWCONFIG)) {
                    var a = "uiview component fqn",
                        e = t.map(function (t) {
                            var e, r = t.uiView,
                                n = t.viewConfig,
                                i = r && r.fqn,
                                o = n && n.viewDecl.$context.name + ": (" + n.viewDecl.$name + ")";
                            return (e = {})[a] = i, e["view config state (view name)"] = o, e
                        }).sort(function (t, e) {
                            return (t[a] || "").localeCompare(e[a] || "")
                        });
                    ie(e)
                }
            }, t.prototype.traceViewServiceEvent = function (t, e) {
                var r, n, i;
                this.enabled(d.Category.VIEWCONFIG) && console.log("VIEWCONFIG: " + t + " " + (n = (r = e).viewDecl, i = n.$context.name || "(root)", "[View#" + r.$id + " from '" + i + "' state]: target ui-view: '" + n.$uiViewName + "@" + n.$uiViewContextAnchor + "'"))
            }, t.prototype.traceViewServiceUIViewEvent = function (t, e) {
                this.enabled(d.Category.VIEWCONFIG) && console.log("VIEWCONFIG: " + t + " " + te(e))
            }, t
        }(),
        ce = new se,
        fe = function () {
            function t(t) {
                this.pattern = /.*/, this.inherit = !0, L(this, t)
            }
            return t.prototype.is = function (t, e) {
                return !0
            }, t.prototype.encode = function (t, e) {
                return t
            }, t.prototype.decode = function (t, e) {
                return t
            }, t.prototype.equals = function (t, e) {
                return t == e
            }, t.prototype.$subPattern = function () {
                var t = this.pattern.toString();
                return t.substr(1, t.length - 2)
            }, t.prototype.toString = function () {
                return "{ParamType:" + this.name + "}"
            }, t.prototype.$normalize = function (t) {
                return this.is(t) ? t : this.decode(t)
            }, t.prototype.$asArray = function (t, e) {
                if (!t) return this;
                if ("auto" === t && !e) throw new Error("'auto' array mode is for query parameters only");
                return new he(this, t)
            }, t
        }();

    function he(n, i) {
        var o = this;

        function a(t) {
            return O(t) ? t : E(t) ? [t] : []
        }

        function u(r, n) {
            return function (t) {
                if (O(t) && 0 === t.length) return t;
                var e = ft(a(t), r);
                return !0 === n ? 0 === ut(e, function (t) {
                    return !t
                }).length : function (t) {
                    switch (t.length) {
                        case 0:
                            return;
                        case 1:
                            return "auto" === i ? t[0] : t;
                        default:
                            return t
                    }
                }(e)
            }
        }

        function s(o) {
            return function (t, e) {
                var r = a(t),
                    n = a(e);
                if (r.length !== n.length) return !1;
                for (var i = 0; i < r.length; i++)
                    if (!o(r[i], n[i])) return !1;
                return !0
            }
        }["encode", "decode", "equals", "$normalize"].forEach(function (t) {
            var e = n[t].bind(n),
                r = "equals" === t ? s : u;
            o[t] = r(e)
        }), L(this, {
            dynamic: n.dynamic,
            name: n.name,
            pattern: n.pattern,
            inherit: n.inherit,
            raw: n.raw,
            is: u(n.is.bind(n), !0),
            $arrayMode: i
        })
    }
    var le, pe = Object.prototype.hasOwnProperty,
        ve = function (t) {
            return 0 === ["value", "type", "squash", "array", "dynamic"].filter(pe.bind(t || {})).length
        };

    function de(t, e, r) {
        var n = !1 === r.reloadOnSearch && e === d.DefType.SEARCH || void 0,
            i = [r.dynamic, n].find(E),
            o = E(i) ? {
                dynamic: i
            } : {},
            a = function (t) {
                function e() {
                    return t.value
                }
                t = ve(t) ? {
                    value: t
                } : t, e.__cacheable = !0;
                var r = j(t.value) ? t.value : e;
                return L(t, {
                    $$fn: r
                })
            }(r && r.params && r.params[t]);
        return L(o, a)
    }(le = d.DefType || (d.DefType = {}))[le.PATH = 0] = "PATH", le[le.SEARCH = 1] = "SEARCH", le[le.CONFIG = 2] = "CONFIG";
    var me, ye = function () {
            function n(t, e, r, n, i) {
                var o = de(t, r, i);
                e = function (t, e, r, n, i) {
                    if (t.type && e && "string" !== e.name) throw new Error("Param '" + n + "' has two type configurations.");
                    if (t.type && e && "string" === e.name && i.type(t.type)) return i.type(t.type);
                    if (e) return e;
                    if (!t.type) {
                        var o = r === d.DefType.CONFIG ? "any" : r === d.DefType.PATH ? "path" : r === d.DefType.SEARCH ? "query" : "string";
                        return i.type(o)
                    }
                    return t.type instanceof fe ? t.type : i.type(t.type)
                }(o, e, r, t, n.paramTypes);
                var a, u, s = (a = {
                    array: r === d.DefType.SEARCH && "auto"
                }, u = t.match(/\[\]$/) ? {
                    array: !0
                } : {}, L(a, u, o).array);
                e = s ? e.$asArray(s, r === d.DefType.SEARCH) : e;
                var c = void 0 !== o.value || r === d.DefType.SEARCH,
                    f = E(o.dynamic) ? !!o.dynamic : !!e.dynamic,
                    h = E(o.raw) ? !!o.raw : !!e.raw,
                    l = function (t, e, r) {
                        var n = t.squash;
                        if (!e || !1 === n) return !1;
                        if (!E(n) || null == n) return r;
                        if (!0 === n || P(n)) return n;
                        throw new Error("Invalid squash policy: '" + n + "'. Valid policies: false, true, or arbitrary string")
                    }(o, c, n.defaultSquashPolicy()),
                    p = function (t, e, r, n) {
                        var i = [{
                                from: "",
                                to: r || e ? void 0 : ""
                            }, {
                                from: null,
                                to: r || e ? void 0 : ""
                            }],
                            o = O(t.replace) ? t.replace : [];
                        P(n) && o.push({
                            from: n,
                            to: void 0
                        });
                        var a = ft(o, w("from"));
                        return ut(i, function (t) {
                            return -1 === a.indexOf(t.from)
                        }).concat(o)
                    }(o, s, c, l),
                    v = E(o.inherit) ? !!o.inherit : !!e.inherit;
                L(this, {
                    id: t,
                    type: e,
                    location: r,
                    isOptional: c,
                    dynamic: f,
                    raw: h,
                    squash: l,
                    replace: p,
                    inherit: v,
                    array: s,
                    config: o
                })
            }
            return n.values = function (t, e) {
                void 0 === e && (e = {});
                for (var r = {}, n = 0, i = t; n < i.length; n++) {
                    var o = i[n];
                    r[o.id] = o.value(e[o.id])
                }
                return r
            }, n.changed = function (t, e, r) {
                return void 0 === e && (e = {}), void 0 === r && (r = {}), t.filter(function (t) {
                    return !t.type.equals(e[t.id], r[t.id])
                })
            }, n.equals = function (t, e, r) {
                return void 0 === e && (e = {}), void 0 === r && (r = {}), 0 === n.changed(t, e, r).length
            }, n.validates = function (t, e) {
                return void 0 === e && (e = {}), t.map(function (t) {
                    return t.validates(e[t.id])
                }).reduce(lt, !0)
            }, n.prototype.isDefaultValue = function (t) {
                return this.isOptional && this.type.equals(this.value(), t)
            }, n.prototype.value = function (t) {
                var i = this;
                return t = function (t) {
                    for (var e = 0, r = i.replace; e < r.length; e++) {
                        var n = r[e];
                        if (n.from === t) return n.to
                    }
                    return t
                }(t), _(t) ? function () {
                    if (i._defaultValueCache) return i._defaultValueCache.defaultValue;
                    if (!A.$injector) throw new Error("Injectable functions cannot be called at configuration time");
                    var t = A.$injector.invoke(i.config.$$fn);
                    if (null != t && !i.type.is(t)) throw new Error("Default value (" + t + ") for parameter '" + i.id + "' is not an instance of ParamType (" + i.type.name + ")");
                    return i.config.$$fn.__cacheable && (i._defaultValueCache = {
                        defaultValue: t
                    }), t
                }() : this.type.$normalize(t)
            }, n.prototype.isSearch = function () {
                return this.location === d.DefType.SEARCH
            }, n.prototype.validates = function (t) {
                if ((_(t) || null === t) && this.isOptional) return !0;
                var e = this.type.$normalize(t);
                if (!this.type.is(e)) return !1;
                var r = this.type.encode(e);
                return !(P(r) && !this.type.pattern.exec(r))
            }, n.prototype.toString = function () {
                return "{Param:" + this.id + " " + this.type + " squash: '" + this.squash + "' optional: " + this.isOptional + "}"
            }, n
        }(),
        ge = function () {
            function t() {
                this.enqueue = !0, this.typeQueue = [], this.defaultTypes = it(t.prototype, ["hash", "string", "query", "path", "int", "bool", "date", "json", "any"]);
                this.types = z(ft(this.defaultTypes, function (t, e) {
                    return new fe(L({
                        name: e
                    }, t))
                }), {})
            }
            return t.prototype.dispose = function () {
                this.types = {}
            }, t.prototype.type = function (t, e, r) {
                if (!E(e)) return this.types[t];
                if (this.types.hasOwnProperty(t)) throw new Error("A type named '" + t + "' has already been defined.");
                return this.types[t] = new fe(L({
                    name: t
                }, e)), r && (this.typeQueue.push({
                    name: t,
                    def: r
                }), this.enqueue || this._flushTypeQueue()), this
            }, t.prototype._flushTypeQueue = function () {
                for (; this.typeQueue.length;) {
                    var t = this.typeQueue.shift();
                    if (t.pattern) throw new Error("You cannot override a type's .pattern at runtime.");
                    L(this.types[t.name], A.$injector.invoke(t.def))
                }
            }, t
        }();
    me = function (t) {
        var e = function (t) {
                return null != t ? t.toString() : t
            },
            r = {
                encode: e,
                decode: e,
                is: p(String),
                pattern: /.*/,
                equals: function (t, e) {
                    return t == e
                }
            };
        return L({}, r, t)
    }, L(ge.prototype, {
        string: me({}),
        path: me({
            pattern: /[^/]*/
        }),
        query: me({}),
        hash: me({
            inherit: !1
        }),
        int: me({
            decode: function (t) {
                return parseInt(t, 10)
            },
            is: function (t) {
                return !S(t) && this.decode(t.toString()) === t
            },
            pattern: /-?\d+/
        }),
        bool: me({
            encode: function (t) {
                return t ? 1 : 0
            },
            decode: function (t) {
                return 0 !== parseInt(t, 10)
            },
            is: p(Boolean),
            pattern: /0|1/
        }),
        date: me({
            encode: function (t) {
                return this.is(t) ? [t.getFullYear(), ("0" + (t.getMonth() + 1)).slice(-2), ("0" + t.getDate()).slice(-2)].join("-") : void 0
            },
            decode: function (t) {
                if (this.is(t)) return t;
                var e = this.capture.exec(t);
                return e ? new Date(e[1], e[2] - 1, e[3]) : void 0
            },
            is: function (t) {
                return t instanceof Date && !isNaN(t.valueOf())
            },
            equals: function (r, n) {
                return ["getFullYear", "getMonth", "getDate"].reduce(function (t, e) {
                    return t && r[e]() === n[e]()
                }, !0)
            },
            pattern: /[0-9]{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2][0-9]|3[0-1])/,
            capture: /([0-9]{4})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])/
        }),
        json: me({
            encode: N,
            decode: F,
            is: p(Object),
            equals: M,
            pattern: /[^/]*/
        }),
        any: me({
            encode: B,
            decode: B,
            is: function () {
                return !0
            },
            equals: M
        })
    });
    var we = function () {
            function t(t) {
                void 0 === t && (t = {}), L(this, t)
            }
            return t.prototype.$inherit = function (t, e, r) {
                var n, i = nt(e, r),
                    o = {},
                    a = [];
                for (var u in i)
                    if (i[u] && i[u].params && (n = Object.keys(i[u].params)).length)
                        for (var s in n) 0 <= a.indexOf(n[s]) || (a.push(n[s]), o[n[s]] = this[n[s]]);
                return L({}, o, t)
            }, t
        }(),
        _e = function () {
            function n(t) {
                if (t instanceof n) {
                    var e = t;
                    this.state = e.state, this.paramSchema = e.paramSchema.slice(), this.paramValues = L({}, e.paramValues), this.resolvables = e.resolvables.slice(), this.views = e.views && e.views.slice()
                } else {
                    var r = t;
                    this.state = r, this.paramSchema = r.parameters({
                        inherit: !1
                    }), this.paramValues = {}, this.resolvables = r.resolvables.map(function (t) {
                        return t.clone()
                    })
                }
            }
            return n.prototype.clone = function () {
                return new n(this)
            }, n.prototype.applyRawParams = function (n) {
                return this.paramValues = this.paramSchema.reduce(function (t, e) {
                    return Et(t, [(r = e).id, r.value(n[r.id])]);
                    var r
                }, {}), this
            }, n.prototype.parameter = function (t) {
                return st(this.paramSchema, y("id", t))
            }, n.prototype.equals = function (t, e) {
                var r = this.diff(t, e);
                return r && 0 === r.length
            }, n.prototype.diff = function (t, e) {
                if (this.state !== t.state) return !1;
                var r = e ? e(this) : this.paramSchema;
                return ye.changed(r, this.paramValues, t.paramValues)
            }, n.clone = function (t) {
                return t.clone()
            }, n
        }(),
        $e = function () {
            function n(t, e, r, n) {
                this._stateRegistry = t, this._identifier = e, this._identifier = e, this._params = L({}, r || {}), this._options = L({}, n || {}), this._definition = t.matcher.find(e, this._options.relative)
            }
            return n.prototype.name = function () {
                return this._definition && this._definition.name || this._identifier
            }, n.prototype.identifier = function () {
                return this._identifier
            }, n.prototype.params = function () {
                return this._params
            }, n.prototype.$state = function () {
                return this._definition
            }, n.prototype.state = function () {
                return this._definition && this._definition.self
            }, n.prototype.options = function () {
                return this._options
            }, n.prototype.exists = function () {
                return !(!this._definition || !this._definition.self)
            }, n.prototype.valid = function () {
                return !this.error()
            }, n.prototype.error = function () {
                var t = this.options().relative;
                if (!this._definition && t) {
                    var e = t.name ? t.name : t;
                    return "Could not resolve '" + this.name() + "' from state '" + e + "'"
                }
                return this._definition ? this._definition.self ? void 0 : "State '" + this.name() + "' has an invalid definition" : "No such state '" + this.name() + "'"
            }, n.prototype.toString = function () {
                return "'" + this.name() + "'" + Bt(this.params())
            }, n.prototype.withState = function (t) {
                return new n(this._stateRegistry, t, this._params, this._options)
            }, n.prototype.withParams = function (t, e) {
                void 0 === e && (e = !1);
                var r = e ? t : L({}, this._params, t);
                return new n(this._stateRegistry, this._identifier, r, this._options)
            }, n.prototype.withOptions = function (t, e) {
                void 0 === e && (e = !1);
                var r = e ? t : L({}, this._options, t);
                return new n(this._stateRegistry, this._identifier, this._params, r)
            }, n.isDef = function (t) {
                return t && t.state && (P(t.state) || P(t.state.name))
            }, n
        }(),
        Se = function () {
            function l() {}
            return l.makeTargetState = function (t, e) {
                var r = Ct(e).state;
                return new $e(t, r, e.map(w("paramValues")).reduce(rt, {}), {})
            }, l.buildPath = function (t) {
                var e = t.params();
                return t.$state().path.map(function (t) {
                    return new _e(t).applyRawParams(e)
                })
            }, l.buildToPath = function (t, e) {
                var r = l.buildPath(e);
                return e.options().inherit ? l.inheritParams(t, r, Object.keys(e.params())) : r
            }, l.applyViewConfigs = function (i, o, e) {
                o.filter(function (t) {
                    return J(e, t.state)
                }).forEach(function (e) {
                    var t = ht(e.state.views || {}),
                        r = l.subPath(o, function (t) {
                            return t === e
                        }),
                        n = t.map(function (t) {
                            return i.createViewConfig(r, t)
                        });
                    e.views = n.reduce(vt, [])
                })
            }, l.inheritParams = function (s, t, c) {
                void 0 === c && (c = []);
                var f = s.map(function (t) {
                    return t.paramSchema
                }).reduce(vt, []).filter(function (t) {
                    return !t.inherit
                }).map(w("id"));
                return t.map(function (t) {
                    var e = L({}, t && t.paramValues),
                        r = it(e, c);
                    e = ot(e, c);
                    var n, i, o, a = ot((n = s, i = t.state, o = st(n, y("state", i)), L({}, o && o.paramValues) || {}), f),
                        u = L(e, a, r);
                    return new _e(t.state).applyRawParams(u)
                })
            }, l.treeChanges = function (t, n, e) {
                for (var r, i, o, a, u, s, c = Math.min(t.length, n.length), f = 0; f < c && t[f].state !== e && (r = t[f], i = n[f], r.equals(i, l.nonDynamicParams));) f++;
                a = (o = t).slice(0, f), u = o.slice(f);
                var h = a.map(function (t, e) {
                    var r = t.clone();
                    return r.paramValues = n[e].paramValues, r
                });
                return s = n.slice(f), {
                    from: o,
                    to: h.concat(s),
                    retained: a,
                    retainedWithToParams: h,
                    exiting: u,
                    entering: s
                }
            }, l.matching = function (t, e, i) {
                var o = !1;
                return Rt(t, e).reduce(function (t, e) {
                    var r = e[0],
                        n = e[1];
                    return (o = o || !r.equals(n, i)) ? t : t.concat(r)
                }, [])
            }, l.equals = function (t, e, r) {
                return t.length === e.length && l.matching(t, e, r).length === t.length
            }, l.subPath = function (t, e) {
                var r = st(t, e),
                    n = t.indexOf(r);
                return -1 === n ? void 0 : t.slice(0, n + 1)
            }, l.nonDynamicParams = function (t) {
                return t.state.parameters({
                    inherit: !1
                }).filter(function (t) {
                    return !t.dynamic
                })
            }, l.paramValues = function (t) {
                return t.reduce(function (t, e) {
                    return L(t, e.paramValues)
                }, {})
            }, l
        }(),
        be = {
            when: {
                LAZY: "LAZY",
                EAGER: "EAGER"
            },
            async: {
                WAIT: "WAIT",
                NOWAIT: "NOWAIT",
                RXWAIT: "RXWAIT"
            }
        },
        Re = {
            when: "LAZY",
            async: "WAIT"
        },
        Ee = function () {
            function a(t, e, r, n, i) {
                if (this.resolved = !1, this.promise = void 0, t instanceof a) L(this, t);
                else if (C(e)) {
                    if (S(t)) throw new Error("new Resolvable(): token argument is required");
                    if (!C(e)) throw new Error("new Resolvable(): resolveFn argument must be a function");
                    this.token = t, this.policy = n, this.resolveFn = e, this.deps = r || [], this.data = i, this.resolved = void 0 !== i, this.promise = this.resolved ? A.$q.when(this.data) : void 0
                } else if (k(t) && t.token && (t.hasOwnProperty("resolveFn") || t.hasOwnProperty("data"))) {
                    var o = t;
                    return new a(o.token, o.resolveFn, o.deps, o.policy, o.data)
                }
            }
            return a.prototype.getPolicy = function (t) {
                var e = this.policy || {},
                    r = t && t.resolvePolicy || {};
                return {
                    when: e.when || r.when || Re.when,
                    async: e.async || r.async || Re.async
                }
            }, a.prototype.resolve = function (e, r) {
                var n = this,
                    t = A.$q,
                    i = e.findNode(this),
                    o = i && i.state,
                    a = "RXWAIT" === this.getPolicy(o).async ? function (t) {
                        var e = t.cache(1);
                        return e.take(1).toPromise().then(function () {
                            return e
                        })
                    } : B;
                return this.promise = t.when().then(function () {
                    return t.all(e.getDependencies(n).map(function (t) {
                        return t.get(e, r)
                    }))
                }).then(function (t) {
                    return n.resolveFn.apply(null, t)
                }).then(a).then(function (t) {
                    return n.data = t, n.resolved = !0, n.resolveFn = null, ce.traceResolvableResolved(n, r), n.data
                })
            }, a.prototype.get = function (t, e) {
                return this.promise || this.resolve(t, e)
            }, a.prototype.toString = function () {
                return "Resolvable(token: " + Bt(this.token) + ", requires: [" + this.deps.map(Bt) + "])"
            }, a.prototype.clone = function () {
                return new a(this)
            }, a.fromData = function (t, e) {
                return new a(t, function () {
                    return e
                }, null, null, e)
            }, a
        }(),
        Ce = be.when,
        Te = [Ce.EAGER, Ce.LAZY],
        Pe = [Ce.EAGER],
        ke = "Native Injector",
        Oe = function () {
            function t(t) {
                this._path = t
            }
            return t.prototype.getTokens = function () {
                return this._path.reduce(function (t, e) {
                    return t.concat(e.resolvables.map(function (t) {
                        return t.token
                    }))
                }, []).reduce(yt, [])
            }, t.prototype.getResolvable = function (e) {
                return Ct(this._path.map(function (t) {
                    return t.resolvables
                }).reduce(vt, []).filter(function (t) {
                    return t.token === e
                }))
            }, t.prototype.getPolicy = function (t) {
                var e = this.findNode(t);
                return t.getPolicy(e.state)
            }, t.prototype.subContext = function (e) {
                return new t(Se.subPath(this._path, function (t) {
                    return t.state === e
                }))
            }, t.prototype.addResolvables = function (t, e) {
                var r = st(this._path, y("state", e)),
                    n = t.map(function (t) {
                        return t.token
                    });
                r.resolvables = r.resolvables.filter(function (t) {
                    return -1 === n.indexOf(t.token)
                }).concat(t)
            }, t.prototype.resolvePath = function (t, u) {
                var s = this;
                void 0 === t && (t = "LAZY");
                var c = (J(Te, t) ? t : "LAZY") === be.when.EAGER ? Pe : Te;
                ce.traceResolvePath(this._path, t, u);
                var f = function (e, r) {
                        return function (t) {
                            return J(e, s.getPolicy(t)[r])
                        }
                    },
                    e = this._path.reduce(function (t, e) {
                        var r = e.resolvables.filter(f(c, "when")),
                            n = r.filter(f(["NOWAIT"], "async")),
                            i = r.filter(h(f(["NOWAIT"], "async"))),
                            o = s.subContext(e.state),
                            a = function (e) {
                                return e.get(o, u).then(function (t) {
                                    return {
                                        token: e.token,
                                        value: t
                                    }
                                })
                            };
                        return n.forEach(a), t.concat(i.map(a))
                    }, []);
                return A.$q.all(e)
            }, t.prototype.injector = function () {
                return this._injector || (this._injector = new xe(this))
            }, t.prototype.findNode = function (e) {
                return st(this._path, function (t) {
                    return J(t.resolvables, e)
                })
            }, t.prototype.getDependencies = function (e) {
                var n = this,
                    r = this.findNode(e),
                    i = (Se.subPath(this._path, function (t) {
                        return t === r
                    }) || this._path).reduce(function (t, e) {
                        return t.concat(e.resolvables)
                    }, []).filter(function (t) {
                        return t !== e
                    });
                return e.deps.map(function (e) {
                    var t = i.filter(function (t) {
                        return t.token === e
                    });
                    if (t.length) return Ct(t);
                    var r = n.injector().getNative(e);
                    if (_(r)) throw new Error("Could not find Dependency Injection token: " + Bt(e));
                    return new Ee(e, function () {
                        return r
                    }, [], r)
                })
            }, t
        }(),
        xe = function () {
            function t(t) {
                this.context = t, this.native = this.get(ke) || A.$injector
            }
            return t.prototype.get = function (t) {
                var e = this.context.getResolvable(t);
                if (e) {
                    if ("NOWAIT" === this.context.getPolicy(e).async) return e.get(this.context);
                    if (!e.resolved) throw new Error("Resolvable async .get() not complete:" + Bt(e.token));
                    return e.data
                }
                return this.getNative(t)
            }, t.prototype.getAsync = function (t) {
                var e = this.context.getResolvable(t);
                return e ? e.get(this.context) : A.$q.when(this.native.get(t))
            }, t.prototype.getNative = function (t) {
                return this.native && this.native.get(t)
            }, t
        }();

    function Ve(t) {
        return t.name
    }

    function je(t) {
        return t.self.$$state = function () {
            return t
        }, t.self
    }

    function Ie(t) {
        return t.parent && t.parent.data && (t.data = t.self.data = z(t.parent.data, t.data)), t.data
    }
    var He = function (o, a) {
        return function (t) {
            var e = t.self;
            e && e.url && e.name && e.name.match(/\.\*\*$/) && (e.url += "{remainder:any}");
            var r = t.parent,
                n = function (t) {
                    if (!P(t)) return !1;
                    var e = "^" === t.charAt(0);
                    return {
                        val: e ? t.substring(1) : t,
                        root: e
                    }
                }(e.url),
                i = n ? o.compile(n.val, {
                    state: e
                }) : e.url;
            if (!i) return null;
            if (!o.isMatcher(i)) throw new Error("Invalid url '" + i + "' in state '" + t + "'");
            return n && n.root ? i : (r && r.navigable || a()).url.append(i)
        }
    };

    function Ae(t) {
        return t.parent ? t.parent.path.concat(t) : [t]
    }

    function qe(t) {
        var e = t.parent ? L({}, t.parent.includes) : {};
        return e[t.name] = !0, e
    }

    function De(t) {
        var e, r, n = function (t) {
                return t.provide || t.token
            },
            i = m([
                [w("resolveFn"), function (t) {
                    return new Ee(n(t), t.resolveFn, t.deps, t.policy)
                }],
                [w("useFactory"), function (t) {
                    return new Ee(n(t), t.useFactory, t.deps || t.dependencies, t.policy)
                }],
                [w("useClass"), function (t) {
                    return new Ee(n(t), function () {
                        return new t.useClass
                    }, [], t.policy)
                }],
                [w("useValue"), function (t) {
                    return new Ee(n(t), function () {
                        return t.useValue
                    }, [], t.policy, t.useValue)
                }],
                [w("useExisting"), function (t) {
                    return new Ee(n(t), B, [t.useExisting], t.policy)
                }]
            ]),
            o = m([
                [s(w("val"), P), function (t) {
                    return new Ee(t.token, B, [t.val], t.policy)
                }],
                [s(w("val"), O), function (t) {
                    return new Ee(t.token, Ct(t.val), t.val.slice(0, -1), t.policy)
                }],
                [s(w("val"), C), function (t) {
                    return new Ee(t.token, t.val, (e = t.val, r = A.$injector, e.$inject || r && r.annotate(e, r.strictDi) || "deferred"), t.policy);
                    var e, r
                }]
            ]),
            a = m([
                [p(Ee), function (t) {
                    return t
                }],
                [function (t) {
                    return !(!t.token || !t.resolveFn)
                }, i],
                [function (t) {
                    return !(!t.provide && !t.token || !(t.useValue || t.useFactory || t.useExisting || t.useClass))
                }, i],
                [function (t) {
                    return !!(t && t.val && (P(t.val) || O(t.val) || C(t.val)))
                }, o],
                [v(!0), function (t) {
                    throw new Error("Invalid resolve value: " + Bt(t))
                }]
            ]),
            u = t.resolve;
        return (O(u) ? u : (e = u, r = t.resolvePolicy || {}, Object.keys(e || {}).map(function (t) {
            return {
                token: t,
                val: e[t],
                deps: void 0,
                policy: r[t]
            }
        }))).map(a)
    }
    var Fe, Ne, Ue = function () {
            function t(e, t) {
                this.matcher = e;
                var n, r, i = this,
                    o = function () {
                        return e.find("")
                    },
                    a = function (t) {
                        return "" === t.name
                    };
                this.builders = {
                    name: [Ve],
                    self: [je],
                    parent: [function (t) {
                        return a(t) ? null : e.find(i.parentName(t)) || o()
                    }],
                    data: [Ie],
                    url: [He(t, o)],
                    navigable: [(r = a, function (t) {
                        return !r(t) && t.url ? t : t.parent ? t.parent.navigable : null
                    })],
                    params: [(n = t.paramFactory, function (r) {
                        var t = r.url && r.url.parameters({
                                inherit: !1
                            }) || [],
                            e = ht(ct(ot(r.params || {}, t.map(w("id"))), function (t, e) {
                                return n.fromConfig(e, null, r.self)
                            }));
                        return t.concat(e).map(function (t) {
                            return [t.id, t]
                        }).reduce(Et, {})
                    })],
                    views: [],
                    path: [Ae],
                    includes: [qe],
                    resolvables: [De]
                }
            }
            return t.prototype.builder = function (t, e) {
                var r = this.builders,
                    n = r[t] || [];
                return P(t) && !E(e) ? 1 < n.length ? n : n[0] : P(t) && C(e) ? (r[t] = n, r[t].push(e), function () {
                    return r[t].splice(r[t].indexOf(e, 1)) && null
                }) : void 0
            }, t.prototype.build = function (t) {
                var e = this.matcher,
                    r = this.builders,
                    n = this.parentName(t);
                if (n && !e.find(n, void 0, !1)) return null;
                for (var i in r)
                    if (r.hasOwnProperty(i)) {
                        var o = r[i].reduce(function (e, r) {
                            return function (t) {
                                return r(t, e)
                            }
                        }, G);
                        t[i] = o(t)
                    }
                return t
            }, t.prototype.parentName = function (t) {
                var e = t.name || "",
                    r = e.split(".");
                if ("**" === r.pop() && r.pop(), r.length) {
                    if (t.parent) throw new Error("States that specify the 'parent:' property should not have a '.' in their name (" + e + ")");
                    return r.join(".")
                }
                return t.parent ? P(t.parent) ? t.parent : t.parent.name : ""
            }, t.prototype.name = function (t) {
                var e = t.name;
                if (-1 !== e.indexOf(".") || !t.parent) return e;
                var r = P(t.parent) ? t.parent : t.parent.name;
                return r ? r + "." + e : e
            }, t
        }(),
        Le = function () {
            function r(t) {
                return r.create(t || {})
            }
            return r.create = function (t) {
                t = r.isStateClass(t) ? new t : t;
                var e = z(z(t, r.prototype));
                return t.$$state = function () {
                    return e
                }, e.self = t, e.__stateObjectCache = {
                    nameGlob: jt.fromString(e.name)
                }, e
            }, r.prototype.is = function (t) {
                return this === t || this.self === t || this.fqn() === t
            }, r.prototype.fqn = function () {
                if (!(this.parent && this.parent instanceof this.constructor)) return this.name;
                var t = this.parent.fqn();
                return t ? t + "." + this.name : this.name
            }, r.prototype.root = function () {
                return this.parent && this.parent.root() || this
            }, r.prototype.parameters = function (e) {
                return ((e = et(e, {
                    inherit: !0,
                    matchingKeys: null
                })).inherit && this.parent && this.parent.parameters() || []).concat(ht(this.params)).filter(function (t) {
                    return !e.matchingKeys || e.matchingKeys.hasOwnProperty(t.id)
                })
            }, r.prototype.parameter = function (t, e) {
                return void 0 === e && (e = {}), this.url && this.url.parameter(t, e) || st(ht(this.params), y("id", t)) || e.inherit && this.parent && this.parent.parameter(t)
            }, r.prototype.toString = function () {
                return this.fqn()
            }, r.isStateClass = function (t) {
                return C(t) && !0 === t.__uiRouterState
            }, r.isState = function (t) {
                return k(t.__stateObjectCache)
            }, r
        }(),
        Me = function () {
            function t(t) {
                this._states = t
            }
            return t.prototype.isRelative = function (t) {
                return 0 === (t = t || "").indexOf(".") || 0 === t.indexOf("^")
            }, t.prototype.find = function (t, e, r) {
                if (void 0 === r && (r = !0), t || "" === t) {
                    var n = P(t),
                        i = n ? t : t.name;
                    this.isRelative(i) && (i = this.resolvePath(i, e));
                    var o = this._states[i];
                    if (o && (n || !(n || o !== t && o.self !== t))) return o;
                    if (n && r) {
                        var a = ht(this._states).filter(function (t) {
                            return t.__stateObjectCache.nameGlob && t.__stateObjectCache.nameGlob.matches(i)
                        });
                        return 1 < a.length && console.log("stateMatcher.find: Found multiple matches for " + i + " using glob: ", a.map(function (t) {
                            return t.name
                        })), a[0]
                    }
                }
            }, t.prototype.resolvePath = function (t, e) {
                if (!e) throw new Error("No reference point given for path '" + t + "'");
                for (var r = this.find(e), n = t.split("."), i = n.length, o = 0, a = r; o < i; o++)
                    if ("" !== n[o] || 0 !== o) {
                        if ("^" !== n[o]) break;
                        if (!a.parent) throw new Error("Path '" + t + "' not valid for state '" + r.name + "'");
                        a = a.parent
                    } else a = r;
                var u = n.slice(o).join(".");
                return a.name + (a.name && u ? "." : "") + u
            }, t
        }(),
        Be = function () {
            function t(t, e, r, n, i) {
                this.$registry = t, this.$urlRouter = e, this.states = r, this.builder = n, this.listeners = i, this.queue = [], this.matcher = t.matcher
            }
            return t.prototype.dispose = function () {
                this.queue = []
            }, t.prototype.register = function (t) {
                var e = this.queue,
                    r = Le.create(t),
                    n = r.name;
                if (!P(n)) throw new Error("State must have a valid name");
                if (this.states.hasOwnProperty(n) || J(e.map(w("name")), n)) throw new Error("State '" + n + "' is already defined");
                return e.push(r), this.flush(), r
            }, t.prototype.flush = function () {
                for (var e = this, t = this.queue, r = this.states, n = this.builder, i = [], o = [], a = {}, u = function (t) {
                        return e.states.hasOwnProperty(t) && e.states[t]
                    }, s = function () {
                        i.length && e.listeners.forEach(function (t) {
                            return t("registered", i.map(function (t) {
                                return t.self
                            }))
                        })
                    }; 0 < t.length;) {
                    var c = t.shift(),
                        f = c.name,
                        h = n.build(c),
                        l = o.indexOf(c);
                    if (h) {
                        var p = u(f);
                        if (p && p.name === f) throw new Error("State '" + f + "' is already defined");
                        var v = u(f + ".**");
                        v && this.$registry.deregister(v), r[f] = c, this.attachRoute(c), 0 <= l && o.splice(l, 1), i.push(c)
                    } else {
                        var d = a[f];
                        if (a[f] = t.length, 0 <= l && d === t.length) return t.push(c), s(), r;
                        l < 0 && o.push(c), t.push(c)
                    }
                }
                return s(), r
            }, t.prototype.attachRoute = function (t) {
                !t.abstract && t.url && this.$urlRouter.rule(this.$urlRouter.urlRuleFactory.create(t))
            }, t
        }(),
        Ge = function () {
            function t(t) {
                this._router = t, this.states = {}, this.listeners = [], this.matcher = new Me(this.states), this.builder = new Ue(this.matcher, t.urlMatcherFactory), this.stateQueue = new Be(this, t.urlRouter, this.states, this.builder, this.listeners), this._registerRoot()
            }
            return t.prototype._registerRoot = function () {
                (this._root = this.stateQueue.register({
                    name: "",
                    url: "^",
                    views: null,
                    params: {
                        "#": {
                            value: null,
                            type: "hash",
                            dynamic: !0
                        }
                    },
                    abstract: !0
                })).navigable = null
            }, t.prototype.dispose = function () {
                var e = this;
                this.stateQueue.dispose(), this.listeners = [], this.get().forEach(function (t) {
                    return e.get(t) && e.deregister(t)
                })
            }, t.prototype.onStatesChanged = function (t) {
                return this.listeners.push(t),
                    function () {
                        K(this.listeners)(t)
                    }.bind(this)
            }, t.prototype.root = function () {
                return this._root
            }, t.prototype.register = function (t) {
                return this.stateQueue.register(t)
            }, t.prototype._deregisterTree = function (t) {
                var r = this,
                    n = this.get().map(function (t) {
                        return t.$$state()
                    }),
                    i = function (e) {
                        var t = n.filter(function (t) {
                            return -1 !== e.indexOf(t.parent)
                        });
                        return 0 === t.length ? t : t.concat(i(t))
                    },
                    e = i([t]),
                    o = [t].concat(e).reverse();
                return o.forEach(function (t) {
                    var e = r._router.urlRouter;
                    e.rules().filter(y("state", t)).forEach(e.removeRule.bind(e)), delete r.states[t.name]
                }), o
            }, t.prototype.deregister = function (t) {
                var e = this.get(t);
                if (!e) throw new Error("Can't deregister state; not found: " + t);
                var r = this._deregisterTree(e.$$state());
                return this.listeners.forEach(function (t) {
                    return t("deregistered", r.map(function (t) {
                        return t.self
                    }))
                }), r
            }, t.prototype.get = function (t, e) {
                var r = this;
                if (0 === arguments.length) return Object.keys(this.states).map(function (t) {
                    return r.states[t].self
                });
                var n = this.matcher.find(t, e);
                return n && n.self || null
            }, t.prototype.decorator = function (t, e) {
                return this.builder.builder(t, e)
            }, t
        }();
    (Fe = d.TransitionHookPhase || (d.TransitionHookPhase = {}))[Fe.CREATE = 0] = "CREATE", Fe[Fe.BEFORE = 1] = "BEFORE", Fe[Fe.RUN = 2] = "RUN", Fe[Fe.SUCCESS = 3] = "SUCCESS", Fe[Fe.ERROR = 4] = "ERROR", (Ne = d.TransitionHookScope || (d.TransitionHookScope = {}))[Ne.TRANSITION = 0] = "TRANSITION", Ne[Ne.STATE = 1] = "STATE";
    var We = {
            current: G,
            transition: null,
            traceData: {},
            bind: null
        },
        ze = function () {
            function o(t, e, r, n) {
                var i = this;
                this.transition = t, this.stateContext = e, this.registeredHook = r, this.options = n, this.isSuperseded = function () {
                    return i.type.hookPhase === d.TransitionHookPhase.RUN && !i.options.transition.isActive()
                }, this.options = et(n, We), this.type = r.eventType
            }
            return o.chain = function (t, e) {
                return t.reduce(function (t, e) {
                    return t.then(function () {
                        return e.invokeHook()
                    })
                }, e || A.$q.when())
            }, o.invokeHooks = function (t, e) {
                for (var r = 0; r < t.length; r++) {
                    var n = t[r].invokeHook();
                    if (I(n)) {
                        var i = t.slice(r + 1);
                        return o.chain(i, n).then(e)
                    }
                }
                return e()
            }, o.runAllHooks = function (t) {
                t.forEach(function (t) {
                    return t.invokeHook()
                })
            }, o.prototype.logError = function (t) {
                this.transition.router.stateService.defaultErrorHandler()(t)
            }, o.prototype.invokeHook = function () {
                var e = this,
                    r = this.registeredHook;
                if (!r._deregistered) {
                    var t = this.getNotCurrentRejection();
                    if (t) return t;
                    var n = this.options;
                    ce.traceHookInvocation(this, this.transition, n);
                    var i = function (t) {
                            return r.eventType.getErrorHandler(e)(t)
                        },
                        o = function (t) {
                            return r.eventType.getResultHandler(e)(t)
                        };
                    try {
                        var a = r.callback.call(n.bind, e.transition, e.stateContext);
                        return !this.type.synchronous && I(a) ? a.catch(function (t) {
                            return At.normalize(t).toPromise()
                        }).then(o, i) : o(a)
                    } catch (t) {
                        return i(At.normalize(t))
                    } finally {
                        r.invokeLimit && ++r.invokeCount >= r.invokeLimit && r.deregister()
                    }
                }
            }, o.prototype.handleHookResult = function (t) {
                var e = this,
                    r = this.getNotCurrentRejection();
                return r || (I(t) ? t.then(function (t) {
                    return e.handleHookResult(t)
                }) : (ce.traceHookResult(t, this.transition, this.options), !1 === t ? At.aborted("Hook aborted transition").toPromise() : p($e)(t) ? At.redirected(t).toPromise() : void 0))
            }, o.prototype.getNotCurrentRejection = function () {
                var t = this.transition.router;
                return t._disposed ? At.aborted("UIRouter instance #" + t.$id + " has been stopped (disposed)").toPromise() : this.transition._aborted ? At.aborted().toPromise() : this.isSuperseded() ? At.superseded(this.options.current()).toPromise() : void 0
            }, o.prototype.toString = function () {
                var t = this.options,
                    e = this.registeredHook;
                return (R("traceData.hookType")(t) || "internal") + " context: " + (R("traceData.context.state.name")(t) || R("traceData.context")(t) || "unknown") + ", " + qt(200, Ut(e.callback))
            }, o.HANDLE_RESULT = function (e) {
                return function (t) {
                    return e.handleHookResult(t)
                }
            }, o.LOG_REJECTED_RESULT = function (e) {
                return function (t) {
                    I(t) && t.catch(function (t) {
                        return e.logError(At.normalize(t))
                    })
                }
            }, o.LOG_ERROR = function (e) {
                return function (t) {
                    return e.logError(t)
                }
            }, o.REJECT_ERROR = function (t) {
                return function (t) {
                    return Vt(t)
                }
            }, o.THROW_ERROR = function (t) {
                return function (t) {
                    throw t
                }
            }, o
        }();

    function Je(t, e) {
        var i = P(e) ? [e] : e;
        return !!(C(i) ? i : function (t) {
            for (var e = i, r = 0; r < e.length; r++) {
                var n = new jt(e[r]);
                if (n && n.matches(t.name) || !n && e[r] === t.name) return !0
            }
            return !1
        })(t)
    }
    var Qe = function () {
        function t(t, e, r, n, i, o) {
            void 0 === o && (o = {}), this.tranSvc = t, this.eventType = e, this.callback = r, this.matchCriteria = n, this.removeHookFromRegistry = i, this.invokeCount = 0, this._deregistered = !1, this.priority = o.priority || 0, this.bind = o.bind || null, this.invokeLimit = o.invokeLimit
        }
        return t.prototype._matchingNodes = function (t, e) {
            if (!0 === e) return t;
            var r = t.filter(function (t) {
                return Je(t.state, e)
            });
            return r.length ? r : null
        }, t.prototype._getDefaultMatchCriteria = function () {
            return ct(this.tranSvc._pluginapi._getPathTypes(), function () {
                return !0
            })
        }, t.prototype._getMatchingNodes = function (o) {
            var a = this,
                u = L(this._getDefaultMatchCriteria(), this.matchCriteria);
            return ht(this.tranSvc._pluginapi._getPathTypes()).reduce(function (t, e) {
                var r = e.scope === d.TransitionHookScope.STATE,
                    n = o[e.name] || [],
                    i = r ? n : [Ct(n)];
                return t[e.name] = a._matchingNodes(i, u[e.name]), t
            }, {})
        }, t.prototype.matches = function (t) {
            var e = this._getMatchingNodes(t);
            return ht(e).every(B) ? e : null
        }, t.prototype.deregister = function () {
            this.removeHookFromRegistry(this), this._deregistered = !0
        }, t
    }();

    function Ke(t, i, o) {
        var a = (t._registeredHooks = t._registeredHooks || {})[o.name] = [],
            u = K(a);

        function e(t, e, r) {
            void 0 === r && (r = {});
            var n = new Qe(i, o, e, t, u, r);
            return a.push(n), n.deregister.bind(n)
        }
        return t[o.name] = e
    }
    var Ye = function () {
        function t(t) {
            this.transition = t
        }
        return t.prototype.buildHooksForPhase = function (t) {
            var e = this;
            return this.transition.router.transitionService._pluginapi._getEvents(t).map(function (t) {
                return e.buildHooks(t)
            }).reduce(vt, []).filter(B)
        }, t.prototype.buildHooks = function (o) {
            var a = this.transition,
                t = a.treeChanges(),
                e = this.getMatchingHooks(o, t);
            if (!e) return [];
            var u = {
                transition: a,
                current: a.options().current
            };
            return e.map(function (i) {
                return i.matches(t)[o.criteriaMatchPath.name].map(function (t) {
                    var e = L({
                            bind: i.bind,
                            traceData: {
                                hookType: o.name,
                                context: t
                            }
                        }, u),
                        r = o.criteriaMatchPath.scope === d.TransitionHookScope.STATE ? t.state.self : null,
                        n = new ze(a, r, i, e);
                    return {
                        hook: i,
                        node: t,
                        transitionHook: n
                    }
                })
            }).reduce(vt, []).sort(function (i) {
                void 0 === i && (i = !1);
                return function (t, e) {
                    var r = i ? -1 : 1,
                        n = (t.node.state.path.length - e.node.state.path.length) * r;
                    return 0 !== n ? n : e.hook.priority - t.hook.priority
                }
            }(o.reverseSort)).map(function (t) {
                return t.transitionHook
            })
        }, t.prototype.getMatchingHooks = function (e, r) {
            var t = e.hookPhase === d.TransitionHookPhase.CREATE,
                n = this.transition.router.transitionService;
            return (t ? [n] : [this.transition, n]).map(function (t) {
                return t.getHooks(e.name)
            }).filter(_t(O, "broken event named: " + e.name)).reduce(vt, []).filter(function (t) {
                return t.matches(r)
            })
        }, t
    }();
    var Ze = w("self"),
        Xe = function () {
            function e(t, e, r) {
                var n = this;
                if (this._deferred = A.$q.defer(), this.promise = this._deferred.promise, this._registeredHooks = {}, this._hookBuilder = new Ye(this), this.isActive = function () {
                        return n.router.globals.transition === n
                    }, this.router = r, !(this._targetState = e).valid()) throw new Error(e.error());
                this._options = L({
                    current: v(this)
                }, e.options()), this.$id = r.transitionService._transitionCount++;
                var i = Se.buildToPath(t, e);
                this._treeChanges = Se.treeChanges(t, i, this._options.reloadState), this.createTransitionHookRegFns();
                var o = this._hookBuilder.buildHooksForPhase(d.TransitionHookPhase.CREATE);
                ze.invokeHooks(o, function () {
                    return null
                }), this.applyViewConfigs(r)
            }
            return e.prototype.onBefore = function (t, e, r) {}, e.prototype.onStart = function (t, e, r) {}, e.prototype.onExit = function (t, e, r) {}, e.prototype.onRetain = function (t, e, r) {}, e.prototype.onEnter = function (t, e, r) {}, e.prototype.onFinish = function (t, e, r) {}, e.prototype.onSuccess = function (t, e, r) {}, e.prototype.onError = function (t, e, r) {}, e.prototype.createTransitionHookRegFns = function () {
                var e = this;
                this.router.transitionService._pluginapi._getEvents().filter(function (t) {
                    return t.hookPhase !== d.TransitionHookPhase.CREATE
                }).forEach(function (t) {
                    return Ke(e, e.router.transitionService, t)
                })
            }, e.prototype.getHooks = function (t) {
                return this._registeredHooks[t]
            }, e.prototype.applyViewConfigs = function (t) {
                var e = this._treeChanges.entering.map(function (t) {
                    return t.state
                });
                Se.applyViewConfigs(t.transitionService.$view, this._treeChanges.to, e)
            }, e.prototype.$from = function () {
                return Ct(this._treeChanges.from).state
            }, e.prototype.$to = function () {
                return Ct(this._treeChanges.to).state
            }, e.prototype.from = function () {
                return this.$from().self
            }, e.prototype.to = function () {
                return this.$to().self
            }, e.prototype.targetState = function () {
                return this._targetState
            }, e.prototype.is = function (t) {
                return t instanceof e ? this.is({
                    to: t.$to().name,
                    from: t.$from().name
                }) : !(t.to && !Je(this.$to(), t.to) || t.from && !Je(this.$from(), t.from))
            }, e.prototype.params = function (t) {
                return void 0 === t && (t = "to"), Object.freeze(this._treeChanges[t].map(w("paramValues")).reduce(rt, {}))
            }, e.prototype.paramsChanged = function () {
                var t = this.params("from"),
                    r = this.params("to"),
                    e = [].concat(this._treeChanges.to).concat(this._treeChanges.from).map(function (t) {
                        return t.paramSchema
                    }).reduce(dt, []).reduce(yt, []);
                return ye.changed(e, t, r).reduce(function (t, e) {
                    return t[e.id] = r[e.id], t
                }, {})
            }, e.prototype.injector = function (e, t) {
                void 0 === t && (t = "to");
                var r = this._treeChanges[t];
                return e && (r = Se.subPath(r, function (t) {
                    return t.state === e || t.state.name === e
                })), new Oe(r).injector()
            }, e.prototype.getResolveTokens = function (t) {
                return void 0 === t && (t = "to"), new Oe(this._treeChanges[t]).getTokens()
            }, e.prototype.addResolvable = function (t, e) {
                void 0 === e && (e = ""), t = p(Ee)(t) ? t : new Ee(t);
                var r = "string" == typeof e ? e : e.name,
                    n = this._treeChanges.to,
                    i = st(n, function (t) {
                        return t.state.name === r
                    });
                new Oe(n).addResolvables([t], i.state)
            }, e.prototype.redirectedFrom = function () {
                return this._options.redirectedFrom || null
            }, e.prototype.originalTransition = function () {
                var t = this.redirectedFrom();
                return t && t.originalTransition() || this
            }, e.prototype.options = function () {
                return this._options
            }, e.prototype.entering = function () {
                return ft(this._treeChanges.entering, w("state")).map(Ze)
            }, e.prototype.exiting = function () {
                return ft(this._treeChanges.exiting, w("state")).map(Ze).reverse()
            }, e.prototype.retained = function () {
                return ft(this._treeChanges.retained, w("state")).map(Ze)
            }, e.prototype.views = function (t, e) {
                void 0 === t && (t = "entering");
                var r = this._treeChanges[t];
                return (r = e ? r.filter(y("state", e)) : r).map(w("views")).filter(B).reduce(vt, [])
            }, e.prototype.treeChanges = function (t) {
                return t ? this._treeChanges[t] : this._treeChanges
            }, e.prototype.redirect = function (t) {
                for (var e = 1, r = this; null != (r = r.redirectedFrom());)
                    if (20 < ++e) throw new Error("Too many consecutive Transition redirects (20+)");
                var n = {
                    redirectedFrom: this,
                    source: "redirect"
                };
                "url" === this.options().source && !1 !== t.options().location && (n.location = "replace");
                var i = L({}, this.options(), t.options(), n);
                t = t.withOptions(i, !0);
                var o, a = this.router.transitionService.create(this._treeChanges.from, t),
                    u = this._treeChanges.entering,
                    s = a._treeChanges.entering;
                return Se.matching(s, u, Se.nonDynamicParams).filter(h((o = t.options().reloadState, function (t) {
                    return o && t.state.includes[o.name]
                }))).forEach(function (t, e) {
                    t.resolvables = u[e].resolvables
                }), a
            }, e.prototype._changedParams = function () {
                var t = this._treeChanges;
                if (!this._options.reload && (!t.exiting.length && !t.entering.length && t.to.length === t.from.length && !Rt(t.to, t.from).map(function (t) {
                        return t[0].state !== t[1].state
                    }).reduce(pt, !1))) {
                    var e = t.to.map(function (t) {
                            return t.paramSchema
                        }),
                        r = [t.to, t.from].map(function (t) {
                            return t.map(function (t) {
                                return t.paramValues
                            })
                        });
                    return Rt(e, r[0], r[1]).map(function (t) {
                        var e = t[0],
                            r = t[1],
                            n = t[2];
                        return ye.changed(e, r, n)
                    }).reduce(vt, [])
                }
            }, e.prototype.dynamic = function () {
                var t = this._changedParams();
                return !!t && t.map(function (t) {
                    return t.dynamic
                }).reduce(pt, !1)
            }, e.prototype.ignored = function () {
                return !!this._ignoredReason()
            }, e.prototype._ignoredReason = function () {
                var t = this.router.globals.transition,
                    n = this._options.reloadState,
                    e = function (t, e) {
                        if (t.length !== e.length) return !1;
                        var r = Se.matching(t, e);
                        return t.length === r.filter(function (t) {
                            return !n || !t.state.includes[n.name]
                        }).length
                    },
                    r = this.treeChanges(),
                    i = t && t.treeChanges();
                return i && e(i.to, r.to) && e(i.exiting, r.exiting) ? "SameAsPending" : 0 === r.exiting.length && 0 === r.entering.length && e(r.from, r.to) ? "SameAsCurrent" : void 0
            }, e.prototype.run = function () {
                var e = this,
                    r = ze.runAllHooks,
                    n = function (t) {
                        return e._hookBuilder.buildHooksForPhase(t)
                    },
                    t = n(d.TransitionHookPhase.BEFORE);
                return ze.invokeHooks(t, function () {
                    var t = e.router.globals;
                    return t.lastStartedTransitionId = e.$id, t.transition = e, t.transitionHistory.enqueue(e), ce.traceTransitionStart(e), A.$q.when(void 0)
                }).then(function () {
                    var t = n(d.TransitionHookPhase.RUN);
                    return ze.invokeHooks(t, function () {
                        return A.$q.when(void 0)
                    })
                }).then(function () {
                    ce.traceSuccess(e.$to(), e), e.success = !0, e._deferred.resolve(e.to()), r(n(d.TransitionHookPhase.SUCCESS))
                }, function (t) {
                    ce.traceError(t, e), e.success = !1, e._deferred.reject(t), e._error = t, r(n(d.TransitionHookPhase.ERROR))
                }), this.promise
            }, e.prototype.valid = function () {
                return !this.error() || void 0 !== this.success
            }, e.prototype.abort = function () {
                _(this.success) && (this._aborted = !0)
            }, e.prototype.error = function () {
                var t = this.$to();
                if (t.self.abstract) return At.invalid("Cannot transition to abstract state '" + t.name + "'");
                var e = t.parameters(),
                    r = this.params(),
                    n = e.filter(function (t) {
                        return !t.validates(r[t.id])
                    });
                if (n.length) {
                    var i = n.map(function (t) {
                            return "[" + t.id + ":" + Bt(r[t.id]) + "]"
                        }).join(", "),
                        o = "The following parameter values are not valid for state '" + t.name + "': " + i;
                    return At.invalid(o)
                }
                return !1 === this.success ? this._error : void 0
            }, e.prototype.toString = function () {
                var t = this.from(),
                    e = this.to(),
                    r = function (t) {
                        return null !== t["#"] && void 0 !== t["#"] ? t : ot(t, ["#"])
                    };
                return "Transition#" + this.$id + "( '" + (k(t) ? t.name : t) + "'" + Bt(r(this._treeChanges.from.map(w("paramValues")).reduce(rt, {}))) + " -> " + (this.valid() ? "" : "(X) ") + "'" + (k(e) ? e.name : e) + "'" + Bt(r(this.params())) + " )"
            }, e.diToken = e
        }();

    function tr(t, e) {
        var r = ["", ""],
            n = t.replace(/[\\\[\]\^$*+?.()|{}]/g, "\\$&");
        if (!e) return n;
        switch (e.squash) {
            case !1:
                r = ["(", ")" + (e.isOptional ? "?" : "")];
                break;
            case !0:
                n = n.replace(/\/$/, ""), r = ["(?:/(", ")|/)?"];
                break;
            default:
                r = ["(" + e.squash + "|", ")?"]
        }
        return n + r[0] + e.type.pattern.source + r[1]
    }
    var er = Zt("/"),
        rr = {
            state: {
                params: {}
            },
            strict: !0,
            caseInsensitive: !0
        },
        nr = function () {
            function m(o, a, t, e) {
                var u = this;
                this._cache = {
                    path: [this]
                }, this._children = [], this._params = [], this._segments = [], this._compiled = [], this.config = e = et(e, rr), this.pattern = o;
                for (var r, n, i, s = /([:*])([\w\[\]]+)|\{([\w\[\]]+)(?:\:\s*((?:[^{}\\]+|\\.|\{(?:[^{}\\]+|\\.)*\})+))?\}/g, c = /([:]?)([\w\[\].-]+)|\{([\w\[\].-]+)(?:\:\s*((?:[^{}\\]+|\\.|\{(?:[^{}\\]+|\\.)*\})+))?\}/g, f = [], h = 0, l = function (t) {
                        if (!m.nameValidator.test(t)) throw new Error("Invalid parameter name '" + t + "' in pattern '" + o + "'");
                        if (st(u._params, y("id", t))) throw new Error("Duplicate parameter name '" + t + "' in pattern '" + o + "'")
                    }, p = function (t, e) {
                        var r, n = t[2] || t[3],
                            i = e ? t[4] : t[4] || ("*" === t[1] ? "[\\s\\S]*" : null);
                        return {
                            id: n,
                            regexp: i,
                            segment: o.substring(h, t.index),
                            type: i ? a.type(i) || (r = i, z(a.type(e ? "query" : "path"), {
                                pattern: new RegExp(r, u.config.caseInsensitive ? "i" : void 0)
                            })) : null
                        }
                    };
                    (r = s.exec(o)) && !(0 <= (n = p(r, !1)).segment.indexOf("?"));) l(n.id), this._params.push(t.fromPath(n.id, n.type, e.state)), this._segments.push(n.segment), f.push([n.segment, Ct(this._params)]), h = s.lastIndex;
                var v = (i = o.substring(h)).indexOf("?");
                if (0 <= v) {
                    var d = i.substring(v);
                    if (i = i.substring(0, v), 0 < d.length)
                        for (h = 0; r = c.exec(d);) l((n = p(r, !0)).id), this._params.push(t.fromSearch(n.id, n.type, e.state)), h = s.lastIndex
                }
                this._segments.push(i), this._compiled = f.map(function (t) {
                    return tr.apply(null, t)
                }).concat(tr(i))
            }
            return m.encodeDashes = function (t) {
                return encodeURIComponent(t).replace(/-/g, function (t) {
                    return "%5C%" + t.charCodeAt(0).toString(16).toUpperCase()
                })
            }, m.pathSegmentsAndParams = function (t) {
                return Rt(t._segments, t._params.filter(function (t) {
                    return t.location === d.DefType.PATH
                }).concat(void 0)).reduce(vt, []).filter(function (t) {
                    return "" !== t && E(t)
                })
            }, m.queryParams = function (t) {
                return t._params.filter(function (t) {
                    return t.location === d.DefType.SEARCH
                })
            }, m.compare = function (t, e) {
                var r = function (t) {
                        return t._cache.weights = t._cache.weights || (e = t, e._cache.segments = e._cache.segments || e._cache.path.map(m.pathSegmentsAndParams).reduce(vt, []).reduce(Xt, []).map(function (t) {
                            return P(t) ? er(t) : t
                        }).reduce(vt, [])).map(function (t) {
                            return "/" === t ? 1 : P(t) ? 2 : t instanceof ye ? 3 : void 0
                        });
                        var e
                    },
                    n = r(t),
                    i = r(e);
                ! function (t, e, r) {
                    for (var n = Math.max(t.length, e.length); t.length < n;) t.push(r);
                    for (; e.length < n;) e.push(r)
                }(n, i, 0);
                var o, a, u = Rt(n, i);
                for (a = 0; a < u.length; a++)
                    if (0 !== (o = u[a][0] - u[a][1])) return o;
                return 0
            }, m.prototype.append = function (t) {
                return this._children.push(t), t._cache = {
                    path: this._cache.path.concat(t),
                    parent: this,
                    pattern: null
                }, t
            }, m.prototype.isRoot = function () {
                return this._cache.path[0] === this
            }, m.prototype.toString = function () {
                return this.pattern
            }, m.prototype.exec = function (t, n, e, r) {
                var i = this;
                void 0 === n && (n = {}), void 0 === r && (r = {});
                var o, a, u, s = (o = this._cache, a = "pattern", u = function () {
                    return new RegExp(["^", gt(i._cache.path.map(w("_compiled"))).join(""), !1 === i.config.strict ? "/?" : "", "$"].join(""), i.config.caseInsensitive ? "i" : void 0)
                }, o[a] = o[a] || u()).exec(t);
                if (!s) return null;
                var c, f = this.parameters(),
                    h = f.filter(function (t) {
                        return !t.isSearch()
                    }),
                    l = f.filter(function (t) {
                        return t.isSearch()
                    }),
                    p = this._cache.path.map(function (t) {
                        return t._segments.length - 1
                    }).reduce(function (t, e) {
                        return t + e
                    }),
                    v = {};
                if (p !== s.length - 1) throw new Error("Unbalanced capture group in route '" + this.pattern + "'");
                for (var d = 0; d < p; d++) {
                    for (var m = h[d], y = s[d + 1], g = 0; g < m.replace.length; g++) m.replace[g].from === y && (y = m.replace[g].to);
                    y && !0 === m.array && (void 0, y = ft(ft((c = function (t) {
                        return t.split("").reverse().join("")
                    })(y).split(/-(?!\\)/), c), function (t) {
                        return t.replace(/\\-/g, "-")
                    }).reverse()), E(y) && (y = m.type.decode(y)), v[m.id] = m.value(y)
                }
                return l.forEach(function (t) {
                    for (var e = n[t.id], r = 0; r < t.replace.length; r++) t.replace[r].from === e && (e = t.replace[r].to);
                    E(e) && (e = t.type.decode(e)), v[t.id] = t.value(e)
                }), e && (v["#"] = e), v
            }, m.prototype.parameters = function (t) {
                return void 0 === t && (t = {}), !1 === t.inherit ? this._params : gt(this._cache.path.map(function (t) {
                    return t._params
                }))
            }, m.prototype.parameter = function (n, t) {
                var i = this;
                void 0 === t && (t = {});
                var e = this._cache.parent;
                return function () {
                    for (var t = 0, e = i._params; t < e.length; t++) {
                        var r = e[t];
                        if (r.id === n) return r
                    }
                }() || !1 !== t.inherit && e && e.parameter(n, t) || null
            }, m.prototype.validates = function (n) {
                return n = n || {}, this.parameters().filter(function (t) {
                    return n.hasOwnProperty(t.id)
                }).map(function (t) {
                    return r = n[(e = t).id], !e || e.validates(r);
                    var e, r
                }).reduce(lt, !0)
            }, m.prototype.format = function (a) {
                void 0 === a && (a = {});
                var t = this._cache.path,
                    e = t.map(m.pathSegmentsAndParams).reduce(vt, []).map(function (t) {
                        return P(t) ? t : n(t)
                    }),
                    r = t.map(m.queryParams).reduce(vt, []).map(n);
                if (e.concat(r).filter(function (t) {
                        return !1 === t.isValid
                    }).length) return null;

                function n(t) {
                    var e = t.value(a[t.id]),
                        r = t.validates(e),
                        n = t.isDefaultValue(e),
                        i = !!n && t.squash,
                        o = t.type.encode(e);
                    return {
                        param: t,
                        value: e,
                        isValid: r,
                        isDefaultValue: n,
                        squash: i,
                        encoded: o
                    }
                }
                var i = e.reduce(function (t, e) {
                        if (P(e)) return t + e;
                        var r = e.squash,
                            n = e.encoded,
                            i = e.param;
                        return !0 === r ? t.match(/\/$/) ? t.slice(0, -1) : t : P(r) ? t + r : !1 !== r ? t : null == n ? t : O(n) ? t + ft(n, m.encodeDashes).join("-") : i.raw ? t + n : t + encodeURIComponent(n)
                    }, ""),
                    o = r.map(function (t) {
                        var e = t.param,
                            r = t.squash,
                            n = t.encoded,
                            i = t.isDefaultValue;
                        if (!(null == n || i && !1 !== r) && (O(n) || (n = [n]), 0 !== n.length)) return e.raw || (n = ft(n, encodeURIComponent)), n.map(function (t) {
                            return e.id + "=" + t
                        })
                    }).filter(B).reduce(vt, []).join("&");
                return i + (o ? "?" + o : "") + (a["#"] ? "#" + a["#"] : "")
            }, m.nameValidator = /^\w+([-.]+\w+)*(?:\[\])?$/, m
        }(),
        ir = Object.assign || function (t) {
            for (var e, r = 1, n = arguments.length; r < n; r++)
                for (var i in e = arguments[r]) Object.prototype.hasOwnProperty.call(e, i) && (t[i] = e[i]);
            return t
        },
        or = function () {
            function t(t) {
                this.umf = t
            }
            return t.prototype.fromConfig = function (t, e, r) {
                return new ye(t, e, d.DefType.CONFIG, this.umf, r)
            }, t.prototype.fromPath = function (t, e, r) {
                return new ye(t, e, d.DefType.PATH, this.umf, r)
            }, t.prototype.fromSearch = function (t, e, r) {
                return new ye(t, e, d.DefType.SEARCH, this.umf, r)
            }, t
        }(),
        ar = function () {
            function t() {
                this.paramTypes = new ge, this._isCaseInsensitive = !1, this._isStrictMode = !0, this._defaultSquashPolicy = !1, this.paramFactory = new or(this), L(this, {
                    UrlMatcher: nr,
                    Param: ye
                })
            }
            return t.prototype.caseInsensitive = function (t) {
                return this._isCaseInsensitive = E(t) ? t : this._isCaseInsensitive
            }, t.prototype.strictMode = function (t) {
                return this._isStrictMode = E(t) ? t : this._isStrictMode
            }, t.prototype.defaultSquashPolicy = function (t) {
                if (E(t) && !0 !== t && !1 !== t && !P(t)) throw new Error("Invalid squash policy: " + t + ". Valid policies: false, true, arbitrary-string");
                return this._defaultSquashPolicy = E(t) ? t : this._defaultSquashPolicy
            }, t.prototype.compile = function (t, e) {
                var r = e && !e.state && e.params;
                e = r ? ir({
                    state: {
                        params: r
                    }
                }, e) : e;
                var n = {
                    strict: this._isStrictMode,
                    caseInsensitive: this._isCaseInsensitive
                };
                return new nr(t, this.paramTypes, this.paramFactory, L(n, e))
            }, t.prototype.isMatcher = function (r) {
                if (!k(r)) return !1;
                var n = !0;
                return U(nr.prototype, function (t, e) {
                    C(t) && (n = n && E(r[e]) && C(r[e]))
                }), n
            }, t.prototype.type = function (t, e, r) {
                var n = this.paramTypes.type(t, e, r);
                return E(e) ? this : n
            }, t.prototype.$get = function () {
                return this.paramTypes.enqueue = !1, this.paramTypes._flushTypeQueue(), this
            }, t.prototype.dispose = function () {
                this.paramTypes.dispose()
            }, t
        }(),
        ur = function () {
            function t(t) {
                this.router = t
            }
            return t.prototype.compile = function (t) {
                return this.router.urlMatcherFactory.compile(t)
            }, t.prototype.create = function (t, e) {
                var r = this,
                    n = Le.isState,
                    i = m([
                        [P, function (t) {
                            return i(r.compile(t))
                        }],
                        [p(nr), function (t) {
                            return r.fromUrlMatcher(t, e)
                        }],
                        [n, function (t) {
                            return r.fromState(t, r.router)
                        }],
                        [p(RegExp), function (t) {
                            return r.fromRegExp(t, e)
                        }],
                        [C, function (t) {
                            return new sr(t, e)
                        }]
                    ]),
                    o = i(t);
                if (!o) throw new Error("invalid 'what' in when()");
                return o
            }, t.prototype.fromUrlMatcher = function (r, e) {
                var t = e;
                P(e) && (e = this.router.urlMatcherFactory.compile(e)), p(nr)(e) && (t = function (t) {
                    return e.format(t)
                });
                var n = {
                    urlMatcher: r,
                    matchPriority: function (e) {
                        var t = r.parameters().filter(function (t) {
                            return t.isOptional
                        });
                        return t.length ? t.filter(function (t) {
                            return e[t.id]
                        }).length / t.length : 1e-6
                    },
                    type: "URLMATCHER"
                };
                return L(new sr(function (t) {
                    var e = r.exec(t.path, t.search, t.hash);
                    return r.validates(e) && e
                }, t), n)
            }, t.prototype.fromState = function (n, i) {
                var t = {
                    state: n,
                    type: "STATE"
                };
                return L(this.fromUrlMatcher(n.url, function (t) {
                    var e = i.stateService,
                        r = i.globals;
                    e.href(n, t) !== e.href(r.current, r.params) && e.transitionTo(n, t, {
                        inherit: !0,
                        source: "url"
                    })
                }), t)
            }, t.prototype.fromRegExp = function (e, t) {
                if (e.global || e.sticky) throw new Error("Rule RegExp must not be global or sticky");
                var r = P(t) ? function (r) {
                        return t.replace(/\$(\$|\d{1,2})/, function (t, e) {
                            return r["$" === e ? 0 : Number(e)]
                        })
                    } : t,
                    n = {
                        regexp: e,
                        type: "REGEXP"
                    };
                return L(new sr(function (t) {
                    return e.exec(t.path)
                }, r), n)
            }, t.isUrlRule = function (e) {
                return e && ["type", "match", "handler"].every(function (t) {
                    return E(e[t])
                })
            }, t
        }(),
        sr = function (t, e) {
            var r = this;
            this.match = t, this.type = "RAW", this.matchPriority = function (t) {
                return 0 - r.$id
            }, this.handler = e || B
        };
    var cr;
    cr = function (t, e) {
        var r, n, i, o, a, u, s, c, f = (r = t, (e.priority || 0) - (r.priority || 0));
        return 0 !== f ? f : (n = e, 0 !== (f = ((i = {
            STATE: 4,
            URLMATCHER: 4,
            REGEXP: 3,
            RAW: 2,
            OTHER: 1
        })[t.type] || 0) - (i[n.type] || 0)) ? f : (a = e, 0 !== (f = (o = t).urlMatcher && a.urlMatcher ? nr.compare(o.urlMatcher, a.urlMatcher) : 0) ? f : (s = e, (c = {
            STATE: !0,
            URLMATCHER: !0
        })[(u = t).type] && c[s.type] ? 0 : (u.$id || 0) - (s.$id || 0))))
    };
    var fr = function () {
        function e(t) {
            this._sortFn = cr, this._rules = [], this.interceptDeferred = !1, this._id = 0, this._sorted = !1, this._router = t, this.urlRuleFactory = new ur(t), W(v(e.prototype), this, v(this))
        }
        return e.prototype.dispose = function () {
            this.listen(!1), this._rules = [], delete this._otherwiseFn
        }, e.prototype.sort = function (t) {
            this._rules = this.stableSort(this._rules, this._sortFn = t || this._sortFn), this._sorted = !0
        }, e.prototype.ensureSorted = function () {
            this._sorted || this.sort()
        }, e.prototype.stableSort = function (t, n) {
            var e = t.map(function (t, e) {
                return {
                    elem: t,
                    idx: e
                }
            });
            return e.sort(function (t, e) {
                var r = n(t.elem, e.elem);
                return 0 === r ? t.idx - e.idx : r
            }), e.map(function (t) {
                return t.elem
            })
        }, e.prototype.match = function (t) {
            var e = this;
            this.ensureSorted(), t = L({
                path: "",
                search: {},
                hash: ""
            }, t);
            var r = this.rules();
            this._otherwiseFn && r.push(this._otherwiseFn);
            for (var n, i, o, a = 0; a < r.length && (!n || 0 === this._sortFn(r[a], n.rule)); a++) {
                var u = (i = r[a], void 0, (o = i.match(t, e._router)) && {
                    match: o,
                    rule: i,
                    weight: i.matchPriority(o)
                });
                n = !n || u && u.weight > n.weight ? u : n
            }
            return n
        }, e.prototype.sync = function (t) {
            if (!t || !t.defaultPrevented) {
                var e = this._router,
                    r = e.urlService,
                    n = e.stateService,
                    i = {
                        path: r.path(),
                        search: r.search(),
                        hash: r.hash()
                    },
                    o = this.match(i);
                m([
                    [P, function (t) {
                        return r.url(t, !0)
                    }],
                    [$e.isDef, function (t) {
                        return n.go(t.state, t.params, t.options)
                    }],
                    [p($e), function (t) {
                        return n.go(t.state(), t.params(), t.options())
                    }]
                ])(o && o.rule.handler(o.match, i, e))
            }
        }, e.prototype.listen = function (t) {
            var e = this;
            if (!1 !== t) return this._stopFn = this._stopFn || this._router.urlService.onChange(function (t) {
                return e.sync(t)
            });
            this._stopFn && this._stopFn(), delete this._stopFn
        }, e.prototype.update = function (t) {
            var e = this._router.locationService;
            t ? this.location = e.url() : e.url() !== this.location && e.url(this.location, !0)
        }, e.prototype.push = function (t, e, r) {
            var n = r && !!r.replace;
            this._router.urlService.url(t.format(e || {}), n)
        }, e.prototype.href = function (t, e, r) {
            var n = t.format(e);
            if (null == n) return null;
            r = r || {
                absolute: !1
            };
            var i, o, a, u, s = this._router.urlService.config,
                c = s.html5Mode();
            if (c || null === n || (n = "#" + s.hashPrefix() + n), i = n, o = c, a = r.absolute, n = "/" === (u = s.baseHref()) ? i : o ? zt(u) + i : a ? u.slice(1) + i : i, !r.absolute || !n) return n;
            var f = !c && n ? "/" : "",
                h = s.port(),
                l = 80 === h || 443 === h ? "" : ":" + h;
            return [s.protocol(), "://", s.host(), l, f, n].join("")
        }, e.prototype.rule = function (t) {
            var e = this;
            if (!ur.isUrlRule(t)) throw new Error("invalid rule");
            return t.$id = this._id++, t.priority = t.priority || 0, this._rules.push(t), this._sorted = !1,
                function () {
                    return e.removeRule(t)
                }
        }, e.prototype.removeRule = function (t) {
            K(this._rules, t)
        }, e.prototype.rules = function () {
            return this.ensureSorted(), this._rules.slice()
        }, e.prototype.otherwise = function (t) {
            var e = hr(t);
            this._otherwiseFn = this.urlRuleFactory.create(v(!0), e), this._sorted = !1
        }, e.prototype.initial = function (t) {
            var e = hr(t);
            this.rule(this.urlRuleFactory.create(function (t, e) {
                return 0 === e.globals.transitionHistory.size() && !!/^\/?$/.exec(t.path)
            }, e))
        }, e.prototype.when = function (t, e, r) {
            var n = this.urlRuleFactory.create(t, e);
            return E(r && r.priority) && (n.priority = r.priority), this.rule(n), n
        }, e.prototype.deferIntercept = function (t) {
            void 0 === t && (t = !0), this.interceptDeferred = t
        }, e
    }();

    function hr(t) {
        if (!(C(t) || P(t) || p($e)(t) || $e.isDef(t))) throw new Error("'handler' must be a string, function, TargetState, or have a state: 'newtarget' property");
        return C(t) ? t : v(t)
    }
    var lr = function () {
            function s(t) {
                var r = this;
                this.router = t, this._uiViews = [], this._viewConfigs = [], this._viewConfigFactories = {}, this._listeners = [], this._pluginapi = {
                    _rootViewContext: this._rootViewContext.bind(this),
                    _viewConfigFactory: this._viewConfigFactory.bind(this),
                    _registeredUIView: function (e) {
                        return st(r._uiViews, function (t) {
                            return r.router.$id + "." + t.id === e
                        })
                    },
                    _registeredUIViews: function () {
                        return r._uiViews
                    },
                    _activeViewConfigs: function () {
                        return r._viewConfigs
                    },
                    _onSync: function (t) {
                        return r._listeners.push(t),
                            function () {
                                return K(r._listeners, t)
                            }
                    }
                }
            }
            return s.normalizeUIViewTarget = function (t, e) {
                void 0 === e && (e = "");
                var r = e.split("@"),
                    n = r[0] || "$default",
                    i = P(r[1]) ? r[1] : "^",
                    o = /^(\^(?:\.\^)*)\.(.*$)/.exec(n);
                o && (i = o[1], n = o[2]), "!" === n.charAt(0) && (n = n.substr(1), i = "");
                /^(\^(?:\.\^)*)$/.exec(i) ? i = i.split(".").reduce(function (t, e) {
                    return t.parent
                }, t).name : "." === i && (i = t.name);
                return {
                    uiViewName: n,
                    uiViewContextAnchor: i
                }
            }, s.prototype._rootViewContext = function (t) {
                return this._rootContext = t || this._rootContext
            }, s.prototype._viewConfigFactory = function (t, e) {
                this._viewConfigFactories[t] = e
            }, s.prototype.createViewConfig = function (t, e) {
                var r = this._viewConfigFactories[e.$type];
                if (!r) throw new Error("ViewService: No view config factory registered for type " + e.$type);
                var n = r(t, e);
                return O(n) ? n : [n]
            }, s.prototype.deactivateViewConfig = function (t) {
                ce.traceViewServiceEvent("<- Removing", t), K(this._viewConfigs, t)
            }, s.prototype.activateViewConfig = function (t) {
                ce.traceViewServiceEvent("-> Registering", t), this._viewConfigs.push(t)
            }, s.prototype.sync = function () {
                var r = this,
                    n = this._uiViews.map(function (t) {
                        return [t.fqn, t]
                    }).reduce(Et, {});

                function i(t) {
                    for (var e = t.viewDecl.$context, r = 0; ++r && e.parent;) e = e.parent;
                    return r
                }
                var o = c(function (t, e, r, n) {
                        return e * (t(r) - t(n))
                    }),
                    t = this._uiViews.sort(o(function (t) {
                        var e = function (t) {
                            return t && t.parent ? e(t.parent) + 1 : 1
                        };
                        return 1e4 * t.fqn.split(".").length + e(t.creationContext)
                    }, 1)).map(function (t) {
                        var e = r._viewConfigs.filter(s.matches(n, t));
                        return 1 < e.length && e.sort(o(i, -1)), {
                            uiView: t,
                            viewConfig: e[0]
                        }
                    }),
                    e = t.map(function (t) {
                        return t.viewConfig
                    }),
                    a = this._viewConfigs.filter(function (t) {
                        return !J(e, t)
                    }).map(function (t) {
                        return {
                            uiView: void 0,
                            viewConfig: t
                        }
                    });
                t.forEach(function (t) {
                    -1 !== r._uiViews.indexOf(t.uiView) && t.uiView.configUpdated(t.viewConfig)
                });
                var u = t.concat(a);
                this._listeners.forEach(function (t) {
                    return t(u)
                }), ce.traceViewSync(u)
            }, s.prototype.registerUIView = function (e) {
                ce.traceViewServiceUIViewEvent("-> Registering", e);
                var t = this._uiViews;
                return t.filter(function (t) {
                        return t.fqn === e.fqn && t.$type === e.$type
                    }).length && ce.traceViewServiceUIViewEvent("!!!! duplicate uiView named:", e), t.push(e), this.sync(),
                    function () {
                        -1 !== t.indexOf(e) ? (ce.traceViewServiceUIViewEvent("<- Deregistering", e), K(t)(e)) : ce.traceViewServiceUIViewEvent("Tried removing non-registered uiView", e)
                    }
            }, s.prototype.available = function () {
                return this._uiViews.map(w("fqn"))
            }, s.prototype.active = function () {
                return this._uiViews.filter(w("$config")).map(w("name"))
            }, s.matches = function (u, s) {
                return function (t) {
                    if (s.$type !== t.viewDecl.$type) return !1;
                    var e = t.viewDecl,
                        r = e.$uiViewName.split("."),
                        n = s.fqn.split(".");
                    if (!M(r, n.slice(0 - r.length))) return !1;
                    var i = 1 - r.length || void 0,
                        o = n.slice(0, i).join("."),
                        a = u[o].creationContext;
                    return e.$uiViewContextAnchor === (a && a.name)
                }
            }, s
        }(),
        pr = function () {
            function t() {
                this.params = new we, this.lastStartedTransitionId = -1, this.transitionHistory = new It([], 1), this.successfulTransitions = new It([], 1)
            }
            return t.prototype.dispose = function () {
                this.transitionHistory.clear(), this.successfulTransitions.clear(), this.transition = null
            }, t
        }(),
        vr = function (t) {
            return t.reduce(function (t, e) {
                return t[e] = H(e), t
            }, {
                dispose: G
            })
        },
        dr = ["url", "path", "search", "hash", "onChange"],
        mr = ["port", "protocol", "host", "baseHref", "html5Mode", "hashPrefix"],
        yr = ["type", "caseInsensitive", "strictMode", "defaultSquashPolicy"],
        gr = ["sort", "when", "initial", "otherwise", "rules", "rule", "removeRule"],
        wr = ["deferIntercept", "listen", "sync", "match"],
        _r = function () {
            function t(t, e) {
                void 0 === e && (e = !0), this.router = t, this.rules = {}, this.config = {};
                var r = function () {
                    return t.locationService
                };
                W(r, this, r, dr, e);
                var n = function () {
                    return t.locationConfig
                };
                W(n, this.config, n, mr, e);
                var i = function () {
                    return t.urlMatcherFactory
                };
                W(i, this.config, i, yr);
                var o = function () {
                    return t.urlRouter
                };
                W(o, this.rules, o, gr), W(o, this, o, wr)
            }
            return t.prototype.url = function (t, e, r) {}, t.prototype.path = function () {}, t.prototype.search = function () {}, t.prototype.hash = function () {}, t.prototype.onChange = function (t) {}, t.prototype.parts = function () {
                return {
                    path: this.path(),
                    search: this.search(),
                    hash: this.hash()
                }
            }, t.prototype.dispose = function () {}, t.prototype.sync = function (t) {}, t.prototype.listen = function (t) {}, t.prototype.deferIntercept = function (t) {}, t.prototype.match = function (t) {}, t.locationServiceStub = vr(dr), t.locationConfigStub = vr(mr), t
        }(),
        $r = 0,
        Sr = function () {
            function t(t, e) {
                void 0 === t && (t = _r.locationServiceStub), void 0 === e && (e = _r.locationConfigStub), this.locationService = t, this.locationConfig = e, this.$id = $r++, this._disposed = !1, this._disposables = [], this.trace = ce, this.viewService = new lr(this), this.globals = new pr, this.transitionService = new Br(this), this.urlMatcherFactory = new ar, this.urlRouter = new fr(this), this.stateRegistry = new Ge(this), this.stateService = new Gr(this), this.urlService = new _r(this), this._plugins = {}, this.viewService._pluginapi._rootViewContext(this.stateRegistry.root()), this.globals.$current = this.stateRegistry.root(), this.globals.current = this.globals.$current.self, this.disposable(this.globals), this.disposable(this.stateService), this.disposable(this.stateRegistry), this.disposable(this.transitionService), this.disposable(this.urlRouter), this.disposable(t), this.disposable(e)
            }
            return t.prototype.disposable = function (t) {
                this._disposables.push(t)
            }, t.prototype.dispose = function (t) {
                var e = this;
                t && C(t.dispose) ? t.dispose(this) : (this._disposed = !0, this._disposables.slice().forEach(function (t) {
                    try {
                        "function" == typeof t.dispose && t.dispose(e), K(e._disposables, t)
                    } catch (t) {}
                }))
            }, t.prototype.plugin = function (t, e) {
                void 0 === e && (e = {});
                var r = new t(this, e);
                if (!r.name) throw new Error("Required property `name` missing on plugin: " + r);
                return this._disposables.push(r), this._plugins[r.name] = r
            }, t.prototype.getPlugin = function (t) {
                return t ? this._plugins[t] : ht(this._plugins)
            }, t
        }();

    function br(e) {
        e.addResolvable(Ee.fromData(Sr, e.router), ""), e.addResolvable(Ee.fromData(Xe, e), ""), e.addResolvable(Ee.fromData("$transition$", e), ""), e.addResolvable(Ee.fromData("$stateParams", e.params()), ""), e.entering().forEach(function (t) {
            e.addResolvable(Ee.fromData("$state$", t), t)
        })
    }
    var Rr = J(["$transition$", Xe]),
        Er = function (t) {
            var e = ht(t.treeChanges()).reduce(vt, []).reduce(yt, []),
                r = function (t) {
                    return Rr(t.token) ? Ee.fromData(t.token, null) : t
                };
            e.forEach(function (t) {
                t.resolvables = t.resolvables.map(r)
            })
        },
        Cr = function (e) {
            var t = e.to().redirectTo;
            if (t) {
                var r = e.router.stateService;
                return C(t) ? A.$q.when(t(e)).then(n) : n(t)
            }

            function n(t) {
                if (t) return t instanceof $e ? t : P(t) ? r.target(t, e.params(), e.options()) : t.state || t.params ? r.target(t.state || e.to(), t.params || e.params(), e.options()) : void 0
            }
        };

    function Tr(r) {
        return function (t, e) {
            return (0, e.$$state()[r])(t, e)
        }
    }
    var Pr = Tr("onExit"),
        kr = Tr("onRetain"),
        Or = Tr("onEnter"),
        xr = function (t) {
            return new Oe(t.treeChanges().to).resolvePath("EAGER", t).then(G)
        },
        Vr = function (t, e) {
            return new Oe(t.treeChanges().to).subContext(e.$$state()).resolvePath("LAZY", t).then(G)
        },
        jr = function (t) {
            return new Oe(t.treeChanges().to).resolvePath("LAZY", t).then(G)
        },
        Ir = function (t) {
            var e = A.$q,
                r = t.views("entering");
            if (r.length) return e.all(r.map(function (t) {
                return e.when(t.load())
            })).then(G)
        },
        Hr = function (t) {
            var e = t.views("entering"),
                r = t.views("exiting");
            if (e.length || r.length) {
                var n = t.router.viewService;
                r.forEach(function (t) {
                    return n.deactivateViewConfig(t)
                }), e.forEach(function (t) {
                    return n.activateViewConfig(t)
                }), n.sync()
            }
        },
        Ar = function (t) {
            var e = t.router.globals,
                r = function () {
                    e.transition === t && (e.transition = null)
                };
            t.onSuccess({}, function () {
                e.successfulTransitions.enqueue(t), e.$current = t.$to(), e.current = e.$current.self, Tt(t.params(), e.params)
            }, {
                priority: 1e4
            }), t.promise.then(r, r)
        },
        qr = function (t) {
            var e = t.options(),
                r = t.router.stateService,
                n = t.router.urlRouter;
            if ("url" !== e.source && e.location && r.$current.navigable) {
                var i = {
                    replace: "replace" === e.location
                };
                n.push(r.$current.navigable.url, r.params, i)
            }
            n.update(!0)
        },
        Dr = function (a) {
            var u = a.router;
            var t = a.entering().filter(function (t) {
                return !!t.$$state().lazyLoad
            }).map(function (t) {
                return Fr(a, t)
            });
            return A.$q.all(t).then(function () {
                if ("url" !== a.originalTransition().options().source) {
                    var t = a.targetState();
                    return u.stateService.target(t.identifier(), t.params(), t.options())
                }
                var e = u.urlService,
                    r = e.match(e.parts()),
                    n = r && r.rule;
                if (n && "STATE" === n.type) {
                    var i = n.state,
                        o = r.match;
                    return u.stateService.target(i, o, a.options())
                }
                u.urlService.sync()
            })
        };

    function Fr(e, r) {
        var n = r.$$state().lazyLoad,
            t = n._promise;
        if (!t) {
            t = n._promise = A.$q.when(n(e, r)).then(function (t) {
                t && Array.isArray(t.states) && t.states.forEach(function (t) {
                    return e.router.stateRegistry.register(t)
                });
                return t
            }).then(function (t) {
                return delete r.lazyLoad, delete r.$$state().lazyLoad, delete n._promise, t
            }, function (t) {
                return delete n._promise, A.$q.reject(t)
            })
        }
        return t
    }
    var Nr = function (t, e, r, n, i, o, a, u) {
        void 0 === i && (i = !1), void 0 === o && (o = ze.HANDLE_RESULT), void 0 === a && (a = ze.REJECT_ERROR), void 0 === u && (u = !1), this.name = t, this.hookPhase = e, this.hookOrder = r, this.criteriaMatchPath = n, this.reverseSort = i, this.getResultHandler = o, this.getErrorHandler = a, this.synchronous = u
    };

    function Ur(t) {
        var e = t._ignoredReason();
        if (e) {
            ce.traceTransitionIgnored(t);
            var r = t.router.globals.transition;
            return "SameAsCurrent" === e && r && r.abort(), At.ignored().toPromise()
        }
    }

    function Lr(t) {
        if (!t.valid()) throw new Error(t.error().toString())
    }
    var Mr = {
            location: !0,
            relative: null,
            inherit: !1,
            notify: !0,
            reload: !1,
            custom: {},
            current: function () {
                return null
            },
            source: "unknown"
        },
        Br = function () {
            function t(t) {
                this._transitionCount = 0, this._eventTypes = [], this._registeredHooks = {}, this._criteriaPaths = {}, this._router = t, this.$view = t.viewService, this._deregisterHookFns = {}, this._pluginapi = W(v(this), {}, v(this), ["_definePathType", "_defineEvent", "_getPathTypes", "_getEvents", "getHooks"]), this._defineCorePaths(), this._defineCoreEvents(), this._registerCoreTransitionHooks(), t.globals.successfulTransitions.onEvict(Er)
            }
            return t.prototype.onCreate = function (t, e, r) {}, t.prototype.onBefore = function (t, e, r) {}, t.prototype.onStart = function (t, e, r) {}, t.prototype.onExit = function (t, e, r) {}, t.prototype.onRetain = function (t, e, r) {}, t.prototype.onEnter = function (t, e, r) {}, t.prototype.onFinish = function (t, e, r) {}, t.prototype.onSuccess = function (t, e, r) {}, t.prototype.onError = function (t, e, r) {}, t.prototype.dispose = function (t) {
                ht(this._registeredHooks).forEach(function (e) {
                    return e.forEach(function (t) {
                        t._deregistered = !0, K(e, t)
                    })
                })
            }, t.prototype.create = function (t, e) {
                return new Xe(t, e, this._router)
            }, t.prototype._defineCoreEvents = function () {
                var t = d.TransitionHookPhase,
                    e = ze,
                    r = this._criteriaPaths;
                this._defineEvent("onCreate", t.CREATE, 0, r.to, !1, e.LOG_REJECTED_RESULT, e.THROW_ERROR, !0), this._defineEvent("onBefore", t.BEFORE, 0, r.to), this._defineEvent("onStart", t.RUN, 0, r.to), this._defineEvent("onExit", t.RUN, 100, r.exiting, !0), this._defineEvent("onRetain", t.RUN, 200, r.retained), this._defineEvent("onEnter", t.RUN, 300, r.entering), this._defineEvent("onFinish", t.RUN, 400, r.to), this._defineEvent("onSuccess", t.SUCCESS, 0, r.to, !1, e.LOG_REJECTED_RESULT, e.LOG_ERROR, !0), this._defineEvent("onError", t.ERROR, 0, r.to, !1, e.LOG_REJECTED_RESULT, e.LOG_ERROR, !0)
            }, t.prototype._defineCorePaths = function () {
                var t = d.TransitionHookScope.STATE,
                    e = d.TransitionHookScope.TRANSITION;
                this._definePathType("to", e), this._definePathType("from", e), this._definePathType("exiting", t), this._definePathType("retained", t), this._definePathType("entering", t)
            }, t.prototype._defineEvent = function (t, e, r, n, i, o, a, u) {
                void 0 === i && (i = !1), void 0 === o && (o = ze.HANDLE_RESULT), void 0 === a && (a = ze.REJECT_ERROR), void 0 === u && (u = !1);
                var s = new Nr(t, e, r, n, i, o, a, u);
                this._eventTypes.push(s), Ke(this, this, s)
            }, t.prototype._getEvents = function (e) {
                return (E(e) ? this._eventTypes.filter(function (t) {
                    return t.hookPhase === e
                }) : this._eventTypes.slice()).sort(function (t, e) {
                    var r = t.hookPhase - e.hookPhase;
                    return 0 === r ? t.hookOrder - e.hookOrder : r
                })
            }, t.prototype._definePathType = function (t, e) {
                this._criteriaPaths[t] = {
                    name: t,
                    scope: e
                }
            }, t.prototype._getPathTypes = function () {
                return this._criteriaPaths
            }, t.prototype.getHooks = function (t) {
                return this._registeredHooks[t]
            }, t.prototype._registerCoreTransitionHooks = function () {
                var t = this._deregisterHookFns;
                t.addCoreResolves = this.onCreate({}, br), t.ignored = this.onBefore({}, Ur, {
                    priority: -9999
                }), t.invalid = this.onBefore({}, Lr, {
                    priority: -1e4
                }), t.redirectTo = this.onStart({
                    to: function (t) {
                        return !!t.redirectTo
                    }
                }, Cr), t.onExit = this.onExit({
                    exiting: function (t) {
                        return !!t.onExit
                    }
                }, Pr), t.onRetain = this.onRetain({
                    retained: function (t) {
                        return !!t.onRetain
                    }
                }, kr), t.onEnter = this.onEnter({
                    entering: function (t) {
                        return !!t.onEnter
                    }
                }, Or), t.eagerResolve = this.onStart({}, xr, {
                    priority: 1e3
                }), t.lazyResolve = this.onEnter({
                    entering: v(!0)
                }, Vr, {
                    priority: 1e3
                }), t.resolveAll = this.onFinish({}, jr, {
                    priority: 1e3
                }), t.loadViews = this.onFinish({}, Ir), t.activateViews = this.onSuccess({}, Hr), t.updateGlobals = this.onCreate({}, Ar), t.updateUrl = this.onSuccess({}, qr, {
                    priority: 9999
                }), t.lazyLoad = this.onBefore({
                    entering: function (t) {
                        return !!t.lazyLoad
                    }
                }, Dr)
            }, t
        }(),
        Gr = function () {
            function r(t) {
                this.router = t, this.invalidCallbacks = [], this._defaultErrorHandler = function (t) {
                    t instanceof Error && t.stack ? (console.error(t), console.error(t.stack)) : t instanceof At ? (console.error(t.toString()), t.detail && t.detail.stack && console.error(t.detail.stack)) : console.error(t)
                };
                var e = Object.keys(r.prototype).filter(h(J(["current", "$current", "params", "transition"])));
                W(v(r.prototype), this, v(this), e)
            }
            return Object.defineProperty(r.prototype, "transition", {
                get: function () {
                    return this.router.globals.transition
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(r.prototype, "params", {
                get: function () {
                    return this.router.globals.params
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(r.prototype, "current", {
                get: function () {
                    return this.router.globals.current
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(r.prototype, "$current", {
                get: function () {
                    return this.router.globals.$current
                },
                enumerable: !0,
                configurable: !0
            }), r.prototype.dispose = function () {
                this.defaultErrorHandler(G), this.invalidCallbacks = []
            }, r.prototype._handleInvalidTargetState = function (t, r) {
                var n = this,
                    i = Se.makeTargetState(this.router.stateRegistry, t),
                    e = this.router.globals,
                    o = function () {
                        return e.transitionHistory.peekTail()
                    },
                    a = o(),
                    u = new It(this.invalidCallbacks.slice()),
                    s = new Oe(t).injector(),
                    c = function (t) {
                        if (t instanceof $e) {
                            var e = t;
                            return (e = n.target(e.identifier(), e.params(), e.options())).valid() ? o() !== a ? At.superseded().toPromise() : n.transitionTo(e.identifier(), e.params(), e.options()) : At.invalid(e.error()).toPromise()
                        }
                    };
                return function e() {
                    var t = u.dequeue();
                    return void 0 === t ? At.invalid(r.error()).toPromise() : A.$q.when(t(r, i, s)).then(c).then(function (t) {
                        return t || e()
                    })
                }()
            }, r.prototype.onInvalid = function (t) {
                return this.invalidCallbacks.push(t),
                    function () {
                        K(this.invalidCallbacks)(t)
                    }.bind(this)
            }, r.prototype.reload = function (t) {
                return this.transitionTo(this.current, this.params, {
                    reload: !E(t) || t,
                    inherit: !1,
                    notify: !1
                })
            }, r.prototype.go = function (t, e, r) {
                var n = et(r, {
                    relative: this.$current,
                    inherit: !0
                }, Mr);
                return this.transitionTo(t, e, n)
            }, r.prototype.target = function (t, e, r) {
                if (void 0 === r && (r = {}), k(r.reload) && !r.reload.name) throw new Error("Invalid reload state object");
                var n = this.router.stateRegistry;
                if (r.reloadState = !0 === r.reload ? n.root() : n.matcher.find(r.reload, r.relative), r.reload && !r.reloadState) throw new Error("No such reload state '" + (P(r.reload) ? r.reload : r.reload.name) + "'");
                return new $e(this.router.stateRegistry, t, e, r)
            }, r.prototype.getCurrentPath = function () {
                var t = this,
                    e = this.router.globals.successfulTransitions.peekTail();
                return e ? e.treeChanges().to : [new _e(t.router.stateRegistry.root())]
            }, r.prototype.transitionTo = function (t, e, r) {
                var o = this;
                void 0 === e && (e = {}), void 0 === r && (r = {});
                var a = this.router,
                    u = a.globals;
                r = et(r, Mr);
                r = L(r, {
                    current: function () {
                        return u.transition
                    }
                });
                var n = this.target(t, e, r),
                    i = this.getCurrentPath();
                if (!n.exists()) return this._handleInvalidTargetState(i, n);
                if (!n.valid()) return Vt(n.error());
                var s = function (i) {
                        return function (t) {
                            if (t instanceof At) {
                                var e = a.globals.lastStartedTransitionId === i.$id;
                                if (t.type === d.RejectType.IGNORED) return e && a.urlRouter.update(), A.$q.when(u.current);
                                var r = t.detail;
                                if (t.type === d.RejectType.SUPERSEDED && t.redirected && r instanceof $e) {
                                    var n = i.redirect(r);
                                    return n.run().catch(s(n))
                                }
                                if (t.type === d.RejectType.ABORTED) return e && a.urlRouter.update(), A.$q.reject(t)
                            }
                            return o.defaultErrorHandler()(t), A.$q.reject(t)
                        }
                    },
                    c = this.router.transitionService.create(i, n),
                    f = c.run().catch(s(c));
                return xt(f), L(f, {
                    transition: c
                })
            }, r.prototype.is = function (t, e, r) {
                r = et(r, {
                    relative: this.$current
                });
                var n = this.router.stateRegistry.matcher.find(t, r.relative);
                if (E(n)) {
                    if (this.$current !== n) return !1;
                    if (!e) return !0;
                    var i = n.parameters({
                        inherit: !0,
                        matchingKeys: e
                    });
                    return ye.equals(i, ye.values(i, e), this.params)
                }
            }, r.prototype.includes = function (t, e, r) {
                r = et(r, {
                    relative: this.$current
                });
                var n = P(t) && jt.fromString(t);
                if (n) {
                    if (!n.matches(this.$current.name)) return !1;
                    t = this.$current.name
                }
                var i = this.router.stateRegistry.matcher.find(t, r.relative),
                    o = this.$current.includes;
                if (E(i)) {
                    if (!E(o[i.name])) return !1;
                    if (!e) return !0;
                    var a = i.parameters({
                        inherit: !0,
                        matchingKeys: e
                    });
                    return ye.equals(a, ye.values(a, e), this.params)
                }
            }, r.prototype.href = function (t, e, r) {
                r = et(r, {
                    lossy: !0,
                    inherit: !0,
                    absolute: !1,
                    relative: this.$current
                }), e = e || {};
                var n = this.router.stateRegistry.matcher.find(t, r.relative);
                if (!E(n)) return null;
                r.inherit && (e = this.params.$inherit(e, this.$current, n));
                var i = n && r.lossy ? n.navigable : n;
                return i && void 0 !== i.url && null !== i.url ? this.router.urlRouter.href(i.url, e, {
                    absolute: r.absolute
                }) : null
            }, r.prototype.defaultErrorHandler = function (t) {
                return this._defaultErrorHandler = t || this._defaultErrorHandler
            }, r.prototype.get = function (t, e) {
                var r = this.router.stateRegistry;
                return 0 === arguments.length ? r.get() : r.get(t, e || this.$current)
            }, r.prototype.lazyLoad = function (t, e) {
                var r = this.get(t);
                if (!r || !r.lazyLoad) throw new Error("Can not lazy load " + t);
                var n = this.getCurrentPath(),
                    i = Se.makeTargetState(this.router.stateRegistry, n);
                return Fr(e = e || this.router.transitionService.create(n, i), r)
            }, r
        }(),
        Wr = {
            when: function (r) {
                return new Promise(function (t, e) {
                    return t(r)
                })
            },
            reject: function (r) {
                return new Promise(function (t, e) {
                    e(r)
                })
            },
            defer: function () {
                var r = {};
                return r.promise = new Promise(function (t, e) {
                    r.resolve = t, r.reject = e
                }), r
            },
            all: function (t) {
                if (O(t)) return Promise.all(t);
                if (k(t)) {
                    var e = Object.keys(t).map(function (e) {
                        return t[e].then(function (t) {
                            return {
                                key: e,
                                val: t
                            }
                        })
                    });
                    return Wr.all(e).then(function (t) {
                        return t.reduce(function (t, e) {
                            return t[e.key] = e.val, t
                        }, {})
                    })
                }
            }
        },
        zr = {},
        Jr = /((\/\/.*$)|(\/\*[\s\S]*?\*\/))/gm,
        Qr = /([^\s,]+)/g,
        Kr = {
            get: function (t) {
                return zr[t]
            },
            has: function (t) {
                return null != Kr.get(t)
            },
            invoke: function (t, e, r) {
                var n = L({}, zr, r || {}),
                    i = Kr.annotate(t),
                    o = _t(function (t) {
                        return n.hasOwnProperty(t)
                    }, function (t) {
                        return "DI can't find injectable: '" + t + "'"
                    }),
                    a = i.filter(o).map(function (t) {
                        return n[t]
                    });
                return C(t) ? t.apply(e, a) : t.slice(-1)[0].apply(e, a)
            },
            annotate: function (t) {
                if (!j(t)) throw new Error("Not an injectable function: " + t);
                if (t && t.$inject) return t.$inject;
                if (O(t)) return t.slice(0, -1);
                var e = t.toString().replace(Jr, "");
                return e.slice(e.indexOf("(") + 1, e.indexOf(")")).match(Qr) || []
            }
        },
        Yr = function (t, e) {
            var r = e[0],
                n = e[1];
            return t.hasOwnProperty(r) ? O(t[r]) ? t[r].push(n) : t[r] = [t[r], n] : t[r] = n, t
        },
        Zr = function (t) {
            return t.split("&").filter(B).map(Kt).reduce(Yr, {})
        };

    function Xr(t) {
        var e = function (t) {
                return t || ""
            },
            r = Jt(t).map(e),
            n = r[0],
            i = r[1],
            o = Qt(n).map(e);
        return {
            path: o[0],
            search: o[1],
            hash: i,
            url: t
        }
    }
    var tn = function (t) {
        var e = t.path(),
            r = t.search(),
            n = t.hash(),
            i = Object.keys(r).map(function (e) {
                var t = r[e];
                return (O(t) ? t : [t]).map(function (t) {
                    return e + "=" + t
                })
            }).reduce(vt, []).join("&");
        return e + (i ? "?" + i : "") + (n ? "#" + n : "")
    };

    function en(n, i, o, a) {
        return function (t) {
            var e = t.locationService = new o(t),
                r = t.locationConfig = new a(t, i);
            return {
                name: n,
                service: e,
                configuration: r,
                dispose: function (t) {
                    t.dispose(e), t.dispose(r)
                }
            }
        }
    }
    var rn, nn, on, an = function () {
            function t(t, e) {
                var r = this;
                this.fireAfterUpdate = e, this._listeners = [], this._listener = function (e) {
                    return r._listeners.forEach(function (t) {
                        return t(e)
                    })
                }, this.hash = function () {
                    return Xr(r._get()).hash
                }, this.path = function () {
                    return Xr(r._get()).path
                }, this.search = function () {
                    return Zr(Xr(r._get()).search)
                }, this._location = q.location, this._history = q.history
            }
            return t.prototype.url = function (e, t) {
                return void 0 === t && (t = !0), E(e) && e !== this._get() && (this._set(null, null, e, t), this.fireAfterUpdate && this._listeners.forEach(function (t) {
                    return t({
                        url: e
                    })
                })), tn(this)
            }, t.prototype.onChange = function (t) {
                var e = this;
                return this._listeners.push(t),
                    function () {
                        return K(e._listeners, t)
                    }
            }, t.prototype.dispose = function (t) {
                tt(this._listeners)
            }, t
        }(),
        un = (rn = Object.setPrototypeOf || {
                __proto__: []
            }
            instanceof Array && function (t, e) {
                t.__proto__ = e
            } || function (t, e) {
                for (var r in e) e.hasOwnProperty(r) && (t[r] = e[r])
            },
            function (t, e) {
                function r() {
                    this.constructor = t
                }
                rn(t, e), t.prototype = null === e ? Object.create(e) : (r.prototype = e.prototype, new r)
            }),
        sn = function (r) {
            function t(t) {
                var e = r.call(this, t, !1) || this;
                return q.addEventListener("hashchange", e._listener, !1), e
            }
            return un(t, r), t.prototype._get = function () {
                return Yt(this._location.hash)
            }, t.prototype._set = function (t, e, r, n) {
                this._location.hash = r
            }, t.prototype.dispose = function (t) {
                r.prototype.dispose.call(this, t), q.removeEventListener("hashchange", this._listener)
            }, t
        }(an),
        cn = (nn = Object.setPrototypeOf || {
                __proto__: []
            }
            instanceof Array && function (t, e) {
                t.__proto__ = e
            } || function (t, e) {
                for (var r in e) e.hasOwnProperty(r) && (t[r] = e[r])
            },
            function (t, e) {
                function r() {
                    this.constructor = t
                }
                nn(t, e), t.prototype = null === e ? Object.create(e) : (r.prototype = e.prototype, new r)
            }),
        fn = function (e) {
            function t(t) {
                return e.call(this, t, !0) || this
            }
            return cn(t, e), t.prototype._get = function () {
                return this._url
            }, t.prototype._set = function (t, e, r, n) {
                this._url = r
            }, t
        }(an),
        hn = (on = Object.setPrototypeOf || {
                __proto__: []
            }
            instanceof Array && function (t, e) {
                t.__proto__ = e
            } || function (t, e) {
                for (var r in e) e.hasOwnProperty(r) && (t[r] = e[r])
            },
            function (t, e) {
                function r() {
                    this.constructor = t
                }
                on(t, e), t.prototype = null === e ? Object.create(e) : (r.prototype = e.prototype, new r)
            }),
        ln = function (r) {
            function t(t) {
                var e = r.call(this, t, !0) || this;
                return e._config = t.urlService.config, q.addEventListener("popstate", e._listener, !1), e
            }
            return hn(t, r), t.prototype._getBasePrefix = function () {
                return zt(this._config.baseHref())
            }, t.prototype._get = function () {
                var t = this._location,
                    e = t.pathname,
                    r = t.hash,
                    n = t.search;
                n = Qt(n)[1], r = Jt(r)[1];
                var i = this._getBasePrefix(),
                    o = e === this._config.baseHref(),
                    a = e.substr(0, i.length) === i;
                return (e = o ? "/" : a ? e.substring(i.length) : e) + (n ? "?" + n : "") + (r ? "#" + r : "")
            }, t.prototype._set = function (t, e, r, n) {
                var i = this._getBasePrefix(),
                    o = r && "/" !== r[0] ? "/" : "",
                    a = "" === r || "/" === r ? this._config.baseHref() : i + o + r;
                n ? this._history.replaceState(t, e, a) : this._history.pushState(t, e, a)
            }, t.prototype.dispose = function (t) {
                r.prototype.dispose.call(this, t), q.removeEventListener("popstate", this._listener)
            }, t
        }(an),
        pn = function () {
            var e = this;
            this.dispose = G, this._baseHref = "", this._port = 80, this._protocol = "http", this._host = "localhost", this._hashPrefix = "", this.port = function () {
                return e._port
            }, this.protocol = function () {
                return e._protocol
            }, this.host = function () {
                return e._host
            }, this.baseHref = function () {
                return e._baseHref
            }, this.html5Mode = function () {
                return !1
            }, this.hashPrefix = function (t) {
                return E(t) ? e._hashPrefix = t : e._hashPrefix
            }
        },
        vn = function () {
            function t(t, e) {
                void 0 === e && (e = !1), this._isHtml5 = e, this._baseHref = void 0, this._hashPrefix = ""
            }
            return t.prototype.port = function () {
                return location.port ? Number(location.port) : "https" === this.protocol() ? 443 : 80
            }, t.prototype.protocol = function () {
                return location.protocol.replace(/:/g, "")
            }, t.prototype.host = function () {
                return location.hostname
            }, t.prototype.html5Mode = function () {
                return this._isHtml5
            }, t.prototype.hashPrefix = function (t) {
                return E(t) ? this._hashPrefix = t : this._hashPrefix
            }, t.prototype.baseHref = function (t) {
                return E(t) && (this._baseHref = t), _(this._baseHref) && (this._baseHref = this.getBaseHref()), this._baseHref
            }, t.prototype.getBaseHref = function () {
                var t = document.getElementsByTagName("base")[0];
                return t && t.href ? t.href.replace(/^(https?:)?\/\/[^/]*/, "") : location.pathname || "/"
            }, t.prototype.dispose = function () {}, t
        }();

    function dn(t) {
        return A.$injector = Kr, {
            name: "vanilla.services",
            $q: A.$q = Wr,
            $injector: Kr,
            dispose: function () {
                return null
            }
        }
    }
    var mn = en("vanilla.hashBangLocation", !1, sn, vn),
        yn = en("vanilla.pushStateLocation", !0, ln, vn),
        gn = en("vanilla.memoryLocation", !1, fn, pn),
        wn = function () {
            function t() {}
            return t.prototype.dispose = function (t) {}, t
        }(),
        _n = Object.freeze({
            root: q,
            fromJson: F,
            toJson: N,
            forEach: U,
            extend: L,
            equals: M,
            identity: B,
            noop: G,
            createProxyFunctions: W,
            inherit: z,
            inArray: J,
            _inArray: Q,
            removeFrom: K,
            _removeFrom: Y,
            pushTo: Z,
            _pushTo: X,
            deregAll: tt,
            defaults: et,
            mergeR: rt,
            ancestors: nt,
            pick: it,
            omit: ot,
            pluck: at,
            filter: ut,
            find: st,
            mapObj: ct,
            map: ft,
            values: ht,
            allTrueR: lt,
            anyTrueR: pt,
            unnestR: vt,
            flattenR: dt,
            pushR: mt,
            uniqR: yt,
            unnest: gt,
            flatten: wt,
            assertPredicate: _t,
            assertMap: $t,
            assertFn: St,
            pairs: bt,
            arrayTuples: Rt,
            applyPairs: Et,
            tail: Ct,
            copy: Tt,
            _extend: Pt,
            silenceUncaughtInPromise: xt,
            silentRejection: Vt,
            notImplemented: H,
            services: A,
            Glob: jt,
            curry: c,
            compose: r,
            pipe: s,
            prop: w,
            propEq: y,
            parse: R,
            not: h,
            and: n,
            or: i,
            all: f,
            any: l,
            is: p,
            eq: o,
            val: v,
            invoke: a,
            pattern: m,
            isUndefined: _,
            isDefined: E,
            isNull: $,
            isNullOrUndefined: S,
            isFunction: C,
            isNumber: T,
            isString: P,
            isObject: k,
            isArray: O,
            isDate: x,
            isRegExp: V,
            isInjectable: j,
            isPromise: I,
            Queue: It,
            maxLength: qt,
            padString: Dt,
            kebobString: Ft,
            functionToString: Nt,
            fnToString: Ut,
            stringify: Bt,
            beforeAfterSubstr: Gt,
            hostRegex: Wt,
            stripLastPathElement: zt,
            splitHash: Jt,
            splitQuery: Qt,
            splitEqual: Kt,
            trimHashVal: Yt,
            splitOnDelim: Zt,
            joinNeighborsR: Xt,
            get Category() {
                return d.Category
            },
            Trace: se,
            trace: ce,
            get DefType() {
                return d.DefType
            },
            Param: ye,
            ParamTypes: ge,
            StateParams: we,
            ParamType: fe,
            PathNode: _e,
            PathUtils: Se,
            resolvePolicies: be,
            defaultResolvePolicy: Re,
            Resolvable: Ee,
            NATIVE_INJECTOR_TOKEN: ke,
            ResolveContext: Oe,
            resolvablesBuilder: De,
            StateBuilder: Ue,
            StateObject: Le,
            StateMatcher: Me,
            StateQueueManager: Be,
            StateRegistry: Ge,
            StateService: Gr,
            TargetState: $e,
            get TransitionHookPhase() {
                return d.TransitionHookPhase
            },
            get TransitionHookScope() {
                return d.TransitionHookScope
            },
            HookBuilder: Ye,
            matchState: Je,
            RegisteredHook: Qe,
            makeEvent: Ke,
            get RejectType() {
                return d.RejectType
            },
            Rejection: At,
            Transition: Xe,
            TransitionHook: ze,
            TransitionEventType: Nr,
            defaultTransOpts: Mr,
            TransitionService: Br,
            UrlMatcher: nr,
            ParamFactory: or,
            UrlMatcherFactory: ar,
            UrlRouter: fr,
            UrlRuleFactory: ur,
            BaseUrlRule: sr,
            UrlService: _r,
            ViewService: lr,
            UIRouterGlobals: pr,
            UIRouter: Sr,
            $q: Wr,
            $injector: Kr,
            BaseLocationServices: an,
            HashLocationService: sn,
            MemoryLocationService: fn,
            PushStateLocationService: ln,
            MemoryLocationConfig: pn,
            BrowserLocationConfig: vn,
            keyValsToObjectR: Yr,
            getParams: Zr,
            parseUrl: Xr,
            buildUrl: tn,
            locationPluginFactory: en,
            servicesPlugin: dn,
            hashLocationPlugin: mn,
            pushStateLocationPlugin: yn,
            memoryLocationPlugin: gn,
            UIRouterPluginBase: wn
        });

    function $n() {
        var r = null;
        return function (t, e) {
            return r = r || A.$injector.get("$templateFactory"), [new En(t, e, r)]
        }
    }
    var Sn = function (t, r) {
        return t.reduce(function (t, e) {
            return t || E(r[e])
        }, !1)
    };

    function bn(n) {
        if (!n.parent) return {};
        var i = ["component", "bindings", "componentProvider"],
            o = ["templateProvider", "templateUrl", "template", "notify", "async"].concat(["controller", "controllerProvider", "controllerAs", "resolveAs"]),
            t = i.concat(o);
        if (E(n.views) && Sn(t, n)) throw new Error("State '" + n.name + "' has a 'views' object. It cannot also have \"view properties\" at the state level.  Move the following properties into a view (in the 'views' object):  " + t.filter(function (t) {
            return E(n[t])
        }).join(", "));
        var a = {},
            e = n.views || {
                $default: it(n, t)
            };
        return U(e, function (t, e) {
            if (e = e || "$default", P(t) && (t = {
                    component: t
                }), t = L({}, t), Sn(i, t) && Sn(o, t)) throw new Error("Cannot combine: " + i.join("|") + " with: " + o.join("|") + " in stateview: '" + e + "@" + n.name + "'");
            t.resolveAs = t.resolveAs || "$resolve", t.$type = "ng1", t.$context = n, t.$name = e;
            var r = lr.normalizeUIViewTarget(t.$context, t.$name);
            t.$uiViewName = r.uiViewName, t.$uiViewContextAnchor = r.uiViewContextAnchor, a[e] = t
        }), a
    }
    var Rn = 0,
        En = function () {
            function t(t, e, r) {
                var n = this;
                this.path = t, this.viewDecl = e, this.factory = r, this.$id = Rn++, this.loaded = !1, this.getTemplate = function (t, e) {
                    return n.component ? n.factory.makeComponentTemplate(t, e, n.component, n.viewDecl.bindings) : n.template
                }
            }
            return t.prototype.load = function () {
                var e = this,
                    t = A.$q,
                    r = new Oe(this.path),
                    n = this.path.reduce(function (t, e) {
                        return L(t, e.paramValues)
                    }, {}),
                    i = {
                        template: t.when(this.factory.fromConfig(this.viewDecl, n, r)),
                        controller: t.when(this.getController(r))
                    };
                return t.all(i).then(function (t) {
                    return ce.traceViewServiceEvent("Loaded", e), e.controller = t.controller, L(e, t.template), e
                })
            }, t.prototype.getController = function (t) {
                var e = this.viewDecl.controllerProvider;
                if (!j(e)) return this.viewDecl.controller;
                var r = A.$injector.annotate(e),
                    n = O(e) ? Ct(e) : e;
                return new Ee("", n, r).get(t)
            }, t
        }(),
        Cn = function () {
            function t() {
                var n = this;
                this._useHttp = b.version.minor < 3, this.$get = ["$http", "$templateCache", "$injector", function (t, e, r) {
                    return n.$templateRequest = r.has && r.has("$templateRequest") && r.get("$templateRequest"), n.$http = t, n.$templateCache = e, n
                }]
            }
            return t.prototype.useHttpService = function (t) {
                this._useHttp = t
            }, t.prototype.fromConfig = function (t, e, r) {
                var n = function (t) {
                        return A.$q.when(t).then(function (t) {
                            return {
                                template: t
                            }
                        })
                    },
                    i = function (t) {
                        return A.$q.when(t).then(function (t) {
                            return {
                                component: t
                            }
                        })
                    };
                return E(t.template) ? n(this.fromString(t.template, e)) : E(t.templateUrl) ? n(this.fromUrl(t.templateUrl, e)) : E(t.templateProvider) ? n(this.fromProvider(t.templateProvider, e, r)) : E(t.component) ? i(t.component) : E(t.componentProvider) ? i(this.fromComponentProvider(t.componentProvider, e, r)) : n("<ui-view></ui-view>")
            }, t.prototype.fromString = function (t, e) {
                return C(t) ? t(e) : t
            }, t.prototype.fromUrl = function (t, e) {
                return C(t) && (t = t(e)), null == t ? null : this._useHttp ? this.$http.get(t, {
                    cache: this.$templateCache,
                    headers: {
                        Accept: "text/html"
                    }
                }).then(function (t) {
                    return t.data
                }) : this.$templateRequest(t)
            }, t.prototype.fromProvider = function (t, e, r) {
                var n = A.$injector.annotate(t),
                    i = O(t) ? Ct(t) : t;
                return new Ee("", i, n).get(r)
            }, t.prototype.fromComponentProvider = function (t, e, r) {
                var n = A.$injector.annotate(t),
                    i = O(t) ? Ct(t) : t;
                return new Ee("", i, n).get(r)
            }, t.prototype.makeComponentTemplate = function (s, c, t, f) {
                f = f || {};
                var h = 3 <= b.version.minor ? "::" : "",
                    l = function (t) {
                        var e = Ft(t);
                        return /^(x|data)-/.exec(e) ? "x-" + e : e
                    },
                    e = function (t) {
                        var e = A.$injector.get(t + "Directive");
                        if (!e || !e.length) throw new Error("Unable to find component named '" + t + "'");
                        return e.map(Tn).reduce(vt, [])
                    }(t).map(function (t) {
                        var e = t.name,
                            r = t.type,
                            n = l(e);
                        if (s.attr(n) && !f[e]) return n + "='" + s.attr(n) + "'";
                        var i = f[e] || e;
                        if ("@" === r) return n + "='{{" + h + "$resolve." + i + "}}'";
                        if ("&" === r) {
                            var o = c.getResolvable(i),
                                a = o && o.data,
                                u = a && A.$injector.annotate(a) || [];
                            return n + "='$resolve." + i + (O(a) ? "[" + (a.length - 1) + "]" : "") + "(" + u.join(",") + ")'"
                        }
                        return n + "='" + h + "$resolve." + i + "'"
                    }).join(" "),
                    r = l(t);
                return "<" + r + " " + e + "></" + r + ">"
            }, t
        }();
    var Tn = function (t) {
            return k(t.bindToController) ? Pn(t.bindToController) : Pn(t.scope)
        },
        Pn = function (e) {
            return Object.keys(e || {}).map(function (t) {
                return [t, /^([=<@&])[?]?(.*)/.exec(e[t])]
            }).filter(function (t) {
                return E(t) && O(t[1])
            }).map(function (t) {
                return {
                    name: t[1][2] || t[0],
                    type: t[1][1]
                }
            })
        },
        kn = function () {
            function r(t, e) {
                this.stateRegistry = t, this.stateService = e, W(v(r.prototype), this, v(this))
            }
            return r.prototype.decorator = function (t, e) {
                return this.stateRegistry.decorator(t, e) || this
            }, r.prototype.state = function (t, e) {
                return k(t) ? e = t : e.name = t, this.stateRegistry.register(e), this
            }, r.prototype.onInvalid = function (t) {
                return this.stateService.onInvalid(t)
            }, r
        }(),
        On = function (r) {
            return function (t, e) {
                var i = t[r],
                    o = "onExit" === r ? "from" : "to";
                return i ? function (t, e) {
                    var r = new Oe(t.treeChanges(o)).subContext(e.$$state()),
                        n = L(zn(r), {
                            $state$: e,
                            $transition$: t
                        });
                    return A.$injector.invoke(i, this, n)
                } : void 0
            }
        },
        xn = function () {
            function t(t) {
                this._urlListeners = [], this.$locationProvider = t;
                var e = v(t);
                W(e, this, e, ["hashPrefix"])
            }
            return t.monkeyPatchPathParameterType = function (t) {
                var e = t.urlMatcherFactory.type("path");
                e.encode = function (t) {
                    return null != t ? t.toString().replace(/(~|\/)/g, function (t) {
                        return {
                            "~": "~~",
                            "/": "~2F"
                        }[t]
                    }) : t
                }, e.decode = function (t) {
                    return null != t ? t.toString().replace(/(~~|~2F)/g, function (t) {
                        return {
                            "~~": "~",
                            "~2F": "/"
                        }[t]
                    }) : t
                }
            }, t.prototype.dispose = function () {}, t.prototype.onChange = function (t) {
                var e = this;
                return this._urlListeners.push(t),
                    function () {
                        return K(e._urlListeners)(t)
                    }
            }, t.prototype.html5Mode = function () {
                var t = this.$locationProvider.html5Mode();
                return (t = k(t) ? t.enabled : t) && this.$sniffer.history
            }, t.prototype.baseHref = function () {
                return this._baseHref || (this._baseHref = this.$browser.baseHref() || this.$window.location.pathname)
            }, t.prototype.url = function (t, e, r) {
                return void 0 === e && (e = !1), E(t) && this.$location.url(t), e && this.$location.replace(), r && this.$location.state(r), this.$location.url()
            }, t.prototype._runtimeServices = function (t, e, r, n, i) {
                var o = this;
                this.$location = e, this.$sniffer = r, this.$browser = n, this.$window = i, t.$on("$locationChangeSuccess", function (e) {
                    return o._urlListeners.forEach(function (t) {
                        return t(e)
                    })
                });
                var a = v(e);
                W(a, this, a, ["replace", "path", "search", "hash"]), W(a, this, a, ["port", "protocol", "host"])
            }, t
        }(),
        Vn = function () {
            function r(t) {
                this._router = t, this._urlRouter = t.urlRouter
            }
            return r.injectableHandler = function (e, r) {
                return function (t) {
                    return A.$injector.invoke(r, null, {
                        $match: t,
                        $stateParams: e.globals.params
                    })
                }
            }, r.prototype.$get = function () {
                var t = this._urlRouter;
                return t.update(!0), t.interceptDeferred || t.listen(), t
            }, r.prototype.rule = function (t) {
                var e = this;
                if (!C(t)) throw new Error("'rule' must be a function");
                var r = new sr(function () {
                    return t(A.$injector, e._router.locationService)
                }, B);
                return this._urlRouter.rule(r), this
            }, r.prototype.otherwise = function (t) {
                var e = this,
                    r = this._urlRouter;
                if (P(t)) r.otherwise(t);
                else {
                    if (!C(t)) throw new Error("'rule' must be a string or function");
                    r.otherwise(function () {
                        return t(A.$injector, e._router.locationService)
                    })
                }
                return this
            }, r.prototype.when = function (t, e) {
                return (O(e) || C(e)) && (e = r.injectableHandler(this._router, e)), this._urlRouter.when(t, e), this
            }, r.prototype.deferIntercept = function (t) {
                this._urlRouter.deferIntercept(t)
            }, r
        }();
    b.module("ui.router.angular1", []);
    var jn = b.module("ui.router.init", ["ng"]),
        In = b.module("ui.router.util", ["ui.router.init"]),
        Hn = b.module("ui.router.router", ["ui.router.util"]),
        An = b.module("ui.router.state", ["ui.router.router", "ui.router.util", "ui.router.angular1"]),
        qn = b.module("ui.router", ["ui.router.init", "ui.router.state", "ui.router.angular1"]),
        Dn = (b.module("ui.router.compat", ["ui.router"]), null);

    function Fn(t) {
        (Dn = this.router = new Sr).stateProvider = new kn(Dn.stateRegistry, Dn.stateService), Dn.stateRegistry.decorator("views", bn), Dn.stateRegistry.decorator("onExit", On("onExit")), Dn.stateRegistry.decorator("onRetain", On("onRetain")), Dn.stateRegistry.decorator("onEnter", On("onEnter")), Dn.viewService._pluginapi._viewConfigFactory("ng1", $n());
        var u = Dn.locationService = Dn.locationConfig = new xn(t);

        function e(t, e, r, n, i, o, a) {
            return u._runtimeServices(i, t, n, e, r), delete Dn.router, delete Dn.$get, Dn
        }
        return xn.monkeyPatchPathParameterType(Dn), ((Dn.router = Dn).$get = e).$inject = ["$location", "$browser", "$window", "$sniffer", "$rootScope", "$http", "$templateCache"], Dn
    }
    Fn.$inject = ["$locationProvider"];
    var Nn = function (r) {
        return ["$uiRouterProvider", function (t) {
            var e = t.router[r];
            return e.$get = function () {
                return e
            }, e
        }]
    };

    function Un(e, t, r) {
        if (A.$injector = e, A.$q = t, !e.hasOwnProperty("strictDi")) try {
            e.invoke(function (t) {})
        } catch (t) {
            e.strictDi = !!/strict mode/.exec(t && t.toString())
        }
        r.stateRegistry.get().map(function (t) {
            return t.$$state().resolvables
        }).reduce(vt, []).filter(function (t) {
            return "deferred" === t.deps
        }).forEach(function (t) {
            return t.deps = e.annotate(t.resolveFn, e.strictDi)
        })
    }
    Un.$inject = ["$injector", "$q", "$uiRouter"];

    function Ln(t) {
        t.$watch(function () {
            ce.approximateDigests++
        })
    }
    Ln.$inject = ["$rootScope"], jn.provider("$uiRouter", Fn), Hn.provider("$urlRouter", ["$uiRouterProvider", function (t) {
        return t.urlRouterProvider = new Vn(t)
    }]), In.provider("$urlService", Nn("urlService")), In.provider("$urlMatcherFactory", ["$uiRouterProvider", function () {
        return Dn.urlMatcherFactory
    }]), In.provider("$templateFactory", function () {
        return new Cn
    }), An.provider("$stateRegistry", Nn("stateRegistry")), An.provider("$uiRouterGlobals", Nn("globals")), An.provider("$transitions", Nn("transitionService")), An.provider("$state", ["$uiRouterProvider", function () {
        return L(Dn.stateProvider, {
            $get: function () {
                return Dn.stateService
            }
        })
    }]), An.factory("$stateParams", ["$uiRouter", function (t) {
        return t.globals.params
    }]), qn.factory("$view", function () {
        return Dn.viewService
    }), qn.service("$trace", function () {
        return ce
    }), qn.run(Ln), In.run(["$urlMatcherFactory", function (t) {}]), An.run(["$state", function (t) {}]), Hn.run(["$urlRouter", function (t) {}]), jn.run(Un);
    var Mn, Bn, Gn, Wn, zn = function (r) {
        return r.getTokens().filter(P).map(function (t) {
            var e = r.getResolvable(t);
            return [t, "NOWAIT" === r.getPolicy(e).async ? e.promise : e.data]
        }).reduce(Et, {})
    };

    function Jn(t) {
        var e, r = t.match(/^\s*({[^}]*})\s*$/);
        if (r && (t = "(" + r[1] + ")"), !(e = t.replace(/\n/g, " ").match(/^\s*([^(]*?)\s*(\((.*)\))?\s*$/)) || 4 !== e.length) throw new Error("Invalid state ref '" + t + "'");
        return {
            state: e[1] || null,
            paramExpr: e[3] || null
        }
    }

    function Qn(t) {
        var e = t.parent().inheritedData("$uiView"),
            r = R("$cfg.path")(e);
        return r ? Ct(r).state.name : void 0
    }

    function Kn(t, e, r) {
        var n, i = r.uiState || t.current.name,
            o = L((n = t, {
                relative: Qn(e) || n.$current,
                inherit: !0,
                source: "sref"
            }), r.uiStateOpts || {}),
            a = t.href(i, r.uiStateParams, o);
        return {
            uiState: i,
            uiStateParams: r.uiStateParams,
            uiStateOpts: o,
            href: a
        }
    }

    function Yn(t) {
        var e = "[object SVGAnimatedString]" === Object.prototype.toString.call(t.prop("href")),
            r = "FORM" === t[0].nodeName;
        return {
            attr: r ? "action" : e ? "xlink:href" : "href",
            isAnchor: "A" === t.prop("tagName").toUpperCase(),
            clickable: !r
        }
    }

    function Zn(o, a, u, s, c) {
        return function (t) {
            var e = t.which || t.button,
                r = c();
            if (!(1 < e || t.ctrlKey || t.metaKey || t.shiftKey || o.attr("target"))) {
                var n = u(function () {
                    o.attr("disabled") || a.go(r.uiState, r.uiStateParams, r.uiStateOpts)
                });
                t.preventDefault();
                var i = s.isAnchor && !r.href ? 1 : 0;
                t.preventDefault = function () {
                    i-- <= 0 && u.cancel(n)
                }
            }
        }
    }

    function Xn(i, t, o, e) {
        var a;
        e && (a = e.events), O(a) || (a = ["click"]);
        for (var r = i.on ? "on" : "bind", n = 0, u = a; n < u.length; n++) {
            var s = u[n];
            i[r](s, o)
        }
        t.$on("$destroy", function () {
            for (var t = i.off ? "off" : "unbind", e = 0, r = a; e < r.length; e++) {
                var n = r[e];
                i[t](n, o)
            }
        })
    }

    function ti(n) {
        var t = function (t, e, r) {
            return n.is(t, e, r)
        };
        return t.$stateful = !0, t
    }

    function ei(n) {
        var t = function (t, e, r) {
            return n.includes(t, e, r)
        };
        return t.$stateful = !0, t
    }

    function ri(d, m, y, t, g, e) {
        var w = R("viewDecl.controllerAs"),
            _ = R("viewDecl.resolveAs");
        return {
            restrict: "ECA",
            priority: -400,
            compile: function (t) {
                var v = t.html();
                return t.empty(),
                    function (e, r) {
                        var t = r.data("$uiView");
                        if (!t) return r.html(v), void d(r.contents())(e);
                        var n = t.$cfg || {
                                viewDecl: {},
                                getTemplate: G
                            },
                            i = n.path && new Oe(n.path);
                        r.html(n.getTemplate(r, i) || v), ce.traceUIViewFill(t.$uiView, r.html());
                        var o = d(r.contents()),
                            a = n.controller,
                            u = w(n),
                            s = _(n),
                            c = i && zn(i);
                        if (e[s] = c, a) {
                            var f = m(a, L({}, c, {
                                $scope: e,
                                $element: r
                            }));
                            u && (e[u] = f, e[u][s] = c), r.data("$ngControllerController", f), r.children().data("$ngControllerController", f), oi(g, y, f, e, n)
                        }
                        if (P(n.component)) var h = Ft(n.component),
                            l = new RegExp("^(x-|data-)?" + h + "$", "i"),
                            p = e.$watch(function () {
                                var t = [].slice.call(r[0].children).filter(function (t) {
                                    return t && t.tagName && l.exec(t.tagName)
                                });
                                return t && b.element(t).data("$" + n.component + "Controller")
                            }, function (t) {
                                t && (oi(g, y, t, e, n), p())
                            });
                        o(e)
                    }
            }
        }
    }
    Mn = ["$uiRouter", "$timeout", function (l, p) {
        var v = l.stateService;
        return {
            restrict: "A",
            require: ["?^uiSrefActive", "?^uiSrefActiveEq"],
            link: function (t, e, r, n) {
                var i, o = Yn(e),
                    a = n[1] || n[0],
                    u = null,
                    s = {},
                    c = function () {
                        return Kn(v, e, s)
                    },
                    f = Jn(r.uiSref);

                function h() {
                    var t = c();
                    u && u(), a && (u = a.$$addStateInfo(t.uiState, t.uiStateParams)), null != t.href && r.$set(o.attr, t.href)
                }
                s.uiState = f.state, s.uiStateOpts = r.uiSrefOpts ? t.$eval(r.uiSrefOpts) : {}, f.paramExpr && (t.$watch(f.paramExpr, function (t) {
                    s.uiStateParams = L({}, t), h()
                }, !0), s.uiStateParams = L({}, t.$eval(f.paramExpr))), h(), t.$on("$destroy", l.stateRegistry.onStatesChanged(h)), t.$on("$destroy", l.transitionService.onSuccess({}, h)), o.clickable && (i = Zn(e, v, p, o, c), Xn(e, t, i, s.uiStateOpts))
            }
        }
    }], Bn = ["$uiRouter", "$timeout", function (p, v) {
        var d = p.stateService;
        return {
            restrict: "A",
            require: ["?^uiSrefActive", "?^uiSrefActiveEq"],
            link: function (r, t, n, e) {
                var i, o = Yn(t),
                    a = e[1] || e[0],
                    u = null,
                    s = {},
                    c = function () {
                        return Kn(d, t, s)
                    },
                    f = ["uiState", "uiStateParams", "uiStateOpts"],
                    h = f.reduce(function (t, e) {
                        return t[e] = G, t
                    }, {});

                function l() {
                    var t = c();
                    u && u(), a && (u = a.$$addStateInfo(t.uiState, t.uiStateParams)), null != t.href && n.$set(o.attr, t.href)
                }
                f.forEach(function (e) {
                    s[e] = n[e] ? r.$eval(n[e]) : null, n.$observe(e, function (t) {
                        h[e](), h[e] = r.$watch(t, function (t) {
                            s[e] = t, l()
                        }, !0)
                    })
                }), l(), r.$on("$destroy", p.stateRegistry.onStatesChanged(l)), r.$on("$destroy", p.transitionService.onSuccess({}, l)), o.clickable && (i = Zn(t, d, v, o, c), Xn(t, r, i, s.uiStateOpts))
            }
        }
    }], Gn = ["$state", "$stateParams", "$interpolate", "$uiRouter", function (v, t, d, m) {
        return {
            restrict: "A",
            controller: ["$scope", "$element", "$attrs", function (u, s, t) {
                var c, n, e, r, i, f = [];
                c = d(t.uiSrefActiveEq || "", !1)(u);
                try {
                    n = u.$eval(t.uiSrefActive)
                } catch (t) {}

                function o(t) {
                    t.promise.then(p, G)
                }

                function a() {
                    h(n)
                }

                function h(t) {
                    k(t) && (f = [], U(t, function (t, e) {
                        var r = function (t, e) {
                            var r = Jn(t);
                            l(r.state, u.$eval(r.paramExpr), e)
                        };
                        P(t) ? r(t, e) : O(t) && U(t, function (t) {
                            r(t, e)
                        })
                    }))
                }

                function l(t, e, r) {
                    var n = {
                        state: v.get(t, Qn(s)) || {
                            name: t
                        },
                        params: e,
                        activeClass: r
                    };
                    return f.push(n),
                        function () {
                            K(f)(n)
                        }
                }

                function p() {
                    var e = function (t) {
                            return t.split(/\s/).filter(B)
                        },
                        t = function (t) {
                            return t.map(function (t) {
                                return t.activeClass
                            }).map(e).reduce(vt, [])
                        },
                        r = t(f).concat(e(c)).reduce(yt, []),
                        n = t(f.filter(function (t) {
                            return v.includes(t.state.name, t.params)
                        })),
                        i = !!f.filter(function (t) {
                            return v.is(t.state.name, t.params)
                        }).length ? e(c) : [],
                        o = n.concat(i).reduce(yt, []),
                        a = r.filter(function (t) {
                            return !J(o, t)
                        });
                    u.$evalAsync(function () {
                        o.forEach(function (t) {
                            return s.addClass(t)
                        }), a.forEach(function (t) {
                            return s.removeClass(t)
                        })
                    })
                }
                h(n = n || d(t.uiSrefActive || "", !1)(u)), this.$$addStateInfo = function (t, e) {
                    if (!(k(n) && 0 < f.length)) {
                        var r = l(t, e, n);
                        return p(), r
                    }
                }, u.$on("$destroy", (e = m.stateRegistry.onStatesChanged(a), r = m.transitionService.onStart({}, o), i = u.$on("$stateChangeSuccess", p), function () {
                    e(), r(), i()
                })), m.globals.transition && o(m.globals.transition), p()
            }]
        }
    }], b.module("ui.router.state").directive("uiSref", Mn).directive("uiSrefActive", Gn).directive("uiSrefActiveEq", Gn).directive("uiState", Bn), ti.$inject = ["$state"], ei.$inject = ["$state"], b.module("ui.router.state").filter("isState", ti).filter("includedByState", ei), Wn = ["$view", "$animate", "$uiViewScroll", "$interpolate", "$q", function (i, o, g, w, _) {
        var $ = {
                $cfg: {
                    viewDecl: {
                        $context: i._pluginapi._rootViewContext()
                    }
                },
                $uiView: {}
            },
            S = {
                count: 0,
                restrict: "ECA",
                terminal: !0,
                priority: 400,
                transclude: "element",
                compile: function (t, e, y) {
                    return function (a, u, t) {
                        var s, c, f, h, e, l = t.onload || "",
                            p = t.autoscroll,
                            v = {
                                enter: function (t, e, r) {
                                    2 < b.version.minor ? o.enter(t, null, e).then(r) : o.enter(t, null, e, r)
                                },
                                leave: function (t, e) {
                                    2 < b.version.minor ? o.leave(t).then(e) : o.leave(t, e)
                                }
                            },
                            r = u.inheritedData("$uiView") || $,
                            d = w(t.uiView || t.name || "")(a) || "$default",
                            m = {
                                $type: "ng1",
                                id: S.count++,
                                name: d,
                                fqn: r.$uiView.fqn ? r.$uiView.fqn + "." + d : d,
                                config: null,
                                configUpdated: function (t) {
                                    if (t && !(t instanceof En)) return;
                                    if (e = h, r = t, e === r) return;
                                    var e, r;
                                    ce.traceUIViewConfigUpdated(m, t && t.viewDecl && t.viewDecl.$context), n(h = t)
                                },
                                get creationContext() {
                                    var t = R("$cfg.viewDecl.$context")(r),
                                        e = R("$uiView.creationContext")(r);
                                    return t || e
                                }
                            };

                        function n(t) {
                            var e = a.$new(),
                                r = _.defer(),
                                n = _.defer(),
                                i = {
                                    $cfg: t,
                                    $uiView: m
                                },
                                o = {
                                    $animEnter: r.promise,
                                    $animLeave: n.promise,
                                    $$animLeave: n
                                };
                            e.$emit("$viewContentLoading", d), c = y(e, function (t) {
                                t.data("$uiViewAnim", o), t.data("$uiView", i), v.enter(t, u, function () {
                                        r.resolve(), f && f.$emit("$viewContentAnimationEnded"), (E(p) && !p || a.$eval(p)) && g(t)
                                    }),
                                    function () {
                                        if (s && (ce.traceUIViewEvent("Removing (previous) el", s.data("$uiView")), s.remove(), s = null), f && (ce.traceUIViewEvent("Destroying scope", m), f.$destroy(), f = null), c) {
                                            var t = c.data("$uiViewAnim");
                                            ce.traceUIViewEvent("Animate out", t), v.leave(c, function () {
                                                t.$$animLeave.resolve(), s = null
                                            }), s = c, c = null
                                        }
                                    }()
                            }), (f = e).$emit("$viewContentLoaded", t || h), f.$eval(l)
                        }
                        ce.traceUIViewEvent("Linking", m), u.data("$uiView", {
                            $uiView: m
                        }), n(), e = i.registerUIView(m), a.$on("$destroy", function () {
                            ce.traceUIViewEvent("Destroying/Unregistering", m), e()
                        })
                    }
                }
            };
        return S
    }], ri.$inject = ["$compile", "$controller", "$transitions", "$view", "$q", "$timeout"];
    var ni = "function" == typeof b.module("ui.router").component,
        ii = 0;

    function oi(n, t, c, e, r) {
        !C(c.$onInit) || r.viewDecl.component && ni || c.$onInit();
        var f = Ct(r.path).state.self,
            i = {
                bind: c
            };
        if (C(c.uiOnParamsChanged)) {
            var h = new Oe(r.path).getResolvable("$transition$").data;
            e.$on("$destroy", t.onSuccess({}, function (t) {
                if (t !== h && -1 === t.exiting().indexOf(f)) {
                    var r = t.params("to"),
                        n = t.params("from"),
                        e = function (t) {
                            return t.paramSchema
                        },
                        i = t.treeChanges("to").map(e).reduce(vt, []),
                        o = t.treeChanges("from").map(e).reduce(vt, []),
                        a = i.filter(function (t) {
                            var e = o.indexOf(t);
                            return -1 === e || !o[e].type.equals(r[t.id], n[t.id])
                        });
                    if (a.length) {
                        var u = a.map(function (t) {
                                return t.id
                            }),
                            s = ut(r, function (t, e) {
                                return -1 !== u.indexOf(e)
                            });
                        c.uiOnParamsChanged(s, t)
                    }
                }
            }, i))
        }
        if (C(c.uiCanExit)) {
            var o = ii++,
                a = "_uiCanExitIds",
                u = function (t) {
                    return !!t && (t[a] && !0 === t[a][o] || u(t.redirectedFrom()))
                },
                s = {
                    exiting: f.name
                };
            e.$on("$destroy", t.onBefore(s, function (t) {
                var e, r = t[a] = t[a] || {};
                return u(t) || (e = n.when(c.uiCanExit(t))).then(function (t) {
                    return r[o] = !1 !== t
                }), e
            }, i))
        }
    }
    b.module("ui.router.state").directive("uiView", Wn), b.module("ui.router.state").directive("uiView", ri), b.module("ui.router.state").provider("$uiViewScroll", function () {
        var r = !1;
        this.useAnchorScroll = function () {
            r = !0
        }, this.$get = ["$anchorScroll", "$timeout", function (t, e) {
            return r ? t : function (t) {
                return e(function () {
                    t[0].scrollIntoView()
                }, 0, !1)
            }
        }]
    });
    d.default = "ui.router", d.core = _n, d.watchDigests = Ln, d.getLocals = zn, d.getNg1ViewConfigFactory = $n, d.ng1ViewsBuilder = bn, d.Ng1ViewConfig = En, d.StateProvider = kn, d.UrlRouterProvider = Vn, d.root = q, d.fromJson = F, d.toJson = N, d.forEach = U, d.extend = L, d.equals = M, d.identity = B, d.noop = G, d.createProxyFunctions = W, d.inherit = z, d.inArray = J, d._inArray = Q, d.removeFrom = K, d._removeFrom = Y, d.pushTo = Z, d._pushTo = X, d.deregAll = tt, d.defaults = et, d.mergeR = rt, d.ancestors = nt, d.pick = it, d.omit = ot, d.pluck = at, d.filter = ut, d.find = st, d.mapObj = ct, d.map = ft, d.values = ht, d.allTrueR = lt, d.anyTrueR = pt, d.unnestR = vt, d.flattenR = dt, d.pushR = mt, d.uniqR = yt, d.unnest = gt, d.flatten = wt, d.assertPredicate = _t, d.assertMap = $t, d.assertFn = St, d.pairs = bt, d.arrayTuples = Rt, d.applyPairs = Et, d.tail = Ct, d.copy = Tt, d._extend = Pt, d.silenceUncaughtInPromise = xt, d.silentRejection = Vt, d.notImplemented = H, d.services = A, d.Glob = jt, d.curry = c, d.compose = r, d.pipe = s, d.prop = w, d.propEq = y, d.parse = R, d.not = h, d.and = n, d.or = i, d.all = f, d.any = l, d.is = p, d.eq = o, d.val = v, d.invoke = a, d.pattern = m, d.isUndefined = _, d.isDefined = E, d.isNull = $, d.isNullOrUndefined = S, d.isFunction = C, d.isNumber = T, d.isString = P, d.isObject = k, d.isArray = O, d.isDate = x, d.isRegExp = V, d.isInjectable = j, d.isPromise = I, d.Queue = It, d.maxLength = qt, d.padString = Dt, d.kebobString = Ft, d.functionToString = Nt, d.fnToString = Ut, d.stringify = Bt, d.beforeAfterSubstr = Gt, d.hostRegex = Wt, d.stripLastPathElement = zt, d.splitHash = Jt, d.splitQuery = Qt, d.splitEqual = Kt, d.trimHashVal = Yt, d.splitOnDelim = Zt, d.joinNeighborsR = Xt, d.Trace = se, d.trace = ce, d.Param = ye, d.ParamTypes = ge, d.StateParams = we, d.ParamType = fe, d.PathNode = _e, d.PathUtils = Se, d.resolvePolicies = be, d.defaultResolvePolicy = Re, d.Resolvable = Ee, d.NATIVE_INJECTOR_TOKEN = ke, d.ResolveContext = Oe, d.resolvablesBuilder = De, d.StateBuilder = Ue, d.StateObject = Le, d.StateMatcher = Me, d.StateQueueManager = Be, d.StateRegistry = Ge, d.StateService = Gr, d.TargetState = $e, d.HookBuilder = Ye, d.matchState = Je, d.RegisteredHook = Qe, d.makeEvent = Ke, d.Rejection = At, d.Transition = Xe, d.TransitionHook = ze, d.TransitionEventType = Nr, d.defaultTransOpts = Mr, d.TransitionService = Br, d.UrlMatcher = nr, d.ParamFactory = or, d.UrlMatcherFactory = ar, d.UrlRouter = fr, d.UrlRuleFactory = ur, d.BaseUrlRule = sr, d.UrlService = _r, d.ViewService = lr, d.UIRouterGlobals = pr, d.UIRouter = Sr, d.$q = Wr, d.$injector = Kr, d.BaseLocationServices = an, d.HashLocationService = sn, d.MemoryLocationService = fn, d.PushStateLocationService = ln, d.MemoryLocationConfig = pn, d.BrowserLocationConfig = vn, d.keyValsToObjectR = Yr, d.getParams = Zr, d.parseUrl = Xr, d.buildUrl = tn, d.locationPluginFactory = en, d.servicesPlugin = dn, d.hashLocationPlugin = mn, d.pushStateLocationPlugin = yn, d.memoryLocationPlugin = gn, d.UIRouterPluginBase = wn, Object.defineProperty(d, "__esModule", {
        value: !0
    })
});
//# sourceMappingURL=angular-ui-router.min.js.map