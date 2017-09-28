package findus_datamodelWarehouses;

import findus_datamodels.AfskrivningModel;
import modules.MO_Afskriv;

public class AfskrivningsWarehouse
{
    public final static AfskrivningModel val_afskrivingsflow = new AfskrivningModel(MO_Afskriv.Afskrivningsmulighed.PROCENT, 99, MO_Afskriv.Afskrivningsaarsag.EFTERGIVELSE);
}
