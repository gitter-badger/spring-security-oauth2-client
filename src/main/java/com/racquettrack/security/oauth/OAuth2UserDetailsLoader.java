package com.racquettrack.security.oauth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.UUID;

/**
 * Interface that allows for retrieving a {@link UserDetails} object from the internal system and for creating
 * the representation of the user in the internal system if it doesn't exist.
 *
 * @see OAuth2UserDetailsService
 *
 * @author paul.wheeler
 */
public interface OAuth2UserDetailsLoader {

    /**
     * Retrieves the {@link UserDetails} object.
     * @param uuid The {@link UUID} of the user in the OAuth Provider's system.
     * @return The {@link UserDetails} of the user if it exists, or null if it doesn't.
     */
    public UserDetails getUserByUserId(UUID uuid);

    /**
     * Expected to be called only when the user described by {@param userInfo} has already been determined to not
     * exist in the system. Implementations should return true when it is okay for the user to be created. For example,
     * implementations may want to return false if a significant time has elapsed between the user being created in
     * the OAuth Provider and now.
     * @param userInfo A {@link Map} of the JSON user object returned by the OAuth Provider.
     * @return True if it is okay to create the user.
     */
    public boolean isCreatable(Map<String, Object> userInfo);

    /**
     * Creates a new user in the internal system. The internal system should store the {@param id} of the User to
     * establish a link between the OAuth Provider and the internal system.
     * @param id The id of the user given by the OAuth Provider.
     * @param userInfo The user info object returned from the OAuth Provider.
     * @return
     */
    public UserDetails createUser(UUID id, Map<String, Object> userInfo);
}
