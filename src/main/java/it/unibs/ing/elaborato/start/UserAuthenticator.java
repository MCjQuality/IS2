package it.unibs.ing.elaborato.start;

import it.unibs.ing.elaborato.model.district.DistrictHandler;
import it.unibs.ing.elaborato.exception.FileWriterException;
import it.unibs.ing.elaborato.model.user.Configurator;
import it.unibs.ing.elaborato.model.user.Consumer;
import it.unibs.ing.elaborato.model.user.User;
import it.unibs.ing.elaborato.model.user.UserHandler;
import it.unibs.ing.elaborato.util.Constants;
import it.unibs.ing.elaborato.util.Printer;
import it.unibs.ing.elaborato.util.Utility;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;
import java.util.Scanner;

public class UserAuthenticator
{
    private final UserHandler userHandler;
    private final DistrictHandler districtHandler;
    private final Scanner scanner;

    public UserAuthenticator(UserHandler userHandler, DistrictHandler districtHandler, Scanner scanner)
    {
        this.userHandler = userHandler;
        this.districtHandler = districtHandler;
        this.scanner = scanner;
    }

    public Optional<User> authenticate()
    {
        System.out.println();
        System.out.println(Constants.LIGHT_BLUE_FORMAT + Constants.ITALICS + Printer.align(Constants.WELCOME_MESSAGE, Constants.MENU_LINE_SIZE) + Constants.RESET_FORMAT);
        System.out.println();

        String username = Utility.checkCondition(Constants.USERNAME_MESSAGE, Constants.INVALID_INPUT_MESSAGE, String::isBlank, scanner);

        System.out.print(Constants.PASSWORD_MESSAGE);
        System.out.print(Constants.ESCAPE_CODE_BLACKED_TEXT);
        String password = scanner.nextLine();
        System.out.println(Constants.RESET_FORMAT);

        return userHandler.findUser(username, password);
    }

    public Consumer register() throws FileWriterException
    {
        Utility.clearConsole(Constants.TRANSACTION_TIME);
        Consumer consumer = null;

        boolean exit;
        do
        {
            exit = false;
            try
            {
                System.out.println(Constants.GRAY_FORMAT + Printer.align(Constants.REGISTRATION_CONSUMER_MESSAGE, Constants.MENU_LINE_SIZE) + Constants.RESET_FORMAT);
                System.out.println();
                System.out.println(Printer.columnize(Printer.printNumberedDistricts(districtHandler.getDistricts()), Constants.MENU_LINE_SIZE));
                int district_index = Integer.parseInt(Utility.checkCondition(Constants.SELECT_FROM_THE_OPTIONS_MESSAGE, Constants.INVALID_INPUT_MESSAGE, input -> !Utility.isInt(input) || !(Integer.parseInt(input) >= Constants.NUMBER_1_MESSAGE && Integer.parseInt(input) <= districtHandler.getDistricts().size()), scanner));
                String district_name = districtHandler.getDistricts().get(district_index - Constants.NUMBER_1_MESSAGE).getName();
                String username = Utility.checkCondition(Constants.USERNAME_MESSAGE, Constants.INVALID_INPUT_MESSAGE, String::isBlank, scanner);
                String password = Utility.checkCondition(Constants.PASSWORD_MESSAGE, Constants.INVALID_INPUT_MESSAGE, input -> !Utility.isPswValid(input.toCharArray(), Constants.DIGITS_REQUIREMENT, Constants.LETTERS_REQUIREMENT), scanner);
                String email = Utility.check2Condition(Constants.MAIL_MESSAGE, Constants.INVALID_INPUT_MESSAGE, Constants.INSERT_VALID_MAIL_ADDRESS, String::isBlank, input -> !Utility.isValidEmail(input), scanner);

                consumer = new Consumer(username, BCrypt.hashpw(password, BCrypt.gensalt()), district_name, email);
                userHandler.add(consumer);
                userHandler.save();
            } catch (IllegalArgumentException e) {
                System.out.println(Constants.USERNAME_ALREADY_EXISTS);
                Utility.clearConsole(Constants.TRANSACTION_TIME);
                exit = true;
            }
        } while(exit);

        return consumer;
    }

    public void update(User user) throws FileWriterException
    {
        String newUsername;
        String newPsw;
        Configurator configurator = (Configurator) user;

        Utility.clearConsole(Constants.TRANSACTION_TIME);

        boolean exit;
        do
        {
            exit = false;
            try
            {
                System.out.println();
                System.out.println(Printer.align(Constants.CREDENTIALS_UPDATE, Constants.MENU_LINE_SIZE));
                System.out.println();

                newUsername = Utility.checkCondition(Constants.INSERT_NEW_USERNAME, Constants.INVALID_INPUT_MESSAGE, input -> input.isBlank() || input.equals(configurator.getUsername()), scanner);
                newPsw = Utility.check2Condition(String.format(Constants.INSERT_NEW_PSW, Constants.DIGITS_REQUIREMENT, Constants.LETTERS_REQUIREMENT), Constants.INSERT_PASSWORD_MATCHES_PREVIOUS_MESSAGE, Constants.INVALID_INPUT_MESSAGE, input -> input.equals(configurator.getPsw()), input -> !Utility.isPswValid(input.toCharArray(), Constants.DIGITS_REQUIREMENT, Constants.LETTERS_REQUIREMENT), scanner);

                configurator.updateCredentials(newUsername, newPsw);
                userHandler.add(configurator);
                userHandler.save();

                System.out.println();
                System.out.println(Printer.align(Constants.CREDENTIALS_UPDATED, Constants.MENU_LINE_SIZE));
            } catch (IllegalArgumentException e) {
                System.out.println(Constants.USERNAME_ALREADY_EXISTS);
                Utility.clearConsole(Constants.TRANSACTION_TIME);
                exit = true;
            }
        } while(exit);
    }
}
