package nl.jovmit.katas.legacy;

import java.util.UUID;

class UserContext {

    private boolean isFeatureEnabled;
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    boolean isFeatureEnabled() {
        return isFeatureEnabled;
    }

    public void setFeatureEnabled(boolean isFeatureEnabled) {
        this.isFeatureEnabled = isFeatureEnabled;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
