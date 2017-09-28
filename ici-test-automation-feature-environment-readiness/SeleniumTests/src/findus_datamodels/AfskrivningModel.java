package findus_datamodels;

import modules.MO_Afskriv;

public class AfskrivningModel
{
    public AfskrivningModel(MO_Afskriv.Afskrivningsmulighed afskrivningsmulighed, double dividendeProcent, MO_Afskriv.Afskrivningsaarsag afskrivningsaarsag)
    {
        this.afskrivningsmulighed = afskrivningsmulighed;
        this.dividendeProcent = dividendeProcent;
        this.afskrivningsaarsag = afskrivningsaarsag;
    }

    public MO_Afskriv.Afskrivningsmulighed afskrivningsmulighed;
    public double dividendeProcent;
    public MO_Afskriv.Afskrivningsaarsag afskrivningsaarsag;
}
