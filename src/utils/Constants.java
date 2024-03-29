package utils;

import entities.Boss;
import entities.Enemy;
import entities.FollowEnemy;
import entities.PassiveEnemy;
import entities.Sniper;
import main.Game;

public class Constants {

    /**
     * is Debug enabled
     */
    public static final boolean DEBUG = false;

    /**
     * update values respect to scale
     */
    public static void updateScaleConsts() {
        UI.Buttons.B_WIDTH = (int) (UI.Buttons.B_WIDTH_DEFAULT * Game.SCALE);
        UI.Buttons.B_HEIGHT = (int) (UI.Buttons.B_HEIGHT_DEFAULT * Game.SCALE);
        UI.PauseButtons.SOUND_SIZE = (int) (UI.PauseButtons.SOUND_SIZE_DEFAULT * Game.SCALE);
        UI.URMButtons.URM_SIZE = (int) (UI.URMButtons.URM_DEFAULT_SIZE * Game.SCALE);
        UI.VolumeButtons.VOLUME_WIDTH = (int) (UI.VolumeButtons.VOLUME_DEFAULT_WIDTH * Game.SCALE);
        UI.VolumeButtons.VOLUME_HEIGHT = (int) (UI.VolumeButtons.VOLUME_DEFAULT_HEIGHT * Game.SCALE);
        UI.VolumeButtons.SLIDER_WIDTH = (int) (UI.VolumeButtons.SLIDER_DEFAULT_WIDTH * Game.SCALE);
        EnemyConstants.CRABBY_WIDTH = (int) (EnemyConstants.CRABBY_WIDTH_DEFAULT * Game.SCALE);
        EnemyConstants.CRABBY_HEIGHT = (int) (EnemyConstants.CRABBY_HEIGHT_DEFAULT * Game.SCALE);
    }

    /**
     * ui constants
     */
    public static class UI {

        /**
         * Buttons constants
         */
        public static class Buttons {

            public final static int B_WIDTH_DEFAULT = 140;
            public final static int B_HEIGHT_DEFAULT = 56;
            public static int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }

        /**
         * Pause buttons constants
         */
        public static class PauseButtons {

            public static final int SOUND_SIZE_DEFAULT = 42;
            public static int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
        }

        /**
         * Unpause-Reset-Menu Buttons
         */
        public static class URMButtons {

            public static final int URM_DEFAULT_SIZE = 56;
            public static int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);

        }

        /**
         * Volume buttons constants
         */
        public static class VolumeButtons {

            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }

        public static class TextInput {

            public final static int TI_WIDTH_DEFAULT = 404;
            public final static int TI_HEIGHT_DEFAULT = 44;
            public static int TI_WIDTH = (int) (TI_WIDTH_DEFAULT * Game.SCALE);
            public static int TI_HEIGHT = (int) (TI_HEIGHT_DEFAULT * Game.SCALE);
        }
    }

    /**
     * directions constants
     */
    public static class Directions {

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    /**
     * player constants
     */
    public static class PlayerConstants {

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case RUNNING -> {
                    return 6;
                }
                case IDLE -> {
                    return 5;
                }
                case HIT -> {
                    return 4;
                }
                case JUMP,
                     ATTACK_1,
                     ATTACK_JUMP_1,
                     ATTACK_JUMP_2 -> {
                    return 3;
                }
                case GROUND -> {
                    return 2;
                }
                case FALLING -> {
                    return 1;
                }
                default -> {
                    return 0;
                }
            }
        }
    }

    /**
     * enemies constants
     */
    public static class EnemyConstants {

        public static enum enemyType {
            PASSIVE_ENEMY, FOLLOW_ENEMY, SNIPER, BOSS;
            
            public static enemyType getFromClass(Enemy c){
                if(c instanceof PassiveEnemy){
                    return  PASSIVE_ENEMY;
                }else if(c instanceof FollowEnemy){
                    return  FOLLOW_ENEMY;
                }else if(c instanceof Sniper){
                    return  SNIPER;
                }else if(c instanceof Boss){
                    return  BOSS;
                }
                return null;
            }
        }
        
        
        
        public static enum enemyAtlas {
            CRABBY, SNIPER, BOSS;
        }

        public static enum enemyState {
            IDLE, RUNNING, ATTACK, HIT, DEAD;
        }

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;

        public static int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
        public static int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);

        /**
         * get sprites for every action for every enemy type
         *
         * @param enemy_atlas
         * @param enemy_state
         * @return the amount of sprites of a enemy atlas of a specific
         * animation;
         */
        public static int GetSpriteAmount(enemyAtlas enemy_atlas, enemyState enemy_state) {

            switch (enemy_atlas) {
                case CRABBY:
                    switch (enemy_state) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                        default:
                            return 0;
                    }
                case SNIPER:
                    switch (enemy_state) {
                        case IDLE:
                            return 1;
                        default:
                            return 0;
                    }
                case BOSS:
                    switch (enemy_state) {
                        case IDLE:
                            return 3;
                        default:
                            return 0;
                    }
            }
            return 0;

        }

    }
}
