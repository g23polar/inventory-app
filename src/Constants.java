import java.util.ArrayList;
import java.util.List;

public final class Constants {

    private Constants() {
        // restrict instantiation
    }
    // define rules here
    public static final Rule allowpartial = new Rule("allowpartial");

    // add to end of list
    public static final List<Rule> SUPPORTED_RULES = List.of(
            allowpartial
    );

}
