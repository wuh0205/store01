package com.wuh.TinkInJavaTest.initAndClean;

/**
 * 1.System.gc 执行垃圾回收和finalize
 */
public class Book {

    private Boolean checkedOut =false;

    private Book(Boolean checkedOut){
        this.checkedOut = checkedOut;
    }

    private void checkIn(){
        checkedOut =false;
    }

    @Override
    protected void finalize() throws Throwable {
        if(checkedOut){
            System.out.println("Error: checked out");
            //Normally, you'll also do this:
            //super.finalize(); //Call the base-class version
        }
    }

    public static void main(String[] args){
        new Book(true);
        System.gc();//Force garbage collection & finalization
    }
    /*
    * Output:
    * Error: checked out
    * */
}
