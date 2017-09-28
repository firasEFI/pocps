package dk.rim.is.abt.nymf.entity;

/**
 * Interface to ensure entities has certain getters / setters
 * <p>
 * Created by      frbm
 * Creation date   22-11-2016
 */
public interface IEntity {
    Long getId();

    /**
     * ONLY USE THIS IN UNIT TESTS! DO NOT USE IT IN REAL CODE! THE ID MUST BE SET BY THE DATABASE!
     * @param id
     * @return
     */
    void setId(Long id);

}
