/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author lalli
 */
public class FileUserDAO implements UserDao {

    private String tiedosto;

    public FileUserDAO(String tiedosto) {
        this.tiedosto = tiedosto;
    }

    @Override
    public List<User> listAll() {
        Scanner scanner = new Scanner(tiedosto);
        ArrayList<User> u = new ArrayList<User>();
        while (scanner.hasNext()) {
            String[] user = scanner.nextLine().split(";");
            u.add(new User(user[0], user[1]));
        }
        return u;
    }

    @Override
    public User findByName(String name) {
        Scanner scanner = new Scanner(tiedosto);
        while (scanner.hasNext()) {
            String[] user = scanner.nextLine().split(";");
            if(user[0].equals(name)){
                return new User(user[0],user[1]);
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            FileWriter f = new FileWriter(tiedosto);
            f.write(user.getUsername()+";"+user.getPassword());
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
