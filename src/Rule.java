import lombok.Data;

@Data
public class Rule {

    private final String ruleName;

    public Rule(String ruleName) {
        this.ruleName = ruleName;
    }

    protected boolean equals(Rule otherRule) {
        return this.ruleName.equals(otherRule.getRuleName());
    }

    public String getRuleName() {
        return this.ruleName;
    }
}
