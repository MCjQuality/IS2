package it.unibs.ing.elaborato.util;

public class Constants {
	
	//format
	public static final String RESET_FORMAT = "\u001B[0m";
	public static final String LIGHT_BLUE_FORMAT = "\u001B[36m";
	public static final String RED_FORMAT = "\u001B[31m";
	public static final String YELLOW_FORMAT = "\u001B[33m";
	public static final String GREEN_FORMAT = "\033[1;32m";
	public static final String GRAY_FORMAT = "\u001B[90m";
	public static final String ESCAPE_CODE_BLACKED_TEXT = "\033[8m";
	public static final String BOLD_FORMAT = "\u001B[1m";
	public static final String NUMBER_FORMAT = "%.2f";
	public static final String TAB = "\t";
	public static final String NEW_LINE = "\n";	
	public static final String DOUBLE_NEW_LINE = "\n\n";
	public static final String COLONS = ": ";
	public static final String SEPARATOR = " - ";
	public static final String ITALICS = "\033[1m\033[3m";
	public static final String UNDERLINE = "\033[4m";
	public static final String COMMA = ", ";
	public static final String SQUARE_BRACKETS1 = " [";
	public static final String SQUARE_BRACKETS2 = "]";
	
	public static final String EMAIL_REGEX =
	        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

	
	//valori
	public static final int MENU_LINE_SIZE = 56;
	public static final int NUMBER_1_MESSAGE = 1;
	public static final int NUMBER_2_MESSAGE = 2;
	public static final int NUMBER_3_MESSAGE = 3;
	public static final int NUMBER_4_MESSAGE = 4;
	public static final int NUMBER_5_MESSAGE = 5;
	public static final int NUMBER_6_MESSAGE = 6;
	public static final int NUMBER_7_MESSAGE = 7;
	public static final int NUMBER_8_MESSAGE = 8;
	public static final int NUMBER_0_MESSAGE = 0;
	public static final int READING_TIME = 2500;
	public static final int TRANSACTION_TIME = 1200;
	
	//main
	public static final String WRONG_CREDENTIAL_MESSAGE = "Credenziali non riconosciute!";
	public static final String CONFIRM_ACCESS_MESSAGE = "Accesso eseguito come: ";
	public static final String RECOGNIZED_CREDENTIALS_MESSAGE = "Credenziali Riconosciute ✔";
	public static final String WELCOME_MESSAGE = "BENVENUTO";
	public static final String USERNAME_MESSAGE = "username: ";
	public static final String PASSWORD_MESSAGE = "password: ";
	public static final String MAIL_MESSAGE = "mail: ";
	public static final String INSERT_NEW_USERNAME = "Inserisci nuovo username: ";
	public static final String INSERT_NEW_PSW = "Inserisci nuova password (min %d cifre - %d lettere): ";
	public static final String FILE_READER_EXCEPTION = BOLD_FORMAT + "Ci sono stati problemi nella lettura dei file. Riprovare piu' tardi" + RESET_FORMAT;
	public static final String FILE_WRITER_EXCEPTION = BOLD_FORMAT + "Ci sono stati problemi nella scrittura sui file. Riprovare piu' tardi" + RESET_FORMAT;
	public static final String INSERT_PASSWORD_MATCHES_PREVIOUS_MESSAGE = BOLD_FORMAT + TAB + "Password uguale alla precedente, reinserire: " + RESET_FORMAT;
	public static final String REGISTRATION_AS_USER_MESSAGE = "Vuoi registrarti come fruitore (y/n)? ";
	public static final String INSERT_VALID_MAIL_ADDRESS = BOLD_FORMAT + TAB + "Inserire un indirizzo mail valido: " + RESET_FORMAT;
	public static final String CREDENTIALS_UPDATE = GRAY_FORMAT + "-AGGIORNAMENTO CREDENZIALI-" + RESET_FORMAT;
	public static final String CREDENTIALS_UPDATED = Constants.GREEN_FORMAT + "Credenziali aggiornate!" + Constants.RESET_FORMAT;
	public static final String USERNAME_ALREADY_EXISTS = "Username gia' presente. Riprovare";

	//menu
		//generali
	public static final String CONFIG_OPTION = Constants.OPTION_1_CONFIG_MENU_MESSAGE
			+ Constants.OPTION_2_CONFIG_MENU_MESSAGE
			+ Constants.OPTION_3_CONFIG_MENU_MESSAGE
			+ Constants.OPTION_4_CONFIG_MENU_MESSAGE
			+ Constants.OPTION_5_CONFIG_MENU_MESSAGE
			+ Constants.OPTION_6_CONFIG_MENU_MESSAGE
			+ Constants.OPTION_7_CONFIG_MENU_MESSAGE
			+ Constants.OPTION_8_CONFIG_MENU_MESSAGE
			+ Constants.OPTION_0_MENU_MESSAGE
			+ Constants.NEW_LINE
			+ Utility.appendHorizontalLine(Constants.MENU_LINE_SIZE);
	public static final String CONSUMER_OPTION = Constants.OPTION_1_CONSUMER_MENU_MESSAGE
			+ Constants.OPTION_2_CONSUMER_MENU_MESSAGE
			+ Constants.OPTION_3_CONSUMER_MENU_MESSAGE
			+ Constants.OPTION_4_CONSUMER_MENU_MESSAGE
			+ Constants.OPTION_5_CONSUMER_MENU_MESSAGE
			+ Constants.OPTION_0_MENU_MESSAGE
			+ (Constants.NEW_LINE
			+ Utility.appendHorizontalLine(Constants.MENU_LINE_SIZE));
		//sottotitoli
	public static final String INSERT_NEW_DISTRICT_MESSAGE = GRAY_FORMAT + "-INSERIMENTO TERRITORIO-" + RESET_FORMAT;
	public static final String NAVIGATE_THROUGH_HIERARCHIES_MESSAGE = GRAY_FORMAT + "-NAVIGAZIONE GERARCHIE-" + RESET_FORMAT;
	public static final String REGISTRATION_CONSUMER_MESSAGE = "-REGISTRAZIONE FRUITORE-";
	public static final String WITHDRAW_PROPOSAL_MESSAGE = GRAY_FORMAT + " -RITIRA PROPOSTE-" + RESET_FORMAT;
	public static final String SHOW_PROPOSAL = GRAY_FORMAT + "-VISUALIZZAZIONE PROPOSTE-" + RESET_FORMAT;
	public static final String ADD_EXCHANGE_PROPOSAL_MENU_MESSAGE = GRAY_FORMAT + "-FORMULAZIONE PROSTA DI SCAMBIO-" + RESET_FORMAT;
	public static final String INSERT_CONV_FACT = GRAY_FORMAT + "-INSERIMENTO FATTORI DI CONVERSIONE- " + RESET_FORMAT;
	public static final String VIEW_CONVERSION_FACTOR_MESSAGE = GRAY_FORMAT + "-VISUALIZZAZIONE FATTORI DI CONVERSIONE-" + RESET_FORMAT;
	public static final String VIEW_ALLA_HIERARCHIES_MESSAGE = GRAY_FORMAT + "-VISUALIZZAZIONE GERARCHIE-" + RESET_FORMAT;
	public static final String VIEW_ALL_DISTRICTS_MESSAGE = GRAY_FORMAT + "-VISUALIZZAZIONE TERRITORI-" + RESET_FORMAT;
	public static final String INSERT_NEW_HIERARCHY_MESSAGE = GRAY_FORMAT + "-INSERIMENTO GERARCHIA-" + RESET_FORMAT;
	public static final String SHOW_CLOSED_SETS = GRAY_FORMAT + "-VISUALIZZAZIONE INSIEMI CHIUSI-" + RESET_FORMAT;

		//voci menu
	public static final String TITLE_MENU = Constants.NEW_LINE
		+ Utility.appendHorizontalLine(Constants.MENU_LINE_SIZE)
		+ Constants.NEW_LINE
		+ Printer.align(Constants.MENU_MESSAGE, Constants.MENU_LINE_SIZE)
		+ Constants.NEW_LINE
		+ Utility.appendHorizontalLine(Constants.MENU_LINE_SIZE);
	public static final String CHOOSE_LEAF_CATEGORY_MESSAGE = "1 - foglia";
	public static final String CHOOSE_NOT_LEAF_CATEGORY_MESSAGE = "2 - NON foglia";
	public static final String CHOOSE_NO_SUBCATEGORY = "0 - Nessuna sotto-categoria";
	public static final String OPTION_0_MENU_MESSAGE = Constants.NEW_LINE + TAB + "0 - Esci";
	public static final String OPTION_8_CONFIG_MENU_MESSAGE = Constants.NEW_LINE + TAB + "8 - Effettua logout";
	public static final String OPTION_7_CONFIG_MENU_MESSAGE = Constants.NEW_LINE + TAB + "7 - Visualizza insiemi chiusi";
	public static final String OPTION_6_CONFIG_MENU_MESSAGE = Constants.NEW_LINE + TAB + "6 - Visualizza proposte";
	public static final String OPTION_5_CONFIG_MENU_MESSAGE = Constants.NEW_LINE + TAB + "5 - Visualizzare i fattori di conversione";
	public static final String OPTION_4_CONFIG_MENU_MESSAGE = Constants.NEW_LINE + TAB + "4 - Visualizzare gerarchie";
	public static final String OPTION_3_CONFIG_MENU_MESSAGE = Constants.NEW_LINE + TAB + "3 - Visualizzare comprensori geografici";
	public static final String OPTION_2_CONFIG_MENU_MESSAGE = Constants.NEW_LINE + TAB + "2 - Introdurre gerarchia";
	public static final String OPTION_1_CONFIG_MENU_MESSAGE = Constants.NEW_LINE + TAB + "1 - Introdurre comprensorio";
	public static final String OPTION_1_CONSUMER_MENU_MESSAGE = Constants.NEW_LINE + TAB + "1 - Naviga tra le gerarchie";
	public static final String OPTION_2_CONSUMER_MENU_MESSAGE = Constants.NEW_LINE + TAB + "2 - Formula proposta di scambio";
	public static final String OPTION_3_CONSUMER_MENU_MESSAGE = Constants.NEW_LINE + TAB + "3 - Ritira proposta";
	public static final String OPTION_4_CONSUMER_MENU_MESSAGE = Constants.NEW_LINE + TAB + "4 - Visualizza proposte";
	public static final String OPTION_5_CONSUMER_MENU_MESSAGE = Constants.NEW_LINE + TAB + "5 - Effettua logout";
	public static final String SHOW_OPEN_PROPOSAL= "1 - Visualizza proposte aperte";
	public static final String SHOW_CLOSED_PROPOSAL = "2 - Visualizza proposte chiuse";
	public static final String SHOW_WITHDRAWN_PROPOSAL = "3 - Visualizza proposte ritirate";
		//richiesta input
	public static final String SPECIFY_DOMAIN_NAME_MESSAGE = "Inserisci il valore del dominio associato alla categoria: ";
	public static final String INSERT_NUMBER_SELECT_HIERARCHY_MESSAGE = "Selezionare il numero relativo alla gerarchia da visualizzare: ";
	public static final String SPECIFY_FIELD_NAME = "Inserisci il campo: ";
	public static final String INSERT_CATEGORY_NAME_MASSAGE = "Inserisci il nome della categoria: ";
	public static final String INSERT_ROOT_NAME_MESSAGE = "Inserisci il nome della radice: ";
	public static final String SPECIFY_COUPLE_NUMBER_MESSAGE = "Inserire il numero della coppia: ";
	public static final String SPECIFY_CONVERSION_FACTOR_NUMBER_MESSAGE = "Specificare il fattore di conversione compreso tra %.2f e %.2f: ";
	public static final String SPECIFY_NAME_LEAF_CATEGORY = "Specifica il numero di una categoria foglia: ";
	public static final String SAVE_THE_CHANGES_MESSAGE = "Desideri salvare le modifiche? (y/n): ";
	public static final String INSERT_DISTRICT_NAME_MESSAGE = "Inserisci nome del comprensorio: ";
	public static final String SELECT_FROM_THE_OPTIONS_MESSAGE = "Seleziona tra le opzioni: ";
	public static final String INSERT_CATEGORY_DESCRIPTION_MESSAGE = "Inserisci la descrizione: ";
	public static final String DESCRIPTION_ASSOCIATED_WITH_DOMAIN_MESSAGE = "Vuoi inserire descrizione associata al dominio (y/n)? ";
	public static final String INSERT_THE_NUMBER_RELATED_TO_THE_HIERARCHY = "Inserire numero: ";
	public static final String HOURS_REQUESTED = "Inserisci le ore di prestazione richieste: ";
	public static final String SERVICE_OFFERED = "Inserisci il servizio offerto: ";
	public static final String SERVICE_REQUESTED = "Inserisci il servizio richiesto: ";
	public static final String SAVING_MESSAGE = "Desideri inviare la proposta (y/n)? ";
	public static final String INSERT_PROPOSAL_NUMBER = "Seleziona tra le opzioni (0 per terminare): ";
	public static final String INSERT_MUNICIPALITY_BELONG_DISTRICT_MESSAGE = "Inserisci i comuni appartenenti al comprensorio (Q per terminare)";
	public static final String INVALID_INPUT_MESSAGE = BOLD_FORMAT + TAB + "Input non valido, reinserire: " + RESET_FORMAT;
	public static final String CHOOSE_LEAF_OPTIONS = NEW_LINE + "Seleziona la tipologia della categoria figlia di '%s': ";
		//errori-conferme
	public static final String CATEGORY_ALREADY_INSERT = BOLD_FORMAT + TAB + "Categoria gia' presente, reinserire: " + RESET_FORMAT;
	public static final String DISTRICT_NOT_SAVED_MESSAGE = YELLOW_FORMAT + TAB + "Comprensorio non salvato!" + RESET_FORMAT;
	public static final String INSERT_VALID_OPTION_MENU_MESSAGE = "Inserire un'opzione valida" + RESET_FORMAT;
	public static final String MUNICIPALITY_NONEXISTENT = RED_FORMAT + TAB + "Comune inesistente!" + RESET_FORMAT;
	public static final String DISTRICT_ALREADY_INSERTED = RED_FORMAT + TAB + "Distretto gia' presente, reinserire: " + RESET_FORMAT;
	public static final String DUPLICATED_MUNICIPALITY_MESSAGE = BOLD_FORMAT + TAB + "Comune già presente!" + RESET_FORMAT;
	public static final String DISTRICT_VALID_MESSAGE = YELLOW_FORMAT + TAB + "Comprensorio salvato!" + RESET_FORMAT;
	public static final String HIERARCHY_VALID_MESSAGE = YELLOW_FORMAT + TAB + "Gerarchia salvata!" + RESET_FORMAT;
	public static final String ROOT_ALREADY_EXIST_MESSAGE = BOLD_FORMAT + TAB + "Radice già presente, reinserire: " + RESET_FORMAT;
	public static final String NO_HIERARCHIES = BOLD_FORMAT + "Nessuna gerarchia presente!" + RESET_FORMAT;
	public static final String NO_DISTRICTS = BOLD_FORMAT + "Nessun comprensorio presente!" + RESET_FORMAT;
	public static final String NO_CONV_FACT = BOLD_FORMAT + "Nessun fattore di conversione presente!" + RESET_FORMAT;
	public static final String NO_LEAVES = BOLD_FORMAT + TAB + 	"Non sono ancora state aggiunte categorie foglia!" + RESET_FORMAT;
	public static final String PRESS_ANY_BUTTONS_TO_GO_BACK = ITALICS + "Premi invio per tornare al menù" + RESET_FORMAT;
	public static final String LEAF_CATEGORY_DOES_NOT_EXIST = BOLD_FORMAT + TAB+ "Servizio inesistente, reinserire: " + RESET_FORMAT;
	public static final String PROPOSAL_SAVED_MESSAGE = YELLOW_FORMAT + TAB + "Proposta salvata!" + RESET_FORMAT;
	public static final String PROPOSAL_NOT_SAVED_MESSAGE = YELLOW_FORMAT + TAB + "Proposta non salvata!"  + RESET_FORMAT;
	public static final String NO_HIERARCHY = BOLD_FORMAT + "Nessuna gerarchia presente!" + RESET_FORMAT;
	public static final String PROPOSAL_ALREADY_INSERT = RED_FORMAT + TAB + "Proposta gia' presente!" + RESET_FORMAT;
	public static final String EXCHANGE_PROPOSAL_CLOSED_MESSAGE = TAB + GREEN_FORMAT +"Proposta soddisfatta e chiusa!" + RESET_FORMAT;
	public static final String PROPOSAL_SUCCESSFULLY_WITHDRAWN = TAB + YELLOW_FORMAT + "Proposta ritirata!" + RESET_FORMAT;
	public static final String NO_PROPOSALS = BOLD_FORMAT + "Nessuna proposta presente!" + RESET_FORMAT;
	public static final String NO_CLOSED_SETS = BOLD_FORMAT + "Nessun insieme chiuso!" + RESET_FORMAT;
	//messaggi
	public static final String WITHDRAWN_PROPOSAL = "Proposta ritirata: ";
	public static final String CONVERSION_FACTOR_ASSOCIATES_LEAF_CATEGORY_MESSAGE = "Fattori di conversione associati a: ";
	public static final String NO_MESSAGE = "n";
	public static final String YES_MESSAGE = "y";
	public static final String Q_MESSAGE = "Q";
	public static final String TERMINATION_MENU_MESSAGE = "Terminazione...";
	public static final String MENU_MESSAGE = "Menu";
	public static final String LOGOUT_MESSAGE = "Logout...";
	public static final String ASSOCIATED_NODE_TO_DOMAIN = "Nodo associato: %s";
	public static final String ACTIVE_PROPOSALS_RELATED_TO_A_CONSUMER = "Proposte attive di: %s";
	public static final String PROPOSALS_ASSOCIATES_LEAF_CATEGORY_MESSAGE = "Proposte associate a: ";
	public static final String PROPOSAL = "Proposta ";
	
	//printer
	public static final String NAME_DISTRICT_MESSAGE = "Nome: ";
	public static final String COUPLE_SEPARATOR = "--->" + TAB + TAB;
	public static final String TERRITORIES_DISTRICT_MESSAGE = "Territori: ";
	public static final String REMAINING_CONVERSION_FACTOR_MESSAGE = "Fattori di conversione da assegnare:";
	public static final String FIXED_CONVERSION_FACTOR_MESSAGE = "Fattori di conversione fissati:";
	public static final String NAME_MESSAGE = "Nome: ";
	public static final String DOMAIN_MESSAGE = "Dominio: ";
	public static final String DESCRIPTION_MESSAGE = "Descrizione: ";
	public static final String FIELD_MESSAGE = "Campo: ";
	public static final String TREE_PRINT_OF_HIERARCHY_MESSAGE = GRAY_FORMAT + " -Visualizzazione ad albero-" + RESET_FORMAT;
	public static final String COMPLETE_PRINT_OF_HIERARCHY_MESSAGE = GRAY_FORMAT + " -Visualizzazione completa-" + RESET_FORMAT;
	public static final String CLOSED_SETS = BOLD_FORMAT + UNDERLINE + "Insieme %d" + RESET_FORMAT;
	public static final String REQUEST_MESSAGE = "Richiesta:";
	public static final String OFFER_MESSAGE = "Offerta:";
	public static final String OPEN = "APERTA";
	public static final String CLOSED = "CHIUSA";
	public static final String WITHDRAWN = "RITIRATA";
	
	//filepath
	public static final String HIERARCHIES_FILEPATH = "src/main/resources/hierarchies.dat";
	public static final String DISTRICTS_FILEPATH = "src/main/resources/districts.dat";
	public static final String ITALIAN_MUNICIPALITIES_FILEPATH = "src/main/resources/italianMunicipalities.dat";
	public static final String CONVERSION_ELEMENTS_FILEPATH = "src/main/resources/conversionElements.dat";
	public static final String USERS_FILEPATH = "src/main/resources/users.dat";
	public static final String PROPOSALS_FILEPATH = "src/main/resources/proposals.dat";
	public static final String CLOSED_SETS_FILEPATH = "src/main/resources/closedSets.dat";

	public static final int DIGITS_REQUIREMENT = 4;
	public static final int LETTERS_REQUIREMENT = 0;
	public static final String CONSUMER = "Fruitore: ";
	public static final String MAIL = "Mail: ";
}