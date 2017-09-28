package findus_pageobjects;

import icisel.pageobjects.elements.Checkbox;
import icisel.pageobjects.elements.DatePicker;
import icisel.pageobjects.elements.Dropdown;
import icisel.pageobjects.elements.Input;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    public static final void setTextBoxTextIfNotNull(Input textBox, Double value) {
        if (value != null)
            setTextBoxTextIfNotNull(textBox, formatDouble(value));
    }

    public static final void setTextBoxTextIfNotNull(Input textBox, Integer value) {
        if(value != null)
            setTextBoxTextIfNotNull(textBox, value.toString());
    }

    public static final void setTextBoxTextIfNotNull(Input textBox, Calendar value) {
        //if(Calendar != null)

    }

    public static final void setTextBoxTextIfNotNull(Input textBox, String text) {
        if(textBox == null)
            throw new IllegalArgumentException("textBox cannot be null");

        if(text != null) {
            textBox.clear();
            textBox.sendKeys(text);
        }
    }

    public static final void setCheckboxCheckedIfNotNull(Checkbox checkbox, Boolean checked) {
        if(checked != null)
            if(checkbox.isSelected() != checked)
                checkbox.click();
    }

    public static final void setDropdownVisibleTextIfNotNull(Dropdown dropdown, DropdownOption option) {
        if(option != null)
            dropdown.pickByVisibleText(option.getVisibleText());
    }

    public static final void setDropdownVisibleTextIfNotNull(Dropdown dropdown, String stringOption) {
        if(stringOption != null)
            dropdown.pickByVisibleText(stringOption);
    }

    public static final void setDatePickerValueIfNotNull(DatePicker datePicker, Calendar date) {
        if(date != null)
            datePicker.sendKeys(formatDate(date));
    }

    public static final void setDatePickerValueIfNotNull(DatePicker datePicker, LocalDate date) {
        if(date != null) {
            datePicker.clear();

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            datePicker.sendKeys(date.format(format));
        }
    }

    static String formatDouble(double value) {
        Locale localeDk = new Locale("da", "DK");

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(localeDk);
        otherSymbols.setDecimalSeparator(',');

        DecimalFormat df = new DecimalFormat("#.##", otherSymbols);

        return df.format(value);
    }

    public static String formatDate(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        return sdf.format(date.getTime());
    }

    public static String createXPathForLabelText(String elementTagName, String labelText) {
        return String.format("");
    }
}
