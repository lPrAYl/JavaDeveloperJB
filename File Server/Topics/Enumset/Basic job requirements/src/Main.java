import java.util.EnumSet;
import java.util.Scanner;

public class Main {

    enum Language {
        JAVA, C_PLUS_PLUS, PYTHON, C_SHARP, JAVA_SCRIPT, HTML, CSS
    }

    enum Role {
        WEB_DEVELOPER, DATA_SCIENTIST, JAVA_EXPERT, GAME_DEVELOPER,
        COMPETITIVE_CODER
    }

    // Do not change the method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String roleName = scanner.next();
        try {
            Role role = Role.valueOf(roleName);
            EnumSet<Language> languages = getRequirementsByRole(role);
            System.out.println("Job Code : " + role);
            System.out.println("Prerequisite : " + languages);
        } catch (IllegalArgumentException e) {
            System.out.println("Thank you for considering us but there is no vacancy.");
        }
    }

    // Implement the method
    public static EnumSet<Language> getRequirementsByRole(Role role) {
        EnumSet<Language> set = EnumSet.noneOf(Language.class);
        switch (role) {
            case WEB_DEVELOPER -> set = EnumSet.of(Language.HTML, Language.CSS, Language.JAVA_SCRIPT);
            case DATA_SCIENTIST -> set = EnumSet.of(Language.PYTHON);
            case JAVA_EXPERT -> set = EnumSet.of(Language.JAVA);
            case GAME_DEVELOPER -> set = EnumSet.of(Language.C_SHARP);
            case COMPETITIVE_CODER -> set = EnumSet.of(Language.C_PLUS_PLUS);
            default -> System.out.println("Error");
        }

        return set;
    }
}