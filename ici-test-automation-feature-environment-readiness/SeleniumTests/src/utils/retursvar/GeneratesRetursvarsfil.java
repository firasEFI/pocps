package utils.retursvar;

import java.io.File;
import java.io.IOException;

public interface GeneratesRetursvarsfil {

    /**
     * Create a file corresponding to an Indbetaling.
     * 
     * @param indbetaling
     *            The Indbetaling to turn into a file
     * @param outputType
     *            The desired payment type of the output file
     * @return the created Indbetalings-file
     * @throws IOException
     */
    public File opretRetursvarFil(Retursvar retursvar)
            throws IOException;

    /**
     * Uploads the File corresponding to an Indbetaling
     * 
     * @param inputFil
     *            The payment file that was generated
     * @param retursvarType
     *            The type of payment file
     */
    public void indsendRetursvarsFil(File inputFil, RetursvarType retursvarType);
}
