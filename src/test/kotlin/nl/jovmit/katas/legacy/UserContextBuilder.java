package nl.jovmit.katas.legacy;

class UserContextBuilder {

    private boolean isFeatureEnabled;

    public static UserContextBuilder aUserContext() {
        return new UserContextBuilder();
    }

    public UserContextBuilder withFeatureEnabled() {
        this.isFeatureEnabled = true;
        return this;
    }

    public UserContext build() {
        UserContext userContext = new UserContext();
        userContext.setFeatureEnabled(isFeatureEnabled);
        return userContext;
    }
}
