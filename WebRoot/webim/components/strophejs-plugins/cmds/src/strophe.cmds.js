// Generated by CoffeeScript 1.7.1
(function() {
  var CMD, CommandNode, RemoteCommand, create, createExecIQ,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; },
    __indexOf = [].indexOf || function(item) { for (var i = 0, l = this.length; i < l; i++) { if (i in this && this[i] === item) return i; } return -1; },
    __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

  CMD = "http://jabber.org/protocol/commands";

  create = function(node, callback) {
    if (callback == null) {
      callback = Strophe.Disco.noop;
    }
    switch (node) {
      case "getUrls":
        return new CommandNode({
          item: "url",
          node: "getUrls",
          name: "Retrieve Urls",
          callback: callback
        });
      case "setUrls":
        return new CommandNode({
          item: "url",
          node: "setUrls",
          name: "Sets Urls",
          callback: callback
        });
      default:
        throw "Strophe.Commands has no implementation for: " + node;
    }
  };

  createExecIQ = function(opt) {
    var cfg, i, iq, item, _ref;
    iq = $iq({
      to: opt.jid,
      type: "set"
    });
    cfg = {
      xmlns: CMD,
      node: opt.node
    };
    cfg.action = opt.action || "execute";
    if (opt.sid) {
      cfg.sessionid = opt.sid;
    }
    iq.c("command", cfg);
    if ($.isArray(opt.data)) {
      _ref = opt.data;
      for (i in _ref) {
        item = _ref[i];
        iq.c(opt.item[0].item).t(item).up();
      }
    } else if (opt.form) {
      iq.cnode(opt.form.toXML());
    }
    return iq;
  };

  RemoteCommand = (function() {
    function RemoteCommand(conn, jid, node) {
      this.conn = conn;
      this.jid = jid;
      this.node = node;
      this.openDialog = __bind(this.openDialog, this);
      this.executeAction = "execute";
      this.actions = [];
      this.sessionid = null;
      this.data = null;
      this.form = null;
      this.resonseForm = null;
      this.status = null;
      this.error = null;
    }

    RemoteCommand.prototype._parseResult = function(res) {
      var cmd;
      cmd = ($(res)).find("command");
      this.sessionid = cmd.attr("sessionid");
      this.status = cmd.attr("status");
      this._parseActions(cmd);
      this._parseResultForm(cmd);
      return this._parseError(res);
    };

    RemoteCommand.prototype._parseActions = function(cmd) {
      var a, actions;
      actions = cmd.find("actions");
      if (actions.length > 0) {
        this.executeAction = actions.attr("execute");
        return this.actions = (function() {
          var _i, _len, _ref, _results;
          _ref = actions.children();
          _results = [];
          for (_i = 0, _len = _ref.length; _i < _len; _i++) {
            a = _ref[_i];
            _results.push(a.nodeName);
          }
          return _results;
        })();
      }
    };

    RemoteCommand.prototype._parseResultForm = function(cmd) {
      var x;
      x = cmd.find("x");
      if (x.length > 0) {
        return this.form = Strophe.x.Form.fromXML(x);
      } else {
        return this.form = null;
      }
    };

    RemoteCommand.prototype._parseError = function(res) {
      var e, err;
      res = $(res);
      err = res.find("error");
      if (err.length > 0) {
        return this.error = {
          code: err.attr("code"),
          type: err.attr("type"),
          conditions: (function() {
            var _i, _len, _ref, _results;
            _ref = err.children();
            _results = [];
            for (_i = 0, _len = _ref.length; _i < _len; _i++) {
              e = _ref[_i];
              _results.push(e.nodeName);
            }
            return _results;
          })()
        };
      } else {
        return this.error = null;
      }
    };

    RemoteCommand.prototype._parseSubmitFormFromHTML = function(html) {
      var f, form, _i, _len, _ref;
      form = Strophe.x.Form.fromHTML(html);
      form.type = "submit";
      _ref = form.fields;
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        f = _ref[_i];
        f.options = [];
        f.required = false;
      }
      return form;
    };

    RemoteCommand.prototype._createFn = function(action, div, opt) {
      var close;
      close = function() {
        return div.dialog("close");
      };
      switch (action.toLowerCase()) {
        case "next":
          return (function(_this) {
            return function() {
              close();
              opt.responseForm = _this._parseSubmitFormFromHTML(div);
              return _this.next(opt);
            };
          })(this);
        case "prev":
          return (function(_this) {
            return function() {
              close();
              return _this.prev(opt);
            };
          })(this);
        case "complete":
          return (function(_this) {
            return function() {
              close();
              opt.responseForm = _this._parseSubmitFormFromHTML(div);
              return _this.complete(opt);
            };
          })(this);
        case "cancel":
          return (function(_this) {
            return function() {
              close();
              return _this.cancel(opt);
            };
          })(this);
        default:
          return (function(_this) {
            return function() {};
          })(this);
      }
    };

    RemoteCommand.prototype.openDialog = function(opt) {
      var a, actions, div, _i, _len, _ref;
      if (!$.fn.dialog) {
        throw new Error("jQuery dialog is not available");
      }
      if (this.form) {
        actions = {};
        div = $(this.form.toHTML());
        _ref = this.actions;
        for (_i = 0, _len = _ref.length; _i < _len; _i++) {
          a = _ref[_i];
          actions[a] = this._createFn(a, div, opt);
        }
        div.find("h1").remove();
        return div.dialog({
          autoOpen: true,
          modal: true,
          title: this.form.title,
          buttons: actions
        });
      }
    };

    RemoteCommand.prototype.onSuccess = function(res, cmd) {};

    RemoteCommand.prototype.onError = function(res, cmd) {
      if (console && cmd.error) {
        return console.error("could not exectute command.\nError:\n  Type: " + cmd.error.type + ",\n  Code: " + cmd.error.code + ",\"\n  Conditions: " + (cmd.error.conditions.join(',')));
      }
    };

    RemoteCommand.prototype._exec = function(opt) {
      if (opt.gui === true) {
        opt.success = function(res, cmd) {
          return cmd.openDialog(opt);
        };
      }
      return this.conn.cmds.execute(this.jid, this.node, {
        success: (function(_this) {
          return function(res) {
            _this._parseResult(res);
            if (opt.success) {
              return opt.success(res, _this);
            } else {
              return _this.onSuccess(res, _this);
            }
          };
        })(this),
        error: (function(_this) {
          return function(res) {
            _this._parseResult(res);
            if (opt.error) {
              return opt.error(res, _this);
            } else {
              return _this.onError(res, _this);
            }
          };
        })(this),
        sid: this.sessionid,
        action: this.executeAction,
        form: this.responseForm
      });
    };

    RemoteCommand.prototype.execute = function(opt) {
      return this._exec(opt);
    };

    RemoteCommand.prototype.next = function(opt) {
      if (opt.responseForm) {
        this.responseForm = opt.responseForm;
      }
      this.executeAction = "next";
      return this._exec(opt);
    };

    RemoteCommand.prototype.prev = function(opt) {
      this.executeAction = "prev";
      return this._exec(opt);
    };

    RemoteCommand.prototype.complete = function(opt) {
      if (opt.responseForm) {
        this.responseForm = opt.responseForm;
      }
      this.executeAction = "complete";
      return this._exec(opt);
    };

    RemoteCommand.prototype.cancel = function(opt) {
      this.executeAction = "cancel";
      return this._exec(opt);
    };

    RemoteCommand.prototype.isValidAction = function(action) {
      return __indexOf.call(this.actions, action) >= 0;
    };

    RemoteCommand.prototype.toIQ = function() {
      return createExecIQ({
        jid: this.jid,
        node: this.node,
        action: this.action,
        sessionid: this.sessionid,
        data: this.data
      });
    };

    return RemoteCommand;

  })();

  CommandNode = (function(_super) {
    __extends(CommandNode, _super);

    function CommandNode(cfg) {
      var k, v;
      for (k in cfg) {
        v = cfg[k];
        this[k] = v;
      }
    }

    CommandNode.prototype.send = function() {
      return $iq({});
    };

    CommandNode.prototype.onError = function() {
      res.attrs({
        status: "error"
      });
      return this.fn.call(this, res);
    };

    CommandNode.prototype.onSuccess = function(obj) {
      var entry, i, item, res;
      res = this.res;
      item = this.item;
      res.attrs({
        status: "completed"
      });
      if ($.isArray(obj)) {
        for (i in obj) {
          entry = obj[i];
          res.c(item).t(entry).up();
        }
      }
      return conn.send(res);
    };

    CommandNode.prototype.reply = function(iq, callback) {
      var req, res;
      req = this.parseRequest(iq);
      res = this.fromTo(req);
      this.addFirstChild(req, res);
      if (callback) {
        callback(req, res);
      } else {
        res.attrs({
          status: "completed"
        });
      }
      return res;
    };

    return CommandNode;

  })(Strophe.Disco.DiscoNode);

  Strophe.Commands = {
    CommandNode: CommandNode,
    RemoteCommand: RemoteCommand,
    create: create
  };

  Strophe.addConnectionPlugin("cmds", {
    _conn: null,
    init: function(c) {
      var conn;
      conn = c;
      this._conn = conn;
      this._command_handlers = {};
      Strophe.addNamespace("COMMANDS", CMD);
      conn.disco.addNode(Strophe.NS.COMMANDS, {
        items: []
      });
      this._cmds = conn.disco.features[CMD];
      return this._command_handler = this._conn.addHandler((function(_this) {
        return function(iq) {
          var cb, n, node, nodeImpl;
          node = ($("command", iq)).attr("node");
          n = $.grep(_this._cmds.items, function(n) {
            return n.node === node;
          });
          if (n.length === 0) {
            nodeImpl = new DiscoNodeNotFound;
            _this._conn.send(nodeImpl.reply(iq));
          } else {
            cb = _this._command_handlers[node] || null;
            nodeImpl = n[0];
            _this._conn.send(nodeImpl.reply(iq, cb));
          }
          return true;
        };
      })(this), CMD, "iq", "set");
    },
    add: function(item, callback) {
      if (!item.node) {
        throw "command needs a node";
      }
      if (!item.jid) {
        item.jid = this._conn.jid;
      }
      this._cmds.items.push(new CommandNode(item));
      return this._command_handlers[item.node] = callback;
    },
    execute: function(jid, node, opt) {
      var iq, noop;
      if (opt == null) {
        opt = {};
      }
      iq = createExecIQ({
        jid: jid,
        node: node,
        action: opt.action,
        sid: opt.sid,
        data: opt.data,
        form: opt.form,
        item: $.grep(this._cmds.items, function(n) {
          return n.node === node;
        })
      });
      noop = Strophe.Disco.noop;
      return this._conn.sendIQ(iq, opt.success || noop, opt.error || noop);
    }
  });

}).call(this);
