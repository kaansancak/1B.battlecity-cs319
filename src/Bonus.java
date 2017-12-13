public class Bonus extends GameObject {

    private int typeID; // 0 = lifeBonus, 1 = speedBonus
    private int releaseTime;
    private int effectDuration;
    private boolean released;
    private boolean taken;
    private boolean timeOut;

    public Bonus( int xLoc, int yLoc) {
        super(xLoc, yLoc);
    }

    private boolean isReleased() {
        return released = true;
    }

    private boolean isTaken() {
        return taken = true;
    }

    private boolean isTimeOut() {
        return timeOut = true;
    }

    private void applyBonus(Player player) {
        player.setHealth(player.getHealth() + 100);
    }
}
