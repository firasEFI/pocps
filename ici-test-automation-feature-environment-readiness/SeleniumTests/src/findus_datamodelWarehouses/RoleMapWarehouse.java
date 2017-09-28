package findus_datamodelWarehouses;

import productabstractions.RoleMap;
import productabstractions.RoleMapImpl;
import productabstractions.Roles;

public class RoleMapWarehouse {
    public static final RoleMap SAGSBEHANDLER_GENEREL = new RoleMapImpl(Roles.SAGSBEHANDLER_GENEREL);

    public static final RoleMap SUPERBRUGER = new RoleMapImpl(true, true, false, true, true, false, true, true, true);

}
