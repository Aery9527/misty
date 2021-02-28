/**
 * 這個package用來存放增強FI(Functional Interface)功能的介面.
 * 因為FI裡面的動作時常會拋錯, 這時候編譯器會要求你try-catch,
 * 但很多時候我們就想把FI內部的錯誤直接向外拋, 不想要再包過一層,
 * 因此這個package內的FI就是可以用來直接拋出內部錯誤用的, 而不用再包一層try-catch.
 */
package org.misty.util.fi;