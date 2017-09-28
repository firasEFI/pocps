package dk.rim.is.ic.inttests.util;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 24.01.2017
 */
@FunctionalInterface
public interface Action {
    void execute() throws Exception;
}
