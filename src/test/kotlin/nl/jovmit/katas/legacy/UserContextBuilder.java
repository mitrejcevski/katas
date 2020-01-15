package nl.jovmit.katas.legacy;

import java.util.UUID;

class UserContextBuilder {

    private boolean isFeatureEnabled;
    private UUID userId;

    public static UserContextBuilder aUserContext() {
        return new UserContextBuilder();
    }

    public UserContextBuilder withFeatureEnabled() {
        this.isFeatureEnabled = true;
        return this;
    }

    public UserContextBuilder withUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public UserContext build() {
        UserContext userContext = new UserContext();
        userContext.setUserId(userId);
        userContext.setFeatureEnabled(isFeatureEnabled);
        return userContext;
    }
}
