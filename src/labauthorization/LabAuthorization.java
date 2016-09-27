/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labauthorization;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class LabAuthorization {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Scanner sc = new Scanner(System.in);
        int type = -1; // 1-registration, 2-authorization
        User user;
        String login;
        String psw;
        Authorizer authorizer = new Authorizer();
        Registrator registrator;

        do {
            System.out.println("Enter \"1\" for registration. Enter \"2\" for authorization. Enter \"0\" for exit.");
            System.out.print(">");
            try {
                type = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {

            }
            if (type == 0) {
                return;
            } else if (type != 1 && type != 2) {
                System.out.println("Wrong input");
            }
        } while (type != 1 && type != 2);

        user = new User();

        do {
            System.out.print("Login:");
            login = sc.nextLine();
        } while (!(login.matches("[A-Za-z0-9_]+") && (type == 2 || authorizer.checkLogin(login))));
        user.setLogin(login);

        do {
            System.out.print("Password:");
            psw = sc.nextLine();
        } while (!psw.matches("[A-Za-z0-9_@#$]+"));
        user.setPassword(psw);

        if (type == 1) {
            System.out.print("Confirm password:");
            if (!sc.nextLine().equals(user.getPassword())) {
                System.out.println("Passwords are not equals.");
                return;
            }
            registrator = new Registrator();
            registrator.writeData(user);
            System.out.println(registrator.toString());
        } else {
            authorizer.authorization(user);
        }
        sc.close();
    }

}
