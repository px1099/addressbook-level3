package seedu.addressbook.commands.menu;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.menu.Type;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.net.Proxy;
import java.util.*;

/**
 * Lists all food items in the address book to the user.
 */
public class MenuListByTypeCommand extends Command {

    public static final String COMMAND_WORD = "listmenutype";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Displays all food item of a specific category in the Rms system as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_ERROR = "Invalid menu type searched! " +
            "\n" +"Only the following types are available: main, sides, beverage, dessert, others, set meals." +
            "\n" + "Only one type search allowed at a time!";
    private final String itemword;
    //public static boolean executedMenutype = false;

    public MenuListByTypeCommand(String itemword){
        this.itemword = itemword;
    }
    final List<ReadOnlyMenus> matchedFoodItems = new ArrayList<>();

    /* public String getItemword(){
         return
     }*/
    private final Set<String> typeSet = new HashSet<>();
    private List<ReadOnlyMenus> getFoodItemsBurger(String itemword) {
        for (ReadOnlyMenus menuItem : rms.getAllMenus()) {
            //final Set<String> wordsInName = new HashSet<>(burger.getType().getWordsInTypeName());
            final String wordsInItemName = menuItem.getType().value;
            typeSet.add(wordsInItemName);
            //System.out.println(wordsInItemName);
            //boolean exist = wordsInName.contains(itemword);
            if (wordsInItemName.equals(itemword)) {
                matchedFoodItems.add(menuItem);
                //System.out.println(true);
            }
        }
        return matchedFoodItems;
    }


    /*private final Set<String> keywords;

    public MenuListBurgerCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    } //required for Parser Test later

    //List<ReadOnlyMenus> allMenus = rms.getAllMenus().immutableListView();
    private List<ReadOnlyMenus> getFoodItemsBurger(Set<String> keywords) {
        final List<ReadOnlyMenus> matchedFoodItems = new ArrayList<>();
        for (ReadOnlyMenus burger : rms.getAllMenus()) {
            final Set<String> wordsInName = new HashSet<>(burger.getType().getWordsInTypeName());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedFoodItems.add(burger);
            }
        }
        return matchedFoodItems;
    }*/


    @Override
    public CommandResult execute() {
        final List<ReadOnlyMenus> itemsFound = getFoodItemsBurger(itemword);
        if(Type.isValidTypeName(itemword)==false){
            return new MenuCommandResult(MESSAGE_ERROR);
        }

        return new MenuCommandResult(getMessageForMenuListShownSummary(itemsFound), itemsFound);
        /*if(MenuListCommand.executeMenu == true) {
            //executedMenutype = true;
            final List<ReadOnlyMenus> itemsFound = getFoodItemsBurger(itemword);
            return new MenuCommandResult(getMessageForMenuListShownSummary(itemsFound), itemsFound);
        }
        return new MenuCommandResult(Messages.MESSAGE_INVALID_COMMAND_FORMAT);*/
    }
}