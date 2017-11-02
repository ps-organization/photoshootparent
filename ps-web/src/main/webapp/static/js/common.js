//正则表达式
    function isMobil(s)
    {
        var patrn=/^1[0-9]{10}$/;
        if (!patrn.exec(s)) return false
        return true
    }
    function isEmail(s)
    {
        var patrn=/(^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/;
        if (!patrn.exec(s)) return false
        return true
    }
    function isEmpty(s)
    {
        var patrn=/\S/;
        if (!patrn.exec(s))return false
        return true
    }
    function isPwd(s)
    {
    var patrn=/\S{8,16}/;
    if (!patrn.exec(s))return false
    return true
    }
