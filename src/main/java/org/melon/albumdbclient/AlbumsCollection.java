package org.melon.albumdbclient;

import org.json.JSONException;
import org.melon.albumdbclient.actions.*;
import org.melon.albumdbclient.utils.ConsoleInputProvider;

import java.io.IOException;
import java.net.URISyntaxException;


public class AlbumsCollection {

    private AddRecord addRecord;
    private PrintToConsole printToConsole;
    private EditRecord editRecord;
    private DeleteRecord deleteRecord;
    private FindRecord findRecord;

    private static final String FIND = "wyszukaj";
    private static final String ADD = "dodaj";
    private static final String ADD_ALBUM = "dodaj album";
    private static final String PRINT = "drukuj";
    private static final String PRINT_DB = "drukuj bazę";
    private static final String EDIT = "edytuj";
    private static final String EDIT_ELEMENTS = "edytuj elementy";
    private static final String DELETE = "usuń";
    private static final String DELETE_FROM_DB = "usuń z bazy";
    private static final String EXIT = "zakończ";

    public AlbumsCollection() {
        initializeActions();
    }

    private void initializeActions() {

        this.addRecord = new AddRecord();
        this.printToConsole = new PrintToConsole();
        this.editRecord = new EditRecord();
        this.deleteRecord = new DeleteRecord();
    }

    public void start() throws IOException, URISyntaxException, JSONException {
        String line;

        do {
            welcomMenu();
            line = ConsoleInputProvider.readStringFromUserHandlingEmptyInput().toLowerCase();

            if (line.equals(ADD) || line.equals(ADD_ALBUM)) {
                addRecord();
//            } else if (line.equals(PRINT) || line.equals(PRINT_DB)) {
//                printToConsole();
            } else if (line.equals(EDIT) || line.equals(EDIT_ELEMENTS)) {
                editRecord();
            } else if (line.equals(DELETE) || line.equals(DELETE_FROM_DB)) {
                deleteRecord();
            } else if (line.equals(EXIT)) {
                ConsoleInputProvider.closeScanner();
                break;
            } else if (line.equals(FIND)) {
                findRecord();
                break;
            } else {
                System.out.println("To nie jest poprawnie wybrana opcja z MENU, wpisz właściwą komendę: ");
            }
        } while (!line.toLowerCase().equals(EXIT));
    }


    private void endMessage() {

        ConsoleInputProvider.waitForPresedEnter();
    }

    private void welcomMenu() {
        System.out.println("---------------------------");
        System.out.println("1. Wyszukaj albumy w bazie");
        System.out.println("2. Dodaj album do bazy");
        System.out.println("3. Drukuj elementy bazy na konsoli");
        System.out.println("4. Edytuj elementy bazy");
        System.out.println("5. Usuń album z bazy");
        System.out.println("6. Zakończ");
        System.out.println("---------------------------");
        System.out.println("Wybierz opcję:");
    }

    private void addRecord() throws IOException, JSONException {
        addRecord.addRecordToDb();
    }

//    private void printToConsole() {
//        printToConsole.printAlbumsDbOnConsole();
//        endMessage();
//    }

    private void editRecord() {
        editRecord.editAlbumFields();
    }

    private void deleteRecord() throws IOException, URISyntaxException {
        deleteRecord.deleteRecordFromDb();
    }
    private void findRecord() throws IOException,URISyntaxException,JSONException{
        findRecord.findRecord();
    }

}
