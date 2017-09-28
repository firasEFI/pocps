package utils.betaling;

import java.io.File;
import java.io.IOException;

public interface GeneratesBetalingsfil {

    /**
     * Create a file corresponding to an Indbetaling and Udbetaling.
     * 
     * @param betaling
     *            The Indbetaling or Udbetaling to turn into a file
     * @param outputType
     *            The desired payment type of the output file
     * @return the created Indbetalings or Udbetalings -file
     * @throws IOException
     */
    public File opretBetalingsfil(Betaling betaling, BetalingsfilType outputType)
            throws IOException;

    /**
     * Uploads the File corresponding to an Indbetaling and Udbetaling.
     * 
     * @param inputFil
     *            The payment file that was generated
     * @param betalingsfilType
     *            The type of payment file
     */
    public void indsendBetalingsfil(File inputFil, BetalingsfilType betalingsfilType);

}
