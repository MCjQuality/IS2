package it.unibs.ing.elaborato.start;

import it.unibs.ing.elaborato.exception.FileReaderException;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.exception.LogoutException;
import it.unibs.ing.elaborato.menu.MenuManager;
import it.unibs.ing.elaborato.model.user.User;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;

import java.util.Optional;

public class Main
{
	public static void main(String[] args)
    {
		boolean exit;
        do
        {
            exit = false;
            try
            {
                AppInitializer appInitializer = new AppInitializer();
                UserAuthenticator authenticator = appInitializer.createUserAuthenticator();
                Optional<User> user = authenticator.authenticate();

                if (user.isPresent())
                {
                    System.out.println(Constants.GREEN_FORMAT + Printer.align(Constants.RECOGNIZED_CREDENTIALS_MESSAGE, Constants.MENU_LINE_SIZE) + Constants.RESET_FORMAT);
                    System.out.println(Printer.align(Constants.CONFIRM_ACCESS_MESSAGE + user.get().getUsername(), Constants.MENU_LINE_SIZE));
                    if (user.get().getFirstAccess())
                        authenticator.update(user.get());

                    MenuManager menuManager = appInitializer.createMenuController(user.get());
                    menuManager.start();
                }
                else
                {
                    System.out.println(Constants.RED_FORMAT + Printer.align(Constants.WRONG_CREDENTIAL_MESSAGE, Constants.MENU_LINE_SIZE) + Constants.RESET_FORMAT);
    				System.out.println();


                    String wantToRegister = Utility.checkCondition(Constants.REGISTRATION_AS_USER_MESSAGE, Constants.INVALID_INPUT_MESSAGE,
                            (input) -> (!input.equalsIgnoreCase(Constants.YES_MESSAGE) && !input.equalsIgnoreCase(Constants.NO_MESSAGE)), appInitializer.getScanner());
                    if (wantToRegister.equalsIgnoreCase(Constants.YES_MESSAGE))
                    {
                        User newUser = authenticator.register();
                        MenuManager menuManager = appInitializer.createMenuController(newUser);
                        menuManager.start();
                    } else
                        exit = true;
                }
            } catch (LogoutException e) {
                System.out.println(e.getMessage());
                exit = true;
            } catch (FileWriterException | FileReaderException e) {
                System.out.println(e.getMessage());
            }
        } while(exit);
    }
}
