package findus_datamodels;

import findus_pageobjects.DropdownOption;

public class BetalingsevneEnums {

    public enum Afdrag implements DropdownOption {
        _0("0", "0"),
        _4("4", "4"),
        _5("5", "5"),
        _6("6", "6"),
        _7("7", "7"),
        _8("8", "8"),
        _9("9", "9"),
        _10("10", "10"),
        _11("11", "11"),
        _12("12", "12"),
        _13("13", "13"),
        _14("14", "14"),
        _15("15", "15"),
        _16("16", "16"),
        _17("17", "17"),
        _18("18", "18"),
        _19("19", "19"),
        _20("20", "20"),
        _21("21", "21"),
        _22("22", "22"),
        _23("23", "23"),
        _24("24", "24"),
        _25("25", "25"),
        _26("26", "26"),
        _27("27", "27"),
        _28("28", "28"),
        _29("29", "29"),
        _30("30", "30");

        private final String value;
        private final String visibleText;

        public String getValue() {
            return this.value;
        }

        public String getVisibleText() {
            return this.visibleText;
        }

        private Afdrag(String value, String visibleText) {
            this.value = value;
            this.visibleText = visibleText;
        }
    }
}
