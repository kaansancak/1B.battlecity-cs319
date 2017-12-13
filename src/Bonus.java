public class Bonus extends GameObject {

    private int typeID; // 0 = lifeBonus, 1 = speedBonus
    private int releaseTime;
    private int effectDuration;
    private boolean released;
    private boolean taken = false;
    private boolean timeOut;

    public Bonus( int xLoc, int yLoc) {
        super(xLoc, yLoc);
    }

    public void setReleased( boolean released) {
        this.released = released;
    }

    private boolean isReleased() {
        return released;
    }

    public void setTaken( boolean taken) {
        this.taken = taken;
    }
    public boolean isTaken() {
        return taken;
    }

    private boolean isTimeOut() {
        return timeOut = true;
    }

    private void applyBonus(Tank tank) {
        tank.setHealth(tank.getHealth() + 100);
    }
}
