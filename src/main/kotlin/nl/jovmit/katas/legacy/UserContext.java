package nl.jovmit.katas.legacy;

import java.util.UUID;

class UserContext {

    private boolean isFeatureEnabled;

    public UUID getUserId() {
        throw new UnsupportedOperationException("Not Implemented");
    }

    boolean isFeatureEnabled() {
        return isFeatureEnabled;
    }

    public void setFeatureEnabled(boolean isFeatureEnabled) {
        this.isFeatureEnabled = isFeatureEnabled;
    }
}
