package GameObject.MapPackage.BonusPackage;

import GameObject.GameObject;

public abstract  class Bonus extends GameObject {

    protected final int BONUS_VIEW_DIMENSION = 32;
    private int typeID; // 0 = lifeBonus, 1 = speedBonus
    private int releaseTime;
    private int effectDuration;
    private boolean released;
    private boolean taken = false;
    private boolean timeOut;


    private boolean isReleased() {
        return released;
    }

    public void setReleased( boolean released) {
        this.released = released;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken( boolean taken) {
        this.taken = taken;
    }

    @Override
    public boolean isPassableByTanks() {
        return true;
    }

    @Override
    public boolean isPassableByBullets() {
        return true;
    }

    @Override
    public boolean isHideable() {
        return false;
    }
}
