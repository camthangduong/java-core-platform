/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trywithresources;

import java.io.IOException;

/**
 *
 * @author thangduong
 */
public class MyAutoCloseable implements AutoCloseable {
    public void saySomething() throws IOException{
        throw new IOException ("Exception from saySomething");
        //System.out.println("Something");
    }
    @Override
    public void close() throws IOException {
        throw new IOException ("Exception from close");
        //System.out.println("close");
    }
}
