package findus_pageobjects._360_graders_overblik;

import findus_pageobjects.SubPage;
import findus_pageobjects.part.PartPage;
import findus_pageobjects.part.Part_PrimaerSubPage;
import icisel.pageobjects.elements.Link;
import icisel.pageobjects.elements.PageElement;
import icisel.pageobjects.frames.Frames;
import org.openqa.selenium.By;

public class _360GradersOverblik_PartsoplysningerSubPage extends SubPage<_360GradersOverblikPage> {

    final Link lnkNavn = new Link(Frames.zoneMapFrame_1, By.xpath("//span[@id=\"personInfo_name\"]/a"));

    public _360GradersOverblik_PartsoplysningerSubPage(_360GradersOverblikPage parentPage) {
        super(parentPage);
    }

    public Part_PrimaerSubPage gaaTilNavnPage(){
        lnkNavn.click();
        return new Part_PrimaerSubPage(new PartPage());
    }

    public String getCurrentTaxDebt() {
        return new PageElement(Frames.zoneMapFrame_1, By.id("personInfo_currentBalance")).getText();
    }

}
