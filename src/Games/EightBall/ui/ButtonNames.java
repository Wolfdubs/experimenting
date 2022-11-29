package Games.EightBall.ui;

public enum ButtonNames {
    ROLL ("Roll Eight Ball"),
    EXIT ("Exit");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    public String getButtonName(){
        return name;
    }

}
