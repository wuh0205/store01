/*
  regexObj.test(str)
  test():
     方法执行一个检索，用来查看正则表达式与指定的字符串是否匹配。返回 true 或 false。
*/
/^translate.*]/.test('translate(50,20)');//true
RegExp.$1;//""
RegExp.$2;//""
/*
  注：执行前需清空RegExp.$n,以免引用之前的值
*/

/^translate[(](\d+),(\d+)[)]/.test('translate(50,20)');//true
RegExp.$1//50
RegExp.$2//20
RegExp.$3//""


/*
  regexObj.exec(str)
  exec():
     方法为指定的一段字符串执行搜索匹配操作。它的返回值是一个数组或者 null。
     返回数组[0]始终返回匹配第一项,数组[1..n]为捕获项
*/
/^translate.*/.exec('translate(50,20)');//["translate(50,20)"]
RegExp.$1//""
RegExp.$2//""


/^translate[(](\d+),(\d+)[)]/.exec('translate(50,20)');//["translate(50,20)", "50", "20"]
RegExp.$1//50
RegExp.$2//20

/translate[(](\d+),(\d+)[)]/g.exec('translate(50,20)translate(250,220)');//["translate(50,20)", "50", "20"]


/*
  str.match(regexp);
  match():
     方法会提取匹配项。
     当match不使用g和exec返回结果一样，match使用g，则返回所有匹配项的数组

*/
'translate(50,20)'.match(/^translate.*/g);//["translate(50,20)"]
RegExp.$1//""
RegExp.$2//""

'translate(50,20)'.match(/^translate[(](\d+),(\d+)[)]/);//["translate(50,20)", "50", "20"]
RegExp.$1//"50"
RegExp.$2//"20"

'translate(50,20)translate(250,220)'.match(/translate[(](\d+),(\d+)[)]/g);//["translate(50,20)", "translate(250,220)"]
RegExp.$1//"250"
RegExp.$2//"220"

'translate(123,20)translate(50,20000)translate(50,20)translate(50,20)'.match(/[(]\d+,\d+[)]/g);//["(123,20)", "(50,20000)", "(50,20)", "(50,20)"]
RegExp.$1//""
RegExp.$2//""

'translate(123,20)translate(50,20000)translate(50,20)translate(50,20)'.match(/[(]\d+,\d+[)]/);//["(123,20)"]
RegExp.$1//""
RegExp.$2//""


/*
  总结：
    1.正则中()用于捕获, ，执行test、exec、match方法，均可在RegExp[$1..9]中找到，如果match使用全匹配(g)，RegExp$n
   可能被覆盖,值为最后一个对应匹配项
    2.exec与match的区别,exec返回数组[0]始终为第一个匹配项，match不使用g和exec返回结果一样，exec=match不使用g，match
   使用g，则返回所有匹配项的数组
*/