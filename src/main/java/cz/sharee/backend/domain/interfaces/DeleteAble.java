package cz.sharee.backend.domain.interfaces;

/**
 * This interface is implemented by entities, that are DeleteAble, but will stay in the database
 * See {@link cz.sharee.backend.domain.profile.Post} as an example
 *
 * @author Jan Cahlik
 */
public interface DeleteAble {
    void delete();
}