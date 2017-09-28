package dk.rim.is.ic.inttests.aogd;

/**
 *
 * Created by maho on 20.06.2017.
 */
public class MeddelelseStatusMultiHentLiveTest extends MeddelelseStatusMultiHentTest {

    @Override
    protected String expectedFagsystemNavn() {
        return "EFI";
    }

    @Override
    protected String expectedDateString() {
        return "2016-11-24T14:37:20.000+02:00";
    }
}
