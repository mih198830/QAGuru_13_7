package amazTest;

public enum EnumExample {
    SUNDAY ("vsk"), MONDAY ("pnd"), TUESDAY ("vt"), WEDNESDAY("sr"),
    THURSDAY("ct"), FRIDAY("pt"), SATURDAY("sb");

    public final String desc;

    EnumExample(String desc) {
        this.desc = desc;
    }
}
