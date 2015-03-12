/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webscraper.actions.grabsourcecode;

/**
 *
 * @author Joshua Powell I give permission for you to use this source code for
 * whatever purposes as long as I am credited with creating it inside the code.
 */
public interface GetWebSourceCode
{
    //Method To Implement When A Class Is Trying To Get Source Code From A Web Page
    public abstract String GetSourceCode(String userInput);
}
