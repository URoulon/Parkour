package me.A5H73Y.parkour.other;

import java.io.File;

import me.A5H73Y.parkour.Parkour;
import me.A5H73Y.parkour.course.CourseInfo;
import me.A5H73Y.parkour.course.CourseMethods;
import me.A5H73Y.parkour.enums.DatabaseType;
import me.A5H73Y.parkour.utilities.DatabaseMethods;
import me.A5H73Y.parkour.utilities.Static;
import me.A5H73Y.parkour.utilities.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public final class Help {

    /**
     * Lookup and display the syntax and description for each Parkour command.
     *
     * @param args
     * @param sender
     */
    public static void lookupCommandHelp(String[] args, CommandSender sender) {
        if (args.length == 1) {
            sender.sendMessage(Static.getParkourString() + "Find helpful information about any Parkour command:");
            sender.sendMessage("             /pa help " + ChatColor.AQUA + "(command)");
            return;
        }

        if (args[1].equalsIgnoreCase("join")) {
            displayHelpMessage(sender, "Join a Course", "/pa join (courseName)", "/pa join tutorial",
                    " You are able to join a course using its name, without having to use the correct case. Each course has a unique numeric identifier (courseID) which can be used to join the course instead of its name. Once you have joined a course, you are in 'Parkour Mode', which allows you to interact with the ParkourKit and track your statistics.");

        } else if (args[1].equalsIgnoreCase("joinall")) {
            displayHelpMessage(sender, "Display Join Courses Menu", "/pa joinall", null,
                    " Display all the Parkour courses available in a scrollable menu. Simply right click the course entry to join it.");

        } else if (args[1].equalsIgnoreCase("leave")) {
            displayHelpMessage(sender, "Leave a Course", "/pa leave", null,
                    " Leaving the course you are currently playing will terminate all information tracking your current progress, and you will be teleported back to the Parkour lobby.");

        } else if (args[1].equalsIgnoreCase("info")) {
            displayHelpMessage(sender, "Display Parkour information", "/pa info [player]", null,
                    " Display all your Parkour statistics, which can include your current progress through a course as well as the saved information, such as your Parkour level. Using the extra argument will allow you to display the information of a different player.");

        } else if (args[1].equalsIgnoreCase("stats")) {
            displayHelpMessage(sender, "Display course statistics", "/pa stats (course)", "/pa stats tutorial",
                    " Display all the course information and statistics, including the requirements to join and the rewards given on completion.");

        } else if (args[1].equalsIgnoreCase("lobby")) {
            displayHelpMessage(sender, "Teleport to Parkour lobby", "/pa lobby [lobby]", null,
                    " Teleport to the chosen lobby. If you do not specify a lobby it will take you to the default lobby, otherwise it will attempt to join the Lobby specified in the argument. Note that some lobbies can have a minimum Parkour level requirement.");

        } else if (args[1].equalsIgnoreCase("perms")) {
            displayHelpMessage(sender, "Display Parkour Permissions", "/pa perms", null,
                    " Your Parkour permissions will be displayed based on the group permissions you have. For example, if you have 'Parkour.Admin.*', then you are a part of the Admin group, same for 'Parkour.Basic.*' etc. However, if you have been given only a selection of permissions from that group, it will not display, for example 'Parkour.Admin.Testmode' does not make you an admin. 'Parkour.*' will give you all Parkour permissions.");

        } else if (args[1].equalsIgnoreCase("like") || args[1].equalsIgnoreCase("dislike")) {
            displayHelpMessage(sender, "Vote opinion of course", "/pa (like / dislike) [course]", null,
                    " Once you complete a course, you will have the ability to submit your vote on whether or not you liked the course. If you do not specify a course then the last course completed is selected. You only have one vote for each course. The sole purpose of this is for statistics, i.e. 70% of people liked this course.");

        } else if (args[1].equalsIgnoreCase("list")) {
            displayHelpMessage(sender, "Display the list contents", "/pa list (courses / players / ranks / lobbies)", null,
                    " This command can display all the courses saved on the server in a page format, ordered by date of creation, each having their own unique numerical ID which can be used to join the course. Display all the players that are currently using the plugin, this includes which course, and how many times they've died. Display the available Parkour Ranks to be unlocked, with the required Parkour Level to achieve each. Display all custom lobbies available.");

        } else if (args[1].equalsIgnoreCase("quiet")) {
            displayHelpMessage(sender, "Toggle Quiet mode", "/pa quiet", null,
                    " If the Parkour messages are becoming annoying i.e. Seeing 'You died! ...' regularly, you can toggle visibility of these messages using this command.");

        } else if (args[1].equalsIgnoreCase("invite")) {
            displayHelpMessage(sender, "Invite a player to a course", "/pa invite (player)", "/pa invite A5H73Y",
                    " If another player is interested in which course you are currently on, simply send them an invite and it will instruct them on how to join. If you want to challenge each other, check out the '/pa challenge' command.");

        } else if (args[1].equalsIgnoreCase("challenge")) {
            displayHelpMessage(sender, "Challenge a player to a course", "/pa challenge (course) (player) [wager]", "/pa challenge tutorial A5H73Y",
                    " You are able to challenge a player to compete for who can complete a course the fastest. Simply execute the command above to send a challenge to the player, if they accept using '/pa accept' then you'll both be teleported to the beginning of the course and a countdown will initiate. When the counter reaches 0 the race will begin. The visibility of each player is configurable. You are able to specify a wager amount which will be rewarded to the winner and deducted from the loser's economy balance.");

        } else if (args[1].equalsIgnoreCase("create")) {
            displayHelpMessage(sender, "Create a Course", "/pa create (courseName)", "/pa create tutorial",
                    " Creating a new Parkour course only takes 1 command, all the setup is automatic. Remember that your location and the direction you're facing is saved and then loaded once the course is joined. By default, the course will be 'unfinished' until set otherwise using '/pa ready'.");

        } else if (args[1].equalsIgnoreCase("checkpoint")) {
            displayHelpMessage(sender, "Create a checkpoint", "/pa checkpoint [number]", "/pa checkpoint 1",
                    " Made to be as automated and easy as possible. All you do is simply select (edit) a course using '/pa select (course)', then stand where you want a checkpoint to be and enter '/pa checkpoint'. As if by magic it's all done! If you mess up a checkpoint, you can simply override it using '/pa checkpoint (number)'. A pressureplate will be placed automatically.");

        } else if (args[1].equalsIgnoreCase("kit")) {
            displayHelpMessage(sender, "Retrieve ParkourKit", "/pa kit [Kit]", "/pa kit firekit",
                    " You can create a set of ParkourKit and name it whatever you want. Using this command you can fill your inventory with the blocks you configured. If you don't specify a ParkourKit set, it will use the Default blocks.");

        } else if (args[1].equalsIgnoreCase("select")) {
            displayHelpMessage(sender, "Edit a course", "/pa select (course)", "/pa select tutorial",
                    " Many of the commands don't require a course argument as they will use the course you are editing to make things a bit easier. For example '/pa checkpoint' will use the course you are currently editing. If you want to find out which course you are currently editing use '/pa select'. When you create a course, it will automatically select it for editing.");

        } else if (args[1].equalsIgnoreCase("done")) {
            displayHelpMessage(sender, "Finish editing a course", "/pa done", null,
                    " Finish editing the course you have selected.");

        } else if (args[1].equalsIgnoreCase("setstart")) {
            displayHelpMessage(sender, "Set start of a course", "/pa setstart", null,
                    " The start of the selected course will be overwritten to your current position, rather than having to recreate the course.");

        } else if (args[1].equalsIgnoreCase("setautostart")) {
            displayHelpMessage(sender, "Create auto start for course", "/pa setautostart (course)", "/pa setautostart tutorial",
                    " Create a pressure plate that will automatically trigger an automatic start of the course specified in the argument. A configured Material will appear below the pressure plate to identify it, and for better performance.");

        } else if (args[1].equalsIgnoreCase("setcreator")) {
            displayHelpMessage(sender, "Set creator of a course", "/pa setcreator (course) (playerName)", "/pa setcreator tutorial A5H73Y",
                    " The creator of the course will be overwritten to what you've specified. Helpful if an Admin has to setup the course which a non-admin player created. The creator of a course will have certain permissions for that course, regardless of if they are an Admin.");

        } else if (args[1].equalsIgnoreCase("setlobby")) {
            displayHelpMessage(sender, "Set a Parkour lobby", "/pa setlobby [name] [levelRequired]", "/pa setlobby city 10",
                    " Create a lobby where you are stood, specifying its name and a level requirement to join. You are able to link courses to lobbies after completion.");

        } else if (args[1].equalsIgnoreCase("finish")) {
            displayHelpMessage(sender, "Set Course finish status", "/pa finish [course]", "/pa finish example",
                    " When you first create a course, it will not be joinable until it has been set to finished by its creator (configurable). The command will toggle the finish status, so you mark it as finished or unfinished. If you don't provide a course parameter, your selected course will be used.");

        } else if (args[1].equalsIgnoreCase("prize")) {
            displayHelpMessage(sender, "Configure course prize", "/pa prize (course)", "/pa prize tutorial",
                    " A conversation will be started to allow you to setup a course prize exactly how you want, without having to enter long ugly commands.");

        } else if (args[1].equalsIgnoreCase("test")) {
            displayHelpMessage(sender, "Toggle Parkour Test Mode", "/pa test [Kit]", "/pa test firekit",
                    " Test Mode can be toggled on and off using the command, this will simulate being on a generic Parkour course. The position you start Test Mode will act as the starting point. If you specify a ParkourKit this will be simulated, otherwise the default Kit will be used.");

        } else if (args[1].equalsIgnoreCase("leaderboard")) {
            displayHelpMessage(sender, "Display course leaderboards", "/pa leaderboard [[course] [amount] [scope]]", "/pa leaderboard tutorial 10 global",
                    " A conversation will be started to display the leaderboards you want, whether it's the best global or personal times. If you do not wish to use the converation, you can provide the required arguments for instant results.");

        } else if (args[1].equalsIgnoreCase("tutorial")) {
            displayHelpMessage(sender, "Display links to tutorials", "/pa tutorial", null,
                    " If you wish to learn from the offical Parkour tutorials, click the link to be navigated to the tutorial section of the bukkit plugin page.");

        } else if (args[1].equalsIgnoreCase("tp")) {
            displayHelpMessage(sender, "Teleport to Course", "/pa tp (course)", "/pa tp tutorial",
                    " Teleport to the start of the chosen course. This will NOT activate 'Parkour Mode', but simply move you to the beginning of the course.");

        } else if (args[1].equalsIgnoreCase("tpc")) {
            displayHelpMessage(sender, "Teleport to Course checkpoint", "/pa tpc (course) (point)", "/pa tpc tutorial 2",
                    " Teleport to the chosen checkpoint on the course. This will NOT activate 'Parkour Mode', but simply move you to the chosen checkpoint on the course.");

        } else if (args[1].equalsIgnoreCase("link")) {
            displayHelpMessage(sender, "Link the course after completion", "/pa link (course / lobby) (argument)", "/pa link course level2",
                    " You are now able to link the selected course to either a custom lobby, or to join a different course straight after you complete the selected course. For example if you selected a course '/pa select tutorial', you would be able to make the player join Level2 after they complete Level1 by doing '/pa link course Level2', or if you wish for them to teleport to a custom lobby '/pa link lobby Admin'. If you want to remove the link enter '/pa link reset'");

        } else if (args[1].equalsIgnoreCase("linkKit")) {
            displayHelpMessage(sender, "Link a course to ParkourKit", "/pa linkKit (course) (kit)", "/pa linkKit tutorial firekit",
                    " Each course has the ability to have a unique ParkourKit, created using the '/pa createKit' command. Once linked, each type of ParkourKit material for that course will be configured to the action you set.");

        } else if (args[1].equalsIgnoreCase("listKit")) {
            displayHelpMessage(sender, "List ParkourKit information", "/pa listKit [kit]", "/pa listKit firekit",
                    " Display all the ParkourKits available by using the command without the parameter. You can specify a kit name if you want to each material with the corresponding actions.");

        } else if (args[1].equalsIgnoreCase("setmode")) {
            displayHelpMessage(sender, "Set Mode for course", "/pa setmode (course)", "/pa setmode tutorial",
                    " By default, a course does not have a Parkour Mode attached. Each mode can affect the interaction with the course, an example being the 'Freedom' mode allows you to create and load your own checkpoints.");

        } else if (args[1].equalsIgnoreCase("setjoinitem")) {
            displayHelpMessage(sender, "Set Join Item for course", "/pa setjoinitem (course) (material) (amount)", "/pa setjoinitem tutorial elytra 1",
                    " By default, a course will give you the default join items (configurable). You can provide the player with an additional specified item when they join a course, such as Elytra or Ender pearls for a certain types of courses etc.");

        } else if (args[1].equalsIgnoreCase("setminlevel")) {
            displayHelpMessage(sender, "Set minimum required level for course", "/pa setminlevel (course) (level)", "/pa setminlevel tutorial 5",
                    " By default, a course does not have a minimum level requirement to join. However, if you want to enforce the progression of Parkour courses, you can require the player to have a Parkour level greater than or equal to the minimum level specified.");

        } else if (args[1].equalsIgnoreCase("setmaxdeath")) {
            displayHelpMessage(sender, "Set maximum amount of deaths for course", "/pa setmaxdeath (course) (amount)", "/pa setmaxdeath tutorial 5",
                    " By default, a course does not have a maximum amount of deaths. However, you can enforce a limit on the amount of deaths the player can accumulate before being forced to leave the course.");

        } else if (args[1].equalsIgnoreCase("setmaxtime")) {
            displayHelpMessage(sender, "Set a time limit for the course", "/pa setmaxtime (course) (seconds)", "/pa setmaxtime tutorial 30",
                    " By default, a course does not have a maximum time limit. However, you can enforce a time limit the course must be completed by before being forced to leave the course.");

        } else if (args[1].equalsIgnoreCase("rewardonce")) {
            displayHelpMessage(sender, "Reward only once for that course", "/pa rewardonce (course)", "/pa rewardonce tutorial",
                    " Prevent a player from rewarding themselves multiple times for completing a course, by only allowing them to claim the reward the first time they complete the course.");

        } else if (args[1].equalsIgnoreCase("rewardlevel")) {
            displayHelpMessage(sender, "Reward a Parkour Level", "/pa rewardlevel (course) (level)", "/pa rewardlevel tutorial 5",
                    " You can reward a player with a Parkour level on the completion of a course, which will allow them to unlock new courses which would have a minimum level requirement to join. Their level is not overwritten if their current level is higher than the reward level.");

        } else if (args[1].equalsIgnoreCase("rewardleveladd")) {
            displayHelpMessage(sender, "Reward an increase to current Parkour Level", "/pa rewardleveladd (course) (amount)", "/pa rewardleveladd tutorial 2",
                    " You can reward a player with an increase to their Parkour level on the completion of a course, which will allow them to unlock new courses which would have a minimum level requirement to join. The amount is added to their current level.");

        } else if (args[1].equalsIgnoreCase("rewardrank")) {
            displayHelpMessage(sender, "Reward a Parkour Rank", "/pa rewardrank (level) (rank)", "/pa rewardrank 4 &4Pro",
                    " You can link a Parkour level to a rank, which would display beside their name if you enable the Parkour Chat Prefix setting. This would allow you to differentiate each Parkour level a player is, based on their rank. ");

        } else if (args[1].equalsIgnoreCase("rewardparkoins")) {
            displayHelpMessage(sender, "Reward Parkoins", "/pa rewardparkoins (course) (amount)", "/pa rewardparkoins tutorial 10",
                    " You can reward a player with Parkoins for completing a course. These can be spent in the store to unlock additional content which would be unobtainable without the minimum amount of Parkoins required.");

        } else if (args[1].equalsIgnoreCase("rewarddelay")) {
            displayHelpMessage(sender, "Reward Delay/Frequency for course", "/pa rewarddelay (course) (delay)", "/pa rewarddelay tutorial 1",
                    " Limit a course reward for a player to once per day, as in the example. The rate at which the reward is given, is achieved by setting the delay to the number of days which need to elapse before the player can win the same prize again.");

        } else if (args[1].equalsIgnoreCase("recreate")) {
            displayHelpMessage(sender, "Recreate course database", "/pa recreate", null,
                    " Used to fix the database if there are missing courses that haven't been synchronised with the server.");

        } else if (args[1].equalsIgnoreCase("delete")) {
            displayHelpMessage(sender, "Delete a course / checkpoint / lobby / kit / autostart", "/pa delete (course / checkpoint / lobby / kit / autostart) (argument)", "/pa delete course tutorial",
                    " You can delete a course, which will remove all information stored on the server about the course, as well as remove all references from the database. You can delete a lobby from the server. The operation will have to be confirmed or cancelled before the change is made.");

        } else if (args[1].equalsIgnoreCase("reset")) {
            displayHelpMessage(sender, "Reset a course / player / leaderboard / prize", "/pa reset (course / player / leaderboard / prize) (argument)", "/pa reset player A5H73Y",
                    " Resetting a course will delete all the statistics stored, which includes leaderboards and various Parkour attributes. This will NOT affect the spawn / checkpoints. Resetting a player will delete all their times across all courses and delete all various Parkour attributes. The operation will have to be confirmed or cancelled before the change is made.");

        } else if (args[1].equalsIgnoreCase("whitelist")) {
            displayHelpMessage(sender, "Whitelist a command", "/pa whitelist (command)", "/pa whitelist help",
                    " Be default, non-Parkour commands are disabled during the course unless you whitelist a command that the players are allowed to use.");

        } else if (args[1].equalsIgnoreCase("economy")) {
            displayHelpMessage(sender, "Display Economy information", "/pa economy (info / recreate / setprize / setfee)", "/pa economy info",
                    " If you have linked Parkour to a compatible Economy plugin, you can perform commands to set the fee to join courses, the prize for completing courses and recreate all the courses to synchronise with the config.");

        } else if (args[1].equalsIgnoreCase("createKit")) {
            displayHelpMessage(sender, "Create ParkourKit", "/pa createKit", null,
                    " A conversation will be started to allow you to create a new ParkourKit, which can be linked to a course to override the default blocks. Each Material chosen must be unique, but many materials can share the same action.");

        } else if (args[1].equalsIgnoreCase("editKit")) {
            displayHelpMessage(sender, "Create ParkourKit", "/pa editKit", null,
                    " A conversation will be started to allow you to edit an exising ParkourKit, you have the option to remove a material or add a new material with the corresponding action and related attributes.");

        } else if (args[1].equalsIgnoreCase("validateKit")) {
            displayHelpMessage(sender, "Validate ParkourKit", "/pa validateKit [Kit]", "/pa validateKit firekit",
                    " If you have manually created a ParkourKit, or something has gone wrong. You can validate a ParkourKit to find out where the problem has originated.");

        } else if (args[1].equalsIgnoreCase("sql")) {
            displayHelpMessage(sender, "Display SQL information", "/pa SQL", null,
                    " Display the SQL information of the server connected to the database.");

        } else if (args[1].equalsIgnoreCase("settings")) {
            displayHelpMessage(sender, "Display Parkour Settings", "/pa settings", null,
                    " Display the main Parkour settings for the server.");

        } else if (args[1].equalsIgnoreCase("request")) {
            displayHelpMessage(sender, "Request a Feature / Report a Bug", "/pa request", null,
                    " Have an idea for the plugin or found a bug you want to report? Click the link provided to navigate to the Parkour forums section.");

        } else if (args[1].equalsIgnoreCase("setlevel")) {
            displayHelpMessage(sender, "Set Player's ParkourLevel", "/pa setlevel (player) (level)", "/pa setlevel A5H73Y 10",
                    " You are able to manually set a player's ParkourLevel. Used to quickly test you've setup requirements correctly, or to reward a player manually.");

        } else if (args[1].equalsIgnoreCase("setrank")) {
            displayHelpMessage(sender, "Set Player's Parkour Rank", "/pa setrank (player) (rank)", "/pa setrank A5H73Y &6God",
                    " You are able to manually set a player's Parkour Rank. This will instantly update their chat prefix.");

        } else {
            sender.sendMessage(Static.getParkourString() + "Unrecognised command. Please find all available commands using '/pa cmds'");
        }
    }

    /**
     * Format and display command usage
     *
     * @param player
     * @param title
     * @param arguments
     * @param description
     */
    private static void displayCommandUsage(Player player, String title, String arguments, String description) {
        player.sendMessage(ChatColor.DARK_AQUA + "/pa " + ChatColor.AQUA + title +
                (arguments != null ? ChatColor.YELLOW + " " + arguments : "") +
                ChatColor.BLACK + " : " + ChatColor.WHITE + description);
    }

    /**
     * Display the sign command usage
     *
     * @param player
     * @param title
     * @param shortcut
     * @param description
     */
    private static void displaySignCommandUsage(Player player, String title, String shortcut, String description) {
        player.sendMessage(ChatColor.AQUA + title + ChatColor.YELLOW + " " + shortcut + ChatColor.BLACK + " : " + ChatColor.WHITE + description);
    }

    /**
     * Display the syntax and description for each Parkour command.
     * Will provide example if applicable.
     *
     * @param sender
     * @param title
     * @param syntax
     * @param example
     * @param description
     */
    private static void displayHelpMessage(CommandSender sender, String title, String syntax, String example, String description) {
        sender.sendMessage("=== " + ChatColor.AQUA + title + ChatColor.WHITE + " ===");
        sender.sendMessage(ChatColor.GRAY + " Syntax: " + ChatColor.WHITE + syntax);
        if (example != null) {
            sender.sendMessage(ChatColor.GRAY + " Example: " + ChatColor.WHITE + example);
        }
        sender.sendMessage("=== " + ChatColor.DARK_AQUA + "Description" + ChatColor.WHITE + " ===");
        sender.sendMessage(description);
    }

    /**
     * Display relevant command pages
     * If signs is specified, will display the available sign commands.
     * If no page is specified, will display the commands menu.
     *
     * @param args
     * @param player
     */
    public static void processCommandsInput(String[] args, Player player) {
        if (args.length == 1) {
            displayCommandsIndex(player);

        } else if (args[1].equals("1")) {
            displayBasicCommands(player);

        } else if (args[1].equals("2")) {
            displayCreatingCommands(player);

        } else if (args[1].equals("3")) {
            displayConfigureCommands(player);

        } else if (args[1].equals("4")) {
            displayAdminCommands(player);

        } else if (args[1].equalsIgnoreCase("signs")) {
            displaySignCommands(player);

        } else {
            player.sendMessage(Static.getParkourString() + "Invalid page!");
            displayCommandsIndex(player);
        }
    }

    /**
     * Display commands menu
     *
     * @param player
     */
    private static void displayCommandsIndex(Player player) {
        player.sendMessage(Utils.getStandardHeading("Parkour Commands Menu"));

        player.sendMessage("Please choose the desired command type:");
        player.sendMessage(" 1" + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + "Basics");
        player.sendMessage(" 2" + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + "Creating a course");
        player.sendMessage(" 3" + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + "Configuring a course");
        player.sendMessage(" 4" + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + "Admin");
        player.sendMessage(" signs" + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + "Sign commands");
        player.sendMessage("");
        player.sendMessage(ChatColor.DARK_GRAY + "Example: " + ChatColor.GRAY + "/pa cmds 1");
        player.sendMessage(ChatColor.DARK_GRAY + "Remember: " + ChatColor.AQUA + "()" + ChatColor.GRAY + " means required, " + ChatColor.AQUA + "[]" + ChatColor.GRAY + " means optional.");
    }

    /**
     * Display the basic Parkour commands
     *
     * @param player
     */
    private static void displayBasicCommands(Player player) {
        player.sendMessage(Utils.getStandardHeading("Basic Commands"));

        displayCommandUsage(player, "join", "(course / courseId)", "Join the course");
        displayCommandUsage(player, "leave", null, "Leave the course");
        displayCommandUsage(player, "info", "[Player]", "Display your players Parkour information");
        displayCommandUsage(player, "stats", "(course)", "Display the course information");
        displayCommandUsage(player, "lobby", "[lobby]", "Teleport to the specified lobby");
        displayCommandUsage(player, "perms", null, "Display your Parkour permissions");
        displayCommandUsage(player, "like / dislike", null, "Vote for course you finished");
        displayCommandUsage(player, "list", "(type)", "Display appropriate list");
        displayCommandUsage(player, "quiet", null, "Toggle visibility of Parkour messages");
        displayCommandUsage(player, "invite", "(player)", "Invite the player to the course");
        displayCommandUsage(player, "challenge", "(course) (player) [wager]", "Challenge player to course");
        displayCommandUsage(player, "joinall", null, "Join All Courses Menu");
        displayCommandUsage(player, "help | contact", null, "To get help or contact me");
        displayCommandUsage(player, "about | version", null, "Display Parkour information");
    }

    /**
     * Display the creating Parkour commands
     *
     * @param player
     */
    private static void displayCreatingCommands(Player player) {
        player.sendMessage(Utils.getStandardHeading("Create Commands"));

        displayCommandUsage(player, "create", "(course)", "Create and select a course");
        displayCommandUsage(player, "checkpoint", "[point]", "Create (or overwrite) a checkpoint");
        displayCommandUsage(player, "kit", "[Kit]", "Retrieve relevant ParkourKit");
        displayCommandUsage(player, "select", "(course)", "Start editing the course");
        displayCommandUsage(player, "done", null, "Stop editing the course");
        displayCommandUsage(player, "setstart", null, "Set selected course start to current position");
        displayCommandUsage(player, "setautostart", "(course)", "Create auto start for course");
        displayCommandUsage(player, "setcreator", "(course) (player)", "Set creator of course");
        displayCommandUsage(player, "setlobby", "[name] [level]", "Create / overwrite Parkour lobby");
        displayCommandUsage(player, "finish", "[course]", "Toggle the finish status of the chosen course");
        displayCommandUsage(player, "prize", "(course)", "Configure course prize");
        displayCommandUsage(player, "test", null, "Toggle Parkour test mode");
        displayCommandUsage(player, "leaderboard", "[course] [amount] [type]", "Show leaderboards");
        displayCommandUsage(player, "tutorial", null, "Link to the official tutorial page");
    }

    /**
     * Display the configure Parkour commands
     *
     * @param player
     */
    private static void displayConfigureCommands(Player player) {
        player.sendMessage(Utils.getStandardHeading("Configure Commands"));

        displayCommandUsage(player, "tp / tpc", "(course)", "Teleport to course / checkpoint");
        displayCommandUsage(player, "link", "(argument) (argument)", "Link a course");
        displayCommandUsage(player, "linkKit", "(course) (kit)", "Link ParkourKit");
        displayCommandUsage(player, "listKit", "[kit]", "Display ParkourKit info");
        displayCommandUsage(player, "setmode", "(course)", "Set Parkour Mode");
        displayCommandUsage(player, "setjoinitem", "(course) (material) (amount)", "Join item");
        displayCommandUsage(player, "setminlevel", "(course) (level)", "Set course minimum level");
        displayCommandUsage(player, "setmaxdeath", "(course) (deaths)", "Set course max deaths");
        displayCommandUsage(player, "setmaxtime", "(course) (seconds)", "Set course time limit");
        displayCommandUsage(player, "rewardonce", "(course)", "Toggle if the prize is given once");
        displayCommandUsage(player, "rewardlevel", "(course) (level)", "Reward level on complete");
        displayCommandUsage(player, "rewardleveladd", "(course) (amount)", "Reward level addon");
        displayCommandUsage(player, "rewardrank", "(level) (rank)", "Reward rank on complete");
        displayCommandUsage(player, "rewardparkoins", "(course) (amount)", "Reward Parkoins");
        displayCommandUsage(player, "rewarddelay", "(course) (delay)", "Reward cool-down (days)");
    }

    /**
     * Display the admin Parkour commands
     *
     * @param player
     */
    private static void displayAdminCommands(Player player) {
        player.sendMessage(Utils.getStandardHeading("Admin Commands"));

        displayCommandUsage(player, "recreate", null, "Fix course database");
        displayCommandUsage(player, "delete", "(feature) (argument)", "Delete various features");
        displayCommandUsage(player, "reset", "(feature) (argument)", "Reset various features");
        displayCommandUsage(player, "whitelist", "(command)", "Whitelist a command");
        displayCommandUsage(player, "economy", null, "Display economy menu");
        displayCommandUsage(player, "createKit", null, "Start ParkourKit creation");
        displayCommandUsage(player, "editKit", null, "Edit existing ParkourKit");
        displayCommandUsage(player, "validateKit", "[Kit]", "Validate ParkourKit");
        displayCommandUsage(player, "setlevel", "(player) (level)", "Set ParkourLevel");
        displayCommandUsage(player, "setrank", "(player) (rank)", "Set Parkour Rank");
        displayCommandUsage(player, "sql", null, "Display SQL menu");
        displayCommandUsage(player, "settings", null, "Display Parkour Settings");
        displayCommandUsage(player, "request / bug", null, "Display relevant info");
    }

    /**
     * Display the sign Parkour commands
     *
     * @param player
     */
    private static void displaySignCommands(Player player) {
        player.sendMessage(Utils.getStandardHeading("Parkour Sign Commands"));

        player.sendMessage(ChatColor.DARK_AQUA + "[pa]");
        displaySignCommandUsage(player, "Join", "(j)", "Join sign for a Parkour course");
        displaySignCommandUsage(player, "Checkpoint", "(c)", "Checkpoint for course");
        displaySignCommandUsage(player, "Finish", "(f)", "Optional finish sign for a Parkour course");
        displaySignCommandUsage(player, "Lobby", "(l)", "Teleport to Parkour lobby");
        displaySignCommandUsage(player, "Leave", "(le)", "Leave the current course");
        displaySignCommandUsage(player, "Effect", "(e)", "Apply a Parkour effect");
        displaySignCommandUsage(player, "Stats", "(s)", "Display course stats");
        displaySignCommandUsage(player, "Leaderboards", "(lb)", "Display course leaderboards");

        player.sendMessage(ChatColor.YELLOW + "() = shortcuts");
    }

    /**
     * Display the economy information
     * Menu is displayed and relevant action will be executed on parameters.
     *
     * @param args
     * @param player
     */
    public static void displayEconomy(String[] args, Player player) {
        if (!Static.getEconomy()) {
            player.sendMessage(Static.getParkourString() + "Vault has not been linked.");
            return;
        }

        if (args[1].equalsIgnoreCase("info")) {
            player.sendMessage(Static.getParkourString() + "Successfully linked with Vault.");

        } else if (args[1].equalsIgnoreCase("setprize")) {
            if (args.length != 4) {
                player.sendMessage(Utils.invalidSyntax("econ", "setprize (course) (amount)"));
                return;
            }
            if (!CourseMethods.exist(args[2])) {
                player.sendMessage(Utils.getTranslation("Error.NoExist").replace("%COURSE%", args[2]));
                return;
            }
            if (!Validation.isPositiveInteger(args[3])) {
                player.sendMessage(Static.getParkourString() + "Amount needs to be numeric.");
                return;
            }

            Parkour.getParkourConfig().getEconData().set("Price." + args[2].toLowerCase() + ".Finish", Integer.parseInt(args[3]));
            Parkour.getParkourConfig().saveEcon();
            player.sendMessage(Static.getParkourString() + "Prize for " + args[2] + " set to " + args[3]);

        } else if (args[1].equalsIgnoreCase("setfee")) {
            if (args.length != 4) {
                player.sendMessage(Utils.invalidSyntax("econ", "setfee (course) (amount)"));
                return;
            }
            if (!CourseMethods.exist(args[2])) {
                player.sendMessage(Utils.getTranslation("Error.NoExist").replace("%COURSE%", args[2]));
                return;
            }
            if (!Validation.isPositiveInteger(args[3])) {
                player.sendMessage(Static.getParkourString() + "Amount needs to be numeric.");
                return;
            }

            Parkour.getParkourConfig().getEconData().set("Price." + args[2].toLowerCase() + ".JoinFee", Integer.parseInt(args[3]));
            Parkour.getParkourConfig().saveEcon();
            player.sendMessage(Static.getParkourString() + "Fee for " + args[2] + " set to " + args[3]);

        } else if (args[1].equalsIgnoreCase("recreate")) {
            player.sendMessage(Static.getParkourString() + "Starting Recreation...");
            int changed = recreateEconomy();
            player.sendMessage(Static.getParkourString() + "Process Complete! " + changed + " courses updated.");

        } else {
            player.sendMessage(Utils.invalidSyntax("econ", "(info / recreate / setprize / setfee)"));
        }
    }

    /**
     * Recreate the courses economy information
     * This includes the cost to join and the finish prize
     *
     * @return int
     */
    private static int recreateEconomy() {
        FileConfiguration econ = Parkour.getParkourConfig().getEconData();

        int updated = 0;
        for (String course : CourseInfo.getAllCourses()) {
            try {
                if (!(Parkour.getParkourConfig().getEconData().contains("Price." + course + ".JoinFee"))) {
                    updated++;
                    econ.set("Price." + course + ".JoinFee", 0);
                }
                if (!(Parkour.getParkourConfig().getEconData().contains("Price." + course + ".Finish"))) {
                    econ.set("Price." + course + ".Finish", 0);
                }
            } catch (Exception ex) {
                Utils.log(Utils.getTranslation("Error.Something", false).replace("%ERROR%", ex.getMessage()));
            }
        }

        Parkour.getParkourConfig().saveEcon();
        return updated;
    }

    public static void displaySQL(Player player) {
        player.sendMessage(Utils.getStandardHeading("SQL Details"));
        player.sendMessage("Type: " + DatabaseMethods.type);
        player.sendMessage("Connected: " + (Parkour.getDatabase().getConnection() != null));
        if (DatabaseMethods.type == DatabaseType.SQLite) {
            player.sendMessage("Database location: " + Parkour.getPlugin().getDataFolder() + File.separator + "sqlite-db" + File.separator + "parkour.db");
        }
    }

    /**
     * Display all relevant Parkour settings
     *
     * @param sender
     */
    public static void displaySettings(CommandSender sender) {
        sender.sendMessage(Utils.getStandardHeading("Parkour Settings"));

        sender.sendMessage("Version: " + ChatColor.AQUA + Static.getVersion());
        sender.sendMessage("Economy: " + ChatColor.AQUA + Static.getEconomy());
        sender.sendMessage("BountifulAPI: " + ChatColor.AQUA + Static.getBountifulAPI());
        sender.sendMessage("Disable Commands: " + ChatColor.AQUA + Parkour.getSettings().isDisableCommandsOnCourse());
        sender.sendMessage("Enforce world: " + ChatColor.AQUA + Parkour.getSettings().isEnforceWorld());
        sender.sendMessage("Less checks: " + ChatColor.AQUA + Parkour.getSettings().isAttemptLessChecks());

        sender.sendMessage(ChatColor.GRAY + "If you want more settings displayed, please ask");
    }

}
